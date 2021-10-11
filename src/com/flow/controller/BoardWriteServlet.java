package com.flow.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flow.db.DBManager;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class BoardWriteServlet
 */
@WebServlet("/BoardWriteServlet")
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardWriteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request
			, HttpServletResponse response)
			throws ServletException, IOException {
		
		//게시판 글쓰기		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String freeboard_title ="";
		String freeboard_content = "";
		String file_name = request.getParameter("file_name");
		String path = request.getParameter("path");		
		String freeboard_writedate = dateFormat.format(date);
		// file upload
		try {
	        path ="C:\\upload_files";
	        
	        File targetDir = new File(path);
	        if(!targetDir.exists()) {
	        	targetDir.mkdirs();
	        }	        
	        int maxSize = 1024 * 1024 * 500;
	        String encoding = "UTF-8";
	        
	        MultipartRequest multipartRequest = new MultipartRequest(request, path, maxSize, encoding, new DefaultFileRenamePolicy());
	        freeboard_title = multipartRequest.getParameter("freeboard_title");
	        freeboard_content =multipartRequest.getParameter("freeboard_content");
	        file_name = multipartRequest.getOriginalFileName("file_name");	        
		} catch (Exception e) {
			e.printStackTrace();
		} 		
		String sql = "insert into Flow_Freeboard(freeboard_no, freeboard_title, freeboard_content, file_name, path, freeboard_writedate) values(freeboard_no_seq.nextval, ?, ?, ?, ?, ?)";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, freeboard_title);
			pstmt.setString(2, freeboard_content);
			pstmt.setString(3, file_name);
			pstmt.setString(4, path);
			pstmt.setString(5, freeboard_writedate);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}	
	}
}