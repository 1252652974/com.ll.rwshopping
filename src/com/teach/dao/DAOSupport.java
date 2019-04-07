package com.teach.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;


public interface DAOSupport<T> {

	/**
	 * 新增一条记录
	 * @param model
	 * @throws SQLException
	 */
	void insert(T model)throws SQLException;
	/**
	 * 新增一条记录(非空属性)
	 * @param model
	 * @throws SQLException
	 */
	void insertSelective(T model)throws SQLException;
	
	/**
	 * 根据主键更新其它字段
	 * @param model
	 * @throws SQLException
	 */
	void update(T model)throws SQLException;
	
	/**
	 * 根据主键更新其它字段（非空属性）
	 * @param model
	 * @throws SQLException
	 */
	void updateSelective(T model)throws SQLException;
	
	/**
	 * 根据主键删除记录
	 * @param id
	 * @throws SQLException
	 */
	void deleteById(Serializable id)throws SQLException;
	
	/**
	 * 根据主键获取一条完整记录
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	T selectById(Serializable id)throws SQLException;
	
	/**
	 * 获取从index开始的len条记录
	 * @param index
	 * @param len
	 * @return
	 * @throws SQLException
	 */
	List<T> selectAll(int index,int len)throws SQLException;
	
	/**
	 * 统计数量
	 * @return
	 * @throws SQLException
	 */
	long countAll()throws SQLException;
}
