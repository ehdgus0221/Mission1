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
    MemberService twentyMemberList = new MemberService();
	String tableName = "wifi_twenty";
	twentyMemberList.createTwentyList();
%>
<%
    List<Member> MemberList = twentyMemberList.twentyWifiList();
%>

<%
    for (int i = 0; i < MemberList.size(); i++) {
    	Member member = MemberList.get(i);
        out.write("<tr>");
        out.write("<td>" + member.getDistance() + "</td>");
        out.write("<td>" + member.getX_SWIFI_MGR_NO() + "</td>");
        out.write("<td>" + member.getX_SWIFI_WRDOFC() + "</td>");
        out.write("<td > <a href=detail.jsp?index=" + i + ">" + member.getX_SWIFI_MAIN_NM() + "</a> </td>");
        out.write("<td>" + member.getX_SWIFI_ADRES1() + "</td>");
        out.write("<td>" + member.getX_SWIFI_ADRES2() + "</td>");
        out.write("<td>" + member.getX_SWIFI_INSTL_FLOOR() + "</td>");
        out.write("<td>" + member.getX_SWIFI_INSTL_TY() + "</td>");
        out.write("<td>" + member.getX_SWIFI_INSTL_MBY() + "</td>");
        out.write("<td>" + member.getX_SWIFI_SVC_SE() + "</td>");
        out.write("<td>" + member.getX_SWIFI_CMCWR() + "</td>");
        out.write("<td>" + member.getX_SWIFI_CNSTC_YEAR() + "</td>");
        out.write("<td>" + member.getX_SWIFI_INOUT_DOOR() + " </td>");
        out.write("<td>" + member.getX_SWIFI_REMARS3() + "</td>");
        out.write("<td>" + member.getLAT() + "</td>");
        out.write("<td>" + member.getLNT() + "</td>");
        out.write("<td>" + member.getWORK_DTTM() + "</td>");
        out.write("</tr>");
    }
%>
</body>
</html>