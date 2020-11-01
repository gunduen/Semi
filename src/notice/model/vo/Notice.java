package notice.model.vo;

import java.sql.Date;

public class Notice 
{
	public Notice() 
	{
		
	}
	
	private int notice_No;
	private String customer_Id;
	private Date notice_Date;
	private int notice_Hits;
	private String notice_Subject;
	private String notice_Contents;
	
	
	public int getNotice_No() {
		return notice_No;
	}
	public void setNotice_No(int notice_No) {
		this.notice_No = notice_No;
	}
	public String getCustomer_Id() {
		return customer_Id;
	}
	public void setCustomer_Id(String customer_Id) {
		this.customer_Id = customer_Id;
	}
	public Date getNotice_Date() {
		return notice_Date;
	}
	public void setNotice_Date(Date notice_Date) {
		this.notice_Date = notice_Date;
	}
	public int getNotice_Hits() {
		return notice_Hits;
	}
	public void setNotice_Hits(int notice_Hits) {
		this.notice_Hits = notice_Hits;
	}
	public String getNotice_Subject() {
		return notice_Subject;
	}
	public void setNotice_Subject(String notice_Subject) {
		this.notice_Subject = notice_Subject;
	}
	public String getNotice_Contents() {
		return notice_Contents;
	}
	public void setNotice_Contents(String notice_Contents) {
		this.notice_Contents = notice_Contents;
	}
	

	
	
	
}
