package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, Long>{
	Admin findByIdAndPw(String id, String pw);
}
