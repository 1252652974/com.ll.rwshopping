package com.teach.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.teach.commons.MD5;
import com.teach.entity.OrderInfo;
import com.teach.entity.UserInfo;
import com.teach.service.impl.UserInfoServiceImpl;
import com.teach.vo.LoginUserVO;

public class UserInfoServiceTest {

	private UserInfoService service = new UserInfoServiceImpl();

	@Test
	public void listTop10(){
		try{
			List<UserInfo> list = service.listTop10();
			System.out.println(list);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@Test
	public void orderSort(){
		List<OrderInfo> list = new ArrayList<OrderInfo>();
		OrderInfo order1 = new OrderInfo(1);
		OrderInfo order2 = new OrderInfo(3);
		OrderInfo order3 = new OrderInfo(2);
		list.add(order1);
		list.add(order2);
		list.add(order3);
		OrderSortService sercice = new OrderSortService();
		System.out.println(list.get(0).getId());
		System.out.println(list.get(1).getId());
		System.out.println(list.get(2).getId());
		System.out.println("-----------");
		sercice.orderSort(list);
		System.out.println(list.get(0).getId());
		System.out.println(list.get(1).getId());
		System.out.println(list.get(2).getId());
	}
	
	@Test
	public void countGoodsTotal(){
		try{
			CountGoodsTotalService service = new CountGoodsTotalService();
			int goodsplus = service.countGoodsTotal(17);
			System.out.println(goodsplus);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@Test
	public void login(){
		System.out.println(MD5.encode("123456"));
		System.out.println("------------------成功登录------------------------");
		try{
			LoginUserVO user = service.login("Tom", "123456");
			System.out.println(user);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		System.out.println("------------------用户不存在------------------------");
		try{
			LoginUserVO user = service.login("lksdjfklsdafjlksdafj", "123456");
			System.out.println(user);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		System.out.println("------------------密码错误------------------------");
		try{
			LoginUserVO user = service.login("Tom", "kjasdflsadflsdakfj");
			System.out.println(user);
		}catch(Exception ex){
			ex.printStackTrace();
		}
//		System.out.println("------------------用户被锁定了------------------------");
//		try{
//			LoginUserVO user = service.login("15111111111", "123456");
//			System.out.println(user);
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
	}
}
