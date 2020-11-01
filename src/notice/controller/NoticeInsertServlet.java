package notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.w3c.dom.css.ViewCSS;

import customer.model.vo.Customer;
import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsertServlet
 */
@WebServlet("/notice/write")
public class NoticeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String notice_Subject = request.getParameter("notice_Subject");
		String notice_Contents = request.getParameter("notice_Contents");
		HttpSession session = request.getSession();
		if ( session != null && (session.getAttribute("customer") != null ))
		{
			String customer_Id	= ((Customer)session.getAttribute("customer")).getCustomer_Id();
			int result = new NoticeService().insertNotice(notice_Subject, notice_Contents, customer_Id);
			if ( result > 0 )
			{
				response.sendRedirect("/notice/list");
			}
			else
			{
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/notice/noticeError.html");
				view.forward(request, response);
			}
		}
		else
		{
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/notice/serviceFailed.html");
			view.forward(request, response);
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
