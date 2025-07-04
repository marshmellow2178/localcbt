package com.marshmellow.localcbt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.marshmellow.localcbt.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.marshmellow.localcbt.entity.Admin;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = {"/admin"})
public class UserCtrl{

	@RequestMapping(value = {"/login"})
	public String login( //어떻게 수정하지...
			@RequestParam("id") String id,
			@RequestParam("pw") String pw,
			HttpServletRequest request,
			RedirectAttributes ra
			) {
		id = id.trim().toLowerCase();
		pw = pw.trim().toLowerCase();
		HttpSession session = request.getSession();
		if(session.getAttribute("admininfo")==null) { 
			User a = userService.login(id, pw);
			if(a == null ) {
				ra.addAttribute("error", "회원정보가 일치하지 않습니다");
			}
			session.setAttribute("admininfo", userService.login(id, pw));
		}
		return "redirect:/admin";
	}
	

}
