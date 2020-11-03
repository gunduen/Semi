<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin - 여행 예약 관리</title>
</head>
<body>
<ul style="border:1px solid black;width:100px;list-style:none;padding:0;margin:0;">
	<li onClick="location.href='/admin/driverList'">기사 회원 관리</li>
	<li onClick="location.href='/admin/customerList'">고객 회원 관리</li>
	<li onClick="location.href='/admin/travelList'">여행 예약 관리</li>
	<li onClick="location.href='/message/receiverList?receiver=${sessionScope.customer.customer_Id}'">쪽지 관리</li>
</ul>

<div>${ sessionScope.customer.customer_Id } 페이지입니다. </div>
<table border="1px solid black">
	<tr>
		
		<th>여행예약코드</th>
		<th>지역</th>
		<th>이용권</th>
		<th>픽업장소</th>
		<th>예약한 날짜</th>
		<th>여행 예정 날짜</th>
		<th>여행 완료 여부</th>
		<th>고객 아이디</th>
		<th>기사 이름</th>
		<th>Button</th>
	</tr>
	<c:forEach items="${ travelList}" var="travelList">
	 <tr>
	 	<td>${ travelList.package_Code }</td>
	 	<td>${ travelList.package_Area }</td>
	 	<td>${ travelList.package_Utilization }</td>
	 	<td>${ travelList.package_Pickup }</td>
	 	<td>${ travelList.package_Date }</td>
	 	<td>${ travelList.package_TravelDate }</td>
	 	<c:if  test="${ travelList.pakcage_Confirm == 0}">
	 	<td> 미완료 </td>
	 	</c:if>
	 	<c:if  test="${ travelList.pakcage_Confirm > 0}">
	 	<td> 완료 </td>
	 	</c:if>
	 	<td>${ travelList.customer_Id }</td>
	 	<td>${ travelList.driver_Name }</td>
	 	<td><button onclick="#">취소한 여행 목록에서 삭제하기</button></td>
	 </c:forEach>
</table>
</body>
</html>