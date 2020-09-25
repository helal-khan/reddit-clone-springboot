package com.redditclone.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Long userId;
	
	@NotBlank(message = "Username is required")
	public String username;
	
	@NotBlank(message = "Password is required")
	public String password;
	
	@Email
	@NotBlank(message = "Email is required")
	public String email;
	
	private Instant createdDate;
	
	private boolean enabled;
}