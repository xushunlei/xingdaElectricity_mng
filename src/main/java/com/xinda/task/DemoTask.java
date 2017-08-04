package com.xinda.task;

import gnu.io.SerialPort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xinda.entity.Meter;
import com.xinda.service.MeterService;
import com.xinda.util.SerialTool;

@Component//实现类上要有组件的注解@Component
public class DemoTask {
	@Autowired
	private MeterService meterService;
	/*CronTrigger配置完整格式为： [秒] [分] [小时] [日] [月] [周] ([年])
	 * 允许的值[0-59] [0-59] [0-23] [1-31] [1-12或JAN-DEC] [1-7或SUN-SAT] [empty 或1970-2099]
	 * 通配符：
	 * 		* 表示所有值. 例如:在分的字段上设置 "*",表示每一分钟都会触发。
	 * 		- 表示区间。例如 在小时上设置 "10-12",表示 10,11,12点都会触发。
	 * 		, 表示指定多个值，例如在周字段上设置 "MON,WED,FRI" 表示周一，周三和周五触发
	 * 		/ 用于递增触发。如在秒上面设置"5/15" 表示从5秒开始，每增15秒触发(5,20,35,50)。 
	 * 			在月字段上设置'1/3'所示每月1号开始，每隔三天触发一次。
	 * 	日和周适用的通配符：
	 * 		? 表示不指定值。使用的场景为不需要关心当前设置这个字段的值。
	 * 			例如:要在每月的10号触发一个操作，但不关心是周几0 0 0 10 * ?
	 * 		L 表示最后的意思。在日字段设置上，表示当月的最后一天，在周字段上表示星期六。
	 * 			如果在"L"前加上数字，则表示该数据的最后一个。例如在周字段上设置"6L"这样的格式,则表示“本月最后一个星期五"
	 * 		C 它是"calendar"的缩写，表示为基于相关的日历所计算出的值，如果没有关联的日历，那它等同于包含全部日历。
	 * 			日字段"5C"表示日历中的第一天或5号以后；周字段"1C"表示日历中的第一天或周日以后
	 * 	只适用周的通配符：
	 * 		# 序号(表示每月的第几个周几)，
	 * 			例如在周字段上设置"6#3"表示在每月的第三个周六.注意如果指定"#5",正好第五周没有周六，则不会触发该配置
	 * 	只适用日的通配符：
	 * 		W 表示离指定日期的最近那个工作日(周一至周五)。
	 * 			例如在日字段上设置"15W"，表示离每月15号最近的那个工作日触发。
	 * 			(注，"W"前只能设置具体的数字,不允许区间"-"。日字段'LW',意为"该月最后一个工作日")。
	 */
	@Scheduled(cron="0 49 3 * * ?")//需要写在实现方法上
	public void sayHi(){
	/*定时器的任务方法不能有返回值
	 * （如果有返回值，spring初始化的时候会告诉你有个错误、需要设定一个proxytargetclass的某个值为true）*/
		SerialTool st = new SerialTool();
		st.scanPorts();
		st.openSerialPort("COM3");
		st.setSeriaPortParam(1200, SerialPort.DATABITS_8,
				SerialPort.STOPBITS_1, SerialPort.PARITY_EVEN);
		for (Meter m : meterService.findAllMeters()){
			String addrStr = m.getMeterContactAddress();
			if(addrStr==null||addrStr.trim()==""){
				continue;
			}
			int len = addrStr.length();
			byte[] addr = new byte[len / 2];
			for (int i = 0; i < len; i += 2) {
				addr[i / 2] = (byte) Long.parseLong(addrStr.substring(i, i + 2));
			}
			st.sendDataToSeriaPort(SerialTool.getValueCommand(addr));
			String redata = st.getReceive();
			System.out.println("getword:"+redata);
		}
		st.closeSerialPort();
		System.out.println("hey");
	}
	//@Scheduled(fixedRate = 1000 * 10)//表示从上一个任务开始到下一个任务开始的间隔.10秒执行一次
	public void job(){
		System.out.println("to do something...");
		
	}
}
