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
import dao.CommentDao;
import dto.BoardListDto;
import dto.NoticeBoardCommentDto;

//public class NoticeBoardDetailReplyAction implements Action {
//
//	@Override
//	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		String loginId = (String)session.getAttribute("loginId"); 
//		int rno = Integer.parseInt(request.getParameter("rno"));
//		CommentDao dao = new CommentDao();
//		
//		ArrayList<NoticeBoardCommetnDto> replyDto = null;
//		try {
//			replyDto = dao.reply_select(rno);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		request.setAttribute("loginId", loginId);
//		request.setAttribute("replyDto", replyDto);
//		RequestDispatcher rd = request.getRequestDispatcher("NoticeBoardDetail.jsp");
//		rd.forward(request, response);
//		
//		
//		
//	}
//
//}
