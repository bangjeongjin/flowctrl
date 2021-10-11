package com.flow.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.*;
import javax.sql.DataSource;

public class DBManager {
	//커넥션 객체를 얻어오는 메서드
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/flow");
			conn = ds.getConnection();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	//작업 수행 후 리소스 해제 - select
	public static void close(Connection conn, Statement pstmt, ResultSet rs) {
		try {
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//작업 수행 후 리소스 해제 - update, insert, delete
	public static void close(Connection conn, Statement pstmt) {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//	
	
	
}



















