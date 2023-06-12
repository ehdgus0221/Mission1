<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="db.Member" %>
<%@page import="db.MemberService" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	    String lat = request.getParameter("lat");
	    String lng = request.getParameter("lng");
	    MemberService memberService = new MemberService();
	    memberService.insertLocationHistory(Double.parseDouble(lat), Double.parseDouble(lng));
	%>
</body>
</html>