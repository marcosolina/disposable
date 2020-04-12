package com.marco.dummyclient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationConverter jwtConverter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * Standard spring security
		 */
		http.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().authorizeRequests()
			.antMatchers("/api/client/**").hasAnyRole("READ_CLIENT")
			.anyRequest().authenticated()
			.and()
			/*
			 * Telling that this is a resource server, so I will receive a token
			 * that needs to be checked
			 */
			.oauth2ResourceServer()
			.jwt()
			.jwtAuthenticationConverter(jwtConverter);
	}
}