package com.flow.quartz.job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.flow.db.DBManager;
import com.flow.vo.FreeboardVO;
import com.flow.vo.SchVO;

public class BillInfoJob implements Job{

	public void execute(JobExecutionContext context) throws JobExecutionException { 
		System.out.println("=========== 20초 마다 ===========");
		System.out.println(new Date(System.currentTimeMillis()));
		//insert 적용
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String type1 = "Sch#1";
		String type1_Time = dateFormat.format(date);
		
		String sql = "insert into Flow_Sch(index_no, type1, type1_Time) values(index_no_seq.nextval, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;		
			try {
				conn = DBManager.getConnection();				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, type1);
				pstmt.setString(2, type1_Time);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
	}
}	

