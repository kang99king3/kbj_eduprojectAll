package com.hk.dtos;

import java.io.Serializable;
import java.util.Date;

//직렬화: 데이터를 전송할때 직렬화시켜서 데이터를 이동시키는 방법으로 안정적으로 보낼 수 있게 함
//Serializable 인터페이스를 implements해서 직렬화시켜 전송하라고 알려줌
public class LoginDto implements Serializable {

	private static final long serialVersionUID = 7655342860359641042L;
	private String id;
	private String name;
	private String password;
	private String address;
	private String phone;
	private String email;
	private String enabled;
	private String role;
	private Date regdate;
	
	public LoginDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public LoginDto(String id, String name, String password, String address, String phone, String email, String enabled,
			String role, Date regdate) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.enabled = enabled;
		this.role = role;
		this.regdate = regdate;
	}
	
	public LoginDto(String id, String name, String password, String address, String phone, String email) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
	
	public LoginDto(String id, String address, String phone, String email) {
		super();
		this.id = id;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "LoginDto [id=" + id + ", name=" + name + ", password=" + password + ", address=" + address + ", phone="
				+ phone + ", email=" + email + ", enabled=" + enabled + ", role=" + role + ", regdate=" + regdate + "]";
	}
	
	
}




