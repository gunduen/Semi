package review.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customer.model.vo.Customer;
import driver.model.vo.Driver;
import review.model.service.ReviewService;
import review.model.vo.Review;
import travel.model.service.TravelService;
import travel.model.vo.Travel;

/**
 * Servlet implementation class ReviewFormServlet
 */
@WebServlet("/review/form")
public class ReviewFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
//		Review review = new Review();
//		review.setCustomerId(request.getParameter("customerId"));
//		review.setReviewSubject(request.getParameter("reviewSubject"));
//		review.setReviewContents(request.getParameter("reviewContents"));
//		review.setReviewArea(request.getParameter("reviewArea"));
		HttpSession session = request.getSession();
		String customerId = null;
		
		if (session.getAttribute("customer")!=null) {
			customerId = ((Customer)session.getAttribute("customer")).getCustomer_Id();
		} else if (session.getAttribute("driver") != null) {
			customerId = ((Driver)session.getAttribute("driver")).getDriverId();
		} else {
			customerId = null;
		}
		
		ArrayList<Travel> TList = new TravelService().selectTravelList(customerId);
		ArrayList<Review> RList = new ReviewService().reviewBeList(customerId);
		System.out.println(TList);
		System.out.println(RList);
		
		if (!TList.isEmpty()) {
			request.setAttribute("rTravel", TList);
			request.setAttribute("RList", RList);
			request.getRequestDispatcher("/review/reviewForm.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/review/reviewError.html");
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
