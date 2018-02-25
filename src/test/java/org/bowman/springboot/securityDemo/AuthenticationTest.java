package org.bowman.springboot.securityDemo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import javax.servlet.Filter;

import org.bowman.springboot.securityDemo.security.WebSecurityConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


public class AuthenticationTest extends AppTest {
	@Autowired
	private WebApplicationContext context;
	@Autowired
	private Filter springSecurityFilterChain;
	
	private MockMvc mvc;
	
	/**
	 * 完全启动project以及添加 filter chain, filter chain的规则定义在  {@link WebSecurityConfig}
	 */
	@Before
	public void setup(){
		mvc = MockMvcBuilders.webAppContextSetup(context).addFilters(springSecurityFilterChain).build();
	}
	
	@Test
	public void testAccessForbidden() throws Exception{
		mvc.perform(get("/baby").param("name", "jerry").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().is(HttpStatus.FORBIDDEN.value()))
			.andExpect(jsonPath("$.message").value("Access is denied"));	
	}
	
	@Test
	public void testInvalidPassword() throws Exception{
		mvc.perform(get("/login").accept(MediaType.APPLICATION_JSON).with(httpBasic("jyin027", "!QAZ2WSX1")))
			.andExpect(status().is(HttpStatus.UNAUTHORIZED.value()))
			.andExpect(jsonPath("$.message").value("Invalid username or password"));
	}
	
	@Test
	public void testAuthenticationSucceed() throws Exception{
		mvc.perform(get("/login").accept(MediaType.APPLICATION_JSON).with(httpBasic("jyin027", "!QAZ2WSX")))
			.andExpect(status().is(HttpStatus.OK.value()))
			.andExpect(jsonPath("$.message").value("Authentication and login secceed"));
	}
}
