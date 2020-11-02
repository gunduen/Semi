package driver.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import driver.model.service.DriverService;
import driver.model.vo.Driver;

/**
 * Servlet implementation class DriverEnrollServlet
 */
@WebServlet("/driver/enroll")
public class DriverEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DriverEnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		
		int uploadFileSizeLimit = 3*1024*1024; //5mb 제한
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		String encType="UTF-8";
		File newFile = new File("D:\\gunduen\\");
		newFile.mkdirs();
		String uploadFilePath = "D:\\gunduen\\";
		MultipartRequest multi = new MultipartRequest(
	            request,
	            uploadFilePath,
	            uploadFileSizeLimit,
	            encType,
	            new DefaultFileRenamePolicy());
	      //첫번째 파일
	     File LicenseFile = multi.getFile("LicenseFile");
	     String LicenseFileName = multi.getFilesystemName("LicenseFile");
	     String LicenseFilePath = LicenseFile.getPath();
	      //두번째 파일
	      
	      File BLicenseFile = multi.getFile("BLicenseFile");
	      String BLicenseFileName = multi.getFilesystemName("BLicenseFile");
	      String BLicenseFilePath = BLicenseFile.getPath();
	      //세번째 파일
	      
	      File DriverInfoFile = multi.getFile("DriverInfoFile");
	      String DriverInfoFileName = multi.getFilesystemName("DriverInfoFile");
	      String DriverInfoFilePath = DriverInfoFile.getPath();
	      
	      //
	      //
	      String Driver_Name = multi.getParameter("userName");
	      String Driver_Id = multi.getParameter("userId");
	      System.out.println(Driver_Name);
	      System.out.println(Driver_Id);
	      Driver driver = new Driver();
	      driver.setDriverId(multi.getParameter("userId"));
	      driver.setDriverPwd(multi.getParameter("userPwd"));
	      driver.setDriverName(multi.getParameter("userName"));
	      driver.setDriverPhone(multi.getParameter("firstPhone")+"-"+multi.getParameter("secondPhone")+"-"+multi.getParameter("thirdPhone"));
	      driver.setDriverEmail(multi.getParameter("emailId")+multi.getParameter("emailTag"));
	      driver.setDriverHome(multi.getParameter("roadAddress")+" "+multi.getParameter("dAddress"));
	      driver.setDriverArea(multi.getParameter("area"));
	      driver.setDriverRrn(multi.getParameter("userRrnFirst")+"-"+multi.getParameter("userRrnSecond"));
	      driver.setDriverSelfInfo(multi.getParameter("driverInfo"));
	      driver.setDriverLicense(LicenseFileName);
	      driver.setDriverLicense_path(LicenseFilePath);
	      driver.setDriverBLicense(BLicenseFileName);
	      driver.setDriverBLicense_path(BLicenseFilePath);
	      driver.setDriverInfoImage(DriverInfoFileName);
	      driver.setDriverInfoImage_path(DriverInfoFilePath);
	      System.out.println(driver.toString());
	      result = new DriverService().insertDriver(driver);
		
	      if(result>0) {
//	    	  out.println("<script>alert('회원가입에 성공하였습니다.'); document.location.href='/index.jsp';</script>");
	    	  System.out.println(Driver_Name);
		      System.out.println(Driver_Id);
		      request.setAttribute("Driver_Name", Driver_Name);
		      request.setAttribute("Driver_Id", Driver_Id);
		      RequestDispatcher view = request.getRequestDispatcher("/travel/base");
		      view.forward(request, response);
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
