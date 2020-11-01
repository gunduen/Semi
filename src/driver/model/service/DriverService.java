package driver.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.model.vo.PageData;
import common.JDBCTemplate;
import driver.model.dao.DriverDAO;
import driver.model.vo.Driver;

public class DriverService {
	private JDBCTemplate factory;
	public DriverService() {
		// TODO Auto-generated constructor stub
		factory = JDBCTemplate.getConnection();
	}
	public boolean idCheck(String id) {
		Connection conn = null;
		boolean x = false;
		
		try {
			conn = factory.createConnection();
			x = new DriverDAO().idCheck(conn, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		
		return x;
	}
	public int insertDriver(Driver driver) {
		int result = 0;
		Connection conn = null;
		System.out.println(driver.getDriverEmail());
		try {
			conn = factory.createConnection();
			result = new DriverDAO().insertDriver(conn, driver);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	public Driver loginDriver(String userId, String userPwd) {
		Driver driver = null;
		Connection conn = null;
		
		try {
			conn=factory.createConnection();
			driver = new DriverDAO().loginDriver(conn, userId, userPwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return driver;
	}
	
	public int deleteDriver(String userId) {
		int result = 0;
		try {
			Connection conn = factory.createConnection();
			
			result = new DriverDAO().deleteDriver(conn,userId);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int resetPassword(String rePwd, String driverId, String driverName) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			result = new DriverDAO().resetPassword(conn, rePwd, driverId, driverName);
			if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	public Driver findPw(String driverId, String driverName) {
		Driver driver = null;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			driver = new DriverDAO().findPw(conn, driverId, driverName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return driver;
	}
	public Driver findId(String driverName, String driverEmail) {
		Driver driver = null;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			driver = new DriverDAO().findId(conn, driverName, driverEmail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return driver;
	}
	
	public boolean phoneCheck(String phoneNum) {
		Connection conn = null;
		boolean x = false;
		
		try {
			conn = factory.createConnection();
			x = new DriverDAO().phoneCheck(conn, phoneNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return x;
	}
	
	public boolean emailCheck(String email) {
		boolean x = false;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			x = new DriverDAO().emailCheck(conn, email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return x;
	}
	
	public Driver selectDriver(String driverId) {
		Connection conn = null;
		Driver driver = null;
		try {
			conn = factory.createConnection();
			driver = new DriverDAO().selectDriver(conn, driverId);
			JDBCTemplate.close(conn);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return driver;
		
	}
	
	public int updateDriver(Driver driver) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new DriverDAO().updateDriver(conn, driver);
			JDBCTemplate.close(conn);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public PageData adminDriverList(int currentPage) {
		Connection conn = null;
		ArrayList<Driver> DList = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 10;
		PageData pd = new PageData();
		try {
			conn=factory.createConnection();
			pd.setDpageList(new DriverDAO().adminDriverList(conn, currentPage,recordCountPerPage));
			pd.setPageNavi(new DriverDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
}
