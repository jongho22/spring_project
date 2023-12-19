package com.office.library.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.office.library.user.member.UserMemberDao;
import com.office.library.user.member.UserMemberService;
import com.office.library.user.member.UserMemberVo;

@Controller
@RequestMapping("/user")
public class UserHomeController {
	
	@GetMapping({"","/"})
	public String home() {
		System.out.println("[UserHomeController] home()");
		
		String nextPage = "user/home";
		
		return nextPage;
	}
}
