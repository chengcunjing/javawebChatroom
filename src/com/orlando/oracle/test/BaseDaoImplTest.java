package com.orlando.oracle.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.orlando.oracle.dao.BaseDao;
import com.orlando.oracle.dao.PRowMapper;
import com.orlando.oracle.dao.impl.BaseDaoImpl;
import com.orlando.oracle.entity.Emp;
import com.orlando.util.C3P0Utils;

 /** 
 * @ClassName: BaseDaoImplTest 
 * @Description: BaseDao测试类
 * @author: 章征武【orlando】
 * @date: 2018年9月13日 上午10:34:40 
 * @tel: 17520490925
 * @email: zhangzw368319@163.com 
 */
public class BaseDaoImplTest {

	@Test
	void testQueryForListStringObjectArray() throws SQLException {
		BaseDao jdbcDao = new BaseDaoImpl();
		String sql = "select * from emp where mgr = ?";
		List<Map<String, Object>> result = jdbcDao.queryForList(sql, 7839);
		for (Map<String, Object> map : result) {
			System.out.println(map.get("EMPNO")+"\t"+
							   map.get("ENAME")+"\t"+
							   map.get("JOB")+"\t"+
							   map.get("MGR")+"\t"+
							   map.get("HIREDATE")+"\t"+
							   map.get("SAL")+"\t"+
							   map.get("COMM")+"\t"+
							   map.get("DEPTNO"));
		}
	}

	@Test
	void testQueryForListPRowMapperOfEStringObjectArray() throws SQLException {
		BaseDao jdbcDao = new BaseDaoImpl();
		String sql = "select * from emp_bak where mgr = ?";
		List<Emp> result = jdbcDao.queryForList(new PRowMapper<Emp>() {
			@Override
			public Emp mappingRow(ResultSet rs, int rownum) throws SQLException {
				Emp emp = new Emp();
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				emp.setMgr(rs.getInt("mgr"));
				emp.setHiredate(rs.getDate("hiredate"));
				emp.setSal(rs.getDouble("sal"));
				emp.setComm(rs.getDouble("comm"));
				emp.setDeptno(rs.getInt("deptno"));
				return emp;
			}
		}, sql, 7839);
		
		for (Emp emp : result) {
			System.out.println(emp.getEmpno() + "\t" + 
							   emp.getEname() + "\t" + 
							   emp.getJob() + "\t" + 
							   emp.getMgr() + "\t" + 
							   emp.getHiredate() + "\t" + 
							   emp.getSal() + "\t" + 
							   emp.getComm() + "\t" + 
							   emp.getDeptno());
		}
	}

	@Test //java.lang.NoSuchMethodException: com.orlando.practice.po.Emp.setEmpno()  --没有参数
	void testQueryForListFailed() throws SQLException {
		BaseDao jdbcDao = new BaseDaoImpl();
		String sql = "select * from emp_bak where mgr = ?";
		List<Emp> result = jdbcDao.queryForListFailed(Emp.class,sql,7839);
		for (Emp emp : result) {
			System.out.println(emp.getEmpno() + "\t" + 
					   emp.getEname() + "\t" + 
					   emp.getJob() + "\t" + 
					   emp.getMgr() + "\t" + 
					   emp.getHiredate() + "\t" + 
					   emp.getSal() + "\t" + 
					   emp.getComm() + "\t" + 
					   emp.getDeptno());
		}
	}

	@Test
	void testQueryForListSuccessByField() throws SQLException {
		BaseDao jdbcDao = new BaseDaoImpl();
		String sql = "select * from emp_bak where mgr = ?";
		List<Emp> result = jdbcDao.queryForListSuccessByField(Emp.class,sql,7839);
		for (Emp emp : result) {
			System.out.println(emp.getEmpno() + "\t" + 
					   emp.getEname() + "\t" + 
					   emp.getJob() + "\t" + 
					   emp.getMgr() + "\t" + 
					   emp.getHiredate() + "\t" + 
					   emp.getSal() + "\t" + 
					   emp.getComm() + "\t" + 
					   emp.getDeptno());
		}
	}
	@Test
	void testQueryForListSuccessByMethod() throws SQLException {
		BaseDao jdbcDao = new BaseDaoImpl();
		String sql = "select empno,ename,job,mgr,hiredate,sal,comm,deptno from emp_bak where mgr = ?";
		List<Emp> result = jdbcDao.queryForListSuccessByMethod(Emp.class,sql,7839);
		for (Emp emp : result) {
			System.out.println(emp.getEmpno() + "\t" + 
					emp.getEname() + "\t" + 
					emp.getJob() + "\t" + 
					emp.getMgr() + "\t" + 
					emp.getHiredate() + "\t" + 
					emp.getSal() + "\t" + 
					emp.getComm() + "\t" + 
					emp.getDeptno());
		}
	}
	@Test
	void testQueryForListSuccessByOtherMethod() throws SQLException {
		BaseDao jdbcDao = new BaseDaoImpl();
		String sql = "select empno,ename,job,mgr,hiredate,sal,comm,deptno from emp_bak where mgr = ?";
		List<Emp> result = jdbcDao.queryForListSuccessByOtherMethod(Emp.class,sql,7839);
		for (Emp emp : result) {
			System.out.println(emp.getEmpno() + "\t" + 
					emp.getEname() + "\t" + 
					emp.getJob() + "\t" + 
					emp.getMgr() + "\t" + 
					emp.getHiredate() + "\t" + 
					emp.getSal() + "\t" + 
					emp.getComm() + "\t" + 
					emp.getDeptno());
		}
	}

	@Test
	void testQueryForObjectStringObjectArray() throws SQLException {
		BaseDao jdbcDao = new BaseDaoImpl();
		String sql = "select * from emp_bak where empno = ?";
		Map<String, Object> result = jdbcDao.queryForObject(sql, 7566);
		System.out.println(result.get("EMPNO")+"\t"+
						   result.get("ENAME")+"\t"+
						   result.get("JOB")+"\t"+
						   result.get("MGR")+"\t"+
						   result.get("HIREDATE")+"\t"+
						   result.get("SAL")+"\t"+
						   result.get("COMM")+"\t"+
						   result.get("DEPTNO"));
	}

	@Test
	void testQueryForObjectPRowMapperOfEStringObjectArray() throws SQLException {
		BaseDao jdbcDao = new BaseDaoImpl();
		String sql = "select * from emp_bak where empno = ?";
		Emp emp = jdbcDao.queryForObject(new PRowMapper<Emp>() {

			@Override
			public Emp mappingRow(ResultSet rs, int rownum) throws SQLException {
				Emp emp = new Emp();
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				emp.setMgr(rs.getInt("mgr"));
				emp.setHiredate(rs.getDate("hiredate"));
				emp.setSal(rs.getDouble("sal"));
				emp.setComm(rs.getDouble("comm"));
				emp.setDeptno(rs.getInt("deptno"));
				return emp;
			}
		}, sql, 7566);
		System.out.println(emp.getEmpno() + "\t" + 
				   emp.getEname() + "\t" + 
				   emp.getJob() + "\t" + 
				   emp.getMgr() + "\t" + 
				   emp.getHiredate() + "\t" + 
				   emp.getSal() + "\t" + 
				   emp.getComm() + "\t" + 
				   emp.getDeptno());
	}

	@Test
	void testQueryForObjectSuccess() throws SQLException {
		BaseDao jdbcDao = new BaseDaoImpl();
		String sql = "select * from emp_bak where empno = ?";
		Emp emp = jdbcDao.queryForObjectSuccess(Emp.class, sql, 7566);
		System.out.println(emp.getEmpno() + "\t" + 
				   emp.getEname() + "\t" + 
				   emp.getJob() + "\t" + 
				   emp.getMgr() + "\t" + 
				   emp.getHiredate() + "\t" + 
				   emp.getSal() + "\t" + 
				   emp.getComm() + "\t" + 
				   emp.getDeptno());
	}
	
	
	@Test
	void testExecuteUpdate() throws SQLException {
		BaseDao jdbcDao = new BaseDaoImpl();
		String sql = "update emp_bak set sal = 3000 where empno = ?";
		int num = jdbcDao.executeUpdate(sql, 7566);
		System.out.println("更新的条数为： "+ num +"条");
	}
	
	@Test
	void testExecuteBatch() throws SQLException {
		int num = -1;
		BaseDao jdbcDao = new BaseDaoImpl();
		String sql = "update emp_bak set sal = 4000 where empno = ?";
		List<Object[]> params = new ArrayList<>();
		Object[] empno1 = new Object[] {7566};
		Object[] empno2 = new Object[] {7698};
		params.add(empno1);
		params.add(empno2);
		//设置回滚点
		Savepoint savePoint = null;
		try {
			//开启事务
			C3P0Utils.openTranslation();
			num = jdbcDao.executeBatch(sql, params);
			//给回滚点赋值
			savePoint = C3P0Utils.getConnection().setSavepoint();
			int ex = 1/0;
			System.out.println(ex);
		} catch (Exception e) {
			//异常回滚
//			C3P0Utils.rollbackTranslation();
			//回滚至回滚点
			C3P0Utils.getConnection().rollback(savePoint);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		//提交事务
		C3P0Utils.commitTranslation();
		System.out.println("更新的条数为： "+ num +"条");
	}

}
