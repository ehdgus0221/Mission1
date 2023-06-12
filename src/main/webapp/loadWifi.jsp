<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import ="api.dbSaveTest"%>
<%@page import="db.Member" %>
<%@page import="db.MemberService" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
<style>
	div {
		display: flex;
		 justify-content: center; 
		 align-items: center; 
	}
</style>
</head>

<body>
	<div>		
		<h1>
		<% 
			dbSaveTest a = new dbSaveTest();
		    out.write(a.totalWifi() + "개의 WIFI 정보를 정상적으로 저장하였습니다.");
		%>
		</h1>
	</div>	
	
	<div>
		<p>
		<a href = "main.jsp">홈 으로 가기</a>
		</p>
	</div>
		
</body>
</html>