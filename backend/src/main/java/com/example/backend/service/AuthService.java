package com.example.backend.service;

import com.example.backend.common.BusinessException;
import com.example.backend.dto.AuthResponse;
import com.example.backend.dto.LoginRequest;
import com.example.backend.dto.RegisterRequest;
import com.example.backend.entity.User;
import com.example.backend.mapper.UserMapper;
import com.example.backend.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {

	private static final Set<String> ALLOWED_ROLES = Set.of("applicant", "mo", "admin");

	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	public AuthService(UserMapper userMapper, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
		this.userMapper = userMapper;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}

	public AuthResponse register(RegisterRequest req) {
		validateRegister(req);

		if (userMapper.findByEmail(req.getEmail()) != null) {
			throw new BusinessException("该邮箱已注册");
		}

		User user = new User();
		user.setEmail(req.getEmail().trim());
		user.setPasswordHash(passwordEncoder.encode(req.getPassword()));
		user.setRole(req.getRole().trim());
		user.setName(req.getName().trim());
		userMapper.insert(user);

		String token = jwtUtil.createToken(user.getId(), user.getEmail(), user.getRole());
		return new AuthResponse(token, user.getId(), user.getEmail(), user.getRole(), user.getName());
	}

	public AuthResponse login(LoginRequest req) {
		if (isBlank(req.getEmail()) || isBlank(req.getPassword())) {
			throw new BusinessException("邮箱和密码不能为空");
		}

		User user = userMapper.findByEmail(req.getEmail().trim());
		if (user == null || !passwordEncoder.matches(req.getPassword(), user.getPasswordHash())) {
			throw new BusinessException("邮箱或密码错误");
		}

		String token = jwtUtil.createToken(user.getId(), user.getEmail(), user.getRole());
		return new AuthResponse(token, user.getId(), user.getEmail(), user.getRole(), user.getName());
	}

	private void validateRegister(RegisterRequest req) {
		if (isBlank(req.getEmail()) || isBlank(req.getPassword())
				|| isBlank(req.getRole()) || isBlank(req.getName())) {
			throw new BusinessException("邮箱、密码、角色、姓名均不能为空");
		}
		if (!ALLOWED_ROLES.contains(req.getRole().trim())) {
			throw new BusinessException("角色只能是 applicant / mo / admin");
		}
		if (req.getPassword().length() < 6) {
			throw new BusinessException("密码至少 6 位");
		}
	}

	private boolean isBlank(String s) {
		return s == null || s.isBlank();
	}
}
