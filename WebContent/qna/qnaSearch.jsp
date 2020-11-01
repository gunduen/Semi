<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--  디렉티브(태그라이브러리) 작성 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>근두운 QnA - 목록</title>
	<style>
	    section
	    {
	        text-align: center;
	    }
	    article>table
	    {
            border: 1px solid black;
            width: 60%;
            margin: auto;
            text-align: center;
            margin-bottom: 20px;
        }
	    a
	    {
	        text-decoration-line: none;
	    }
	    #write
	    {
	        float: right;
	    }
	    th
	    {
	        background-color: lightgray;
	        height: 40px;
	    }
	    #dfdf{
	    text-align : center;
	    }
	    #qna:hover{
	    background : yellow;
	    }
	    tr {
	    height : 50px;
	    }
	</style>
</head>
	<body>
	<header>
        <nav>
            
        </nav>
    </header>
    <section>
        <h1>Q&amp;A</h1>
        <h5>빠른 시일내에 답변드리도록 노력하겠습니다.</h5>
        <article>
            <table>
                <tr>
                    <th>글번호</th>
                    <th>제목</th>
                    <th>작성자(고객)</th>
                    <th>작성자(기사)</th>
                    <th>작성일</th>
                    <th>조회수</th>
                    <th>답변여부</th>
                </tr>
                <c:forEach items="${qList }" var="qna"  >
                	<tr id="qna" onClick="location.href='/qna/detail?qnaNoticeNo=${qna.qnaNoticeNo }'">
                		<td>${qna.qnaNoticeNo }</td>
                		<td>${qna.qnaNoticeSubject }</td>
                		<td>${qna.customerId }</td>
						<td>${qna.driverId }</td>
                		<td>${qna.qnaNoticeDate }</td>
                		<td>${qna.qnaNoticeHits }</td>
                		<td>${qna.qnaNoticeCheck }</td>
                	</tr>
                </c:forEach>
            </table>
        </article>
        <tr id="dfdf">
           <td colspan="4" align="center">
              ${pageNavi }
           </td>
        </tr>
        <br><br>
        <article>
            <a href="qnaWrite.html"><input type="button" id="write" value="글쓰기" name="글쓰기"></a>
        </article>
        <article>
        <form action="/qna/search" method="get">
            <select id="type" name="type">
<!--                 <option value="QNANOTICE_SUBJECTS">제목</option>
                <option value="QNANOTICE_CONTENTS">내용</option>
                <option value="CUSTOMER_ID">작성자</option>
                <option value="QNANOTICE_NO">글번호</option> -->
                <option>제목</option>
                <option>내용</option>
                <option>작성자</option>
                <option>글번호</option>
             </select>
             <!--<c:forEach items="qList">
             	<select>
             		<option value="${qna}">${qna}</option>
             	</select>
              </c:forEach>-->
            <input type="text" name="search" size="50" placeholder="검색어를 입력하세요">
            <input type="submit" value="검색">
            </form>
            </article>
    </section>
    <aside>
    
    </aside>
    <footer>
    
    </footer>
    <script>
//	   $("#type").change(function(data){
//		   alert("변경");
//		   location.href="/qna/search?type"+data.type;
//	   });
    </script>
	</body>
</html>