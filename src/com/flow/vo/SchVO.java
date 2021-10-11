package com.flow.vo;

public class SchVO {
	int index_no;
	String type1;
	String type1_Time;
	String type2;
	String type2_Time;
	
	public SchVO() {
		super();
	}
	
	public SchVO(int index_no, String type1, String type1_Time, String type2, String type2_Time) {
		super();
		this.index_no = index_no;
		this.type1 = type1;
		this.type1_Time = type1_Time;
		this.type2 = type2;
		this.type2_Time = type2_Time;
	}
	public int getIndex_no() {
		return index_no;
	}
	public void setIndex_no(int index_no) {
		this.index_no = index_no;
	}
	public String getType1() {
		return type1;
	}
	public void setType1(String type1) {
		this.type1 = type1;
	}
	public String getType1_Time() {
		return type1_Time;
	}
	public void setType1_Time(String type1_Time) {
		this.type1_Time = type1_Time;
	}
	public String getType2() {
		return type2;
	}
	public void setType2(String type2) {
		this.type2 = type2;
	}
	public String getType2_Time() {
		return type2_Time;
	}
	public void setType2_Time(String type2_Time) {
		this.type2_Time = type2_Time;
	}
	
}
