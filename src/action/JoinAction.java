package action;
//회원가입
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NoticeBoardLoginDao;

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
	    
    	String memberId = request.getParameter("memberId");
    	String password = request.getParameter("password");
    	String username = request.getParameter("name");
    	String email = request.getParameter("email");
    	String nickname = request.getParameter("nickname");
    	String birth = request.getParameter("birthdate");
    	String gender = request.getParameter("gender");
    	String phone = request.getParameter("phone");
    	
    	
    	NoticeBoardLoginDao dao = new NoticeBoardLoginDao();
    	dao.boardJoin(memberId, password, username, email, nickname, birth, gender, phone);
    	
    	request.getRequestDispatcher("NoticeBoardLogin.jsp")
    			.forward(request, response);;
	}

}
