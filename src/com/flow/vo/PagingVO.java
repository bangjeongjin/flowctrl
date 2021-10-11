package com.flow.vo;

public class PagingVO {
	private int rows; //페이지에 보여질 게시글 수
	private int currentPage;  //현재 페이지
	private int totalPage; //총 페이지 수
	private int startPage; //시작 페이지
	private int endPage;  //끝 페이지
	private int pageScale; //한 페이지에 보여질 페이지 수
	private int onePage;
	private boolean prev;
	private boolean next;
	
	
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getcurrentPage() {
		return currentPage;
	}
	public void setcurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getPageScale() {
		return pageScale;
	}
	public void setPageScale(int pageScale) {
		this.pageScale = pageScale;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getOnePage() {
		return onePage;
	}
	public void setOnePage(int onePage) {
		this.onePage = onePage;
	}
	
	
	
	
}
