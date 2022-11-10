package com.greatlearning.employeemanagement.util;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatlearning.employeemanagement.dao.repository.EmployeeRepository;
import com.greatlearning.employeemanagement.dao.repository.UserRepository;
import com.greatlearning.employeemanagement.entity.Role;
import com.greatlearning.employeemanagement.entity.User;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BootStrapAppData {
	
	private final EmployeeRepository employeeRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	
	
	@EventListener(ApplicationReadyEvent.class)
	public void  insertUser(ApplicationReadyEvent Event) {
		
		User vinay = new User();
		vinay.setUsername("Vinay");
		vinay.setPassword(this.passwordEncoder.encode("welcome"));
		vinay.setEmailAddress("vinay@gmail.com");
		
		//kiran has user authorization
		Role kiranRole = new Role();
		kiranRole.setRoleName("USER");
		
		//vinay has admin auth
		Role vinayRole = new Role();
		vinayRole.setRoleName("ADMIN");
		
		
		vinayRole.setUser(vinay);
		vinay.addRole(vinayRole);
		
		
		User kiran = new User();
		kiran.setUsername("kiran");
		kiran.setPassword(this.passwordEncoder.encode("welcome"));
		kiran.setEmailAddress("kiran@gmail.com");
		kiran.addRole(kiranRole);
	
		this.userRepository.save(kiran);
		this.userRepository.save(vinay); 
		
		
	}
	
	
}
