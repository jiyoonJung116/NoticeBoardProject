package action;
//게시글 삭제
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;

public class NoticeBoardDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));	
		

		BoardDao dao = new BoardDao();
		dao.listdelete(bno);
		

	 	request.setAttribute("bno", bno);
	
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Controller?command=notice_board");
	    dispatcher.forward(request, response);
		
		
		
	}

}
