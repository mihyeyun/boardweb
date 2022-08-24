package com.spring.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.spring.common.JDBCUtil;

//DAO(Data Access Object)
@Repository("boardDAO")
public class BoardDAO {
	
	//jdbc ���� ���� ����
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//sql ���� ����� ����
	private final String BOARD_INSERT =
			"INSERT INTO board(bno, title, writer, content) "
			+ "VALUES (sqe.nextval, ?, ?, ?)";
	private final String BOARD_LIST =
		"SELECT * FROM board";
	
	//�Խñ� ���
	public void insertBoard(BoardVO vo) {
		System.out.println("==> insertBoard() ó��");
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_INSERT);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	public ArrayList<BoardVO> getBoardList(){
	System.out.println("==> getBoardList() ó��");
	ArrayList<BoardVO> boardList = new ArrayList<>();
	try {
		conn = JDBCUtil.getConnection();
		pstmt = conn.prepareStatement(BOARD_LIST);
		rs = pstmt.executeQuery();
		while(rs.next()) {	//��ȯ �ڷᰡ �ִ� ���� 
			BoardVO vo = new BoardVO();
			vo.setBno(rs.getInt("bno"));	//db Į���� �����ͼ� ��ü�� ����
			vo.setTitle(rs.getString("title"));
			vo.setWriter(rs.getString("writer"));
			vo.setContent(rs.getString("content"));
			vo.setRegDate(rs.getDate("regdate"));
			vo.setCnt(rs.getInt("cnt"));
			
			boardList.add(vo);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		JDBCUtil.close(conn, pstmt, rs);
	}
	return boardList;
	}
}