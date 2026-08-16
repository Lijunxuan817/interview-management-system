package com.example.backend.mapper;

import com.example.backend.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

	@Select("SELECT id, email, password_hash, role, name, created_at FROM users WHERE email = #{email}")
	User findByEmail(String email);

	@Insert("""
			INSERT INTO users(email, password_hash, role, name)
			VALUES(#{email}, #{passwordHash}, #{role}, #{name})
			""")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(User user);
}
