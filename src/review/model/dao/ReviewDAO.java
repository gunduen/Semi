package review.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import message.model.vo.Message;
import review.model.vo.Review;

public class ReviewDAO {

	public ArrayList<Review> reviewList(Connection conn, int currentPage, int recordCountPerPage, String area){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Review> RList = null;
		String query = "SELECT * FROM (SELECT REVIEW.*, ROW_NUMBER() OVER(ORDER BY REVIEW_NO DESC) AS NUM FROM REVIEW WHERE REVIEW_AREA =?) WHERE NUM BETWEEN ? AND ?";
		int start = currentPage*recordCountPerPage - (recordCountPerPage -1);
		int end = currentPage*recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, area);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			RList = new ArrayList<Review>();
			while(rset.next()) {
				Review reviewOne = new Review();
				reviewOne.setReviewNo(rset.getInt("REVIEW_NO"));
				reviewOne.setReviewSubject(rset.getString("REVIEW_SUBJECT"));
				reviewOne.setReviewContents(rset.getString("REVIEW_CONTENTS"));
				reviewOne.setReviewHits(rset.getInt("REVIEW_HITS"));
				reviewOne.setReviewDate(rset.getDate("REVIEW_DATE"));
				reviewOne.setCustomerId(rset.getString("CUSTOMER_ID"));
				reviewOne.setReviewArea(rset.getString("REVIEW_AREA"));
				reviewOne.setPackageCode(rset.getInt("PACKAGE_CODE"));
				RList.add(reviewOne);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return RList;
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
	         sb.append("<a href='/review/list?currentPage="+(startNavi-1) + "'> < </a>");
	      }
	      // 1~ 10까지의 숫자를 for문을 이용해서 생성하고 만들어주는 부분
	      for (int i = startNavi; i <= endNavi; i++) {
	         //i가 현재 페이지일 떄, 숫자 굵게만들기 = <b></b>
	         if(i == currentPage) {
	            sb.append("<a href='/review/list?currentPage="+i+"'><b> " + i + " </b></a>");
	         } else { 
	            sb.append("<a href='/review/list?currentPage="+i+"'> " + i + " </a>");
	         }
	      }// 다음 버튼 누를 수 있을 때, 다음 버튼을 생성하는 부분
	      if (needNext) {
	         sb.append("<a href='/review/list?currentPage=" + (endNavi + 1) + "'> > </a>");
	      }
	      //태그를 StringBuilder의 append() 메소드를 이용해서 붙인 후에
	      //toString() 메소드를 이용하여 String으로 만들어서 리턴
	      return sb.toString();
	   };
	   
	   public int totalCount(Connection conn) {
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      String query = "SELECT COUNT(*) AS TOTALCOUNT FROM REVIEW";
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
	   
	   public Review selectReview(Connection conn, int reviewNo) {
		   PreparedStatement pstmt = null;
		   ResultSet rset = null;
		   Review reviewOne = null;
		   String query = "SELECT * FROM REVIEW WHERE REVIEW_NO = ?";
		   try {
			   pstmt = conn.prepareStatement(query);
			   pstmt.setInt(1, reviewNo);
			   rset = pstmt.executeQuery();
			   if (rset.next()) {
				   reviewOne = new Review();
				   reviewOne.setReviewNo(rset.getInt("REVIEW_NO"));
				   reviewOne.setReviewSubject(rset.getString("REVIEW_SUBJECT"));
				   reviewOne.setReviewContents(rset.getString("REVIEW_CONTENTS"));
				   reviewOne.setReviewHits(rset.getInt("REVIEW_HITS"));
				   reviewOne.setReviewDate(rset.getDate("REVIEW_DATE"));
				   reviewOne.setCustomerId(rset.getString("CUSTOMER_ID"));
				   reviewOne.setReviewArea(rset.getString("REVIEW_AREA"));
				   reviewOne.setPackageCode(rset.getInt("PACKAGE_CODE"));
			   }
		   }catch (SQLException e) {
			   e.printStackTrace();
		   } finally {
			   JDBCTemplate.close(rset);
			   JDBCTemplate.close(pstmt);
		   }
		   return reviewOne;
	   }
	   
	   public int insertReview(Connection conn, String subject, String content, String customerId, String area, int packageCode) {
		   PreparedStatement pstmt = null;
		   int result = 0;
		   String query = "INSERT INTO REVIEW VALUES(REVIEW_NUM.NEXTVAL,?,?,0,SYSDATE,?,?,?)";
		   try {
			   pstmt = conn.prepareStatement(query);
			   pstmt.setString(1, subject);
			   pstmt.setString(2, content);
			   pstmt.setString(3, customerId);
			   pstmt.setString(4, area);
			   pstmt.setInt(5, packageCode);
			   result = pstmt.executeUpdate();
		   } catch (SQLException e) {
			   e.printStackTrace();
		   } finally {
			   JDBCTemplate.close(pstmt);
		   }
		   return result;
	   }
	   
	   public int updateReview(Connection conn, String subject, String contents, int reviewNo) {
		   int result = 0;
		   PreparedStatement pstmt = null;
		   String query = "UPDATE REVIEW SET REVIEW_SUBJECT = ?, REVIEW_CONTENTS = ? WHERE REVIEW_NO = ?";
		   try {
			   pstmt = conn.prepareStatement(query);
			   pstmt.setString(1, subject);
			   pstmt.setString(2, contents);
			   pstmt.setInt(3, reviewNo);
			   result = pstmt.executeUpdate();
		   } catch (SQLException e) {
			   e.printStackTrace();
		   } finally {
			   JDBCTemplate.close(pstmt);
		   }
		   return result;
	   }
	   
	   public int deleteReview(Connection conn, int reviewNo) {
		   PreparedStatement pstmt = null;
		   int result = 0;
		   String query="DELETE FROM REVIEW WHERE REVIEW_NO = ?";
		   try {
			   pstmt = conn.prepareStatement(query);
			   pstmt.setInt(1, reviewNo);
			   result = pstmt.executeUpdate();
		   } catch (SQLException e) {
			   e.printStackTrace();
		   } finally {
			   JDBCTemplate.close(conn);
		   }
		   return result;
	   }
	   
	   public ArrayList<Review> reviewBeList(Connection conn, String customerId, int num) {
		   PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<Review> RList = null;
			String query = "SELECT * FROM REVIEW WHERE CUSTOMER_ID =? AND REVIEW_NO =?" ;
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, customerId);
				pstmt.setInt(2, num);
				rset = pstmt.executeQuery();
				RList = new ArrayList<Review>();
				while(rset.next()) {
					Review reviewOne = new Review();
					reviewOne.setCustomerId(rset.getString("CUSTOMER_ID"));
					reviewOne.setReviewNo(rset.getInt("REVIEW_NO"));
					reviewOne.setReviewSubject(rset.getString("REVIEW_SUBJECT"));
					reviewOne.setReviewContents(rset.getString("REVIEW_CONTENTS"));
					reviewOne.setReviewHits(rset.getInt("REVIEW_HITS"));
					reviewOne.setReviewDate(rset.getDate("REVIEW_DATE"));
					reviewOne.setCustomerId(rset.getString("CUSTOMER_ID"));
					reviewOne.setReviewArea(rset.getString("REVIEW_AREA"));
					reviewOne.setPackageCode(rset.getInt("PACKAGE_CODE"));
					RList.add(reviewOne);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			return RList;
	   }

	   public ArrayList<Review> reviewBeList(Connection conn, String customerId) {
		   PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<Review> RList = null;
			String query = "SELECT * FROM REVIEW WHERE CUSTOMER_ID =?" ;
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, customerId);
				rset = pstmt.executeQuery();
				RList = new ArrayList<Review>();
				while(rset.next()) {
					Review reviewOne = new Review();
					reviewOne.setCustomerId(rset.getString("CUSTOMER_ID"));
					reviewOne.setReviewNo(rset.getInt("REVIEW_NO"));
					reviewOne.setReviewSubject(rset.getString("REVIEW_SUBJECT"));
					reviewOne.setReviewContents(rset.getString("REVIEW_CONTENTS"));
					reviewOne.setReviewHits(rset.getInt("REVIEW_HITS"));
					reviewOne.setReviewDate(rset.getDate("REVIEW_DATE"));
					reviewOne.setCustomerId(rset.getString("CUSTOMER_ID"));
					reviewOne.setReviewArea(rset.getString("REVIEW_AREA"));
					reviewOne.setPackageCode(rset.getInt("PACKAGE_CODE"));
					RList.add(reviewOne);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			return RList;
	   }
}
