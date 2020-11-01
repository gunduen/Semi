<%@page import="driver.model.vo.Driver"%>
<%@page import="customer.model.vo.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="javax.activation.*"%>
<%@ page import="javax.mail.*"%>
<%@ page import="javax.mail.internet.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Driver driver = (Driver)request.getAttribute("driver");
%>
<%!
	public class MyAuthentication extends Authenticator { //아이디 패스워드 인증받기 함수
		  PasswordAuthentication pa;
		  public MyAuthentication(){
		    pa=new PasswordAuthentication("allllliz1583@gmail.com","qnrhks88#");        
		  }
		  @Override
		  protected PasswordAuthentication getPasswordAuthentication() {
		    return pa;
		  }
	}
%>


<%
	 // SMTP 서버 주소
	 String smtpHost = "smtp.gmail.com";
	
	
	 //받는 사람의 정보
	 String toName = driver.getDriverName();
	 String toEmail = driver.getDriverEmail();
	
	
	 //보내는 사람의 정보
	 String fromName = "근두운 관리자";
	 String fromEmail = "allllliz1583@gmail.com";
	 
	 try {
		 Properties props = new Properties();
		 props.put("mail.smtp.user", fromEmail);
		 props.put("mail.smtp.host", "smtp.googlemail.com");
		 props.put("mail.smtp.port", "465");
		 props.put("mail.smtp.starttls.enable", "true");
		 props.put("mail.smtp.auth", "true");
		 props.put("mail.smtp.debug", "true");
		 props.put("mail.smtp.socketFactory.port", "465");
		 props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		 props.put("mail.smtp.socketFactory.fallback", "false");
		
		  // 메일 인증
		  Authenticator myauth=new MyAuthentication(); 
		  Session sess=Session.getInstance(props, myauth);
		
		
		  InternetAddress addr = new InternetAddress();
		  addr.setPersonal(fromName,"UTF-8");
		  addr.setAddress(fromEmail);
		
		
		  Message msg = new MimeMessage(sess);
		  msg.setFrom(addr);         
		  msg.setSubject(MimeUtility.encodeText("안녕하세요 근두운입니다.", "utf-8","B"));
		  msg.setContent("비밀번호는"+driver.getDriverPwd()+"입니다.", "text/html;charset=utf-8");
		  msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
		
		
		  Transport.send(msg);
		
		
	 } catch (Exception e) {
	  e.printStackTrace();
	  out.println("<script>alert('메일 전송에 실패했습니다.\\n다시 시도해주세요.');</script>");
	  return;
	 }

%>	 
	 <script>
	 	alert('메일을 확인해주세요.');
	 	location.href='/index.jsp';
	 </script>

