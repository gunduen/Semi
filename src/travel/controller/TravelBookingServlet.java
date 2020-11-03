package travel.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import customer.model.vo.Customer;
import travel.model.service.TravelService;
import travel.model.vo.Travel;

/**
 * Servlet implementation class TravelBookingServlet
 */
@WebServlet("/travel/insert")
public class TravelBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TravelBookingServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		request.setCharacterEncoding("utf-8");
		Travel travel = new Travel();
		travel.setPackage_Area(request.getParameter("sido"));
		travel.setPackage_Pickup(request.getParameter("roadAddr"));
		travel.setPackage_TravelDate(request.getParameter("package_Date"));
		travel.setPackage_Utilization(request.getParameter("package_Item"));
		travel.setDriver_Name(request.getParameter("driverName"));
		travel.setDriver_Id(request.getParameter("driverId"));
		travel.setCoordx(request.getParameter("coordx"));
		travel.setCoordy(request.getParameter("coordy"));
		
		
		
		HttpSession session = request.getSession();
		if( session != null && ( session.getAttribute("customer")!=null)) {
			String customerId = ((Customer)session.getAttribute("customer")).getCustomer_Id();

			int result = new TravelService().insertTravel(travel,customerId);

			if ( result > 0) { response.sendRedirect("/index.jsp"); 
			}else {
				response.sendRedirect("error"); 
			}
		}else {
			RequestDispatcher view = request.getRequestDispatcher("error");
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
