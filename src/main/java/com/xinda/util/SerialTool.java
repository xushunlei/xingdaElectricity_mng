package com.xinda.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.TooManyListenersException;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

public class SerialTool implements SerialPortEventListener {
	static Enumeration<CommPortIdentifier> portList;// 枚举类
	static CommPortIdentifier portId;
	static SerialPort serialPort;
    private OutputStream outputStream;  
    private InputStream inputStream;
    private int packetlength=1;
    private String receive="";
	static final char[] HEX_CHAR_TABLE = { '0', '1', '2', '3', '4', '5', '6',  
        '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	
	/** 
     * 扫描本机的所有COM端口 
     *  
     */  
    @SuppressWarnings("unchecked")
	public void scanPorts(){
    	portList = CommPortIdentifier.getPortIdentifiers();
//    	CommPortIdentifier portId;
//		while (portList.hasMoreElements()) {
//			portId = (CommPortIdentifier) portList.nextElement();
//			/* getPortType方法返回端口类型 */
//			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
//				String name = portId.getName();
//				if (!portList.contains(name)) {  
//                    portList.add(name);  
//                }
//			}
//		}
    	if(portList==null||!portList.hasMoreElements()){
    		System.out.println("未找到可用的串行端口号,程序无法启动!");
    	}
    }
    
    /** 
     * 打开串行端口 
     *  
     */  
    public void openSerialPort(String portname){
    	try {  
            portId = CommPortIdentifier.getPortIdentifier(portname);  
        } catch (NoSuchPortException e) {  
            System.out.println("抱歉,没有找到" + portname + "串行端口号!");  
            return;  
        }
    }
    
    /** 
     * 设置端口参数 
     *  
     * @param rate 波特率
     * @param data 数据位
     * @param stop 停止位
     * @param parity 校验
     */
    public void setSeriaPortParam(int rate, int data, int stop, int parity){
    	//打开串口
    	try {
			serialPort = (SerialPort) portId.open("SimpleReadApp", 2000);
		} catch (PortInUseException e) {
			System.out.println(serialPort.getName() + "端口已被占用,请检查!");  
            return;
		}
    	//设置串口参数
    	try {
			serialPort.setSerialPortParams(rate, data, stop, parity);
		} catch (UnsupportedCommOperationException e) {
			e.printStackTrace();
		}
    	// 打开端口的IO流管道
    	try {
			outputStream = serialPort.getOutputStream();
			inputStream = serialPort.getInputStream();  
		} catch (IOException e) {
			e.printStackTrace();
		}  
    	// 给端口添加监听器
    	try {
			serialPort.addEventListener(this);
		} catch (TooManyListenersException e) {
			e.printStackTrace();
		}
    	serialPort.notifyOnDataAvailable(true);
    }

    /** 
     * 给串行端口发送数据 
     *  
     */  
    public void sendDataToSeriaPort(byte[] b) {  
    	setReceive("");
        try {  
            outputStream.write(b);  
            outputStream.flush();
            Robot r=new Robot();
			r.delay(600);//发送指令后等待500ms接收返回数据
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (AWTException e) {
			e.printStackTrace();
		}  
    }
    
    /** 
     * 关闭串行端口 
     *  
     */  
    public void closeSerialPort() {  
        try {  
            if (outputStream != null)  
                outputStream.close();  
            if (inputStream != null)  
                inputStream.close();  
            if (serialPort != null)  
                serialPort.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    } 
    
    /** 
     * 事件监听 
     */
	@Override
	public void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
		case SerialPortEvent.BI:/* Break interrupt,通讯中断 */
		case SerialPortEvent.OE:/* Overrun error，溢位错误 */
		case SerialPortEvent.FE:/* Framing error，传帧错误 */
		case SerialPortEvent.PE:/* Parity error，校验错误 */
		case SerialPortEvent.CD:/* Carrier detect，载波检测 */
		case SerialPortEvent.CTS:/* Clear to send，清除发送 */
		case SerialPortEvent.DSR:/* Data set ready，数据设备就绪 */
		case SerialPortEvent.RI:/* Ring indicator，响铃指示 */
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:/* 输出缓冲区清空 */
			break;
		case SerialPortEvent.DATA_AVAILABLE:/* 端口有可用数据。读到缓冲数组，输出到终端 */
			//数据处理  
            String data=toHexString(getPack(packetlength));  
            receive+=data;  
            System.out.println("返回数据"+data);
		}
	}
	
	public String toHexString(byte[] data) {  
        if (data == null || data.length == 0)  
            return null;  
        byte[] hex = new byte[data.length * 2];  
        int index = 0;  
        for (byte b : data) {  
            int v = b & 0xFF;  
            hex[index++] = (byte) HEX_CHAR_TABLE[v >>> 4];  
            hex[index++] = (byte) HEX_CHAR_TABLE[v & 0xF];  
        }  
        return new String(hex);  
    }
	
	public byte[] getPack(int packetlength) {  
        while (true) {  
            // PacketLength为数据包长度  
            byte[] msgPack = new byte[packetlength];  
            for (int i = 0; i < packetlength; i++) {  
                int newData = 0;  
                try {  
                    if ((newData = inputStream.read()) != -1) {  
                        msgPack[i] = (byte) newData;  
                    }  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
            return msgPack;  
        }  
    } 
	
	public int getPacketlength() {  
        return packetlength;  
    }  
  
    public void setPacketlength(int packetlength) {  
        this.packetlength = packetlength;  
    }
    
    public String getReceive(){
    	return receive;
    }
    private void setReceive(String receive) {
		this.receive = receive;
	}

	public void cleanReceive(){
    	receive="";
    }
    /** 根据传入的电表地址获取拉闸命令*/
	public static byte[] getShutdownCommand(byte[] addr){
		byte[] reback=ConstantPool.SHUT_DOWN_SOMEONE;
		return getCommand(addr,reback);
	}
	/**根据传入的电表地址获取合闸命令*/
	public static byte[] getStartupCommand(byte[] addr){
		//byte[] reback=ConstantPool.START_UP_SOMEONE;
		return getCommand(addr,ConstantPool.START_UP_SOMEONE);
	}
	public static byte[] getValueCommand(byte[] addr){
		return getCommand(addr,ConstantPool.READ_VALUE_SOMEONE);
	}
	/**
	 * 根据给定的地址值和指令模版获取相应指令。
	 * 1.地址值倒序后替换模版中的地址域，并补足长度到6字节。
	 * 如：00 00 01处理后为01 00 00 00 00 00
	 * 2.重新计算校验码使命令合法。指令的最后第二个值：
	 * 从帧起始符开始到校验码之前的所有各字节的模256的和， 即各字节二进制 算术和，不计超过256的溢出值
	 * @param addr 地址值
	 * @param command 指令模版
	 * @return 字节类型的指令，方便输出流输出
	 */
	private static byte[] getCommand(byte[] addr, byte[] command){
		byte[] src=new byte[6];
		for(int t=0;t<6;t++){
			if(t<addr.length){
				src[t]=addr[addr.length-1-t];//倒序
			}else{
				src[t]=0x00;//补位
			}
		}
		System.arraycopy(src, 0, command, 2, 6);//替换地址值
		int total=0;
		for(int i=1;i<command.length-2;i++){
			total+=command[i];
		}
		int mod=total%256;//计算校验码
		String hex=Integer.toHexString(mod);
		int len = hex.length();  
		if (len < 2) {  
			hex = "0" + hex;  
		}
		command[command.length-2]=(byte) Long.parseLong(hex, 16);//替换校验码
		return command;
	}
    public static void main(String[] args) {
		SerialTool st=new SerialTool();
		st.scanPorts();
		st.openSerialPort("COM2");
		st.setSeriaPortParam(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		Scanner sc=new Scanner(System.in);
		String in=null;
		do{
			in=sc.nextLine();
			if("send".equals(in)){
				byte[] addr={0x04};
				st.sendDataToSeriaPort(getStartupCommand(addr));
			}else if("find".equals(in)){
				st.sendDataToSeriaPort(ConstantPool.READ_CONTACT_ADDRESS);
			}else if("see".equals(in)){
				System.out.println(st.getReceive());
			}else if("cls".equals(in)){
				st.cleanReceive();
			}else{
				st.sendDataToSeriaPort(in.getBytes());
			}
		}while(!"exit".equals(in));
		sc.close();
		st.closeSerialPort();
	}
}
