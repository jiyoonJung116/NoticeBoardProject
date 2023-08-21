package action;
//게시글 수정
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;

public class NoticeBoardUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		String title = request.getParameter("title");
    	String content = request.getParameter("content");
		BoardDao dao = new BoardDao();
		
		 dao.boardUpdate(title, content, bno);

	        // 업데이트된 게시글 정보를 request 객체에 저장
		 	request.setAttribute("bno", bno);

	        // 다음 페이지로 이동 (예시로 result.jsp로 이동)
	        RequestDispatcher dispatcher = request.getRequestDispatcher("Controller?command=notice_detail");
	        dispatcher.forward(request, response);
		
		
		
	}

}
