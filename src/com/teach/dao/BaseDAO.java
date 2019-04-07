package com.teach.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import com.teach.commons.DBUtil;

public class BaseDAO<T> implements DAOSupport<T> {

	@Override
	public void insert(T model) throws SQLException {

		DBUtil.insert(model);

	}

	@Override
	public void insertSelective(T model) throws SQLException {

		DBUtil.insertSelective(model);

	}

	@Override
	public void update(T model) throws SQLException {

		DBUtil.update(model);

	}

	@Override
	public void updateSelective(T model) throws SQLException {

		DBUtil.updateSelective(model);

	}

	@Override
	public void deleteById(Serializable id) throws SQLException {
		Class<T> cls = getTClass();

		DBUtil.delete(id, cls);

	}

	@Override
	public T selectById(Serializable id) throws SQLException {

		return DBUtil.get(String.format("SELECT * FROM %s WHERE %s", DBUtil.getTableName(getTClass()),
				DBUtil.getIdWhere(getTClass())), getTClass(), id);
	}

	@Override
	public List<T> selectAll(int index, int len) throws SQLException {

		return DBUtil.list(String.format("SELECT * FROM %s LIMIT ?,?", DBUtil.getTableName(getTClass())), getTClass(),
				index, len);

	}

	@Override
	public long countAll() throws SQLException {

		return DBUtil.getLong("SELECT COUNT(*) AS C FROM " + DBUtil.getTableName(getTClass()));

	}

	/**
	 * 获取泛型类
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Class<T> getTClass() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

}
