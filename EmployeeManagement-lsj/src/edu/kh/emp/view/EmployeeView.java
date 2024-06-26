package edu.kh.emp.view;


import java.util.Scanner;

import edu.kh.emp.model.service.EmployeeService;
import edu.kh.emp.model.vo.Employee;

public class EmployeeView {
	private EmployeeService service = new EmployeeService();
	private Scanner sc = new Scanner(System.in);
	
	
	
	
	
	

	public void viewTable() {
		String st =  service.viewTable();
		
		System.out.println(st);
	}
	
	
	
	public void insert() {
		System.out.print("아이디를 입력하세요 : ");
		int empId = sc.nextInt();
		
		System.out.print("이름을 입력하세요 : ");
		String empName = sc.next();
		
		System.out.print("주민번호 입력하세요 : ");
		String empNo = sc.next();
		
		System.out.print("이메일을 입력하세요 : ");
		String email = sc.next();
		
		System.out.print("전화번호를 입력하세요 : ");
		String phone = sc.next();
		
		System.out.print("부서명을 입력하세요 : ");
		String deptTitle = sc.next();
		
		System.out.print("직업명을 입력하세요 : ");
		String jobName = sc.next();
		
		System.out.print("급여를 입력하세요 : ");
		int salary = sc.nextInt();
		
		Employee emp1 = new Employee(empId, empName, empNo, email, phone, deptTitle, jobName, salary);
		
		int result = service.insert(emp1);
		
		if(result > 0) System.out.println("추가 성공");
		else		   System.out.println("추가 실패");
		
	}
	

}
