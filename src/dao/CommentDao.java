package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.NoticeBoardCommentDto;

public class CommentDao {
	//deleteComment 메서드 : 댓글 삭제(rno : 댓글번호)
	//리턴값 : 없음
	public void deleteComment(int rno) {	
		Connection conn =JdbcConn.connect();
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM comments WHERE rno = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,rno);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			 e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//updateComment메서드 : 댓글 수정(content : 내용, rno : 댓글번호)
	//리턴값 : 없음
	public void updateComment(String content, int rno) {
		Connection conn =JdbcConn.connect();
		PreparedStatement pstmt = null;
		String sql = "UPDATE comments" + 
					" SET content =?, writedate = sysdate" + 
					" WHERE rno = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setInt(2,rno);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			 e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//getCommentWritedateByRno메서드 : 날짜 출력(rno : 댓글번호)
	//리턴값 : 날짜 데이터 문자열(예를 들어 "2023-05-10 16:14:30")
	public String getCommentWritedateByRno(int rno) {	
		Connection conn = JdbcConn.connect();
		String retWritedate = "";
		
		String sql = "SELECT writedate FROM comments WHERE rno=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				retWritedate = rs.getString("writedate");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return retWritedate;
	}
	
	//writeComment메서드: 댓글 작성(bno : 글번호, writerId : 작성자, content : 내용)
	// 리턴값 : 없음
	public void writeComment(int bno, String writerId, String content) {	
		Connection conn =JdbcConn.connect();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO comments(rno, bno, writer_id, content, writedate,step,r_order)" + 
					" VALUES(SEQ_rno.nextval, ?, ?, ?, sysdate,0,SEQ_r_order.nextval)" ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,bno);
			pstmt.setString(2, writerId);
			pstmt.setString(3, content);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//writeReply메서드 : 대댓글작성(rno : 대댓글번호, bno : 게시글번호, writerId : 작성자ID, content : 내용)
	// 리턴값 : 없음
	public void writeReply(int rno, int bno, String writerId, String content) {
		Connection conn =JdbcConn.connect();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO comments (rno, bno, writer_id, content, writedate, step, ref, r_order)" + 
					 " VALUES (SEQ_rno.nextval, ?, ?, ?, sysdate, 1, ?, SEQ_r_order.nextval)" ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,bno);
			pstmt.setString(2, writerId);
			pstmt.setString(3, content);
			pstmt.setInt(4,rno);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
