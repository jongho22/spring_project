package com.office.library.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.office.library.user.member.UserMemberService;
import com.office.library.user.member.UserMemberVo;

@Controller
@RequestMapping("/user/member")
public class UserHomeController {
	
	@Autowired
	UserMemberService userMemberService;
	
	@GetMapping({"","/"})
	public String home() {
		System.out.println("[UserHomeController] home()");
		
		String nextPage = "user/home";
		
		return nextPage;
	}
	
	// 회원가입 
	@GetMapping("/createAccountForm")
	public String createAccountForm() {
		System.out.println("[UserHomeController] createAccountForm()");
		
		String nextPage = "user/member/create_account_form";
		
		return nextPage;
	}
	
	// 회원가입 확인 
	@PostMapping("/createAccountConfirm")
	public String createAccountConfirm(UserMemberVo userMemberVo) {
		System.out.println("[UserHomeController] createAccountConfirm()");
		
		String nextPage = "user/member/create_account_ok";
		
		int result = userMemberService.createAccountConfirm(userMemberVo);
		
		if (result <= 0 ) {
			nextPage = "user/member/create_account_ng";
		}
		
		return nextPage;
	}
}
