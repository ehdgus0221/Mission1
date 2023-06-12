<%@page import="java.util.List" %>
<%@page import="db.Member" %>
<%@page import="db.MemberService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="main.jsp">홈</a>
	<a> | </a>
	<a href="history.jsp">위치 히스토리 목록</a>
	<a> | </a>
	<a href="loadWifi.jsp" onclick="executeDistance()">Open API 와이파이 정보 가져오기</a>
	<a> | </a>
	<a href="bookmarkList.jsp">북마크 보기</a>
	<a> | </a>
	<a href="bookmarkGroup.jsp">북마크 그룹 관리</a>
</body>
</html>