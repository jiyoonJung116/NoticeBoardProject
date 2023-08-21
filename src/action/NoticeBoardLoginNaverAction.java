package action;
//네이버 로그인 API
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.NoticeBoardLoginDao;

public class NoticeBoardLoginNaverAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String token = request.getParameter("token");
		String email = request.getParameter("email");
		String nickName = request.getParameter("nickName");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		
		NoticeBoardLoginDao dao = new NoticeBoardLoginDao();
		int naver = dao.naverLogin(token, email, nickName, name, gender, birthday);
	
		if(naver == 2) {
			session.setAttribute("loginId", email);
			session.setAttribute("nickname", nickName);
			RequestDispatcher rd = request.getRequestDispatcher("Controller?command=notice_board");
			rd.forward(request, response);
		}
	}
}
