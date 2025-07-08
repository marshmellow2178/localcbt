package com.marshmellow.localcbt.controller;

import com.marshmellow.localcbt.entity.Problem;
import com.marshmellow.localcbt.service.ProblemService;
import com.marshmellow.localcbt.web.ProblemFormDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	public String getProblemPage(
			Model model,
			@RequestParam(required = false) String keyword,
			@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable){
		Page<Problem> problemPage;
		if(keyword != null && !keyword.isEmpty()){
			problemPage = problemService.findByKeyword(pageable, keyword);
			model.addAttribute("keyword", keyword);
		}else{
			problemPage = problemService.getProblemPage(pageable);
		}
		model.addAttribute("problemPage", problemPage);
		return "list";
	}

	@GetMapping("/create")
	public String showCreateFrom(Model model){
		model.addAttribute("problemForm", new ProblemFormDto());
		return "problem_form";
	}

	@PostMapping("/create")
	public String postCreateForm(
			@Valid
			@ModelAttribute("problemForm") ProblemFormDto form,
			BindingResult res,
			Model model
	){
		if(res.hasErrors()){
			return "problem_form";
		}
		long id = problemService.createProblem(form.getQuestion(), form.getAnswer());
		return "redirect:/problem/" + id;
	}

	@GetMapping("/update/{id}")
	public String showUpdateForm(
			@PathVariable Long id,
			Model model
	){
		Problem problem = problemService.getProblemById(id);
		ProblemFormDto form = new ProblemFormDto();
		form.setId(id);
		form.setQuestion(problem.getQuestion());
		form.setAnswer(problem.getAnswer());
		model.addAttribute("problemForm", form);
		return "problem_form";
	}

	@PostMapping("/update")
	public String postUpdateForm(
			@Valid
			@ModelAttribute("problemForm") ProblemFormDto form,
			BindingResult res
	){
		if(res.hasErrors()){
			return "problem_form";
		}
		problemService.updateProblem(form.getId(), form.getQuestion(), form.getAnswer());
		return "redirect:/problem/" + form.getId();
	}

	@PostMapping("/delete")
	public String deleteProblem(@RequestParam Long id){
		problemService.deleteProblem(id);
		return "redirect:/problem/list";
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

