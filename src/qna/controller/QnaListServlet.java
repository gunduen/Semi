package qna.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;


import qna.model.service.QnaService;
import qna.model.vo.PageData;
import qna.model.vo.QnaNotice;

/**
 * Servlet implementation class QnaListServlet
 */
@WebServlet("/qna/list")
public class QnaListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage = 0;
		if (request.getParameter("currentPage") == null) {
			currentPage = 1;
		}else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		PageData pageData = new QnaService().selectQnaList(currentPage);
		ArrayList<QnaNotice> qList = pageData.getPageList();
		if(!qList.isEmpty()) {
			request.setAttribute("qList", qList);
			request.setAttribute("pageNavi", pageData.getPageNavi());
			RequestDispatcher qna = request.getRequestDispatcher("/qna/qnaList.jsp");
			qna.forward(request, response);
		}else {
			// 서비스 요청 실패
			request.getRequestDispatcher("/qna/qnaError.html");
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