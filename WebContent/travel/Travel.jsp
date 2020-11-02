

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=0g63w7xmeu&submodules=geocoder"></script>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a8e5007416460a5bee56aaba2bb1ea6d&libraries=services"></script>

<title>CSS 구조만들기7</title>
<style>
header {
	width: 100%;
	height: 150px;
}

section {
	width: 100%;
	height: 750px;
}

footer {
	width: 100%;
	height: 150px;
}
</style>
</head>
<body>
	<header></header>
	<section>
		<form name="form" action="/travel/pay.jsp" method="post">
			<h1>예약하기</h1>
			
			<input id="addrSearchBtn" type="button" onclick="addSearch();"value="주소 검색"><br> 
			<input type="text" id="sido" name="sido" style="width: 400px; display: inline-block;" placeholder="시도" value="${sido }"><br> 
			<input type="text" id="roadAddr" name="roadAddr" style="width: 400px; display: inline-block;" placeholder="도로명주소" value="${address }"><br>
			<div id="map" style="width: 300px; height: 300px; margin-top: 10px;"></div><br>
			
			
			<input type="date" name="package_Date" id="package_Date" value="${packageDate }"><br> <select id="bb" name="package_Item">
				<option value="3시간 : 80,000원">3시간 : 80,000원</option>
				<option value="5시간 : 120,000원">5시간 : 120,000원</option>
				<option value="종일권(10시간) : 180,000원">종일권(10시간) : 180,000원</option>
			</select><br> <select name="driverName" id="aa">
				<c:forEach items="${tList }" var="driver">
					<option value="${driver.driverName }">${driver.driverName }</option>
				</c:forEach>
			</select>
			<c:forEach items="${tList }" var="driver">
				<input type="hidden" id="driverId" name="driverId" value="${driver.driverId }">
			</c:forEach>
			<br>
			<input type="hidden" name = "coordx" value="${coordx }">
			<input type="hidden" name = "coordy" value="${coordy }">
			<input class="btn" type="submit" value="예약 및 결제 하기">
			

			<article id="article4">
				<div>
					<h2>예약 변경 및 취소:</h2>
					변경 가능한 요금 제도 - 예약시간 기준 24시간 전까지 위약금 없이 변경, 취소 할 수 있습니다 24시간이 지난
					이후에는 추가 요금이 적용 됩니다.<br> 고객이 노쇼하는(오지 않는) 경우에는 예약금(50,000원)이 반환
					불가합니다.
				</div>
				<br> <br> <br>
			</article>
		</form>
	</section>
	<footer></footer>

	<script>
		var coords;
		var sido;
		var roadAddr;
		function addSearch() {
			new daum.Postcode({
				// 주소 찾기  API가 한 일은 주소검색창 팝업 띄어주고
				// 검색결과 항목을 선택했을 때 선택한 값에 대한 데이터를 보내주는 역할
				// 자바스크립트 객체(복습)
				oncomplete : function(data) {
					// 팝업에서 검색결과 항목을 클릭을 하면 그 때의 데이터가 data 매개변수로
					// 넘어오고 console.log로 그 data를 출력해봄
					// 내가 선택한 input태그에 api서버가 응답한 값을 셋팅을 해줌
					sido = data.sido;
					roadAddr = data.address;
					$("#roadAddr").val(roadAddr);
					$("#sido").val(sido);
					//location.href="/travel/travelList?sido="+sido+"&address="+roadAddr;
					// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
					// 예제를 참고하여 다양한 활용법을 확인해 보세요.
					geocoder.addressSearch(data.address, function(results, status) {
						// 정상적으로 검색이 완료됐으면
						if (status === daum.maps.services.Status.OK) {

							var result = results[0]; //첫번째 결과의 값을 활용

							// 해당 주소에 대한 좌표를 받아서
							coords = new daum.maps.LatLng(result.y, result.x);
							// 지도를 보여준다.
							mapContainer.style.display = "block";
							map.relayout();
							// 지도 중심을 변경한다.
							map.setCenter(coords);
							// 마커를 결과값으로 받은 위치로 옮긴다.
							marker.setPosition(coords)
						}
					});
				}
			}).open();
		}
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div
		mapOption = {
			center : new daum.maps.LatLng('${coordx}','${coordy}'), // 지도의 중심좌표
			level : 1
		// 지도의 확대 레벨
		};
		//지도를 미리 생성
		var map = new daum.maps.Map(mapContainer, mapOption);
		//주소-좌표 변환 객체를 생성
		var geocoder = new daum.maps.services.Geocoder();
		//마커를 미리 생성
		var marker = new daum.maps.Marker({
			position : new daum.maps.LatLng('${coordx}', '${coordy}'),
			map : map
		});
		
		
		
		
		var geocoder = new kakao.maps.services.Geocoder();

		var marker = new kakao.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
		    infowindow = new kakao.maps.InfoWindow({zindex:1}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다
		
		
		kakao.maps.event.addListener(map, 'click', function(mouseEvent) {    
			coords = mouseEvent.latLng;
			searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
		        if (status === kakao.maps.services.Status.OK) {
		            var detailAddr = !!result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
		            detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';
		            
		            var content = '<div class="bAddr">' +
		                            '<span class="title">법정동 주소정보</span>' + 
		                            detailAddr + 
		                        '</div>';
		                        
		                        
					
		            // 마커를 클릭한 위치에 표시합니다 
		            marker.setPosition(mouseEvent.latLng);
		            marker.setMap(map);

		            // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
		            infowindow.setContent(content);
		            infowindow.open(map, marker);
		            $("#roadAddr").val(result[0].road_address.address_name);
		            
		         // 해당 주소에 대한 좌표를 받아서
					
					
		        }   
		    });
		});		
		
		// 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
		kakao.maps.event.addListener(map, 'idle', function() {
		    searchAddrFromCoords(map.getCenter(), displayCenterInfo);
		});

		function searchAddrFromCoords(coords, callback) {
		    // 좌표로 행정동 주소 정보를 요청합니다
		    geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);         
		}

		function searchDetailAddrFromCoords(coords, callback) {
		    // 좌표로 법정동 상세 주소 정보를 요청합니다
		    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
		}

		// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
		function displayCenterInfo(result, status) {
		    if (status === kakao.maps.services.Status.OK) {
		        var infoDiv = document.getElementById('centerAddr');

		        for(var i = 0; i < result.length; i++) {
		            // 행정동의 region_type 값은 'H' 이므로
		            if (result[i].region_type === 'H') {
		                infoDiv.innerHTML = result[i].address_name;
		                break;
		            }
		        }
		    }    
		}		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		$("#package_Date").on("change keyup paste",function() {
					var packageDate = $("#package_Date").val();
					location.href = "/travel/travelList?package_Date="
							+ packageDate + "&address=" + roadAddr + "&sido="
							+ sido+"&coords="+coords;
				});
		
		
		
	</script>
</body>
</html>