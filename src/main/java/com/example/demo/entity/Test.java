package com.example.demo.entity;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "test_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Test {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx")
	private int idx;
	
	@Column(name = "name")
	private String name;
	
	@Builder
	public Test(int idx, String name) {
		super();
		this.idx = idx;
		this.name = name;
	}
}
