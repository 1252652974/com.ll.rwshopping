package com.teach.commons;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.teach.annotations.Name;
import com.teach.annotations.Id;

/**
 * jdbc工具类
 * 
 * @author J.L.Zhou
 *
 */
public class DBUtil {

	/**
	 * 构造方法私有化，无例模式
	 */
	private DBUtil() {
	}

	private static String url;
	private static String user;
	private static String pwd;

	static {
		try {
			Properties config = new Properties();
			config.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));

			url = config.getProperty("jdbc.url");
			user = config.getProperty("jdbc.username");
			pwd = config.getProperty("jdbc.password");
			String className = config.getProperty("jdbc.driverClassName");
            //返回classNmae这个类
			Class.forName(className);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, pwd);
	}

	/**
	 * 关闭jdbc对象
	 * 
	 * @param conn
	 * @param stmt
	 */
	public static void close(Connection conn, Statement stmt) {
		try {
			stmt.close();
		} catch (Exception ex) {
		}
		try {
			conn.close();
		} catch (Exception ex) {
		}
	}

	/**
	 * 关闭jdbc对象
	 * 
	 * @param conn
	 * @param stmt
	 * @param rs
	 */
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			rs.close();
		} catch (Exception ex) {
		}
		close(conn, stmt);
	}

	/**
	 * 执行sql语句
	 * 
	 * @param sql
	 * @param args
	 * @throws SQLException
	 */
	public static void execute(String sql, Object... args) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			setParams(pstmt, args);
			pstmt.executeUpdate();
		} finally {
			close(conn, pstmt);
		}
	}

	/**
	 * 获取一个对象
	 * 
	 * @param sql
	 * @param cls
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public static <T> T get(String sql, Class<T> cls, Object... args) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			setParams(pstmt, args);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				T t = load(rs, cls);
				if (rs.next()) {
					throw new SQLException("发现的记录>1");
				} else {
					return t;
				}
			} else {
				throw new SQLException("没有发现一条记录");
			}
		} finally {
			close(conn, pstmt, rs);
		}
	}
	
	public static Map<String,Object> getMap(String sql, Object... args) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			setParams(pstmt, args);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Map<String,Object> t = load(rs);
				if (rs.next()) {
					throw new SQLException("发现的记录>1");
				} else {
					return t;
				}
			} else {
				throw new SQLException("没有发现一条记录");
			}
		} finally {
			close(conn, pstmt, rs);
		}
	}

	/**
	 * 查询数据库获取结果集的第一行第一列的数据
	 * 
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public static String getString(String sql, Object... args) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			setParams(pstmt, args);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			} else {
				throw new SQLException("没有发现一条记录");
			}
		} finally {
			close(conn, pstmt, rs);
		}
	}

	/**
	 * 查询数据库获取结果集的第一行第一列的数据
	 * 
	 * @param sql
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public static long getLong(String sql, Object... args) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			setParams(pstmt, args);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getLong(1);
			} else {
				throw new SQLException("没有发现一条记录");
			}
		} finally {
			close(conn, pstmt, rs);
		}
	}

	/**
	 * 获取对象列表
	 * 
	 * @param sql
	 * @param cls
	 * @param args
	 * @return
	 * @throws SQLException
	 */
	public static <T> List<T> list(String sql, Class<T> cls, Object... args) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			setParams(pstmt, args);
			rs = pstmt.executeQuery();
			List<T> list = new ArrayList<T>();
			while (rs.next()) {
				list.add(load(rs, cls));
			}
			return list;
		} finally {
			close(conn, pstmt, rs);
		}
	}
	
	public static  List<Map<String,Object>> listMap(String sql, Object... args) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			setParams(pstmt, args);
			rs = pstmt.executeQuery();
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			while (rs.next()) {
				list.add(load(rs));
			}
			return list;
		} finally {
			close(conn, pstmt, rs);
		}
	}

	/**
	 * 设置预编译的语句的所有参数
	 * 
	 * @param pstmt
	 * @param args
	 * @throws SQLException
	 */
	public static void setParams(PreparedStatement pstmt, Object... args) throws SQLException {
		for (int i = 0; i < args.length; i++) {
			setParams(pstmt, i + 1, args[i]);
		}
	}

	/**
	 * 设置预编译的语句的一个参数
	 * 
	 * @param pstmt
	 * @param index
	 * @param value
	 * @throws SQLException
	 */
	public static void setParams(PreparedStatement pstmt, int index, Object value) throws SQLException {
		if (value == null) {
			pstmt.setObject(index, null);
		} else if (value instanceof Date) {
			Date d = (Date) value;
			pstmt.setTimestamp(index, new java.sql.Timestamp(d.getTime()));
		} else {
			pstmt.setObject(index, value);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T load(ResultSet rs, Class<T> cls) throws SQLException {
		Object obj = null;
		try {
			obj = cls.newInstance();
		} catch (Exception e) {
			throw new SQLException("调用无参构造方法实例化错误：" + cls.getName(), e);
		}

		for (PropertyDescriptor prop : getPropertyDescriptors(cls)) {
			if (prop.getName().equals("class")) {
				continue;
			}
			String columnName = getColumnName(cls, prop);

			try {
				Object value = null;
				try {
					value = rs.getObject(columnName);
				} catch (Exception ex) {
					// 警告，字段不存在
				}
				if (value != null) {
					if (prop.getPropertyType() == Integer.class) {
						value = rs.getInt(columnName);
					} else if (prop.getPropertyType() == Character.class) {
						value = rs.getString(columnName).charAt(0);
					} else if (prop.getPropertyType() == Date.class) {
						value = new Date(rs.getTimestamp(columnName).getTime());
					} else {
						try {
							value = ResultSet.class
									.getMethod("get" + prop.getPropertyType().getSimpleName(), String.class)
									.invoke(rs, columnName);
						} catch (Exception ex) {
						}
					}
					prop.getWriteMethod().invoke(obj, value);
				}

			} catch (Exception e) {
				throw new SQLException("设置字段错误：" + columnName, e);
			}
		}
		return (T) obj;
	}
	
	public static Map<String,Object> load(ResultSet rs) throws SQLException {
		 java.sql.ResultSetMetaData rsmd = rs.getMetaData();
		 Map<String,Object> map = new HashMap<String,Object>();
		 for(int i=1;i<=rsmd.getColumnCount();i++){
			 String[] ks = rsmd.getColumnName(i).toLowerCase().split("_+");
			 StringBuffer sb = new StringBuffer(ks[0]);
			 for(int j=1;j<ks.length;j++){
				 sb.append(ks[j].substring(0, 1).toUpperCase()+ks[j].substring(1));
			 }
			 map.put(sb.toString(), rs.getObject(i));
		 }
		 return map;
	}

	/**
	 * 根据属性名，获取字段名
	 * 
	 * @param cls
	 * @param proName
	 * @return
	 */
	public static String getColumnName(Class<?> cls, PropertyDescriptor prop) throws SQLException {
		if (prop.getReadMethod() != null && prop.getReadMethod().isAnnotationPresent(Name.class)) {
			return prop.getReadMethod().getAnnotation(Name.class).value();
		} else if (prop.getWriteMethod() != null && prop.getWriteMethod().isAnnotationPresent(Name.class)) {
			return prop.getWriteMethod().getAnnotation(Name.class).value();
		} else {
			Field f = null;
			try {
				f = cls.getDeclaredField(prop.getName());
			} catch (Exception e) {
			}
			if (f != null && f.isAnnotationPresent(Name.class)) {
				return f.getAnnotation(Name.class).value();
			}

			return prop.getName().replaceAll("([A-Z])", "_$1").toUpperCase();
		}
	}

	/**
	 * 判断是否是主键
	 * 
	 * @param cls
	 * @param prop
	 * @return
	 * @throws SQLException
	 */
	public static boolean isId(Class<?> cls, PropertyDescriptor prop) throws SQLException {
		if (prop.getReadMethod() != null && prop.getReadMethod().isAnnotationPresent(Id.class)) {
			return true;
		} else if (prop.getWriteMethod() != null && prop.getWriteMethod().isAnnotationPresent(Id.class)) {
			return true;
		} else {
			Field f = null;
			try {
				f = cls.getDeclaredField(prop.getName());
			} catch (Exception e) {
			}
			if (f != null && f.isAnnotationPresent(Id.class)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据cls获取表名
	 * 
	 * @param cls
	 * @return
	 */
	public static String getTableName(Class<?> cls) throws SQLException {
		String tableName = null;
		if (cls.isAnnotationPresent(Name.class)) {
			Name name = cls.getAnnotation(Name.class);
			tableName = name.value();
		} else {
			tableName = cls.getSimpleName();
			tableName = tableName.replaceAll("(VO|PO|POJO|DTO)$", "");
			tableName = tableName.replaceAll("([A-Z])", "_$1");
			tableName = tableName.substring(1).toUpperCase();
		}
		return tableName;
	}



	/**
	 * 添加
	 * 
	 * @param model
	 * @throws SQLException
	 */
	public static void insert(Object model) throws SQLException {
		Class<?> cls = null;
		cls = model.getClass();

		StringBuffer columns = new StringBuffer();
		StringBuffer values = new StringBuffer();
		List<Object> list = new ArrayList<Object>();

		for (PropertyDescriptor prop : getPropertyDescriptors(cls)) {
			if (prop.getName().equals("class")) {
				continue;
			}
			columns.append(",");
			columns.append(getColumnName(cls, prop));
			values.append(",?");
			try {
				Object param = prop.getReadMethod().invoke(model);
				list.add(param);
			} catch (Exception e) {
				throw new SQLException("获取参数数据异常:" + prop.getName(), e);
			}
		}

		String sql = "INSERT INTO " + getTableName(cls) + "(" + columns.substring(1) + ") VALUES(" + values.substring(1)
				+ ")";
		Object[] args = list.toArray();
		execute(sql, args);
	}

	/**
	 * 添加非空属性
	 * 
	 * @param model
	 * @throws SQLException
	 */
	public static void insertSelective(Object model) throws SQLException {
		Class<?> cls = null;
		cls = model.getClass();

		StringBuffer columns = new StringBuffer();
		StringBuffer values = new StringBuffer();
		List<Object> list = new ArrayList<Object>();

		for (PropertyDescriptor prop : getPropertyDescriptors(cls)) {
			if (prop.getName().equals("class")) {
				continue;
			}
			try {
				Object param = prop.getReadMethod().invoke(model);
				if (param != null) {
					list.add(param);
					columns.append(",");
					columns.append(getColumnName(cls, prop));
					values.append(",?");
				}
			} catch (Exception e) {
				// throw new SQLException("获取参数数据异常",e);
			}
		}

		String sql = "INSERT INTO " + getTableName(cls) + "(" + columns.substring(1) + ") VALUES(" + values.substring(1)
				+ ")";
		Object[] args = list.toArray();
		execute(sql, args);
	}

	/**
	 * 修改
	 * 
	 * @param model
	 * @throws SQLException
	 */
	public static void update(Object model) throws SQLException {
		Class<?> cls = null;
		cls = model.getClass();

		StringBuffer columns = new StringBuffer();
		StringBuffer where = new StringBuffer();
		
		List<Object> list = new ArrayList<Object>();
		List<Object> whereList = new ArrayList<Object>();

		for (PropertyDescriptor prop : getPropertyDescriptors(cls)) {
			if (prop.getName().equals("class")) {
				continue;
			}
			if (isId(cls, prop)) {
				where.append("AND ");
				where.append(getColumnName(cls, prop));
				where.append("=?");
				try {
					Object param = prop.getReadMethod().invoke(model);
					whereList.add(param);
				} catch (Exception e) {
					throw new SQLException("获取参数数据异常:" + prop.getName(), e);
				}
			} else {
				columns.append(",");
				columns.append(getColumnName(cls, prop));
				columns.append("=?");
				try {
					Object param = prop.getReadMethod().invoke(model);
					list.add(param);
				} catch (Exception e) {
					throw new SQLException("获取参数数据异常:" + prop.getName(), e);
				}
			}
		}

		String sql = String.format("UPDATE %s SET %s WHERE %s", getTableName(cls), columns.substring(1),
				where.substring(4));
		list.addAll(whereList);
		Object[] args = list.toArray();
		execute(sql, args);
	}

	/**
	 * 修改非空属性
	 * 
	 * @param model
	 * @throws SQLException
	 */
	public static void updateSelective(Object model) throws SQLException {
		Class<?> cls = null;
		cls = model.getClass();

		StringBuffer columns = new StringBuffer();
		StringBuffer where = new StringBuffer();
		List<Object> list = new ArrayList<Object>();
		List<Object> whereList = new ArrayList<Object>();

		for (PropertyDescriptor prop : getPropertyDescriptors(cls)) {
			if (prop.getName().equals("class")) {
				continue;
			}
			try {
				Object param = prop.getReadMethod().invoke(model);
				if (param != null) {
					if (isId(cls, prop)) {
						where.append("AND ");
						where.append(getColumnName(cls, prop));
						where.append("=?");
						whereList.add(param);
					} else {
						columns.append(",");
						columns.append(getColumnName(cls, prop));
						columns.append("=?");
						list.add(param);
					}
				}
			} catch (Exception e) {
				throw new SQLException("获取参数数据异常:" + prop.getName(), e);
			}
		}

		String sql = String.format("UPDATE %s SET %s WHERE %s", getTableName(cls), columns.substring(1),
				where.substring(4));
		list.addAll(whereList);
		Object[] args = list.toArray();
		execute(sql, args);
	}

	public static void delete(Serializable id, Class<?> cls) throws SQLException {
		String sql = String.format("DELETE FROM %s WHERE %s", getTableName(cls), 
				getIdWhere(cls));
		execute(sql, id);
	}
	
	public static String getIdWhere(Class<?> cls)throws SQLException{
		StringBuffer where = new StringBuffer();
		for (PropertyDescriptor prop : getPropertyDescriptors(cls)) {
			if (prop.getName().equals("class")) {
				continue;
			}
			if (isId(cls, prop)) {
				where.append(getColumnName(cls, prop));
				where.append("=?");
				break;
			} 
		}
		return where.toString();
	}

	public static PropertyDescriptor[] getPropertyDescriptors(Class<?> cls) throws SQLException {
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(cls);
		} catch (IntrospectionException e) {
			throw new SQLException("获取数据模型对象信息出错", e);
		}
		
		return beanInfo.getPropertyDescriptors();
	}
}
