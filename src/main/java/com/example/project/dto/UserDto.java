package com.example.project.dto;


public class UserDto {
	private String email;
	private String password;
	private String role;
	private String fullname;
	private String phone;
	private  String addresse;
	private String  country;



	public String getCountry() {
		return country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddresse() {
		return addresse;
	}

	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}

	public UserDto(String email, String password, String role, String fullname ,String addresse, String phone, String country) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
		this.fullname = fullname;
		this.addresse = addresse;
		this.phone = phone;
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}






}
