package servlet;

/*
 * 검색 ajax의 요청을 받아 글번호, 제목, 내용, 작성일자 , 조회수, 작성일자, 별명, 사진을 받아 JSONArray로 만들어주는 서블릿
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.BoardDao;
import dto.BoardListDto;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchKeyword = request.getParameter("searchKeyword");

		BoardDao bDao = new BoardDao();
		ArrayList<BoardListDto> listBoard = null;
		try {
			listBoard = bDao.getBoardListBySearchKeyword(searchKeyword);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		for(BoardListDto dto : listBoard) {
			JSONObject obj = new JSONObject();
			obj.put("bno", dto.getBno());
			obj.put("title", dto.getTitle());
			obj.put("content", dto.getContent());
			obj.put("writer_id", dto.getWritedate());
			obj.put("hitcount", dto.getHitcount());
			obj.put("writedate", dto.getWritedate());
			obj.put("nickname", dto.getNickname());
			obj.put("photo", dto.getPhoto());
			array.add(obj);
		}
		out.println(array);
	}

}
