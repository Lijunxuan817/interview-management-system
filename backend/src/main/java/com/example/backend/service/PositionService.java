package com.example.backend.service;

import com.example.backend.common.BusinessException;
import com.example.backend.dto.PositionRequest;
import com.example.backend.dto.PositionResponse;
import com.example.backend.entity.Position;
import com.example.backend.mapper.PositionMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PositionService {

	private static final Set<String> ALLOWED_TYPES = Set.of("course_ta", "exam_proctor");

	private final PositionMapper positionMapper;

	public PositionService(PositionMapper positionMapper) {
		this.positionMapper = positionMapper;
	}

	public List<PositionResponse> listOpenPositions() {
		return positionMapper.findOpenPositions().stream()
				.map(this::toResponse)
				.toList();
	}

	public PositionResponse createPosition(Long userId, String role, PositionRequest req) {
		if (!"mo".equals(role)) {
			throw new BusinessException("仅课程组织者可发布岗位");
		}
		validate(req);

		Position position = new Position();
		position.setMoId(userId);
		position.setTitle(req.getTitle().trim());
		position.setCourseName(trim(req.getCourseName()));
		position.setType(req.getType() == null || req.getType().isBlank()
				? "course_ta"
				: req.getType().trim());
		position.setRequirements(trim(req.getRequirements()));
		position.setQuota(req.getQuota() == null ? 1 : req.getQuota());
		position.setStatus("open");

		positionMapper.insert(position);
		return toResponse(positionMapper.findById(position.getId()));
	}

	private void validate(PositionRequest req) {
		if (req.getTitle() == null || req.getTitle().isBlank()) {
			throw new BusinessException("岗位标题不能为空");
		}
		String type = req.getType() == null || req.getType().isBlank()
				? "course_ta"
				: req.getType().trim();
		if (!ALLOWED_TYPES.contains(type)) {
			throw new BusinessException("岗位类型只能是 course_ta / exam_proctor");
		}
		if (req.getQuota() != null && req.getQuota() < 1) {
			throw new BusinessException("招聘人数至少为 1");
		}
	}

	private PositionResponse toResponse(Position p) {
		return new PositionResponse(
				p.getId(),
				p.getMoId(),
				p.getTitle(),
				p.getCourseName(),
				p.getType(),
				p.getRequirements(),
				p.getQuota(),
				p.getStatus(),
				p.getCreatedAt());
	}

	private String trim(String value) {
		return value == null ? null : value.trim();
	}
}
