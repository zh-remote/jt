package com.jt.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
 
public class TestObjectMapper {

	@Test
	public void javaToJson() throws IOException {
		User user=new User();
		user.setId(11);
		user.setName("tomcat");
		ObjectMapper objectMapper=new ObjectMapper();
		String userString = objectMapper.writeValueAsString(user);
		System.out.println(userString);
	    User uu = objectMapper .readValue(userString, User.class);
	    System.out.println(uu);
	}
	
	@Test
	public void javaToJsonList() throws IOException {
		User user1=new User();
		user1.setId(33);
		user1.setName("lishi");
		User user2=new User();
		user2.setId(11);
		user2.setName("tomcat");
		List<User> users=new ArrayList<User>();
		users.add(user1);
		users.add(user2);
		ObjectMapper objectMapper=new ObjectMapper();
		String usersJSON = objectMapper.writeValueAsString(users);
		System.out.println(usersJSON);
		
		 List<User> usersList = objectMapper.readValue(usersJSON, ArrayList.class);
		 System.out.println();
		System.out.println(usersList );
		
		User[] usersArray = objectMapper.readValue(usersJSON, User[].class);
		List<User> users2=Arrays.asList(usersArray);
		
		System.out.println(users2);
	}
	
}


class User {
	Integer id;
	String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	
}