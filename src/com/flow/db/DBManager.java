package com.flow.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.*;
import javax.sql.DataSource;

public class DBManager {
	//Ŀ�ؼ� ��ü�� ������ �޼���
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
	
	//�۾� ���� �� ���ҽ� ���� - select
	public static void close(Connection conn, Statement pstmt, ResultSet rs) {
		try {
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//�۾� ���� �� ���ҽ� ���� - update, insert, delete
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



















