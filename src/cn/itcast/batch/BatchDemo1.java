package cn.itcast.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

import cn.itcast.utils.MyJdbcUtil;

/**
 * �����ݿ������1000������
 */
public class BatchDemo1 {
	
	/**
	 * ���������
	 */
	@Test
	public void run(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// ��ȡ����
			conn = MyJdbcUtil.getConnection();
			// ��дSQL���
			String sql = "insert into mybatch values (null,?)";
			// Ԥ����SQL
			stmt = conn.prepareStatement(sql);
			// ����ֵ��1000����
			for (int i = 1; i <= 1000; i++) {
				stmt.setString(1, "name"+i);
				// ��ӵ���������
				stmt.addBatch();
				if(i % 100 == 0){
					stmt.executeBatch();
					stmt.clearBatch();
				}
			}
			stmt.executeBatch();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MyJdbcUtil.release(stmt, conn);
		}
		
	}

}










