package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Question;
import com.example.demo.repository.QuestionRepo;
import com.example.demo.vo.Pageinfo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionSvc {
	private final QuestionRepo qRepo;
	
	public long countQuestion() {
		return qRepo.count();
	}
	
	public Question getQuestion(int idx) {
		return qRepo.findByIdx(idx);
	}
	
	public List<Question> getQuestionList(Pageable pageable, Pageinfo pageinfo){
		Page<Question> qPage = qRepo.findAll(pageable);
		pageinfo.setPage(qPage);
		return qPage.getContent();
	}
	
	public int insertQuestion(String question, String answer) {
		Question q = Question.builder()
				.question(question)
				.answer(answer)
				.date(LocalDateTime.now())
				.build();
		return qRepo.save(q).getIdx();
	}
	
	public void updateQuestion(int idx, String question, String answer) {
		Question q = Question.builder()
				.idx(idx)
				.question(question)
				.answer(answer)
				.date(LocalDateTime.now())
				.build();
		qRepo.save(q);
	}
	
	public void deleteQuestion(int idx) {
		Question q = qRepo.findByIdx(idx);
		qRepo.delete(q);
	}
}
