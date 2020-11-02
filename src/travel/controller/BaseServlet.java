package travel.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import travel.model.service.TravelService;
import travel.model.vo.Travel;

/**
 * Servlet implementation class BaseServlet
 */
@WebServlet("/travel/base")

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		System.out.println(request.getParameter("Driver_Id"));
		System.out.println(request.getParameter("Driver_Name"));
		
		String Driver_Id = (String)request.getAttribute("Driver_Id");
		String Driver_Name = (String)request.getAttribute("Driver_Name");
		
	
		System.out.println(Driver_Id);
		System.out.println(Driver_Name);
		
		int result = new TravelService().insertBaseTravel(Driver_Id,Driver_Name);
		
		if(result>0) {
			out.println("<script>alert('회원가입에 성공하였습니다.'); document.location.href='/index.jsp';</script>");
		}else {
			out.println("<script>alert('회원가입에 실패하셨어요');");
	    	out.println("history.back();</script>");
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
