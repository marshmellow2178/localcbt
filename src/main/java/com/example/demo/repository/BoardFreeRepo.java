package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.BoardFree;

public interface BoardFreeRepo extends JpaRepository<BoardFree, Long>{
	BoardFree findByIdx(int idx);
	Page<BoardFree> findAll(Pageable pageable);
	Page<BoardFree> findByMidx(int midx, Pageable pageable);
}
