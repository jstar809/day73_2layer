package com.kim.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kim.biz.board.BoardVO;
import com.kim.biz.common.JDBCUtil;


@Repository("boardDAO")
public class BoardDAO2 {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	final String sql_selectOne="SELECT * FROM BOARD WHERE BID=?";
	//final String sql_selectAll="SELECT * FROM BOARD ORDER BY BID DESC";
	final String sql_selectAll_TITLE="SELECT * FROM BOARD WHERE TITLE LIKE '%'||?||'%' ORDER BY BID DESC";
	final String sql_selectAll_WRITER="SELECT * FROM BOARD WHERE WRITER LIKE '%'||?||'%' ORDER BY BID DESC";
	
	final String sql_insert="INSERT INTO BOARD(BID,TITLE,WRITER,CONTENT,IMG) VALUES((SELECT NVL(MAX(BID),0)+1 FROM BOARD),?,?,?,?)";
	final String sql_update="UPDATE BOARD SET TITLE=?,CONTENT=? ,IMG=? WHERE BID=?";
	final String sql_delete="DELETE BOARD WHERE BID=?";
	
	public void insertBoard(BoardVO vo) {
		System.out.println("인서트 시작 "+vo.getFileName());
		jdbcTemplate.update(sql_insert, vo.getTitle(),vo.getWriter(),vo.getContent(),vo.getFileName());
	}
	
	public void updateBoard(BoardVO vo) {
		System.out.println("업데이트 파일 이름 "+vo.getFileName());

		jdbcTemplate.update(sql_update, vo.getTitle(),vo.getContent(),vo.getFileName(),vo.getBid());
		//jdbcTemplate.update(sql_update, vo.getTitle(),vo.getContent(),vo.getBid());
	}
	
	public void deleteBoard(BoardVO vo) {
		System.out.println(vo+"딜리트 할때");
		jdbcTemplate.update(sql_delete, vo.getBid());
	}
	
	public BoardVO selectOneBoard(BoardVO vo) {
		Object[] args = {vo.getBid()};
		return jdbcTemplate.queryForObject(sql_selectOne, args, new  BoardRowMapper() );
	}
	
	public List<BoardVO> selectAllBoard(BoardVO vo) {
		System.out.println("검색 할때 또는 메인 갈때 검색어"+vo.getSearchCondition()+"검색 종ㄹ류"+vo.getSearchContent());
		Object[] args = {vo.getSearchContent()};
		System.out.println(args.toString() + "args 상태");
		String sql=sql_selectAll_TITLE;
		if(vo.getSearchCondition()!=null && vo.getSearchCondition().equals("WRITER")) {
			sql=sql_selectAll_WRITER;
			System.out.println("라이터 검색");
		}
		System.out.println("제목 검색");
		List<BoardVO> data=jdbcTemplate.query(sql,args,new BoardRowMapper());
		System.out.println(data);
		return data;
	}
}
class BoardRowMapper implements RowMapper<BoardVO>{

	@Override
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardVO data=new BoardVO();
		data.setBid(rs.getInt("BID"));
		data.setContent(rs.getString("CONTENT"));
		data.setTitle(rs.getString("TITLE"));
		data.setWriter(rs.getString("WRITER"));
		data.setCnt(rs.getInt("CNT"));
		data.setRegdate(rs.getString("REGDATE"));
		data.setFileName(rs.getString("IMG"));
		//System.out.println(data);
		return data;
	}
	
}
