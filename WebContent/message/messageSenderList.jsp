<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="customer.model.vo.Customer"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보낸 쪽지함</title>
</head>
<body>
<!-- 고객 -->
	<c:if
		test="${sessionScope.customer ne null && sessionScope.driver eq null}">
		<div class="side">
			<ul>
				<li><a href="/message/sendform"><input type="submit"
						value="쪽지 쓰기" class="btn1"></a></li>
				<li><a
					href="/message/receiverList?receiver=${ sessionScope.customer.customer_Id }">받은
						쪽지함</a></li>
				<li><a
					href="/message/senderList?sender=${ sessionScope.customer.customer_Id }">보낸
						쪽지함</a></li>
			</ul>
		</div>
		<div class="title">[${ sessionScope.customer.customer_Id }]님의
				 쪽지함입니다.</div>
				<hr>
	<table border="1">
		<tr>
			<th>보낸 이</th>
			<th>제목</th>
			<th>보낸 날짜</th>
			<th>받는 이</th>
		</tr>
		<c:forEach items="${ senderList }" var="Smessage" varStatus="index">
			<tr onClick="location.href='/message/select?messageNo=${SSmessage.message_No}'">
				<td>${ Smessage.sender }</td>
				<td>${ Smessage.message_Subject}</td>
				<td>${ Smessage.message_Date }</td>
				<td>${ Smessage.receiver }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5" align="center">${ pageNavi }
		</tr>
	</table>
</c:if>

<!-- 기사 -->
	<c:if
		test="${sessionScope.customer eq null && sessionScope.driver ne null}">
		<div class="side">
			<ul>
				<li><a href="/message/sendform"><input type="submit"
						value="쪽지 쓰기" class="btn1"></a></li>
				<li><a
					href="/message/receiverList?receiver=${ sessionScope.driver.driverId }">받은
						쪽지함</a></li>
				<li><a
					href="/message/senderList?sender=${ sessionScope.driver.driverId }">보낸
						쪽지함</a></li>
			</ul>
		</div>
			<div class="title">[${ sessionScope.customer.customer_Id }]님의
				 쪽지함입니다.</div>
				<hr>
	<table border="1">
		<tr>
			<th>보낸 이</th>
			<th>제목</th>
			<th>보낸 날짜</th>
			<th>받는 이</th>
		</tr>
		<c:forEach items="${ senderList }" var="Smessage" varStatus="index">
			<tr onClick="location.href='/message/select?messageNo=${Smessage.message_No}'">
				<td>${ Smessage.sender }</td>
				<td>${ Smessage.message_Subject}</td>
				<td>${ Smessage.message_Date }</td>
				<td>${ Smessage.receiver }</td>
				
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5" align="center">${ pageNavi }
		</tr>
	</table>
</c:if>
</body>
</html>