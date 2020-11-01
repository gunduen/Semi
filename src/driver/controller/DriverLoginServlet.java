package driver.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import driver.model.service.DriverService;
import driver.model.vo.Driver;

/**
 * Servlet implementation class DriverLoginServlet
 */
@WebServlet("/driver/login")
public class DriverLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		Driver driver = new DriverService().loginDriver(userId, userPwd);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if(driver!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("driver", driver);
			response.sendRedirect("/index.jsp");
			System.out.println("기사 : "+driver.getDriverId()+"님 로그인");
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
