package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BoardFree;
import com.example.demo.entity.FreeReply;
import com.example.demo.repository.BoardFreeRepo;
import com.example.demo.repository.FreeReplyRepo;
import com.example.demo.vo.Pageinfo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardSvc {
	private final BoardFreeRepo bfRepo;
	private final FreeReplyRepo frRepo;
	
	public List<BoardFree> getFreeList(Pageable pageable, Pageinfo pageinfo){
		Page<BoardFree> bfPage = bfRepo.findAll(pageable);
		pageinfo.setPage(bfPage);
		return bfPage.getContent();
	}
	
	public BoardFree getBoardFree(int idx) {
		return bfRepo.findByIdx(idx);
	}
	
	public List<FreeReply> getFreeReply(int bidx){
		return frRepo.findByBidx(bidx);
	}
	
	public void setBoardFree(int midx, String mid, String title, String content) {
		BoardFree bf = BoardFree.builder()
				.midx(midx)
				.mid(mid)
				.title(title)
				.content(content)
				.date(LocalDateTime.now())
				.reply(0)
				.build();
		bfRepo.save(bf);
	}
	
	public void upBoardFree(int bidx, String title, String content) {
		BoardFree bf = bfRepo.findByIdx(bidx);
		bf.setBoardFree(title, content);
		bfRepo.save(bf);
	}
	
	public void deleteBoardFree(int bidx) {
		bfRepo.delete(bfRepo.findByIdx(bidx));
	}
	
	public void replyBoardFree(int midx, int bidx, String mid, String content) {
		FreeReply fr = FreeReply.builder()
				.midx(midx)
				.mid(mid)
				.bidx(bidx)
				.content(content)
				.date(LocalDateTime.now())
				.build();
		frRepo.save(fr);
		BoardFree bf = bfRepo.findByIdx(bidx);
		bf.setReply(bf.getReply()+1);
		bfRepo.save(bf);
	}
	
	public void deleteReply(int idx, int midx) {
		FreeReply fr = frRepo.findByIdx(idx);
		if(fr.getMidx()==midx) {
			frRepo.delete(fr);
		}
	}
	
	public List<BoardFree> findBoard(int midx, Pageable pageable, Pageinfo pageinfo){
		Page<BoardFree> bfPage = bfRepo.findByMidx(midx, pageable);
		pageinfo.setPage(bfPage);
		return bfPage.getContent();
	}
	
	public List<FreeReply> findReply(int midx, Pageable pageable, Pageinfo pageinfo){
		Page<FreeReply> frPage = frRepo.findByMidx(midx, pageable);
		pageinfo.setPage(frPage);
		return frPage.getContent();
	}
}
