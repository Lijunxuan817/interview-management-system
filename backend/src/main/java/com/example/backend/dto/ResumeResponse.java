package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ResumeResponse {
	private Long userId;
	private String filePath;
	private LocalDateTime uploadedAt;
}
