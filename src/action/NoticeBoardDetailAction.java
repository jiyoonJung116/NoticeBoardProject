package action;
//댓글 조회
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDao;
import dto.BoardListDto;
import dto.NoticeBoardCommentDto;

public class NoticeBoardDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String loginId = (String)session.getAttribute("loginId"); 
		int bno = Integer.parseInt(request.getParameter("bno"));
		String nickname = request.getParameter("nickname");
		BoardDao dao = new BoardDao();
		
		ArrayList<BoardListDto> boardDto = null;
	   	dao.hitupdate(bno);
	   	String writerId = dao.getWriterId(nickname, bno);
	   	String commentNickname = dao.getNickname(loginId);
		ArrayList<NoticeBoardCommentDto> listComment = dao.comment_select(bno);	
		try {
			boardDto = dao.board_detail(bno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("loginId", loginId);
		request.setAttribute("boardDto", boardDto);
		request.setAttribute("nickname", nickname);
		request.setAttribute("writerId", writerId);
		request.setAttribute("listComment", listComment);
		request.setAttribute("commentNickname", commentNickname);
		RequestDispatcher rd = request.getRequestDispatcher("NoticeBoardDetail.jsp");
		rd.forward(request, response);
		
		
		
	}

}
