<%@page import="dto.BoardListDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	int bno = Integer.parseInt(request.getParameter("bno"));
        
        BoardDao dao = new BoardDao();
    	ArrayList<BoardListDto> listBoard = dao.board_detail(bno);
    %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글 상세보기</title>
	<link rel="stylesheet" href="css/NoticeBaordDetail.css" />
	<script>
		function goToNoticeBoard() {	//메인 게시판 이동
			location.href = "Controller?command=notice_board";
		}
	</script>
</head>
<body>
	<div style="width: 1904px;">
		<div class="board_background">
			<div class="fl po_rela">
				<span class="board_font">Board</span>
			</div>
		</div>
		<form action="Controller" method="get">
			<input type="hidden" name="command" value="notice_update"/>
			<table>
				<tbody>
					<%for(BoardListDto dto : listBoard){%>
						<tr>
							<th class="detail_st1 detail_st2">제목</th>
		                    <td class="detail_st">
		                    	<input type="text" name = "title" class="title_update" value="<%=dto.getTitle() %>"/>
		                    	<input type="hidden" name ="bno" value="<%=bno %>">
		                    </td>
		                </tr>
		                <tr>
							<th class="detail_st1 detail_st2">작성자</th>
		                    <td class="writer txtLittle detail_st3"><%=dto.getNickname() %></td>
		                </tr>
		                <tr>
							<th class="detail_st1 detail_st2">작성일</th>
		                    <td class="writer txtLittle detail_st3"><%=dto.getWritedate() %></td>
		                </tr>
		                <tr>
							<th class="detail_st1 detail_st2">조회</th>
		                    <td class="writer txtLittle detail_st3"><%=dto.getHitcount() %></td>
		                </tr>
		                <tr>
							<th class="detail_st1 detail_st2">내용</th>
		                    <td class="writer txtLittle detail_st3">
		                    	<textarea name="content" class="update_comment_area"><%=dto.getContent() %></textarea>
		                    </td>
		                </tr>
		             <%} %>
				</tbody>
			</table>
			<input type="submit" class="detail_bt" value="작성완료"/>
			<button id="btn_write" class="detail_bt" onclick="goToNoticeBoard()">목록</button>
		</form>	
	</div>
</body>
</html>