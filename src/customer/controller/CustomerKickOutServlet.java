package customer.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.model.service.CustomerService;

/**
 * Servlet implementation class CustomerKickOutServlet
 */
@WebServlet("/customer/kickout")
public class CustomerKickOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerKickOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userId = request.getParameter("customer_Id");
		int result = new CustomerService().kickOut(userId);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if(result>0) {
			out.println("<script>alert('"+userId+"님이 회원탈퇴 처리 되었습니다.');");
			out.println("location.href='/admin/customerList';</script>");
		}else {
			out.println("<script><alert('회원탈퇴에 실패하였습니다.');");
			out.println("location.href='/admin/customerList';</script>");
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
