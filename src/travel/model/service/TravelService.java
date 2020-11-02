package travel.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import driver.model.vo.Driver;
import travel.model.dao.TravelDAO;
import travel.model.vo.Travel;

public class TravelService {

	private JDBCTemplate factory;
	public TravelService() {
		factory = JDBCTemplate.getConnection();
	}

	public int insertTravel(Travel travel, String customerId) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new TravelDAO().insertTravel(conn, travel, customerId);
		}catch(SQLException e) {
			e.printStackTrace();

			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		}finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	public int insertBaseTravel(String Driver_Id,String Driver_Name) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = new TravelDAO().insertBaseTravel(conn,Driver_Id,Driver_Name);
		}catch(SQLException e) {
			e.printStackTrace();
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		}finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	public ArrayList<Driver> selectList(String sido,String packageDate){
		Connection conn = null;
		ArrayList<Driver> tList = null;
		try {
			conn = factory.createConnection();
			tList = new TravelDAO().selectList(conn,sido,packageDate);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return tList;
	}
	public ArrayList<Travel> selectTravelList(String customerId) {
		Connection conn = null;
		ArrayList<Travel> rList = null;
		try {
			conn = factory.createConnection();
			rList = new TravelDAO().selectTravelList(conn,customerId);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return rList;
	}
	public ArrayList<Travel> selctMyTravel(int packageCode){
		Connection conn = null;
		ArrayList<Travel> tdList = null;
		try {
			conn = factory.createConnection();
			tdList = new TravelDAO().selctMyTravel(conn,packageCode);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return tdList;
	}
	public int deleteTravel(int package_Code) {
		int result = 0;
		Connection conn = null;
		try {
			conn=factory.createConnection();
			result=new TravelDAO().deleteTravel(conn,package_Code);
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
}
