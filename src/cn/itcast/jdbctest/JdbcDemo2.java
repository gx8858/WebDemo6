package cn.itcast.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

/**
 * 设置滚动的结果集
 * @author Administrator
 *
 */
public class JdbcDemo2 {
	
	@Test
	public void run1() throws ClassNotFoundException, SQLException{
		/**
		 * 1.注册驱动
		 * 2，获取链接
		 * 3.执行SQL语句
		 * 4.释放资源
		 */
		// 注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		// 获取链接
		Connection conn = DriverManager.getConnection("jdbc:mysql:///day17", "root", "root");
		// 编写SQL语句
		String sql  = "select * from t_user";
		// 创建执行SQL语句对象  设置滚动的结果集（又可以滚，又可以改）
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		// 执行SQL语句
		ResultSet rs = stmt.executeQuery(sql);
		
		// 执行跳到3行
		rs.absolute(3);
		// 修改该行
		rs.updateString("username", "ccc");
		// 执行
		rs.updateRow();
		
		rs.close();
		stmt.close();
		conn.close();
	}

}






