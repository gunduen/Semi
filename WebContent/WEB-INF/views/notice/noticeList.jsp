<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>근두운 - 공지사항 리스트</title>
    <style>
        body {
            border: 1px solid black;
            width: 100%;
        }
        header {
            height: 150px;
        }
        /* section {
            height: 350px;
        } */
        article h1, h4 {
            text-align: center;
            margin: 10px;
        }
        #title {
            background-color: lightgray;
            height: 40px;
        }
        #notice {
            border: 1px solid black;
            width: 60%;
            margin: auto;
            text-align: center;
            margin-bottom: 20px;
        }
        tr {
            height: 50px;
        }
        input[type="button"] {
            font-size: 15px;
        }
        .pagination
        {
        	display: block;
        	text-align:center;
        	text-decoration-line: none;
        }
        .button
        {
        	text-align: right;
        	margin : 0 50px 0 0;
        }
        a
        {
        	text-decoration-line: none;
        	color : black;
        }
        section
        {
        	text-align:center;
        }
    </style>
</head>
    <body>
        <header>
            <h1>header</h1>
        </header>
        <section>
            <article>
                <h1 style="font-size: 30px;">공지사항</h1>
            </article>
            <article>
                <table id="notice">
                    <tr id="title">
                        <th>글번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                        <th>조회수</th>
                    </tr>
                    <c:forEach items="${ nList }" var="notice">
	                    <tr>
	                        <td>${ notice.notice_No }</td>
	                        <td><a href="/notice/select?notice_No=${ notice.notice_No }">${ notice.notice_Subject }</a></td>
	                        <td>${ notice.customer_Id }</td>
	                        <td>${ notice.notice_Date }</td>
	                        <td>${ notice.notice_Hits }</td>
	                    </tr>
                    </c:forEach>
                </table>
            </article>
            <article>
            	<form class="pagination">
            		${ pageNavi }
            	</form>
	            	<c:if test="${ sessionScope.customer.adminCheck == 1 }">
		            	<a href="/notice/writeform"><input id="noticeWrite" type="submit" value="글쓰기" name="글쓰기"></a>
			        </c:if>
            </article>
            <br><br>
            <article>
            	<form action="/notice/search" method="post">
            		<select>
	                    <option>제목</option>
	                    <option>내용</option>
                	</select>
            		<input type="text" name="search" size="50" placeholder="검색어를 입력하세요">
            		<input type="submit" value="검색">
            	</form>
            </article>
        </section>
        <footer>
            <h1>footer</h1>
        </footer>
    </body>
</html>