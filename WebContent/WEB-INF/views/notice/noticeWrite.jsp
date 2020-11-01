<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>공지사항 작성</title>
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
            <article id="top">공지사항 작성
	            <form action="/notice/write" method="post">
	                <fieldset>
	                    <input type="text" size="100" placeholder="제목을 작성하세요" name="notice_Subject"><br><br>
	                    <textarea rows="10" cols="100" placeholder="내용을 작성하세요" name="notice_Contents"></textarea><br>
	                    <div id="bottom">
		                    <input type="submit" value="업로드">
		                    <input type="reset" value="취소">
		                    <a href="/notice/list"><input type="button" value="목록으로"></a>
	                    </div>
	                </fieldset>
	            </form>
            </article>
        </section>
        <footer>
            <h1>footer</h1>
        </footer>
    </body>
</html>