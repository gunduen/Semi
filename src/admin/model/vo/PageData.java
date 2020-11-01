package admin.model.vo;

import java.util.ArrayList;

import customer.model.vo.Customer;
import driver.model.vo.Driver;
import travel.model.vo.Travel;

public class PageData {

	public PageData() {}
	private ArrayList<Driver> DpageList;
	private ArrayList<Customer> CpageList;
	private ArrayList<Travel> TpageList;
	private String pageNavi;
	public ArrayList<Driver> getDpageList() {
		return DpageList;
	}
	public void setDpageList(ArrayList<Driver> dpageList) {
		DpageList = dpageList;
	}
	public ArrayList<Customer> getCpageList() {
		return CpageList;
	}
	public void setCpageList(ArrayList<Customer> cpageList) {
		CpageList = cpageList;
	}
	public ArrayList<Travel> getTpageList() {
		return TpageList;
	}
	public void setTpageList(ArrayList<Travel> tpageList) {
		TpageList = tpageList;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
}
