<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<title>마이페이지</title>
<meta charset="utf-8">
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=0g63w7xmeu&submodules=geocoder"></script>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a8e5007416460a5bee56aaba2bb1ea6d&libraries=services"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<style>
body {
	margin: 0 auto;
}

/* div {
	border: 1px solid white;
	box-sizing: border-box;
} */


body {
	width: 1800px;
	height: 1080px;
}

header {
	width: 90%;
	height: 15%;
	border: 1px solid black;
	text-align: center;
	box-sizing: border-box;
}

#rightBanner {
	width: 25%;
	height: 100%;
	float: left;
	z-index: 100;
}

#mypage {
	width: 35%;
	height: 100%;
	float: left;
	line-height: 250px;
}

#logout {
	width: 35%;
	height: 100%;
	float: left;
	line-height: 150px;
}

#welcome {
	width: 30%;
	height: 100%;
	float: left;
	line-height: 130px;
}

#title {
	width: 75%;
	height: 100%;
	float: left;
}

section {
	width: 90%;
	height: 75%;
	border: 1px solid black;
	box-sizing: border-box;
}

footer {
	width: 100%;
	height: 15%;
	border: 1px solid black;
}

#navi {
	width: 15%;
	height: 40%;
	border: 1px solid black;
	box-sizing: border-box;
	float: left;
}

#contents {
	width: 85%;
	height: 100%;
	border: 1px solid black;
	box-sizing: border-box;
	float: left;
}

footer {
	width: 100%;
	height: 10%;
	border: 1px solid black;
	text-align: center;
	line-height: 100px;
}

#mypageList {
	width: 100%;
	height: 100%;
}

#navi #navigator a {
	text-decoration: none;
	display: block;
}

#navigator {
	width: 100%;
	height: 100%;
}

#navigator li {
	text-align: center;
	line-height: 70px;
	list-style: none;
	border: 1px solid black;
	height: 20%;
	box-sizing: border-box;
}

#mybooking {
	border: 1px solid black;
	width: 100%;
	height: 10%;
}

#mybookinglist {
	border: 1px solid black;
	width: 100%;
	height: 90%;
}

#mybooking p {
	font-size: 1.6em;
	font-weight: bold;
}

#mybookinglist table {
	margin: 0 auto;
}

tr th {
	text-align: center;
}
tr td {
	text-align: center;
}
#travel:hover {
	background : yellow;
}

#main {
	width: 100%;
	height: 80%;
	border: 1px solid black;
}

#mainNav {
	width: 100%;
	height: 20%;
	border: 1px solid black;
}

#mainNav li {
	border: 1px solid black;
	float: left;
	list-style: none;
	width: 20%;
	height: 100%;
	line-height: 30px;
}

#mainNav li a {
	display: block;
	text-decoration: none;
}

li:hover {
	background-color: cornsilk;
}
</style>
</head>
<body>
	<header>
		<div id="main">
			<div id="title"></div>

			<div id="rightBanner">

				<div id="welcome">
					<p style="line-height: 130px; text-align: right;">${sessionScope.customer.customer_Id}님</p>
				</div>
				<div id="logout">
					<form action="/mypage/logout">
						<button type="button" class="btn btn-default"
							style="margin-top: -20px;">로그아웃</button>
					</form>
				</div>
				<div id="mypage">
					<a href="mypage.html" id="MyPageImg"><img
						src="../img/MyPageImg/MyPageImg.png"
						style="width: 100px; height: 100px; margin-top: -125px;"
						class="img-circle"></a>
				</div>

			</div>
		</div>
		<div id="mainNav">
			<li></li>
			<li><a href="noticelist.html">공지사항</a></li>
			<li><a href="driverList.html">기사 소개</a></li>
			<li><a href="reviewlist.html">사용자 후기</a></li>
			<li><a href="qnalist.html">Q&A게시판</a></li>
		</div>

	</header>

	<section>
		<section id="navi">
			<nav id="navigator">
				<!-- if문으로 li 개수 조절 -->
				<li><a href="/mypage/travel">예약확인/ 예약취소</a></li>
				<li><a href="messageList.html">쪽지함</a></li>
				<li><a
					href="/customer/myInfo?customerId=${sessionScope.customer.customer_Id }">회원정보
						수정</a></li>
				<li><a
					href="/customer/delete?customerId=${sessionScope.customer.customer_Id }">회원
						탈퇴</a></li>
				<li><a href="selectHoliday.html">My 일정관리</a></li>
			</nav>

		</section>
		<section id="contents">
			<h1>예약확인</h1>
                <table class="table" style="width: 70%; height: 30px;">
                
                   <c:forEach items="${tdList }" var="travel">
                    <div id="map" style="width: 300px; height: 300px; margin-top: 10px;"></div><br>
                    <tr>
                        <th>이름</th>
                        <td>${travel.customer_Id }</td>
                    </tr>
                    <tr>
                        <th>여행장소</th>
                        <td>${travel.package_Area }</td>
                    </tr>
                    <tr>
                        <th>여행 날짜</th>
                        <td>${travel.package_TravelDate }</td>
                    </tr>
                    <tr>
                        <th>예약코드</th>
                        <td>${travel.package_Code }</td>
                    </tr>
                    <tr>
                        <th>담당 기사 이름</th>
                        <td>${travel.driver_Name }</td>
                    </tr>
                    </c:forEach>
                </table>
            <hr style="border: solid 2px gray;">
            <section id="contents">
            <h1>예약취소</h1>
            <a>예약 취소는 여행 시작 5일전 까지만 비용이 발생합니다. 그 이후에는 취소하실 수 없으며 취소시 금액<br>환불은 매우 어려운점 미리 양해 말씀 드립니다.</a>
            <table class="table" style="width: 70%; height: 30px;">
                <c:forEach items="${tdList }" var="travel">
                <tr>
                    <th>이름</th>
                    <th>여행장소</th>
                    <th>결제 날짜</th>
                    <th>예약 코드</th>
                </tr>
                <tr>
                    <td>${travel.customer_Id }</td>
                    <td>${travel.package_Area }</td>
                    <td>${travel.package_Date.getYear()+1900 }년${travel.package_Date.getMonth() }월 ${travel.package_Date.getDay()}일</td>
                    <td>${travel.package_Code }</td>
                </tr>
               
                <input type="button" value="예약취소" name="cancle" onClick="location.href='/travel/delete?package_Code=${travel.package_Code}'">
                </c:forEach>
            </table>
             
			</section>
		</section>
	</section>
	
	<footer>
		<span id="copyright">Copyright 2020 CLOUD All rights reserved</span>
	</footer>
	<c:forEach items="${tdList }" var="travel">
		<script>
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div
			mapOption = {
				center : new daum.maps.LatLng('${travel.coordx}','${travel.coordy}'), // 지도의 중심좌표
				level : 5
			// 지도의 확대 레벨
			};
			//지도를 미리 생성
			var map = new daum.maps.Map(mapContainer, mapOption);
			//주소-좌표 변환 객체를 생성
			var geocoder = new daum.maps.services.Geocoder();
			//마커를 미리 생성
			var marker = new daum.maps.Marker({
				position : new daum.maps.LatLng('${travel.coordx}','${travel.coordy}'),
				map : map
			});
			var infowindow = new kakao.maps.InfoWindow({
	            content: '<div style="width:150px;text-align:center;padding:6px 0;">픽업위치 : ${travel.package_Pickup}</div>'
	        });
	        infowindow.open(map, marker);
			
			
		</script>
	</c:forEach>
</body>

</html>