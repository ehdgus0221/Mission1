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
	th {
		background-color: green;
		color: white;
		border: solid 1px #000;

	}
	td {
		border: solid 1px #000;

	}

</style>
</head>
<body>
	<div>
	<h1>북마크 그룹 수정</h1>
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
		<span>
			<a href="bookmarkGroup.jsp">돌아가기</a>
			<a>|</a>
			<button onclick="updateBookmark()">수정</button>
		</span>
	</div>

	<script>
	    function updateBookmark() {
	        <% 
	        request.setCharacterEncoding("UTF-8");
	        int index = Integer.parseInt(request.getParameter("index"));
	        // index 값을 사용하여 필요한 작업을 수행합니다.
	    	%>
	        var idx = <%= index %>; 
	        var bookmarkName = encodeURIComponent(document.getElementById("bookmarkName").value);
	        var order = encodeURIComponent(document.getElementById("order").value);
	        
	        // AJAX를 사용하여 서버에 요청 보내기
	        var xhttp = new XMLHttpRequest();
 	        xhttp.onreadystatechange = function() {
	            if (this.readyState == 4 && this.status == 200) {
	                console.log(this.responseText);
	                alert("그룹이 수정되었습니다.");
	            }
	        }; 
	        xhttp.open("POST", "editBookmark.jsp", true);
	        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	        xhttp.send("bookmarkName=" + bookmarkName + "&order=" + order + "&index=" + idx);
	    }
	</script>

</body>
</html>