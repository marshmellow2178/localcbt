package com.example.demo.entity;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "admin_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx")
	private int idx;
	
	@Column(name = "id")
	private String id;
	
	@Column(name = "pw")
	private String pw;

	@Builder
	public Admin(int idx, String id, String pw) {
		super();
		this.idx = idx;
		this.id = id;
		this.pw = pw;
	}
	
}
