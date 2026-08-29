package com.example.backend.controller;

import com.example.backend.common.ApiResponse;
import com.example.backend.dto.PositionRequest;
import com.example.backend.dto.PositionResponse;
import com.example.backend.service.PositionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

	private final PositionService positionService;

	public PositionController(PositionService positionService) {
		this.positionService = positionService;
	}

	@GetMapping
	public ApiResponse<List<PositionResponse>> list() {
		return ApiResponse.ok(positionService.listOpenPositions());
	}

	@PostMapping
	public ApiResponse<PositionResponse> create(
			@RequestAttribute("userId") Long userId,
			@RequestAttribute("role") String role,
			@RequestBody PositionRequest request) {
		return ApiResponse.ok(positionService.createPosition(userId, role, request));
	}
}
