package com.kim.biz.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.kim.biz.board.BoardService;
import com.kim.biz.board.BoardVO;
import com.kim.biz.member.MemberVO;
import com.kim.biz.member.impl.MemberDAO;

@Controller
@SessionAttributes("data") // "data"라는 이름의 데이터가 Model 객체에 세팅이된다면, 그것을 session에 기억시키겠다.
public class BoardController {
	
	@Autowired
	private BoardService boardService; // 비즈니스 컴포넌트. DAO를 직접 이용 xxx

	@ModelAttribute("scMap")
	public Map<String,String> searchConditionMap(){
		Map<String,String> scMap=new HashMap<String,String>();
		scMap.put("제목", "TITLE");
		scMap.put("작성자", "WRITER");
		return scMap;
	}
	
	@RequestMapping("/main.do")
	public String main(HttpSession session,MemberVO mVO,MemberDAO mDAO,BoardVO bVO,Model model) {
		//검색조건이 null 인지 아닌지 체크하는 로직
//		if(bVO.getSearchCondition()==null) {
//			bVO.setSearchCondition("TITLE");
//		}
//		if(bVO.getSearchContent(bVO))
		
		
		
		List<BoardVO> datas=boardService.selectAllBoard(bVO);
		model.addAttribute("datas", datas);

		mVO=(MemberVO)session.getAttribute("user");
		mVO=mDAO.selectOneMember(mVO);
		model.addAttribute("userName", mVO.getName());
		
		return "main.jsp";
	}
	
	@RequestMapping("/board.do")
	public String board(BoardVO bVO,Model model) {
		bVO=boardService.selectOneBoard(bVO);

		model.addAttribute("data", bVO);
		return "board.jsp";
	}
	
	@RequestMapping("/update.do")
	public String update(@ModelAttribute("data")BoardVO bVO) {
		System.out.println("update.do 로그: "+bVO); // @SA,@MA 덕분에 writer 등의 값도 로그에 출력된다.
	
		MultipartFile uploadFile=bVO.getUploadFile();
	     System.out.println(uploadFile);
		
	     if(!uploadFile.isEmpty()) { // 업로드한 파일 존재여부 확인
	         String fileName=uploadFile.getOriginalFilename(); // 업로드한 파일명
	         bVO.setFileName(fileName);// 파일 네임
	         try {
				uploadFile.transferTo(new File("C:\\Users\\Leejunseon\\git\\day73_2layer\\day6801\\src\\main\\webapp\\images\\"+fileName));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         // 업로드한 파일을 지정한 경로에 저장
	      }
	    System.out.println("인서트 하기전에 vo"+bVO);
		
		boardService.updateBoard(bVO);
		return "redirect:main.do";
	}
	@RequestMapping("/delete.do")
	public String delete(BoardVO bVO) {
		boardService.deleteBoard(bVO);
		return "redirect:main.do";
	}
	@RequestMapping("/insert.do")
	public String insertBoard(BoardVO vo){
		System.out.println("글 추가 할때 vo"+vo);
		MultipartFile uploadFile=vo.getUploadFile();
	      if(!uploadFile.isEmpty()) { // 업로드한 파일 존재여부 확인
	         String fileName=uploadFile.getOriginalFilename(); // 업로드한 파일명
	         System.out.println("인서트 할떄 파일이름 "+fileName);
	         vo.setFileName(fileName);// 파일 네임
	         try {
				uploadFile.transferTo(new File("C:\\Users\\Leejunseon\\git\\day73_2layer\\day6801\\src\\main\\webapp\\images\\"+fileName));
			} catch (Exception e) {
				System.out.println("너 에러난거야");
				e.printStackTrace();
			} 
	         // 업로드한 파일을 지정한 경로에 저장
	      }
	    System.out.println("인서트 하기전에 vo"+vo);
		boardService.insertBoard(vo);
		return "main.do";
	}

	/*
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		BoardVO bVO=new BoardVO();
		bVO.setBid(Integer.parseInt(request.getParameter("bid")));
		
		BoardDAO bDAO=new BoardDAO();
		bVO=bDAO.selectOneBoard(bVO);
		
		HttpSession session=request.getSession();
		session.setAttribute("data", bVO);
	
		return "board";
	}
	 */
	
}
