package com.arohau.webmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private Long userId;
	@NotBlank(message = "Login is required")
	@Size(min = 3, max = 30, message = "Login must be between 3 and 30 characters")
	private String name;
	private String login;
	private String password1;
	private String password2;
}

