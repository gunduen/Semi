package travel.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import driver.model.vo.Driver;
import travel.model.service.TravelService;
import travel.model.vo.Travel;

/**
 * Servlet implementation class TravelListServlet
 */
@WebServlet("/travel/travelList")
public class TravelListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TravelListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Travel travel = new Travel();
//		travel.setPackage_Area(request.getParameter("sido"));
		
		String sido = request.getParameter("sido");
		String address = request.getParameter("address");
		String packageDate = request.getParameter("package_Date");
		String coords = request.getParameter("coords");
		
		String coordx = coords.substring(1,coords.indexOf(","));
		String coordy = coords.substring(coords.indexOf(" ")+1,coords.indexOf(")"));
		
		System.out.println(sido);
		System.out.println(address);
		System.out.println(packageDate);
		System.out.println(coords);
		System.out.println(coordx);
		System.out.println(coordy);
		
		ArrayList<Driver> tList = new TravelService().selectList(sido,packageDate);
		if(!tList.isEmpty()) {
			request.setAttribute("tList", tList);
			request.setAttribute("sido", sido);
			request.setAttribute("address", address);
			request.setAttribute("packageDate", packageDate);
			request.setAttribute("coordx", coordx);
			request.setAttribute("coordy", coordy);
			RequestDispatcher view = request.getRequestDispatcher("/travel/Travel.jsp");
			view.forward(request, response);
		}
		else {
			request.getRequestDispatcher("error");
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
