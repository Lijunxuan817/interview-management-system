package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileResponse {
	private Long userId;
	private String major;
	private String grade;
	private String skills;
	private String phone;
}
