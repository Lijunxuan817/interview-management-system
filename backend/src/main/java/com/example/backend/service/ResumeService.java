package com.example.backend.service;

import com.example.backend.common.BusinessException;
import com.example.backend.dto.ResumeResponse;
import com.example.backend.entity.Resume;
import com.example.backend.mapper.ResumeMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ResumeService {

	private final ResumeMapper resumeMapper;
	private final Path uploadDir;

	public ResumeService(
			ResumeMapper resumeMapper,
			@Value("${app.upload.dir:uploads}") String uploadDir) {
		this.resumeMapper = resumeMapper;
		this.uploadDir = Paths.get(uploadDir).toAbsolutePath().normalize();
	}

	public ResumeResponse getResume(Long userId, String role) {
		requireApplicant(role);

		Resume resume = resumeMapper.findLatestByUserId(userId);
		if (resume == null) {
			return new ResumeResponse(userId, null, null);
		}
		return toResponse(resume);
	}

	public ResumeResponse uploadResume(Long userId, String role, MultipartFile file) {
		requireApplicant(role);
		validatePdf(file);

		try {
			Files.createDirectories(uploadDir);
			String fileName = userId + "_" + System.currentTimeMillis() + ".pdf";
			Path target = uploadDir.resolve(fileName);
			file.transferTo(target.toFile());

			Resume resume = new Resume();
			resume.setUserId(userId);
			resume.setFilePath("uploads/" + fileName);
			resumeMapper.insert(resume);

			return toResponse(resumeMapper.findLatestByUserId(userId));
		} catch (IOException e) {
			throw new BusinessException("简历保存失败");
		}
	}

	private void requireApplicant(String role) {
		if (!"applicant".equals(role)) {
			throw new BusinessException("仅申请者可上传简历");
		}
	}

	private void validatePdf(MultipartFile file) {
		if (file == null || file.isEmpty()) {
			throw new BusinessException("请选择 PDF 文件");
		}
		String name = file.getOriginalFilename();
		if (name == null || !name.toLowerCase().endsWith(".pdf")) {
			throw new BusinessException("仅支持 PDF 格式");
		}
	}

	private ResumeResponse toResponse(Resume resume) {
		return new ResumeResponse(resume.getUserId(), resume.getFilePath(), resume.getUploadedAt());
	}
}
