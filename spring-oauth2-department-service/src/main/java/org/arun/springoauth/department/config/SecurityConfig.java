package org.arun.springoauth.department.config;

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
		
		//@PreAuthorize("hasAnyAuthority('ROLE_READ_CALLEE')")
		
		http.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().authorizeRequests()
			.antMatchers("/api/departments/**").hasAnyRole("READ_CALLEE")
			.anyRequest().authenticated()
			.and()
			.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtConverter);
	}
}