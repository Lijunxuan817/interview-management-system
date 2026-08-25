package com.example.backend.controller;

import com.example.backend.common.ApiResponse;
import com.example.backend.dto.ResumeResponse;
import com.example.backend.service.ResumeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

	private final ResumeService resumeService;

	public ResumeController(ResumeService resumeService) {
		this.resumeService = resumeService;
	}

	@GetMapping
	public ApiResponse<ResumeResponse> get(
			@RequestAttribute("userId") Long userId,
			@RequestAttribute("role") String role) {
		return ApiResponse.ok(resumeService.getResume(userId, role));
	}

	@PostMapping("/upload")
	public ApiResponse<ResumeResponse> upload(
			@RequestAttribute("userId") Long userId,
			@RequestAttribute("role") String role,
			@RequestParam("file") MultipartFile file) {
		return ApiResponse.ok(resumeService.uploadResume(userId, role, file));
	}
}
