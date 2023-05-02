package com.peterss7.prs.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Vendor")
public class Vendor {

	@Id
	@Column(name="Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="Code")
	private String code;
	
	@Column(name = "Name")
	private String name;
	@Column(name="Address")
	private String address;
	@Column(name="City")
	private String city;
	@Column(name="State")
	private String state;
	@Column(name="Zip")
	private String zip;
	@Column(name="Phone")
	private String phone;
	@Column(name="Email")
	private String email;
	
	
	
	
	public Vendor(int id, String code, String name, String address, String city, String state,String zip, String phone, String email) {
		
		this.id = id;
		this.code = code;
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}
	
	public Vendor() {
		
	}
	
	@Override
	public String toString() {
		return "Vendor [id=" + id + ", code=" + code + ", name=" + name + ", address=" + address + ", city=" + city + ", state=" + state
				+ ", zip= " + zip + ", phone=" + phone + ", email=" + email + "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getZip() {
		return zip;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	
	
	
	
}
