package edu.kh.jdbc.member.model.sevice;

import java.sql.Connection;
import java.util.List;
import java.util.Random;

import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.member.model.dao.MemberDAO;
import edu.kh.jdbc.member.model.dto.Member;

public class MemberService {

	private MemberDAO dao = new MemberDAO();

	/** 회원 목록 조회(아이디, 이름, 성별)
	 * @return
	 * @throws Exception 
	 */
	public List<Member> selectMemberList() throws Exception {

		Connection conn = getConnection();
		
		List<Member> memberList = dao.selectMemberList(conn);
		
		close(conn);
		
		return memberList;
	}

	/** 내 정보 수정(이름, 성별, (현재 로그인한 회원번호))
	 * @param updateName
	 * @param updateGender
	 * @param memberNo
	 * @return
	 * @throws Exception 
	 */
	public int updateMember(String memberName, String memberGender, int memberNo) throws Exception {

		Connection conn = getConnection();
		
		int result = dao.updateMember(conn, memberName, memberGender, memberNo);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		
		return result;
	}

	/** 비밀번호 변경(현재 비밀번호, 새 비밀번호, 새 비밀번호 확인)
	 * @param newPw
	 * @return
	 * @throws Exception 
	 */
	public int updatePassword(String pw, String newPw, int MemberNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.updatePassword(conn, pw, newPw, MemberNo);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 숫자 6자리 보안코드 생성 서비스
	 * @return code
	 */
	public String createSecurityCode() {
		
		StringBuffer code = new StringBuffer();
		
		
		Random ran = new Random(); // 난수 생성 객체
		
		for(int i=0; i<6; i++) {
			int x = ran.nextInt(10); // 0이상 10미만 정수
			code.append(x); // StringBuffer 마지막에 생성된 난수 x를 이어붙임
		}
	
		return code.toString();
	}

	/** 회원 탈퇴 서비스
	 * @param memberPw
	 * @param memberNo
	 * @return result
	 * @throws Exception 
	 */
	public int unRegisterMember(String memberPw, int memberNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.unRegisterMember(conn, memberPw, memberNo);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
