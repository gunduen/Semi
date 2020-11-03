package comment.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import comment.model.vo.Comment;
import common.JDBCTemplate;

public class CommentDAO {
	
	
	public ArrayList<Comment> commentList(Connection conn, int currentPage, int recordCountPerPage, int reviewNo){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Comment> CList = null;
		String query = "SELECT * FROM (SELECT COMMENTS.*, ROW_NUMBER() OVER(ORDER BY COMMENT_DATE DESC) AS NUM FROM COMMENTS WHERE REVIEW_NO = ?) WHERE NUM BETWEEN ? AND ?";
		int start = currentPage*recordCountPerPage - (recordCountPerPage -1);
		int end = currentPage*recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reviewNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			CList = new ArrayList<Comment>();
			while(rset.next()) {
				Comment cOne = new Comment();
				cOne.setCommentNo(rset.getInt("COMMENT_NO"));
				cOne.setCommentContents(rset.getString("COMMENT_CONTENTS"));
				cOne.setCommentDate(rset.getDate("COMMENT_DATE"));
				cOne.setCustomerId(rset.getString("CUSTOMER_ID"));
				cOne.setReviewNo(rset.getInt("REVIEW_NO"));
				cOne.setDriverId(rset.getString("DRIVER_ID"));
				CList.add(cOne);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return CList;
	}
	
	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
	      int recordTotalCount = totalCount(conn);
	      int pageTotalCount = 0;
	      if(recordTotalCount % recordCountPerPage > 0) {
	         pageTotalCount = recordTotalCount / recordCountPerPage + 1;
	      } else {
	         pageTotalCount = recordTotalCount / recordCountPerPage;
	      }
	      
	      //#오류 방지 코드 
	      if (currentPage < 1) {
	         currentPage = 1;
	      } else if (currentPage > pageTotalCount) {
	         currentPage = pageTotalCount;
	      }
	      //startNavi = 각 페이지의 범위 시작 번호
	      //naviCountPerPage = 한 페이지 범위에 보여질 페이지(Navi)의 갯수
	      int startNavi = ((currentPage - 1)/ naviCountPerPage) * naviCountPerPage + 1;
	      //endNavi = 각 페이지 범위 끝 번호
	      int endNavi = startNavi + naviCountPerPage - 1;
	      
	      //#오류 방지 코드 : 만약 endNavi 값이 전 체 페이지의 값보다 큰 값이 들어간다면?
	      if (endNavi > pageTotalCount) {
	         endNavi = pageTotalCount;
	      }
	      
	      //prev, next를 준비하기 위해 필요한 변수
	      boolean needPrev = true;
	      boolean needNext = true;
	      if(startNavi == 1) {
	         needPrev = false;
	      } 
	      if(endNavi == pageTotalCount) {
	         needNext = false;
	      }
	      
	      StringBuilder sb = new StringBuilder();
	      if(needPrev) {
	         sb.append("<a href='/comment/list?currentPage="+(startNavi-1) + "'> < </a>");
	      }
	      // 1~ 10까지의 숫자를 for문을 이용해서 생성하고 만들어주는 부분
	      for (int i = startNavi; i <= endNavi; i++) {
	         //i가 현재 페이지일 떄, 숫자 굵게만들기 = <b></b>
	         if(i == currentPage) {
	            sb.append("<a href='/comment/list?currentPage="+i+"'><b> " + i + " </b></a>");
	         } else { 
	            sb.append("<a href='/comment/list?currentPage="+i+"'> " + i + " </a>");
	         }
	      }// 다음 버튼 누를 수 있을 때, 다음 버튼을 생성하는 부분
	      if (needNext) {
	         sb.append("<a href='/comment/list?currentPage=" + (endNavi + 1) + "'> > </a>");
	      }
	      //태그를 StringBuilder의 append() 메소드를 이용해서 붙인 후에
	      //toString() 메소드를 이용하여 String으로 만들어서 리턴
	      return sb.toString();
	   };
	   
	   public int totalCount(Connection conn) {
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      String query = "SELECT COUNT(*) AS TOTALCOUNT FROM COMMENTS";
	      int recordTotalCount = 0;
	      try {
	         pstmt = conn.prepareStatement(query);
	         rset = pstmt.executeQuery();
	         if(rset.next()) {
	            recordTotalCount = rset.getInt("TOTALCOUNT");
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         JDBCTemplate.close(rset);
	         JDBCTemplate.close(pstmt);
	      }
	      return recordTotalCount;
	   }
	
	public int insertComment(Connection conn, String commentContents, String customerId, String driverId, int reviewNo) {
		   PreparedStatement pstmt = null;
		   int result = 0;
		   String query = "INSERT INTO COMMENTS VALUES(COMMENTS_NUM.NEXTVAL,SYSDATE,?,?,?,?)";
		   try {
			   pstmt = conn.prepareStatement(query);
			   pstmt.setString(1, commentContents);
			   pstmt.setString(2, customerId);
			   pstmt.setInt(3, reviewNo);
			   pstmt.setString(4, driverId);
			   result = pstmt.executeUpdate();
		   } catch (SQLException e) {
			   e.printStackTrace();
		   } finally {
			   JDBCTemplate.close(pstmt);
		   }
		   return result;
	   }

	
	
	public int deleteComment(Connection conn, int commentNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM COMMENTS WHERE COMMENT_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, commentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int deleteAllComment(Connection conn, int reviewNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM COMMENTS WHERE REVIEW_NO = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reviewNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
}
