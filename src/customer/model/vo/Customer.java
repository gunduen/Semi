package customer.model.vo;

public class Customer {
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	private String customer_Id;
	private String customer_Pwd;
	private String customer_Name;
	private String customer_Phone;
	private String customer_Email;
	private String customer_Rrn;
	private int adminCheck;
	
	public String getCustomer_Id() {
		return customer_Id;
	}
	public void setCustomer_Id(String customer_Id) {
		this.customer_Id = customer_Id;
	}
	public String getCustomer_Pwd() {
		return customer_Pwd;
	}
	public void setCustomer_Pwd(String customer_Pwd) {
		this.customer_Pwd = customer_Pwd;
	}
	public String getCustomer_Name() {
		return customer_Name;
	}
	public void setCustomer_Name(String customer_Name) {
		this.customer_Name = customer_Name;
	}
	public String getCustomer_Phone() {
		return customer_Phone;
	}
	public void setCustomer_Phone(String customer_Phone) {
		this.customer_Phone = customer_Phone;
	}
	public String getCustomer_Email() {
		return customer_Email;
	}
	public void setCustomer_Email(String customer_Email) {
		this.customer_Email = customer_Email;
	}
	public String getCustomer_Rrn() {
		return customer_Rrn;
	}
	public void setCustomer_Rrn(String customer_Rrn) {
		this.customer_Rrn = customer_Rrn;
	}
	public int getAdminCheck() {
		return adminCheck;
	}
	public void setAdminCheck(int adminCheck) {
		this.adminCheck = adminCheck;
	}
	@Override
	public String toString() {
		return "Customer [customer_Id=" + customer_Id + ", customer_Pwd=" + customer_Pwd + ", customer_Name="
				+ customer_Name + ", customer_Phone=" + customer_Phone + ", customer_Email=" + customer_Email
				+ ", customer_Rrn=" + customer_Rrn + ", adminCheck=" + adminCheck + "]";
	}
	
	
}
