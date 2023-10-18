package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Point;

public interface PointRepo extends JpaRepository<Point, Long>{
	Page<Point> findByMidx(int midx, Pageable pageable);
}
