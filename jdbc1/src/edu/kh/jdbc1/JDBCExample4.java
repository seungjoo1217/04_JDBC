package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc1.model.vo.Emp2;

public class JDBCExample4 {

	public static void main(String[] args) {
		
		// 직급명, 급여를 입력받아
		// 해당 직급에서 입력 받은 급여보다 많이 받는 사원의
		// 이름, 직급명, 급여, 연봉을 조회하여 출력
		
		// 단, 조회 결과가 없으면 "조회 결과 없음" 출력
		
		// 조회 결과가 있으면 아래와 같이 출력
		// 직급명 입력 : 부장
		// 급여 입력 : 3000000
		// 송종기 / 부사장/ 6000000 / 72000000
		// 노옹철 / 부사장 / 3700000 / 44400000
		// ...
		
		// Employee (empName, jobName, salary, annualIncome)
		
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		
		try {
			System.out.print("직급명 입력 : ");
			String input1 = sc.next();
			
			System.out.print("급여 입력 : ");
			int input2 = sc.nextInt();
			
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String type = "jdbc:oracle:thin:@"; // JDBC 드라이버 종류
			String ip = "localhost"; // DB 서버 컴퓨터 IP
			String port = ":1522"; // 포트번호
			String sid = ":XE"; // DB 이름
			String user = "kh_lsj"; // 사용자계정
			String pw = "kh1234"; // 비밀번호
			
			conn = DriverManager.getConnection(type+ip+port+sid, user, pw);
			
			
			String sql = "SELECT EMP_NAME, JOB_NAME, SALARY, SALARY*(1 + NVL(BONUS, 0))*12 AS ANNUALINCOME"
					+ " FROM EMPLOYEE"
					+ " NATURAL JOIN JOB"
					+ " WHERE JOB_NAME = " + "'" + input1 + "'"
					+ " AND SALARY > " + input2;
			
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			List<Emp2> list = new ArrayList<Emp2>();
			
			while(rs.next()) {
				String empName = rs.getString("EMP_NAME");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				int annualIncome = rs.getInt("ANNUALINCOME");
				
				Emp2 emp = new Emp2(empName, jobName, salary, annualIncome);
				
				list.add(emp);
			}
			
			if(list.isEmpty()) {
				System.out.println("조회 결과 없음");
			}else {
				for(Emp2 emp : list) {
					System.out.println(emp);
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
