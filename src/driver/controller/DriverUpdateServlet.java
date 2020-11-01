package driver.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import driver.model.service.DriverService;
import driver.model.vo.Driver;

/**
 * Servlet implementation class DriverUpdateServlet
 */
@WebServlet("/driver/update")
public class DriverUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Driver driver = new Driver();
		driver.setDriverId(request.getParameter("driverId"));
		driver.setDriverPwd(request.getParameter("driverPwd"));
		driver.setDriverName(request.getParameter("driverName"));
		driver.setDriverPhone(request.getParameter("driverPhone"));
		driver.setDriverEmail(request.getParameter("driverEmail"));
		driver.setDriverHome(request.getParameter("driverHome"));
		driver.setDriverArea(request.getParameter("driverArea"));
		driver.setDriverRrn(request.getParameter("driverRrn"));
		driver.setDriverSelfInfo(request.getParameter("driverSelfIntro"));
		driver.setDriverLicense(request.getParameter("driverLicense"));
		driver.setDriverBLicense(request.getParameter("driverBlicense"));
		driver.setDriverInfoImage(request.getParameter("driverInfoImage"));
		
		
		
		int result = new DriverService().updateDriver(driver);
		
		if(result >0) {
			response.sendRedirect("/index.jsp");
			
		}else {
			response.sendRedirect("/login/driverError.jsp");
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
