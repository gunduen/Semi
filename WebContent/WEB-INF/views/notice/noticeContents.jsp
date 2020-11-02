<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>공지사항 상세보기</title>
    <style>
        body {
            width: 100%;
            border: 1px solid black;
        }
        header {
            height: 150px;
        }
        section {
            height: 500px;
        }
        #notice {
            font-size: 30px;
            margin: 0 auto;
            text-align: center;
            margin-bottom: 15px;
        }
        #top {
            height: 30px;
            width: 900px;
            text-align: center;
            margin: 0 auto;
        }
        .topside {
            text-align: center;
            
            float: left;
        }
        textarea {
            border: 1px solid black;
        }
        .btn {
            font-size: 20px;
            margin-top: 30px;
        }
        #bottom {
            text-align: center;
        }
    </style>
</head>
    <body>
        <header><h1>header</h1></header>
        <section>
            <article><h1 id="notice" onClick="location.href='/notice/list'" style="font-size: 30px;">공지사항</h1></article>
                <div id="top">
                    <div class="topside" style="width:60%;font-size: 1.2em;background-color: lightgray">제목 : ${ content.notice_Subject }</div>
                    <div class="topside" style="width:20%;font-size: 0.8em;">작성자 : ${ content.customer_Id }</div>
                    <div class="topside" style="width:20%;font-size: 0.8em;">작성일 : ${ content.notice_Date }</div>
                </div>
            <article>
                <div style="width:900px;height:300px;font-size: 1.2em;margin:auto;">${ content.notice_Contents }</div>
            </article>
            <article id="bottom">
            	<c:if test="${ sessionScope.customer.adminCheck == 1 }">
                	<a href="/notice/updateform?notice_No=${ content.notice_No }"><input class="btn" type="button" value="수정"></a>
                	<a href="/notice/delete?notice_No=${ content.notice_No }" onclick="return question();"><input class="btn" type="button" value="삭제"></a>
                </c:if>
                <input class="btn" type="button" value="목록으로" onClick="location.href='/notice/list'">  <!-- onclick="location.href='/notice/select'" -->
            </article>
        </section>
        <footer>
        	<h1>footer</h1>
        </footer>
        <script>
        	function question()
        	{
        		return confirm("정말로 삭제하시겠습니까?");
        	}
        </script>
    </body>
</html>