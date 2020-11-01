package qna.model.vo;

import java.util.ArrayList;

public class PageData {
	
	private ArrayList<QnaNotice> pageList;
	private String pageNavi;
	
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
}
