package com.example.project.service;

import com.example.project.dto.UserDto;
import com.example.project.model.User;
import com.example.project.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
	
@Autowired
	private PasswordEncoder passwordEncoder;

@Autowired
	private UserRepository userRepository;



	@Override
	public User save(UserDto userDto) {
		User user = new User(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()) , userDto.getRole(), userDto.getFullname() , userDto.getAddresse(), userDto.getPhone(), userDto.getCountry());
		return userRepository.save(user);
	}
}
