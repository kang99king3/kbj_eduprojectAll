package com.hk.test.main;

import java.util.List;
import java.util.Scanner;

import com.hk.test.daos.TestHRDao;
import com.hk.test.dtos.TestHRDto;

public class TestHRMain {

	public static void main(String[] args) {
		//System.in: 키보드로 입력받기
		Scanner scan=new Scanner(System.in);
		while(true) {
			int num=scan.nextInt();//1,2,3,4,5,
			if(num==1) {
				System.out.println("종료~~");
				break;
			}else if(num==2) {
				continue;
			}
			System.out.println("입력값출력:"+num);
			String s=scan.next();//"aaaa bbb"---> "aaaa"
			System.out.println("입력값출력:"+s);
			
		}
		
		
		
//		TestHRDao dao=new TestHRDao();
//		System.out.println("사원전체조회하기");
//		List<TestHRDto> list= dao.getHRList();
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}
//		
//		System.out.println("해당부서의 사원정보조회하기");
//		List<TestHRDto> dlist=dao.getDeptEmpList(50);
//		for (int i = 0; i < dlist.size(); i++) {
//			System.out.println(dlist.get(i));
//		}
		
	}

}








