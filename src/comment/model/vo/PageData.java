package comment.model.vo;

import java.util.ArrayList;

public class PageData {
	//생성자 
	public PageData() {}

	private ArrayList<Comment> pageList;
	private String pageNavi;
	
	//getter, setter
	public ArrayList<Comment> getPageList() {
		return pageList;
	}
	public void setPageList(ArrayList<Comment> pageList) {
		this.pageList = pageList;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
	
}
