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
	th {
		height : 40px;
		border: solid 1px #000;
		text-align : center;
		background-color: green;
		color: white;
	}
	td {
		text-align : center;
		border: solid 1px #000;
	}
	p {
		text-align : center;
	}


</style>
</head>

<body>

	<%
	// idx 수정 필요
	int idx = Integer.parseInt(request.getParameter("index"));
	MemberService bookmarkMemberList = new MemberService();
	List<Member> MemberList = bookmarkMemberList.bookmarkList();
	Member member = MemberList.get(idx);	 // idx 수정 필요
	%>
	
	
	<div>
	<h1>북마크 목록</h1>
	</div>
	<div>
		<!-- 메인페이지, 내용 상세보기, 북마크 그룹에서 공통으로 사용하는 부분 common.jsp  -->
		<%@ include file="common.jsp" %>
	</div>
<div style="margin-top: 10px; margin-bottom: 10px;">북마크를 삭제하시겠습니까?</div>
	<div>
	<table>
	    <thead>
	        <tr>
	            <th>북마크 이름</th>
	            <td><%=member.getBookmarkName() %></td>
	        </tr>
	        <tr>
	            <th>와이파이명</th>
	            <td><%=member.getWifiName() %></td>
	        </tr>
	        <tr>
	            <th>등록일자</th>
	            <td><%=member.getRegisterDate() %></td>
	        </tr>
			
	    </thead>
		</table>
	</div>
<div style="text-align: center; margin-top: 10px;">
		<span>
			<a href="bookmarkList.jsp">돌아가기</a>
		    <a> | </a>
		    <% out.write("<button onclick=\"deleteBookmark(" + member.getId() + ")\">삭제</button>"); %>
		</span>
	</div>


	<script>
	    function deleteBookmark() {
	        <% 
	        request.setCharacterEncoding("UTF-8");
	        int id = member.getId();
	        // index 값을 사용하여 필요한 작업을 수행합니다.
	    	%>
	        var id = <%= id %>; 
	        
	        // AJAX를 사용하여 서버에 요청 보내기
	        var xhttp = new XMLHttpRequest();
 	        xhttp.onreadystatechange = function() {
	            if (this.readyState == 4 && this.status == 200) {
	                console.log(this.responseText);
	                alert("북마크가 제거되었습니다.");
	                location.href = "bookmarkList.jsp";
	            }
	        }; 
	        xhttp.open("POST", "deleteBookmark.jsp", true);
	        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	        xhttp.send("id=" + id);
	    }
	</script>
</body>
</html>