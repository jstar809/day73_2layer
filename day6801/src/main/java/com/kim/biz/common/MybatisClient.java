package com.kim.biz.common;

import java.util.List;

import com.kim.biz.board.BoardVO;
import com.kim.biz.board.impl.BoardDAO3;
import com.kim.biz.member.MemberVO;
import com.kim.biz.member.impl.MemberDAO;
import com.kim.biz.member.impl.MemberDAO3;

public class MybatisClient {
   public static void main(String[] args) {

      BoardDAO3 boardDAO=new BoardDAO3();
      MemberDAO3 memberDAO=new MemberDAO3();
      
      //my test
      MemberVO mvo=new MemberVO();
      mvo.setMid("duckbae4");
      mvo.setMpw("1234");
      mvo.setName("�����");
      mvo.setRole("admin");
      memberDAO.insertMember(mvo);
      
      System.out.println("��� �߰��� ���̵�"+memberDAO.selectOne(mvo));
      System.out.println("��� ���̵�"+memberDAO.selectAllMember(mvo));
      
      BoardVO vo=new BoardVO();
      vo.setTitle("���̹�Ƽ��");
      vo.setWriter("����Ƽ��");
      vo.setContent("mybatis");
      boardDAO.insertBoard(vo);
      
      vo.setSearchCondition("TITLE");
      vo.setSearchContent("��");
      List<BoardVO> datas=boardDAO.selectAllBoard(vo);
      for(BoardVO v:datas) {
         System.out.println(v);
      }
      
   }
}