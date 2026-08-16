package com.example.backend.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
	private Long id;
	private String email;
	private String passwordHash;
	private String role;
	private String name;
	private LocalDateTime createdAt;
}
