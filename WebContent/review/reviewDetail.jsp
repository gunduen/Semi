<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="review.model.vo.Review"%>
<%@page import="comment.model.vo.Comment"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후기 디테일</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script>
    	window.onload = function() {
    		var flag = '${checkReview }';
    		if(flag != '' && flag == 'false') {
    			alert("Review End!!");
    		}
    	};	
    </script>
</head>
<body>
	<!-- 후기 상세 -->
	<div> 예약번호 : ${review.packageCode }</div>
	<div>제목 : ${ review.reviewSubject }</div>
	<div>내용 : ${ review.reviewContents }</div>
	<div>작성자 : ${ review.customerId}</div>
	<div>작성날짜 : ${review.reviewDate }</div>
	<c:if
		test="${sessionScope.customer ne null && (sessionScope.customer.customer_Id eq review.customerId) }">
		
			<button type="button" onclick="delfunc();">삭제</button>
			<button type="button" onclick="updatefunc(${review.reviewNo});">수정</button>
		<script>
			function updatefunc(num) {
				location.href="/review/modifyForm?reviewNo="+num;
			}
		</script>
	</c:if>
	<hr>
	<!-- 댓글 -->
	<!-- 로그인 안했을 때  -->
	<c:if
		test="${ sessionScope.customer eq null && (sessionScope.driver eq null)}">
		<form action="/comment/insert?reviewNo=${ review.reviewNo }">
			<div>댓글</div>
			<textarea style="width: 100%; height: 50px" name="commentContents"
				placeholder="로그인 후 이용해주세요" readonly></textarea>
		</form>
	</c:if>
	
	<c:if
		test="${sessionScope.customer ne null || (sessionScope.driver ne null)}">
		<!-- 고객 댓글입니다!! -->
		<c:if
			test="${ (sessionScope.customer ne null) && (sessionScope.driver eq null)}">
			<form action="/comment/insert?reviewNo=${ review.reviewNo }">
				<div>댓글</div>
				<div>나의 아이디 ' ${ sessionScope.customer.customer_Id } '</div>
				<input type="hidden" name="customerId" value="${ sessionScope.customer.customer_Id }"> 
				<input type="hidden" name="driverId" value="${ sessionScope.driver.driverId }"> 
				<input
					type="hidden" name="reviewNo" value="${ review.reviewNo }">
				<textarea style="width: 100%; height: 50px" name="commentContents"></textarea>
				<div id="commentBtn">
					<input type="submit" value="댓글입력">
				</div>
			</form>
		</c:if>
		<!-- 기사 댓글입니다.  -->
		<c:if
			test="${ (sessionScope.customer eq null) && (sessionScope.driver ne null)}">
			<form action="/comment/insert?reviewNo=${ review.reviewNo }">
				<div>댓글</div>
				<div>나의 아이디 ' ${ sessionScope.driver.driverId } '</div>
				<input type="hidden" name="customerId" value="${ sessionScope.customer.customer_Id }"> 
				<input type="hidden" name="driverId" value="${ sessionScope.driver.driverId }"> 
				<input
					type="hidden" name="reviewNo" value="${ review.reviewNo }">
				<textarea style="width: 100%; height: 50px" name="commentContents"></textarea>
				<div id="commentBtn">
					<input type="submit" value="댓글입력">
				</div>
			</form>
		</c:if>
	</c:if>


	<hr>
	
	<c:forEach items="${ CList }" var="comments" varStatus="index">
			<table>
				<tr class="comList"
					style="border: 1px solid gold; width: 800px; list-style: none; padding: 0 0;">
					<c:if test="${comments.customerId ne null }">
					<td style="border: 1px solid black; width: 10%; float: left;">${comments.customerId }</td>
					</c:if>
					<c:if test="${comments.customerId eq null }">
					<td style="border: 1px solid black; width: 10%; float: left;">${comments.driverId }</td>
					</c:if>
					<td style="border: 1px solid black; width: 80%; position: relative">${comments.commentDate }</td>
					<td style="border: 1px solid black; width: 100%;">${comments.commentContents }</td>
					<c:if test="${comments.customerId eq sessionScope.customer.customer_Id }">
					<td><button type="button" onClick="delComfunc();">삭제</button></td>
					</c:if>
				</tr>

			</table>
		<script>
			function delComfunc() {
				var con = confirm("정말로 삭제하시겠습니까?");
				if (con) {
					location.href="/comment/delete?commentNo="+${comments.commentNo} + "&reviewNo=" +${review.reviewNo};
				}
				/* if (con) {
					location.href="/comment/delete?commentNo="+${comments.commentNo}+"&?reviewNo="+${reviewOne.reviewNo};
				} */
			}
			</script>
	</c:forEach>
	<td colspan="3"align="center">${ pageNavi }</td>
	<div></div>
	<div><a href="/review/list?reviewArea=서울">후기게시판으로 이동</a></div>
	<script>
	
		function delfunc() {
			var con = confirm("정말로 삭제하시겠습니까?");
			if (con) {
				location.href="/review/delete?reviewNo="+${review.reviewNo};
			}
		}
		
	</script>
</body>
</html>