package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.FreeReply;

public interface FreeReplyRepo extends JpaRepository<FreeReply, Long>{
	List<FreeReply> findByBidx(int bidx);
	Page<FreeReply> findByMidx(int midx, Pageable pageable);
	FreeReply findByIdx(int idx);
}
