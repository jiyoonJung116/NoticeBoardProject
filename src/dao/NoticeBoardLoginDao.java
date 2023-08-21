package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.BoardListDto;

public class NoticeBoardLoginDao {
	
	//Log메서드 : 로그인(id : ID, pw : 비밀번호)
	//리턴값 : 로그인 성공 여부
	public boolean Log(String id, String pw) {	//로그인
		Connection conn = JdbcConn.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT count(*) FROM member WHERE member_id = ? AND password = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			while(rs.next()) { 
				int cnt = rs.getInt(1);
				if(cnt == 1) {
					return true;
				} else {
					return false;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)
				rs.close();
				if(pstmt!=null)
				pstmt.close();
				if(conn!=null)
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	//getNicknameByLoginId메서드 : 닉네임 출력(id : ID)
	//리턴값 : 닉네임 문자열(예를 들어 "sky")
	public String getNicknameByLoginId(String id) {	
		Connection conn = JdbcConn.connect();
		String sql = "SELECT nickname FROM member WHERE member_id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String nickname = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				nickname = rs.getString("nickname");
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
		return nickname;
	}
	
	//boardJoin메서드 : 회원가입(memberId : ID, password : 비밀번호, username : 이름, email : 이메일, nickname : 별명, birth : 생일, gender : 성별, phone : 핸드폰번호 )
	//리턴값 : 없음
	public void boardJoin(String memberId, String password, String username, String email, String nickname, String birth, String gender,String phone) {
			Connection conn =JdbcConn.connect();
			PreparedStatement pstmt = null;
			String sql = "INSERT INTO member(member_id, password, username, email, nickname, birth, gender, phone, join_date)" 
						+ " VALUES (?,?,?,?,?,?,?,?,SYSDATE)";
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,memberId);
				pstmt.setString(2, password);
				pstmt.setString(3, username);
				pstmt.setString(4, email);
				pstmt.setString(5, nickname);
				pstmt.setString(6, birth);
				pstmt.setString(7, gender);
				pstmt.setString(8, phone);
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
		
	//naverLogin메서드 : 회원가입(token : 토큰값, email : 이메일, nickname : 별명 , username : 이름, gender : 성별 , birth : 생일)
	//리턴값 : 로그인 여부 숫자 타입
	public int naverLogin(String token, String email, String nickName, String name, String gender, String birthday) {
			Connection conn =JdbcConn.connect();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM member WHERE member_id = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String naverToken = rs.getString("token");
				if(naverToken.equals(token)) {
					return 2; // 로그인
				} else {
					sql = "UPDATE member SET token = ? WHERE member_id = ? ";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, token);
					pstmt.setString(2, email);
					pstmt.executeUpdate();
					return 2;
				}
			} else if(!rs.next()) {
				// 아이디 없을때 (회원가입 -> 로그인)
				sql = "INSERT INTO member(member_id, username, email, nickname, birth, gender, token) VALUES(?, ?, ?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, email);
				pstmt.setString(2, name);
				pstmt.setString(3, email);
				pstmt.setString(4, nickName);
				pstmt.setString(5, birthday);
				pstmt.setString(6, gender);
				pstmt.setString(7, token);
				pstmt.executeUpdate();
				return 2;
			}
		} catch(Exception e) {
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
		return 0; 
	}
}
