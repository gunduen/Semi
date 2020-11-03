<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="review.model.vo.Review"%>
<%@page import="travel.model.vo.Travel"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>

<head>
    <title>후기 등록</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        div {
            border: 1px solid none;
            box-sizing: border-box;
        }

        body {
            width: 1800px;
            height: 1080px;
        }

        header {
            width: 100%;
            height: 10%;
            border: 1px solid black;
            text-align: center;
            box-sizing: border-box;
        }
        article {
            text-align: center;
        }
        nav {
            border: 1px solid black;
            height: 50px;
            background-color: skyblue;
        }
        nav a {
            color : white;
            font-size : 1.2em;
            text-decoration: none;
        }
        #menu {
            margin: 0;
            padding: 0;
            height: 100%;
            overflow: hidden;
            border : 1px solid black;
            width: 1600px;
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
            color : white;
        }
        .side ul li>a:hover {
            color: blue;
            text-decoration: none;
        }
        
        #title {
            font-size: 2em;
            text-align: center;
        }
        .send {
            width: 1030px;
            text-align: center;
        }
        #btn2 {
            background-color: cornflowerblue;    
            border: none;
            color: white;
        }
        
        footer {
            width: 100%;
            height: 10%;
            border: 1px solid black;
            text-align: center;
            line-height: 100px;
        }
    </style>
</head>

<body>
    <header>
        <div id="title">
            header
        </div>
    </header>
	<section>
		<nav>
			<ul id="menu">
				<li><a href="/noticeList.html">공지사항</a></li>
				<li><a href="/driverList.html">기사소개</a></li>
				<li><a href="/reviewList.html">사용자 후기</a></li>
				<li><a href="/qnaList.html">Q&A</a></li>
			</ul>
		</nav>

		<article class="article">

			<div id="title">이용후기</div>
			<hr>
			
				<form action="/review/insert" method="post">
					<div class="message title">
						제목 ${review.reviewNo } <input type="text" style="width: 1000px;" name="subject" placeholder="제목을 입력해주세요">
					</div>
					지역과 여행예약번호를 선택해주세요 : 
					<select name="area">
						<c:forEach items="${ travelList }" var="travel">
							<option value="${travel.package_Area }" selected>${travel.package_Area }
								- '날짜 : ${ travel.package_TravelDate }'/ ${travel.package_Code }</option>

						</c:forEach>
					</select>
					<select name="packageCode">
						<c:forEach items="${ travelList }" var="travel">
							<option value="${travel.package_Code }" selected> 예약번호 : ${travel.package_Code }</option>

						</c:forEach>
					</select>

					
					<div class="message contents">
						<textarea style="width: 1030px; height: 200px" name="contents" placeholder="내용을 입력해주세요"></textarea>
					</div>
					<br>
					<div class="message update">
						<input type="submit" value="등록" id="btn2">
					</div>
				</form>
			

		</article>
	</section>

	<footer>
        <span id="copyright">Copyright 2020 CLOUD All rights reserved</span>
    </footer>
</body>

</html>