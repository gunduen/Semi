package review.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customer.model.vo.Customer;
import review.model.service.ReviewService;
import travel.model.service.TravelService;
import travel.model.vo.Travel;

/**
 * Servlet implementation class ReviewInsertServlet
 */
@WebServlet("/review/insert")
public class ReviewInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String subject = request.getParameter("subject");
		System.out.println(subject);
		String contents = request.getParameter("contents");
		System.out.println(contents);
		String area = request.getParameter("area");
		System.out.println(area);
		int packageCode = Integer.parseInt(request.getParameter("packageCode"));
		System.out.println(packageCode);
		
		
//		int confirm = Integer.parseInt(request.getParameter("confirm"));
		
		HttpSession session  = request.getSession();
		if (session != null && (session.getAttribute("customer") != null)) {
			String customerId = ((Customer)session.getAttribute("customer")).getCustomer_Id();
			System.out.println(customerId);

			int result = new ReviewService().insertReview(subject, contents, customerId, area, packageCode);
			System.out.println(result);
			if (result > 0) {
				int resulta = new TravelService().updateTravel(packageCode); // traveldao (reviewCheck) = y/n 추가 해서 와이이면 리뷰작성 , 엔이면 리뷰 안작성
				if (resulta >0) {
					System.out.println(area);
					response.sendRedirect("/review/list?reviewArea=서울");
				}
			} else {
				RequestDispatcher view = request.getRequestDispatcher("/review/reviewError.html");
				view.forward(request, response);
			}
		} else {
			response.sendRedirect("/review/reviewError.html");
		}
//		else {
//			RequestDispatcher view = request.getRequestDispatcher("/login/loginError.html");
//			view.forward(request, response);
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
