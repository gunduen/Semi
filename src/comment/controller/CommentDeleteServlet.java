package comment.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.model.service.CommentService;
import review.model.service.ReviewService;
import review.model.vo.Review;

/**
 * Servlet implementation class CommentDeleteServlet
 */
@WebServlet("/comment/delete")
public class CommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		Review review = new ReviewService().selectReview(reviewNo);
		System.out.println(review);
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
//		System.out.println(commentNo);
		int result = new CommentService().deleteComment(commentNo);
//		System.out.println(result);
		if (result > 0) {
			request.setAttribute("review", review);
			RequestDispatcher reviewView = request.getRequestDispatcher("/review/reviewDetail.jsp");
			reviewView.forward(request, response);
		} else {
			response.sendRedirect("/review/reviewDetail.jsp");
			
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
