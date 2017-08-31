package com.xinda.task;

import java.math.BigDecimal;
import java.util.Date;

import gnu.io.SerialPort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xinda.entity.Log;
import com.xinda.entity.Meter;
import com.xinda.service.LogService;
import com.xinda.service.MeterService;
import com.xinda.service.PriceService;
import com.xinda.util.SerialTool;

@Component
public class AutomaticTask {
	@Autowired
	private MeterService meterService;
	@Autowired
	private PriceService priceService;
	@Autowired
	private LogService logService;

	/**30 0 4 * ? *
	 * 记录每日消费额 每天4点统计
	 * 正常应该全天记录。
	 */
	@Scheduled(cron = "30 0 4 * * ?")
	public void consumptionByDay() {
		//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		//当前电价
		BigDecimal price = priceService.findPriceByActive(1).getPriceValue();
		SerialTool st = new SerialTool();
		st.scanPorts();
		st.openSerialPort("COM3");
		/*
		 * 打算用while循环读取，若某次读取失败则重复读取
		 * 弊端：容易进死循环。如果不是通讯原因造成读取失败，如数据库中存的地址值就是错误会造成死循环
		 */
		for (Meter m : meterService.findAllMeters()) {
			String addrStr = m.getMeterAddress();
			if(addrStr==null||addrStr.trim()==""){
				continue;
			}
			//原电量
			BigDecimal oldval = m.getMeterValue()==null?BigDecimal.ZERO:m.getMeterValue();
			st.setSeriaPortParam(m.getMeterRate(), SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_EVEN);
			System.out.println("地址"+addrStr);
			if(addrStr.length()%2==1){
				addrStr="0"+addrStr;
			}
			int len = addrStr.length();
			byte[] addr = new byte[6];
			for (int i = 0; i < len/2; i ++) {
				/*完成了地址域的倒序和补位*/
				addr[i]=(byte) Long.parseLong(addrStr.substring(len-i*2-2, len-i*2),16);
			}
			st.sendDataToSeriaPort(SerialTool.getValueCommand(addr));
			double value = 0;
			String receive=new String(st.getReceive());
			System.out.println("接收来自"+m.getMeterName()+"的返回数据："+receive+",长度："+receive.length()+"\n");
			if(receive.length()>=26&&receive.endsWith("16")){//接收到完整返回帧
				receive=receive.split("68"+st.toHexString(addr)+"68")[1];
				if(receive.startsWith("81")){//正常应答且无后续数据帧
					String reval = receive.substring(8, 16);
					for (int i = 0; i < reval.length(); i += 2) {
						/*
						 * 计算返回的电量 因为返回的值是16进制加33H并倒序处理的
						 * 所以还原时第一个两位数是低位（小数位），第二个两位数是个位和十位，第三各是百位和千位
						 * 即取得的一个数要乘以10的-2次方，第二个乘以10的0次方，第三个乘以10的2次方
						 */
						value += (Integer.parseInt(reval.substring(i, i + 2), 16) - 51)
								* Math.pow(10, i - 2);
					}
					// 当前电表值
					BigDecimal curval=new BigDecimal(value);
					// 余额
					BigDecimal balance = m.getMeterBalance();
					// 用量
					BigDecimal useval = curval.subtract(oldval);
					// 消费额
					BigDecimal consumption = price.multiply(useval);
					m.setMeterValue(curval);
					if (balance.compareTo(consumption) < 0) {// 余额不足
						m.setMeterBalance(BigDecimal.ZERO);
						m.setMeterCurrOverdraft(m.getMeterCurrOverdraft()
								.add(consumption).subtract(balance));
						if(m.getMeterMaxOverdraft().compareTo(m.getMeterCurrOverdraft())<=0){//透支额度用尽
							st.sendDataToSeriaPort(SerialTool.getShutdownCommand(addr));
							m.setMeterStatus(2);//拉闸
						}else{
							m.setMeterStatus(1);//透支
						}
					} else {
						m.setMeterBalance(balance.subtract(consumption));
					}
					m.setMeterTotalPay(m.getMeterTotalPay().add(consumption));//更新总消费额
					System.out.println(m);
					meterService.saveMeter(m);
					Log log=new Log();
					log.setLogMeter(m);
					log.setLogUnitPrice(price);
					log.setLogValue(useval);
					log.setLogTotalPrice(consumption);
					log.setLogDate(new Date(System.currentTimeMillis()));
					logService.saveLog(log);
				}else if(receive.startsWith("C1")){
					System.out.println("电表错误应答");
				}
			}
		}
		st.closeSerialPort();
	}
	/**
	 * 每天00:00搜查2遍数据库中有没有当天要改电价的记录，有的话将电价修改
	 */
	@Scheduled(cron="0/30 0 0 * * ?")
	public void rechargePrice(){
		if(priceService.findPriceByActive(0)==null){//不存在待改价记录
			return;
		}
		priceService.tx_autoModify();
	}
}
