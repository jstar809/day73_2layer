package com.kim.biz.common;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionBean {
	// Mybatis�� DAO Ŭ������ CRUD �޼��带 ����Ϸ���,
	// Mybatis���� �����ϴ� SqlSession ��ü�� ����ؾ��Ѵ�!
	//  -> Factory ����
	private static SqlSessionFactory sessionFactory=null;
	static {
		try {
			if(sessionFactory==null) {
				// �̰��� �۾��� ��Ʈ���� ����մϴ�.
				// �ܺο������� ���� ������ ���� �߻��ϱ⶧���� ����ó���߽��ϴ�.
				
				// builder�� Mybatis ��������(sql-map-config.xml)�� �ε��ϸ鼭
				// SSF ��ü�� ������
				
				// �������� �ε��� ���� �Է½�Ʈ��(Reader)�� ���
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
