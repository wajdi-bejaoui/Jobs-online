package com.example.project.configurations;

import com.example.project.service.CustomSuccessHandler;
import com.example.project.service.CustomUserDetailsService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
@Autowired
	private CustomSuccessHandler customSuccessHandler;
@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private JwtAuthentificationFilter jwtAuthFilter;
	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;

	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

		http.csrf(c -> c.disable())

		.authorizeHttpRequests(request -> request.requestMatchers("/freelancer-page")
				.hasAuthority("freelancer").requestMatchers("/user-page").hasAuthority("client").requestMatchers("/admin-page","/css/**").hasAuthority("admin")
				.requestMatchers("/listWorkSample", "/css/**").permitAll()
				.requestMatchers("/worktest", "/css/**").permitAll()
				.requestMatchers("//buywork", "/css/**").permitAll()
				.requestMatchers("/registration", "/css/**").permitAll()
				.requestMatchers("/profilclient", "/css/**","/js/**","/img/**").permitAll()
				.requestMatchers("/donnee","/css/**").permitAll()
				.requestMatchers("ajoutwork","/css/**").permitAll()
				.requestMatchers("/updateprofilclientt","/css/**").permitAll()
				.requestMatchers("/updateprofilfreelancer").permitAll()
				.requestMatchers("donneeClient" ,"/css/**","/js/**","/img/**").permitAll()
				.requestMatchers("donneefreelancer","/css/*").permitAll()
				.requestMatchers("updateuser","/css/*").permitAll()
				.requestMatchers("saveuser","/css/*").permitAll()
				.requestMatchers("/profilec","/js/**","/css/**").permitAll()
				.requestMatchers("/ajoutprojet","/js/**","/css/**").permitAll()
				.requestMatchers("/quiz", "/css/**","/js/**").permitAll()
				.requestMatchers("listefreelancer").permitAll()
				.requestMatchers("forgot-password").permitAll()
				.requestMatchers("reset-password").permitAll()
				.requestMatchers("/editclient", "/csss/**", "/js/**", "/img/**").permitAll()
				.requestMatchers("/", "/csss/**", "/js/**", "/img/**").permitAll()
				.requestMatchers("/about", "/csss/**", "/js/**", "/img/**").permitAll()
				.requestMatchers("/category", "/csss/**", "/js/**", "/img/**").permitAll()
				.requestMatchers("/blog", "/csss/**", "/js/**", "/img/**").permitAll()
				.requestMatchers("/contact", "/csss/**", "/js/**", "/img/**").permitAll()
				.anyRequest().authenticated())


				// configure session management
				// like this spring we'll create a new session for every request
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(customAuthenticationProvider)//authenticationProvider original
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // because i want to execute this filter before UsernamePasswordAuthenticationFilter

		.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login")
				.successHandler(customSuccessHandler).permitAll())

		.logout(form -> form.invalidateHttpSession(true).clearAuthentication(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessHandler(new CustomLogoutSuccessHandler())
				/*.logoutSuccessUrl("/login?logout")*/
				.permitAll());

		return http.build();

	}


	@Autowired
	public void configure (AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider); // added
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public AuthenticationProvider authenticationProvider() { // added
		return new CustomAuthenticationProvider();
	}

}
