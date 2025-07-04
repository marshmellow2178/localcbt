package com.marshmellow.localcbt.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

import org.springframework.ui.Model;
@Controller
@RequiredArgsConstructor
public class HomeCtrl{

	
	@RequestMapping(value = {"/"})
	public String home_ctrl(
			@AuthenticationPrincipal UserDetails userDetails,
			Model model
			) {
		if(userDetails == null){
			return "redirect:/login";
		}
		return "redirect:/problem/list";
	}

	@GetMapping("/login")
	public String loginForm(){
		return "login";
	}
}
