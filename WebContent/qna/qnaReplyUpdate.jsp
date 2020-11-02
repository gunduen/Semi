<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>근두운 QnA - 답변수정 </title>
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
            <h2>관리자 답변 수정</h2>
            <form action="/qna/replyupdate" method="post">
            <% request.setCharacterEncoding("utf-8"); %>
            	<input name = qnaNoticeNo type="hidden" value="${answer.qnanoticeNo }">
                <textarea name="subject" style="resize:none" cols="130" rows="1" placeholder="답변제목을 입력하세요">${answer.answerTitle }</textarea>
                <br>
                <textarea name="content" style="resize:none" cols="130" rows="15" placeholder="답변내용을 입력하세요">${answer.answerContents }</textarea>
                <br><br>
            	<a href="/qna/list"><input type="button" id="list" class="left align" value="목록" name="목록"></a>
       		    <input type="submit" id="upload" class="left align" value="답변수정" name="답변수정">
            </form>
        </article>
        <br>
            
    </section>
    <aside>
    
    </aside>
    <footer>
    
    </footer>
</body>
</html>