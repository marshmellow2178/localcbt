package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Member;
import com.example.demo.entity.Problem;
import com.example.demo.service.TestSvc;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = {"/test"})
public class TestCtrl{
	
	private final TestSvc tSvc;
	
	@RequestMapping(value = {"/form"})
	public String printTest(
			@RequestParam("tidx") String t,
			Model model
			) {
		int tidx = Integer.parseInt(t);
		if(tidx == 0) {
			return "index";
		}
		List<Problem> problem = tSvc.getProblemList(tidx);
		model.addAttribute("tidx", tidx);
		model.addAttribute("problem", problem);
		return "question";
	}
	
	@RequestMapping(value = {"/check"}, method = RequestMethod.POST)
	@ResponseBody
	public void checkAnswer(
			@RequestParam("tidx") String tidx,
			@RequestParam("answers") String[] answers,
			HttpServletRequest request
			) {
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("logininfo");
		tSvc.setResult(Integer.parseInt(tidx), m.getIdx(), answers);
	}
}
