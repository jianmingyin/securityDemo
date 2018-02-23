package org.bowman.springboot.securityDemo.utils;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class StringUtils {
	
	public static boolean blank(String src) {
		return null == src || src.trim().isEmpty();
	}
	
	public static String trim(String src, String trimed) {
		if(!blank(src) && !blank(src)) {
			
			if (src.startsWith(trimed)) {
				src = src.substring(trimed.length());
			}
			
			if (src.endsWith(trimed)) {
				src = src.substring(0, src.length() - trimed.length());
			}
		}
		return src;
	}
	
	public static <T> String connect2String(List<T> list, String delimiter) {
		if (null == list) {
			return null;
		} else if (list.isEmpty()) {
			return "";
		} else {
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<list.size(); i++) {
				if (i != 0) {
					sb.append(",");
				}
				sb.append(list.get(i));
			}
			return sb.toString();
		}
	}
	
	public static List<String> split(String str, String delimiter) {
		return split(str, delimiter, 0);
	}
	
	public static List<String> split(String str, String delimiter, int n) {
		if (null == str) {
			return null;
		}
		
		List<String> list = new ArrayList<>();
		if (null == delimiter || delimiter.length() == 0) {
			list.add(str);
			return list;
		}
		
		String temp = str;
		int s = -1;
		int hit = 0;
		
		while((s = temp.indexOf(delimiter)) != -1 && (0 == n || hit++ < n)) {
			list.add(temp.substring(0, s));
			temp = temp.substring(s + delimiter.length());
		}
		list.add(temp);
		return list;
	}

	public static String base64Decode(String phase) {
		Charset charset = Charset.forName("UTF-8");
		return new String(Base64.getDecoder().decode(phase.getBytes(charset)), charset);
	}

	public static String base64Encode(String phase) {
		Charset charset = Charset.forName("UTF-8");
		return new String(Base64.getEncoder().encode(phase.getBytes(charset)), charset);
	}
	
//	public static void main(String[] args) {
//		String  s = "\"abc\"";
//		System.out.println(s);
//		System.out.println(trim(s, "\""));
//	}
}
