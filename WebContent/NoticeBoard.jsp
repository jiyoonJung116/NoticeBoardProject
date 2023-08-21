<%@page import="dto.BoardListDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%
   		String loginId = (String)session.getAttribute("loginId");   

      	BoardDao dao = new BoardDao();
      	ArrayList<BoardListDto> listBoard = (ArrayList<BoardListDto>)request.getAttribute("listBoard");
      	ArrayList<BoardListDto> listBoard1 = (ArrayList<BoardListDto>)request.getAttribute("listBoard1");
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판</title>
	<script src="js/jquery-3.7.0.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
    	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    	<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
	<script>
	$(document).ready(function(){	//게시글에 사진이 있으면 bxslider 라인에 올라감.
		$( '.slider' ).bxSlider({
		      pause: 1000,
		      autoHover: true,
		      minSlides: 3,
		      maxSlides: 3,
		      slideWidth: 800,
		      slideMargin: 5,
		      touchEnabled : (navigator.maxTouchPoints > 0)
		  });
    	});
	let page_requested = 1;
	function request_one_page(){	//무한스크롤
		page_requested +=1;
		$.ajax({
			type : 'get',
			url : 'NoticeBoardServlet',
			data : { 'pageNum': page_requested },
			dataType : 'json',
			success: function(data){
				for(var i = 0; i<=data.length-1; i++){
					let str = "<tr>"
					         +"<td class=\"bno\">"+data[i].bno+"</td>"
					         +"<td>"+data[i].title+"</td>"
					         +"<td class=\"nickname\">"+data[i].nickname+"</td>"
					         +"<td>"+data[i].writedate+"</td>"
					         +"<td>"+data[i].hitcount+"</td>"
					         +"</tr>";
					$("#board_table").append(str);
					$("tr").click(function() {
						let bno = $(this).find(".bno").text();
						location.href = "Controller?command=notice_detail&bno=" + bno;
					});
				}
			},
			error: function (request, status, error) {
		        console.log("code: " + request.status)
		        console.log("message: " + request.responseText)
		        console.log("error: " + error);
		    }
		});
	}
	$(function() {
		$("tr").click(function() {	//게시글 상세보기
			  let bno = $(this).find(".bno").text();
			  let nickname = $(this).find(".nickname").text();
			  location.href = "Controller?command=notice_detail&bno=" + bno + "&nickname=" + nickname;
		});
		$("#searchButton").click(function() {	//검색 ajax
			  var searchKeyword = $("#searchWord").val();
	
			  $.ajax({
					type : 'get',
					url : 'SearchServlet',
					data : { 'searchKeyword': searchKeyword },
					dataType : 'json',
					success: function(data){
						console.log(data);
						$("#board_table").html('<tr>'
											+ '	<th class="notice_width2">번호</th>'
											+ '	<th class="notice_width">제목</th>'
											+ '	<th class="notice_width1">작성자</th>'
											+ '	<th class="notice_width3">작성일시</th>'
											+ '	<th>조회수</th>'
											+ '</tr>');
						
						for(var i = 0; i<=data.length-1; i++) {
							let str = "<tr>"
						         +"<td class=\"bno\">"+data[i].bno+"</td>"
						         +"<td>"+data[i].title+"</td>"
						         +"<td class=\"nickname\">"+data[i].nickname+"</td>"
						         +"<td>"+data[i].writedate+"</td>"
						         +"<td>"+data[i].hitcount+"</td>"
						         +"</tr>";
							$("#board_table").append(str);
							$("tr").click(function() {
								let bno = $(this).find(".bno").text();
								let nickname = $(this).find(".nickname").text();
								location.href = "Controller?command=notice_detail&bno=" + bno + "&nickname=" + nickname;
							});
						}
					},
					error: function (request, status, error) {
				        console.log("code: " + request.status)
				        console.log("message: " + request.responseText)
				        console.log("error: " + error);
				    }
				});				
		}); 
		$(window).scroll(function(){
			  var scrT = $(window).scrollTop();
			  console.log(scrT); //스크롤 값 확인용
			  if(scrT == $(document).height() - $(window).height()){
			  	request_one_page();
			  } 
		});
		$(".write_bt").click(function(){	//글쓰기
			location.href = "Controller?command=notice_board_write";
		});
		$(".login_bt").click(function(){	//로그인
			location.href = "Controller?command=notice_board_login";
		});
		$(".join_bt").click(function(){		//회원가입
			location.href = "Controller?command=join";
		});
		$(".logout_bt").click(function(){	//로그아웃
			location.href = "Controller?command=member_logout";
		});
	});
	</script>
	<link rel="stylesheet" href="css/NoticeBoard.css" />
</head>
<body>
	<div id="searchResults">
		<div class="board_background">
			<div class="fl po_rela">
				<span class="board_font">Board</span>
			</div>
			  <div class="notice_st">
			    <input type="text" class="input_border form-control" placeholder="Search" name="searchWord" id="searchWord" />
			  	<button class="notice_st1 notice_bt1" type="button" id="searchButton">
			    	<span>검색</span>
			  	</button>
			  </div>
		</div>
		<div class="slider" style="width : 100% !important;">
			<% for (BoardListDto dto : listBoard1) { %>
			    <a id="<%=dto.getBno() %>" href="Controller?command=notice_detail&bno=<%=dto.getBno()%>">
			    	<img class="notice_image" src="Images/<%= dto.getPhoto() %>">
			    </a>
			<% } %>
   		</div>
		<div class="board_background1">
			<div class="fr po_rela">
				<%if(loginId !=null) {%>
					<button type="button" class="notice_bt logout_bt">로그아웃</button>
					<button type="button" class="notice_bt write_bt">글쓰기</button>
				<%} else { %>
					<button type="button" class="notice_bt login_bt">로그인</button>
					<button type="button" class="notice_bt join_bt">회원가입</button>
				<%} %>
			</div>
		</div> 
		<div style="height : 100%">
			<table id="board_table" class="po_rela notice_location">
				<tr>
					<th class="notice_width2">번호</th>
					<th class="notice_width">제목</th>
					<th class="notice_width1">작성자</th>
					<th class="notice_width3">작성일시</th>
					<th class="notice_width5">조회수</th>
				</tr>
				<% for(BoardListDto dto : listBoard) { %>
					<tr>
						<td class="bno"><%=dto.getBno() %></td>
						<td><%=dto.getTitle() %></td>
						<td class="nickname"><%=dto.getNickname() %></td>
						<td><%=dto.getWritedate() %></td>
						<td><%=dto.getHitcount() %></td>
					</tr>
				<% } %>
			</table>
		</div> 
	</div>
</body>
</html>