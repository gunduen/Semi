<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
    <head>
        <title>마이페이지</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
         <style>
            body{
                margin: 0 auto;
            }
            div{
                border: 1px solid black;
                box-sizing: border-box;
            }
            body{
               width: 1800px;
                height: 1080px;
            }
            header{
                width: 90%;
                height: 15%;
                border: 1px solid black;
                text-align : center;
                box-sizing: border-box;        
            }
            #rightBanner{
                width: 25%;
                height: 100%;
                float: left;
                z-index: 100;
            }
            #mypage{
                width: 35%;
                height: 100%;
                float: left;
                line-height: 250px;
            }
            #logout{
                width: 35%;
                height: 100%;
                float: left;
                line-height: 150px;
            }
            #welcome{
                width: 30%;
                height: 100%;
                float: left;
                line-height: 130px;
            }
            #title{
                width: 75%;
                height: 100%;
                float: left;
            }            
            section{
                width: 90%;
                height: 75%;
                border: 1px solid black;
                box-sizing: border-box;              
            }
            #footer{
                width: 100%;
                height: 15%;
                border: 1px solid black;
            }
            #navi{
                width: 15%;
                height: 40%;
                border: 1px solid black;
                box-sizing: border-box;
                float: left;      
            }
            #contents{
                width: 85%;
                height: 100%;
                border: 1px solid black;
                box-sizing: border-box;
                float: left;
            }
            
            footer{
                width: 100%;
                height: 10%;
                border: 1px solid black;
                text-align: center;
                line-height: 100px;
            }
            #mypageList{
                width: 100%;
                height: 100%;
                
            }
            #navi #navigator a{
                text-decoration: none;
                display: block;
            }
            #navigator{
                width: 100%;
                height: 100%;
            }
            #navigator li{
                text-align: center;
                line-height: 70px;
                list-style: none;
                border: 1px solid black;
                height: 20%;
                box-sizing: border-box;
            }    
            #mybooking{
                border: 1px solid black;
                width: 100%;
                height: 10%;
            }
            #mybookinglist{
                border: 1px solid black;
                width: 100%;
                height: 90%;
                
            }
            #mybooking p{
                font-size: 1.6em;
                font-weight: bold;
            }
            #mybookinglist table{
                margin: 0 auto;
            }
            #mybookinglist table thead tr th{
                text-align: center;
            }
             #main{
                width: 100%;
                height: 80%;
                border: 1px solid black;
            }
            #mainNav{
                width: 100%;
                height: 20%;
                border: 1px solid black;
            }
            #mainNav li{
                border: 1px solid black;
                float: left;
                list-style : none;
                width: 20%;
                height: 100%;
                line-height: 30px;
            }
            #mainNav li a{
                display: block;
                text-decoration: none;
            }
            li:hover{
                background-color: cornsilk;
            }
        </style>
    </head>
    <body>
        <header>
            <div id="main">
            <div id="title">
            
            </div>
            
            <div id="rightBanner">
            
            <div id="welcome">
                <p style="line-height: 130px; text-align: right;">${sessionScope.driver.driverId}님</p>        
            </div>    
            <div id="logout">
                <form action="/mypage/logout">
                    <button type="button" class="btn btn-default" style="margin-top: -20px;">로그아웃</button>
                </form>
            </div>
                <div id="mypage">
                    <a href="mypage.html" id="MyPageImg"><img src="../img/MyPageImg/MyPageImg.png" style="width: 100px; height: 100px; margin-top: -125px;" class="img-circle"></a> 
                </div>
                
                </div>
            </div>
            <div id="mainNav">
                <li></li>
                <li><a href="noticelist.html">공지사항</a></li>
                <li><a href="driverList.html">기사 소개</a></li>
                <li><a href="reviewlist.html">사용자 후기</a></li>
                <li><a href="qnalist.html">Q&A게시판</a></li>
            </div>
            
        </header>
        
        <section>
            <section id="navi">
                <nav id="navigator">
                    <!-- if문으로 li 개수 조절 -->
                    <li><a href="travelCheck.html">예약확인/ 예약취소</a></li>
                    <li><a href="/message/receiverList?receiver=${ sessionScope.driver.driverId }">쪽지함</a></li>
                    <li><a href="/login/driverMyInfo.jsp">회원정보 수정</a></li> 
                    <li><a href="/driver/delete?driverId=${sessionScope.driver.driverId }">회원 탈퇴</a></li>
                    <li><a href="selectHoliday.html">My 일정관리</a></li>
                </nav>
                
            </section>
            <section id="contents">
                <section id="mybooking">
                    <p style="line-height: 80px;">&nbsp;&nbsp;My 예약</p>
                </section>
                
                <section id="mybookinglist">
                    <br><br>
                    <table class="table" style="width: 70%;height: 30px;">
                     <thead>
                       <tr>
                           <th>여행 날짜</th>
                           <th>여행 장소</th>
                           <th>담당 기사</th>
                           <th>예약 상태</th>
                       </tr>
                     </thead>
                     <tbody>

                     </tbody> 

                    </table>
                </section>
            </section>  
        </section>
        
        <footer>
            <span id="copyright">Copyright 2020 CLOUD All rights reserved</span>
        </footer>
       
    </body>

</html>