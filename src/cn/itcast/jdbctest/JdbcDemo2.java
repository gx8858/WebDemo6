package cn.itcast.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

/**
 * ���ù����Ľ����
 * @author Administrator
 *
 */
public class JdbcDemo2 {
	
	@Test
	public void run1() throws ClassNotFoundException, SQLException{
		/**
		 * 1.ע������
		 * 2����ȡ����
		 * 3.ִ��SQL���
		 * 4.�ͷ���Դ
		 */
		// ע������
		Class.forName("com.mysql.jdbc.Driver");
		// ��ȡ����
		Connection conn = DriverManager.getConnection("jdbc:mysql:///day17", "root", "root");
		// ��дSQL���
		String sql  = "select * from t_user";
		// ����ִ��SQL������  ���ù����Ľ�������ֿ��Թ����ֿ��Ըģ�
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		// ִ��SQL���
		ResultSet rs = stmt.executeQuery(sql);
		
		// ִ������3��
		rs.absolute(3);
		// �޸ĸ���
		rs.updateString("username", "ccc");
		// ִ��
		rs.updateRow();
		
		rs.close();
		stmt.close();
		conn.close();
	}

}






