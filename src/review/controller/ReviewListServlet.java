package review.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customer.model.vo.Customer;
import driver.model.vo.Driver;
import review.model.vo.PageData;
import review.model.service.ReviewService;
import review.model.vo.Review;
import travel.model.service.TravelService;
import travel.model.vo.Travel;

/**
 * Servlet implementation class ReviewListServlet
 */
@WebServlet("/review/list")
public class ReviewListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String area = request.getParameter("reviewArea");
		HttpSession session = request.getSession();
		
//		if (session.isNew()) {
//			if(request.getParameter("currnetPage") == null) {
//				currentPage = 1;
//			} else {
//				currentPage = Integer.parseInt(request.getParameter("currentPage"));
//			}
//			PageData pageData = new ReviewService().reviewList(currentPage, area);
//
//			ArrayList<Review> RList = pageData.getPageList();
//			if(!RList.isEmpty()) {
//				request.setAttribute("RList", RList);
//				request.setAttribute("pageNavi", pageData.getPageNavi());
//				RequestDispatcher view = request.getRequestDispatcher("/review/reviewList.jsp");
//				view.forward(request, response);
//			} else {
//				response.sendRedirect("/review/reviewList.jsp");
//			}
//		} else {
		String customerId = null;
		if (session.getAttribute("customer")!=null) {
			customerId = ((Customer)session.getAttribute("customer")).getCustomer_Id();
		} else if (session.getAttribute("driver") != null) {
			customerId = ((Driver)session.getAttribute("driver")).getDriverId();
		} else {
			customerId = null;
		}
		
		int currentPage = 0;
			if(request.getParameter("currnetPage") == null) {
				currentPage = 1;
			} else {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			PageData pageData = new ReviewService().reviewList(currentPage, area);
			ArrayList<Review> RList = pageData.getPageList();
			System.out.println(customerId);
			ArrayList<Travel> TList = new TravelService().selectTravelList(customerId);
//			System.out.println(TList.size());
//			System.out.println(RList.size());

			if(!RList.isEmpty()) {
				request.setAttribute("RList", RList);
				request.setAttribute("pageNavi", pageData.getPageNavi()); 
				if (!TList.isEmpty()) {
				request.setAttribute("rTravel", TList);
				}
				RequestDispatcher view = request.getRequestDispatcher("/review/reviewList.jsp");
				view.forward(request, response);
			} else {
				if (!TList.isEmpty()) {
					request.setAttribute("rTravel", TList);
				}System.out.println(TList);
				RequestDispatcher view = request.getRequestDispatcher("/review/reviewList.jsp");
				view.forward(request, response);
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
