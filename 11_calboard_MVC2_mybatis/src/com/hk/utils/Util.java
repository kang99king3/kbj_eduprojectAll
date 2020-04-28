package com.hk.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import com.hk.dtos.CalDto;

public class Util {

	private String toDates;
	
	//mDate는 12자리 문자열이며, 이 값을 날짜형식으로 만들어준 뒤
	//--->  Date타입으로 변환한다.
	//날짜형식: yyyy-MM-dd hh:mm:ss
	public void setToDates(String mDate) {
		//mDate를 날짜형식으로 편집한다.
		String m=mDate.substring(0,4)+"-"  //year-
				+mDate.substring(4,6)+"-"  //year-month-
				+mDate.substring(6,8)+" "  //"yyyy-MM-dd "
				+mDate.substring(8,10)+":" //"yyyy-MM-dd hh:"
				+mDate.substring(10)+":00";//"yyyy-MM-dd hh:mm:ss"
		
		//문자열--> date타입으로 변환 실행
		Timestamp tm=Timestamp.valueOf(m);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy년MM월dd일 HH시mm분");
		
		this.toDates=sdf.format(tm);
	}
	
	public String getToDates() {
		return this.toDates;
	}
	
	//한자리값을 두자리로 변환하는 메서드
	public static String isTwo(String s) {
		return s.length()<2?"0"+s:s;
	}
	
	//달력의 날짜에 대한 요일을 확인해서 폰트 색을 적용하는 메서드
	public static String fontColor(int dayOfWeek, int i){
		String color="";
		// (공백수+현재날짜)%7==0 토요일
		if((dayOfWeek-1+i)%7==0){ //토요일을 확인
			color="blue";
		}else if((dayOfWeek-1+i)%7==1){ //일요일을 확인
			color="red";
		}else{ //평일
			color="black";
		}
		
		return color;
	}
	
	//달력에 해당날짜에 존재하는 일정을 구하는 기능
	//해당 달의 일정을 가지고 있는 list와 그 날의 일일을 받아서 확인
	public static String getCalView(List<CalDto> list,int i) {
		//list에 저장된 mdate는 "202004011230"
		String d=isTwo(i+"");// 1일 ---> "01" 변환    --> "01" != "1"이런 상황때문에
		String titleList="";//달력에서 일정을 출력해줄 문자열
		                    //"<p>title</p><p>title</p><p>title</p>"
		for (CalDto calDto : list) {
			if(calDto.getMdate().substring(6, 8).equals(d)) {
				titleList+="<p class='clist'>"
						 +(calDto.getTitle().length()>6?
								 calDto.getTitle().substring(0, 6)+"..":calDto.getTitle())  
						 +"</p>";
			}
		}
		return titleList;
	}
}



















