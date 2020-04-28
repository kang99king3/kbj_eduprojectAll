package com.hk.ansboard.dtos;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AnsDto {

	private int seq;
	private String id;
	private String title;
	private String content;
	private Date regdate;
	private int refer;
	private int step;
	private int depth;
	private int readcount;
	private String delflag;
	
	
	public AnsDto() {
		// TODO Auto-generated constructor stub
	}
	
	public AnsDto(int seq, String id, String title, String content) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
	}




	public AnsDto(int seq, String id, String title, String content, Date regdate, int refer, int step, int depth,
			int readcount, String delflag) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.refer = refer;
		this.step = step;
		this.depth = depth;
		this.readcount = readcount;
		this.delflag = delflag;
	}
	
	
}



