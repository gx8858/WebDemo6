package cn.itcast.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

/**
 * JDBC的快速入门
 * @author Administrator
 *
 */
public class JdbcDemo1 {
	
	@Test
	public void run2(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 现在这么做
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql:///day17", "root", "root");
			// 编写SQL语句了
			String sql = "select * from t_user";
			// 创建能执行SQL语句的Statement对象
			stmt = conn.createStatement();
			// 执行SQL语句
			rs = stmt.executeQuery(sql);
			// 循环变量rs
			while(rs.next()){
				// 拿出来
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				System.out.println(id+" "+username+" "+password);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs = null;
			}
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
	
	
	@Test
	public void run1() throws SQLException, ClassNotFoundException{
		/**
		 * 1.完成驱动的注册
		 * 2.获取链接
		 * 3.执行SQL语句
		 * 4.释放资源
		 */
		// 注册驱动
		// DriverManager.registerDriver(new Driver());
		
		// 现在这么做
		Class.forName("com.mysql.jdbc.Driver");
		
		// 获取链接
		// Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day17", "root", "root");
		
		Connection conn = DriverManager.getConnection("jdbc:mysql:///day17", "root", "root");
		
		// 编写SQL语句了
		String sql = "select * from t_user";
		// 创建能执行SQL语句的Statement对象
		Statement stmt = conn.createStatement();
		// 执行SQL语句
		ResultSet rs = stmt.executeQuery(sql);
		// 循环变量rs
		while(rs.next()){
			// 拿出来
			int id = rs.getInt("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			System.out.println(id+" "+username+" "+password);
		}
		rs.close();
		stmt.close();
		conn.close();
	}
}








