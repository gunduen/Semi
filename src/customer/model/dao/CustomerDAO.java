package customer.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import customer.model.vo.Customer;

public class CustomerDAO {
	public CustomerDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public int insertCustomer(Connection conn, Customer customer) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO CUSTOMER VALUES(?,?,?,?,?,?,0)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, customer.getCustomer_Id());
			pstmt.setString(2, customer.getCustomer_Pwd());
			pstmt.setString(3, customer.getCustomer_Name());
			pstmt.setString(4, customer.getCustomer_Phone());
			pstmt.setString(5, customer.getCustomer_Email());
			pstmt.setString(6, customer.getCustomer_Rrn());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public boolean idCheck(Connection conn,String id) {
		boolean x = false;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				x = true;
				return x;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x;
	}
	
	public Customer loginCustomer(Connection conn, String userId, String userPwd) {
		Customer customer = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID=? AND CUSTOMER_PWD=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				customer = new Customer();
				customer.setCustomer_Id(rset.getString("customer_Id"));
				customer.setCustomer_Pwd(rset.getString("customer_Pwd"));
				customer.setCustomer_Name(rset.getString("customer_Name"));
				customer.setCustomer_Phone(rset.getString("customer_Phone"));
				customer.setCustomer_Email(rset.getString("customer_Email"));
				customer.setCustomer_Rrn(rset.getString("customer_Rrn"));
				customer.setAdminCheck(rset.getInt("adminCheck"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customer;
	}
	
	public Customer findPw(Connection conn, String userId, String userName) {
		Customer customer = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID=? AND CUSTOMER_NAME=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userName);
			rset = pstmt.executeQuery();
			
			if(rset!=null) {
				while(rset.next()) {
					customer = new Customer();
					customer.setCustomer_Id(rset.getString("customer_Id"));
					customer.setCustomer_Pwd(rset.getString("customer_Pwd"));
					customer.setCustomer_Name(rset.getString("customer_Name"));
					customer.setCustomer_Phone(rset.getString("customer_Phone"));
					customer.setCustomer_Email(rset.getString("customer_Email"));
					customer.setCustomer_Rrn(rset.getString("customer_Rrn"));
					customer.setAdminCheck(rset.getInt("adminCheck"));
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return customer;
	}
	
	public int resetPassword(Connection conn, String rePwd, String userId, String userName) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE CUSTOMER SET customer_Pwd=? WHERE customer_Id=? AND customer_Name=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rePwd);
			pstmt.setString(2, userId);
			pstmt.setString(3, userName);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public Customer findId(Connection conn, String userName, String userEmail) {
		Customer customer = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM CUSTOMER WHERE customer_Name=? AND customer_Email=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userName);
			pstmt.setString(2, userEmail);
			rset = pstmt.executeQuery();
			if(rset!=null) {
				while(rset.next()) {
					customer = new Customer();
					customer.setCustomer_Id(rset.getString("customer_Id"));
					customer.setCustomer_Pwd(rset.getString("customer_Pwd"));
					customer.setCustomer_Name(rset.getString("customer_Name"));
					customer.setCustomer_Phone(rset.getString("customer_Phone"));
					customer.setCustomer_Email(rset.getString("customer_Email"));
					customer.setCustomer_Rrn(rset.getString("customer_Rrn"));
					customer.setAdminCheck(rset.getInt("adminCheck"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return customer;
	}
	
	public Customer selectCustomer(Connection conn, String customerId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID = ?";
		Customer customer = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, customerId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				customer = new Customer();
				customer.setCustomer_Id(rset.getString("customer_Id"));
				customer.setCustomer_Pwd(rset.getString("customer_Pwd"));
				customer.setCustomer_Name(rset.getString("customer_Name"));
				customer.setCustomer_Phone(rset.getString("customer_Phone"));
				customer.setCustomer_Email(rset.getString("customer_Email"));
				customer.setCustomer_Rrn(rset.getString("customer_Rrn"));
				customer.setAdminCheck(rset.getInt("adminCheck"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return customer;
	}
	
	public int updateCustomer(Connection conn, Customer customer) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE CUSTOMER SET CUSTOMER_NAME = ?, CUSTOMER_PWD = ?, CUSTOMER_PHONE = ?, CUSTOMER_EMAIL = ? WHERE CUSTOMER_ID = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, customer.getCustomer_Name());
			pstmt.setString(2, customer.getCustomer_Pwd());
			pstmt.setString(3, customer.getCustomer_Phone());
			pstmt.setString(4, customer.getCustomer_Email());
			pstmt.setString(5, customer.getCustomer_Id());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int deleteCustomer(Connection conn,String userId) {
		  int result = 0;
		  PreparedStatement pstmt = null;
		  String query = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID = ?";
		  try {
			  pstmt = conn.prepareStatement(query);
			  pstmt.setString(1, userId);
			  result = pstmt.executeUpdate();
		  } catch (Exception e) {
			  e.printStackTrace();
		  } finally {
			  JDBCTemplate.close(pstmt);
		  }
		  return result;
	  }
	
	public boolean phoneCheck(Connection conn, String phoneNum) {
		boolean x = false;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM CUSTOMER WHERE CUSTOMER_PHONE=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, phoneNum);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				x = true;
				return x;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return x;
	}
	
	public boolean emailCheck(Connection conn, String email) {
		boolean x = false;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM CUSTOMER WHERE CUSTOMER_EMAIL=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				x = true;
				return x;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return x;
	}
	
	public ArrayList<Customer> adminCustomerList(Connection conn, int currentPage, int recordCountPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Customer> CList = null;
		String query= "SELECT * FROM (SELECT CUSTOMER.*, ROW_NUMBER() OVER(ORDER BY CUSTOMER_NAME DESC) AS NUM FROM CUSTOMER) WHERE NUM BETWEEN ? AND ?";
		int start = currentPage*recordCountPerPage - (recordCountPerPage -1);
		int end = currentPage*recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			CList = new ArrayList<Customer>();
			while (rset.next()) {
				Customer customer = new Customer();
				customer.setCustomer_Id(rset.getString("customer_Id"));
				customer.setCustomer_Pwd(rset.getString("customer_Pwd"));
				customer.setCustomer_Name(rset.getString("customer_Name"));
				customer.setCustomer_Phone(rset.getString("customer_Phone"));
				customer.setCustomer_Email(rset.getString("customer_Email"));
				customer.setCustomer_Rrn(rset.getString("customer_Rrn"));
				customer.setAdminCheck(rset.getInt("adminCheck"));
				CList.add(customer);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
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
	         sb.append("<a href='/admin/customerList?currentPage="+(startNavi-1) + "'> < </a>");
	      }
	      // 1~ 10까지의 숫자를 for문을 이용해서 생성하고 만들어주는 부분
	      for (int i = startNavi; i <= endNavi; i++) {
	         //i가 현재 페이지일 떄, 숫자 굵게만들기 = <b></b>
	         if(i == currentPage) {
	            sb.append("<a href='/admin/customerList?currentPage="+i+"'><b> " + i + " </b></a>");
	         } else { 
	            sb.append("<a href='/admin/customerList?currentPage="+i+"'> " + i + " </a>");
	         }
	      }// 다음 버튼 누를 수 있을 때, 다음 버튼을 생성하는 부분
	      if (needNext) {
	         sb.append("<a href='/admin/customerList?currentPage=" + (endNavi + 1) + "'> > </a>");
	      }
	      //태그를 StringBuilder의 append() 메소드를 이용해서 붙인 후에
	      //toString() 메소드를 이용하여 String으로 만들어서 리턴
	      return sb.toString();
	   };
	   
	   public int totalCount(Connection conn) {
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      String query = "SELECT COUNT(*) AS TOTALCOUNT FROM CUSTOMER";
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
	   
	   public int kickOut(Connection conn, String userId) {
		   PreparedStatement pstmt = null;
		   String query = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID=?";
		   
		   int result = 0;
		   try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		   return result;
	   }
}
