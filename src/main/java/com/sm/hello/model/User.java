package com.sm.hello.model;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = 2647284108982437332L;
	
	private String username;
	private Integer age;
	private String phoneNumber;
	private String email;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", age=" + age + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ "]";
	}
}
