package qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customer.model.vo.Customer;
import driver.model.vo.Driver;
import qna.model.service.QnaService;

/**
 * Servlet implementation class QnaWriteServlet
 */
@WebServlet("/qna/write")
public class QnaWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		HttpSession session = request.getSession();
		// 고객이 QnA 등록했을때 코드
		if (session != null && (session.getAttribute("customer") != null)) {
			String userId = ((Customer)session.getAttribute("customer")).getCustomer_Id();
			int result = new QnaService().insertQna(subject, content, userId);
			if (result > 0) {
				response.sendRedirect("/qna/list");
			}
			else {
				// insert가 되지 않았을 때 출력하는 메시지
				RequestDispatcher view = request.getRequestDispatcher("/qna/qnaError.html");
				view.forward(request, response);
			}
			// 기사가 QnA 등록했을때 코드
		}else if(session != null && (session.getAttribute("driver") != null)) {
			String driverId = ((Driver)session.getAttribute("driver")).getDriverId();
			int result = new QnaService().insertQnaa(subject, content, driverId);
			if (result > 0) {
				response.sendRedirect("/qna/list");
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
