package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.BoardListDto;
import dto.NoticeBoardCommentDto;

public class BoardDao {
	//boardWriteInsert메서드 : (사진첨부x)글쓰기(title : 제목, content : 내용, loginId : 아이디)
	//리턴값 : 없음
	public void boardWriteInsert(String title, String content, String loginId) { 
		Connection conn =JdbcConn.connect();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO board_list (bno, title, content, writer_id, hitcount, writedate, nickname) " + 
				" VALUES (SEQ_BNO.nextval, ?, ?, ?, 0, sysdate, " + 
				" (SELECT nickname FROM member WHERE member_id=?))";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,title);
			pstmt.setString(2, content);
			pstmt.setString(3, loginId);
			pstmt.setString(4, loginId);
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
	
	//boardInsert메서드 : (사진첨부ㅇ)글쓰기(title : 제목, content : 내용, loginId : 아이디, photo : 사진)
	//리턴값 : 없음
	public void boardInsert(String title, String content, String loginId, String photo) {	//(사진O) 글쓰기
		Connection conn =JdbcConn.connect();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO board_list (bno, title, content, writer_id, hitcount, writedate, nickname,photo) " + 
				" VALUES (SEQ_BNO.nextval, ?, ?, ?, 0, sysdate, " + 
				" (SELECT nickname FROM member WHERE member_id='jjyjyd'),?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,title);
			pstmt.setString(2, content);
			pstmt.setString(3, loginId);
			pstmt.setString(4, photo);
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
	
	//board_select메서드 : 게시판 리스트 조회
	//리턴값: 없음
	public ArrayList<BoardListDto> board_select() {	
		Connection conn = JdbcConn.connect();
		ArrayList<BoardListDto> board_select = new ArrayList<BoardListDto>();
		String sql = "SELECT bs.bno, bs.title, m.nickname, bs.writedate, bs.hitcount, bs.photo" + 
					" FROM board_list bs, member m " + 
					" WHERE bs.writer_id=m.member_id";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()) {
				int bno = rs.getInt("bno");
				String title = rs.getString("title");
				String nickname = rs.getString("nickname");
				String writedate = rs.getString("writedate");
				int hitcount = rs.getInt("hitcount");
				String photo = rs.getString("photo");
				BoardListDto dto = new BoardListDto(bno, title, nickname, writedate, hitcount);
				board_select.add(dto);
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
		return board_select;
    }
	
	//board_detail메서드 : 상세글 조회(bno : 게시글번호)
	//리턴값 : ArrayList<BoardListDto>
	public ArrayList<BoardListDto> board_detail(int bno) {	
		Connection conn = JdbcConn.connect();
		ArrayList<BoardListDto> board_select = new ArrayList<BoardListDto>();
		String sql = "SELECT * FROM board_list WHERE bno =?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			if(rs.next())  {
				String title = rs.getString("title");
				String content = rs.getString("content");
				String nickname = rs.getString("nickname");
				String writedate = rs.getString("writedate");
				int hitcount = rs.getInt("hitcount");
				String photo = rs.getString("photo");
				BoardListDto dto = new BoardListDto(title, nickname, writedate, hitcount, content, photo);
				board_select.add(dto);
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
		return board_select;
	}
	
	//getWriterId메서드 : 작성자ID 출력(nickname : 별명, bno : 게시글 번호)
	//리턴값 : 작성자ID(예를 들어 "sky123")
	public String getWriterId(String nickname,int bno) {
		Connection conn = JdbcConn.connect();
		String sql = "SELECT writer_id "
					+" FROM board_list" 
					+" WHERE nickname = ? AND bno =?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String writedId = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickname);
			pstmt.setInt(2, bno);
			rs = pstmt.executeQuery();
			if(rs.next())  {
				writedId = rs.getString("writer_id");
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
		return writedId;
	}
	
	//getNickname메서드 : 닉네임 출력(loginId : ID)
	//리턴값 : 닉네임 문자열(예를 들어 "sky")
	public String getNickname(String loginId) {
		Connection conn = JdbcConn.connect();
		String sql = "SELECT nickname" + 
				" FROM member" + 
				" WHERE member_id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String loginNickname = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);
			rs = pstmt.executeQuery();
			if(rs.next())  {
				loginNickname = rs.getString("nickname");
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
		return loginNickname;
	}
	
	//hitupdate메서드 : 조회수 증가(bno : 게시글 번호)
	//리턴값 : 없음
	public void hitupdate(int bno) {	//조회수
		Connection conn =JdbcConn.connect();
		PreparedStatement pstmt = null;
		String sql = "UPDATE BOARD_LIST SET hitcount=hitcount+1 WHERE bno = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,bno);
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
	
	//comment_select메서드 : 댓글 출력(bno : 게시글 번호)
	//리턴값 : ArrayList<NoticeBoardCommentDto>
	public ArrayList<NoticeBoardCommentDto> comment_select(int bno) {	//댓글 조회
		Connection conn = JdbcConn.connect();
		ArrayList<NoticeBoardCommentDto> comment_select = new ArrayList<NoticeBoardCommentDto>();
		String sql = "SELECT c.*, m.nickname" + 
				" FROM comments c, member m" + 
				" WHERE c.writer_id = m.member_id" + 
				" AND c.bno=? ORDER BY NVL(c.ref, c.rno), c.r_order";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bno);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					int rno = rs.getInt("rno");
					String writerId = rs.getString("writer_id");
					String content= rs.getString("content");
					String writedate = rs.getString("writedate");
					String nickname = rs.getString("nickname");
					int step = rs.getInt("step");
					int ref = rs.getInt("ref");
					NoticeBoardCommentDto dto = new NoticeBoardCommentDto(rno, writerId, content, writedate, nickname, step, ref);
					comment_select.add(dto);
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
		return comment_select;
    }
	
	//getBoardListByPageNum메서드 : 페이징 처리한 게시글 리스트 조회(pageNum : 페이지번호)
	//리턴값 : ArrayList<BoardListDto>
	public ArrayList<BoardListDto> getBoardListByPageNum(int pageNum) {	//페이징
		Connection conn = JdbcConn.connect();
		int endRnum = pageNum * 30;
		int startRnum = endRnum - 29;
		ArrayList<BoardListDto> listBoard = new ArrayList<BoardListDto>();
		String sql = "SELECT *" + 
					" FROM (SELECT rownum rnum, bs.*" + 
					" FROM (SELECT * FROM board_list ORDER BY bno DESC) bs) bs2" + 
					" WHERE rnum>=? AND rnum<=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRnum);
			pstmt.setInt(2, endRnum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int bno = rs.getInt("bno");
				String title = rs.getString("title");
				String nickname = rs.getString("nickname");
				String writedate = rs.getString("writedate");
				int hitcount = rs.getInt("hitcount");
				BoardListDto dto = new BoardListDto(bno, title, nickname, writedate, hitcount);
				listBoard.add(dto);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return listBoard;
	}
	
	//getBoardListBySearchKeyword메서드 : 페이징 처리한 게시글 리스트 조회(searchKeyword : 제목, 내용)
	//리턴값 : ArrayList<BoardListDto>
	public ArrayList<BoardListDto> getBoardListBySearchKeyword(String searchKeyword) {	
		ArrayList<BoardListDto> listRet = new ArrayList<BoardListDto>();
		Connection conn = JdbcConn.connect();

		String sql = "SELECT * FROM board_list WHERE title LIKE ? OR content LIKE ? ORDER BY bno DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchKeyword + "%");
			pstmt.setString(2, "%" + searchKeyword + "%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int bno = rs.getInt("bno");
				String title = rs.getString("title");
				String nickname = rs.getString("nickname");
				String writedate = rs.getString("writedate");
				int hitcount = rs.getInt("hitcount");
				BoardListDto dto = new BoardListDto(bno, title, nickname, writedate, hitcount);
				listRet.add(dto);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return listRet;
	}
	
	//listdelete메서드 : 게시글 삭제(bno : 글번호)
	//리턴값 :없음
	public void listdelete(int bno) {	//글삭제
		Connection conn =JdbcConn.connect();
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM board_list WHERE bno=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,bno);
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
	
	//boardUpdate메서드 : 게시글 수정(title : 제목, content : 내용, bno : 글번호)
	//리턴값 :없음
	public void boardUpdate(String title, String content, int bno) {	
		Connection conn =JdbcConn.connect();
		PreparedStatement pstmt = null;
		String sql = "UPDATE board_list" + 
					" SET title=?, content = ?,writedate= SYSDATE" + 
					" WHERE bno = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,title);
			pstmt.setString(2, content);
			pstmt.setInt(3,bno);
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
	
	//getPhoto메서드 : 게시글(사진ㅇ) 조회
	//리턴값 : ArrayList<BoardListDto>
	public ArrayList<BoardListDto> getPhoto() {	//사진있는 게시글 조회
		ArrayList<BoardListDto> listPhoto = new ArrayList<BoardListDto>();
		Connection conn = JdbcConn.connect();

		String sql = "SELECT *" + 
				" FROM board_list" + 
				" WHERE photo IS NOT NULL";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int bno = rs.getInt("bno");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int hitcount = rs.getInt("hitcount");
				String writedate = rs.getString("writedate");
				String nickname = rs.getString("nickname");
				String photo = rs.getString("photo");
				BoardListDto dto = new BoardListDto(bno, title, nickname, writedate, hitcount, content, photo);
				listPhoto.add(dto);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return listPhoto;
	}
	
}

