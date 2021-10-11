package com.flow.vo;

import java.util.Date;

public class FreeboardVO {
	private int freeboard_no;
	private String freeboard_title;
	private String freeboard_content;
	private String file_name;
	private String path;
	private int file_size;
	private Date freeboard_writedate;
	
	public FreeboardVO() {
		super();
	}
	public FreeboardVO(int freeboard_no, String freeboard_title, String freeboard_content, String file_name,
			String path, int file_size, Date freeboard_writedate) {
		super();
		this.freeboard_no = freeboard_no;
		this.freeboard_title = freeboard_title;
		this.freeboard_content = freeboard_content;
		this.file_name = file_name;
		this.path = path;
		this.file_size = file_size;
		this.freeboard_writedate = freeboard_writedate;
	}
	public int getFreeboard_no() {
		return freeboard_no;
	}
	public void setFreeboard_no(int freeboard_no) {
		this.freeboard_no = freeboard_no;
	}
	public String getFreeboard_title() {
		return freeboard_title;
	}
	public void setFreeboard_title(String freeboard_title) {
		this.freeboard_title = freeboard_title;
	}
	public String getFreeboard_content() {
		return freeboard_content;
	}
	public void setFreeboard_content(String freeboard_content) {
		this.freeboard_content = freeboard_content;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getFile_size() {
		return file_size;
	}
	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}
	public Date getFreeboard_writedate() {
		return freeboard_writedate;
	}
	public void setFreeboard_writedate(Date freeboard_writedate) {
		this.freeboard_writedate = freeboard_writedate;
	}
	
	
	
	
	
	
}
