package com.office.library;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		System.out.println("[HomeController] home()");
		
		return "redirect:/user/";
		
	}
}
