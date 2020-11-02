<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!--  디렉티브(태그라이브러리) 작성 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>근두운 QnA - 상세보기</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<style>
    section
    {
        text-align: center;
    }
    article>table
    {
        width: 100%;
        border: 1px solid black;
        box-sizing: border-box;
    }
    a
    {
        text-decoration-line: none;
    }
    .left
    {
        float: right;
    }
/*
    h3
    {
        text-align: left;
    }
*/
    #question
    {
        border: 1px solid black;
        width: 1000px;
        height: 100%;
        margin: auto;
    }
    #answer
    {
        border: 1px solid black;
        width: 1000px;
        height: 100%;
        margin: auto;
    }
</style>
</head>
<body>
    <header>
        <nav>
            
        </nav>
    </header>
    <section>
        <h1>근두운 Q&amp;A</h1>
        <h5>빠른 시일내에 답변드리도록 노력하겠습니다.</h5>
        <article>
            <h3>Question</h3>
            <form id="question">
                <h3>Q : ${content.qnaNoticeSubject }</h3>
                <br>
                <div>
                    내용 : ${content.qnaNoticeContent }
                </div>
                <br>
            </form>
            <h3>Answer</h3>
            <form id="answer">
                <h3>A : ${answer.answerTitle }</h3>
                <br>
                <div>
                    답변 내용 : ${answer.answerContents } 
                </div>
                <br>
            </form>
        </article>
        <br>
        <article>
        
	        <!-- 로그인 아이디와 게시글 아이디 확인  -->
        	<c:if test="${ sessionScope.customer.customer_Id == content.customerId }">
        		<a href="/qna/updateform?qnaNo=${content.qnaNoticeNo }"><input type="button" value="수정"></a>
        		<a href="/qna/delete?qnaNo=${content.qnaNoticeNo }"><input type="button" value="삭제" onclick="return del();"></a>
        		
        	</c:if>
        	<!-- 관리자인지 확인  -->
        	<c:if test="${ sessionScope.customer.adminCheck == 1 }">
        	<form action="/qna/qnaReplyWrite.jsp" method="post">
        		<input name = qnaNoticeNo type="hidden" value=${content.qnaNoticeNo }>
        		<input name ="replyCheck" type="hidden" value="complete">
        		<input type="submit" value="답변등록">
        		</form>
        		
        		<form action="/qna/replyupdateform?qnaNo=${content.qnaNoticeNo }" method="post">
        		<input name = qnaNoticeNo type="hidden" value=${content.qnaNoticeNo }>
        		<input type="submit" value="답변수정">
        		</form>
        		
        		<form action="/qna/replydelete?qnaNo=${content.qnaNoticeNo }" method="post">
        		<input name = qnaNoticeNo type="hidden" value=${content.qnaNoticeNo }>
        		<input name ="replyCheck" type="hidden" value="wait">
        		<input type="submit" value="답변삭제">
        		</form>
        		
        		<a href="/qna/updateform?qnaNo=${content.qnaNoticeNo }"><input type="button" value="수정"></a>
        		<a href="/qna/delete?qnaNo=${content.qnaNoticeNo }"><input type="button" value="삭제" onclick="return del();"></a>
        	</c:if>
            <a href="/qna/list"><input type="button" value="목록으로"></a>
        </article>
    </section>
    <aside>
    
    </aside>
    <footer>
    
    </footer>
    <script>
		function del() {
			return confirm("정말로 삭제하시겠습니까?")
			}
	</script>
</body>
</html>