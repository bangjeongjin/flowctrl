package com.flow.controller;

import java.io.IOException;
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
 * Servlet implementation class BoardSearchServlet
 */
@WebServlet("/BoardSearchServlet")
public class BoardSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String searchWord = request.getParameter("searchWord");
		BoardDAO bdao = BoardDAO.getInstance();
		List<FreeboardVO> list = bdao.getSearchedList(searchWord);
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		response.getWriter().write(json.toString());
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
	}
}