<%@page import="java.util.List" %>
<%@page import="db.Member" %>
<%@page import="db.MemberService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<style>
	table{
		width: 100%;
		border-spacing : 0;
	}
	th, td {
		border: solid 0.1px #000;
		text-align : center;
	}
	p {
		text-align : center;
	}

	button {
		margin-top: 5px;
		margin-bottom : 5px;
	}
	thead {
		background-color: green;
		color: white;
	}
	tbody tr:nth-child(odd) {
    background-color: #ffffff; /* 홀수 줄 배경색 */
 	}

 	tbody tr:nth-child(even) {
    background-color: #f0f0f0; /* 짝수 줄 배경색 */
  	}
	</style>
</head>
<body>
	<h1>와이파이 정보 구하기</h1>
	
	<!-- 메인페이지, 내용 상세보기, 북마크 그룹에서 공통으로 사용하는 부분 common.jsp  -->
	<%@ include file="common.jsp" %>

	<div>
     <form style="display: inline;">
        LAT: <input type = "text" id = "latitude" value = "0.0" />
        , LNT: <input type = "text" id = longitude value = "0.0" /> 
     </form>
     

 	<button onclick="clickBtn1()">내 위치 가져오기</button>
    <button onclick="clickBtn2()">근처 WIPI 정보 보기</button>
	</div>

    <table>
		<thead>
			<tr>
				<th>거리(Km)</th>
				<th>관리번호</th>
				<th>자치구</th>
				<th>와이파이명</th>
				<th>도로명주소</th>
				<th>상세주소</th>
				<th>설치위치(층)</th>
				<th>설치유형</th>
				<th>설치기관</th>
				<th>서비스구분</th>
				<th>망종류</th>
				<th>설치년도</th>
				<th>실내외구분</th>
				<th>WIFI접속환경</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>작업일자</th>
			</tr>

		</thead>
		<tbody id="tableBody">
			<tr>
				<td id="phide" colspan = "17">위치 정보를 입력한 후에 조회해 주세요.</td>
			</tr>
		</tbody>
	</table>

	<script>
	    function executeDistance() {
	    	window.navigator.geolocation.getCurrentPosition( function(position){ //OK
	        var lat= position.coords.latitude;
	        var lng= position.coords.longitude;
	    	
	        var xhr = new XMLHttpRequest();
	        var url = "distance.jsp"; // distance.jsp 파일 경로
	        var params = "lat=" + encodeURIComponent(lat) + "&lng=" + encodeURIComponent(lng);
	        xhr.open("POST", url, true);
	        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	        xhr.send(params);
            } ,
            function(error){ //error
                switch(error.code){
                    case error.PERMISSION_DENIED:
                        str="사용자 거부";
                        break;
                    case error.POSITION_UNAVAILABLE:
                        str="지리정보 없음";
                        break;
                    case error.TIMEOUT:
                        str="시간 초과";
                        break;
                    case error.UNKNOWN_ERROR:
                        str="알수없는 에러";
                        break;
                }
                document.getElementById("latitude").value = str;
                document.getElementById("longitude").value = str;
                
            });
        	
        } 
	</script>
	<script>
	        function clickBtn1(){
	        	// 내 좌표 구하는 부분
	            // BOM의 navigator객체의 하위에 geolocation객체가 새로 추가되었음.
	            window.navigator.geolocation.getCurrentPosition( function(position){ //OK
	                var lat= position.coords.latitude;
	                var lng= position.coords.longitude;
	 
	                document.getElementById("latitude").value = lat;
	                document.getElementById("longitude").value = lng;
	                
	                var xhr = new XMLHttpRequest();	// XMLHttpRequest 객체 생성
	                var url = "coordinate.jsp"; // url 설정 -> JSP 파일 경로
	                var params = "lat=" + encodeURIComponent(lat) + "&lng=" + encodeURIComponent(lng); // 전송할 데이터
	                xhr.open("POST", url, true);
	                /*
	                 요청 설정
	                - POST 방식
	                - setRequestHeader 메서드를 사용하여 요청 헤더 설정
	                */
	                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	                xhr.send(params);	// 요청 전송


	            } ,
	            function(error){ //error
	                switch(error.code){
	                    case error.PERMISSION_DENIED:
	                        str="사용자 거부";
	                        break;
	                    case error.POSITION_UNAVAILABLE:
	                        str="지리정보 없음";
	                        break;
	                    case error.TIMEOUT:
	                        str="시간 초과";
	                        break;
	                    case error.UNKNOWN_ERROR:
	                        str="알수없는 에러";
	                        break;
	                }
	                document.getElementById("latitude").value = str;
	                document.getElementById("longitude").value = str;
	                
	            });
	        	
	        }        

	</script>
	<script>
	function clickBtn2() {
		var paragraph = document.getElementById("phide");	// 가까운 WIFI 정보 출력 시 기존에 있던 내용 숨기기
        paragraph.style.display = "none";					// 133번 줄 참고
        
	    var xhr = new XMLHttpRequest();
        var url = "wifiList.jsp"; // JSP file path
        xhr.open("GET", url, true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var response = xhr.responseText;
                var tableBody = document.getElementById("tableBody");
                tableBody.innerHTML = response;
            }
        };
        xhr.send();
    }
	</script>	

</body>
</html>