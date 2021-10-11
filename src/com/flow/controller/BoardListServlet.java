package com.flow.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flow.dao.BoardDAO;
import com.flow.vo.FreeboardVO;
import com.google.gson.Gson;


/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/BoardListServlet")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html;charset=utf-8");
		//게시판 리스트	
		BoardDAO bdao = BoardDAO.getInstance();
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		String searchWord =null;
		if( request.getParameter("searchWord")!=null) {
			searchWord=request.getParameter("searchWord");
		}
		String searchType = request.getParameter("searchType");

		ArrayList<FreeboardVO> list = bdao.PagingList(currentPage, searchWord, searchType);
		/*
		 * list를 제이슨으로 변환후 out.println();여기다가 호출
		 */
		//Gson 사용 
		Gson gson = new Gson();
		String json = gson.toJson(list);
		response.getWriter().write(json.toString());		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
	}
}