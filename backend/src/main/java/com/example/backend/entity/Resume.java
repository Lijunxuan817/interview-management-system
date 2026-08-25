package com.example.backend.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Resume {
	private Long id;
	private Long userId;
	private String filePath;
	private LocalDateTime uploadedAt;
}
