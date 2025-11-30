package com.hospital.automation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hospital.automation.dto.LoginDto;
import com.hospital.automation.model.User;
import com.hospital.automation.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	private final UserService userService;
	
	@GetMapping("/login")
	public String showloginForm(Model model) {
		model.addAttribute("login", new LoginDto());
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute LoginDto loginDto,HttpSession session,Model model) {
		try {
			User user = userService.login(loginDto);
			session.setAttribute("userId", user.getId());
			session.setAttribute("password", user.getRole().toString());
			
			if(user.getRole().toString().equals("ADMIN")) {
				return "redirect:/admin/dashboard";
			}else if (user.getRole().toString().equals("DOCTOR")) {
				return "redirect:/doctor/dashboard";
			}else {
				return "redirect:/patient/dashboard";
			}
		} catch (RuntimeException e) {
			model.addAttribute("error",e.getMessage());
			return "login";
		}
	}
		
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}
