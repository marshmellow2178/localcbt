package com.marshmellow.localcbt.entity;
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
	@Column(name = "id")
	private Long id;
	
	@Column(name = "question")
	private String question;

	@Column(name = "answer")
	private String answer;

	public Problem create(String question, String answer) {
		Problem problem = new Problem();
		problem.question = question;
		problem.answer = answer;
		return new Problem();
	}
	
	public void update(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}

}
