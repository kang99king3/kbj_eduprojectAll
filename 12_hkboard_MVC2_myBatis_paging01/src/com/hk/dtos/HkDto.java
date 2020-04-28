package com.hk.dtos;

import java.util.Date;
/*
  CREATE table "HKBOARD" (
    "SEQ"        NUMBER NOT NULL,
    "ID"         VARCHAR2(20) NOT NULL,
    "TITLE"      VARCHAR2(1000) NOT NULL,
    "CONTENT"    VARCHAR2(4000) NOT NULL,
    "REGDATE"    DATE NOT NULL,
    constraint  "HKBOARD_PK" primary key ("SEQ")
);

CREATE sequence "HKBOARD_SEQ" START WITH 1 INCREMENT BY 1;
 */
public class HkDto {

	private int seq;
	private String id;
	private String title;
	private String content;
	private Date regdate;
	
	public HkDto() {
		super();
	}
	
	public HkDto(int seq, String id, String title, String content, Date regdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
	}
	//insert할때 사용
	public HkDto(String id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}
	//update할때 사용
	public HkDto(int seq, String title, String content) {
		super();
		this.seq = seq;
		this.title = title;
		this.content = content;
	}

	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "HkDto [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ", regdate=" + regdate
				+ "]";
	}
	
	
}








