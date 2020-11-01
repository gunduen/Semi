package qna.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import qna.model.dao.QnaDAO;
import qna.model.vo.Answer;
import qna.model.vo.PageData;
import qna.model.vo.QnaNotice;

public class QnaService {
	
	private JDBCTemplate factory;
	
	public QnaService() {
		factory = JDBCTemplate.getConnection();
	}
	
	public PageData selectQnaList(int currentPage) {
		Connection conn = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 10;
		PageData pd = new PageData();
		try {
			conn = factory.createConnection();
			pd.setPageList(new QnaDAO().selectQnaList(conn, currentPage, recordCountPerPage));
			pd.setPageNavi(new QnaDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		 return pd;
	}
	
	public int insertQna(String subject, String content, String userId) {
		// 고객이 QnA 등록
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new QnaDAO().insertQna(conn, subject, content, userId);
			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	public int insertQnaa(String subject, String content, String driverId) {
		// 기사가 QnA 등록
		System.out.println("service-");
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new QnaDAO().insertQnaa(conn, subject, content, driverId);
			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	public QnaNotice selectQna(int qnaNo) {
		QnaNotice qna = null;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			qna = new QnaDAO().selectQna(conn, qnaNo);
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return qna;
	}
	
	public int updateQna(String subject, String content, int qnaNo) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new QnaDAO().updateQna(conn, subject, content, qnaNo);
			if (result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	public int deleteQna(int qnaNo) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new QnaDAO().deleteQna(conn, qnaNo);
			if (result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	public PageData selectSearch(String search, int currentPage) {
		Connection conn = null;
		PageData pd = new PageData();
		int recordCountPerPage = 10;
		int naviCountPerPage = 10;
		try {
			conn = factory.createConnection();
			pd.setPageList(new QnaDAO().QnaNoticeSearchList(conn, search, currentPage, recordCountPerPage));
			pd.setPageNavi(new QnaDAO().getSearchPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage, search));
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	
	public int insertReply(String subject, String content, int qnaNo) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new QnaDAO().insertReply(conn, subject, content, qnaNo);
			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	public Answer selectAnswer(int qnaNo) {
		Answer answer = null;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			answer = new QnaDAO().selectAnswer(conn, qnaNo);
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return answer;
	}
	
	public int updateCheck(int qnaNo) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new QnaDAO().updateCheck(conn, qnaNo);
			if (result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
}
