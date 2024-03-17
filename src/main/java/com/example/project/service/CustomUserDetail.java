package com.example.project.service;

import java.util.Collection;
import java.util.List;

import com.example.project.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class CustomUserDetail implements UserDetails {

	private User user;
	private String country;
	private String addresse;

	public String getCountry() {
		return user.getCountry();
	}

	public String getAddresse() {
		return user.getAddresse();
	}

	public String getPhone() {
		return user.getPhone();
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	private String phone;
	public CustomUserDetail(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(() -> user.getRole());
	}

	public String getFullname() {
		return user.getFullname();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
