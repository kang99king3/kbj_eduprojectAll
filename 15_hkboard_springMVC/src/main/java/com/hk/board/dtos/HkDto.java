package com.hk.board.dtos;

import java.util.Date;

import org.junit.platform.commons.annotation.Testable;

import com.sun.javafx.beans.IDProperty;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//lombok 라이브러리 ---> 맴버필드만 정의하고 놔두면 됨

@Data
@ToString(exclude= {"content"})
public class HkDto {

	
	private int seq;
	private String id;
	private String title;
	private String content;
	private Date regdate;
	
//	public HkDto() {
//		super();
//	}

//	public HkDto(int seq) {
//		super();
//		this.seq = seq;
//	}
//	public HkDto(int seq, String id, String title, String content, Date regdate) {
//		super();
//		this.seq = seq;
//		this.id = id;
//		this.title = title;
//		this.content = content;
//		this.regdate = regdate;
//	}
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
//	public Date getRegdate() {
//		return regdate;
//	}
//	public void setRegdate(Date regdate) {
//		this.regdate = regdate;
//	}
//	@Override
//	public String toString() {
//		return "HkDto [seq=" + seq + ", id=" + id + ", title=" + title + ", content=" + content + ", regdate=" + regdate
//				+ "]";
//	}
	
	
}
