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
	private int currentPage;
	private int recordTotalCount;
	private int naviCountPerPage;
	
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

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getRecordTotalCount() {
		return recordTotalCount;
	}

	public void setRecordTotalCount(int recordTotalCount) {
		this.recordTotalCount = recordTotalCount;
	}

	public int getNaviCountPerPage() {
		return naviCountPerPage;
	}

	public void setNaviCountPerPage(int naviCountPerPage) {
		this.naviCountPerPage = naviCountPerPage;
	}
	
}
