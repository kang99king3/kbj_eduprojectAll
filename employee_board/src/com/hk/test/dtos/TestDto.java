package com.hk.test.dtos;

import java.util.Date;

//DTO클래스: 데이터를 담아서 운반하는 객체 
//테이블의 하나의 row를 나타낸다.
public class TestDto {

	private int empno;//사원번호
	private String ename;//사원이름
	private String job;//직업
	private int mgr;//관리자의 사원번호
	private Date hiredate;
	private int sal;
	private int comm;
	private int deptno;//부서번호
	
	//default생성자는 생략가능하지만, 생성자오버로딩을 하면 생략 못함
	public TestDto() {
		super();
		// TODO Auto-generated constructor stub
	}
    //생성자 오버로딩: 파리미터선언
	public TestDto(int empno, String ename, String job, int mgr, Date hiredate, int sal, int comm, int deptno) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.mgr = mgr;
		this.hiredate = hiredate;
		this.sal = sal;
		this.comm = comm;
		this.deptno = deptno;
	}
	public TestDto(int empno, String ename, String job, int mgr) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.mgr = mgr;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getMgr() {
		return mgr;
	}
	public void setMgr(int mgr) {
		this.mgr = mgr;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public int getComm() {
		return comm;
	}
	public void setComm(int comm) {
		this.comm = comm;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	
	//toString()---> 최상위객체 Object에 구현된 메서드
	//TestDto에서 오버라이딩함--> 부모의 메서드를 자식이 재정의했음
	//                --> 부모의 메서드를 호출하면 자식의 메서드가 실행됨
	@Override
	public String toString() {
		return "TestDto [empno=" + empno + ", ename=" + ename + ", job=" + job + ", mgr=" + mgr + ", hiredate="
				+ hiredate + ", sal=" + sal + ", comm=" + comm + ", deptno=" + deptno + "]";
	}
	
	
}










