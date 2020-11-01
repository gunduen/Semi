<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function subChk(){
		var driverInfo = document.driverIdFindInfo;
		
		if(!driverInfo.driverName.value){
			alert('이름을 입력해주세요.');
			return false;
		}
		if(!driverInfo.driverEmail.value){
			alert('이메일 주소를 입력해주세요.');
			return false;
		}
	}
</script>
</head>

<body>
    <div>
        <form action="/driver/findid" name="driverIdFindInfo" method="post" onsubmit="return subChk();">
            <h2>아이디 찾기</h2>
            <p>
               아래 정보를 입력하시면<br> 아이디를 찾을 수 있습니다.<br>
                <br>
            <div>
                <label>이름 : <input type="text" name="driverName" placeholder="이름"></label>
            </div>
            <div>
                <label>이메일 : <input type="text" name="driverEmail" placeholder="이메일"></label>
                <p>정보를 정확히 입력해주세요.</p>
            </div>
 
            <div>
                <button type="submit">아이디찾기</button>
            </div>
        </form>
    </div>
</body>


</html>