package com.flow.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flow.dao.BoardDAO;
import com.flow.vo.FreeboardVO;
import com.google.gson.Gson;

/**
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet("/BoardUpdateServlet")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String freeboard_title = request.getParameter("freeboard_title");		
		String freeboard_content = request.getParameter("freeboard_content");		
		int freeboard_no = Integer.parseInt(request.getParameter("freeboard_no"));
		
		FreeboardVO fvo = new FreeboardVO();
		fvo.setFreeboard_no(freeboard_no);
		fvo.setFreeboard_title(freeboard_title);
		fvo.setFreeboard_content(freeboard_content);
		
		BoardDAO bdao = BoardDAO.getInstance();
		FreeboardVO fbvo = bdao.updateBoard(fvo);
		
		Gson gson = new Gson();
		String json = gson.toJson(fbvo);
		response.getWriter().write(json.toString());
	}
}