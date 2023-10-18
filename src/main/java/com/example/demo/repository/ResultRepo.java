package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Result;

public interface ResultRepo extends JpaRepository<Result, Long>{
	List<Result> findByMidx(int midx);
	Result findByMidxAndTidx(int midx, int tidx);
}
