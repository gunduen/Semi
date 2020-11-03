package review.model.vo;

import java.sql.Date;

public class Review {

	public Review() {}
	
	private int reviewNo;
	private String reviewSubject;
	private String reviewContents;
	private Date reviewDate;
	private String customerId;
	private String reviewArea;
	private int packageCode;
	
	public int getPackageCode() {
		return packageCode;
	}
	public void setPackageCode(int packageCode) {
		this.packageCode = packageCode;
	}
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
				+ reviewContents + ", reviewDate=" + reviewDate + ", customerId="
				+ customerId + ", reviewArea=" + reviewArea + "]";
	}
	
	
	
	
}
