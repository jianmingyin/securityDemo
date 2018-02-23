package org.bowman.springboot.securityDemo.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	
	public static String toJson(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String toJson(String key, Object value) {
		Map<String, Object> m = new HashMap<>();
		m.put(key, value);
		return toJson(m); 
	}
	
	public static String fromJson(String json, String field) {
		try {
			JsonNode node = new ObjectMapper().readTree(json);
			node = node.get(field);
			return node.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
