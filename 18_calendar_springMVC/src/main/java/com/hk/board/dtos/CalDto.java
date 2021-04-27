package com.hk.board.dtos;

import java.util.Date;

import lombok.Data;


@Data
public class CalDto {
	
	private int seq;
	private String id;
	private String title;
	private String content;
	private String mdate;
	private Date regdate;
	
	//날짜 정보 파라미터를 받는 용도
	private String year;
	private String month;
	private String date;
	private String hour;
	private String min;
	public CalDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CalDto(int seq, String id, String title, String content, String mdate, Date regdate, String year,
			String month, String date, String hour, String min) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.mdate = mdate;
		this.regdate = regdate;
		this.year = year;
		this.month = month;
		this.date = date;
		this.hour = hour;
		this.min = min;
	}
//	public int getSeq() {
//		return seq;
//	}
//	public void setSeq(int seq) {
//		this.seq = seq;
//	}
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}
//	public String getContent() {
//		return content;
//	}
//	public void setContent(String content) {
//		this.content = content;
//	}
//	public String getMdate() {
//		return mdate;
//	}
//	public void setMdate(String mdate) {
//		this.mdate = mdate;
//	}
//	public Date getRegdate() {
//		return regdate;
//	}
//	public void setRegdate(Date regdate) {
//		this.regdate = regdate;
//	}
//	public String getYear() {
//		return year;
//	}
//	public void setYear(String year) {
//		this.year = year;
//	}
//	public String getMonth() {
//		return month;
//	}
//	public void setMonth(String month) {
//		this.month = month;
//	}
//	public String getDate() {
//		return date;
//	}
//	public void setDate(String date) {
//		this.date = date;
//	}
//	public String getHour() {
//		return hour;
//	}
//	public void setHour(String hour) {
//		this.hour = hour;
//	}
//	public String getMin() {
//		return min;
//	}
//	public void setMin(String min) {
//		this.min = min;
//	}
	@Override
	public String toString() {
		return "CalDto [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ", mdate=" + mdate
				+ ", regdate=" + regdate + ", year=" + year + ", month=" + month + ", date=" + date + ", hour=" + hour
				+ ", min=" + min + "]";
	}
	public CalDto(int seq, String id, String title, String content, String mdate, Date regdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.mdate = mdate;
		this.regdate = regdate;
	}
	
	
}
