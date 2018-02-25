package org.bowman.springboot.securityDemo.resources;

import org.bowman.springboot.securityDemo.domains.Baby;
import org.bowman.springboot.securityDemo.exceptions.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/baby")
public class BabyResource {
	
	@RequestMapping
	public ResponseEntity<Baby> getBadyByName(@RequestParam(required = false) String name){
		log.info("Get baby name = {}", name);
		if(null == name) {
			throw new BadRequestException("name should not be NULL");
		}
		return ResponseEntity.ok(Baby.jerry());
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Baby> removeBadyByName(@RequestParam(required = false) String name){
		log.info("Remove baby name = {}", name);
		if(null == name) {
			throw new BadRequestException("name should not be NULL");
		}
		return ResponseEntity.ok(Baby.jerry());
	}
}
