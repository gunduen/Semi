<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 체크 페이지</title>
<style>
	#wrap {
            width: 490px;
            text-align :center;
            margin: 0 auto 0 auto;
        }
        
        #chk{
            text-align :center;
        }
        
        #cancelBtn{
            visibility:visible;
        }
        
        #useBtn{
             visibility:hidden;
             
        }
		
		
	
</style>
<script>
	var httpRequest = null; //getXMLHttpRequest()함수로 생성한 객체를 저장하기위해 선언한 변수.
	
	function getXMLHttpRequest(){
		if(window.ActiveXObject){
			try{
				httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
				
			}catch(e){
				try{
					httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
					
				}catch(e1){
					httpRequest = null;}
			}
	
		}else if(window.XMLHttpRequest){
			httpRequest = new XMLHttpRequest();
		}
		return httpRequest;
			
	}
	function pValue(){
        document.getElementById("driverEmailId").value =  opener.document.userInfo.emailId.value;
        document.getElementById("driverEmailTag").value = opener.document.userInfo.emailTag.value;
    }
    
    // 아이디 중복체크
    function emailCheck(){

        var emailId = document.getElementById("driverEmailId").value;
		var emailTag =  document.getElementById("driverEmailTag").value;
        if (!emailId) {
            alert("이메일 아이디를 입력하지 않았습니다.");
            document.getElementById("useBtn").style.visibility='hidden';
            document.getElementById("driverEmail").style.border = '3px solid red';
            return false;
        }else if((emailId < "0" || emailId > "9") && (emailId < "A" || emailId > "Z") && (emailId < "a" || emailId > "z")){ 
            alert("한글 및 특수문자는 이메일 아이디로 사용하실 수 없습니다.");
            document.getElementById("useBtn").style.visibility='hidden';
            document.getElementById("driverEmail").style.border = '3px solid red';
            return false;
        }else{
        	var email = emailId+emailTag;
        	console.log(email);
            var param="email="+email;
            httpRequest = getXMLHttpRequest();
            httpRequest.onreadystatechange = callback;
            httpRequest.open("POST", "/customer/emailCheck", true);    
            httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
            httpRequest.send(param);
            
        }
    }
    
    function callback(){
        if(httpRequest.readyState == 4){
            // 결과값을 가져온다.
            var resultText = httpRequest.responseText;
            if(resultText == 0){
                alert("사용할수없는 아이디입니다.");
                document.getElementById("cancelBtn").style.visibility='visible';
                document.getElementById("useBtn").style.visibility='hidden';
                document.getElementById("msg").innerHTML ="";
                document.getElementById("driverEmailId").style.border = '3px solid red';
            } 
            else if(resultText == 1){ 
                document.getElementById("cancelBtn").style.visibility='hidden';
                document.getElementById("useBtn").style.visibility='visible';
                document.getElementById("msg").innerHTML = "사용 가능한 아이디입니다.";
                document.getElementById("driverEmailId").style.border = '3px solid green';
            }
        }
    }
    
    // 사용하기 클릭 시 부모창으로 값 전달 
    function sendCheckValue(){
        // 중복체크 결과인 idCheck 값을 전달한다.
        opener.document.userInfo.emailCheck.value ="emailCheck";

       
        // 회원가입 화면의 ID입력란에 값을 전달
        opener.document.userInfo.emailId.value = document.getElementById("driverEmailId").value;
        opener.document.userInfo.emailTag.value = document.getElementById("driverEmailTag").value;
        if (opener != null) {
            opener.chkForm = null;
            self.close();
        }    
    }    



</script>
</head>
<body onload="pValue();">
<div id="wrap">
    <br>
    <b><font size="4" color="gray">이메일 중복체크</font></b>
    <hr size="1" width="460">
    <br>
    <div id="chk">
        <form id="checkForm">
            <input type="text" name="emailInput" id="driverEmailId"><input type="text" name="emailInput" id="driverEmailTag">
            <input type="button" value="중복확인" onclick="emailCheck()">
        </form>
        <div id="msg"></div>
        <br>
        <input id="cancelBtn" type="button" value="취소" onclick="window.close()"><br>
        <input id="useBtn" type="button" value="사용하기" onclick="sendCheckValue()">
    </div>
</div>    
</body>
</html>
