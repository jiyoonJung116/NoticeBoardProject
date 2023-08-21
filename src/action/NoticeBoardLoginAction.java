package action;
//로그인
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDao;
import dao.NoticeBoardLoginDao;
import dto.BoardListDto;
import dto.LoginDto;

public class NoticeBoardLoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
	    String id = request.getParameter("id");
	    String pw = request.getParameter("pw");
	    
	    NoticeBoardLoginDao dao = new NoticeBoardLoginDao();
	    boolean loginSuccess = dao.Log(id, pw);
	    String url = "";
	    if (loginSuccess) {
	        LoginDto dto = new LoginDto(id,pw);
	        session.setAttribute("loginId", dto.getId());
	        try {
				session.setAttribute("nickname", dao.getNicknameByLoginId(dto.getId()));
			} catch (Exception e) {
				e.printStackTrace();
			}
	        url = "Controller?command=notice_board";
	    } else {
	    	url = "NoticeBoardLogin.jsp";
	    }
	    request.setAttribute("id", id);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		

	}

}
