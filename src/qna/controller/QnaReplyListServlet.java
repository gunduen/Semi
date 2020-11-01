package qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.model.service.QnaService;
import qna.model.vo.Answer;
import qna.model.vo.QnaNotice;

/**
 * Servlet implementation class QnaReplyListServlet
 */
@WebServlet("/qna/replylist")
public class QnaReplyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaReplyListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자 답변 불러오는 서블릿 (SELECT)
		request.setCharacterEncoding("utf-8");
		int qnaNo = Integer.parseInt(request.getParameter("qnaNoticeNo"));
		Answer answer = new QnaService().selectAnswer(qnaNo);
		if (answer != null) {
			// 밑의 두 줄 오류원인
			request.setAttribute("answer", answer);
			RequestDispatcher view = request.getRequestDispatcher("/qna/replyupdate");
			view.forward(request, response);
//			RequestDispatcher views = request.getRequestDispatcher("/qna/qnaDetail.jsp");
//			views.forward(request, response);
		}if(answer == null){
			// 관리자 답변이 없을때 실행되는 코드
			RequestDispatcher view = request.getRequestDispatcher("/qna/qnaDetail.jsp");
			view.forward(request, response);
		}
		else {
			//오류 페이지
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
