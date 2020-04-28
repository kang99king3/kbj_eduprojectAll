package com.hk.test.main;

import java.util.List;
import java.util.Scanner;

import com.hk.test.daos.TestDao;
import com.hk.test.dtos.TestDto;

public class TestMain {

	public static void main(String[] args) {
		TestDao dao=new TestDao();
		
		System.out.println("****************");
		System.out.println("사원관리시스템입니다.");
		System.out.println("****************");
		System.out.println("< 1.전체조회 2.사원조회 3.사원추가 4.사원수정 5.사원삭제 6.시스템 종료>");
		Scanner scan=new Scanner(System.in);
		while(true) {
			int num=scan.nextInt();
			if(num==1) {
				System.out.println("사원전체목록조회하기");
				List<TestDto> list= dao.getAllList();//모든 사원목록을 구해서 list에 저장
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i));
				}
			}else if(num==2){
				System.out.println("사원한명에 대한 정보조회하기");
				System.out.println("사원번호를 입력하세요");
				int empno=scan.nextInt();//입력받은 내용 int형으로 받아오기
				if(empno==000) {
					System.out.println("메뉴의 번호를 입력하세요");
					continue;
				}
				TestDto dto=dao.getEmplyee(empno);//입력받은 값 전달 및 조회
				System.out.println(dto);
			}else if(num==3) {
				System.out.println("사원정보를 입력하세요(ㅇㅇ,ㅇㅇ,ㅇㅇ..)");
				System.out.println("-사원번호,사원이름,직무,관리자번호,급여,부서번호-");
				String s=scan.next();
				String [] ss=s.split(",");
				boolean isS=dao.addEmployee(
						new TestDto(Integer.parseInt(ss[0]),ss[1],ss[2]
									,Integer.parseInt(ss[3])
									,null,Integer.parseInt(ss[4]),0
									,Integer.parseInt(ss[5])));
				System.out.println(isS?"사원정보추가성공":"추가실패");
				
			}else if(num==4) {
				System.out.println("수정할 사원의 정보를 입력하세요");
				System.out.println("수정할 급여를 입력하세요");
				int sal=scan.nextInt();
				System.out.println("수정할 커미션을 입력하세요");
				int comm=scan.nextInt();
				System.out.println("수정할 사원의 번호를 입력하세요");
				int empno=scan.nextInt();
				boolean isS=dao.updateEmployee(sal, comm, empno);
				System.out.println(isS?"사원정보수정성공":"수정실패");
				
			}else if(num==5) {
				System.out.println("삭제할 사원의 사원번호를 입력하세요");
				int empno=scan.nextInt();
				boolean isS=dao.delEmployee(empno);
				System.out.println(isS?"사원삭제성공":"사원삭제실패");
			}else if(num==6) {
				System.out.println("사원관리시스템을 종료합니다.~~");
				System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");
				System.out.println("□□□■■■■■□□□□■□□□■□□□■■■■■□□□□□□");
				System.out.println("□□□■□□□□■□□□□■□■□□□□■□□□□□□□□□");
				System.out.println("□□□■■■■■□□□□□□■□□□□□■■■■■□□□□□");
				System.out.println("□□□■□□□□■□□□□□■□□□□□■□□□□□□□□□");
				System.out.println("□□□■■■■■□□□□□□■□□□□□■■■■■□□□□□");
				System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");
				break;
			}else {
				System.out.println("메뉴의 번호를 정확하게 입력하세요");
			}
		
		}
		
		
		
		
		
	}

}










