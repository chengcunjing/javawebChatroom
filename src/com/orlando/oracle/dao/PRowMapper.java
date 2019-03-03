package com.orlando.oracle.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @ClassName: PRowMapper 
 * @Description: 将一行数据转换成一个对象的一个实例【我是这么理解的】，具体实现由调用者来决定
 * @author: 章征武【orlando】
 * @date: 2018年7月25日 下午3:19:30 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com
 * @param <T>
 */
public interface PRowMapper<T> {
	public T mappingRow(ResultSet rs,int rownum) throws SQLException;
}
