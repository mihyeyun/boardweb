package com.spring.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.spring.common.JDBCUtil;

@Repository("userDAO")
public class UserDAO {
	
	//회원 로그인
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//sql - 상수 선언
	private final String LOGIN = 
			"SELECT * FROM users WHERE id=? and passwd=?";
	
	//회원 로그인
	public boolean login(UserVO vo) {	
		System.out.println("===> login 처리");
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(LOGIN);
			pstmt.setString(1, vo.getId());		//setId()를 가져옴
			pstmt.setString(2, vo.getPasswd());
			rs = pstmt.executeQuery();
			if(rs.next()) {		//아이디와 비번이 일치하는 자료가 있으면
				return true;	//true 반환
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		} return false;
	}
}
