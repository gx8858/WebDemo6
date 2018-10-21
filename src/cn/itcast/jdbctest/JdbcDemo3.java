package cn.itcast.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import cn.itcast.utils.MyJdbcUtil;

/**
 * 完成t_user表的增删改查
 * @author Administrator
 *
 */
public class JdbcDemo3 {
	
	@Test
	public void testSelect(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 加载驱动，现在不用了，直接获取链接对象
			conn = MyJdbcUtil.getConnection();
			// 编写SQL语句
			String sql = "select * from t_user";
			// 创建执行
			stmt = conn.createStatement();
			// 执行
			rs = stmt.executeQuery(sql);
			// 循环遍历
			while(rs.next()){
				System.out.println(rs.getInt("id")+" "+rs.getString("username")+" "+rs.getString("password"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			// 释放资源
			MyJdbcUtil.release(rs, stmt, conn);
		}
	}
	
	/**
	 * 测试添加的功能
	 */
	@Test
	public void testInsert(){
		Connection conn = null;
		Statement stmt = null;
		try {
			// 注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 获取链接
			conn = DriverManager.getConnection("jdbc:mysql:///day17", "root", "123456");
			// 编写SQL语句
			String sql = "insert into t_user values (null,'小凤','111')";
			// 能执行该SQL语句的对象
			stmt = conn.createStatement();
			// 执行
			int index = stmt.executeUpdate(sql);
			if(index > 0){
				System.out.println("新增数据成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stmt = null;
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				conn = null;
			}
		}
	}
	
	
	/**
	 * 测试添加的功能
	 */
	@Test
	public void testUpdate(){
		Connection conn = null;
		Statement stmt = null;
		try {
			// 注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 获取链接
			conn = DriverManager.getConnection("jdbc:mysql:///day17", "root", "123456");
			// 编写SQL语句
			String sql = "update t_user set username = '如花',password='555' where id = 5 ";
			// 能执行该SQL语句的对象
			stmt = conn.createStatement();
			// 执行
			int index = stmt.executeUpdate(sql);
			if(index > 0){
				System.out.println("修改数据成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stmt = null;
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				conn = null;
			}
		}
	}
	
	
	/**
	 * 测试添加的功能
	 */
	@Test
	public void testDelete(){
		Connection conn = null;
		Statement stmt = null;
		try {
			// 注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 获取链接
			conn = DriverManager.getConnection("jdbc:mysql:///day17", "root", "123456");
			// 编写SQL语句
			String sql = "delete from t_user where id = 5";
			// 能执行该SQL语句的对象
			stmt = conn.createStatement();
			// 执行
			int index = stmt.executeUpdate(sql);
			if(index > 0){
				System.out.println("删除数据成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stmt = null;
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				conn = null;
			}
		}
	}

}
