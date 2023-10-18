package com.example.demo.entity;
import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "free_reply")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeReply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx")
	private int idx;
	
	@Column(name = "midx")
	private int midx;
	
	@Column(name = "bidx")
	private int bidx;
	
	@Column(name = "mid")
	private String mid;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "date")
	private LocalDateTime date;

	@Builder
	public FreeReply(int idx, int midx, int bidx, String mid, String content, LocalDateTime date) {
		super();
		this.idx = idx;
		this.midx = midx;
		this.mid = mid;
		this.bidx = bidx;
		this.content = content;
		this.date = date;
	}
	
}
