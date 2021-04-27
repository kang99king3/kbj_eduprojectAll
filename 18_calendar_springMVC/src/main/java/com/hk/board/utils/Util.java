package com.hk.board.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import com.hk.board.dtos.CalDto;

public class Util {

	private String toDates;
	
	public String getToDates() {
		return toDates;
	}

	//날짜를 나타내는 문자열을 Date타입으로 변환하고 원하는 패턴으로 표현해주는 메서드
	public void setToDates(String mDate) {
		//날짜형식: yyyy-MM-dd hh:mm:ss --> mDate를 날짜형식으로 만들어주자
		String m=mDate.substring(0, 4)+"-"    // 2020 12101502
				+mDate.substring(4, 6)+"-"
				+mDate.substring(6,8)+" "
				+mDate.substring(8, 10)+":"
				+mDate.substring(10)+":00";
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy년MM월dd일 HH시mm분");
		Timestamp tm=Timestamp.valueOf(m);//문자열을 Date타입으로 변환
		
		this.toDates = sdf.format(tm);
	}
	
	//한자리 값을 두자리로 변환해주는 메서드
	public static String isTwo(String msg) {
		return msg.length()<2?"0"+msg:msg;//"2"--> "02"
	}
	
	//토요일과 일요일을 확인해서 "blue" 또는 "red" 문자열을 반환하는 메서드
	public static String fontColor(int date, int dayOfweek) {
		String color="";
		// (dayOfweek-1+date)%7==0 토요일
		if((dayOfweek-1+date)%7==0) {
			color="blue;font-weight:bold;";
		}else if((dayOfweek-1+date)%7==1) {//일요일
			color="red;font-weight:bold;";
		}else {
			color="black";
		}
		return color; 
	}
	
	//"4"---> 4 : Integer.parseInt("4")
	// 4 ---> "4" : String.valueOf(4) 또는 4+""
	public static String getCalViewList(int i,List<CalDto> clist) {
		String d=isTwo(i+"");//5 ---> "05"
		String calList="";//해당일정들의 제목을 저장할 변수"<p>title</p><p>title</p>"
		for (CalDto calDto:clist) {
			if(calDto.getMdate().substring(6, 8).equals(d)) {
				calList+="<p>"
						+(calDto.getTitle().length()>7?
						calDto.getTitle().substring(0, 7)+"..":calDto.getTitle())
						+"</p>";
			}
		}
		return calList;
	}
	


}
