package com.example.backend.mapper;

import com.example.backend.entity.Resume;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ResumeMapper {

	@Select("""
			SELECT id, user_id, file_path, uploaded_at
			FROM resumes
			WHERE user_id = #{userId}
			ORDER BY uploaded_at DESC
			LIMIT 1
			""")
	Resume findLatestByUserId(Long userId);

	@Insert("INSERT INTO resumes(user_id, file_path) VALUES(#{userId}, #{filePath})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(Resume resume);
}
