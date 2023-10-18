package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Problem;

public interface ProblemRepo extends JpaRepository<Problem, Long>{
	List<Problem> findByTidx(int tidx);
	Problem findByIdx(int idx);
}
