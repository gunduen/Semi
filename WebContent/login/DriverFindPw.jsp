<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function subChk(){
		var driverPwFindInfo = document.driverPwFindInfo;
		
		if(!driverPwFindInfo.driverName.value){
			alert('이름을 입력해주세요.');
			return false;
		}
		if(!driverPwFindInfo.driverId.value){
			alert('아이디를 입력해주세요.');
			return false;
		}
		
	}
</script>
</head>

<body>
    <div>
        <form action="/driver/findpw" name="driverPwFindInfo" method="post" onsubmit="return subChk();">
            <h2>비밀번호 찾기</h2>
            <p>
               아래 정보를 입력하시면,<br> 가입시 입력한 이메일로 새암호를 보내드립니다<br>
                <br>
            <div>
                <label>이름 : <input type="text" name="driverName" placeholder="이름"></label>
            </div>
            <div>
                <label>아이디 : <input type="text" name="driverId" placeholder="아이디"></label>
                <p>정보를 정확히 입력해주세요.</p>
            </div>
 
            <div>
                <button type="submit">비밀번호찾기</button>
            </div>
        </form>
    </div>
</body>


</html>