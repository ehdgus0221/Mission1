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
</head>
<body>
	<%
		int idx = Integer.parseInt(request.getParameter("index"));
		MemberService historyDelete = new MemberService();
		historyDelete.deleteHistoryList(idx);
	%>
</body>
</html>