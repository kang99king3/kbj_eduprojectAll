package com.hk.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Test.do")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/xml;charset=utf-8");
		
		String tmnNm=request.getParameter("tmnNm");
		String encoding=URLEncoder.encode(tmnNm, "UTF-8"); 
//		String encoding=new String(tmnNm.getBytes(),"utf-8");
		System.out.println(encoding);
		String serviceKey="VtBzpYcBps6iwerhxo4%2B7BxXrWTKOanX4K8B2HV6mpGZmCXet%2FjgJFkpD%2FP%2F%2Fq0NdZTvd4udWhlAms8FaEaTUg%3D%3D";
		String urlPath="http://openapi.tago.go.kr/openapi/service/ExpBusArrInfoService/getExpBusTmnList?ServiceKey="
						+serviceKey
						+"&tmnNm="+encoding;

			try {
				//특정 사이트를 url을 이용하여 객체 생성
				URL url=new URL(urlPath);
				
				//IO 스트림을 이용하여 파일 읽어 들이기 
				InputStreamReader in=new InputStreamReader(url.openStream());
				BufferedReader br=new BufferedReader(in);
				String s="";
				
				//읽어들인 결과 문자열로 저장하기
				String p="";
				while((s=br.readLine())!=null){
					p+=s+"\n";
				}
				
				PrintWriter pr=response.getWriter();
				pr.print(p);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
	}

}
