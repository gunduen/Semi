package review.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import review.model.dao.ReviewDAO;
import review.model.vo.PageData;
import review.model.vo.Review;

public class ReviewService {
	
	private JDBCTemplate factory;
	
	public ReviewService() {
		factory = JDBCTemplate.getConnection();
	}
	
	public PageData reviewList(int currentPage, String area) {
		Connection conn = null;
		ArrayList<Review> RList = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 10;
		PageData pd = new PageData();
		try {
			conn = factory.createConnection();
			pd.setPageList(new ReviewDAO().reviewList(conn, currentPage, recordCountPerPage, area));
			pd.setPageNavi(new ReviewDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage, area));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	
	public Review selectReview(int reviewNo) {
		Review review = null;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			review = new ReviewDAO().selectReview(conn, reviewNo);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return review;
	}
	
	public int insertReview(String subject, String contents, String customerId, String area, int packageCode) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new ReviewDAO().insertReview(conn, subject, contents, customerId, area, packageCode);
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	public int updateReview(String subject, String contents, int reviewNo) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new ReviewDAO().updateReview(conn, subject, contents, reviewNo);
			JDBCTemplate.close(conn);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteReview(int reviewNo) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new ReviewDAO().deleteReview(conn, reviewNo);
			if (result >0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	public ArrayList<Review> reviewBeList(String customerId, int num) {
		Connection conn = null;
		ArrayList<Review> RList = null;
		try {
			conn = factory.createConnection();
			RList = new ReviewDAO().reviewBeList(conn, customerId, num);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return RList;
	}
	
	public ArrayList<Review> reviewBeList(String customerId) {
		Connection conn = null;
		ArrayList<Review> RList = null;
		try {
			conn = factory.createConnection();
			RList = new ReviewDAO().reviewBeList(conn, customerId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return RList;
	}
}
