package test;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.kim.biz.board.BoardService;
import com.kim.biz.board.BoardVO;
import com.kim.biz.member.MemberService;
import com.kim.biz.member.MemberVO;

public class Client {
	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
//		BoardService bs =(BoardService) factory.getBean("boardService");
//		
//		BoardVO vo = new BoardVO();
//		Scanner sc = new Scanner(System.in);
//		System.out.println("���� �ۼ� >> ");
//		String msg = sc.nextLine();
//		vo.setContent(msg);
//		vo.setTitle("�� ����");
//		vo.setWriter("���� Ƽ��");
//		bs.insertBoard(vo);
//		
//		List<BoardVO> datas = bs.selectAllBoard(vo);
//		for(BoardVO v:datas) {
//			System.out.println(v);
//		}
	
		MemberService ms= (MemberService)factory.getBean("memberService"); //lookup == �޸𸮿��� ��ü�� ã�� ��û
		MemberVO mvo = new MemberVO();
		Scanner sc = new Scanner(System.in);
		System.out.println("���̵� �Է� :");
		String mid = sc.next();
		System.out.println("��й�ȣ �Է� :");
		String mpw = sc.next();
		
		mvo.setMid(mid);
		mvo.setMpw(mpw);
		mvo=ms.selectOneMember(mvo);
		if(mvo==null) {
			System.out.println("�α��� ����");
		}
		else {
			System.out.println(mvo.getName()+"��, �ݰ����ϴ�");
		}
		factory.close();
	}

}
