package com.kim.biz.common;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionBean {
	// Mybatis로 DAO 클래스의 CRUD 메서드를 사용하려면,
	// Mybatis에서 제공하는 SqlSession 객체를 사용해야한다!
	//  -> Factory 패턴
	private static SqlSessionFactory sessionFactory=null;
	static {
		try {
			if(sessionFactory==null) {
				// 이곳의 작업은 스트림을 사용합니다.
				// 외부요인으로 인한 에러가 자주 발생하기때문에 예외처리했습니다.
				
				// builder는 Mybatis 설정파일(sql-map-config.xml)을 로딩하면서
				// SSF 객체를 생성함
				
				// 설정파일 로딩을 위해 입력스트림(Reader)을 사용
				Reader reader=Resources.getResourceAsReader("sql-map-config.xml");
				sessionFactory=new SqlSessionFactoryBuilder().build(reader);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSession getSqlSessionInstance() {
		return sessionFactory.openSession();
	}
}
