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
	button {
		 display: block;
 		 margin: 0 auto;
	}
</style>
</head>
<body>
	<div>
	<h1>북마크 그룹 추가</h1>
	</div>
	<div>
		<!-- 메인페이지, 내용 상세보기, 북마크 그룹에서 공통으로 사용하는 부분 common.jsp  -->
		<%@ include file="common.jsp" %>
	</div>
	<div>
	    <!-- insertBookmark.jsp로 폼 데이터를 전송하는 POST 요청 -->
	    <form id="bookmarkForm" action="insertBookmark.jsp" method="POST" accept-charset="UTF-8"> 
	        <table>
	            <tbody>    
	                <tr>
	                    <th>북마크 이름</th>
	                    <td><input type="text" id="bookmarkName" name="bookmarkName"></td>
	                </tr>
	                <tr>
	                    <th>순서</th>
	                    <td><input type="number" id="order" name="order"></td>
	                </tr>
	            </tbody>
	        </table>
	    </form>
	</div>
	<div>
		<button onclick="insertBookmark()">추가</button>
	</div>

	<script>
	    function insertBookmark() {
	        var bookmarkName = encodeURIComponent(document.getElementById("bookmarkName").value);
	        var order = encodeURIComponent(document.getElementById("order").value);
	        
	        // AJAX를 사용하여 서버에 요청 보내기
	        var xhttp = new XMLHttpRequest();
	        xhttp.onreadystatechange = function() {
	            if (this.readyState == 4 && this.status == 200) {
	            	alert("그룹이 추가되었습니다.");
	                // 서버로부터의 응답을 처리
	                console.log(this.responseText);
	                location.reload();
	                // 추가로 실행할 코드 작성
	            }
	        };
	        xhttp.open("POST", "insertBookmark.jsp", true);
	        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	        xhttp.send("bookmarkName=" + bookmarkName + "&order=" + order);
	    }
	</script>
</body>
</html>