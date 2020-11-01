package travel.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import travel.model.service.TravelService;
import travel.model.vo.Travel;

/**
 * Servlet implementation class MyTravelListServlet
 */
@WebServlet("/mypage/travel")
public class MyTravelListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyTravelListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String customerId = request.getParameter("customerId");
		
		ArrayList<Travel> rList = new TravelService().selectTravelList(customerId);
		if(!rList.isEmpty()) {
			request.setAttribute("rList", rList);
			request.setAttribute("customerId", customerId);
			RequestDispatcher travel = request.getRequestDispatcher("/myPage/CustomerMyPage.jsp");
			travel.forward(request, response);
		}if(rList.isEmpty()){
			RequestDispatcher travel =request.getRequestDispatcher("/myPage/CustomerMyPage.jsp");
			travel.forward(request, response);
		}
		
		else {
			request.getRequestDispatcher("error");
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
