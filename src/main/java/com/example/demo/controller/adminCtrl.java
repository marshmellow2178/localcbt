package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Question;
import com.example.demo.service.AdminSvc;
import com.example.demo.service.QuestionSvc;
import com.example.demo.vo.Pageinfo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class adminCtrl {
	private final QuestionSvc qSvc;
	private final AdminSvc aSvc;
	
	@RequestMapping(value = {"/login_form"})
	public String login_form(
			HttpServletRequest reqeust) {
		HttpSession session = reqeust.getSession();
		if(session.getAttribute("admin")!= null) {
			return "redirect:/list";
		}
		return "admin/login";
	}
	
	@RequestMapping(value = {"/login_proc"})
	public String login(
			@RequestParam("id") String id,
			@RequestParam("pw") String pw,
			HttpServletRequest reqeust
			) {
		id = id.trim().toLowerCase();
		pw = pw.trim().toLowerCase();
		HttpSession session = reqeust.getSession();
		Admin a = aSvc.login(id, pw);
		session.setAttribute("admin", a);
		return "redirect:/list";
	}
	
	@RequestMapping(value = {"/list"})
	public String list(
			Model model, 
			@PageableDefault(size=10, sort="date", direction = Sort.Direction.DESC)Pageable pageable) {
		Pageinfo pageinfo = new Pageinfo();
		List<Question> qList = qSvc.getQuestionList(pageable, pageinfo);
		model.addAttribute("pageinfo", pageinfo);
		model.addAttribute("qList", qList);
		return "admin/list";
	}
	
	@RequestMapping(value = {"/register_form"})
	public String register_form() {
		
		return "admin/register";
	}
	
	@RequestMapping(value = {"/register_proc"})
	public String register(
			@RequestParam("question") String question,
			@RequestParam("answer") String answer
			) {
		question = question.trim().toLowerCase();
		answer = answer.trim().toLowerCase();
		if(qSvc.insertQuestion(question, answer)==0) {
			return "error";
		}
		return "redirect:/list";
	}
	
	@RequestMapping(value = {"/update_form"})
	public String update_form(
			@RequestParam("idx") Integer idx,
			Model model
			) {
		model.addAttribute("question", qSvc.getQuestion(idx));
		return "admin/update";
	}
	
	@RequestMapping(value = {"/update_proc"})
	public String update(
			@RequestParam("idx") Integer idx,
			@RequestParam("question") String question,
			@RequestParam("answer") String answer
			) {
		question = question.trim().toLowerCase();
		answer = answer.trim().toLowerCase();
		qSvc.updateQuestion(idx, question, answer);
		return "redirect:/list";
	}
	
	@RequestMapping(value = {"/delete"})
	public String delete(
			@RequestParam("idx") Integer idx
			) {
		qSvc.deleteQuestion(idx);
		return "redirect:/list";
	}
}
