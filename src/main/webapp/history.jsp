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
	tr, th, td {
		border: solid 1px #000;
		text-align : center;
		
	}
	p {
		text-align : center;
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
	<h1>와이파이 상세정보</h1>
	<!-- 메인페이지, 내용 상세보기, 북마크 그룹에서 공통으로 사용하는 부분 common.jsp  -->
	<%@ include file="common.jsp" %>

	<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>X좌표</th>
					<th>Y좌표</th>
					<th>조회일자</th>
					<th>비고</th>
				</tr>
	
			</thead>
			<tbody id = "historyList">
			<%
			    MemberService historyMember = new MemberService();
				List<Member> MemberList = historyMember.historyList();
			%>
			
			<%
			    for (int i = 0; i < MemberList.size(); i++) {
			    	Member member = MemberList.get(i);
			        out.write("<tr>");
			        out.write("<td>" + member.getId() + "</td>");
			        out.write("<td>" + member.getX_coordi() + "</td>");
			        out.write("<td>" + member.getY_coordi() + "</td>");
			        out.write("<td>" + member.getLoad_date() + "</td>");
			        out.write("<td><button onclick=\"deleteMember(" + member.getId() + ")\">삭제</button></td>");
			        out.write("</tr>");
			    }
			%>
	
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
	function deleteMember(index) {
	    var xhr = new XMLHttpRequest();
	    var url = "historyListDelete.jsp"; // 삭제 처리를 수행할 java 파일 경로
	    var params = "index=" + index; // 삭제할 멤버의 인덱스를 파라미터로 전달
	
	    xhr.open("POST", url, true);
	    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	    xhr.onreadystatechange = function() {
	        if (xhr.readyState === 4 && xhr.status === 200) {
	            // 삭제 처리가 완료되었을 때 수행할 동작
	            alert("멤버가 삭제되었습니다.");
	            // 페이지를 새로고침하여 업데이트된 목록을 표시
	            location.reload();
	        }
	    };
	    xhr.send(params);
	}
	</script>
</body>
</html>