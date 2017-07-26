package com.xinda.task;

import java.math.BigDecimal;

import gnu.io.SerialPort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xinda.entity.Meter;
import com.xinda.service.MeterService;
import com.xinda.service.PriceService;
import com.xinda.util.SerialTool;

@Component
public class AutomaticTask {
	@Autowired
	private MeterService meterService;
	@Autowired
	private PriceService priceService;
	/**
	 * 记录每日消费额
	 * 每天4点统计
	 */
	@Scheduled(cron="0 0 4 * ? *")
	public void consumptionByDay(){
		BigDecimal price=priceService.findPriceByActive((byte)1).getPrice();
		SerialTool st=new SerialTool();
		st.scanPorts();
		st.openSerialPort("COM3");
		st.setSeriaPortParam(1200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_EVEN);
		/*打算用while循环读取，若某次读取失败则重复读取
		 * 弊端：容易进死循环。如果不是通讯原因造成读取失败，如数据库中存的地址值就是错误会造成死循环*/
		for(Meter m:meterService.findAllMeters()){
			String addrStr=m.getMeterContactAddress();
			double oldval=Double.parseDouble(m.getMeterTotalValue());
			int len=addrStr.length();
			byte[] addr=new byte[len/2];
			for(int i=0;i<len;i+=2){
				addr[i/2]=(byte)Long.parseLong(addrStr.substring(i, i+2));
			}
			st.sendDataToSeriaPort(SerialTool.getValueCommand(addr));
			String redata=st.getReceive();//6804000000000068（81）0643C3（33333333）2D16
			double value=0;
			if("81".equals(redata.substring(16, 18))){//成功接收并且无后续数据帧
				String reval=redata.substring(24,32);
				for(int i=0;i<reval.length();i+=2){
					/*计算返回的电量
					 * 因为返回的值是16进制加33H并倒序处理的
					 * 所以还原时第一个两位数是低位（小数位），第二个两位数是个位和十位，第三各是百位和千位
					 * 即取得的一个数要乘以10的-2次方，第二个乘以10的0次方，第三个乘以10的2次方
					 */
					value+=(Integer.parseInt(reval.substring(i,i+2), 16)-51)*Math.pow(10, i-2);
				}
				BigDecimal balance=m.getMeterBalance();
				BigDecimal consumption=price.multiply(new BigDecimal(value-oldval));
				m.setMeterTotalValue(Double.toString(value));
				if(balance.compareTo(consumption)<0){//余额不足
					m.setMeterBalance(BigDecimal.ZERO);
					m.setMeterCurrentOverdraft(m.getMeterCurrentOverdraft().add(consumption).subtract(balance));
				}else{
					m.setMeterBalance(balance.subtract(consumption));
				}
				m.setMeterTotalConsumption(m.getMeterTotalConsumption().add(consumption));
				meterService.saveMeter(m);
			}
		}
	}
}
