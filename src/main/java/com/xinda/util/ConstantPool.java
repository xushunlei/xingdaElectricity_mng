package com.xinda.util;

/**
 * 常量管理类 用于管理系统中使用的各种常量
 * 
 * @author Administrator
 */
public class ConstantPool {
	/*
	 * 拉合闸命令帧格式 
	 * +---+------+-----+------+--------+----+ 
	 * |功能|地址域    |命令字 |数据标识|密码权限	|数据  | 
	 * +---+------+-----+------+--------+----+ 
	 * |拉闸|A0-A5 | 04  | C028 |00000000|3355| 
	 * +---+------+-----+------+--------+----+ 
	 * |合闸|实际地址| 04  | C028 |00000000|9966| 
	 * +---+------+-----+------+--------+----+
	 */
	/** 拉闸 */
	private static byte[] shut_down_somebody = { (byte) 0xfe, 0x68, 0x00, 0x00, 0x00,
			0x00, 0x00, 0x00, 0x68, 0x04, 0x08, 0x5b, (byte) 0xf3, 0x33, 0x33, 0x33,
			0x33, (byte) 0x88, 0x66, 0x00, 0x16 };
	/** 合闸 */
	private static byte[] start_up_somebody = { (byte) 0xfe, 0x68, 0x00, 0x00,
			0x00, 0x00, 0x00, 0x00, 0x68, 0x04, 0x08, 0x5b, (byte) 0xf3, 0x33,
			0x33, 0x33, 0x33, (byte) 0x99, (byte) 0xCC, 0x00, 0x16 };
	/** 广播读通信地址 */
	public static final byte[] READ_CONTACT_ADDRESS = { (byte) 0xFE, 0x68,
			(byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0xAA, (byte) 0xAA,
			(byte) 0xAA, 0x68, 0x11, 0x04, 0x34, 0x37, 0x33, 0x37, (byte) 0xB6,
			0x16 };

	public static byte[] getStart_up_somebody() {
		return start_up_somebody;
	}

	public static byte[] getShut_down_somebody() {
		return shut_down_somebody;
	}

	/** 浮点型正则 */
	public static final String DOUBLE_REG_EX = "^\\d+(.\\d+)?$";
	/** 整形正则 */
	public static final String INTEGER_REG_EX = "^\\d+$";
	/** 整形数组正则：1,2,3 */
	public static final String INTEGER_ARRAY_REG_EX = "^\\d+(,\\d+)*";

}
