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
				<td><button id="btn1" onclick="click1(${Smessage.message_No})">삭제</button></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5" align="center">${ pageNavi }
		</tr>
	</table>

</body>
</html>