package action;
//댓글 작성 

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDao;

public class ReplyWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		   
		int rno = Integer.parseInt(request.getParameter("rno"));
		int bno = Integer.parseInt(request.getParameter("bno"));
		String loginId = request.getParameter("loginId");
		String content = request.getParameter("content");
	   	
	   	CommentDao dao = new CommentDao();
	   	dao.writeReply(rno, bno, loginId, content);
	   	
	   	request.getRequestDispatcher("Controller?command=notice_detail")
	   			.forward(request, response);
	}

}
