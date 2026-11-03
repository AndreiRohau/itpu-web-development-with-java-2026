package com.arohau.webmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.processing.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private Long userId;
//	@Pattern(regexp="^[A-Z][a-z]{4,10}$", message="Name must be alpha with no spaces and first capital. 3-10 letters")
	private String name;
	private String login;
	private String password1;
	private String password2;
}

