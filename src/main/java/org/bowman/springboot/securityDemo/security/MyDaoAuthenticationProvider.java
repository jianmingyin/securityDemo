package org.bowman.springboot.securityDemo.security;

import org.bowman.springboot.securityDemo.exceptions.MyBadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyDaoAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		if (!userDetails.getPassword().equals(authentication.getCredentials())) {
			throw new MyBadCredentialsException("Invalid username or password");
		}
		
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		log.info("Get username = " + authentication.getName());
		log.info("Get password = " + authentication.getCredentials());
		
		MyUserDetails user = new MyUserDetails();
		return user;
	}
	
}
