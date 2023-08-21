package dto;

public class BoardListDto {
	private int bno;	//글번호
	private String title;	//제목
	private String nickname;	//별명
	private String writedate;	//작성일자
	private int hitcount;	//조회수
	private String content;	//글내용
	private String photo;	//사진
	

	public BoardListDto(String title, String nickname, String writedate, int hitcount, String content, String photo) {
		this.title = title;
		this.nickname = nickname;
		this.writedate = writedate;
		this.hitcount = hitcount;
		this.content = content;
		this.photo = photo;
	}
	

	public BoardListDto(int bno, String title, String nickname, String writedate, int hitcount) {
		this.bno = bno;
		this.title = title;
		this.nickname = nickname;
		this.writedate = writedate;
		this.hitcount = hitcount;
	}
	
	public BoardListDto(int bno, String title, String nickname, String writedate, int hitcount, String content,
			String photo) {
		this(title, nickname, writedate, hitcount, content, photo);
		this.bno = bno;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}

	public int getHitcount() {
		return hitcount;
	}

	public void setHitcount(int hitcount) {
		this.hitcount = hitcount;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}

	
	
	
	
}
