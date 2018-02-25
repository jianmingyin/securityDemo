package org.bowman.springboot.securityDemo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.bowman.springboot.securityDemo.resources.BabyResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class HelloTest extends AppTest {
	@Value("${test.hello}")
	private String hello;
	
	@Value("${test.jerry}")
	private String jerry;
	
	/************************************************************************
	 *  application-test.properties:
	 *  	test.hello = Hello testing world!
	 *  	
	 *  application.yml:
	 *  	test.hello = Hello world!
	 *  	test.jerry = I am Jerry
	 ************************************************************************/
	@Test
    public void testHello() throws Exception {
		Assert.assertEquals("Hello testing world!", hello);
    }
	
	@Test
	public void testJerry() {
		Assert.assertEquals("I am Jerry", jerry);
	}
	
	
	/************************************************************************
	 *  使用 .standaloneSetup 构建 MockMvc时，Filters 不起作用
	 ************************************************************************/
	@Autowired
	private BabyResource babyResource;
	private MockMvc mvc;
	
	@Before
	public void setup(){
		mvc = MockMvcBuilders.standaloneSetup(babyResource).build();
	}
	
	@Test
	public void testMockMvcHello() throws Exception{
		mvc.perform(get("/baby").param("name", "jerry").accept(MediaType.APPLICATION_JSON)).andExpect(status().is(200));
	}
	
}
