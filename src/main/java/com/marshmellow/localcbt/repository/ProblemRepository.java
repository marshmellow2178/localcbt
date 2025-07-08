package com.marshmellow.localcbt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.marshmellow.localcbt.entity.Problem;

import java.util.List;
import java.util.Optional;

public interface ProblemRepository extends JpaRepository<Problem, Long>{
    Page<Problem> findByQuestionContaining(Pageable pageable, String keyword);
}
