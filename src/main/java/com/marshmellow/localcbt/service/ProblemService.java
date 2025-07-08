package com.marshmellow.localcbt.service;

import com.marshmellow.localcbt.entity.Problem;
import com.marshmellow.localcbt.repository.ProblemRepository;
import jakarta.persistence.EntityNotFoundException;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@AllArgsConstructor
public class ProblemService {

    private final ProblemRepository problemRepository;

    @Transactional(readOnly = true)
    public Problem getRandomProblem() {
        long total = problemRepository.count();
        if (total == 0) {
            throw new EntityNotFoundException("등록된 문제가 없습니다.");
        }
        int offset = new Random().nextInt((int) total);
        Page<Problem> page = problemRepository.findAll(PageRequest.of(offset, 1));
        //offset -> 레코드의 몇 번째부터 limit개까지 가져온다 -> 빈 공간 무시 가능
        return page.getContent().get(0);
    }

    @Transactional(readOnly = true)
    public Problem getProblemById(Long id) {
        return problemRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Problem with id: " + id + " not found"));
    }

    @Transactional(readOnly = true)
    public Page<Problem> getProblemPage(Pageable pageable){
        return problemRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Problem> findByKeyword(Pageable pageable, String keyword){
        return problemRepository.findByQuestionContaining(pageable, keyword);
    }

    @Transactional
    public long createProblem(String question, String answer) {
        Problem problem = Problem.create(question, answer);
        problemRepository.save(problem);
        return problem.getId();
    }

    @Transactional
    public long updateProblem(Long id, String question, String answer) {
        Problem problem = problemRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Problem with id: " + id + " not found"));
        problem.update(question, answer);
        problemRepository.save(problem);
        return problem.getId();
    }

    @Transactional
    public void deleteProblem(Long id) {
        problemRepository.deleteById(id);
    }

    public boolean checkAnswer(String answer, String input){
        input = input.trim();
        return answer.equalsIgnoreCase(input);
    }
}
