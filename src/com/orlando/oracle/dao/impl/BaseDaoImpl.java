package com.orlando.oracle.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.orlando.oracle.dao.BaseDao;
import com.orlando.oracle.dao.PRowMapper;
import com.orlando.util.C3P0Utils;

/**
 * 
 * @ClassName: JDBCDaoImpl 
 * @Description: 数据库接口实现类
 * @author: 章征武【orlando】
 * @date: 2018年7月25日 上午11:56:11 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com
 */
public class BaseDaoImpl implements BaseDao {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public List<Map<String, Object>> queryForList(String sql, Object... params) throws SQLException{
		List<Map<String, Object>> result = new ArrayList<>();
		try {
			conn = C3P0Utils.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			rs = ps.executeQuery();
			//获取元数据
			ResultSetMetaData rsmd = rs.getMetaData();
			//获取列数
			int sumColumn = rsmd.getColumnCount();
			//定义一行数据存入obj
			Map<String, Object> obj;  
			while(rs !=null && rs.next()) {
				obj = new HashMap<>();
				for (int i = 1; i <= sumColumn; i++) {
					//获取列别名
					String columnLabel = rsmd.getColumnLabel(i);
					obj.put(columnLabel, rs.getObject(i));
				}
				result.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			C3P0Utils.closeConnection();
		}
		return result;
	}

	@Override
	public <E> List<E> queryForList(PRowMapper<E> mapper, String sql, Object... params) throws SQLException{
		List<E> result = new ArrayList<>();
		try {
			conn = C3P0Utils.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			rs = ps.executeQuery();
			int rownum = 0;
			while(rs != null && rs.next()) {
				rownum++;
				//通过接口转换成对象，然后一个一个将对象加入到result结果中
				result.add(mapper.mappingRow(rs, rownum));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			C3P0Utils.closeConnection();
		}
		return result;
	}

	@Override
	public <E> List<E> queryForListFailed(Class<E> clazz, String sql, Object... params) throws SQLException{
		List<E> result = new ArrayList<>();
		try {
			conn = C3P0Utils.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			rs = ps.executeQuery();
			//获取元数据
			ResultSetMetaData rsmd = rs.getMetaData();
			//获取列
			int columnNum = rsmd.getColumnCount();
			//定义一个实体类E
			E obj;
			Method method;
			while(rs != null & rs.next()) {
				//通过反射获取实体类对象，实体类必须包含空参的构造器
				obj = clazz.newInstance();
				for (int i = 1; i <= columnNum; i++) {
					//获取列别名
					String columnName = rsmd.getColumnLabel(i);
					
					//构造列set方法名  第一个单词大写 toUpperCase()，后面的单词小写 toLowerCase()
					String methodName = "set"+columnName.substring(0, 1).toUpperCase()+columnName.substring(1).toLowerCase();
					//通过方法名获取方法
					method = clazz.getDeclaredMethod(methodName);
					System.out.println("------>"+method);
					//暴力访问
//					method.setAccessible(true);
					//调用方法将值设置进obj
					method.invoke(obj, rs.getObject(i));
				}
				result.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			C3P0Utils.closeConnection();
		}
		return result;
	}
	
	
	
	@Override
	public <E> List<E> queryForListSuccessByField(Class<E> clazz, String sql, Object... params) throws SQLException{
		List<E> result = new ArrayList<>();
		try {
			conn = C3P0Utils.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			rs = ps.executeQuery();
			//定义一个实体类E
			E obj;
			
			Method method;
			//通过反射获得所有实体类的属性
			Field[] fields = clazz.getDeclaredFields();
			//定义实体类的属性值
			Field field;
			while(rs != null & rs.next()) {
				//获得实体类对象，必须提供空参的构造方法
				obj = clazz.newInstance();
				//遍历所有的属性
				for (int i = 0; i < fields.length; i++) {
					//拿到属性值
					field = fields[i];
					//通过属性值拼接出所有的set方法名
					String methodName = "set"+field.getName().substring(0, 1).toUpperCase()+field.getName().substring(1);
					//通过set方法名获得对于的方法
					method = clazz.getDeclaredMethod(methodName, field.getType());//通过属性值获得属性的返回值类型
//					System.out.println(field.getType().getSimpleName());
					//对参数类型进行判断
					if(field.getType().getSimpleName().equals("int")) {
						//当属性的返回值类型是Integer时，调用invoke方法将获取的值存入实体类对象obj，由于Oracle列名都是大写，所以需要toUpperCase()
						method.invoke(obj, rs.getInt(field.getName().toUpperCase()));
					}else if(field.getType().getSimpleName().equals("String")) {
						method.invoke(obj, rs.getString(field.getName().toUpperCase()));
					}else if(field.getType().getSimpleName().equals("Date")) {
						method.invoke(obj, rs.getDate(field.getName().toUpperCase()));
					}else if(field.getType().getSimpleName().equals("double")) {
						method.invoke(obj, rs.getDouble(field.getName().toUpperCase()));
					}else {
						//TODO 其他类型待补充
					}
				}
				result.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			C3P0Utils.closeConnection();
		}
		return result;
	}

	@Override
	public Map<String, Object> queryForObject(String sql, Object... params) throws SQLException{
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			conn = C3P0Utils.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			rs = ps.executeQuery();
			//获取元数据
			ResultSetMetaData rsmd = rs.getMetaData();
			//获取列数
			int columnNum =rsmd.getColumnCount();
			if(rs !=null && rs.next()) {
				for (int i = 1; i <= columnNum; i++) {
					String columnName = rsmd.getColumnLabel(i);
					result.put(columnName, rs.getObject(i));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			C3P0Utils.closeConnection();
		}
		return result;
	}

	@Override
	public <E> E queryForObject(PRowMapper<E> mapper, String sql, Object... params) throws SQLException{
		E result = null;
		try {
			conn = C3P0Utils.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			rs = ps.executeQuery();
			int rownum = 0;	
			if(rs != null && rs.next()) {
				result = mapper.mappingRow(rs, rownum);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			C3P0Utils.closeConnection();
		}
		return result;
	}

	@Override
	public <E> E queryForObjectSuccess(Class<E> clazz, String sql, Object... params) throws SQLException{
		E obj = null;
		try {
			conn = C3P0Utils.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			rs = ps.executeQuery();
			Method method;
			//通过反射获得所有实体类的属性
			Field[] fields = clazz.getDeclaredFields();
			//定义实体类的属性值
			Field field;
			while(rs != null & rs.next()) {
				//获得实体类对象，必须提供空参的构造方法
				obj = clazz.newInstance();
				//遍历所有的属性
				for (int i = 0; i < fields.length; i++) {
					//拿到属性值
					field = fields[i];
					//通过属性值拼接出所有的set方法名
					String methodName = "set"+field.getName().substring(0, 1).toUpperCase()+field.getName().substring(1);
					//通过set方法名获得对于的方法
					method = clazz.getDeclaredMethod(methodName, field.getType());//通过属性值获得属性的返回值类型
//					System.out.println(field.getType().getSimpleName());
					//对参数类型进行判断
					if(field.getType().getSimpleName().equals("int")) {
						//当属性的返回值类型是Integer时，调用invoke方法将获取的值存入实体类对象obj，由于Oracle列名都是大写，所以需要toUpperCase()
						method.invoke(obj, rs.getInt(field.getName().toUpperCase()));
					}else if(field.getType().getSimpleName().equals("String")) {
						method.invoke(obj, rs.getString(field.getName().toUpperCase()));
					}else if(field.getType().getSimpleName().equals("Date")) {
						method.invoke(obj, rs.getDate(field.getName().toUpperCase()));
					}else if(field.getType().getSimpleName().equals("double")) {
						method.invoke(obj, rs.getDouble(field.getName().toUpperCase()));
					}else {
						//TODO 其他类型待补充
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			C3P0Utils.closeConnection();
		}
		return obj;
	}

	@Override
	public <E> List<E> queryForListSuccessByMethod(Class<E> clazz, String sql, Object... params) throws SQLException{
		List<E> result = new ArrayList<>();
		try {
			conn = C3P0Utils.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			rs = ps.executeQuery();
			//获取元数据
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnNum = rsmd.getColumnCount();
			Method[] methods = clazz.getDeclaredMethods();
			Method method;
			E obj;
			//定义一个map用来存储<属性,方法>
			Map<String,Method> m = new HashMap<>();
			for (int i = 0; i < methods.length; i++) {
				method = methods[i];
				//
				String methodName = method.getName();
				if(methodName.startsWith("set")) {
					m.put(methodName.substring(3).toLowerCase(), method);
				}
			}
			while(rs != null && rs.next()) {
				obj = clazz.newInstance();
				for (int i = 1; i <= columnNum; i++) {
					String columnName = rsmd.getColumnLabel(i);
					
					if(m.containsKey(columnName.toLowerCase())) {
						//构造属性的get方法名得到get方法并获取其返回值类型
						method = m.get(columnName.toLowerCase());
						String getMethodName = "get"+columnName.substring(0, 1).toUpperCase()+columnName.substring(1).toLowerCase();
						Method getMethod = clazz.getDeclaredMethod(getMethodName);
						if(getMethod.getReturnType().getSimpleName().equals("int")) {
							method.invoke(obj, rs.getInt(columnName));
						}else if(getMethod.getReturnType().getSimpleName().equals("String")) {
							method.invoke(obj, rs.getString(columnName));
						}else if(getMethod.getReturnType().getSimpleName().equals("double")) {
							method.invoke(obj, rs.getDouble(columnName));
						}else if(getMethod.getReturnType().getSimpleName().equals("Date")) {
							method.invoke(obj, rs.getDate(columnName));
						}else {
							//TODO 其他类型待补充
						}
						
					}
				}
				result.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			C3P0Utils.closeConnection();
		}
		return result;
	}

	@Override
	public int executeUpdate(String sql, Object... params) throws SQLException{
		int result = -1;
		try {
			conn = C3P0Utils.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			C3P0Utils.closeConnection();
		}
		return result;
	}

	@Override
	public int executeBatch(String sql, List<Object[]> params) throws SQLException{
		int[] result = null;
		try {
			conn = C3P0Utils.getConnection();
			ps = conn.prepareStatement(sql);
			//在使用增强for循环之前一定要先验证params是否有值
			if(params != null && params.size() != 0) {
				for (Object[] object : params) {
					for (int i = 0; i < object.length; i++) {
						ps.setObject(i+1, object[i]);
					}
					ps.addBatch();
				}
			}
			result = ps.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			C3P0Utils.closeConnection();
		}
		return result.length;
	}

	@Override
	public <E> List<E> queryForListSuccessByOtherMethod(Class<E> clazz, String sql, Object... params)
			throws SQLException {
		List<E> result = new ArrayList<>();
		try {
			conn = C3P0Utils.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			rs = ps.executeQuery();
			//获取元数据
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnNum =rsmd.getColumnCount();
			Method[] methods = clazz.getDeclaredMethods();
			Method method;
			Map<String, Method> map = new HashMap<>();
			for (int i = 0; i < methods.length; i++) {
				method = methods[i];
				String methodName = method.getName();
				if(methodName.startsWith("set")) {
					map.put(methodName.substring(3).toUpperCase(), method);
				}
			}
			E obj = null;
			while(rs !=null && rs.next()) {
				obj = clazz.newInstance();
				for (int i = 1; i <= columnNum; i++) {
					String columnName = rsmd.getColumnLabel(i);
					//如果map的key中包含列别名
					if(map.containsKey(columnName)) {
						method = map.get(columnName);
						//获取set方法的参数类型 getParameterTypes 返回参数列表对应的数组
						String returnType = map.get(columnName).getParameterTypes()[0].getSimpleName();
						if("int".equals(returnType)) {
							method.invoke(obj, rs.getInt(i));
						}else if("String".equals(returnType)) {
							method.invoke(obj, rs.getString(i));
						}else if("Date".equals(returnType)) {
							method.invoke(obj, rs.getDate(i));
						}else if("double".equals(returnType)) {
							method.invoke(obj, rs.getDouble(i));
						}else {
							//TODO 其他类型待补充
						}
					}
				}
				result.add(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return result;
	}


	
	
	
	
	
	
	
}
