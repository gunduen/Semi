package review.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.model.service.CommentService;
import comment.model.vo.Comment;
import comment.model.vo.PageData;
import review.model.service.ReviewService;
import review.model.vo.Review;

/**
 * Servlet implementation class ReviewDetailServlet
 */
@WebServlet("/review/select")
public class ReviewDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reviewNo = Integer.parseInt(request.getParameter("reviewNo"));
		Review review = new ReviewService().selectReview(reviewNo);
//		System.out.println(review);
		int currentPage = 0;
		
		if(request.getParameter("currnetPage") == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		PageData pageData = new CommentService().commentList(currentPage, reviewNo);
		ArrayList<Comment> CList = pageData.getPageList();
//		System.out.println(CList);

		if (review != null) {
			request.setAttribute("review", review);
			if(!CList.isEmpty()) {
				request.setAttribute("CList", CList);
				request.setAttribute("pageNavi", pageData.getPageNavi());
			}
			RequestDispatcher reviewView = request.getRequestDispatcher("/review/reviewDetail.jsp");
			reviewView.forward(request, response);
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
