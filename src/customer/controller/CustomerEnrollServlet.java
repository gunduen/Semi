package customer.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.model.service.CustomerService;
import customer.model.vo.Customer;

/**
 * Servlet implementation class CustomerEnrollServlet
 */
@WebServlet("/customer/enroll")
public class CustomerEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerEnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		Customer customer = new Customer();
		customer.setCustomer_Id(request.getParameter("userId"));
		customer.setCustomer_Pwd(request.getParameter("userPwd"));
		customer.setCustomer_Name(request.getParameter("userName"));
		customer.setCustomer_Phone(request.getParameter("firstPhone")+"-"+request.getParameter("secondPhone")+"-"+request.getParameter("thirdPhone"));
		customer.setCustomer_Email(request.getParameter("emailId")+request.getParameter("emailTag"));
		customer.setCustomer_Rrn(request.getParameter("userRrnFirst")+"-"+request.getParameter("userRrnSecond"));
		
		System.out.println(customer.toString());
		
		result = new CustomerService().insertCustomer(customer);
		
		if(result>0) {
			response.sendRedirect("/index.jsp");
		}else {
			response.sendRedirect("/enrollError.jsp");
		}
	}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
