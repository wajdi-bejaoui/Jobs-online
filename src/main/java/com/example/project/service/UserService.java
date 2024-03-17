package com.example.project.service;


import com.example.project.dto.UserDto;
import com.example.project.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {


	User save (UserDto userDto);



}
