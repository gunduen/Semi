package qna.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.JDBCTemplate;
import qna.model.vo.Answer;
import qna.model.vo.QnaNotice;

public class QnaDAO {

	public ArrayList<QnaNotice> selectQnaList(Connection conn, int currentPage, int recordCountPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<QnaNotice> qList = null;
		
		String query = "SELECT * FROM (SELECT QNANOTICE.*, ROW_NUMBER() OVER(ORDER BY QNANOTICE_NO DESC) AS NUM FROM QNANOTICE) WHERE NUM BETWEEN ? AND ?";
		int start = currentPage*recordCountPerPage - (recordCountPerPage -1);
		int end = currentPage*recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			qList = new ArrayList<QnaNotice>();
			while(rset.next()) {
				QnaNotice qnaOne = new QnaNotice();
				qnaOne.setQnaNoticeNo(rset.getInt("QNANOTICE_NO"));
				qnaOne.setQnaNoticeDate(rset.getDate("QNANOTICE_DATE"));
				qnaOne.setQnaNoticeHits(rset.getInt("QNANOTICE_HITS"));
				qnaOne.setQnaNoticeSubject(rset.getString("QNANOTICE_SUBJECTS"));
				qnaOne.setQnaNoticeContent(rset.getString("QNANOTICE_CONTENTS"));
				qnaOne.setQnaNoticeCheck(rset.getString("QNANOTICE_CHECK"));
				qnaOne.setCustomerId(rset.getString("CUSTOMER_ID"));
				qnaOne.setDriverId(rset.getString("DRIVER_ID"));
				qList.add(qnaOne);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return qList;
	}
	
	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		int recordTotalCount = totalCount(conn);
		int pageTotalCount = 0;
		
		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		}else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		
		//오류 방지 코드
		if (currentPage < 1) {
			currentPage = 1;
		}else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		
		int startNavi = ((currentPage -1) / naviCountPerPage) * naviCountPerPage +1;
		int endNavi = startNavi + naviCountPerPage - 1;
		
		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		
		if (startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageTotalCount) {
			needNext = false;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(needPrev) {
			sb.append("<a href='/qna/list?currentPage=" + (startNavi-1) + "'> < </a>");
		}
		
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append("<a href='/qna/list?currentPage="+i+"'><b> " + i + " </b></a>");
			}else {
				sb.append("<a href='/qna/list?currentPage="+i+"'> " + i + " </a>");
			}
		}
		
		if (needNext) {
			sb.append("<a href='/qna/list?currentPage=" + (endNavi+1) + "'> > </a>");
		}
		return sb.toString();
	}
	
	public int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// 게시글의 총 개수를 알아오는 쿼리
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM QNANOTICE";
		int recordTotalCount = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				// 쿼리한 결과 값이 하나의 컬럼에 number값이므로 1줄로 끝.
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return recordTotalCount;
	}
	
	public ArrayList<QnaNotice> QnaNoticeSearchList(Connection conn, String search, int currentPage, int recordCountPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT QNANOTICE.*, ROW_NUMBER() OVER(ORDER BY QNANOTICE_NO DESC) AS NUM FROM QNANOTICE WHERE QNANOTICE_SUBJECTS LIKE ?) WHERE NUM BETWEEN ? AND ?";
		ArrayList<QnaNotice> qList = null;
		int start = currentPage*recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage*recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			qList = new ArrayList<QnaNotice>();
			while(rset.next()) {
				QnaNotice qna = new QnaNotice();
				qna.setQnaNoticeNo(rset.getInt("QNANOTICE_NO"));
				qna.setQnaNoticeDate(rset.getDate("QNANOTICE_DATE"));
				qna.setQnaNoticeHits(rset.getInt("QNANOTICE_HITS"));
				qna.setQnaNoticeSubject(rset.getString("QNANOTICE_SUBJECTS"));
				qna.setQnaNoticeContent(rset.getString("QNANOTICE_CONTENTS"));
				qna.setQnaNoticeCheck(rset.getString("QNANOTICE_CHECK"));
				qna.setCustomerId(rset.getString("CUSTOMER_ID"));
				qna.setDriverId(rset.getString("DRIVER_ID"));
				qList.add(qna);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return qList;
	}
	
	public String getSearchPageNavi(Connection conn, int currentPage, 
			int recordCountPerPage, int naviCountPerPage, String search) {
		int recordTotalCount = searchTotalCount(conn, search);
		int pageTotalCount = 0;
		if (recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		}else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		// 오류방지용 코드
		if (currentPage < 1) {
			currentPage = 1;
		}else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;
		// 오류방지용 코드
		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		// perv, next를 준비하기 위한 필요한 변수
		boolean needPrev = true;
		boolean needNext = true;
		if (startNavi == 1) {
			needPrev = false;
		}
		if (endNavi == pageTotalCount) {
			needNext = false;
		}
		StringBuilder sb = new StringBuilder();
		if (needPrev) {
			sb.append("<a href='/qna/search?search="+search+"&currentPage="+(startNavi-1)+"'> < </a>");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append("<a href='/qna/search?search="+search+"&currentPage="+i+"'><b> "+ i + " </b></a>");
			}else {
				sb.append("<a href='/qna/search?search="+search+"&currentPage="+i+"'> "+ i + " </a>");
			}
		}
		if (needNext) {
			sb.append("<a href='/qna/search?search="+search+"&currentPage="+(endNavi+1)+"'> > </a>");
		}
		return sb.toString();
	}
	
	public int searchTotalCount(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// 게시글의 총 개수를 알아오는 쿼리
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM QNANOTICE WHERE QNANOTICE_SUBJECTS LIKE ?";
		int recordTotalCount = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+search+"%");
			rset = pstmt.executeQuery();
			if(rset.next()) {
				// 쿼리한 결과 값이 하나의 컬럼에 number값이므로 1줄로 끝.
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}
		return recordTotalCount;
	}
	
	public int insertQna(Connection conn, String subject, String content, String userId) {
		// 고객이 QnA 등록
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO QNANOTICE VALUES(SEQ_QNANO.NEXTVAL, SYSDATE, 0, ?, ?, '대기' , ?, null)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setString(3, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertQnaa(Connection conn, String subject, String content, String driverId) {
		// 기사가 QnA 등록
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO QNANOTICE VALUES(SEQ_QNANO.NEXTVAL, SYSDATE, 0, ?, ?, '대기', null, ?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setString(3, driverId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public QnaNotice selectQna(Connection conn, int qnaNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		QnaNotice qna = null;
		String query = "SELECT * FROM QNANOTICE WHERE QNANOTICE_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				qna = new QnaNotice();
				qna.setQnaNoticeNo(rset.getInt("QNANOTICE_NO"));
				qna.setQnaNoticeDate(rset.getDate("QNANOTICE_DATE"));
				qna.setQnaNoticeHits(rset.getInt("QNANOTICE_HITS"));
				qna.setQnaNoticeSubject(rset.getString("QNANOTICE_SUBJECTS"));
				qna.setQnaNoticeContent(rset.getString("QNANOTICE_CONTENTS"));
				qna.setQnaNoticeCheck(rset.getString("QNANOTICE_CHECK"));
				qna.setCustomerId(rset.getString("CUSTOMER_ID"));
				qna.setDriverId(rset.getString("DRIVER_ID"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return qna;
	}

	public int updateQna(Connection conn, String subject, String content, int qnaNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE QNANOTICE SET QNANOTICE_SUBJECTS = ?, QNANOTICE_CONTENTS = ? WHERE QNANOTICE_NO = ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setInt(3, qnaNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteQna(Connection conn, int qnaNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM QNANOTICE WHERE QNANOTICE_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int insertReply(Connection conn, String subject, String content, int qnaNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO ANSWER VALUES(SEQ_ANSWERNO.NEXTVAL, ?, ?, SYSDATE, ?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, content);
			pstmt.setString(2, subject);
			pstmt.setInt(3, qnaNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public Answer selectAnswer(Connection conn, int qnaNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Answer answer = null;
		String query = "SELECT * FROM ANSWER WHERE QNANOTICE_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				answer = new Answer();
				answer.setAnswerContents(rset.getString("ANSWER_CONTENTS"));
				answer.setAnswerDate(rset.getDate("ANSWER_DATE"));
				answer.setAnswerNo(rset.getInt("ANSWER_NO"));
				answer.setAnswerTitle(rset.getString("ANSWER_TITLE"));
				answer.setQnanoticeNo(rset.getInt("QNANOTICE_NO"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return answer;
	}
	
	public int updateCheck(Connection conn, int qnaNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE QNANOTICE SET QNANOTICE_CHECK = '완료' WHERE QNANOTICE_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qnaNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
//  기존 게시물 찾기 코드 (저번에 실습했던 페이징 기능 있는 찾기 코드 사용중)
//	public ArrayList<QnaNotice> selectSearch(Connection conn, String search, String type) {
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		String query = "SELECT * FROM QNANOTICE WHERE ? LIKE ? ORDER BY QNANOTICE_NO DESC";
//		ArrayList<QnaNotice> qList = null;
//		try {
//			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, type);
//			pstmt.setString(2, "%"+search+"%");
//			rset = pstmt.executeQuery();
//			qList = new ArrayList<QnaNotice>();
//			while(rset.next()) {
//				QnaNotice qna = new QnaNotice();
//				qna.setQnaNoticeNo(rset.getInt("QNANOTICE_NO"));
//				qna.setQnaNoticeDate(rset.getDate("QNANOTICE_DATE"));
//				qna.setQnaNoticeHits(rset.getInt("QNANOTICE_HITS"));
//				qna.setQnaNoticeSubject(rset.getString("QNANOTICE_SUBJECTS"));
//				qna.setQnaNoticeContent(rset.getString("QNANOTICE_CONTENTS"));
//				qna.setQnaNoticeCheck(rset.getString("QNANOTICE_CHECK"));
//				qna.setCustomerId(rset.getString("CUSTOMER_ID"));
//				qna.setDriverId(rset.getString("DRIVER_ID"));
//				qList.add(qna);
//			}
//		} catch(SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JDBCTemplate.close(rset);
//			JDBCTemplate.close(pstmt);
//		}
//		return qList;
//	}

}
