package message.model.vo;

import java.util.ArrayList;

public class PageData {
	//생성자 
	public PageData() {}

	private ArrayList<Message> pageList;
	private String pageNavi;
	
	//getter, setter
	public ArrayList<Message> getPageList() {
		return pageList;
	}
	public void setPageList(ArrayList<Message> pageList) {
		this.pageList = pageList;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
	
}
