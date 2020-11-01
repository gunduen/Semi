package message.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import message.model.vo.Message;

public class MessageDAO {
	
	public ArrayList<Message> messageList(Connection conn, int currentPage, int recordCountPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Message> mList = null;
		String query = "SELECT * FROM (SELECT MESSAGE.*, ROW_NUMBER() OVER(ORDER BY MESSAGE_NO DESC) AS NUM FROM MESSAGE ) WHERE NUM BETWEEN ? AND ?";
		int start = currentPage*recordCountPerPage - (recordCountPerPage -1);
		int end = currentPage*recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			mList = new ArrayList<Message>();
			while(rset.next()) {
				Message messageOne = new Message();
				messageOne.setMessage_No(rset.getInt("MESSAGE_NO"));
				messageOne.setMessage_Date(rset.getDate("MESSAGE_DATE"));
				messageOne.setMessage_Subject(rset.getString("MESSAGE_SUBJECT"));
				messageOne.setMessage_Contents(rset.getString("MESSAGE_CONTENTS"));
				messageOne.setSender(rset.getString("SENDER"));
				messageOne.setReceiver(rset.getString("RECEIVER"));
				mList.add(messageOne);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return mList;
	}
	
	public ArrayList<Message> messageReceiverList(Connection conn, int currentPage, int recordCountPerPage, String receiver) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Message> mList = null;
		String query = "SELECT * FROM (SELECT MESSAGE.*, ROW_NUMBER() OVER(ORDER BY MESSAGE_NO DESC) AS NUM FROM MESSAGE WHERE RECEIVER = ?) WHERE NUM BETWEEN ? AND ?";
		int start = currentPage*recordCountPerPage - (recordCountPerPage -1);
		int end = currentPage*recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, receiver);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			mList = new ArrayList<Message>();
			while(rset.next()) {
				Message messageOne = new Message();
				messageOne.setMessage_No(rset.getInt("MESSAGE_NO"));
				messageOne.setMessage_Date(rset.getDate("MESSAGE_DATE"));
				messageOne.setMessage_Subject(rset.getString("MESSAGE_SUBJECT"));
				messageOne.setMessage_Contents(rset.getString("MESSAGE_CONTENTS"));
				messageOne.setSender(rset.getString("SENDER"));
				messageOne.setReceiver(rset.getString("RECEIVER"));
				mList.add(messageOne);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return mList;
	}
	
	//
	public ArrayList<Message> messageSenderList(Connection conn, int currentPage, int recordCountPerPage, String sender) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Message> mList = null;
		String query = "SELECT * FROM (SELECT MESSAGE.*, ROW_NUMBER() OVER(ORDER BY MESSAGE_NO DESC) AS NUM FROM MESSAGE WHERE SENDER = ?) WHERE NUM BETWEEN ? AND ?";
		int start = currentPage*recordCountPerPage - (recordCountPerPage -1);
		int end = currentPage*recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sender);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			mList = new ArrayList<Message>();
			while(rset.next()) {
				Message messageOne = new Message();
				messageOne.setMessage_No(rset.getInt("MESSAGE_NO"));
				messageOne.setMessage_Date(rset.getDate("MESSAGE_DATE"));
				messageOne.setMessage_Subject(rset.getString("MESSAGE_SUBJECT"));
				messageOne.setMessage_Contents(rset.getString("MESSAGE_CONTENTS"));
				messageOne.setSender(rset.getString("SENDER"));
				messageOne.setReceiver(rset.getString("RECEIVER"));
				mList.add(messageOne);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return mList;
	}
	
	public String getPageNaviSender(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
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
	      
	      StringBuilder sbS = new StringBuilder();
	      if(needPrev) {
	         sbS.append("<a href='/message/senderList?currentPage="+(startNavi-1) + "'> < </a>");
	      }
	      // 1~ 10까지의 숫자를 for문을 이용해서 생성하고 만들어주는 부분
	      for (int i = startNavi; i <= endNavi; i++) {
	         //i가 현재 페이지일 떄, 숫자 굵게만들기 = <b></b>
	         if(i == currentPage) {
	            sbS.append("<a href='/message/senderList?currentPage="+i+"'><b> " + i + " </b></a>");
	         } else { 
	            sbS.append("<a href='/message/senderList?currentPage="+i+"'> " + i + " </a>");
	         }
	      }// 다음 버튼 누를 수 있을 때, 다음 버튼을 생성하는 부분
	      if (needNext) {
	         sbS.append("<a href='/message/senderList?currentPage=" + (endNavi + 1) + "'> > </a>");
	      }
	      //태그를 StringBuilder의 append() 메소드를 이용해서 붙인 후에
	      //toString() 메소드를 이용하여 String으로 만들어서 리턴
	      return sbS.toString();
	   };
	   
	   public String getPageNaviReceiver(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
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
		      
		      StringBuilder sbR = new StringBuilder();
		      if(needPrev) {
		         sbR.append("<a href='/message/receiverList?currentPage="+(startNavi-1) + "'> < </a>");
		      }
		      // 1~ 10까지의 숫자를 for문을 이용해서 생성하고 만들어주는 부분
		      for (int i = startNavi; i <= endNavi; i++) {
		         //i가 현재 페이지일 떄, 숫자 굵게만들기 = <b></b>
		         if(i == currentPage) {
		            sbR.append("<a href='/message/receiverList?currentPage="+i+"'><b> " + i + " </b></a>");
		         } else { 
		            sbR.append("<a href='/message/receiverList?currentPage="+i+"'> " + i + " </a>");
		         }
		      }// 다음 버튼 누를 수 있을 때, 다음 버튼을 생성하는 부분
		      if (needNext) {
		         sbR.append("<a href='/message/receiverList?currentPage=" + (endNavi + 1) + "'> > </a>");
		      }
		      //태그를 StringBuilder의 append() 메소드를 이용해서 붙인 후에
		      //toString() 메소드를 이용하여 String으로 만들어서 리턴
		      return sbR.toString();
		   };
	
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
	         sb.append("<a href='/message/list?currentPage="+(startNavi-1) + "'> < </a>");
	      }
	      // 1~ 10까지의 숫자를 for문을 이용해서 생성하고 만들어주는 부분
	      for (int i = startNavi; i <= endNavi; i++) {
	         //i가 현재 페이지일 떄, 숫자 굵게만들기 = <b></b>
	         if(i == currentPage) {
	            sb.append("<a href='/message/list?currentPage="+i+"'><b> " + i + " </b></a>");
	         } else { 
	            sb.append("<a href='/message/list?currentPage="+i+"'> " + i + " </a>");
	         }
	      }// 다음 버튼 누를 수 있을 때, 다음 버튼을 생성하는 부분
	      if (needNext) {
	         sb.append("<a href='/message/list?currentPage=" + (endNavi + 1) + "'> > </a>");
	      }
	      //태그를 StringBuilder의 append() 메소드를 이용해서 붙인 후에
	      //toString() 메소드를 이용하여 String으로 만들어서 리턴
	      return sb.toString();
	   };
	   
	   public int totalCount(Connection conn) {
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      String query = "SELECT COUNT(*) AS TOTALCOUNT FROM MESSAGE";
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
	   
	   public Message selectMessage(Connection conn, int messageNo) {
		   PreparedStatement pstmt = null;
		   ResultSet rset = null;
		   Message messageOne = null;
		   String query = "SELECT * FROM MESSAGE WHERE MESSAGE_NO = ?";
		   try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, messageNo);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					messageOne = new Message();
					messageOne.setMessage_No(rset.getInt("MESSAGE_NO"));
					messageOne.setMessage_Date(rset.getDate("MESSAGE_DATE"));
					messageOne.setMessage_Subject(rset.getString("MESSAGE_SUBJECT"));
					messageOne.setMessage_Contents(rset.getString("MESSAGE_CONTENTS"));
					messageOne.setSender(rset.getString("SENDER"));
					messageOne.setReceiver(rset.getString("RECEIVER"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
		   return messageOne;
	   }
	   
	   public int insertMessage(Connection conn, Message message, String userId) {
		   PreparedStatement pstmt = null;
		   int result = 0;
		   String query = "INSERT INTO MESSAGE VALUES(MESSAGE_NUM.NEXTVAL,SYSDATE,?,?,0,?,?)";
		   try {
			   pstmt = conn.prepareStatement(query);
			   pstmt.setString(1, message.getMessage_Subject());
			   pstmt.setString(2, message.getMessage_Contents());
			   pstmt.setString(3, userId);
			   pstmt.setString(4, message.getReceiver());
			   result = pstmt.executeUpdate();
			   System.out.println(result);
		   } catch (Exception e) {
			   e.printStackTrace();
		   } finally {
			   JDBCTemplate.close(pstmt);
		   }
		   return result;
	   }
	   
	   
	   public int deleteMessage(Connection conn, int messageNo) {
		   PreparedStatement pstmt = null;
		   int result = 0;
		   String query="DELETE FROM MESSAGE WHERE MESSAGE_NO = ?";
		   try {
			   pstmt = conn.prepareStatement(query);
			   pstmt.setInt(1, messageNo);
			   result = pstmt.executeUpdate();
		   } catch (SQLException e) {
			   e.printStackTrace();
		   } finally {
			   JDBCTemplate.close(pstmt);
		   }
		   return result;
	   }
}
