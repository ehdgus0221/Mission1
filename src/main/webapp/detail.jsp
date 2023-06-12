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
	tr, td {
		border: solid 1px #000;
		text-align : center;
		height: 40px;
		
	}
	p {
		text-align : center;
	}
	th {
		height: 40px;
		border: solid 1px #000;
		text-align : center;
		background-color: green;
		color: white;
	}


</style>
</head>
<body>
	<%
	    MemberService twentyMemberList = new MemberService();
	    List<Member> MemberList = twentyMemberList.twentyWifiList();
	%>
	<%
	    // URL 매개변수로 전달된 인덱스 값을 받아옴
	    int index = Integer.parseInt(request.getParameter("index"));
	    // 전체 MemberList에서 해당 인덱스의 Member 객체를 가져옴
	    Member member = MemberList.get(index);
	%>
	<div>
	<h1>와이파이 정보 구하기</h1>
	</div>
	<div>
	<%@ include file="common.jsp" %>
	</div>
	<div>
	<select name="bookmarkGroup" id = "select">
	  <option value="" disabled selected>북마크 이름 선택</option>
	
	<%
		MemberService bookmarkGroup = new MemberService();
		List<Member> bookmarkGroupList = bookmarkGroup.bookmarkGroupList();
	    for (int i = 0; i < bookmarkGroupList.size(); i++) {
	      Member bookmarkGroupmember = bookmarkGroupList.get(i);
	      out.write("<option value=\"" + bookmarkGroupmember.getBookmarkName() + "\">" + bookmarkGroupmember.getBookmarkName() + "</option>");
	    }
  	%>
	</select>
	<button onclick="addBookmark()">북마크 추가하기</button>
	</div>	

	<div>
 	<table>
	    <tbody>

	        <tr>
	            <th>거리(Km)</th>
	            <td><%=member.getDistance() %></td>
	        </tr>
	        <tr>
	            <th>관리번호</th>
	            <td><%=member.getX_SWIFI_MGR_NO() %></td>
	        </tr>
	        <tr>
	            <th>자치구</th>
	            <td><%=member.getX_SWIFI_WRDOFC() %></td>
	        </tr>
	        <tr>
	            <th>와이파이명</th>
	            <td><a href="detail.jsp?index=<%= index %>"><%=member.getX_SWIFI_MAIN_NM() %></a></td>
	        </tr>
	        <tr>
	        	<th>도로명주소</th>
	            <td><%=member.getX_SWIFI_ADRES1() %></td>
	        </tr>
	        <tr>
	        	<th>상세주소</th>
	            <td><%=member.getX_SWIFI_ADRES2() %></td>
	        </tr>
	         <tr>
	         	<th>설치위치(층)</th>  
	            <td><%=member.getX_SWIFI_INSTL_FLOOR() %></td>
	        </tr>
	        <tr>
	        	<th>설치유형</th> 
	            <td><%=member.getX_SWIFI_INSTL_TY() %></td>
	        </tr>
	        <tr>
	       		<th>설치기관</th>         
	            <td><%=member.getX_SWIFI_INSTL_MBY() %></td>
	        </tr>
	        <tr>
	        	<th>서비스구분</th>            
	            <td><%=member.getX_SWIFI_SVC_SE() %></td>
	        </tr>
	        <tr>
	        	<th>망종류</th>
	            <td><%=member.getX_SWIFI_CMCWR() %></td>
	        </tr>
	        <tr>
	            <th>설치년도</th>
	            <td><%=member.getX_SWIFI_CNSTC_YEAR() %></td>
	        </tr>
	        <tr>
	            <th>실내외구분</th>
	            <td><%=member.getX_SWIFI_INOUT_DOOR() %></td>
	        </tr>
	        <tr>
	        	<th>WIFI접속환경</th>
	            <td><%=member.getX_SWIFI_REMARS3() %></td>
	        </tr>
	        <tr>
	        	<th>X좌표</th>
	            <td><%=member.getLAT() %></td>
	        </tr>
	        <tr>
	            <th>Y좌표</th>
	            <td><%=member.getLNT() %></td>
	        </tr>
	        <tr>
	            <th>작업일자</th>
	            <td><%=member.getWORK_DTTM() %></td>
	        </tr>
	    </tbody>
	</table>
	</div>
	
	<script>
	  	var wifiName = "<%=member.getX_SWIFI_MAIN_NM()%>";
	  	var registerDate = "<%=member.getWORK_DTTM()%>";
	    function addBookmark() {

	    	var selectElement = document.getElementById("select");
	        var bookmarkName = selectElement.value;

	        // AJAX를 사용하여 서버에 요청 보내기
	        var xhttp = new XMLHttpRequest();
	        xhttp.onreadystatechange = function() {
	            if (this.readyState == 4 && this.status == 200) {
	            	alert("북마크 추가되었습니다.");
	                // 서버로부터의 응답을 처리
	                console.log(this.responseText);
	                location.reload();
	                // 추가로 실행할 코드 작성
	            }
	        };
	        xhttp.open("POST", "insertDetailBookmark.jsp", true);
	        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	        xhttp.send("bookmarkName=" + bookmarkName + "&wifiName=" + wifiName + "&registerDate=" + registerDate);
	    }
	</script>
</body>
</html>