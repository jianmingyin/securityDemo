package org.bowman.springboot.securityDemo.resources;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserResource {
//	private static final String PREFIX = "Basic ";
	
	@RequestMapping(value="/login")
	public ResponseEntity<?> login(){
		log.info("Authentication and login secceed");
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Authentication and login secceed");
		return ResponseEntity.ok(body);
	}
	
	@RequestMapping(value="/logout")
	public ResponseEntity<?> logout(HttpServletRequest request){
		log.info("Logout logoutSeccess");
		Map<String, Object> body = new HashMap<>();
		body.put("message", "Logout logoutSeccess");
		return ResponseEntity.ok(body);
	}
}
