<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>
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
<body>
	<div>
	<h1>북마크 목록</h1>
	</div>
	<div>
		<!-- 메인페이지, 내용 상세보기, 북마크 그룹에서 공통으로 사용하는 부분 common.jsp  -->
		<%@ include file="common.jsp" %>
	</div>
	<div>
		<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>북마크 이름</th>
				<th>와이파이명</th>
				<th>등록일자</th>
				<th>비고</th>
			</tr>

		</thead>
		<tbody>
			<%
			    MemberService bookmarkMemberList = new MemberService();
				List<Member> MemberList = bookmarkMemberList.bookmarkList();
			%>
			
			<%
			    for (int i = 0; i < MemberList.size(); i++) {
			    	Member member = MemberList.get(i);
			        out.write("<tr>");
			        out.write("<td>" + member.getId() + "</td>");
			        out.write("<td>" + member.getBookmarkName() + "</td>");
			        out.write("<td>" + member.getWifiName() + "</td>");
			        out.write("<td>" + member.getRegisterDate() + "</td>");
			        out.write("<td><button onclick=\"location.href='bookmarkDelete.jsp?index=" + i + "'\">삭제</button></td>");
			        out.write("</tr>");
			    }
			%>
		</tbody>
	</table>
	</div>
</body>
</html>