<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>근두운 QnA - 질문수정</title>
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
            <h2>질문수정</h2>
            <form action="/qna/update" method="get">
                <textarea name="subject" style="resize:none" cols="130" rows="1" 
                placeholder="제목을 입력하세요">${qna.qnaNoticeSubject }</textarea>
                <br>
                <textarea name="content" style="resize:none" cols="130" rows="15" 
                placeholder="질문내용을 입력하세요">${qna.qnaNoticeContent }</textarea>
                <input type="hidden" name="qnaNo" value="${qna.qnaNoticeNo }">
                <br><br>
            	<input type="button" onclick="javascript:history.back();" value="취소">
       		    <input type="submit" id="upload" class="left align" value="수정하기" name="수정하기;">
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