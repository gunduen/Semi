<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>휴대폰 중복 체크 페이지</title>
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
			//Msxml2.XMLTTP -> 먼저 시도
			try{
				httpRequest = new ActiveXObject("Msxml2.XMLHTTP");
				
			}catch(e){
				try{
					httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
					
				}catch(e1){
					httpRequest = null;}
			}
	
		}else if(window.XMLHttpRequest){
			//IE 제외하고 파이어폭스 등 브라우저에서 HTTPREQUEST구하기
			httpRequest = new XMLHttpRequest();
		}
		return httpRequest;
			
	}
	function pValue(){
        document.getElementById("firstPhone").value = opener.document.userInfo.firstPhone.value;
        document.getElementById("secondPhone").value = opener.document.userInfo.secondPhone.value;
        document.getElementById("thirdPhone").value = opener.document.userInfo.thirdPhone.value;
    }
    
    // 아이디 중복체크
    function phoneCheck(){

        var phoneNum = document.getElementById("firstPhone").value+"-"+document.getElementById("secondPhone").value+"-"
        +document.getElementById("thirdPhone").value;

        console.log(phoneNum.length);
        
        if (!phoneNum) {
            alert("휴대폰 번호를 입력하지 않았습니다.");
            document.getElementById("useBtn").style.visibility='hidden';
            document.getElementById("firstPhone").style.border = '3px solid red';
            document.getElementById("secondPhone").style.border = '3px solid red';
            document.getElementById("thirdPhone").style.border = '3px solid red';
            return false;
        } 
        else if(phoneNum.length!=13){ 
            alert("휴대폰번호를 전부 입력해주세요.");
            document.getElementById("useBtn").style.visibility='hidden';
            document.getElementById("firstPhone").style.border = '3px solid red';
            document.getElementById("secondPhone").style.border = '3px solid red';
            document.getElementById("thirdPhone").style.border = '3px solid red';
            return false;
        }else{
            var param="phoneNum="+phoneNum;
            httpRequest = getXMLHttpRequest();
            httpRequest.onreadystatechange = callback;
            httpRequest.open("POST", "/customer/phoneCheck", true);    
            httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
            httpRequest.send(param);
            
        }
    }
    
    function callback(){
        if(httpRequest.readyState == 4){
            // 결과값을 가져온다.
            var resultText = httpRequest.responseText;
            if(resultText == 0){
                alert("사용할수없는 번호입니다.");
                document.getElementById("cancelBtn").style.visibility='visible';
                document.getElementById("useBtn").style.visibility='hidden';
                document.getElementById("msg").innerHTML ="";
                document.getElementById("firstPhone").style.border = '3px solid red';
                document.getElementById("secondPhone").style.border = '3px solid red';
                document.getElementById("thirdPhone").style.border = '3px solid red';
            } 
            else if(resultText == 1){ 
                document.getElementById("cancelBtn").style.visibility='hidden';
                document.getElementById("useBtn").style.visibility='visible';
                document.getElementById("msg").innerHTML = "사용 가능한 번호입니다.";
                document.getElementById("firstPhone").style.border = '3px solid green';
                document.getElementById("secondPhone").style.border = '3px solid green';
                document.getElementById("thirdPhone").style.border = '3px solid green';
            }
        }
    }
    
    // 사용하기 클릭 시 부모창으로 값 전달 
    function sendCheckValue(){
        // 중복체크 결과인 idCheck 값을 전달한다.
        opener.document.userInfo.phoneCheck.value ="phoneCheck";
        console.log(opener.document.userInfo.phoneCheck.value);
        // 회원가입 화면의 ID입력란에 값을 전달
        opener.document.userInfo.firstPhone.value = document.getElementById("firstPhone").value;
        opener.document.userInfo.secondPhone.value = document.getElementById("secondPhone").value;
        opener.document.userInfo.thirdPhone.value = document.getElementById("thirdPhone").value;
        
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
    <b><font size="4" color="gray">휴대폰번호 중복체크</font></b>
    <hr size="1" width="460">
    <br>
    <div id="chk">
        <form id="checkForm">
            <input type="text" name="firstPhone" id="firstPhone" maxlength="3" size="2">-<input type="text" name="secondPhone" id="secondPhone" maxlength="4" size="3">-
            <input type="text" name="thirdPhone" id="thirdPhone" maxlength="4" size="3">
            <input type="button" value="중복확인" onclick="phoneCheck()">
        </form>
        <div id="msg"></div>
        <br>
        <input id="cancelBtn" type="button" value="취소" onclick="window.close()"><br>
        <input id="useBtn" type="button" value="사용하기" onclick="sendCheckValue()">
    </div>
</div>    
</body>
</html>
