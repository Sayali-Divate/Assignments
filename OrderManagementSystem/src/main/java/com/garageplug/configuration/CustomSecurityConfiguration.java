package com.garageplug.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class CustomSecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(
				
				auth -> auth.requestMatchers("garageplug/user/signup", "garageplug/user/login").permitAll()
				.requestMatchers("garageplug/order").authenticated()
				
				).csrf().disable().httpBasic();
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	        final List<GlobalAuthenticationConfigurerAdapter> configurers = new ArrayList<>();
	        configurers.add(new GlobalAuthenticationConfigurerAdapter() {
	                    @Override
	                    public void configure(AuthenticationManagerBuilder auth) throws Exception {
	                        auth.getDefaultUserDetailsService();
	                    }
	                }
	        );
	        return authConfig.getAuthenticationManager();
	    }
}
