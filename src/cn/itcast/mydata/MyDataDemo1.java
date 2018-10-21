package cn.itcast.mydata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import cn.itcast.utils.MyJdbcUtil;

/**
 * �ı��ļ����浽���ݿ���
 */
public class MyDataDemo1 {
	@Test
	public void run(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// ��ȡ����
			conn = MyJdbcUtil.getConnection();
			// ��д
			String sql = "insert into mytext values (null,?)";
			// Ԥ����SQL
			stmt = conn.prepareStatement(sql);
			
			File file = new File("d:/dulala.txt");
			
			//��ϵͳ�ı��벻��utf-8ʱ�������ֽ������ļ�
			//************************************
			FileInputStream fis=new FileInputStream(file);  //��ȡ�ֽ���
			InputStreamReader isr=new InputStreamReader(fis,"UTF-8"); //����utf-8���뷽ʽת�����ַ���
			Reader reader = new BufferedReader(isr);  //��ȡ�ı��ļ������л���
			//************************************
			
			//��ϵͳ�ı�����utf-8ʱ�������ַ������ļ�
			//************************************
			//Reader reader = new FileReader(file);
			//************************************
			
			// ���ò���
			stmt.setCharacterStream(1, reader, (int)file.length());
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
			String sql = "select * from mytext where id = ?";
			// Ԥ����SQL
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 11);
			// ִ��SQL
			rs = stmt.executeQuery();
			if(rs.next()){
				
				//��ϵͳ�ı��벻��utf-8ʱ���ֽ���д�ļ�
				//************************************
				Reader r = rs.getCharacterStream("mydata");
				FileOutputStream fis=new FileOutputStream("d:/dulala2.txt");
				OutputStreamWriter isr=new OutputStreamWriter(fis,"UTF-8"); //����utf-8���뷽ʽת�����ַ���
				Writer w = new BufferedWriter(isr);  //��ȡ�ı��ļ������л���
				byte [] b = new byte [1024];
				int len = 0;
				while((len = r.read()) != -1){
					isr.write(len);
				}
				//************************************
				
				
				//��ϵͳ�ı��벻��utf-8ʱ���ַ���д�ļ�
				//************************************
				//Reader r = rs.getCharacterStream("mydata");
				//Writer w = new FileWriter("d:/dulala2.txt");
				//int len = 0;
				//while((len = r.read()) != -1){
				//	w.write(len);
				//}
				//************************************
				
				
				w.close();
				r.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MyJdbcUtil.release(rs,stmt, conn);
		}
	}
	
	@Test
	public void run3() {
		
		// �鿴ϵͳ�ı��루Ĭ���ַ�����
		
		String encoding = System.getProperty("file.encoding");
        System.out.println("Default System Encoding:" + encoding);
	}

}






