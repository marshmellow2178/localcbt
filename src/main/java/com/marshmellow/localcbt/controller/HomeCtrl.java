package com.marshmellow.localcbt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

import org.springframework.ui.Model;
@Controller
@RequiredArgsConstructor
public class HomeCtrl{

	
	@RequestMapping(value = {"/"})
	public String home_ctrl(
			Model model
			) {
		return "index";
	}
}
