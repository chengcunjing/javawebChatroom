package com.orlando.oracle.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: JDBCDao 
 * @Description: 操作数据库的接口
 * @author: 章征武【orlando】
 * @date: 2018年7月25日 上午11:52:13 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com
 */
public interface BaseDao {
	/**
	 * @Title: queryForList
	 * @Description: 查询数据库，返回List<Map<String, Object>>
	 * @param @param sql
	 * @param @param params
	 * @param @return    参数
	 * @return List<Map<String,Object>>    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年7月25日 下午2:45:39  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws SQLException 
	 */
	public List<Map<String, Object>> queryForList(String sql, Object... params) throws SQLException;
	

	/**
	 * @Title: queryForObject
	 * @Description: 查询一列
	 * @param @param sql
	 * @param @param params
	 * @param @return    参数
	 * @return Map<String,Object>    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年7月25日 下午8:00:05  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws SQLException
	 */
	public Map<String, Object> queryForObject(String sql, Object... params) throws SQLException;
	
	/**
	 * @Title: queryForList
	 * @Description: 查询数据库，返回List<E>
	 * @param @param mapper
	 * @param @param sql
	 * @param @param params
	 * @param @return    参数
	 * @return List<E>    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年7月25日 下午3:29:58  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws SQLException
	 */
	public <E> List<E> queryForList(PRowMapper<E> mapper, String sql, Object... params) throws SQLException;
	

	/**
	 * @Title: queryForObject
	 * @Description: 查询一列，返回一个对象
	 * @param @param mapper
	 * @param @param sql
	 * @param @param params
	 * @param @return    参数
	 * @return E    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年7月25日 下午8:30:14  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws SQLException
	 */
	public <E> E queryForObject(PRowMapper<E> mapper, String sql, Object... params) throws SQLException;
	
	/**
	 * 思想【重要】：
	 * 该方法通过元数据获取数据库列别名，再通过列别名拼接set方法，
	 * 然后通过set方法名找到set方法，在通过获取的set方法调用invoke，将值传入对象
	 * 但Oracle数据库列名统一都是大写，并且set方法中需要带有形参，导致出了问题
	 * @Title: queryForList
	 * @Description: 通过反射机制获取List (方法一：失败)
	 * @param @param clazz
	 * @param @param sql
	 * @param @param params
	 * @param @return    参数
	 * @return List<E>    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年7月25日 下午4:57:34  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws SQLException
	 */
	public <E> List<E> queryForListFailed(Class<E> clazz, String sql, Object... params) throws SQLException;
	
	
	/**
	 * 思想【重要】
	 * 上面的 queryForListFailed 方法之所以失败是因为从数据库获取列名的同时想要获取该列对应的返回值类型不好拿【可否拿到需要问下老师】
	 * 所以本方法就改从实体类对象入手，使用反射获得实体类中的所有属性和属性返回值类型，
	 * 最后通过属性名字与列名进行匹配对应拿到对应的值
	 * 
	 * 该方法的前提是实体类的属性和数据库对应的表列名必须一一对应
	 * 此方法目前仅适用于Oracle数据库
	 * @Title: queryForListSuccess1
	 * @Description: 通过反射机制获取List (方法二：成功)
	 * @param @param clazz
	 * @param @param sql
	 * @param @param params
	 * @param @return    参数
	 * @return List<E>    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年7月25日 下午7:21:53  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws SQLException
	 */
	public <E> List<E> queryForListSuccessByField(Class<E> clazz, String sql, Object... params) throws SQLException;
	
	
	
	/**
	 * 本方法就改从实体类对象入手，使用反射获得实体类中的所有方法
	 * @Title: queryForListSuccessByMethod   
	 * @Description: 通过反射机制获取List (方法三：成功)  
	 * @param: @param clazz
	 * @param: @param sql
	 * @param: @param params
	 * @param: @return     
	 * @return: List<E>     
	 * @Author: 章征武【EN:orlando】
	 * @date: 2018年7月25日 下午11:50:40
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws SQLException
	 */
	public <E> List<E> queryForListSuccessByMethod(Class<E> clazz, String sql, Object... params) throws SQLException;
	
	/**
	 * 与queryForListSuccessByField 不同的点在于获取返回值参数类型的方法
	 * @Title: queryForListSuccessByOtherMethod
	 * @Description: 通过反射机制获取List (方法四：成功)
	 * @param @param clazz
	 * @param @param sql
	 * @param @param params
	 * @param @return
	 * @param @throws SQLException    参数
	 * @return List<E>    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年7月27日 上午9:38:34  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws
	 */
	public <E> List<E> queryForListSuccessByOtherMethod(Class<E> clazz, String sql, Object... params) throws SQLException;

	/**
	 * 通过Field获取
	 * @Title: queryForObjectSuccess
	 * @Description: 查询一列，返回一个对象
	 * @param @param clazz
	 * @param @param sql
	 * @param @param params
	 * @param @return    参数
	 * @return E    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年7月25日 下午8:39:15  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws SQLException
	 */
	public <E> E queryForObjectSuccess(Class<E> clazz, String sql, Object... params) throws SQLException;	
	
	
	
	/**
	 * @Title: executeUpdate   
	 * @Description: 执行单挑增加、修改、删除   
	 * @param: @param sql
	 * @param: @param params
	 * @param: @return     
	 * @return: int     
	 * @Author: 章征武【EN:orlando】
	 * @date: 2018年7月26日 上午12:45:33
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws SQLException
	 */
	public int executeUpdate(String sql,Object...params) throws SQLException;
	
	/**
	 * @Title: executeBatch   
	 * @Description: 批量更新语句   
	 * @param: @param sql
	 * @param: @param params
	 * @param: @return     
	 * @return: int     
	 * @Author: 章征武【EN:orlando】
	 * @date: 2018年7月26日 上午12:47:14
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws SQLException
	 */
	public int executeBatch(String sql, List<Object[]> params) throws SQLException;
	
}
