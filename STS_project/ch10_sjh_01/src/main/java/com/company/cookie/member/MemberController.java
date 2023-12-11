package com.company.cookie.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@GetMapping({"","/"})
	public String home() {
		System.out.println("[MemberController] home()");
		
		String nextPage = "member/home";
		
		return nextPage;
	}
}
