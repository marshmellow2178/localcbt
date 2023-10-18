package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Problem;
import com.example.demo.entity.Result;
import com.example.demo.entity.Test;
import com.example.demo.repository.ProblemRepo;
import com.example.demo.repository.ResultRepo;
import com.example.demo.repository.TestRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TestSvc {

	private final TestRepo tRepo;
	private final ProblemRepo pRepo;
	private final ResultRepo rRepo;
	
	public List<Test> getTestList(){
		return tRepo.findAll();
	}
	
	public Problem getProblem(int idx) {
		return pRepo.findByIdx(idx);
	}
	
	public void setProblem(int tidx, String question, String answer) {
		Problem p = Problem.builder()
				.tidx(tidx)
				.question(question)
				.answer(answer)
				.build();
		pRepo.save(p);
	}
	
	public int updateProblem(int pidx, String question, String answer) {
		Problem p = pRepo.findByIdx(pidx);
		p.update(question, answer);
		pRepo.save(p);
		return p.getTidx();
	}
	
	public List<Problem> getProblemList(int tidx){
		return pRepo.findByTidx(tidx);
	}
	
	public int setResult(int tidx, int midx, String[] answers) {
		List<Problem> pList = pRepo.findByTidx(tidx);
		int correct = 0;
		for(int i = 0;i<answers.length;i++) {
			answers[i] = answers[i].trim().toLowerCase();
			if(pList.get(i).getAnswer().equals(answers[i])) {
				correct++;
			}
		}
		Result r = rRepo.findByMidxAndTidx(midx, tidx);
		if(r == null) {
			r = Result.builder()
					.correct(correct)
					.date(LocalDateTime.now())
					.tidx(tidx)
					.midx(midx)
					.pcount(pList.size())
					.build();
		}else {
			r.setResult(correct, pList.size());
		}
				
		return rRepo.save(r).getCorrect();
	}
	
	public List<Result> getResultList(int midx){
		return rRepo.findByMidx(midx);
	}
}
