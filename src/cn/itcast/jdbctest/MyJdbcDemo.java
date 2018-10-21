package cn.itcast.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class MyJdbcDemo {

	public static void main(String[] args) {

		
	}
	
	@Test
	public void run1() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/day17?useUnicode=true&characterEncoding=UTF-8", "root", "123456");
		Statement stat = con.createStatement();
		String sql = "select * from t_user";
		ResultSet rs = stat.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			System.out.println(id + " " + username + " " + password);
		}
		rs.close();
		stat.close();
		con.close();
	}
	
	/**
	 * 从数据库中select
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void run2() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/day17?useUnicode=true&characterEncoding=UTF-8", "root", "123456");
		String sql = "select * from t_user";
		PreparedStatement stat = con.prepareStatement(sql);
		ResultSet rs = stat.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			System.out.println(id + " " + username + " " + password);
		}
		rs.close();
		stat.close();
		con.close();
	}
	
	
	/**
	 * 向数据库中insert
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void run3() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/day17?useUnicode=true&characterEncoding=UTF-8", "root", "123456");
		String sql = "insert into t_user values(null,?,?)";
		PreparedStatement stat = con.prepareStatement(sql);
		stat.setString(1, "张三");
		stat.setString(2, "888888");
		int row = stat.executeUpdate();
		System.out.println(row + "条插入成功");
		stat.close();
		con.close();
	}
	
	/**
	 * 向数据库中update
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void run4() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/day17?useUnicode=true&characterEncoding=UTF-8", "root", "123456");
		String sql = "update t_user set password = ? where username = ?";
		PreparedStatement stat = con.prepareStatement(sql);
		stat.setString(1, "777777");
		stat.setString(2, "张三");
		int row = stat.executeUpdate();
		System.out.println(row + "条更新成功");
		stat.close();
		con.close();
	}
	
	/**
	 * 向数据库中delete
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Test
	public void run5() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/day17?useUnicode=true&characterEncoding=UTF-8", "root", "123456");
		String sql = "delete from t_user where username = ?";
		PreparedStatement stat = con.prepareStatement(sql);
		stat.setString(1, "张三");
		int row = stat.executeUpdate();
		System.out.println(row + "条删除成功");
		stat.close();
		con.close();
	}

}
