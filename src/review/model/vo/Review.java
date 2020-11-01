package review.model.vo;

import java.sql.Date;

public class Review {

	public Review() {}
	
	private int reviewNo;
	private String reviewSubject;
	private String reviewContents;
	private int reviewHits;
	private Date reviewDate;
	private String customerId;
	private String reviewArea;
	
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public String getReviewSubject() {
		return reviewSubject;
	}
	public void setReviewSubject(String reviewSubject) {
		this.reviewSubject = reviewSubject;
	}
	public String getReviewContents() {
		return reviewContents;
	}
	public void setReviewContents(String reviewContents) {
		this.reviewContents = reviewContents;
	}
	public int getReviewHits() {
		return reviewHits;
	}
	public void setReviewHits(int reviewHits) {
		this.reviewHits = reviewHits;
	}
	public Date getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getReviewArea() {
		return reviewArea;
	}
	public void setReviewArea(String reviewArea) {
		this.reviewArea = reviewArea;
	}
	@Override
	public String toString() {
		return "Review [reviewNo=" + reviewNo + ", reviewSubject=" + reviewSubject + ", reviewContents="
				+ reviewContents + ", reviewHits=" + reviewHits + ", reviewDate=" + reviewDate + ", customerId="
				+ customerId + ", reviewArea=" + reviewArea + "]";
	}
	
	
	
	
}
