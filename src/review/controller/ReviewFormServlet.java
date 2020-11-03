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
		ArrayList<Review> reviewList = null;
		int num = 0;
		
		if (session.getAttribute("customer")!=null) {
			customerId = ((Customer)session.getAttribute("customer")).getCustomer_Id();
		} else if (session.getAttribute("driver") != null) {
			customerId = ((Driver)session.getAttribute("driver")).getDriverId();
		} else {
			customerId = null;
		}
		
		if (request.getParameter("reviewNo") != null) {
			num = Integer.parseInt(request.getParameter("reviewNo"));
			reviewList = new ReviewService().reviewBeList(customerId, num);
			System.out.println(num);
		}else {
			reviewList = new ReviewService().reviewBeList(customerId, num);
			System.out.println(num);
		}
		
		ArrayList<Travel> travelList = new TravelService().selectTravelList(customerId);
		System.out.println(num);
		
		System.out.println(travelList);
		System.out.println(reviewList);
		
		if (!travelList.isEmpty()) {
			System.out.println(num);
			request.setAttribute("travelList", travelList);
			request.setAttribute("RList", reviewList);
			if (reviewList.size() == travelList.size()) {
				request.setAttribute("checkReview", false);
				request.getRequestDispatcher("/review/reviewList.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/review/reviewForm.jsp").forward(request, response);
			}
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
