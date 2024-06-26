package edu.kh.emp.model.service;

import java.sql.Connection;

import static edu.kh.emp.common.JDBCTemplate.*;
import static edu.kh.emp.common.JDBCTemplate.commit;
import static edu.kh.emp.common.JDBCTemplate.rollback;

import edu.kh.emp.model.dao.EmployeeDAO;
import edu.kh.emp.model.vo.Employee;

public class EmployeeService {
	
	private EmployeeDAO dao = new EmployeeDAO();
	private Connection conn = getConnection();
	
	
	public String viewTable() {
		String st = dao.viewTable(conn);
		
		close(conn);
		
		return st;
	}
	
	
	
	public int insert(Employee emp1) {
		int result = dao.insert(conn, emp1);
		
		if(result > 0) commit(conn);
		else 			rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
