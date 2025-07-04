package com.marshmellow.localcbt.service;

import com.marshmellow.localcbt.entity.Problem;
import com.marshmellow.localcbt.repository.ProblemRepository;
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
        int num = random.nextInt(10);
        return problemRepository.findById(num);
    }

    public Page<Problem> getProblemPage(Pageable pageable){
        return problemRepository.findAll(pageable);
    }
}
