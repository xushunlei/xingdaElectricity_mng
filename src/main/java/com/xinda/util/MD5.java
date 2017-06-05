package com.xinda.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	private static String[] hexstrings= {"0","1","2","3","4","5","6","7","8","9","a","b"
		,"c","d","e","f"};
	public static String byteToHexString(byte src){
		// 233 e0 ---> "e0"
		int b = src;
		//利用补码把负数变成整数
		if(b<0){
			b=b+256;
		}
		int shi = b / 16;
		int ge = b % 16;
		
		return hexstrings[shi]+hexstrings[ge];
	}
	public static String bytesToHexString(byte[] src){
		if(src == null || src.length <= 0){
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<src.length;i++){
			sb.append(byteToHexString(src[i]));
		}
		return sb.toString();
	}
	public static String md5encode(String src){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MD5.bytesToHexString(md.digest(src.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
