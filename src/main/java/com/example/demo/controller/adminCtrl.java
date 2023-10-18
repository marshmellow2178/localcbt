package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Problem;
import com.example.demo.service.AdminSvc;
import com.example.demo.service.TestSvc;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = {"/admin"})
public class AdminCtrl{
	private final TestSvc tSvc;
	private final AdminSvc aSvc;
	
	@RequestMapping(value = {"/login"})
	public String login(
			@RequestParam("id") String id,
			@RequestParam("pw") String pw,
			HttpServletRequest request,
			RedirectAttributes ra
			) {
		id = id.trim().toLowerCase();
		pw = pw.trim().toLowerCase();
		HttpSession session = request.getSession();
		if(session.getAttribute("admininfo")==null) { 
			Admin a = aSvc.login(id, pw);
			if(a == null ) {
				ra.addAttribute("error", "회원정보가 일치하지 않습니다");
			}
			session.setAttribute("admininfo", aSvc.login(id, pw));
		}
		return "redirect:/admin";
	}
	
	@RequestMapping(value = {"/list"})
	public String list(
			HttpServletRequest request,
			@RequestParam("tidx") String t,
			Model model
			) {
		HttpSession session = request.getSession();
		if(session.getAttribute("admininfo")==null) { 
			return "redirect:/admin";
		}
		List<Problem> pList = tSvc.getProblemList(Integer.parseInt(t));
		model.addAttribute("tidx", Integer.parseInt(t));
		model.addAttribute("pList", pList);
		return "admin/list";
	}
	
	@RequestMapping(value = {"/register_form"})
	public String register_form(
			HttpServletRequest request,
			@RequestParam("tidx") String t,
			Model model
			) {
		HttpSession session = request.getSession();
		if(session.getAttribute("admininfo")==null) { 
			return "redirect:/localcbt/admin";
		}
		model.addAttribute("tidx", Integer.parseInt(t));
		return "admin/register";
	}
	
	@RequestMapping(value = {"/register_proc"})
	public String register(
			@RequestParam("question") String question,
			@RequestParam("answer") String answer,
			@RequestParam("tidx") String tidx ,
			RedirectAttributes ra
			) {
		tSvc.setProblem(Integer.parseInt(tidx), question, answer);
		ra.addAttribute("tidx", tidx);
		return "redirect:localcbt/admin/list";
	}
	
	@RequestMapping(value = {"/update_form"})
	public String update_form(
			@RequestParam("pidx") String p,
			HttpServletRequest request,
			Model model
			) {
		HttpSession session = request.getSession();
		if(session.getAttribute("admininfo")==null) { 
			return "redirect:/localcbt/admin";
		}
		model.addAttribute("problem", tSvc.getProblem(Integer.parseInt(p)));
		return "admin/update";
	}
	
	@RequestMapping(value = {"/update_proc"})
	public String update(
			@RequestParam("pidx") String pidx,
			@RequestParam("question") String question,
			@RequestParam("answer") String answer,
			RedirectAttributes ra
			) {
		int tidx = tSvc.updateProblem(Integer.parseInt(pidx), question, answer);
		ra.addAttribute("tidx", tidx);
		return "redirect:/localcbt/admin/list";
	}
}
