package edu.kh.jdbc.phone.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.phone.model.dto.Phone;

public class PhoneDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public PhoneDAO() {
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("phone-sql.xml"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 전체 전화번호 표시 SQL 생성 DAO
	 * @param conn
	 * @return phoneList
	 * @throws Exception
	 */
	public List<Phone> viewPhone(Connection conn) throws Exception{
		List<Phone> phoneList = new ArrayList<Phone>();
		Phone phone;
		try {
			String sql = prop.getProperty("viewPhone");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				phone = new Phone();
				
				phone.setPhoneNo(rs.getString(1));
				phone.setName(rs.getString(2));
				phone.setFavorit(rs.getString(3).charAt(0));
				
				phoneList.add(phone);
			}
			
		} finally {
			close(rs);
			close(stmt);
		}
		
		return phoneList;
	}

	/** 전화번호 추가 SQL 생성 DAO
	 * @param conn
	 * @param inputPhone
	 * @param inputName
	 * @return reuslt
	 */
	public int addPhone(Connection conn, String inputPhone, String inputName) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("addPhone");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inputPhone);
			pstmt.setString(2, inputName);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 정보 수정 SQL 생성 DAO
	 * @param conn
	 * @param input
	 * @return result
	 */
	public int updatePhone(Connection conn, String inputName, String inputPhoneNo, String inputNewName, String inputFavorit) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("updatePhone");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inputPhoneNo);
			pstmt.setString(2, inputNewName);
			pstmt.setString(3, inputFavorit);
			pstmt.setString(4, inputName);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 전화번호 삭제 SQL 생성 DAO
	 * @param conn
	 * @param inputName
	 * @return result
	 */
	public int deletePhone(Connection conn, String inputName) throws Exception{
		int result = 0;
		
		try {
			String sql = prop.getProperty("deletePhone");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inputName);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
