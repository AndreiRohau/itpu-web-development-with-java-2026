package com.arohau.webmvc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "login", nullable = false)
	private String login;
	@Column(name = "password", nullable = false)
	private String password;
}

