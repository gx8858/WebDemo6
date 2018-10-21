package cn.itcast.mydata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import cn.itcast.utils.MyJdbcUtil;

/**
 * ���ֱ��浽���ݿ���
 * @author Administrator
 *
 */
public class MyDataDemo2 {
	
	/**
	 * �������
	 */
	@Test
	public void run(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// ��ȡ����
			conn = MyJdbcUtil.getConnection();
			// ��д
			String sql = "insert into myblob values (null,?)";
			// Ԥ����SQL
			stmt = conn.prepareStatement(sql);
			File file = new File("d:/shizong.mp3");
			InputStream in = new FileInputStream(file);
			stmt.setBinaryStream(1, in, (int)file.length());
			// ִ��SQL
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MyJdbcUtil.release(stmt, conn);
		}
	}
	
	/**
	 * �����ݿ���ȡ������
	 */
	@Test
	public void run2(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// ��ȡ����
			conn = MyJdbcUtil.getConnection();
			// ��д
			String sql = "select * from myblob where id = ?";
			// Ԥ����SQL
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 1);
			// ִ��SQL
			rs = stmt.executeQuery();
			if(rs.next()){
				InputStream in = rs.getBinaryStream("mydata");
				OutputStream os = new FileOutputStream("d:/ss.mp3");
				byte [] b = new byte [1024];
				int len = 0;
				while((len = in.read(b)) != -1){
					os.write(b, 0, len);
				}
				os.close();
				in.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MyJdbcUtil.release(rs,stmt, conn);
		}
	}

}






