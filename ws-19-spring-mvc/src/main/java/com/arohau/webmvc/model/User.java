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

	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	private String name;

	@NotBlank(message = "Login is required")
	@Size(min = 3, max = 30, message = "Login must be between 3 and 30 characters")
	private String login;

	@NotBlank(message = "Password is required")
	@Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters")
	private String password1;

	@NotBlank(message = "Please confirm your password")
	@Size(min = 8, max = 64, message = "Password confirmation must be between 8 and 64 characters")
	private String password2;
}
