package com.example.demo.vo;

import lombok.Getter;

@Getter
public class MemberUpdateVo {
	private String idx, id, phone, email, domain;

	public MemberUpdateVo(String idx, String id, String pw, String phone, String email, String domain) {
		super();
		this.idx = idx;
		this.id = id;
		this.phone = phone;
		this.email = email;
		this.domain = domain;
	}

}
