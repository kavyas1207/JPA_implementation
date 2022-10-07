package com.sample.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="buyer")

public class Buyer {
	
    private String name;
	@Id
	
	private String contactno;
	
	private String email;
	
	private String address;
	
	private String gender;
public Buyer() {
	
}
public Buyer(String name, String contactno, String email, String address, String gender) {
	super();
	this.name = name;
	this.contactno = contactno;
	this.email = email;
	this.address = address;
	this.gender = gender;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getContactno() {
	return contactno;
}
public void setContactno(String contactno) {
	this.contactno = contactno;
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
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
@Override
public String toString() {
	return "Buyer [name=" + name + ", contactno=" + contactno + ", email=" + email + ", address=" + address
			+ ", gender=" + gender + "]";
}

}
