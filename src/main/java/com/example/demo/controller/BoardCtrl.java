package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.BoardFree;
import com.example.demo.entity.FreeReply;
import com.example.demo.entity.Member;
import com.example.demo.service.BoardSvc;
import com.example.demo.vo.Pageinfo;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/free")
public class BoardCtrl{
	private final BoardSvc bSvc;
	
	@RequestMapping("")
	public String getBoardFreeList(
			@PageableDefault(size=10, sort="date", direction = Sort.Direction.DESC) Pageable pageable,
			Model model
			) {
		Pageinfo pageInfo = new Pageinfo();
		List<BoardFree> bfList = bSvc.getFreeList(pageable, pageInfo);
		model.addAttribute("pageinfo", pageInfo);
		model.addAttribute("bfList", bfList);
		return "board/free";
	}
	
	@RequestMapping("/view")
	public String getBoardFree(
			@RequestParam("idx") String idx,
			Model model,
			HttpServletRequest request
			) {
		HttpSession session = request.getSession();
		if(session.getAttribute("logininfo")==null) {
			return "redirect:/index";
		}
		BoardFree bf = bSvc.getBoardFree(Integer.parseInt(idx));
		List<FreeReply> frList = bSvc.getFreeReply(Integer.parseInt(idx));
		model.addAttribute("boardfree", bf);
		model.addAttribute("frList", frList);
		return "board/view";
	}
	
	@RequestMapping(value = {"/write"})
	public String boardFreeForm(
			HttpServletRequest request
			) {
		HttpSession session = request.getSession();
		if(session.getAttribute("logininfo")==null) {
			return "redirect:/index";
		}
		return "board/form";
	}
	
	@RequestMapping(value = {"/write_proc"})
	public String boardFreeProc(
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			HttpServletRequest request
			) {
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("logininfo");
		bSvc.setBoardFree(m.getIdx(), m.getId(), title, content);
		return "redirect:/free";
	}
	
	@RequestMapping(value = {"/update_form"})
	public String boardFreeUpForm(
			HttpServletRequest request,
			Model model,
			RedirectAttributes ra,
			@RequestParam("idx") String idx
			) {
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("logininfo");
		BoardFree bf = bSvc.getBoardFree(Integer.parseInt(idx));
		if(m==null) {
			return "redirect:/index";
		}else if(m.getIdx() != bf.getMidx()) {
			ra.addAttribute("alert", "권한이 없습니다");
			return "redirect:/free";
		}
		model.addAttribute("bf", bSvc.getBoardFree(Integer.parseInt(idx)));
		return "board/update";
	}
	
	@RequestMapping(value = {"/update_proc"})
	public String boardFreeUpProc(
			@RequestParam("bidx") String bidx,
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			RedirectAttributes ra
			) {
		bSvc.upBoardFree(Integer.parseInt(bidx), title, content);
		ra.addAttribute("idx", bidx);
		return "redirect:/free/view";
	}
	
	@RequestMapping(value = {"/del"})
	public String boardFreeDelete(
			@RequestParam("bidx") String bidx,
			HttpServletRequest request,
			RedirectAttributes ra
			) {
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("logininfo");
		BoardFree bf = bSvc.getBoardFree(Integer.parseInt(bidx));
		if(m==null) {
			return "redirect:/index";
		}else if(m.getIdx() != bf.getMidx()) {
			ra.addAttribute("alert", "권한이 없습니다");
			return "redirect:/free";
		}
		bSvc.deleteBoardFree(Integer.parseInt(bidx));
		return "redirect:/free";
	}
	
	@RequestMapping(value = {"/reply"})
	public String boardFreeReply(
			RedirectAttributes ra,
			@RequestParam("bidx") String bidx,
			@RequestParam("content") String content,
			HttpServletRequest request
			) {
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("logininfo");
		bSvc.replyBoardFree(m.getIdx(), Integer.parseInt(bidx), m.getId(), content);
		ra.addAttribute("idx", bidx);
		return "redirect:/free/view";
	}
	
	@RequestMapping(value = {"/reply_del"})
	public String replyDel(
			@RequestParam("bidx") String bidx,
			@RequestParam("ridx") String ridx,
			RedirectAttributes ra,
			HttpServletRequest request
			) {
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("logininfo");
		if(m==null) {
			return "redirect:/index";
		}
		bSvc.deleteReply(Integer.parseInt(ridx), m.getIdx());
		ra.addAttribute("idx", bidx);
		return "redirect:/free/view";
	}

}
