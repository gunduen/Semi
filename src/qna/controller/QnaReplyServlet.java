package qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import qna.model.service.QnaService;
import qna.model.vo.Answer;
import qna.model.vo.QnaNotice;

/**
 * Servlet implementation class QnaReplyServlet
 */
@WebServlet("/qna/reply")
public class QnaReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자가 답변 달때 사용하는 서블릿 (INSERT)
		request.setCharacterEncoding("utf-8");
		int qnaNo = Integer.parseInt(request.getParameter("qnaNoticeNo"));
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		HttpSession session = request.getSession();
		
		if (session != null && (session.getAttribute("customer") != null)) {
			int result = new QnaService().insertReply(subject, content, qnaNo);
			if (result > 0) {
				response.sendRedirect("/qna/detail?qnaNoticeNo="+qnaNo);
			}
			else {
				// 답변 업로드 실패 시 출력하는 메시지
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
