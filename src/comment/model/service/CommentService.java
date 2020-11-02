package comment.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import comment.model.dao.CommentDAO;
import comment.model.vo.Comment;
import comment.model.vo.PageData;
import common.JDBCTemplate;

public class CommentService {

	
	private JDBCTemplate factory;

	public CommentService() {
		factory = JDBCTemplate.getConnection();
	}
	
	public PageData commentList(int currentPage, int reviewNo) {
		Connection conn = null;
		ArrayList<Comment> CList = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 10;
		PageData pd = new PageData();
		try {
			conn = factory.createConnection();
			pd.setPageList(new CommentDAO().commentList(conn, currentPage, recordCountPerPage, reviewNo));
			pd.setPageNavi(new CommentDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	
	
	public int insertComment(String commentContents, String customerId, String driverId, int reviewNo) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new CommentDAO().insertComment(conn, commentContents, customerId, driverId, reviewNo);
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	
	public int deleteComment(int commentNo) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new CommentDAO().deleteComment(conn, commentNo);
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	public int deleteAllComment(int reviewNo) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new CommentDAO().deleteAllComment(conn, reviewNo);
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
}
