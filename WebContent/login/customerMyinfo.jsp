<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="customer.model.vo.Customer" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/customer/update" method="post">
		<div id="title">회원정보 수정</div>
		<table border="1px">
			<tr>
				<th>ID</th>
				<td><input type="text" value="${customer.customer_Id }" name="customerId" readonly></td>
				<td></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" placeholder="내용을 입력해주세요" name="customerName" value="${customer.customer_Name }"></td>
				<td></td>
			</tr>
			<tr>
				<th>Password</th>
				<td><input type="text" placeholder="내용을 입력해주세요" name="customerPwd" value="${customer.customer_Pwd }"></td>
				<td></td>
			</tr>
			<tr>
				<th>Password Check</th>
				<td><input type="text" placeholder="내용을 입력해주세요" ></td>
				<td></td>
			</tr>
			<tr>
				<th>Phone</th>
				<td><input type="text" placeholder="내용을 입력해주세요" name="customerPhone" value="${customer.customer_Phone }"></td>
				<td><input type="button" value="인증" class="btn"></td>
			</tr>
			<tr>
				<th>E-mail</th>
				<td><input type="text" placeholder="내용을 입력해주세요" name="customerEmail" value="${customer.customer_Email }"></td>
				<td><input type="button" value="인증" class="btn"></td>
			</tr>
			
		</table>
		<div id="btn">
			<input type="submit" value="수정완료" class="btn"> <input
				type="reset" value="수정취소" class="btn">
		</div>
	</form>
</body>
</html>