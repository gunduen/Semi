<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메세지 디테일</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
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
	
	nav {
		border: 1px solid black;
		height: 50px;
		background-color: skyblue;
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
		border: 1px solid black;
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
	
	.article:first-of-type {
		float: left;
		background-color: lightblue;
		height: 90%;
	}
	
	.article:last-of-type {
		width: 80%;
		float: left;
		margin: 20px 50px;
		text-align: left;
	}
	
	.side ul {
		padding: 0 30px;
	}
	
	.side ul li {
		list-style: none;
		height: 40px;
		font-size: 1.2em;
		margin: 15% 0;
	}
	
	.side ul li>a {
		color: white;
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
	
	#title {
		font-size: 2em;
		text-align: left;
	}
	
	.send {
		width: 1030px;
		text-align: right;
	}
	
	.contents {
		height: 230px;
		font-size: 1.2em;
	}
	
	#btn2, #btn3 {
		background-color: cornflowerblue;
		border: none;
		color: white;
		padding: 0 20px;
		text-align: center;
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
		<div id="title">header</div>
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
		<article class="article ">
			<div class="side">
				<ul>
					<li><a href="messageForm.html"><input type="submit"
							value="쪽지 쓰기" class="btn1"></a></li>
					<li><a href="#">받은 쪽지함</a></li>
					<li><a href="#">보낸 쪽지함</a></li>
				</ul>
			</div>
		</article>
		<article class="article">
			<h2>제목 : ${ detail.message_Subject }</h2>
			<h6>글번호 : ${ detail.message_No } / 보낸 이 : ${ detail.sender } / 받는 이 : ${detail.receiver}
				작성일 : ${ detail.message_Date }</h6>
			<h3>${ detail.message_Contents }</h3>
		</article>
	</section>

	<footer>
		<span id="copyright">Copyright 2020 CLOUD All rights reserved</span>
	</footer>
</body>
</html>