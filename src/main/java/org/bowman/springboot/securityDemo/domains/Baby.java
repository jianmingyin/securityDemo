package org.bowman.springboot.securityDemo.domains;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Baby{
	private String name;
	@JsonFormat(pattern = "MM/dd/yyyy")
	private Timestamp birthday;
	private String father;
	private String mother;
	
	public static Baby jerry() {
		//yyyy-mm-dd hh:mm:ss
		return new Baby("Jerry", Timestamp.valueOf("2017-04-30 00:00:00"), "Bowman", "Kelly");
	}
}
