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
@SessionAttributes("data") // "data"��� �̸��� �����Ͱ� Model ��ü�� �����̵ȴٸ�, �װ��� session�� ����Ű�ڴ�.
public class BoardController {
	
	@Autowired
	private BoardService boardService; // ����Ͻ� ������Ʈ. DAO�� ���� �̿� xxx

	@ModelAttribute("scMap")
	public Map<String,String> searchConditionMap(){
		Map<String,String> scMap=new HashMap<String,String>();
		scMap.put("����", "TITLE");
		scMap.put("�ۼ���", "WRITER");
		return scMap;
	}
	
	@RequestMapping("/main.do")
	public String main(HttpSession session,MemberVO mVO,MemberDAO mDAO,BoardVO bVO,Model model) {
		//�˻������� null ���� �ƴ��� üũ�ϴ� ����
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
		System.out.println("update.do �α�: "+bVO); // @SA,@MA ���п� writer ���� ���� �α׿� ��µȴ�.
	
		MultipartFile uploadFile=bVO.getUploadFile();
	     System.out.println(uploadFile);
		
	     if(!uploadFile.isEmpty()) { // ���ε��� ���� ���翩�� Ȯ��
	         String fileName=uploadFile.getOriginalFilename(); // ���ε��� ���ϸ�
	         bVO.setFileName(fileName);// ���� ����
	         try {
				uploadFile.transferTo(new File("C:\\Users\\Leejunseon\\git\\day73_2layer\\day6801\\src\\main\\webapp\\images\\"+fileName));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         // ���ε��� ������ ������ ��ο� ����
	      }
	    System.out.println("�μ�Ʈ �ϱ����� vo"+bVO);
		
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
		System.out.println("�� �߰� �Ҷ� vo"+vo);
		MultipartFile uploadFile=vo.getUploadFile();
	      if(!uploadFile.isEmpty()) { // ���ε��� ���� ���翩�� Ȯ��
	         String fileName=uploadFile.getOriginalFilename(); // ���ε��� ���ϸ�
	         System.out.println("�μ�Ʈ �ҋ� �����̸� "+fileName);
	         vo.setFileName(fileName);// ���� ����
	         try {
				uploadFile.transferTo(new File("C:\\Users\\Leejunseon\\git\\day73_2layer\\day6801\\src\\main\\webapp\\images\\"+fileName));
			} catch (Exception e) {
				System.out.println("�� �������ž�");
				e.printStackTrace();
			} 
	         // ���ε��� ������ ������ ��ο� ����
	      }
	    System.out.println("�μ�Ʈ �ϱ����� vo"+vo);
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
