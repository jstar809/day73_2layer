package com.kim.biz.member.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kim.biz.common.SqlSessionBean;
import com.kim.biz.member.MemberVO;

public class MemberDAO3 {
	private SqlSession mybatis;
	   public MemberDAO3() {
	      mybatis=SqlSessionBean.getSqlSessionInstance();
	   }
	   
	   public void insertMember(MemberVO vo) {
		   System.out.println("안나오면 dao3 에서 문제 발생 VO" +vo);
		   mybatis.insert("memberDAO.insertMember", vo);
		   mybatis.commit();
	   }
	   public void deleteMember(MemberVO vo) {
		   mybatis.delete("memberDAO.deleteMember", vo);
		   mybatis.commit();
	   }
	   public void updateMember(MemberVO vo) {
		   mybatis.update("memberDAO.updateMember", vo);
		   mybatis.commit();
	   }
	   public MemberVO selectOne(MemberVO vo) {
		   return mybatis.selectOne("memberDAO.selectOneMember", vo);
	   }
	   
	   public List<MemberVO> selectAllMember(MemberVO vo){
		   return mybatis.selectList("memberDAO.selectAllMember",vo);
	   }
}
