package com.gotop.util;

import java.util.Date;

public class Student {   
	private int id;   
	private String name;  
	private String email;  
	private String address;   
	private Date birthday;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}   
}
																																						// public
																																						// String
																																						// toString()
																																						// {
																																						// return
																																						// this.name
																																						// +
																																						// "#"
																																						// +
																																						// this.id
																																						// +
																																						// "#"
																																						// +
																																						// this.address
																																						// +
																																						// "#"
																																						// +
																																						// this.birthday
																																						// +
																																						// "#"
																																						// +
																																						// this.email;
																																						// }}
