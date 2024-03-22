package edu.kh.jdbc.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.member.model.dto.Member;

import static edu.kh.jdbc.common.JDBCTemplate.*;

public class MemberDAO {

	// JDBC 객체 참조 변수
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	// 기본생성자 member-sql.xml 읽어오고 prop 저장
	public MemberDAO() {
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("member-sql.xml"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 회원 목록 조회(아이디, 이름, 성별)
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public List<Member> selectMemberList(Connection conn) throws Exception {
		List<Member> memberList = new ArrayList<Member>();
		
		try {
			
			String sql = prop.getProperty("selectMemberList");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String memberId = rs.getString("MEMBER_ID");
				String memberName = rs.getString("MEMBER_NM");
				String memberGender = rs.getString("성별");
				
				// 컬럼 값을 Member 객체에 저장
				Member member = new Member();
				member.setMemberId(memberId);
				member.setMemberName(memberName);
				member.setMemberGender(memberGender);
				
				// Member 객체를 List에 추가
				memberList.add(member);
				
			}
			
			
		} finally {
			close(rs);
			close(stmt);
		}
		
		return memberList;
	}

	/** 내 정보 수정(이름, 성별, (현재 로그인한 회원번호))
	 * @param updateName
	 * @param updateGender
	 * @param memberNo
	 * @return
	 */
	public int updateMember(Connection conn, String memberName,
							String memberGender, int memberNo) throws Exception{
		
		// 1. 결과 저장용 변수 선언
		int result = 0;
		
		try {
			// 2. SQL 작성, 수행
			String sql = prop.getProperty("updateMember");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberName);
			pstmt.setString(2, memberGender);
			pstmt.setInt(3, memberNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			// 3. JDBC 객체 자원 반환
			close(pstmt);
		}
		
		// 4, 결과 반환
		return result;
	}

	/** 비밀번호 변경(현재 비밀번호, 새 비밀번호, 새 비밀번호 확인)
	 * @param conn
	 * @param newPw
	 * @return
	 */
	public int updatePassword(Connection conn, String pw, String newPw, int MemberNo) throws Exception{
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("updatePassword");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPw);
			pstmt.setString(2, pw);
			pstmt.setInt(3, MemberNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 회원 탈퇴 SQL 수행 DAO
	 * @param conn
	 * @param memberPw
	 * @param memberNo
	 * @return result
	 */
	public int unRegisterMember(Connection conn, String memberPw, int memberNo) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("unRegisterMember");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, memberPw);
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			close(pstmt);
		}
		
		
		
		return result;
	}
	
	
	
	
	
	
	
	
	
}
