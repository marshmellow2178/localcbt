package com.example.demo.entity;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@DynamicUpdate
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx")
	private int idx;
	
	@Column(name = "id")
	private String id;
	
	@Column(name = "pw")
	private String pw;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "domain")
	private String domain;
	
	@Column(name = "joindate")
	private LocalDateTime joindate;
	
	@Column(name = "lastlogin")
	private LocalDateTime lastlogin;
	
	@Column(name = "point")
	private int point;
	
	@Column(name = "state")
	private String state;
	
	public void login() {
		this.lastlogin = LocalDateTime.now();
	}
	
	public void update(String id, String phone, String email, String domain) {
		this.id = id;
		this.phone = phone;
		this.email = email;
		this.domain = domain;
	}
	
	public void setPoint(int point) {
		this.point += point;
	}
	
	@Builder
	public Member(int idx, String id, String pw, String name, String phone, String email, String domain,
			LocalDateTime joindate, LocalDateTime lastlogin, int point, String state) {
		super();
		this.idx = idx;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.domain = domain;
		this.joindate = joindate;
		this.lastlogin = lastlogin;
		this.point = point;
		this.state = state;
	}
}
