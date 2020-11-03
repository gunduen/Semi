<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin - 기사 관리</title>
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
		
		<th>아이디</th>
		<th>이름</th>
		<th>이메일</th>
		<th>주소</th>
		<th>주민등록번호</th>
		<th>승인여부</th>
		<th>자기소개</th>
		<th>담당지역</th>
		<th>택시운전 자격증</th>
		<th>사업자 등록증</th>
		<th>소개용 사진</th>
		<th>Button</th>
	</tr>
	<c:forEach items="${ DList }" var="driverList">
	 <tr>
	 	<td>${ driverList.driverId }</td>
	 	<td>${ driverList.driverName }</td>
	 	<td>${ driverList.driverEmail }</td>
	 	<td>${ driverList.driverHome }</td>
	 	<td>${ driverList.driverRrn }</td>
	 	<c:if test="${ driverList.driverCheck eq 1}">
	 	<td>승인완료</td>
	 	</c:if>
	 	<c:if test="${ driverList.driverCheck eq 0}">
	 	<td>승인미완료</td>
	 	</c:if>
	 	<td>${ driverList.driverSelfInfo }</td>
	 	<td>${ driverList.driverArea }</td>
	 	<td onClick="location.href='#'" class="image">${ driverList.driverLicense }</td>
	 	<td onClick="location.href='#'" class="image">${ driverList.driverBLicense }</td>
	 	<td onClick="location.href='#'" class="image">${ driverList.driverInfoImage }</td>
		<c:if test="${driverList.driverCheck eq 1 }">
	 	<td><button type="button">승인 완료됨</button></td>
	 	</c:if>	 
	 	<c:if test="${driverList.driverCheck eq 0 }">
	 	<td><button type="button">승인 하기</button></td>
	 	</c:if>
	 </tr>
	<!--  <script>
	 	${".image"}.mouseover(function() {
	 		$(this).css("color","lightgray");
	 	});
	 </script> -->
	 </c:forEach>
</table>
</body>
</html>