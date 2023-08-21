package servlet;
// 댓글 수정의 Ajax의 요청을 받아 댓글을 수정해주는 서블릿
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dao.CommentDao;

@WebServlet("/CommentModifyServlet")
public class CommentModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rno = Integer.parseInt(request.getParameter("rno"));
		String content = request.getParameter("content");
		
		//System.out.println("서블릿 도착함. rno:" + rno + ", content:" + content);
		
		CommentDao cDao = new CommentDao();
		cDao.updateComment(content, rno); 
		String writedate = cDao.getCommentWritedateByRno(rno);
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		obj.put("result","5K");
		obj.put("writedate", writedate);
		out.println(obj);
	}
}
