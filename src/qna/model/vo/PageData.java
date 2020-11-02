package qna.model.vo;

import java.util.ArrayList;

public class PageData {
	
	private ArrayList<QnaNotice> pageList;
	private String pageNavi;
	private int totalCount;
	private int recordCountPerPage;
	
	public PageData() {}

	public ArrayList<QnaNotice> getPageList() {
		return pageList;
	}

	public void setPageList(ArrayList<QnaNotice> pageList) {
		this.pageList = pageList;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
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
	
}
