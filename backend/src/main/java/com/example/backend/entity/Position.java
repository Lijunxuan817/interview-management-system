package com.example.backend.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Position {
	private Long id;
	private Long moId;
	private String title;
	private String courseName;
	private String type;
	private String requirements;
	private Integer quota;
	private String status;
	private LocalDateTime createdAt;
}
