package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Member;

public interface MemberRepo extends JpaRepository<Member, Long>{
	Member findByIdAndPw(String id, String pw);
	Member findByIdx(int idx);
	Long countById(String id);
}
