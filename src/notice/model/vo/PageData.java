package notice.model.vo;

import java.util.ArrayList;

public class PageData 
{
	private ArrayList<Notice> pageList;
	private String pageNavi;
	private int totalCount;
	private int searchTotalCount;
	private int recordCountPerPage;
	
	public int getSearchTotalCount() {
		return searchTotalCount;
	}

	public void setSearchTotalCount(int searchTotalCount) {
		this.searchTotalCount = searchTotalCount;
	}
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public PageData() {}

	public ArrayList<Notice> getPageList() {
		return pageList;
	}

	public void setPageList(ArrayList<Notice> pageList) {
		this.pageList = pageList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	
	
}
