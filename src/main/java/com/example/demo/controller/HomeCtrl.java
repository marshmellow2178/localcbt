package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.TestSvc;

import lombok.RequiredArgsConstructor;

import org.springframework.ui.Model;
@Controller
@RequiredArgsConstructor
public class HomeCtrl{
	
	private final TestSvc tSvc;
	
	@RequestMapping(value = {"/", "/index"})
	public String home_ctrl(
			Model model
			) {
		model.addAttribute("testinfo", tSvc.getTestList());
		return "index";
	}
	
	@RequestMapping(value = {"/admin"})
	public String admin_index(
			Model model
			) {
		model.addAttribute("testinfo", tSvc.getTestList());
		return "admin/login";
	}
}
