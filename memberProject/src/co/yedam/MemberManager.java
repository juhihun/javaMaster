package co.yedam;

import java.text.SimpleDateFormat;
import java.util.*;

public class MemberManager {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		MemDAO dao = new MemDAO();
		
		boolean run = true;
		while(run) {
			System.out.println("1.회원목록 2.회원등록 3.정보수정 4.정보삭제 5.상세화면 6.종료");
			System.out.print("선택 > ");
			int ch = sc.nextInt();
			sc.nextLine();
			switch(ch) {
			case 1 :
				List<Member> member = dao.memList();
				System.out.println("회원번호 회원명	 회원연락처 		회원생일 성별");
				System.out.println("----------------------------------------------");
				for(Member mem : member) {
					System.out.println(mem.toString());
				}
				break;
			case 2 : 
				System.out.println("회원명 > ");
				String name = sc.nextLine();
				
				System.out.print("회원연락처 > ");
				String ph = sc.nextLine();
				
				System.out.print("회원생일 > ");
				String bd = sc.nextLine();
				
				System.out.print("성별 > ");
				String gen = sc.nextLine();
				
				Member mem = new Member();
				mem.setName(name);
				mem.setPhone(ph);
				mem.setBirthday(bd);
				mem.setGender(gen);
				
				if(dao.insertMem(mem)) {
					System.out.println("정상등록");
				}else {
					System.out.println("예외발생");
				}
				break;
			
			case 3 : 
				System.out.println("수정할 회원번호 > ");
				int up = Integer.parseInt(sc.nextLine());
				System.out.println("전화번호 > ");
				String pn = sc.nextLine();
				
				mem = new Member();
				mem.setMemNo(up);
				mem.setPhone(pn);
				
				if(dao.updateMem(mem)) {
					System.out.println("수정완료");
				}else {
					System.out.println("처리실패");
				}
				break;
			case 4 : 
				System.out.println("삭제할 회원번호");
				int del = sc.nextInt();
				sc.nextLine();
				if(dao.deleteMem(del)) {
					System.out.println("정상처리");
				}else {
					System.out.println("삭제실패");
				}
				break;
			case 5 : 
				System.out.println("회원번호" );
			case 6 : 
				System.out.println("종료");
				run = false;
			}
		}

	}

}
