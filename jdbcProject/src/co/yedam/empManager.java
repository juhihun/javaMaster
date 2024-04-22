package co.yedam;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class empManager {

	public static void main(String[] args) {
		//scanner클래스
		Scanner scn = new Scanner(System.in);
		//반복문
		boolean run = true;
		EmpDAO dao = new EmpDAO();
		while(run) {
			System.out.println("1.사원목록 2.사원등록 3.정보수정 4.사원삭제 5.종료");
			System.out.print("선택 > ");
			int menu = Integer.parseInt(scn.nextLine());
			
			switch(menu) {
			case 1 : 
				List<Employee> emps = dao.empList();
				//타이틀
				System.out.println("사번       이름        이메일    급여");
				System.out.println("------------------------------------");
				for(Employee emp : emps) {
					System.out.println(emp.showInfo());
					
				}
				break;
			case 2 : 
				System.out.print("사원명>> ");
				String name = scn.nextLine();
				System.out.print("연락처>> ");
				String phone = scn.nextLine();
				System.out.print("이메일>> ");
				String mail = scn.nextLine();
				System.out.print("급여>> ");
				String salary = scn.nextLine();
				System.out.print("입사일자>> ");
				String hdate = scn.nextLine(); //2024-05-02
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				
				Employee emp = new Employee();
				emp.setEmpName(name);
				emp.setEmail(mail);
				emp.setPhone(phone);
				emp.setSalary(Integer.parseInt(salary));
				emp.setHireDate(hdate);
				
				
				if(dao.insertEmp(emp)) {
					System.out.println("정상등록");
				}else {
					System.out.println("예외발생");
				}
				break;
			case 3 : 
				System.out.print("사원번호>> ");
				String eno = scn.nextLine();
				System.out.print("이메일>> ");
				mail = scn.nextLine();
				System.out.print("급여>> ");
				salary = scn.nextLine();
				
				emp = new Employee();
				emp.setEmpNo(Integer.parseInt(eno));
				emp.setEmail(mail);
				emp.setSalary(Integer.parseInt(salary));
				
				if(dao.updateEmp(emp)) {
					System.out.println("수정완료");
				}else {
					System.out.println("처리실패");
				}
				break;
			case 4 : 
				System.out.print("삭제할 사원번호");
				int del = scn.nextInt();
				scn.nextLine();
				if(dao.deleteEmp(del)) {
					System.out.println("정상처리");					
				}else {
					System.out.println("삭제실패");										
				}
				break;
			case 5 : 
				System.out.println("종료.....");
				run = false;
			}
	
		}
		System.out.println("end of prog");
	}

}
