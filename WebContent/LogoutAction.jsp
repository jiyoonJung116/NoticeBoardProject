<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.removeAttribute("loginId");
%>
<script>alert("로그아웃되었습니다"); location.href = "Controller?command=notice_board";</script>