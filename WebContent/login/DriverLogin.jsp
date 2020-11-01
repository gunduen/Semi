<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <title>기사 로그인</title>
    <style>
        <!--keep -->
        .member {
       	   	 font-size: 30px;
          	 color: black;
         	  margin: 0 auto;
         	  text-align:left;
         	  display: inline-block;
         	  font-family: "맑은 고딕";
           }
        
     	   article {
     	   text-align: center;
     	   }
        
        body {
            border: 1px solid black;
            width: 100%;
        }
        header {
            height: 150px;
        }
        section {
            height: 350px;
        }
        article h1, h4 {
            text-align: center;
            margin: 10px;
        }
        article form {
            text-align: center;
        }
        tr {
            height: 50px;
        }
        input[type="submit"] {
 
		 /*  display:block; */
		 height: 30px;
		 background:lightblue;
		 border-radius: 5px;
		 border: none;
		 font-family: "맑은 고딕";
		 }
		input[type="button"] {
		 height: 30px;
		 background: lightblue;
		 border-radius: 5px;
		/*  width: 140px; */
		 font-family:"맑은 고딕";
		 margin-top:20px;
		 margin-right:10px;
		}
        
    </style>
	</head>
    <body>

        <section>
            <article>
                <div id="wrap">
                    <h4 class="member">근 두 운</h4>
                    <h4>기사 로그인 페이지</h4>
                    <a href="" target="_blank"><img src="/img/MainPageImg/cloud.PNG" id="logo"></a>
                </div>
            </article>
            <article>
                <form action="/driver/login" method="post">
                    <label>아이디 : <input type="text" name="userId" id="userId" placeholder="아이디를 입력하세요."></label><br>
                   	<label>비밀번호 : <input type="password" name="userPwd" id="userPwd" placeholder="비밀번호를 입력하세요."></label><br>
                    <input type="submit" value="로그인">
                    <a href="/login/DrvierEnroll.html"><input type="button" value="회원가입"></a><br>
                    <a href="DriverFindId.jsp">아이디 찾기</a>
                    <a href="DriverFindPw.jsp">비밀번호 찾기</a>
                </form>
            </article>

        </section>

    </body>
</html>