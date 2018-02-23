package org.bowman.springboot.securityDemo.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.bowman.springboot.securityDemo.utils.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class WebExceptionHandler {
	@ResponseBody
	@ExceptionHandler
	ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
		log.error("System error");
		
		HttpStatus status;
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == null) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		} else {
			status = HttpStatus.valueOf(statusCode);
		}
		return new ResponseEntity<>(ex.getMessage(), status);
	}
	
	@ResponseBody
	@ExceptionHandler(BadRequestException.class)
	ResponseEntity<?> badRequestException(BadRequestException ex) {
		log.error(ex.getMessage());
		return ResponseUtil.message(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
