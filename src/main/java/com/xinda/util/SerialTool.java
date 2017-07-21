package com.xinda.util;

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
        try {  
            outputStream.write(b);  
            outputStream.flush();  
        } catch (IOException e) {  
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
    public void cleanReceive(){
    	receive="";
    }
    public static void main(String[] args) {
    	byte[] command1={(byte)0xFE,(byte)0x68,(byte)0xCC,(byte)0x68,(byte)0x37,(byte)0x82,(byte)0x11};
		SerialTool st=new SerialTool();
		st.scanPorts();
		st.openSerialPort("COM3");
		st.setSeriaPortParam(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		Scanner sc=new Scanner(System.in);
		String in=null;
		do{
			in=sc.nextLine();
			if("find".equals(in)){
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
