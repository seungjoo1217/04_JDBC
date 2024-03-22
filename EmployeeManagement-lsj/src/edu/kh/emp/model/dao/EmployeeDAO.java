package edu.kh.emp.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import static edu.kh.emp.common.JDBCTemplate.*;
import edu.kh.emp.model.vo.Employee;

public class EmployeeDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public EmployeeDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML( new FileInputStream("query.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public String viewTable(Connection conn) {
		
		try {
			String sql = prop.getProperty("all");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			StringBuilder sb = new StringBuilder();
			
			while(rs.next()) {
				int empId = rs.getInt("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String deptCode = rs.getString("DEPT_CODE");
				String jobCode = rs.getString("JOB_CODE");
				String salLevel = rs.getString("SAL_LEVEL");
				int salary = rs.getInt("SALARY");
				
				String st = String.format("%d / %s / %s / %s / %s / %s / %s / %s / %d\n",
						empId, empName, empNo, email, phone, deptCode, jobCode, salLevel, salary);
				
				sb.append(st);
			}
			
			return sb.toString();
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
			
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public int insert(Connection conn, Employee emp1) {
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("insert");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, emp1.getEmpId());
			pstmt.setString(2, emp1.getEmpName());
			pstmt.setString(3, emp1.getEmpNo());
			pstmt.setString(4, emp1.getEmail());
			pstmt.setString(5, emp1.getPhone());
			pstmt.setString(6, emp1.getDepartmentTitle());
			pstmt.setString(7, emp1.getJobName());
			pstmt.setInt(8, emp1.getSalary());
			
			result = pstmt.executeUpdate();

			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			close(pstmt);
		}
		
		return result;
		
	}
	
	
	
	
	
	
	
	
}
