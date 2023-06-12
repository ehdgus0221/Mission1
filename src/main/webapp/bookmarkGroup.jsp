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
		border: solid 1px #000;
		text-align : center;
		background-color: green;
		color: white;
	}
	td {
	text-align : center;
	border: solid 1px #000;
	
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
	<h1>북마크 그룹</h1>
	<%@ include file="common.jsp" %>
	<div>
		<button onclick="location.href='bookmarkGroupAdd.jsp'">북마크 그룹 이름 추가</button>
	</div>
	<div>
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>북마크 이름</th>
					<th>순서</th>
					<th>등록일자</th>
					<th>수정일자</th>
					<th colspan="2">비고</th>
				</tr>
			</thead>
			<tbody>	
			<%
			    MemberService bookmarkGroupMemberList = new MemberService();
				List<Member> MemberList = bookmarkGroupMemberList.bookmarkGroupList();
			%>
			
			<%
			    for (int i = 0; i < MemberList.size(); i++) {
			    	Member member = MemberList.get(i);
			        out.write("<tr>");
			        out.write("<td>" + member.getId() + "</td>");
			        out.write("<td>" + member.getBookmarkName() + "</td>");
			        out.write("<td>" + member.getOrderValue() + "</td>");
			        out.write("<td>" + member.getRegisterDate() + "</td>");
			        out.write("<td>" + member.getEditDate() + "</td>");
			        out.write("<td><a href='bookmarkGroupEdit.jsp?index=" + member.getId() + "'>수정</a></td>");
			        out.write("<td><a href='javascript:void(0);' onclick=\"deleteBookmark(" + member.getId() + ")\">삭제</a></td>");
			        out.write("</tr>");
			    }
			%>
			</tbody>
		</table>
	</div>
	<script>
	function deleteBookmark(index) {
	    var xhr = new XMLHttpRequest();
	    var url = "bookmarkGroupDelete.jsp"; // 삭제 처리를 수행할 java 파일 경로
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