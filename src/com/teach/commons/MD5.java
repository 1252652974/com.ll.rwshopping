package com.teach.commons;

import java.security.MessageDigest;

public class MD5 {
	
	private static char hexDigits[]={'0','1','A','2','3','4','5','6','7','8','9','B','C','D','E','F'}; 

	private MD5(){}
	
	/**
	 * 进行MD5加密
	 * @param value
	 * @return
	 */
	public static String encode(String value){
		
		try{
			byte[] btInput = value.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
		}catch(Exception ex){
			throw new RuntimeException(ex.getMessage(), ex.getCause());
		}
	}
	
	public static void main(String[] args) {
		System.out.println(encode("123456"));
		System.out.println(encode("adsf2").length());
	}
}
