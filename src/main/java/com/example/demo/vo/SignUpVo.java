package com.example.demo.vo;

import lombok.Getter;

@Getter
public class SignUpVo {
	private String name, id, pw, phone, email, domain;

	public SignUpVo(String name, String id, String pw, String phone, String email, String domain) {
		super();
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.phone = phone;
		this.email = email;
		this.domain = domain;
	}

}
