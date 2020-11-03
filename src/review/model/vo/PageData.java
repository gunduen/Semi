package review.model.vo;
import java.util.ArrayList;


public class PageData {
	//생성자 
	public PageData() {}

	private ArrayList<Review> pageList;
	private String pageNavi;
	
	public ArrayList<Review> getPageList() {
		return pageList;
	}
	public void setPageList(ArrayList<Review> pageList) {
		this.pageList = pageList;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
	
	
}
