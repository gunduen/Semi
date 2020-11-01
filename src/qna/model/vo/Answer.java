package qna.model.vo;

import java.sql.Date;

public class Answer {
	
	private int answerNo;
	private String answerContents;
	private String answerTitle;
	private Date answerDate;
	private int qnanoticeNo;
	
	public Answer() {}

	public int getAnswerNo() {
		return answerNo;
	}


	public void setAnswerNo(int answerNo) {
		this.answerNo = answerNo;
	}


	public String getAnswerContents() {
		return answerContents;
	}


	public void setAnswerContents(String answerContents) {
		this.answerContents = answerContents;
	}


	public String getAnswerTitle() {
		return answerTitle;
	}


	public void setAnswerTitle(String answerTitle) {
		this.answerTitle = answerTitle;
	}


	public Date getAnswerDate() {
		return answerDate;
	}


	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}


	public int getQnanoticeNo() {
		return qnanoticeNo;
	}


	public void setQnanoticeNo(int qnanoticeNo) {
		this.qnanoticeNo = qnanoticeNo;
	}

}
