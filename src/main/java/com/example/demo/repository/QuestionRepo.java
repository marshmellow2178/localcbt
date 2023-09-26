package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Question;

public interface QuestionRepo extends JpaRepository<Question, Long>{
	Question findByIdx(int idx);
	Page<Question> findAll(Pageable pageable);
}
