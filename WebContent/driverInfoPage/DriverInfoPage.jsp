<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<meta charset="UTF-8">
<title>기사 자기소개 게시판</title>
    <style>
        header{
            height: 150px;
        }
        section{
            height: 700px;
        }
        footer{
            height: 150px;
        }
        .tablinks {
            width: 100px;
            overflow: hidden;
            border: 1px solid #ccc;
            background-color: #f1f1f1;
            border: none;
            outline: none;
            cursor: pointer;
            padding: 14px 16px;
            font-size: 17px;
        }
        ul button:hover{
            background-color: #ddd;
        }
        ul button:active{
            background-color: #ccc;
        }
        ul{
            width: 25%;
            height: 100%;
            text-align: left;
            list-style: none;
        }
        .tabcontent{
            display: none;
            padding: 6px 12px;
            border: 1px solid #ccc;
            width: 70%;
            border: none;
        }
        .top1{
            height: 20%;
        }
        .bottom1{
            height: 80%;
        }
        #img_top{
            width: 100%;
            height: 50%;
        }
        #img_bottom{
            width: 100%;
            height: 50%;
        }
        #top_left{
            width: 50%;
            height: 100%;
            float: left;
        }
        #top_right{
            width: 50%;
            height: 100%;
            float: left;
        }
        #bottom_left{
            width: 50%;
            height: 100%;
            float: left;
            
        }
        #bottom_right{
            width: 50%;
            height: 100%;
            float: left;
            
        }
        #intro{
            text-align: center;
        }
        #left {
            width: 30%;
            float: left;
        }
        #right {
            widows: 70%;
        }
        header{
            height: 150px;
        }
        header #mypage{
            float: right;
        }
        #content_left{
            width: 30%;
            float: left;
        }
        #content_right{
            width: 70%;
            float: left;
        }
        
    </style>
    <script>
    $(document).ready(function(){
    	$(".tablinks").click(function(){
    		location.href='/driver/autoMyinfo?area='+$(this).text();
    	});
    });
    	

    	
    </script>
</head>
<body>
    <header>
        <h1>HEADER</h1>
        <input href="#" id="mypage" type="image"  src="img/%EC%BD%94%EB%82%9C%EB%B2%94%EC%9D%B8.PNG">
    </header>
    <section>
        <div id="content_left">
        <nav>
            <ul>
                <li class="tablinks">서울</li>
                <li class="tablinks">부산</li>
                <li class="tablinks">대구</li>
                <li class="tablinks">인천</li>
                <li class="tablinks">광주</li>
                <li class="tablinks">대전</li>
                <li class="tablinks">울산</li>
                <li class="tablinks">세종</li>
                <li class="tablinks">경기</li>
                <li class="tablinks">강원</li>
                <li class="tablinks">충남</li>
                <li class="tablinks">전북</li>
                <li class="tablinks">전남</li>
                <li class="tablinks">경북</li>
                <li class="tablinks">경남</li>
                <li class="tablinks">제주</li>
            </ul>
        </nav>
        </div>
        <div id="content_right">
        <article>
        	<c:choose>
        		<c:when test="${dList ne null}">
        			<c:forEach items="${dList}" var="list">
        				<div><img src="${list.driverInfoImage_path }" style="width: 100px; height: 100px;"></div>
        				&nbsp;&nbsp;<div>${list.driverName} 기사님</div>
        				<br><div>${list.driverSelfInfo}</div>
        			</c:forEach>
        		</c:when>
        		<c:otherwise>
        			<p>기사가 없어요~</p>
        		</c:otherwise>
        	</c:choose>
        </article>
        </div>
    </section>
    <footer>
        <h1>FOOTER</h1>
    </footer>
    <script>
    

</script>
    
</body>
</html>