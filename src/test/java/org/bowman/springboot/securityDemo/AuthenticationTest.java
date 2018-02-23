package org.bowman.springboot.securityDemo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

public class AuthenticationTest extends AppTest {
	@Value("${test.hello}")
	private String hello;
	
	@Value("${test.jerry}")
	private String jerry;
	
	@Test
    public void helloTest() throws Exception {
        System.out.println("test message: " + hello);
    }
	
	@Test
	public void testAuthenticationSuccess() {
		System.out.println("test message: " + jerry);
		
	}
}
