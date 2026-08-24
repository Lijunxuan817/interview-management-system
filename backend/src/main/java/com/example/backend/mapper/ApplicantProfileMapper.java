package com.example.backend.mapper;

import com.example.backend.entity.ApplicantProfile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ApplicantProfileMapper {

	@Select("""
			SELECT id, user_id, major, grade, skills, phone
			FROM applicant_profiles WHERE user_id = #{userId}
			""")
	ApplicantProfile findByUserId(Long userId);

	@Insert("""
			INSERT INTO applicant_profiles(user_id, major, grade, skills, phone)
			VALUES(#{userId}, #{major}, #{grade}, #{skills}, #{phone})
			""")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(ApplicantProfile profile);

	@Update("""
			UPDATE applicant_profiles
			SET major = #{major}, grade = #{grade}, skills = #{skills}, phone = #{phone}
			WHERE user_id = #{userId}
			""")
	int update(ApplicantProfile profile);
}
