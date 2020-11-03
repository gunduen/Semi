package comment.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comment.model.service.CommentService;
import comment.model.vo.Comment;
import review.model.service.ReviewService;
import review.model.vo.Review;
import customer.model.vo.Customer;

/**
 * Servlet implementation class CommentInsertServlet
 */
@WebServlet("/comment/insert")
public class CommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String commentContents = request.getParameter("commentContents");
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		String customerId = request.getParameter("customerId");
		String driverId = request.getParameter("driverId");
		
		Review review = new ReviewService().selectReview(reviewNo);
	
		if(commentContents == null || commentContents.trim().equals("")){
			commentContents = "0";
			}
		HttpSession session = request.getSession(); // session에서 userId 가져와야
		if (session != null && ((session.getAttribute("customer") != null) || session.getAttribute("driver") != null )) {
			
//			String customerId = ((Comment)session.getAttribute("comment")).getCustomerId();
//			String driverId = ((Comment)session.getAttribute("comment")).getDriverId();
			int result = new CommentService().insertComment(commentContents, customerId, driverId, reviewNo);
			
			if (result > 0) {
				request.setAttribute("review", review);
				RequestDispatcher reviewView = request.getRequestDispatcher("/review/reviewDetail.jsp");
				reviewView.forward(request, response);
			} else {
				request.getRequestDispatcher("/review/reviewError.html");
			}
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
