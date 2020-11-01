package driver.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import driver.model.service.DriverService;
import driver.model.vo.Driver;

/**
 * Servlet implementation class DriverFindIdServlet
 */
@WebServlet("/driver/findid")
public class DriverFindIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverFindIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String driverName = request.getParameter("driverName");
		String driverEmail = request.getParameter("driverEmail");
		
		Driver driver = new DriverService().findId(driverName, driverEmail);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(driver!=null) {
			out.println("<script>alert('"+"회원님의 아이디는"+driver.getDriverId()+"입니다."+"')");
			out.println("history.back();</script>");
		}else {
			out.println("<script>alert('존재하지 않는 회원입니다.')");
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
