package org.bowman.springboot.securityDemo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.bowman.springboot.securityDemo.exceptions.MyBadCredentialsException;
import org.bowman.springboot.securityDemo.utils.ResponseUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("-----------------------configure--------------------------");
		http
			.addFilterBefore(new AuthenticationErrorHander(), BasicAuthenticationFilter.class)
			.addFilterAfter(new AccessDeniedExceptionHandler(), ExceptionTranslationFilter.class)
			.csrf().disable()
			.authenticationProvider(new MyDaoAuthenticationProvider())
	        .authorizeRequests()
	        .antMatchers("/login").permitAll()
	        .anyRequest().authenticated()
        .and()
        		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        .and()
        		.logout().logoutUrl("/logout")
        		.logoutSuccessHandler(new MySeccessHandler())
        		.clearAuthentication(true)
        		.invalidateHttpSession(true)	
        .and()
        		.httpBasic();
	}
	
	private static class MySeccessHandler implements LogoutSuccessHandler {

		@Override
		public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
				throws IOException, ServletException {
			ResponseUtil.response(response, HttpStatus.OK, "Logout Successfully");
		}

	}
	
	private static class AuthenticationErrorHander extends MyBaseErrorHandler{
		private static final long serialVersionUID = 1L;

		@Override
		protected void handlerException(Exception e, HttpServletResponseWrapper response) throws IOException {
			if (e instanceof MyBadCredentialsException) {
				ResponseUtil.response(response, HttpStatus.UNAUTHORIZED, e.getMessage());
			}
		}

	}
	
	private static class AccessDeniedExceptionHandler extends MyBaseErrorHandler{
		private static final long serialVersionUID = 1L;

		@Override
		protected void handlerException(Exception e, HttpServletResponseWrapper response) throws IOException {
			if (e instanceof AccessDeniedException) {
				ResponseUtil.response(response, HttpStatus.FORBIDDEN, e.getMessage());
			}
		}
	}
}
