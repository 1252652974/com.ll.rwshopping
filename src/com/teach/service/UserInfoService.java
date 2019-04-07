package com.teach.service;

import java.util.List;

import com.teach.entity.UserInfo;
import com.teach.vo.LoginUserVO;

/**
 * vo
 * View Object
 * Value Object
 * @author J.L.Zhou
 *
 */
public interface UserInfoService {

	/**
	 * 返回最新的10个用户
	 * @return	最新的10个用户
	 * @throws Exception 未知异常
	 */
	List<UserInfo> listTop10()throws Exception;
	
	/**
	 * 用户登录
	 * @param userName	用户名
	 * @param userPwd	密码
	 * @param ip	登录IP
	 * @return	成功登录的用户基本信息
	 * @throws Exception 用户不存在 密码错误 用户被锁定了
	 */
	LoginUserVO login(String userName,String userPwd)throws Exception;


}
