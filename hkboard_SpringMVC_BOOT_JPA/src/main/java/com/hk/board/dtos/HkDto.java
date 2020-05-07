package com.hk.board.dtos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Builder;
import lombok.Data;

@Data
@Entity(name="hkboard") //@Entity를 설정한 클래스는 jpa가 관리하는 클래스
@SequenceGenerator(name="HKBOARD_SEQ_GENERATOR",
sequenceName="HKBOARD_SEQ",
initialValue=1,
allocationSize=1) 
public class HkDto {
	//기본키 설정(@Id)
	//기본키의 값이 자동생성되도록 설정(@Generated..)
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
    generator="HKBOARD_SEQ_GENERATOR")  
	private int seq;
	
	private String id;
	
	private String title;
	
	private String content;
	
	@Column(nullable=false,columnDefinition="date default sysdate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date regdate = new Date();
	
	
	
	
}
