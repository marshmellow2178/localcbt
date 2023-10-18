package com.example.demo.entity;
import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@DynamicUpdate
@Table(name = "problem")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Problem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx")
	private int idx;
	
	@Column(name = "tidx")
	private int tidx;
	
	@Column(name = "question")
	private String question;

	@Column(name = "answer")
	private String answer;
	
	public void update(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}
		
	@Builder
	public Problem(int idx, int tidx, String question, String answer) {
		super();
		this.idx = idx;
		this.tidx = tidx;
		this.question = question;
		this.answer = answer;
	}
}
