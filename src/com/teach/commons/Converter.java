package com.teach.commons;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 类型装换器
 * 支持从String转换为其它类型
 * 	String
 *  Date(yyyy-MM-dd [HH:mm])
 *  基本数据类型和包装类
 * @author J.L.Zhou
 *
 */
public class Converter {
	
	private static Map<Class<?>,Class<?>> map = new HashMap<Class<?>,Class<?>>();
	static{
		map.put(int.class, Integer.class);
		map.put(short.class, Short.class);
		map.put(long.class, Long.class);
		map.put(float.class, Float.class);
		map.put(double.class,Double.class);
		map.put(boolean.class, Boolean.class);
		map.put(byte.class, Byte.class);
		map.put(char.class, ConvertChar.class);
		map.put(Character.class, ConvertChar.class);
		map.put(Date.class, ConvertDate.class);
	}

	public static <T> T convert(String str,Class<T> cls){
		if(str==null){
			return null;
		}
		if(cls==String.class){
			return (T)str;
		}
		T value = null;
		try{
			if(map.get(cls)!=null){
				cls = (Class<T>) map.get(cls);
			}
			value=(T)cls.getMethod("valueOf", String.class).invoke(null, str);
		}catch(Exception ex){
			throw new RuntimeException(String.format("执行%s的valueOf方式失败。", cls.getName()),ex);
		}
		return value;
	}
	
	
	public static void main(String[] args) {
		System.out.println(convert("1", Long.class));
		System.out.println(convert("1", int.class));
		System.out.println(convert("1", String.class));
		System.out.println(convert("1", char.class));
		System.out.println(convert("2017-1-1", Date.class));
	}
}

class ConvertChar {
	public static char valueOf(String str){
		return str.charAt(0);
	}
}

class ConvertDate {
	public static Date valueOf(String str){
		SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
		try{
			return f1.parse(str);
		}catch(Exception ex){
			SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try{
				return f2.parse(str);
			}catch(Exception e){
				throw new RuntimeException("不支持的日期格式yyyy-MM-dd[ HH:mm]:"+str,e);
			}
			
		}
	}
}
