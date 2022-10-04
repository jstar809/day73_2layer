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
      mvo.setName("김덕배");
      mvo.setRole("admin");
      memberDAO.insertMember(mvo);
      
      System.out.println("방금 추가된 아이디"+memberDAO.selectOne(mvo));
      System.out.println("모든 아이디"+memberDAO.selectAllMember(mvo));
      
      BoardVO vo=new BoardVO();
      vo.setTitle("마이바티스");
      vo.setWriter("작은티모");
      vo.setContent("mybatis");
      boardDAO.insertBoard(vo);
      
      vo.setSearchCondition("TITLE");
      vo.setSearchContent("이");
      List<BoardVO> datas=boardDAO.selectAllBoard(vo);
      for(BoardVO v:datas) {
         System.out.println(v);
      }
      
   }
}