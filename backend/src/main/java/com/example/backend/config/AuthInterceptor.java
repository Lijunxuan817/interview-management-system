package com.example.backend.config;

import com.example.backend.common.UnauthorizedException;
import com.example.backend.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

	private final JwtUtil jwtUtil;

	public AuthInterceptor(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String auth = request.getHeader("Authorization");
		if (auth == null || !auth.startsWith("Bearer ")) {
			throw new UnauthorizedException("请先登录");
		}

		String token = auth.substring(7).trim();
		request.setAttribute("userId", jwtUtil.parseUserId(token));
		request.setAttribute("role", jwtUtil.parseRole(token));
		return true;
	}
}
