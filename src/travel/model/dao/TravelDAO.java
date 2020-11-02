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
	public ArrayList<Travel> selectTravelList(Connection conn,String customerId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Travel> rList = null;
		String query = "SELECT * FROM TRAVEL WHERE CUSTOMER_ID = ? ORDER BY PACKAGE_CODE";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, customerId);
			rset= pstmt.executeQuery();
			rList = new ArrayList<Travel>();
			while(rset.next()) {
				Travel travel = new Travel();
				travel.setPackage_Area(rset.getString("package_Area"));
				travel.setPackage_Pickup(rset.getString("package_Pickup"));
				travel.setPackage_TravelDate(rset.getString("package_TravelDate"));
				travel.setPackage_Utilization(rset.getString("package_Utilization"));
				travel.setDriver_Name(rset.getString("driver_Name"));
				travel.setPackage_Code(rset.getInt("package_Code"));
				travel.setCustomer_Id(rset.getString("customer_Id"));
				travel.setDriver_Id(rset.getString("driver_Id"));
				travel.setCoordx(rset.getString("coordx"));
				travel.setCoordy(rset.getString("coordy"));
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
}
