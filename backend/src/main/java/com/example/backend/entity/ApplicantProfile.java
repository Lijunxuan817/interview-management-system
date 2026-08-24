package com.example.backend.entity;

import lombok.Data;

@Data
public class ApplicantProfile {
	private Long id;
	private Long userId;
	private String major;
	private String grade;
	private String skills;
	private String phone;
}
