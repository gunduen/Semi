package comment.model.vo;

import java.sql.Date;

public class Comment {

	private int commentNo;
	private Date commentDate;
	private String commentContents;
	private String customerId;
	private int reviewNo;
	private String driverId;
	
	public Comment() {}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public String getCommentContents() {
		return commentContents;
	}

	public void setCommentContents(String commentContents) {
		this.commentContents = commentContents;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	@Override
	public String toString() {
		return "Comment [commentNo=" + commentNo + ", commentDate=" + commentDate + ", commentContents="
				+ commentContents + ", customerId=" + customerId + ", reviewNo=" + reviewNo + ", driverId=" + driverId
				+ "]";
	}
	
	
	

}
