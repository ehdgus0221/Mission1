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
	 request.setCharacterEncoding("UTF-8");
	 String bookmarkName = request.getParameter("bookmarkName");
	 String wifiName = request.getParameter("wifiName");
	 String registerDate = request.getParameter("registerDate");

	
		MemberService insertdetailBookMark = new MemberService();
		insertdetailBookMark.insertDetailBookmark(bookmarkName, wifiName, registerDate);
	%>
</body>
</html>