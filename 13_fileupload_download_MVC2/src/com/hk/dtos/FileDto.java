package com.hk.dtos;

import java.util.Date;

public class FileDto {

	private int seq; //파일번호
	private String origin_fname;//원본파일명
	private String stored_fname;//저장파일명
	private int file_size;//파일 사이즈
	private Date f_regdate;//파일업로드 날짜
	private String f_delflag;//파일삭제여부
	public FileDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FileDto(int seq, String origin_fname, String stored_fname, int file_size, Date f_regdate, String f_delflag) {
		super();
		this.seq = seq;
		this.origin_fname = origin_fname;
		this.stored_fname = stored_fname;
		this.file_size = file_size;
		this.f_regdate = f_regdate;
		this.f_delflag = f_delflag;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getOrigin_fname() {
		return origin_fname;
	}
	public void setOrigin_fname(String origin_fname) {
		this.origin_fname = origin_fname;
	}
	public String getStored_fname() {
		return stored_fname;
	}
	public void setStored_fname(String stored_fname) {
		this.stored_fname = stored_fname;
	}
	public int getFile_size() {
		return file_size;
	}
	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}
	public Date getF_regdate() {
		return f_regdate;
	}
	public void setF_regdate(Date f_regdate) {
		this.f_regdate = f_regdate;
	}
	public String getF_delflag() {
		return f_delflag;
	}
	public void setF_delflag(String f_delflag) {
		this.f_delflag = f_delflag;
	}
	@Override
	public String toString() {
		return "FileDto [seq=" + seq + ", origin_fname=" + origin_fname + ", stored_fname=" + stored_fname
				+ ", file_size=" + file_size + ", f_regdate=" + f_regdate + ", f_delflag=" + f_delflag + "]";
	}
	
}
