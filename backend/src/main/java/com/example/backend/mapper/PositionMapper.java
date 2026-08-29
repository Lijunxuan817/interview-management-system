package com.example.backend.mapper;

import com.example.backend.entity.Position;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PositionMapper {

	@Select("""
			SELECT id, mo_id, title, course_name, type, requirements, quota, status, created_at
			FROM positions
			WHERE status = 'open'
			ORDER BY created_at DESC
			""")
	List<Position> findOpenPositions();

	@Select("""
			SELECT id, mo_id, title, course_name, type, requirements, quota, status, created_at
			FROM positions WHERE id = #{id}
			""")
	Position findById(Long id);

	@Insert("""
			INSERT INTO positions(mo_id, title, course_name, type, requirements, quota, status)
			VALUES(#{moId}, #{title}, #{courseName}, #{type}, #{requirements}, #{quota}, #{status})
			""")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(Position position);
}
