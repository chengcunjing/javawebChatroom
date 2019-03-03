package com.orlando.mysql.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.orlando.entity.ChatUserInfo;

/** 
 * @ClassName: UserInfoDao 
 * @Description: ChatUserInfo 实体类 Dao 接口  DataAccessObject  数据访问对象
 * @author: 章征武【orlando】
 * @date: 2018年9月14日 下午3:31:48 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com 
 */
public interface UserInfoDao {

	/**
	 * @throws SQLException 
	 *新增方法(方法名称前缀通常为（不是强制要求，是约定俗成的规矩）：add、save)
	 *需要的参数其实是表中每一个字段的数据（采用实体对象传递）
	 *通常返回类型可以是：void、int、boolean
	 * @Title: add
	 * @Description: 新增
	 * @param @param userInfo
	 * @param @return    参数
	 * @return boolean    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年9月14日 下午3:50:35  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws
	 */
	public boolean add(ChatUserInfo userInfo) throws SQLException;
	
	
	
	/**
	 * @throws SQLException 
	 * 删除方法(方法名称前缀通常为（不是强制要求，是约定俗成的规矩）：remove、delete)
	 * @Title: remove
	 * @Description: 删除
	 * @param @param userInfo
	 * @param @return    参数
	 * @return boolean    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年9月14日 下午3:52:27  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws
	 */
	public boolean remove(ChatUserInfo userInfo) throws SQLException;
	
	
	
	
	
	/**
	 * @throws SQLException 
	 * @Title: remove
	 * @Description: 删除
	 * @param @param pkId
	 * @param @return    参数
	 * @return boolean    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年9月14日 下午3:53:35  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws
	 */
	public boolean remove(int pkId) throws SQLException;
	
	
	
	
	
	
	
	
	/**
	 * @throws SQLException 
	 * 修改方法(方法名称前缀通常为（不是强制要求，是约定俗成的规矩）：merge、update)
	 * @Title: merge
	 * @Description: 修改
	 * @param @param userInfo
	 * @param @return    参数
	 * @return boolean    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年9月14日 下午3:54:27  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws
	 */
	public boolean merge(ChatUserInfo userInfo) throws SQLException;
	
	
	
	
	
	
	
	/**
	 * @throws SQLException 
	 * 查询方法(方法名称前缀通常为（不是强制要求，是约定俗成的规矩）：find、query)
	 * @Title: findById
	 * @Description: 查询，根据主键id
	 * @param @param pkId
	 * @param @return    参数
	 * @return ChatUserInfo    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年9月14日 下午3:55:57  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws
	 */
	public ChatUserInfo findById(int pkId) throws SQLException;
	
	
	
	/**
	 * @throws SQLException 
	 * @Title: findAll
	 * @Description: 查询，查全部
	 * @param @return    参数
	 * @return List<ChatUserInfo>    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年9月14日 下午3:56:35  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws
	 */
	public List<ChatUserInfo> findAll() throws SQLException;
	
	
	/**
	 * @throws SQLException 
	 * @Title: findByExample
	 * @Description: 根据条件查询（多条件查询）
	 * @param @param isFuzzyQuery  true:模糊查询，false:精确查询
	 * @param @param example  封装查询条件的实体对象，在这个对象中，有条件，就set条件属性，没有的条件，就set为null（不设置默认为null）
	 * @param @return    参数
	 * @return List<ChatUserInfo>    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年9月14日 下午3:57:33  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws
	 */
	public List<ChatUserInfo> findByExample(boolean isFuzzyQuery, ChatUserInfo example) throws SQLException;
	
	
	
	/**
	 * @throws SQLException 
	 * @Title: findBySQL
	 * @Description: 查询，通过SQL,用于查询 大于 、 小于 、 区间 等...
	 * @param @param sql
	 * @param @param params
	 * @param @return    参数
	 * @return List<ChatUserInfo>    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年9月14日 下午4:01:18  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws
	 */
	public List<ChatUserInfo> findBySQL(String sql, Object ... params) throws SQLException;
	
	
	/**
	 * @Title: bulidListObj
	 * @Description: 封装方法，将查询结果集保存到List集合中
	 * @param @param rs
	 * @param @return
	 * @param @throws SQLException    参数
	 * @return List<ChatUserInfo>    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年9月17日 上午1:16:23  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws
	 */
	public List<ChatUserInfo> bulidListObj(ResultSet rs) throws SQLException;
	
	/**
	 * @Title: bulidObj
	 * @Description: 封装方法：将查询结果保存到对象中(单个)
	 * @param @param rs
	 * @param @return
	 * @param @throws SQLException    参数
	 * @return ChatUserInfo    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年9月17日 上午1:16:54  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws
	 */
	public ChatUserInfo bulidObj(ResultSet rs) throws SQLException;
	
	/**
	 * @Title: showListResult
	 * @Description: 显示List列表
	 * @param @param list    参数
	 * @return void    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年9月17日 上午8:36:59  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws
	 */
	public void showListResult(List<ChatUserInfo> list);
	
	
	
	/**
	 * @Title: closeDb
	 * @Description: 关闭数据库连接
	 * @param     参数
	 * @return void    返回类型
	 * @author: 章征武【orlando】
	 * @date: 2018年9月17日 上午9:52:52  
	 * @tel: 17520490925
	 * @email: zhangzw368319@163.com
	 * @throws
	 */
	public void closeDb();
	

}
