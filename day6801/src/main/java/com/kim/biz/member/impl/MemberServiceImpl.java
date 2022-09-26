package com.kim.biz.member.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.kim.biz.member.MemberService;
import com.kim.biz.member.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	
	@Autowired  // Ÿ������ ã�� ���̱� ������ MemberDAO Ÿ���� ��ü�� �޸𸮿� �־������ DI(������ ����)�� �ʿ��ϴ�
	private MemberDAO2 memberDAO;	//�ٽ� ������ ������ ��ü
			
	
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
			throw new IllegalArgumentException("Ƽ�� ����� ���� ");
		}
		return memberDAO.selectOneMember(vo);
	}

	@Override
	public List<MemberVO> selectAllMember(MemberVO vo) {
		// TODO Auto-generated method stub
		return memberDAO.selectAllMember(vo);
	}

}
