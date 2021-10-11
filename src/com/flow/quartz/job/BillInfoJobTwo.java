package com.flow.quartz.job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.flow.db.DBManager;
import com.flow.vo.SchVO;

public class BillInfoJobTwo implements Job{
	
	public void execute(JobExecutionContext context) throws JobExecutionException { 
		System.out.println("=========== 30초 마다 ===========");
		System.out.println(new Date(System.currentTimeMillis()));
		//update 적용
		
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String type2_Time = dateFormat.format(date);
		
		String sql = "UPDATE Flow_Sch SET type2 = 'Sch#2', type2_Time = ? WHERE type2 is null";
		Connection conn = null;
		PreparedStatement pstmt = null;		
			try {
				conn = DBManager.getConnection();				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, type2_Time);
				pstmt.executeUpdate();				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
	}	
}