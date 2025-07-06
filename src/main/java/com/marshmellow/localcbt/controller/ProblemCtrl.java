package com.marshmellow.localcbt.controller;

import com.marshmellow.localcbt.entity.Problem;
import com.marshmellow.localcbt.service.ProblemService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/problem")
public class ProblemCtrl {
	
	private final ProblemService problemService;

	@GetMapping("/random")
	public String getRandomProblem(
			Model model
	){
		model.addAttribute("problem", problemService.getRandomProblem());
		return "problem";
	}

	@GetMapping("/{id}")
	public String getProblemById(@PathVariable Long id, Model model){
		model.addAttribute("problem", problemService.getProblemById(id));
		return "problem";
	}

	@GetMapping("/list")
	public String getAllProblems(
			Model model,
			@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable){
		model.addAttribute("problemPage", problemService.getProblemPage(pageable));
		return "list";
	}

	@GetMapping("/create")
	public String showCreateFrom(){
		return "register";
	}

	@PostMapping("/create")
	public String postCreateForm(
			@RequestParam String question,
			@RequestParam String answer
	){
		long id = problemService.createProblem(question, answer);
		return "redirect:/problem/" + id;
	}

	@PostMapping("/check")
	public String check(
			@RequestParam long id,
			@RequestParam String input,
			Model model
	){
		Problem p = problemService.getProblemById(id);
		model.addAttribute("result", problemService.checkAnswer(p.getAnswer(), input));
		model.addAttribute("answer", p.getAnswer());
		return "result";
	}
}

