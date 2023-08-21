<%@page import="dto.NoticeBoardCommentDto"%>
<%@page import="dto.BoardListDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
		String loginId = (String)session.getAttribute("loginId"); 
		String nickname = (String)session.getAttribute("nickname");
		String commentNickname = nickname;
    		if(loginId==null){
%>
			<script>alert("로그인부터 하세요"); loaction.href = "NoticeBoardLogin.jsp";</script>
<%
		}
		int bno = Integer.parseInt(request.getParameter("bno"));
    	
    	
	    	ArrayList<BoardListDto> listBoard = (ArrayList<BoardListDto>)request.getAttribute("boardDto");
			    	
	    	String writerId = (String)request.getAttribute("writerId");
	    	ArrayList<NoticeBoardCommentDto> listComment = (ArrayList<NoticeBoardCommentDto>)request.getAttribute("listComment");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글 상세보기</title>
	<link rel="stylesheet" href="css/NoticeBaordDetail.css" />
	<script src="js/jquery-3.7.0.min.js"></script>
	<script>
		function showReplyForm(commentId) {
		        let replyForm = document.getElementById("replyForm_" + commentId);
		        if (replyForm.style.display === "block") {
		            replyForm.style.display = "none";
		        } else {
		            replyForm.style.display = "block";
		        }
	    	}
		function confirmDelete() {	//게시글 삭제
		    if (confirm("정말로 삭제하시겠습니까?")) {
		    	var bnoValue = <%=bno%>;
		        location.href = 'Controller?command=notice_delete&bno=' + bnoValue;
		    }
		}
		function commentDelete(rno) {	//댓글 삭제
		    	location.href = 'Controller?command=notice_delete&rno=' + rno + '&bno=<%=bno%>&nickname=<%=nickname%>';
		}
		function confirmUpdate(rno) {	//게시글 수정
		    	location.href = 'Controller?command=notice_update_form&bno=' + <%=bno%>;
		}
		
		function goToNoticeBoard() {	//메인게시판 이동
			  location.href = "Controller?command=notice_board";
		}
		$(function() {
			$(".delete_bt, .reply_delete_bt").click(function() {	//댓글 삭제
				let rno = $(this).parents(".comment_area").attr("rno");
				//alert("RNO : " + rno + " --> AJAX 보냄.");
				let _this = $(this).parents(".comment_area");
				 $.ajax({
				    type: 'post',
				    url: 'CommentDeleteServlet', 
				    data: { "rno": rno },
				    dataType: 'json', 
				    success: function(data){
						//console.log(data.result);   // "5K"
						_this.remove();
						alert("삭제되었습니다.");
					},
				    error: function (request, status, error) {
				      console.log("code: " + request.status);
				      console.log("message: " + request.responseText);
				      console.log("error: " + error);
				    }
				  });
			});
			$(".update_bt").click(function() {	//댓글 수정
				let content = $(this).parents(".comment_area").find(".comment_content").text();
				let str = '<div>'
						+ '	<textarea>'+content+'</textarea>'
						+ '	<button class="update_complete_bt">수정완료</button>'
						+ '</div>';
				$(this).parents(".comment_area").append(str);
			});
			$(document).on("click", ".update_complete_bt", function() {	//댓글 수정
				let new_content = $(this).prev().val();
				let rno = $(this).parents(".comment_area").attr("rno");
				_this = $(this);
				 $.ajax({
					    type: 'post',
					    url: 'CommentModifyServlet', 
					    data: { "rno": rno, "content":new_content },
					    dataType: 'json', 
					    success: function(data){
							//console.log(data);
							_this.parents(".comment_area").find(".comment_date").text(data.writedate);
							_this.parents(".comment_area").find(".comment_content").text(new_content);
							_this.parent().remove();
							alert("수정되었습니다.");
						},
					    error: function (request, status, error) {
					      console.log("code: " + request.status);
					      console.log("message: " + request.responseText);
					      console.log("error: " + error);
					    }
				});	
			});
			$(".reply_update_bt").click(function() {	//대댓글 수정
				let content = $(this).parents(".reply_area").find(".comment_content").text();
				let str = '<div>'
						+ '	<textarea>'+content+'</textarea>'
						+ '	<button class="update_complete_bt">수정완료</button>'
						+ '</div>';
				$(this).parents(".reply_area").append(str);
			});
			$(document).on("click", ".update_complete_bt", function() {	//대댓글 수정
				let new_content = $(this).prev().val();
				let rno = $(this).parents(".reply_area").attr("rno");
				_this = $(this);
				 $.ajax({
					    type: 'post',
					    url: 'CommentModifyServlet', 
					    data: { "rno": rno, "content":new_content },
					    dataType: 'json', 
					    success: function(data){
							//console.log(data);
							_this.parents(".reply_area").find(".comment_date").text(data.writedate);
							_this.parents(".reply_area").find(".comment_content").text(new_content);
							_this.parent().remove();
							alert("수정되었습니다.");
						},
					    error: function (request, status, error) {
					      console.log("code: " + request.status);
					      console.log("message: " + request.responseText);
					      console.log("error: " + error);
					    }
				});	
			});
		});
	</script>
</head>
<body>
	<div style="width: 1904px;">
			<div class="board_background">
				<div class="fl po_rela">
					<span class="board_font">Board</span>
				</div>
			</div>
			<table style="margin-left: 388px;">
				<tbody>
					<%
						for(BoardListDto dto : listBoard){
					%>
				<tr>
					<th class="detail_st1 detail_st2">제목</th>
                    			<td class="detail_st"><%=dto.getTitle()%></td>
		                </tr>
		                <tr>
					<th class="detail_st1 detail_st2">작성자</th>
                    			<td class="writer txtLittle detail_st3"><%=dto.getNickname()%></td>
		                </tr>
		                <tr>
					<th class="detail_st1 detail_st2">작성일</th>
                    			<td class="writer txtLittle detail_st3"><%=dto.getWritedate()%></td>
		                </tr>
		                <tr>
					<th class="detail_st1 detail_st2">조회</th>
                    			<td class="writer txtLittle detail_st3"><%=dto.getHitcount()%></td>
		                </tr>
		                <tr class="view">
		                	<th class="detail_st5"></th>
					<td>
		                        	<div class="detail">
		                        	<%
		                        		String photoUrl = dto.getPhoto();
		                        		if (photoUrl != null && !photoUrl.isEmpty()) {
		                        	%>
								<img src="Images/<%=photoUrl%>">
									<%
							}
									%>
		                        	<div><%=dto.getContent()%></div>
		                        </div>
		                    </td>
		                </tr>
		             <%
		             	}
		             %>
			</tbody>
		</table>
		<div>
			<button id="btn_write" class="detail_bt detail_st4" onclick="goToNoticeBoard()">목록</button>
			<%
				if(loginId.equals(writerId)) {
			%> 
				<button class="detail_bt" onclick="confirmUpdate()">수정하기</button>
				<button class="detail_bt" onclick="confirmDelete()">삭제하기</button>
			<%
				}
			%>
		</div>
		<h3 class="detail_st4 comment_area1">댓글</h3>
		<form method="post" action="Controller" class="detail_st7">
		 		<input type="hidden" name = "command" value="comment_write"/>
				<input type="hidden" name="bno" value="<%=bno%>">
				<input type="hidden" name="loginId" value="<%=loginId%>">
				<input type="hidden" name="nickname" value="<%=commentNickname%>">
				<span class="comment_write_nick"><%=commentNickname%></span>
				<p>
					<textarea class="comment_write_area" rows="5" cols="50" name="content" placeholder="댓글을 입력해주세요"></textarea>
				</p>
				<p>
					<button type="submit" class="detail_bt comment_write_complete">등록</button>
				</p>
		</form> 
		<div>
    		<%
    			for (NoticeBoardCommentDto dto : listComment) {
    		%>
        	<div class="comment_area comment_line" style="width: 500px;" rno="<%= dto.getRno() %>">
            <div class="comment_box">
            	<%if(dto.getStep()==0){ %>
	                <input type="hidden" name="rno" value="<%= dto.getRno() %>">
	                <div class="comment_nick_box">
	                    <div class="comment_nickname detail_st5"><%= dto.getNickname() %></div>
	                    <p>
	                        <span class="comment_content detail_st5"><%= dto.getContent() %></span>
	                    </p>
	                </div>
	                <div>
	                    <span class="comment_date detail_st5 detail_st6" style="color: #dbd3d3;"><%= dto.getWritedate() %></span>
	                    <button type="button" class="reply_bt" onclick="showReplyForm(<%= dto.getRno() %>)">답글쓰기</button>
	                </div>
		                <% if (loginId.equals(dto.getWriterId())) { %>
			                <button class="detail_bt update_bt">수정하기</button>
			                <button class="detail_bt delete_bt">삭제하기</button>
		            	<% } %>
                <%} else if(dto.getStep()==1){ %>
                <!-- 대댓글 -->
	                <div id="replies_<%= dto.getRno() %>">
	                    <div class="comment_area reply_area reply" style="width: 470px; margin-left: 30px;" rno="<%= dto.getRno() %>">
	                        <div class="comment_box">
	                            <input type="hidden" name="rno" value="<%= dto.getRno() %>">
	                            <div class="comment_nick_box">
	                                <div class="comment_nickname detail_st5"><%= dto.getNickname() %></div>
	                                <p>
	                                    <span class="comment_content detail_st5"><%= dto.getContent() %></span>
	                                </p>
	                            </div>
	                            <div>
	                                <span class="comment_date detail_st5 detail_st6" style="color: #dbd3d3;"><%= dto.getWritedate() %></span>
	                                <!-- 대댓글 수정, 삭제 버튼 -->
	                                <% if (loginId.equals(dto.getWriterId())) { %>
						                <button class="detail_bt reply_update_bt">수정하기</button>
						                <button class="detail_bt reply_delete_bt">삭제하기</button>
		            				<% } %>
	                            </div>
	                        </div>
	                    </div>
	            	</div>
            	<%} %>
            </div>
	            <form method="post" action="Controller" class="detail_st7" id="replyForm_<%= dto.getRno() %>" style="display: none; margin-left: 30px;">
	                <input type="hidden" name = "command" value="reply_write"/>
	                <input type="hidden" name="rno" value="<%=dto.getRno()%>">
	                <input type="hidden" name="bno" value="<%=bno%>">
	                <input type="hidden" name="loginId" value="<%=loginId%>">
	                <input type="text" name="content" class="reply_st" id="replyInput_<%= dto.getRno() %>"  placeholder="대댓글 입력">
	                <button class="detail_bt reply_submit_bt" type="submit">등록</button>
	            </form>
        	</div>
    		<% } %>
		</div>
	</div>
</body>
</html>