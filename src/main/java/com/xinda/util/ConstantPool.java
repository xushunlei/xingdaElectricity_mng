package com.xinda.util;
/**
 * 常量管理类
 * 用于管理系统中使用的各种常量
 * @author Administrator
 */
public class ConstantPool {
	/**广播读通信地址*/
	static final byte[] READ_CONTACT_ADDRESS={(byte) 0xFE,0x68,(byte) 0xAA,(byte) 0xAA,(byte) 0xAA,
		(byte) 0xAA,(byte) 0xAA,(byte) 0xAA,0x68,0x11,0x04,0x34,0x37,0x33,0x37,(byte) 0xB6,0x16};
	
	/**浮点型正则*/
	public static final String DOUBLE_REG_EX="^\\d+(.\\d+)?$";
	/**整形正则*/
	public static final String INTEGER_REG_EX="^\\d+$";
	/**整形数组正则：1,2,3*/
	public static final String INTEGER_ARRAY_REG_EX="^\\d+(,\\d+)*";
}
