package com.example.backend.dto;

import lombok.Data;

@Data
public class PositionRequest {
	private String title;
	private String courseName;
	private String type;
	private String requirements;
	private Integer quota;
}
