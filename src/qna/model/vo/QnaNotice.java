package qna.model.vo;

import java.sql.Date;

public class QnaNotice {
	
	private int qnaNoticeNo;
	private Date qnaNoticeDate;
	private int qnaNoticeHits;
	private String qnaNoticeSubject;
	private String qnaNoticeContent;
	private String qnaNoticeCheck;
	private String driverId;
	private String customerId;
	private int totalCount;
	private int currentPage;
	private int recordCountPerPage;
	
	public QnaNotice () {}

	public int getQnaNoticeNo() {
		return qnaNoticeNo;
	}

	public void setQnaNoticeNo(int qnaNoticeNo) {
		this.qnaNoticeNo = qnaNoticeNo;
	}

	public Date getQnaNoticeDate() {
		return qnaNoticeDate;
	}

	public void setQnaNoticeDate(Date qnaNoticeDate) {
		this.qnaNoticeDate = qnaNoticeDate;
	}

	public int getQnaNoticeHits() {
		return qnaNoticeHits;
	}

	public void setQnaNoticeHits(int qnaNoticeHits) {
		this.qnaNoticeHits = qnaNoticeHits;
	}

	public String getQnaNoticeSubject() {
		return qnaNoticeSubject;
	}

	public void setQnaNoticeSubject(String qnaNoticeSubject) {
		this.qnaNoticeSubject = qnaNoticeSubject;
	}

	public String getQnaNoticeContent() {
		return qnaNoticeContent;
	}

	public void setQnaNoticeContent(String qnaNoticeContent) {
		this.qnaNoticeContent = qnaNoticeContent;
	}


	public String getQnaNoticeCheck() {
		return qnaNoticeCheck;
	}

	public void setQnaNoticeCheck(String qnaNoticeCheck) {
		this.qnaNoticeCheck = qnaNoticeCheck;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	
}
