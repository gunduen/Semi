package travel.model.vo;

import java.sql.Date;

public class Travel {
	
	private int package_Code;
	private String package_Area;
	private String package_Utilization;
	private String package_Pickup;
	private String package_TravelDate;
	private String customer_Id;
	private int pakcage_Confirm;
	private String driver_Name;
	private Date package_Date;
	private String driver_Id;
	private String coordx;
	private String coordy;
	private String reviewYN;
	
	
	public String getReviewYN() {
		return reviewYN;
	}


	public void setReviewYN(String reviewYN) {
		this.reviewYN = reviewYN;
	}


	public int getPakcage_Confirm() {
		return pakcage_Confirm;
	}


	public void setPakcage_Confirm(int pakcage_Confirm) {
		this.pakcage_Confirm = pakcage_Confirm;
	}


	public int getPackage_Code() {
		return package_Code;
	}

	
	public String getCoordx() {
		return coordx;
	}


	public void setCoordx(String coordx) {
		this.coordx = coordx;
	}


	public String getCoordy() {
		return coordy;
	}


	public void setCoordy(String coordy) {
		this.coordy = coordy;
	}


	public String getDriver_Id() {
		return driver_Id;
	}


	public void setDriver_Id(String driver_Id) {
		this.driver_Id = driver_Id;
	}


	public Date getPackage_Date() {
		return package_Date;
	}

	public void setPackage_Date(Date package_Date) {
		this.package_Date = package_Date;
	}

	public String getPackage_Area() {
		return package_Area;
	}

	public void setPackage_Area(String package_Area) {
		this.package_Area = package_Area;
	}

	public String getPackage_Utilization() {
		return package_Utilization;
	}

	public void setPackage_Utilization(String package_Utilization) {
		this.package_Utilization = package_Utilization;
	}

	public String getPackage_Pickup() {
		return package_Pickup;
	}

	public void setPackage_Pickup(String package_Pickup) {
		this.package_Pickup = package_Pickup;
	}

	public String getPackage_TravelDate() {
		return package_TravelDate;
	}

	public void setPackage_TravelDate(String package_TravelDate) {
		this.package_TravelDate = package_TravelDate;
	}

	public String getCustomer_Id() {
		return customer_Id;
	}

	public void setCustomer_Id(String customer_Id) {
		this.customer_Id = customer_Id;
	}

	public String getDriver_Name() {
		return driver_Name;
	}

	public void setDriver_Name(String driver_Name) {
		this.driver_Name = driver_Name;
	}

	public void setPackage_Code(int package_Code) {
		this.package_Code = package_Code;
	}
	
	
	
	
	
	
}
