package cn.itcast.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import cn.itcast.utils.MyJdbcUtil;

/**
 * ���t_user�����ɾ�Ĳ�
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
			// �������������ڲ����ˣ�ֱ�ӻ�ȡ���Ӷ���
			conn = MyJdbcUtil.getConnection();
			// ��дSQL���
			String sql = "select * from t_user";
			// ����ִ��
			stmt = conn.createStatement();
			// ִ��
			rs = stmt.executeQuery(sql);
			// ѭ������
			while(rs.next()){
				System.out.println(rs.getInt("id")+" "+rs.getString("username")+" "+rs.getString("password"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			// �ͷ���Դ
			MyJdbcUtil.release(rs, stmt, conn);
		}
	}
	
	/**
	 * ������ӵĹ���
	 */
	@Test
	public void testInsert(){
		Connection conn = null;
		Statement stmt = null;
		try {
			// ע������
			Class.forName("com.mysql.jdbc.Driver");
			// ��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql:///day17", "root", "123456");
			// ��дSQL���
			String sql = "insert into t_user values (null,'С��','111')";
			// ��ִ�и�SQL���Ķ���
			stmt = conn.createStatement();
			// ִ��
			int index = stmt.executeUpdate(sql);
			if(index > 0){
				System.out.println("�������ݳɹ�");
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
	 * ������ӵĹ���
	 */
	@Test
	public void testUpdate(){
		Connection conn = null;
		Statement stmt = null;
		try {
			// ע������
			Class.forName("com.mysql.jdbc.Driver");
			// ��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql:///day17", "root", "123456");
			// ��дSQL���
			String sql = "update t_user set username = '�绨',password='555' where id = 5 ";
			// ��ִ�и�SQL���Ķ���
			stmt = conn.createStatement();
			// ִ��
			int index = stmt.executeUpdate(sql);
			if(index > 0){
				System.out.println("�޸����ݳɹ�");
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
	 * ������ӵĹ���
	 */
	@Test
	public void testDelete(){
		Connection conn = null;
		Statement stmt = null;
		try {
			// ע������
			Class.forName("com.mysql.jdbc.Driver");
			// ��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql:///day17", "root", "123456");
			// ��дSQL���
			String sql = "delete from t_user where id = 5";
			// ��ִ�и�SQL���Ķ���
			stmt = conn.createStatement();
			// ִ��
			int index = stmt.executeUpdate(sql);
			if(index > 0){
				System.out.println("ɾ�����ݳɹ�");
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
