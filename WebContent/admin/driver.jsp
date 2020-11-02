<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin - 기사 관리</title>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
	function approveChk(){
		var question = confirm('정말 승인 하시겠습니까?');
		if(question){
			return true;
		}else{
			return false;
		}
		
	}
	function kickOutChk(){
		var question = confirm('정말 탈퇴 시겠습니까?');
		if(question){
			return true;
		}else{
			return false;
		}
		
	}
	
</script>
</head>
<body>
<ul style="border:1px solid black;width:100px;list-style:none;padding:0;margin:0;">
	<li onClick="location.href='/admin/driverList'">기사 회원 관리</li>
	<li onClick="location.href='/admin/customerList'">고객 회원 관리</li>
	<li onClick="location.href='/admin/travelList'">여행 예약 관리</li>
	<li onClick="location.href='/message/list'">쪽지 관리</li>
</ul>
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
		<th>강제 탈퇴</th>
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
	 	<td><form action="/driver/approve?driverId=${driverList.driverId}" method="post" onsubmit="return approveChk();"><input type="submit" value="승인 하기"></form></td>
	 	</c:if>
	 	<td><form action="/driver/kickout?driverId=${driverList.driverId}" method="post" onsubmit="return kickOutChk();"><input type="submit" value="회원 탈퇴"></form></td>
	 </tr>
	 <script>
	 	$(".image").mouseover(function() {
	 		$(this).css("color","lightgray");
	 	});
	 </script>
	 </c:forEach>
</table>
</body>
</html>