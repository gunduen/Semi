package travel.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import driver.model.vo.Driver;
import travel.model.vo.Travel;

public class TravelDAO {

	public int insertTravel(Connection conn, Travel travel, String customerId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL,?,?,?,SYSDATE,?,1,?,?,?,?,?)";
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, travel.getPackage_Area());
			pstmt.setString(2, travel.getPackage_Utilization());
			pstmt.setString(3, travel.getPackage_Pickup());
			pstmt.setString(4, travel.getPackage_TravelDate());
			pstmt.setString(5, customerId);
			pstmt.setString(6, travel.getDriver_Name());
			pstmt.setString(7, travel.getDriver_Id());
			pstmt.setString(8, travel.getCoordx());
			pstmt.setString(9, travel.getCoordy());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int insertBaseTravel(Connection conn,String Driver_Id,String Driver_Name) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO TRAVEL VALUES(SEQ_TRAVEL.NEXTVAL,'1','1','1',SYSDATE,'1','1','admin',?,?,'1','1')";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, Driver_Name);
			pstmt.setString(2, Driver_Id);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public ArrayList<Driver> selectList(Connection conn,String sido,String packageDate){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Driver> tList = null;
		String query = "SELECT DISTINCT DRIVER_NAME,DRIVER_ID FROM (SELECT * FROM TRAVEL WHERE DRIVER_NAME NOT IN (SELECT DRIVER_NAME FROM TRAVEL WHERE PACKAGE_TRAVELDATE = ?)) INTERSECT SELECT DRIVER_NAME,DRIVER_ID FROM DRIVER WHERE DRIVER_CHECK='1' AND DRIVER_AREA = ? ORDER BY DRIVER_NAME";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, sido);
			pstmt.setString(2, packageDate);
			rset=pstmt.executeQuery();
			tList = new ArrayList<Driver>();
			while(rset.next()) {
				Driver driver = new Driver();
				driver.setDriverName(rset.getString("driver_Name"));
				driver.setDriverId(rset.getString("driver_Id"));
				tList.add(driver);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return tList;
	}
	
	
	//jimin
	public ArrayList<Travel> selectTravelList(Connection conn,String customerId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Travel> rList = null;
		String query = "SELECT * FROM TRAVEL WHERE CUSTOMER_ID = ? AND REVIEWYN = 'N' ORDER BY PACKAGE_CODE";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, customerId);
			rset= pstmt.executeQuery();
			rList = new ArrayList<Travel>();
			while(rset.next()) {
				Travel travel = new Travel();
				travel.setPackage_Code(rset.getInt("PACKAGE_CODE"));
				travel.setPackage_Area(rset.getString("PACKAGE_AREA"));
				travel.setPackage_Utilization(rset.getString("PACKAGE_UTILIZATION"));
				travel.setPackage_Pickup(rset.getString("PACKAGE_PICKUP"));
				travel.setPackage_Date(rset.getDate("PACKAGE_DATE"));
				travel.setPackage_TravelDate(rset.getString("PACKAGE_TRAVELDATE"));
				travel.setPakcage_Confirm(rset.getInt("PACKAGE_CONFIRM"));
				travel.setCustomer_Id(rset.getString("CUSTOMER_ID"));
				travel.setDriver_Name(rset.getString("DRIVER_NAME"));
				travel.setDriver_Id(rset.getString("DRIVER_ID"));
				travel.setCoordx(rset.getString("COORDX"));
				travel.setCoordy(rset.getString("COORDY"));
				travel.setReviewYN(rset.getString("REVIEWYN"));
				rList.add(travel);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return rList;
	}
	
	public ArrayList<Travel> selectUpdateTravelList(Connection conn, String customerId, int packageCode){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Travel> rList = null;
		String query = "SELECT * FROM TRAVEL WHERE CUSTOMER_ID = ? AND PACKAGE_CODE = ?AND REVIEWYN = 'N' ORDER BY PACKAGE_CODE";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, customerId);
			pstmt.setInt(2, packageCode);
			rset= pstmt.executeQuery();
			rList = new ArrayList<Travel>();
			while(rset.next()) {
				Travel travel = new Travel();
				travel.setPackage_Code(rset.getInt("PACKAGE_CODE"));
				travel.setPackage_Area(rset.getString("PACKAGE_AREA"));
				travel.setPackage_Utilization(rset.getString("PACKAGE_UTILIZATION"));
				travel.setPackage_Pickup(rset.getString("PACKAGE_PICKUP"));
				travel.setPackage_Date(rset.getDate("PACKAGE_DATE"));
				travel.setPackage_TravelDate(rset.getString("PACKAGE_TRAVELDATE"));
				travel.setPakcage_Confirm(rset.getInt("PACKAGE_CONFIRM"));
				travel.setCustomer_Id(rset.getString("CUSTOMER_ID"));
				travel.setDriver_Name(rset.getString("DRIVER_NAME"));
				travel.setDriver_Id(rset.getString("DRIVER_ID"));
				travel.setCoordx(rset.getString("COORDX"));
				travel.setCoordy(rset.getString("COORDY"));
				travel.setReviewYN(rset.getString("REVIEWYN"));
				rList.add(travel);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return rList;
	}
	
	public ArrayList<Travel> adminTravelList(Connection conn, int currentPage, int recordCountPerPage){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Travel> TList = null;
		String query = "SELECT * FROM (SELECT TRAVEL.*, ROW_NUMBER() OVER(ORDER BY PACKAGE_CODE DESC) AS NUM FROM TRAVEL) WHERE NUM BETWEEN ? AND ?";
		int start = currentPage*recordCountPerPage - (recordCountPerPage -1);
		int end = currentPage*recordCountPerPage;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			TList = new ArrayList<Travel>();
			while(rset.next()) {
				Travel travelOne = new Travel();
				travelOne.setPackage_Code(rset.getInt("PACKAGE_CODE"));
				travelOne.setPackage_Area(rset.getString("PACKAGE_AREA"));
				travelOne.setPackage_Utilization(rset.getString("PACKAGE_UTILIZATION"));
				travelOne.setPackage_Pickup(rset.getString("PACKAGE_PICKUP"));
				travelOne.setPackage_Date(rset.getDate("PACKAGE_DATE"));
				travelOne.setPackage_TravelDate(rset.getString("PACKAGE_TRAVELDATE"));
				travelOne.setPakcage_Confirm(rset.getInt("PACKAGE_CONFIRM"));
				travelOne.setCustomer_Id(rset.getString("CUSTOMER_ID"));
				travelOne.setDriver_Name(rset.getString("DRIVER_NAME"));
				travelOne.setDriver_Id(rset.getString("DRIVER_ID"));
				travelOne.setCoordx(rset.getString("COORDX"));
				travelOne.setCoordy(rset.getString("COORDY"));
				travelOne.setReviewYN(rset.getString("REVIEWYN"));
				TList.add(travelOne);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return TList;
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
	         sb.append("<a href='/admin/travelList?currentPage="+(startNavi-1) + "'> < </a>");
	      }
	      // 1~ 10까지의 숫자를 for문을 이용해서 생성하고 만들어주는 부분
	      for (int i = startNavi; i <= endNavi; i++) {
	         //i가 현재 페이지일 떄, 숫자 굵게만들기 = <b></b>
	         if(i == currentPage) {
	            sb.append("<a href='/admin/travelList?currentPage="+i+"'><b> " + i + " </b></a>");
	         } else { 
	            sb.append("<a href='/admin/travelList?currentPage="+i+"'> " + i + " </a>");
	         }
	      }// 다음 버튼 누를 수 있을 때, 다음 버튼을 생성하는 부분
	      if (needNext) {
	         sb.append("<a href='/admin/travelList?currentPage=" + (endNavi + 1) + "'> > </a>");
	      }
	      //태그를 StringBuilder의 append() 메소드를 이용해서 붙인 후에
	      //toString() 메소드를 이용하여 String으로 만들어서 리턴
	      return sb.toString();
	   };
	   
	   public int totalCount(Connection conn) {
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      String query = "SELECT COUNT(*) AS TOTALCOUNT FROM TRAVEL";
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
	
	//
	
	
	public ArrayList<Travel> selctMyTravel(Connection conn,int packageCode){
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Travel> tdList = null;
		String query = "SELECT * FROM TRAVEL WHERE PACKAGE_CODE = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, packageCode);
			rset = pstmt.executeQuery();
			tdList = new ArrayList<Travel>();
			while(rset.next()) {
				Travel travel = new Travel();
				travel.setPackage_Area(rset.getString("package_Area"));
				travel.setPackage_Pickup(rset.getString("package_Pickup"));
				travel.setPackage_TravelDate(rset.getString("package_TravelDate"));
				travel.setPackage_Utilization(rset.getString("package_Utilization"));
				travel.setDriver_Name(rset.getString("driver_Name"));
				travel.setPackage_Code(rset.getInt("package_Code"));
				travel.setCustomer_Id(rset.getString("customer_Id"));
				travel.setPackage_Date(rset.getDate("package_Date"));
				travel.setDriver_Id(rset.getString("driver_Id"));
				travel.setCoordx(rset.getString("coordx"));
				travel.setCoordy(rset.getString("coordy"));
				tdList.add(travel);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return tdList;
	}
	public int deleteTravel(Connection conn, int package_Code) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "DELETE FROM TRAVEL WHERE PACKAGE_CODE = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, package_Code);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}
	
	public int updateTravel(Connection conn, int packageCode) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE TRAVEL SET REVIEWYN = 'Y' WHERE PACKAGE_CODE = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, packageCode);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
}
