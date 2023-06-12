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
	<%
		request.setCharacterEncoding("UTF-8");
	    int id = Integer.parseInt(request.getParameter("id"));
		MemberService deletebookMark = new MemberService();
		deletebookMark.deleteBookmark(id);
	%>
</body>
</html>