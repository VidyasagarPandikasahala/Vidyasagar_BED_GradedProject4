package com.greatlearning.employeemanagement.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatlearning.employeemanagement.service.DomainUserDetailsService;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private final DomainUserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder encoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	        //configure users
	        authenticationManagerBuilder
	                .userDetailsService(this.userDetailsService)
	                .passwordEncoder(encoder());
	    }

	/**kiran and vinay has both get and post mapping aunthorization
	vinay has put delete in additionto above
	**/
	@Override
	protected void configure (HttpSecurity httpSecurity)throws Exception{
		
		httpSecurity.cors().disable();
		httpSecurity.csrf().disable();
		httpSecurity.authorizeRequests()
					.antMatchers(HttpMethod.GET,"/**")
					.hasAnyRole("USER","ADMIN")
					.and()
					.authorizeRequests()
					.antMatchers(HttpMethod.POST,"/**")
					.hasRole("ADMIN")			
					.antMatchers(HttpMethod.PUT,"/**")
					.hasRole("ADMIN")		
					.antMatchers(HttpMethod.DELETE,"/**")
					.hasRole("ADMIN")
					.anyRequest()
					.authenticated()
					.and()
					.formLogin()
					.and()
					.httpBasic();
	}
}
