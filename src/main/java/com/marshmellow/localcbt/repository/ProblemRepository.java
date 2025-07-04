package com.marshmellow.localcbt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.marshmellow.localcbt.entity.Problem;

public interface ProblemRepository extends JpaRepository<Problem, Long>{
    Problem findById(long id);
    Page<Problem> findAll(Pageable pageable);
}
