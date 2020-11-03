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
 * Servlet implementation class ReviewModifyForm
 */
@WebServlet("/review/modifyForm")
public class ReviewModifyFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewModifyFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		System.out.println(reviewNo);
		
		HttpSession session = request.getSession();
		String customerId = null;
		
		if (session.getAttribute("customer")!=null) {
			customerId = ((Customer)session.getAttribute("customer")).getCustomer_Id();
		} else if (session.getAttribute("driver") != null) {
			customerId = ((Driver)session.getAttribute("driver")).getDriverId();
		} else {
			customerId = null;
		}
		
		Review review = new ReviewService().selectReview(reviewNo);
		int packageCode = review.getPackageCode();
		ArrayList<Travel> travelList = new TravelService().selectUpdateTravelList(customerId,packageCode);
		
		if ( review != null ) {
			request.setAttribute("review", review);
			request.setAttribute("travelList", travelList);
			request.getRequestDispatcher("/review/reviewModifyForm.jsp").forward(request, response);
			
		} else {
			request.getRequestDispatcher("/review/reviewList.jsp").forward(request, response);
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
