package com.kim.biz.member.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kim.biz.member.MemberVO;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

@Repository("MemberDAO") //모델의 역할을 하는 것이라 알려주는 어노테이션 - 이때 이 @은 id 역할임
public class MemberDAO2 {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	final String sql_selectOne="SELECT * FROM MEMBER WHERE MID=? AND MPW=?";
	final String sql_selectAll="SELECT * FROM MEMBER";
	final String sql_insert="INSERT INTO MEMBER(MID, MPW, NAME, ROLE) VALUES(?,?,?,?)";
	final String sql_delete="DELETE FROM MEMBER WHERE MID=? AND MPW=?";
	final String sql_update="UPDATE MEMBER SET MPW=? ,NAME=? WHERE MID=?";
	
	
	
	public void insertMember(MemberVO vo) {
		System.out.println("dao2"+vo);
		Object[] args = {vo.getMid(),vo.getMpw(), vo.getName(), vo.getRole()};
		jdbcTemplate.update(sql_insert, args);
	}
	
	public void updateMember(MemberVO vo) {
		jdbcTemplate.update(sql_update, vo.getMpw(), vo.getName(), vo.getMid());
	}
	
	public void deleteMember(MemberVO vo) {
		jdbcTemplate.update(sql_delete, vo.getMid(), vo.getMpw());
	}
	
	public MemberVO selectOneMember(MemberVO vo) {
		System.out.println("바뀐 멤버 메서드 사용 나와야함 수정");
		Object[] args = {vo.getMid(), vo.getMpw()};
		try {
			MemberVO user=jdbcTemplate.queryForObject(sql_selectOne, args, new MemberRowMapper());
			return user;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		//return jdbcTemplate.queryForObject(sql_selectOne, args, new MemberRowMapper());
	}
	
	public List<MemberVO> selectAllMember(MemberVO vo){
		return jdbcTemplate.query(sql_selectAll, new MemberRowMapper());
	}
}

class MemberRowMapper implements RowMapper<MemberVO>{
	@Override
	public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberVO data=new MemberVO();
		data.setMid(rs.getString("MID"));
		data.setMpw(rs.getString("MPW"));
		data.setName(rs.getString("NAME"));
		data.setRole(rs.getString("ROLE"));
		return data;
	}
}





