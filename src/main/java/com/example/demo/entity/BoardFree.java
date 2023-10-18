package com.example.demo.entity;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@DynamicUpdate
@Table(name = "board_free")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardFree {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx")
	private int idx;
	
	@Column(name = "midx")
	private int midx;
	
	@Column(name = "mid")
	private String mid;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "content")
	private String content;
	
	@Setter
	@Column(name = "reply")
	private int reply;
	
	@Column(name = "date")
	private LocalDateTime date;
	
	public void setBoardFree(String title, String content) {
		this.title = title;
		this.content = content;
		this.date = LocalDateTime.now();
	}

	@Builder
	public BoardFree(int idx, int midx, String mid, String title, String content, int reply, LocalDateTime date) {
		super();
		this.idx = idx;
		this.midx = midx;
		this.mid = mid;
		this.title = title;
		this.content = content;
		this.reply = reply;
		this.date = date;
	}
	
}
