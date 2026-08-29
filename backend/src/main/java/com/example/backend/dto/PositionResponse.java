package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PositionResponse {
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
