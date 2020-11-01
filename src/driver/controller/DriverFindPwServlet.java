package driver.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import driver.model.service.DriverService;
import driver.model.vo.Driver;

/**
 * Servlet implementation class DriverFindPwServlet
 */
@WebServlet("/driver/findpw")
public class DriverFindPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverFindPwServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String driverId = request.getParameter("driverId");
		String driverName = request.getParameter("driverName");
		int result = 0;
		String rePwd = "";
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		for(int i=0;i<4;i++) {
			rePwd += (int)(Math.random()*9+1);
		}
		result = new DriverService().resetPassword(rePwd, driverId, driverName);
		if(result>0) {
			Driver driver = new DriverService().findPw(driverId, driverName);
			System.out.println(driver.toString());
			if(driver!=null) {
				request.setAttribute("driver", driver);
				RequestDispatcher view = request.getRequestDispatcher("/login/driverSearchPw.jsp");
				view.forward(request, response);
			}
		}else {
			out.println("<script> alert('이름 또는 아이디가 맞지 않습니다. 다시 확인해주세요');");
			out.println("history.back(); </script>");
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
