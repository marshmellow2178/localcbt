package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Domain;
import com.example.demo.entity.Member;
import com.example.demo.repository.DomainRepo;
import com.example.demo.repository.MemberRepo;
import com.example.demo.vo.MemberUpdateVo;
import com.example.demo.vo.SignUpVo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberSvc {

	private final MemberRepo mRepo;
	private final DomainRepo dRepo;
	
	public Member login(String id, String pw) {
		Member m = mRepo.findByIdAndPw(id, pw);
		if(m != null) {
			m.login();
		}
		return m;
	}
	
	public void sign_up(SignUpVo sv) {
		Member m = Member.builder()
				.name(sv.getName())
				.id(sv.getId())
				.pw(sv.getPw())
				.phone(sv.getPhone())
				.email(sv.getEmail())
				.domain(sv.getDomain())
				.joindate(LocalDateTime.now())
				.build();
		mRepo.save(m);
	}
	
	public int countById(String id) {
		return mRepo.countById(id).intValue();
	}
	
	public List<Domain> getDomainList(){
		return dRepo.findAll();
	}
	
	public void memberUpdate(MemberUpdateVo muv, Member m) {
		if(m.getIdx() == Integer.parseInt(muv.getIdx())) {
			m.update(muv.getId(), muv.getPhone(), muv.getEmail(), muv.getDomain());
			mRepo.save(m);
		}
	}
}
