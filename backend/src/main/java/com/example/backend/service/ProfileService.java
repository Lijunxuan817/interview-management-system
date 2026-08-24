package com.example.backend.service;

import com.example.backend.common.BusinessException;
import com.example.backend.dto.ProfileRequest;
import com.example.backend.dto.ProfileResponse;
import com.example.backend.entity.ApplicantProfile;
import com.example.backend.mapper.ApplicantProfileMapper;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

	private final ApplicantProfileMapper profileMapper;

	public ProfileService(ApplicantProfileMapper profileMapper) {
		this.profileMapper = profileMapper;
	}

	public ProfileResponse getProfile(Long userId, String role) {
		requireApplicant(role);

		ApplicantProfile profile = profileMapper.findByUserId(userId);
		if (profile == null) {
			return new ProfileResponse(userId, null, null, null, null);
		}
		return toResponse(profile);
	}

	public ProfileResponse saveProfile(Long userId, String role, ProfileRequest req) {
		requireApplicant(role);

		ApplicantProfile profile = new ApplicantProfile();
		profile.setUserId(userId);
		profile.setMajor(trim(req.getMajor()));
		profile.setGrade(trim(req.getGrade()));
		profile.setSkills(trim(req.getSkills()));
		profile.setPhone(trim(req.getPhone()));

		if (profileMapper.findByUserId(userId) == null) {
			profileMapper.insert(profile);
		} else {
			profileMapper.update(profile);
		}
		return toResponse(profileMapper.findByUserId(userId));
	}

	private void requireApplicant(String role) {
		if (!"applicant".equals(role)) {
			throw new BusinessException("仅申请者可填写个人档案");
		}
	}

	private ProfileResponse toResponse(ApplicantProfile profile) {
		return new ProfileResponse(
				profile.getUserId(),
				profile.getMajor(),
				profile.getGrade(),
				profile.getSkills(),
				profile.getPhone());
	}

	private String trim(String value) {
		return value == null ? null : value.trim();
	}
}
