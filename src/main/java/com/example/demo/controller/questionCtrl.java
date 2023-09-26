package com.example.demo.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.entity.Question;
import com.example.demo.service.QuestionSvc;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class questionCtrl {
	private final QuestionSvc qSvc;
	
	@RequestMapping(value = {"/question"})
	public String get_question(Model m) {
		int count = (int)qSvc.countQuestion();
		int num = (count==1)?1:new Random().nextInt(count)+1;
		
		Question q = qSvc.getQuestion(num);
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

}

