package action;

public class ActionFactory {
	private static ActionFactory instance = new ActionFactory();
	private ActionFactory() { }
	public static ActionFactory getInstance() {
		return instance;
	}
	public Action getAction(String command) {
//System.out.println("AF.getAction(), command : " + command);
		Action action = null;
		switch(command) {
		case "notice_board" :
			action = new NoticeBoardAction();	//메인 게시판
			break;
		case "notice_board_write" :
			action = new NoticeBoardForm();	//글쓰기
			break;
		case "notice_detail" :
			action = new NoticeBoardDetailAction();	//상세게시글
			break;
		case "notice_update" :
			action = new NoticeBoardUpdateAction();	//게시글수정
			break;
		case "notice_update_form" :
			action = new NoticeBoardUpdate();	//게시글수정
			break;
		case "notice_delete" :
			action = new NoticeBoardDeleteAction();	//게시글 삭제
			break;
		case "notice_login"	:
			action = new NoticeBoardLoginAction();	//로그인
			break;
		case "notice_login_naver" :
			action = new NoticeBoardLoginNaverAction();	//NaverLogin
			break;
		case "notice_board_login"	:
			action = new NoticeBoardLogin();	//로그인페이지 이동
			break;
		case "comment_write" :
			action = new CommentWriteAction();	//댓글 작성
			break;
		case "reply_write" :
			action = new ReplyWriteAction();	//대댓글 작성
			break;
		case "member_join" :
			action = new JoinAction();	//회원가입
			break;
		case "join" :
			action = new Join();	//회원가입페이지 이동
			break;
		case "member_logout" :
			action = new LogoutAction();	//로그아웃
			break;
		}
//System.out.println("AF.getAction(), returns==null : " + (action==null));
		return action;
	}
}
