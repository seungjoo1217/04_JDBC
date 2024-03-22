package edu.kh.jdbc.phone.model.service;

import java.sql.Connection;
import java.util.List;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import edu.kh.jdbc.phone.model.dao.PhoneDAO;
import edu.kh.jdbc.phone.model.dto.Phone;

public class PhoneService {
	private PhoneDAO dao = new PhoneDAO();

	/** 전체 전화번호부 표시 서비스
	 * @return phoneList
	 * @throws Exception 
	 */
	public List<Phone> viewPhone() throws Exception {

		Connection conn = getConnection();
		
		List<Phone> phoneList = dao.viewPhone(conn);
		
		close(conn);
		
		return phoneList;
	}

	/** 전화번호 추가 서비스
	 * @param inputPhone
	 * @param inputName
	 * @return result
	 * @throws Exception 
	 */
	public int addPhone(String inputPhone, String inputName) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.addPhone(conn, inputPhone, inputName);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 전화번호 수정 서비스
	 * @param input
	 * @return result
	 * @throws Exception 
	 */
	public int updatePhone(String inputName, String inputPhoneNo, String inputNewName, String inputFavorit) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.updatePhone(conn, inputName, inputPhoneNo, inputNewName, inputFavorit);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 전화번호 삭제 서비스
	 * @param inputName
	 * @return result
	 * @throws Exception 
	 */
	public int deletePhone(String inputName) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.deletePhone(conn, inputName);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
