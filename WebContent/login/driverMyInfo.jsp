<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="driver.model.vo.Driver" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/driver/update" method="post">
		<div id="title">기사회원정보 수정</div>
		<table border="1px">
			<tr>
				<th>ID</th>
				<td><input type="text" value="${sessionScope.driver.driverId }" name="driverId" maxlength="20" readonly></td>
				<td></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" placeholder="내용을 입력해주세요" name="driverPwd" maxlength="15"  value="${driver.driverPwd}"></td>
				<td></td>
			</tr>
			
			<tr>
				<th>비밀번호 확인</th>
				<td><input type="text" placeholder="내용을 입력해주세요" maxlength="15"></td>
				<td></td>
			</tr>
			
			<tr>
				<th>이름</th>
				<td><input type="text" placeholder="내용을 입력해주세요" name="driverName"  maxlength="10" value="${driver.driverName }"></td>
				<td></td>
			</tr>
			
			<tr>
				<th>핸드폰</th>
				<td><input type="text" placeholder="내용을 입력해주세요" name="driverPhone" value="${driver.driverPhone }"></td>
				<td></td>
			</tr>
			
			
			<tr>
				<th>E-mail</th>
				<td><input type="text" placeholder="내용을 입력해주세요" name="driverEmail" value="${driver.driverEmail }"></td>
				<td><input type="button" value="인증" class="btn"></td>
			</tr>
			
			
			<tr>
				<th>자택주소</th>
				<td><input type="text" placeholder="내용을 입력해주세요" name="driverHome" value="${driver.driverHome }"></td>
				<td></td>
			</tr>
			
			<tr>
				<th>지역선택</th>
				<td><input type="text" placeholder="내용을 입력해주세요" name="driverArea" value="${driver.driverArea }"></td>
				<td></td>
			</tr>
			<tr>
				<th>자기소개</th>
				<td><input type="text" placeholder="내용을 입력해주세요" name="driverSelfIntro" value="${driver.driverSelfInfo }"></td>
				<td></td>
			</tr>
			
			<tr>
				<th>운전면허증</th>
				<td><input type="text" placeholder="내용을 입력해주세요" name="driverLicense" value="${driver.driverLicense}"></td>
				<td></td>
			</tr>
			
			<tr>
				<th>사업자등록증</th>
				<td><input type="text" placeholder="내용을 입력해주세요" name="driverBlicense" value="${driver.driverBLicense}"></td>
				<td></td>
			</tr>
			
			<tr>
				<th>개인 사진</th>
				<td><input type="text" placeholder="내용을 입력해주세요" name="driverInfoImage" value="${driver.driverInfoImage}"></td>
				<td></td>
			</tr>
			
			
			
			
			
			
			
			
			
			
			
		</table>
		<div id="btn">
			<input type="submit" value="수정완료" class="btn"> <input
				type="reset" value="수정취소" class="btn">
		</div>
	</form>
</body>
</html>