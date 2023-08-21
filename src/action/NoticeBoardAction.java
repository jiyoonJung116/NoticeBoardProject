package action;
//메인게시글 페이징 처리 및 사진있는 게시글 불러와 bxslider랑 연결
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import dto.BoardListDto;

public class NoticeBoardAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao dao = new BoardDao();
		ArrayList<BoardListDto> listBoard =null; 
		ArrayList<BoardListDto> listBoard1 =null; 
		
		try {
			listBoard = dao.getBoardListByPageNum(1);
			listBoard1 = dao.getPhoto();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("listBoard", listBoard);
		request.setAttribute("listBoard1", listBoard1);
		RequestDispatcher rd = request.getRequestDispatcher("NoticeBoard.jsp");
		rd.forward(request, response);
		

	}

}
