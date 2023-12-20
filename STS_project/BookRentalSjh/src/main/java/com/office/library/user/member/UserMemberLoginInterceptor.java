package com.office.library.user.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserMemberLoginInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		System.out.println("[UserMemberLoginInterceptor]");
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			Object object = session.getAttribute("loginedUserMemberVo");
			if (object != null) return true;
		}
		
		response.sendRedirect(request.getContextPath() + "/user/member/loginForm");
		
		return false;
	}
}
