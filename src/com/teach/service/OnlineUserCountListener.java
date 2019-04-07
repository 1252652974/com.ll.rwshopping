package com.teach.service;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class OnlineUserCountListener
 *
 */
@WebListener
public class OnlineUserCountListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public OnlineUserCountListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
    	ServletContext context = se.getSession().getServletContext();//得到ServletContext对象
		Integer num = (Integer) context.getAttribute("num");//得到存在ServletContext对象中的num的值
		if(num==null) {//如果为空,说明是第一个访问的用户
			num = 1;
		}else {
			num++;//不为空,num++
		}
		context.setAttribute("num", num);//再把num装到ServletContext域中
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	ServletContext context = se.getSession().getServletContext();
		Integer num = (Integer) context.getAttribute("num");
		num--;//session，num--
		context.setAttribute("num", num);//再把num装到ServletContext域中,方便页面中得到num信息
    }
	
}
