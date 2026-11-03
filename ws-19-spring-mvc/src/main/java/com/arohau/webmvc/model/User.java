package com.arohau.webmvc.model;

import lombok.Data;

@Data
public class User {
	private Long userId;
	private String name;
	private String login;
	private String password1;
	private String password2;
}
