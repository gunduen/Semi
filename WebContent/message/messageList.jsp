<%@page import="customer.model.vo.Customer"%>
<%@page import="driver.model.vo.Driver"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<title>쪽지 리스트</title>
<style>
div {
	border: 1px solid blue;
	box-sizing: border-box;
}

body {
	width: 100%;
	height: 100%;
} /* 
        header {
            width: 100%;
            height: 100px;
            border: 5px solid black;
            text-align: center;
            box-sizing: border-box;
        }
        nav {
            border: 3px solid red;
            height: 50px;
            background-color: skyblue;
        }
        section {
            width: 100%;
            height: 20%;
            border: 5px solid gold;
            box-sizing: border-box;
            text-align: center;
        }
        footer {
            width: 100%;
            height: 10%;
            border: 5px solid green;
            text-align: center;
            line-height: 100px;
        }

        nav a {
            color: white;
            font-size: 1.2em;
            text-decoration: none;
        }

        #menu {
            margin: 0;
            padding: 0;
            height: 100%;
            overflow: hidden;
            margin: 0 auto;
        }

        #menu li {
            width: 25%;
            height: 100%;
            float: left;
            text-align: center;
            list-style: none;
            line-height: 50px;
            vertical-align: middle;
        }

        #menu li:hover {
            background-color: steelblue;
        }

        .article:first-of-type {
            float: left;
            background-color: lightblue;
            height: 90%;
        }

        .article:last-of-type {
            width: 80%;
            float: left;
            margin: 20px 50px;
        }

        .side ul {
            padding: 0 30px;
        }

        .side ul li {
            list-style: none;
            height: 50px;
            font-size: 1.2em;
            margin: 15% 0;
        }

        .side ul li>a {
            color: white;
            text-decoration: none;
        }

        .side ul li>a:hover {
            color: blue;
            text-decoration: none;
        }

        .btn1 {
            background-color: cornflowerblue;
            border: none;
            color: white;
            padding: 0 20px;
            text-align: center;
        }

        .title {
            font-size: 2em;
            text-align: left;
        }

        navbar {
            text-align: center;
        } */
</style>
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
		<section>
			<%-- <c:if test="${sessionScope.customer ne null }"> --%>
			<article class="article">
				<div class="title">[${ sessionScope.customer.customer_Id }]님의
				 쪽지함입니다.</div>
				<hr>
				<c:if test="${ sessionScope.customer.customer_Id eq 'admin'}">
					<table border="1">
						<tr>
							<th>보낸 이</th>
							<th>제목</th>
							<th>보낸 날짜</th>
							<th>받는 이</th>
						</tr>
						<c:forEach items="${ mList }" var="allmessage" varStatus="index">
							<tr>
								<td>${ allmessage.sender }</td>
								<td><a
									href="/message/select?messageNo=${ allmessage.message_No }">${ allmessage.message_Subject}</a></td>
								<td>${ allmessage.message_Date }</td>
								<td>${ allmessage.receiver }</td>
								<td><button id="btn1"
										onclick="click1(${allmessage.message_No})">삭제</button></td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="5" align="center">${ pageNavi }
						</tr>
					</table>
				</c:if>
			</article>
			<%-- </c:if> --%>

		</section>
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
		<section>
			<%-- <c:if test="${sessionScope.customer ne null }"> --%>
			<article class="article">
				<div class="title">[${ sessionScope.driver.driverId }]님의 
					쪽지함입니다.</div>
				<hr>

				<table border="1">
					<tr>
						<th>보낸 이</th>
						<th>제목</th>
						<th>보낸 날짜</th>
						<th>받는 이</th>
					</tr>
					<c:forEach items="${ mList }" var="allmessage" varStatus="index">
						<tr onClick="location.href='/message/select?messageNo=${allmessage.message_No}'">
							<td>${ allmessage.sender }</td>
							<td>${ allmessage.message_Subject}</td>
							<td>${ allmessage.message_Date }</td>
							<td>${ allmessage.receiver }</td>
							<td><button id="btn1"
									onclick="click1(${allmessage.message_No})">삭제</button></td>
						</tr>
					</c:forEach>
					
					<tr>
						<td colspan="5" align="center">${ pageNavi }
					</tr>
				</table>

			</article>

		</section>

	</c:if>
	<footer>
		<span id="copyright">Copyright 2020 CLOUD All rights reserved</span>
	</footer>

	<script>
	function click1(messageNo) {
		var answer = confirm("정말로 삭제하시겠습니까?");
		if (answer) {
			location.href='/message/delete?messageNo='+messageNo;
		}
	};
	</script>
</body>
</html>