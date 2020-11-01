package qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.model.service.QnaService;
import qna.model.vo.QnaNotice;

/**
 * Servlet implementation class QnaDetailServlet
 */
@WebServlet("/qna/detail")
public class QnaDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int qnaNo = Integer.parseInt(request.getParameter("qnaNoticeNo"));
		QnaNotice qna = new QnaService().selectQna(qnaNo);
		if (qna != null) {
			request.setAttribute("content", qna);
			RequestDispatcher view = request.getRequestDispatcher("/qna/replylist");
			view.forward(request, response);
		}if(qna==null) {
			RequestDispatcher view = request.getRequestDispatcher("/qna/list");
			view.forward(request, response);
		}
		else{
			
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
