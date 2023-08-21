package servlet;

//form 태그에 있는 내용을 받아 파일 작성을 할 수 있게 해주는 서블릿
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.BoardDao;

@WebServlet("/NoticeBoardWriteServlet")
public class NoticeBoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		String loginId = (String)session.getAttribute("loginId");
		int maxSize = 1024 * 1024 * 5; // 키로바이트 * 메가바이트 * 기가바이트   
		String location = request.getRealPath("Images");
		MultipartRequest multi = new MultipartRequest(request,
												location,
												  maxSize,
												  "utf-8",
												  new DefaultFileRenamePolicy());

		String userName = multi.getParameter("userName");

		Enumeration<?> files = multi.getFileNames(); // <input type="file">인 모든 파라메타를 반환
			
		String element = "";
		String filesystemName = "";
		String originalFileName = "";
		String contentType = "";
		long length = 0;

		if (files.hasMoreElements()) {
		    element = (String) files.nextElement();
		    filesystemName = multi.getFilesystemName(element);
		    originalFileName = multi.getOriginalFileName(element);
		    
		    // 파일이 첨부되었을 때만 파일 크기 가져오기
		    if (filesystemName != null) {
		        length = multi.getFile(element).length();
		        contentType = multi.getContentType(element);
		    }
		}

		String title = multi.getParameter("title");
		String photo = filesystemName; 
		String content = multi.getParameter("content");

		BoardDao boardWrite = new BoardDao();

		if (title == null) {
		    title = ""; 
		}

		if (content == null) {
		    content = ""; 
		}

		String url = "";
		if (photo != null && !photo.isEmpty()) {
		    boardWrite.boardInsert(title, content, loginId, photo); // 파일 첨부가 있는 경우
		    url = "Controller?command=NoticeBoard.jsp";
		} else {
		    boardWrite.boardWriteInsert(title, content, loginId); // 파일 첨부가 없는 경우
		    url = "Controller?command=NoticeBoard.jsp";
		}
		
		
		request.setAttribute("loginId", loginId);
		request.setAttribute("userName", userName);
		request.setAttribute("files", files);
		request.setAttribute("filesystemName", filesystemName);
		request.setAttribute("originalFileName", originalFileName);
		request.setAttribute("length", length);
		request.setAttribute("title", title);
		request.setAttribute("content", content);
		request.setAttribute("photo", photo);
		
		RequestDispatcher rd = request.getRequestDispatcher("Controller?command=notice_board");
		rd.forward(request, response);

	}

}
