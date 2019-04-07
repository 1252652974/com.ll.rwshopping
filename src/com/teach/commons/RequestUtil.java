package com.teach.commons;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

	private RequestUtil(){}
	
	public static <T> T loadParams(HttpServletRequest request,Class<T> cls){
		try{
			Object obj = cls.newInstance();
			BeanInfo beanInfo = Introspector.getBeanInfo(cls);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			for(PropertyDescriptor pd : pds){
//				System.out.println(pd.getName());
//				System.out.println("\t"+pd.getPropertyType());
//				System.out.println("\t"+pd.getReadMethod());
//				System.out.println("\t"+pd.getWriteMethod());
				String value = request.getParameter(pd.getName());
				if(value!=null&&pd.getWriteMethod()!=null){
					if(pd.getPropertyType()==String.class){
						pd.getWriteMethod().invoke(obj, value);
					}else if(!"".equals(value)){
						Object v = Converter.convert(value, pd.getPropertyType());
						pd.getWriteMethod().invoke(obj, v);
					}
				}
//				System.out.println(value);
			}
			
			
			return (T)obj;
		}catch(Exception ex){
			throw new RuntimeException("获取请求参数错误",ex);
		}
	}
}
