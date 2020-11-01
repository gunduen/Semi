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
 * Servlet implementation class updateServlet
 */
@WebServlet("/customer/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Customer customer = new Customer();
		customer.setCustomer_Id(request.getParameter("customerId"));
		customer.setCustomer_Pwd(request.getParameter("customerPwd"));
		customer.setCustomer_Name(request.getParameter("customerName"));
		customer.setCustomer_Phone(request.getParameter("customerPhone"));
		customer.setCustomer_Email(request.getParameter("customerEmail"));
		
		int result = new CustomerService().updateCustomer(customer);
		
		if(result >0) {
			response.sendRedirect("/index.jsp");
		} else {
			response.sendRedirect("/login/customerError.jsp");
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
