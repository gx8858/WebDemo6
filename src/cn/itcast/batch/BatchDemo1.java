package cn.itcast.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

import cn.itcast.utils.MyJdbcUtil;

/**
 * 想数据库中添加1000条数据
 */
public class BatchDemo1 {
	
	/**
	 * 完成批处理
	 */
	@Test
	public void run(){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			// 获取链接
			conn = MyJdbcUtil.getConnection();
			// 编写SQL语句
			String sql = "insert into mybatch values (null,?)";
			// 预编译SQL
			stmt = conn.prepareStatement(sql);
			// 设置值（1000条）
			for (int i = 1; i <= 1000; i++) {
				stmt.setString(1, "name"+i);
				// 添加到批处理中
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










