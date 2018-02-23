package org.bowman.springboot.securityDemo.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {
	
	public static <T> ResponseEntity<T> ok(T body) {
		return ResponseEntity.ok(body);
	}
	
	public static ResponseEntity<?> message(Object message, HttpStatus status) {
		Map<String, Object> body = new HashMap<>();
		body.put("message", message);
		
		return new ResponseEntity<>(body, status);
	}
	
	public static ResponseEntity<?> seccess(){
		return message("SUCCESS", HttpStatus.OK);
	}
	
	public static void response
			(HttpServletResponse response, HttpStatus status, String message) throws IOException {
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		Map<String, Object> body = new HashMap<>();
		body.put("message", message);
		String bodyStr = JsonUtils.toJson(body);
		
		response.setStatus(status.value());
		response.getWriter().write(bodyStr);
		response.getWriter().flush();
	}
}
