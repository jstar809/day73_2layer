package com.kim.biz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.kim.biz.member.MemberVO;
import com.kim.biz.member.impl.MemberDAO;
import com.kim.biz.member.impl.MemberDAO2;

public class SignController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberVO vo =new MemberVO();
		MemberDAO dao =new MemberDAO();
		vo.setMid(request.getParameter("mid"));
		vo.setMpw(request.getParameter("mpw"));
		vo.setName(request.getParameter("name"));
		vo.setRole(request.getParameter("role"));
		System.out.println(vo);
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:login.do");
		return mav;
	}

//	@Override
//	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
//		MemberVO vo =new MemberVO();
//		MemberDAO dao =new MemberDAO();
//		vo.setMid(request.getParameter("mid"));
//		vo.setMpw(request.getParameter("mpw"));
//		vo.setName(request.getParameter("name"));
//		vo.setRole(request.getParameter("role"));
//		System.out.println(vo);
//		
//		dao.insertMember(vo);
//		
//		
//	
//		return "login";
//	}

}
