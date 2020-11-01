package customer.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import admin.model.vo.PageData;
import common.JDBCTemplate;
import customer.model.dao.CustomerDAO;
import customer.model.vo.Customer;

public class CustomerService {
	private JDBCTemplate factory;
	
	public CustomerService() {
		factory = JDBCTemplate.getConnection();
	}
	
	public int insertCustomer(Customer customer) {
		int result = 0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new CustomerDAO().insertCustomer(conn, customer);
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
	
	public boolean idCheck(String id) {
		Connection conn = null;
		boolean x = false;;
		
		try {
			conn = factory.createConnection();
			x = new CustomerDAO().idCheck(conn, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return x;
	}
	
	public Customer loginCustomer(String userId, String userPwd) {
		Customer customer = null;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			customer = new CustomerDAO().loginCustomer(conn, userId, userPwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
	
		return customer;
	}
	public Customer findPw(String userId, String userName) {
		Customer customer = null;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			customer = new CustomerDAO().findPw(conn, userId, userName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return customer;
	}
	public Customer findId(String userName, String userEmail) {
		Customer customer = null;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			customer = new CustomerDAO().findId(conn, userName, userEmail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return customer;
	}
	
	public int resetPassword(String rePwd, String userId, String userName) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn=factory.createConnection();
			result = new CustomerDAO().resetPassword(conn, rePwd, userId, userName);
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
	
	public Customer selectCustomer(String customerId) {
		Connection conn = null;
		Customer customer = null;
		try {
			conn = factory.createConnection();
			customer = new CustomerDAO().selectCustomer(conn, customerId);
			JDBCTemplate.close(conn);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;
	}
	
	public int updateCustomer(Customer customer) {
		int result =0;
		Connection conn = null;
		try {
			conn = factory.createConnection();
			result = new CustomerDAO().updateCustomer(conn, customer);
			JDBCTemplate.close(conn);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteCustomer(String userId) {
		int result = 0;
		try {
			Connection conn = factory.createConnection();
			
			result = new CustomerDAO().deleteCustomer(conn,userId);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public boolean phoneCheck(String phoneNum) {
		Connection conn = null;
		boolean x = false;
		
		try {
			conn = factory.createConnection();
			x = new CustomerDAO().phoneCheck(conn, phoneNum);
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
			x = new CustomerDAO().emailCheck(conn, email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(conn);
		}
		return x;
	}
	
	public PageData adminCustomerList(int currentPage) {
		Connection conn = null;
		ArrayList<Customer> CList = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 10;
		PageData pd = new PageData();
		try {
			conn=factory.createConnection();
			pd.setCpageList(new CustomerDAO().adminCustomerList(conn, currentPage,recordCountPerPage));
			pd.setPageNavi(new CustomerDAO().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
}
