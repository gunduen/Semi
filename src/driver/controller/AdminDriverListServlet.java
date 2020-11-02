package driver.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.vo.PageData;
import driver.model.service.DriverService;
import driver.model.vo.Driver;

/**
 * Servlet implementation class AdminDriverListServlet
 */
@WebServlet("/admin/driverList")
public class AdminDriverListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDriverListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int currentPage = 0;
		if(request.getParameter("currnetPage") == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		PageData pageData = new DriverService().adminDriverList(currentPage);
		ArrayList<Driver> DList = pageData.getDpageList();
		
		if(!DList.isEmpty()) {
			request.setAttribute("DList", DList);
			request.setAttribute("pageNavi", pageData.getPageNavi());
			RequestDispatcher view = request.getRequestDispatcher("/admin/driver.jsp");
			view.forward(request, response);
		} else {
			response.sendRedirect("/admin/driver.jsp");
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
