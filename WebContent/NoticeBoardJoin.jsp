<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
	<script src="js/jquery-3.7.0.min.js"></script>
	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	<script>
	$(function() {
		$("#birthdate").datepicker(
				{
					showOn : 'both',
					buttonImage : 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACgAAAAeCAYAAABe3VzdAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyhpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTExIDc5LjE1ODMyNSwgMjAxNS8wOS8xMC0wMToxMDoyMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTUgKE1hY2ludG9zaCkiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6ODgwN0NCRURFODgzMTFFNzhFNzQ4NjYwMENFMEQ3RTciIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6ODgwN0NCRUVFODgzMTFFNzhFNzQ4NjYwMENFMEQ3RTciPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo4ODA3Q0JFQkU4ODMxMUU3OEU3NDg2NjAwQ0UwRDdFNyIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDo4ODA3Q0JFQ0U4ODMxMUU3OEU3NDg2NjAwQ0UwRDdFNyIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PjTBh6MAAAEoSURBVHjaYvz//z/DYAZMDIMcjDpw1IGjDiQAWEjVUNXUiS4EKqcYsSjFEG+rK6e9A6EWEyOGTZyRXlHMiITfo/EZkRyCzh+QNCiExxMDnkk6gFgAiB2AOAIqVgDEGlDxjoF2oCDUDA4g5oaK8QExK1RckO65GA2sBuJvQHwDiJ9AxXYB8XOo+OqBDsGZ0DToAcTJULE6INaFis8c6BBUhtIzkMS8sMgPWAiCyjk5IK4A4qVQsUtA7A0V/z/QIQiKxo9APAGImaFiNkD8HYj/4imC6BaCu4FYDFrEVELFZgGxOVR890CHIKhi/gTEJ4D4OlRsHhDfhYp3DpQDYeXbHiBmB+KXUAwSPw2VY4fKCw6EA9/Rq7nFONqrG3XgqANHHTjCHQgQYAAjWTNTyGzxagAAAABJRU5ErkJggg==',
					dateFormat : "yy-mm-dd",
					prevText : '이전 달',

					nextText : '다음 달',
					monthNames : [ '01', '02', '03', '04', '05', '06',
							'07', '08', '09', '10', '11', '12' ],
					monthNameshort : [ '1월', '2월', '3월', '4월', '5월',
							'6월', '7월', '8월', '9월', '10월', '11월', '12월' ],
					dayNames : [ '일', '월', '화', '수', '목', '금', '토' ],
					dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
					showMonthAfterYear : true,
					yearSuffix : '.',
					onSelect : function(dateText, inst) {
						let dateObj = $(this).datepicker('getDate');
						let formattedDate = $.datepicker.formatDate(
								"yy-F-DDDD", dateObj);
						$("#formatted_date").text(formattedDate);
					}
				});
	});
	</script>
	<link rel="stylesheet" href="css/NoticeBoardJoin.css" />
</head>
<body>
	<div class="signup-container">
        <h1>회원가입</h1>
        <form id="signupForm" action = "Controller">
         	<input type="hidden" name="command" value="member_join"/>
            <label for="username">아이디:</label>
            <input type="text" id="memberId" name="memberId" required>
            <br>
            <label for="password">패스워드:</label>
            <input type="password" id="password" name="password" required>
            <br>
            <label for="name">이름:</label>
            <input type="text" id="name" name="name" required>
            <br>
            <label for="email">이메일 주소:</label>
            <input type="email" id="email" name="email" required>
            <br>
            <label for="nickname">닉네임:</label>
            <input type="text" id="nickname" name="nickname" required>
            <br>
            <label for="birthdate">생일:</label>
            <input type="text" id="birthdate" name="birthdate" required>
            <br>
            <label class="gender-label">성별:</label>
            <input type="radio" class="choose_man" id="male" name="gender" value="남성" required>
            <label for="male" style="position: relative; bottom: 31px;">남성</label>
            <input type="radio" class="choose_woman" id="female" name="gender" value="여성" required>
            <label for="female" style="position: relative; bottom: 50px;">여성</label>
            <label for="phone">핸드폰 번호:</label>
            <input type="tel" id="phone" name="phone" required>
            <br>
            <input type="submit" value="가입하기">
        </form>
    </div>
</body>
</html>