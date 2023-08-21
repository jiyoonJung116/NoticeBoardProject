<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
<script type="text/javascript">
	  var naver_id_login = new naver_id_login("HfZiNOJCPUmElEYXkheR", "http://localhost:9090/NoticeBoardProject/callback.jsp");
	  // 접근 토큰 값 출력
	  let token = naver_id_login.oauthParams.access_token;
	  // 네이버 사용자 프로필 조회
	  naver_id_login.get_naver_userprofile("naverSignInCallback()");
	  // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
	  function naverSignInCallback() {
		let email = naver_id_login.getProfileData('email');
		let nickName = naver_id_login.getProfileData('nickname');
		let name = naver_id_login.getProfileData('name');
		let gender = naver_id_login.getProfileData('gender');
		let birthday = naver_id_login.getProfileData('birthday');
		
		$.ajax({
			type:'post',
			url:'Controller?command=notice_login_naver',
			data:{"token": token, "email": email , "nickName" : nickName, "name": name, "gender": gender, "birthday": birthday},
			success:function(){
				console.log("성공");
				location.href = "Controller?command=notice_board";
			},
			error:function(){
				console.log("실패");
			}
		});
	  }
</script>
</body>
</html>