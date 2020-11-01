<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>


    
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01

 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


    
<html>
<head>

 <script type="text/javascript">
        $(document).ready(function(){
    
            $("#agree1").click(function(){    
                if($("#check_1").is(":checked") == false){
                    alert("모든 약관에 동의 하셔야 다음 단계로 진행 가능합니다.");
                    return false;
                }else if($("#check_2").is(":checked") == false){
                    alert("모든 약관에 동의 하셔야 다음 단계로 진행 가능합니다..");
                    return false;
                }else if($("#check_3").is(":checked") == false){
                    alert("모든 약관에 동의 하셔야 다음 단계로 진행 가능합니다...");
                    return false;
                }else if($("#check_4").is(":checked") == false){
                    alert("모든 약관에 동의 하셔야 다음 단계로 진행 가능합니다...");
                    return false;
                }else{
                    $("#terms_form").submit();
                }
            });    
        });
        
        
        
        $(document).ready(function(){
            
            $("#agree2").click(function(){    
                if($("#check_1").is(":checked") == false){
                    alert("모든 약관에 동의 하셔야 다음 단계로 진행 가능합니다.");
                    return  false;
                }else if($("#check_2").is(":checked") == false){
                    alert("모든 약관에 동의 하셔야 다음 단계로 진행 가능합니다..");
                    return  false;
                }else if($("#check_3").is(":checked") == false){
                    alert("모든 약관에 동의 하셔야 다음 단계로 진행 가능합니다...");
                    return  false;
                }else if($("#check_4").is(":checked") == false){
                    alert("모든 약관에 동의 하셔야 다음 단계로 진행 가능합니다...");
                    return  false;
                }else{
                    $("#terms_form").submit();
                }
            });    
        });
        
        
        //------------------------------ 모두 체크하기 및 해지 -----------------------------
        
   $(document).ready(function() {
    
     $("#chk").click(function(){
            //만약 전체 선택 체크박스가 체크된상태일경우
            if($("#chk").prop("checked")) {
                $("input[type=checkbox]").prop("checked",true);
                //input type이 체크박스인것은 모두 체크함
            } else {
                $("input[type=checkbox]").prop("checked",false);
                //input type이 체크박스인것은 모두 체크 해제함
            }
      });
});
   
        
        
    </script>






<!--   <script src="http://code.jquery.com/jquery-3.3.1.min.js"></script> -->
  
  
  
  
  
  <!-- <script type="text/javascript">


   
  function chk1(){
	  var chk = document.form.chk.checked;
	  var num = 0;
	  if(chk == true){
	   num = 1;
	  }
	  if(num==1){
	   document.form.submit();		   	   
	  }else{
	   alert("개인정보 약관에 동의하셔야 합니다.");
	  }
	 }
  
  
	 
  
	 
	 
    </script> -->

  
<!--     <script>
    $(function(){
    	
    	$('#agree1').val(function() {
    		
    		var agree1 = $('#agree1').val()
    		var num = 0;
    		if( agree1 == true) {
    			
    		}else{
    			
    			alert("약관에 동의 해주세요");
    			
    			$('#agree1').val ('');
    			}
    	})
    }) ;
</script -->


<!-- <!-- <script type="text/javascript">
function chk(){
 var req = document.form.req.checked;
 var num = 0;
 if(req == true){
  num = 1;
 }
 if(num==1){
  document.form.submit();
 }else{
  alert("개인정보 약관에 동의하셔야 합니다.");
 }
}
</script  -->





<meta charset="UTF-8">
<title>개인정보 약관 동의</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

    
    
    <style>
    *{margin: 0;padding: 0;box-sizing: border-box}
body{background-color: #f7f7f7;
        }
ul>li{list-style: none
        }
a{text-decoration: none;}
.clearfix::after{content: "";display: block;clear: both;
        }
        #joinForm{width: 460px;margin: 0 auto;
        }
ul.join_box{border: 1px solid #ddd;background-color: #fff;
        }
.checkBox,.checkBox>ul{position: relative;
        }
.checkBox>ul>li{float: left;
        }
.checkBox>ul>li:first-child{width: 85%;padding: 15px;font-weight: 600;color: #888;
        }
.checkBox>ul>li:nth-child(2){position: absolute;top: 50%;right: 30px;margin-top: -12px;
        }
.checkBox textarea{width: 96%;height: 90px; margin: 0 2%;background-color: #f7f7f7;color: #888; border: none;
        }
.footBtwrap{margin-top: 15px;
        }
.footBtwrap>li{float: left;width: 50%;height: 60px;
        }
.footBtwrap>li>button{display: block; width: 100%;height: 100%; font-size: 20px;text-align: center;line-height: 60px;
        }
#agree1{background-color: #fff; color:#888; width: 400px;  height: 80px; margin-left: 550px;  text-align: center;
        }
#agree2{background-color: lightblue;   color: #fff; width: 400px;  height: 80px;  margin-right: 390px;     text-align: center;
        }
        #wrap {
            text-align: center;
        }
        .radio {
            text-align: center;
                                          
        }
       
        
                
           
                
        }


        



    </style>
</head>
<body>
    
    <section id="terms_form">
    <!-- <form  action="#" name="form" method="post" id="terms_form"> -->
    
    
     <div id="wrap">
   <h2 class="member">개인정보 이용약관</h2>
   
      <a href="" target="_blank" title="로그인"><img src="../img/%EA%B7%BC%EB%91%90%EC%9A%B4.PNG" id="logo"></a>
       </div>
   
            <ul class="join_box">
               <li class="checkBox check01">
                    <ul class="clearfix">
                        <li>이용약관, 개인정보 수집 및 이용,
                            위치정보 이용약관(필수), 프로모션 안내
                            메일 수신(필수)에 모두 동의합니다.</li>
                        <li class="checkAllBtn">
                            <input type="checkbox" name="chkAll" id="chk" class="chkAll">
                        </li>
                    </ul>
                </li>

                <li class="checkBox check02">
                    <ul class="clearfix">
                        <li>이용약관 동의(필수)</li>
                        <li class="checkBtn">
                            <input type="checkbox" name="chk" id="check_1"> 
                        </li>
                    </ul>
                    <textarea name="" id="">여러분을 환영합니다.
근두운 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 근두운 서비스의 이용과 관련하여 근두운 서비스를 제공하는 근두운 주식회사(이하 ‘근두운’)와 이를 이용하는 근두운 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 근두운 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
       </textarea>
                </li>
                <li class="checkBox check03">
                    <ul class="clearfix">
                        <li>개인정보 수집 및 이용에 대한 안내(필수)</li>
                        <li class="checkBtn">
                            <input type="checkbox" name="chk" id="check_2">
                        </li>
                    </ul>
 
                    <textarea name="" id="">여러분을 환영합니다.
근두운 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 근두운 서비스의 이용과 관련하여 근두운 서비스를 제공하는 근두운 주식회사(이하 ‘근두운’)와 이를 이용하는 근두운 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 근두운 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
       </textarea>
                </li>
                <li class="checkBox check03">
                    <ul class="clearfix">
                        <li>위치정보 이용약관 동의(필수)</li>
                        <li class="checkBtn">
                            <input type="checkbox" name="chk" id="check_3">
                        </li>
                    </ul>
 
                    <textarea name="" id="">여러분을 환영합니다.
근두운 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 근두운 서비스의 이용과 관련하여 근두운 서비스를 제공하는 근두운 주식회사(이하 ‘근두운’)와 이를 이용하는 근두운 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 근두운 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
       </textarea>
                </li>
                <li class="checkBox check04">
                    <ul class="clearfix">
                        <li>이벤트 등 프로모션 알림 메일 수신(필수)</li>
                        <li class="checkBtn">
                            <input type="checkbox" name="chk" id="check_4">
                        </li>
                    </ul>
 
                </li>
            </ul>
        
     <!-- onclick="chk1()" -->
          
            <ul class="footBtwrap clearfix">
            	<li>
            		<form action="#고객회원가입페이지" method="post"> 	
                	<!--<button type="button" onclick="' " class="fpmgBt1">고객회원가입</button>-->
                    	<input type="submit" id="agree1"  class="fpmgBt1" value = "고객회원가입">
               		</form>
                </li>
                <li>
                	<form action="#기사회원가입페이지" method="post"> 
               			<input  type="submit" id="agree2" class="fpmgBt2" value ="기사회원가입">
               		</form>
               	</li>
            </ul>
        
	<!-- </form> -->
</section>


</body>
</html>