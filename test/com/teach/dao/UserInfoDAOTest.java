package com.teach.dao;

import java.util.List;

import org.junit.Test;

import com.teach.entity.GoodsInfo;
import com.teach.entity.UserInfo;

public class UserInfoDAOTest {
	private UserInfoDAO dao = new UserInfoDAO();
	private GoodsInfoDAO dao1 = new GoodsInfoDAO();
	@Test
	public void insert(){
		try{
			UserInfo model = new UserInfo();
			model.setUserName("Rose");
			model.setUserPwd("123");
			dao.insert(model);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	@Test
	public void update(){
		try{
			UserInfo model = new UserInfo();
			model.setUserId(1);
			model.setRoleId(1);
			model.setUserName("Jack");
			model.setUserPwd("123");
			dao.update(model);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@Test
	public void deleteById(){
		try{
			dao.deleteById(4);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@Test
	public void selectById(){
		try{
			System.out.println(dao.selectByUserId(5).getRoleId());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@Test
	public void selectAll(){
		try{
			//List<GoodsInfo> lists = dao1.selectAll(0, 1);
			GoodsInfo list = dao1.selectById(16);
			//UserInfo list1 = dao.selectById("James");
			System.out.println(list);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@Test
	public void countAll(){
		try{
			System.out.println(dao.countAll());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@Test
	public void login() {
		try{
			UserInfoDAO dao = new UserInfoDAO();
			int userid = dao.login("Alice", "123");
			System.out.println(userid);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@Test
	public void ou() {
		char[] A = {'q','q'};
		A[0] = 'a';
		A[1] = 'b';
		System.out.println(java.util.Arrays.toString(A));
	}
}
