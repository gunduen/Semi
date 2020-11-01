<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>공지사항 수정</title>
    <style>
        #top {
            font-size: 30px;
            text-align: center;
        }
        form {
            text-align: center;
        }
        fieldset {
            width: 10%;
            margin: auto;
        }
        #bottom {
            text-align: right;
        }
    </style>
</head>
    <body>
        <header>
            <h1>header</h1>
        </header>
        <section>
            <article id="top"><h1>공지사항 수정</h1></article>
            <form action="/notice/update" method="post">
	            <input type="text" size="110" name="notice_Subject" value="${ notice.notice_Subject }"><br><br>
	            <textarea rows="10" cols="100" name="notice_Contents">${ notice.notice_Contents }</textarea><br><br>
	            <input type="hidden" name="notice_No"value="${ notice.notice_No }">
	            <input type="submit" value="수정" name="수정">
	            <input type="reset" value="취소" name="취소">
	            <a href="/notice/list"><input type="button" value="목록으로"></a> 
            </form>
        </section>
        <footer>
            <h1>footer</h1>
        </footer>
    </body>
</html>