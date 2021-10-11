package com.flow.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flow.dao.BoardDAO;
import com.flow.vo.FreeboardVO;
import com.flow.vo.PagingVO;
import com.google.gson.Gson;

/**
 * Servlet implementation class BoardPageServlet
 */
@WebServlet("/BoardPageServlet")
public class BoardPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		BoardDAO dao = BoardDAO.getInstance();
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		String searchWord =null;
		if( request.getParameter("searchWord")!=null) {
			searchWord=request.getParameter("searchWord");
		}
		String searchType = request.getParameter("searchType");
		int count = dao.getAllCount(currentPage, searchWord, searchType);			
		int totalCount = count;
		int rows = 10; //페이지에 보여질 게시글 수
		int totalPage = 0; //총 페이지 수
		int startPage = 1; //시작 페이지
		int onePage = 1;
		int endPage;  //끝 페이지
		int pageScale = 10; //한 페이지에 보여질 페이지 수
		boolean prev;
		boolean next;

		endPage = ((int)Math.ceil(currentPage /(double)pageScale))*pageScale;		
		startPage = (endPage - pageScale) + 1;		
		totalPage = (int)Math.ceil(totalCount/(double)rows);
		
		//-----처음 페이지 끝 페이지
		if(totalPage<endPage) {
			endPage = totalPage;		
		}
		next = endPage * rows >= totalCount ? false:true;
		
		prev = (startPage == 1)?false:true; //page가 11이상에서만 나오게 해야함
		//--------		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}		
		PagingVO pagingVO = new PagingVO();
		pagingVO.setcurrentPage((int)currentPage);
		pagingVO.setTotalPage(count);
		pagingVO.setEndPage(endPage);
		pagingVO.setNext(next);
		pagingVO.setPrev(prev);
		pagingVO.setStartPage(startPage);
		pagingVO.setOnePage(onePage);
		pagingVO.setTotalPage(totalPage);
		
		Gson gson = new Gson();
		String json = gson.toJson(pagingVO);
		response.getWriter().write(json.toString());		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
