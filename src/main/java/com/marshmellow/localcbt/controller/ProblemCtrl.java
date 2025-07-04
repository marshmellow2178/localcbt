package com.marshmellow.localcbt.controller;

import java.util.List;
import java.util.Random;

import com.marshmellow.localcbt.entity.Problem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.marshmellow.localcbt.entity.Question;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class ProblemCtrl {
	private final QuestionSvc qSvc;
	
	@RequestMapping(value = {"/question"})
	public String get_question(Model m) {
		int count = (int)qSvc.countQuestion();
		int num = (count==1)?1:new Random().nextInt(count)+1;
		
		Problem q = qSvc.getQuestion(num);
		m.addAttribute("question", q);
		return "question";
	}
	
	@RequestMapping(value = {"/result"})
	public String get_answer(
			@RequestParam("answer") String answer,
			@RequestParam("idx") Integer idx,
			Model m) {
		String chk = "X";
		Question q = qSvc.getQuestion(idx);
		if(answer.equals(q.getAnswer())) {
			chk = "O";
		}
		m.addAttribute("answer", chk);
		return "result";
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

