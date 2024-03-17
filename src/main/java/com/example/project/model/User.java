package com.example.project.model;

import jakarta.persistence.*;

@Entity


@Table(name = "Model_user" ,uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
	@Id
	@SequenceGenerator(name = "usersequence", sequenceName = "usersquence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "usersequence")

	private Long id;

	private String email;
	private String password;
	private String role;
	private String fullname;
	private String phone;
	private  String addresse;
	private String  country;

	public User() {

	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCountry() {
		return country;
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

	public User(String email, String password, String role, String fullname, String addresse, String phone, String country,long id) {
		this.id = id;
		this.email=email;
		this.password=password;
		this.role=role;
		this.fullname=fullname;
		this.addresse = addresse;
		this.phone =phone;
		this.country = country;

	}

	public User(String email, String role, String fullname, String addresse, String phone, String country,long id) {
		this.id = id;
		this.email = email;
		this.role = role;
		this.fullname = fullname;
		this.phone = phone;
		this.addresse = addresse;
		this.country = country;
	}

	public User(String email, String password, String role, String fullname, String addresse, String phone, String country) {

		this.email=email;
		this.password=password;
		this.role=role;
		this.fullname=fullname;
		this.addresse = addresse;
		this.phone =phone;
		this.country = country;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}


public void resetPwd() {

}


	public void getFullname(Object fullname) {
	}
}
