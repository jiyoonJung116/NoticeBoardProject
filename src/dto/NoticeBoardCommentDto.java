package dto;

public class NoticeBoardCommentDto {
	private int rno;	//댓글번호
	private int bno; 	//게시글 번호
	private String writerId;	//작성자Id
	private String content;	//댓글 내용
	private String writedate;	//작성일자
	private String nickname;	//별명
	private int step;			//0댓글 1대댓글
	private int ref;			//누구에대한답글인지
	private int r_order;		//댓글순서
	
	public NoticeBoardCommentDto(int rno, String writerId, String content, String writedate, String nickname, int step, int ref) {
		this.rno = rno;
		this.writerId = writerId;
		this.content = content;
		this.writedate = writedate;
		this.nickname = nickname;
		this.step = step;
		this.ref=ref;
	}
	
	public NoticeBoardCommentDto(int bno, String writerId, String content, String writedate, 
			int step, int ref, int r_order) {
		this.bno = bno;
		this.writerId = writerId;
		this.content = content;
		this.writedate = writedate;
		this.step = step;
		this.ref = ref;
		this.r_order = r_order;
	}

	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getR_order() {
		return r_order;
	}
	public void setR_order(int r_order) {
		this.r_order = r_order;
	}
	
	
	
	
}
