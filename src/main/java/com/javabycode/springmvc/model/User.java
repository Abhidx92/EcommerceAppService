package com.javabycode.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
 
int userId;
 String username;
 String password;
 String gender;
 String address;
 String age;
 String emailId;
 
@Id
@Column(name = "userId")
public int getUserId() {
	return userId;
}

public void setUserId(int userId) {
	this.userId = userId;
}

@Column(name = "username")
public String getUsername() {
	return username;
}


public void setUsername(String username) {
	this.username = username;
}
@Column(name = "password")
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
@Column(name = "gender")
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
@Column(name = "address")
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
@Column(name = "age")
public String getAge() {
	return age;
}
public void setAge(String age) {
	this.age = age;
}
@Column(name = "MailId")
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}

 
}
