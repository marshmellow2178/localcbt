package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Test;

public interface TestRepo extends JpaRepository<Test, Long>{
	Test findByIdx(int idx);
	List<Test> findAll();
}
