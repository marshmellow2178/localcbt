package com.marshmellow.localcbt.service;

import com.marshmellow.localcbt.entity.Problem;
import com.marshmellow.localcbt.repository.ProblemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class ProblemService {

    private final ProblemRepository problemRepository;

    public Problem getRandomProblem() {
        Random random = new Random();
        long num = random.nextLong(problemRepository.count());
        return problemRepository.findById(num);
    }

    public Problem getProblemById(Long id) {
        return problemRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Problem with id: " + id + " not found"));
    }

    public Page<Problem> getProblemPage(Pageable pageable){
        return problemRepository.findAll(pageable);
    }

    public long createProblem(String question, String answer) {
        Problem problem = Problem.create(question, answer);
        return problemRepository.save(problem).getId();
    }

    public boolean checkAnswer(String answer, String input){
        return answer.equals(input);
    }
}
