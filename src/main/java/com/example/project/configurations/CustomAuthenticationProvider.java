package com.example.project.configurations;

import com.example.project.model.User;
import com.example.project.repositories.UserRepository;
import com.example.project.service.CustomUserDetailsService;
import com.example.project.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import static com.example.project.configurations.SecurityConfig.passwordEncoder;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        User user = userRepository.findByEmail(username);
        // Implement your custom logic for password validation
        if (!passwordEncoder().matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        // Generate JWT upon successful authentication
        HashMap<String, Object> data = new HashMap<>();
        data.put("id", user.getId());
        data.put("role", user.getRole());
        data.put("addresse", user.getAddresse());
        data.put("fullname", user.getFullname());
        data.put("phone", user.getPhone());
        data.put("country", user.getCountry());
        String jwtToken = jwtService.generateToken(data,userDetails);
        JwtLocalStorage.setJwt(jwtToken);
        System.out.println("jwt = " + jwtToken);
        return new UsernamePasswordAuthenticationToken(username, jwtToken, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
