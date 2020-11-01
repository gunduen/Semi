package customer.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customer.model.service.CustomerService;
import customer.model.vo.Customer;

/**
 * Servlet implementation class CustomerLoginServlet
 */
@WebServlet("/customer/login")
public class CustomerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		Customer customer = new CustomerService().loginCustomer(userId, userPwd);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if(customer!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("customer", customer);
			System.out.println("고객 : "+customer.getCustomer_Id()+"님 로그인");
			response.sendRedirect("/index.jsp");
		}else {
			out.println("<script>alert('아이디 또는 비밀번호가 맞지 않아요!')");
			out.println("history.back();</script>");
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
