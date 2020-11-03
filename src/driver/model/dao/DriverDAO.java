package driver.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import driver.model.vo.Driver;

public class DriverDAO {
	public int insertDriver(Connection conn, Driver driver) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO DRIVER VALUES(?,?,?,?,?,?,?,0,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, driver.getDriverId());
			pstmt.setString(2, driver.getDriverPwd());
			pstmt.setString(3, driver.getDriverName());
			pstmt.setString(4, driver.getDriverPhone());
			pstmt.setString(5, driver.getDriverEmail());
			pstmt.setString(6, driver.getDriverHome());
			pstmt.setString(7, driver.getDriverRrn());
			pstmt.setString(8, driver.getDriverSelfInfo());
			pstmt.setString(9, driver.getDriverLicense());
			pstmt.setString(10, driver.getDriverLicense_path());
			pstmt.setString(11, driver.getDriverBLicense());
			pstmt.setString(12, driver.getDriverBLicense_path());
			pstmt.setString(13, driver.getDriverInfoImage());
			pstmt.setString(14, driver.getDriverInfoImage_path());
			pstmt.setString(15, driver.getDriverArea());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public Driver loginDriver(Connection conn, String userId, String userPwd) {
		Driver driver = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query="SELECT * FROM DRIVER WHERE DRIVER_ID=? AND DRIVER_PWD=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			rset = pstmt.executeQuery();
			if(rset!=null) {
				while(rset.next()) {
					driver = new Driver();
					driver.setDriverId(rset.getString("driver_Id"));
					driver.setDriverPwd(rset.getString("driver_Pwd"));
					driver.setDriverName(rset.getString("driver_Name"));
					driver.setDriverPhone(rset.getString("driver_Phone"));
					driver.setDriverEmail(rset.getString("driver_Email"));
					driver.setDriverHome(rset.getString("driver_Home"));
					driver.setDriverRrn(rset.getString("driver_Rrn"));
					driver.setDriverCheck(rset.getString("driver_Check").charAt(0));
					driver.setDriverSelfInfo(rset.getString("driver_SelfIntro"));
					driver.setDriverLicense(rset.getString("driver_License"));
					driver.setDriverBLicense(rset.getString("driver_bLicense"));
					driver.setDriverInfoImage(rset.getString("driver_InfoImage"));
					driver.setDriverArea(rset.getString("driver_Area"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn);
		}
		return driver;
	}
	
	public int deleteDriver(Connection conn,String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "DELETE FROM DRIVER WHERE DRIVER_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
													
	}
	
	public int resetPassword(Connection conn, String rePwd, String driverId, String driverName) {
		int result=0;
		PreparedStatement pstmt = null;
		String query = "UPDATE DRIVER SET DRIVER_PWD=? WHERE DRIVER_ID=? AND DRIVER_NAME=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rePwd);
			pstmt.setString(2, driverId);
			pstmt.setString(3, driverName);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public Driver findPw(Connection conn, String driverId, String driverName) {
		Driver driver = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM DRIVER WHERE DRIVER_ID=? AND DRIVER_NAME = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, driverId);
			pstmt.setString(2, driverName);
			rset = pstmt.executeQuery();
			if(rset!=null) {
				while(rset.next()) {
					driver = new Driver();
					driver.setDriverId(rset.getString("driver_Id"));
					driver.setDriverPwd(rset.getString("driver_Pwd"));
					driver.setDriverName(rset.getString("driver_Name"));
					driver.setDriverPhone(rset.getString("driver_Phone"));
					driver.setDriverEmail(rset.getString("driver_Email"));
					driver.setDriverHome(rset.getString("driver_Home"));
					driver.setDriverRrn(rset.getString("driver_Rrn"));
					driver.setDriverCheck(rset.getString("driver_Check").charAt(0));
					driver.setDriverSelfInfo(rset.getString("driver_SelfIntro"));
					driver.setDriverLicense(rset.getString("driver_License"));
					driver.setDriverBLicense(rset.getString("driver_bLicense"));
					driver.setDriverInfoImage(rset.getString("driver_InfoImage"));
					driver.setDriverArea(rset.getString("driver_Area"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return driver;
	}
	
	public Driver findId(Connection conn, String driverName, String driverEmail) {
		Driver driver = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query  = "SELECT * FROM DRIVER WHERE DRIVER_NAME=? AND DRIVER_EMAIL=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, driverName);
			pstmt.setString(2, driverEmail);
			rset = pstmt.executeQuery();
			if(rset!=null) {
				while(rset.next()) {
					driver = new Driver();
					driver.setDriverId(rset.getString("driver_Id"));
					driver.setDriverPwd(rset.getString("driver_Pwd"));
					driver.setDriverName(rset.getString("driver_Name"));
					driver.setDriverPhone(rset.getString("driver_Phone"));
					driver.setDriverEmail(rset.getString("driver_Email"));
					driver.setDriverHome(rset.getString("driver_Home"));
					driver.setDriverRrn(rset.getString("driver_Rrn"));
					driver.setDriverCheck(rset.getString("driver_Check").charAt(0));
					driver.setDriverSelfInfo(rset.getString("driver_SelfIntro"));
					driver.setDriverLicense(rset.getString("driver_License"));
					driver.setDriverBLicense(rset.getString("driver_bLicense"));
					driver.setDriverInfoImage(rset.getString("driver_InfoImage"));
					driver.setDriverArea(rset.getString("driver_Area"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return driver;
	}
	
	public boolean idCheck(Connection conn, String id) {
		boolean x = false;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM DRIVER WHERE DRIVER_ID=?";
		
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
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return x;
	}
	
	public boolean phoneCheck(Connection conn, String phoneNum) {
		boolean x = false;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM DRIVER WHERE DRIVER_PHONE=?";
		
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
		String query = "SELECT * FROM DRIVER WHERE DRIVER_EMAIL = ?";
		
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
		}
		return x;
	}
	
	public Driver selectDriver(Connection conn, String driverId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM DRIVER WHERE DRIVER_ID = ?";
		Driver driver = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, driverId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				driver = new Driver();
				driver.setDriverId(rset.getString("driverId"));
				driver.setDriverPwd(rset.getString("driverPwd"));
				driver.setDriverName(rset.getString("driverName"));
				driver.setDriverPhone(rset.getString("driverPhone"));
				driver.setDriverEmail(rset.getString("driverEmail"));
				driver.setDriverHome(rset.getString("driverHome"));
				driver.setDriverArea(rset.getString("driverArea"));
				driver.setDriverRrn(rset.getString("driverRrn"));
				driver.setDriverCheck (rset.getInt("driverCheck"));
				driver.setDriverSelfInfo(rset.getString("driverSelfIntro"));
				driver.setDriverLicense(rset.getString("driverLicense"));
				driver.setDriverBLicense(rset.getString("driverBlicense"));
				driver.setDriverInfoImage(rset.getString("driverInfoImage"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return driver;
		
	}
	
	
	public int updateDriver(Connection conn, Driver driver) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE DRIVER SET DRIVER_PWD = ?, DRIVER_NAME = ?, DRIVER_PHONE = ?, DRIVER_EMAIL = ?, DRIVER_HOME = ?, DRIVER_AREA = ?, DRIVER_SELFINTRO = ?, DRIVER_LICENSE = ?, DRIVER_BLICENSE = ?, DRIVER_INFOIMAGE = ? WHERE DRIVER_ID = ? ";
	    try {
	    	pstmt = conn.prepareStatement(query);
	    	pstmt.setString(1, driver.getDriverPwd());
	    	pstmt.setString(2, driver.getDriverName());
	    	pstmt.setString(3, driver.getDriverPhone());
	    	pstmt.setString(4, driver.getDriverEmail());
	    	pstmt.setString(5, driver.getDriverHome());
	    	pstmt.setString(6, driver.getDriverArea());
	    	pstmt.setString(7, driver.getDriverSelfInfo());
	    	pstmt.setString(8, driver.getDriverLicense());
	    	pstmt.setString(9, driver.getDriverBLicense());
	    	pstmt.setString(10,driver.getDriverInfoImage());
	    	pstmt.setString(11,driver.getDriverId());
	    	
	    	result = pstmt.executeUpdate();
	    	
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }finally {
	    	JDBCTemplate.close(pstmt);
	    }
	    return result;
	}
	
	//admin
	
	public ArrayList<Driver> adminDriverList(Connection conn, int currentPage, int recordCountPerPage){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Driver> DList = null;
		String query = "SELECT * FROM (SELECT DRIVER.*, ROW_NUMBER() OVER(ORDER BY DRIVER_NAME DESC) AS NUM FROM DRIVER) WHERE NUM BETWEEN ? AND ?";
		int start = currentPage*recordCountPerPage - (recordCountPerPage -1);
		int end = currentPage*recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			DList = new ArrayList<Driver>();
			while(rset.next()) {
				Driver driverOne = new Driver();
				driverOne.setDriverId(rset.getString("DRIVER_ID"));
				driverOne.setDriverPwd(rset.getString("DRIVER_PWD"));
				driverOne.setDriverName(rset.getString("DRIVER_NAME"));
				driverOne.setDriverPhone(rset.getString("DRIVER_PHONE"));
				driverOne.setDriverEmail(rset.getString("DRIVER_EMAIL"));
				driverOne.setDriverHome(rset.getString("DRIVER_HOME"));
				driverOne.setDriverRrn(rset.getString("DRIVER_RRN"));
				driverOne.setDriverCheck(rset.getInt("DRIVER_CHECK"));
				driverOne.setDriverSelfInfo(rset.getString("DRIVER_SELFINTRO"));
				driverOne.setDriverLicense(rset.getString("DRIVER_LICENSE"));
				driverOne.setDriverBLicense(rset.getString("DRIVER_BLICENSE"));
				driverOne.setDriverInfoImage(rset.getString("DRIVER_INFOIMAGE"));
				driverOne.setDriverArea(rset.getString("DRIVER_AREA"));
				DList.add(driverOne);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return DList;
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
	         sb.append("<a href='/admin/driverList?currentPage="+(startNavi-1) + "'> < </a>");
	      }
	      // 1~ 10까지의 숫자를 for문을 이용해서 생성하고 만들어주는 부분
	      for (int i = startNavi; i <= endNavi; i++) {
	         //i가 현재 페이지일 떄, 숫자 굵게만들기 = <b></b>
	         if(i == currentPage) {
	            sb.append("<a href='/admin/driverList?currentPage="+i+"'><b> " + i + " </b></a>");
	         } else { 
	            sb.append("<a href='/admin/driverList?currentPage="+i+"'> " + i + " </a>");
	         }
	      }// 다음 버튼 누를 수 있을 때, 다음 버튼을 생성하는 부분
	      if (needNext) {
	         sb.append("<a href='/admin/driverList?currentPage=" + (endNavi + 1) + "'> > </a>");
	      }
	      //태그를 StringBuilder의 append() 메소드를 이용해서 붙인 후에
	      //toString() 메소드를 이용하여 String으로 만들어서 리턴
	      return sb.toString();
	   };
	   
	   public int totalCount(Connection conn) {
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      String query = "SELECT COUNT(*) AS TOTALCOUNT FROM DRIVER";
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
	
}
