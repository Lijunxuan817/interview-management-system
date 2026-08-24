package com.example.backend.controller;

import com.example.backend.common.ApiResponse;
import com.example.backend.dto.ProfileRequest;
import com.example.backend.dto.ProfileResponse;
import com.example.backend.service.ProfileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

	private final ProfileService profileService;

	public ProfileController(ProfileService profileService) {
		this.profileService = profileService;
	}

	@GetMapping
	public ApiResponse<ProfileResponse> get(
			@RequestAttribute("userId") Long userId,
			@RequestAttribute("role") String role) {
		return ApiResponse.ok(profileService.getProfile(userId, role));
	}

	@PutMapping
	public ApiResponse<ProfileResponse> update(
			@RequestAttribute("userId") Long userId,
			@RequestAttribute("role") String role,
			@RequestBody ProfileRequest request) {
		return ApiResponse.ok(profileService.saveProfile(userId, role, request));
	}
}
