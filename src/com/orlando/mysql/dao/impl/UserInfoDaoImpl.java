package com.orlando.mysql.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.orlando.entity.ChatUserInfo;
import com.orlando.mysql.dao.UserInfoDao;
import com.orlando.utils.DBUtils;
import com.orlando.utils.Util;


 /** 
 * @ClassName: UserInfoDaoImpl 
 * @Description: ChatUserInfo实体类Dao接口实现(初级版本，使用匿名参数)
 * @author: 章征武【orlando】
 * @date: 2018年9月14日 下午3:32:28 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com 
 */
public class UserInfoDaoImpl implements UserInfoDao {
	
	//解耦DBUtils 数据库连接工具类
	//定义私有属性
	private DBUtils db;
	
	//空构造器
	public UserInfoDaoImpl() {
		//在创建对象的时候同时创建数据看连接
		this.setDb(new DBUtils());
	}
	//通过set方法
	public void setDb(DBUtils db) {
		this.db = db;
	}

	
	public DBUtils getDb() {
		return db;
	}


	@Override
	public boolean add(ChatUserInfo userInfo) throws SQLException {
		int num = -1;
		StringBuffer sql = new StringBuffer("");
		sql.append(" insert into tb_user_info ");
		sql.append(" (u_name,u_pwd,u_nick,u_email,u_phone,u_card_id,u_register_time,u_remark)");
		sql.append(" values (?,?,?,?,?,?,?,?);");
		try {
			num = db.doUpdate(sql.toString(), 
					userInfo.getUserName(),
					userInfo.getUserPwd(),
					userInfo.getUserNick(),
					userInfo.getUserEmail(),
					userInfo.getUserPhone(),
					userInfo.getUserCardId(),
					userInfo.getUserRegisterTime(),
					userInfo.getUserRemark());
			if(num > 0){
				return true;
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		}
		return false;
	}

	@Override
	public boolean remove(ChatUserInfo userInfo) throws SQLException {
		try {
			if(remove(userInfo.getUserId())){
				return true;
			}
		} catch (SQLException e) {
			throw  new SQLException(e);
		}
		return false;
	}

	@Override
	public boolean remove(int pkId) throws SQLException {
		int num = -1;
		StringBuffer sql = new StringBuffer("");
		sql.append(" delete from tb_user_info");
		sql.append(" where u_id = ?;");
		try {
			num = db.doUpdate(sql.toString(), pkId);
			if(num>0){
				return true;
			}
		} catch (SQLException e) {
			throw  new SQLException(e);
		}
		return false;
	}

	@Override
	public boolean merge(ChatUserInfo userInfo) throws SQLException {
		int num = -1;
		StringBuffer sql = new StringBuffer("");
		sql.append(" update tb_user_info set");
		sql.append(" u_name=?, u_pwd=?, u_nick=?, u_img=?,");
		sql.append(" u_email=?, u_phone=?, u_card_id=?, u_state=?, u_remark=?");
		sql.append(" where u_id=?;");
		try {
			num = db.doUpdate(sql.toString(), 
						userInfo.getUserName(),
						userInfo.getUserPwd(),
						userInfo.getUserNick(),
						userInfo.getUserImg(),
						userInfo.getUserEmail(),
						userInfo.getUserPhone(),
						userInfo.getUserCardId(),
						userInfo.getUserState()? 1 : 0,
						userInfo.getUserRemark(),
						userInfo.getUserId());
			if(num > 0){
				return true;
			}
		} catch (SQLException e) {
			throw  new SQLException(e);
		}
		return false;
	}

	@Override
	public ChatUserInfo findById(int pkId) throws SQLException {
		StringBuffer sql = new StringBuffer("");
		sql.append(" select * from tb_user_info where u_id = ?;");
		ResultSet rs = null;
		try {
			rs = db.doQuery(sql.toString(), pkId);
			if(rs.next()){
				return bulidObj(rs);    //需要将结果return出去
			}
		} catch (SQLException e) {
			throw  new SQLException(e);
		}
		return null;
	}

	@Override
	public ChatUserInfo bulidObj(ResultSet rs) throws SQLException{
		ChatUserInfo userInfo = new ChatUserInfo();
		userInfo.setUserId(Integer.valueOf(rs.getString("u_id")));
		userInfo.setUserName(rs.getString("u_name"));
		userInfo.setUserPwd(rs.getString("u_pwd"));
		userInfo.setUserNick(rs.getString("u_nick"));
		userInfo.setUserImg(rs.getString("u_img"));
		userInfo.setUserEmail(rs.getString("u_email"));
		userInfo.setUserPhone(rs.getString("u_phone"));
		userInfo.setUserCardId(rs.getString("u_card_id"));
		userInfo.setUserRegisterTime(new Date(System.currentTimeMillis()));
		userInfo.setUserState(rs.getBoolean("u_state"));
		userInfo.setUserRemark(rs.getString("u_remark"));
		return userInfo;
	}
	
	@Override
	public List<ChatUserInfo> bulidListObj(ResultSet rs) throws SQLException{
		List<ChatUserInfo> list = new ArrayList<>();
		rs.beforeFirst();
		while(rs.next()){
			list.add(bulidObj(rs));
		}
		return list;
	}
	
	
	
	@Override
	public List<ChatUserInfo> findAll() throws SQLException {
		StringBuffer sql = new StringBuffer("");
		sql.append(" select * from tb_user_info;");
		try {
			return bulidListObj(db.doQuery(sql.toString()));
		} catch (SQLException e) {
			throw  new SQLException(e);
		}
	}

	@Override
	public List<ChatUserInfo> findByExample(boolean isFuzzyQuery, ChatUserInfo example) throws SQLException {
		List<Object> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer("");
		sql.append(" select * from tb_user_info where 1=1");
		if(isFuzzyQuery){//模糊查询
			if(!Util.objIsNULL(example.getUserId())){
				sql.append(" and u_id like ?");
				list.add("%"+example.getUserId()+"%");
			}
			if(!Util.objIsNULL(example.getUserName())){
				sql.append(" and u_name like ?");
				list.add("%"+example.getUserName()+"%");
			}
			if(!Util.objIsNULL(example.getUserPwd())){
				sql.append(" and u_pwd like ?");
				list.add("%"+example.getUserPwd()+"%");
			}
			if(!Util.objIsNULL(example.getUserNick())){
				sql.append(" and u_nick like ?");
				list.add("%"+example.getUserNick()+"%");
			}
			if(!Util.objIsNULL(example.getUserImg())){
				sql.append(" and u_img like ?");
				list.add("%"+example.getUserImg()+"%");
			}
			if(!Util.objIsNULL(example.getUserEmail())){
				sql.append(" and u_email like ?");
				list.add("%"+example.getUserEmail()+"%");
			}
			if(!Util.objIsNULL(example.getUserPhone())){
				sql.append(" and u_phone like ?");
				list.add("%"+example.getUserPhone()+"%");
			}
			if(!Util.objIsNULL(example.getUserCardId())){
				sql.append(" and u_card_id like ?");
				list.add("%"+example.getUserCardId()+"%");
			}
			if(!Util.objIsNULL(example.getUserRegisterTime())){
				sql.append(" and u_register_time like ?");
				list.add("%"+example.getUserRegisterTime()+"%");
			}
			if(!Util.objIsNULL(example.getUserState())){
				sql.append(" and u_state like ?");
				list.add("%"+example.getUserState()+"%");
			}
			if(!Util.objIsNULL(example.getUserRemark())){
				sql.append(" and u_remark like ?");
				list.add("%"+example.getUserRemark()+"%");
			}
		}else{//精确查询
			if(!Util.objIsNULL(example.getUserId())){
				sql.append(" and u_id = ?");
				list.add(example.getUserId());
			}
			if(!Util.objIsNULL(example.getUserName())){
				sql.append(" and u_name = ?");
				list.add(example.getUserName());
			}
			if(!Util.objIsNULL(example.getUserPwd())){
				sql.append(" and u_pwd = ?");
				list.add(example.getUserPwd());
			}
			if(!Util.objIsNULL(example.getUserNick())){
				sql.append(" and u_nick = ?");
				list.add(example.getUserNick());
			}
			if(!Util.objIsNULL(example.getUserImg())){
				sql.append(" and u_img = ?");
				list.add(example.getUserImg());
			}
			if(!Util.objIsNULL(example.getUserEmail())){
				sql.append(" and u_email = ?");
				list.add(example.getUserEmail());
			}
			if(!Util.objIsNULL(example.getUserPhone())){
				sql.append(" and u_phone = ?");
				list.add(example.getUserPhone());
			}
			if(!Util.objIsNULL(example.getUserCardId())){
				sql.append(" and u_card_id = ?");
				list.add(example.getUserCardId());
			}
			if(!Util.objIsNULL(example.getUserRegisterTime())){
				sql.append(" and u_register_time = ?");
				list.add(example.getUserRegisterTime());
			}
			if(!Util.objIsNULL(example.getUserState())){
				sql.append(" and u_state = ?");
				list.add(example.getUserState());
			}
			if(!Util.objIsNULL(example.getUserRemark())){
				sql.append(" and u_remark = ?");
				list.add(example.getUserRemark());
			}			
		}
		try {
			return bulidListObj(db.doQuery(sql.toString(),list.toArray()));
		} catch (SQLException e) {
			throw  new SQLException(e);
		}
	}

	@Override
	public List<ChatUserInfo> findBySQL(String sql, Object... params) throws SQLException {
		try {
			return bulidListObj(db.doQuery(sql, params));
		} catch (SQLException e) {
			throw  new SQLException(e);
		}
	}
	
	@Override
	public void showListResult(List<ChatUserInfo> list){
		for (ChatUserInfo cui : list) {
			System.out.println(cui);
		}
	}
	
	@Override
	public void closeDb(){
		db.closeConnection();
	}
	
	
}
