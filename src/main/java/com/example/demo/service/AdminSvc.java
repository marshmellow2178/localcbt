package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminSvc {

	public final AdminRepo aRepo;
	
	public Admin login(String id, String pw) {
		return aRepo.findByIdAndPw(id, pw);
	}
}
