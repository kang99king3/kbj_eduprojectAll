package com.hk.utils;

public class Util {

	//Action Tag: <usebean>: 값을 담고 꺼내는 객체를 사용할때 쓰임
	//        ---> DTO모양으로 만들어야함
	
	//화면에 출력해줘야할 값---> "&nbsp;&nbsp;<img src/>"
	private String arrowNbsp;

	public void setArrowNbsp(String depth) {
		String nbsp="";
		int depthInt=Integer.parseInt(depth);
		for (int i = 0; i < depthInt; i++) {
			nbsp+="&nbsp;&nbsp;&nbsp;&nbsp;";
		}
		this.arrowNbsp=depthInt>0?nbsp+
				"<img style='width:12px;height:12px;' "
				+ "src='img/arrow2.png'alt='답글'/>":"";
	}
	
	public String getArrowNbsp() {
		return arrowNbsp;
	}

	
	
}







