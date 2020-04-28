package com.hk.test.dtos;

import java.util.Date;

public class TestHRDto {

	//필요한 컬럼 값에 대한 맴버필드 정의
	private int employee_id;
	private String last_name;
	private String email;
	private String phone_number;
	private Date hire_date;
	private int salary;
	private int department_id;
	
	//default 생성자
	public TestHRDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//생성자 오버로딩 
	public TestHRDto(int employee_id, String last_name, String email, String phone_number, Date hire_date, int salary,
			int department_id) {
		super();
		this.employee_id = employee_id;
		this.last_name = last_name;
		this.email = email;
		this.phone_number = phone_number;
		this.hire_date = hire_date;
		this.salary = salary;
		this.department_id = department_id;
	}

	public TestHRDto(String email, String phone_number, int salary) {
		super();
		this.email = email;
		this.phone_number = phone_number;
		this.salary = salary;
	}
	//getter, setter메서드 작성
	public int getEmployee_id() {
		return employee_id;			
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public Date getHire_date() {
		return hire_date;
	}

	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	
	//toString() 오버라이딩
	@Override
	public String toString() {
		return "TestHRDto [employee_id=" + employee_id + ", last_name=" + last_name + ", email=" + email
				+ ", phone_number=" + phone_number + ", hire_date=" + hire_date + ", salary=" + salary
				+ ", department_id=" + department_id + "]";
	}
	
}





