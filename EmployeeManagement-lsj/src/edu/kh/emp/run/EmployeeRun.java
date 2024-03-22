package edu.kh.emp.run;

import static edu.kh.emp.view.EmployeeView.*;

import java.util.Scanner;

import edu.kh.emp.view.EmployeeView;

public class EmployeeRun {
	
	

	public static void main(String[] args) {
		EmployeeView view = new EmployeeView();
		Scanner sc = new Scanner(System.in);
		
		
		try {
			
			int menuNum = 0;
			
			do {
				System.out.println("******Employee Table*****");
				System.out.println("1. 테이블 전체 보기");
				System.out.println("2. 조회하기");
				System.out.println("3. 추가하기");
				System.out.println("4. 수정하기");
				System.out.println("5. 삭제하기");
				System.out.println("0. 종료하기");
				System.out.print("선택 : ");
				menuNum = sc.nextInt();
				
				switch(menuNum) {
				case 1 : view.viewTable(); break;
				case 2 : /*view.search();*/ break;
				case 3 : view.insert(); break;
				case 4 : /*view.alter();*/ break;
				case 5 : /*veiw.delete();*/ break;
				case 0 : System.out.println("[종료]"); break;
				default : System.out.println("잘못 입력하셨습니다");
				}
			} while (menuNum != 0);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
