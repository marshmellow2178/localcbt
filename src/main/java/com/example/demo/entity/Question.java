package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Builder;

@Entity
@Getter
@Table(name = "sample_q")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx")
	private int idx;
	
	@Column(name = "question")
	private String question;
	
	@Column(name = "answer")
	private String answer;
	
	@Column(name = "date")
	private LocalDateTime date;

	@Builder
	public Question(int idx, String question, String answer, LocalDateTime date) {
		super();
		this.idx = idx;
		this.question = question;
		this.answer = answer;
		this.date = date;
	}

}
