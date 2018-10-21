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
 * 文本文件保存到数据库中
 */
public class MyDataDemo1 {
	@Test
	public void run(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// 获取链接
			conn = MyJdbcUtil.getConnection();
			// 编写
			String sql = "insert into mytext values (null,?)";
			// 预编译SQL
			stmt = conn.prepareStatement(sql);
			
			File file = new File("d:/dulala.txt");
			
			//当系统的编码不是utf-8时可以用字节流读文件
			//************************************
			FileInputStream fis=new FileInputStream(file);  //获取字节流
			InputStreamReader isr=new InputStreamReader(fis,"UTF-8"); //按照utf-8编码方式转化成字符流
			Reader reader = new BufferedReader(isr);  //获取文本文件并进行缓冲
			//************************************
			
			//当系统的编码是utf-8时可以用字符流读文件
			//************************************
			//Reader reader = new FileReader(file);
			//************************************
			
			// 设置参数
			stmt.setCharacterStream(1, reader, (int)file.length());
			// 执行SQL
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MyJdbcUtil.release(stmt, conn);
		}
	}
	
	/**
	 * 从数据库中取出数据
	 */
	@Test
	public void run2(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			// 获取链接
			conn = MyJdbcUtil.getConnection();
			// 编写
			String sql = "select * from mytext where id = ?";
			// 预编译SQL
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 11);
			// 执行SQL
			rs = stmt.executeQuery();
			if(rs.next()){
				
				//当系统的编码不是utf-8时用字节流写文件
				//************************************
				Reader r = rs.getCharacterStream("mydata");
				FileOutputStream fis=new FileOutputStream("d:/dulala2.txt");
				OutputStreamWriter isr=new OutputStreamWriter(fis,"UTF-8"); //按照utf-8编码方式转化成字符流
				Writer w = new BufferedWriter(isr);  //获取文本文件并进行缓冲
				byte [] b = new byte [1024];
				int len = 0;
				while((len = r.read()) != -1){
					isr.write(len);
				}
				//************************************
				
				
				//当系统的编码不是utf-8时用字符流写文件
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
		
		// 查看系统的编码（默认字符集）
		
		String encoding = System.getProperty("file.encoding");
        System.out.println("Default System Encoding:" + encoding);
	}

}






