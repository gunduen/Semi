<%@page import="review.model.vo.Review"%>
<%@page import="travel.model.vo.Travel"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
    <title>사용자 후기 게시판</title>
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

        section {
            width: 100%;
            height: 50%;
            border: 1px solid black;
            box-sizing: border-box;
            text-align: center;
        }

        footer {
            width: 100%;
            height: 10%;
            border: 1px solid black;
            text-align: center;
            line-height: 100px;
        }

        button {
            float: left;
        }

        #sectionbar {
        }
        .menubar {
            width: 150px;
            height : 340px;
            display : inline-block;
            float: left;
            border: 1px solid black;
            margin : 0 20px 0 0;
            
        }

        .menubar li {
            width: 100%;
            
        }
        ul {
            padding : 0;
        }
        ul li {
            height : 50px;
            border : 1px solid black;
            list-style: none;
        }
        .menubar ul li:first-child{
            background-color : blue;
            color : white;
        }

        .table {
            width : 80%;
            border: 1px solid black;
        }
        #menu {
            margin: 0;
            padding: 0;
            height: 100%;
            overflow: hidden;
        }
        #menu li {
            width: 25%;
            height: 100%;
            float: left;
            text-align: center;
            list-style: none;
        }
    </style>
    <script>
    	window.onload = function() {
    		var flag = '${checkReview }';
    		if(flag != '' && flag == 'false') {
    			alert("Review End!!");
    		}
    	};	
    </script>
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
            <%-- <c:if  test="${ sessionScope.review ne null}"> --%>
		<div id="sectionbar">
			<div class="menubar">
				<ul>
					<li>지역</li>
					<li><a href="/review/list?reviewArea=서울">서울</a></li>
					<li><a href="/review/list?reviewArea=부산">부산</a></li>
					<li><a href="/review/list?reviewArea=대구">대구</a></li>
					<li><a href="/review/list?reviewArea=인천">인천</a></li>
					<li><a href="/review/list?reviewArea=광주">광주</a></li>
					<li><a href="/review/list?reviewArea=대전">대전</a></li>
					<li><a href="/review/list?reviewArea=울산">울산</a></li>
					<li><a href="/review/list?reviewArea=세종">세종</a></li>
					<li><a href="/review/list?reviewArea=경기">경기</a></li>
					<li><a href="/review/list?reviewArea=강원">강원</a></li>
					<li><a href="/review/list?reviewArea=충남">충남</a></li>
					<li><a href="/review/list?reviewArea=충북">충북</a></li>
					<li><a href="/review/list?reviewArea=전남">전남</a></li>
					<li><a href="/review/list?reviewArea=전북">전북</a></li>
					<li><a href="/review/list?reviewArea=경남">경남</a></li>
					<li><a href="/review/list?reviewArea=경북">경북</a></li>
					<li><a href="/review/list?reviewArea=제주">제주</a></li>
				</ul>
			</div>
			<div>후기 게시판</div>
			<table border="1">
				<tr>
					<th>작성자</th>
					<th>게시글</th>
					<th>지역</th>
					<th>날짜</th>
				</tr>
				<c:forEach items="${ RList }" var="review">
				
					<%-- <tr onClick="location.href='/review/select?reviewNo=${ review.reviewNo }'"> --%>
					<tr onclick="selnumFunc(${review.reviewNo});">
						<td>${review.customerId }</td>
						<td>${review.reviewSubject }</td>
						<td>${review.reviewArea }</td>
						<td>${review.reviewDate }</td>
					</tr>
					<script>
					 function selnumFunc(num) {
						 location.href="/review/select?reviewNo="+ num;
					 }
					</script>
				</c:forEach>
				<tr>
				<td colspan="4" align="center">${ pageNavi }
			</tr>
		</table>
		<!-- 글쓰기 버튼  -->
		${fn:length(rTravel) }
			<c:if test="${fn:length(rTravel) == 0 }">
			<div></div>
			</c:if>
			<c:if test="${fn:length(rTravel) > 0}">
				<!-- https://okky.kr/article/187911 -->
				<c:if
					test="${ sessionScope.customer ne null || ( sessionScope.driver ne null)}">
					<button type="button" id="btn1"
						onclick="location.href='/review/form'">글쓰기</button>
				</c:if>
			</c:if>
		</section>
    <footer>
        <span id="copyright">Copyright 2020 CLOUD All rights reserved</span>
    </footer>
</body></html>
