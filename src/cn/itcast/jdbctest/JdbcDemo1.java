package cn.itcast.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

/**
 * JDBC�Ŀ�������
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
			// ������ô��
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql:///day17", "root", "root");
			// ��дSQL�����
			String sql = "select * from t_user";
			// ������ִ��SQL����Statement����
			stmt = conn.createStatement();
			// ִ��SQL���
			rs = stmt.executeQuery(sql);
			// ѭ������rs
			while(rs.next()){
				// �ó���
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
		 * 1.���������ע��
		 * 2.��ȡ����
		 * 3.ִ��SQL���
		 * 4.�ͷ���Դ
		 */
		// ע������
		// DriverManager.registerDriver(new Driver());
		
		// ������ô��
		Class.forName("com.mysql.jdbc.Driver");
		
		// ��ȡ����
		// Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day17", "root", "root");
		
		Connection conn = DriverManager.getConnection("jdbc:mysql:///day17", "root", "root");
		
		// ��дSQL�����
		String sql = "select * from t_user";
		// ������ִ��SQL����Statement����
		Statement stmt = conn.createStatement();
		// ִ��SQL���
		ResultSet rs = stmt.executeQuery(sql);
		// ѭ������rs
		while(rs.next()){
			// �ó���
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








