package com.kim.biz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.kim.biz.member.MemberVO;
import com.kim.biz.member.impl.MemberDAO;

public class MypageController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav=new ModelAndView();
		MemberDAO mdao=new MemberDAO();
		MemberVO vo=new MemberVO();
		HttpSession session=request.getSession();
		session.getAttribute("mvo");
		mdao.selectOneMember(vo);
		mav.setViewName("mypage.jsp");
		mav.addObject("login", mav);
		return mav;
	}

	

}
