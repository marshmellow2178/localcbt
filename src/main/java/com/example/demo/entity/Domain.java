package com.example.demo.entity;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "domain")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Domain {
	@Id
	@Column(name = "name")
	private String name;

	@Builder
	public Domain(String name) {
		super();
		this.name = name;
	}
}
