package com.kim.biz.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.kim.biz.member.MemberService;
import com.kim.biz.member.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	
	@Autowired  // 타입으로 찾는 것이기 때문에 MemberDAO 타입의 객체가 메모리에 있어야지만 DI(의존성 주입)이 필요하다
	private MemberDAO2 memberDAO;	//핵심 로직을 수행할 객체
			
	
	@Override
	public void insertMember(MemberVO vo) {
		// TODO Auto-generated method stub
		memberDAO.insertMember(vo);
	}

	@Override
	public void updateMember(MemberVO vo) {
		// TODO Auto-generated method stub
		memberDAO.updateMember(vo);
	}

	@Override
	public void deleteMember(MemberVO vo) {
		// TODO Auto-generated method stub
		memberDAO.deleteMember(vo);
	}

	@Override
	public MemberVO selectOneMember(MemberVO vo) {
		if(vo.getMid().equals("timo")) {
			throw new IllegalArgumentException("티모 실행시 예외 ");
		}
		return memberDAO.selectOneMember(vo);
	}

	@Override
	public List<MemberVO> selectAllMember(MemberVO vo) {
		// TODO Auto-generated method stub
		return memberDAO.selectAllMember(vo);
	}

}
