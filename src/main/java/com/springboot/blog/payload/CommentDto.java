package com.springboot.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
	private long id;
//	Name should not be null or empty
	@NotEmpty(message = "Name should not be null or empty")
	private String name;
	
//	email should not be null or empty
//	email field validation
	@NotEmpty(message = "Email should not be null or empty")
	@Email
	private String email;
	
//	comment body should not be null or empty
//	comment body must be minimum 10 character
	@NotEmpty
	@Size(min = 10, message = "comment body must be minimum 10 character")
	private String body;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
