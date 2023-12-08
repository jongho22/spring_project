package com.office.library.admin.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/member")
public class AdminMemberController {
	
	@Autowired
	AdminMemberService adminMemberService;
	
	// 어드민 회원가입 화면 이동 - GET
	// @RequestMapping(value = {"/createAccountForm"}, method = RequestMethod.GET)
	@GetMapping("/createAcoountForm")
	public String createAccountForm() {
		System.out.println("[AdminMemberController] createAccountForm()");
		
		String nextPage = "admin/member/create_account_form";
		
		return nextPage;
	}
	
	// 회원가입 확인 - POST
    // @RequestMapping(value = {"/createAccountConfirm"}, method = RequestMethod.POST)
	@PostMapping("/createAcoountForm")
	public String createAccountConfirm(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberController] createAccountCofirm()");
		
		String nextPage = "admin/member/create_account_ok";
		
		int result = adminMemberService.createAccoountConfirm(adminMemberVo);
		
		if (result <= 0)
			nextPage = "admin/member/create_account_ng";
		
		return nextPage;
	}
}
