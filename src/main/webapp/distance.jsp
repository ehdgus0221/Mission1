<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import ="api.dbSaveTest"%>
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
    
    // 문자열로 받은 값을 double로 변환하여 JSP 변수에 할당
    double latJsp = 0.0;
	double lngJsp = 0.0;
	
	if (lat != null && !lat.trim().isEmpty()) {
	    latJsp = Double.parseDouble(lat.trim());
	}
	
	if (lng != null && !lng.trim().isEmpty()) {
	    lngJsp = Double.parseDouble(lng.trim());
	}
   
    
    dbSaveTest a = new dbSaveTest();
    out.write(a.totalWifi() + "개의 WIFI 정보를 정상적으로 저장하였습니다.");

    Long wifiTotalNum = a.totalWifi();
    
    MemberService memberService = new MemberService();
	String deleteRecord = "wifi_info";
	memberService.deleteAllRecord(deleteRecord);
	
    Long startRow;
    Long lastRow;
    
    
    

    if (wifiTotalNum >= 1000) {
        startRow = 1L;
        lastRow = 1000L;
        memberService.register(startRow, lastRow, latJsp, lngJsp);
        wifiTotalNum -= 1000L;
    } else {
        startRow = 1L;
        lastRow = wifiTotalNum;
        memberService.register(startRow, lastRow, latJsp, lngJsp);
        wifiTotalNum = 0L;
    }

    while (wifiTotalNum != 0) {
        if (wifiTotalNum >= 1000) {
            startRow += 1000L;
            lastRow += 1000L;
            memberService.register(startRow, lastRow, latJsp, lngJsp);
            wifiTotalNum -= 1000L;
        } else {
            startRow += 1000L;
            lastRow += wifiTotalNum;
            memberService.register(startRow, lastRow, latJsp, lngJsp);
            wifiTotalNum = 0L;
        }
    }

%>
</body>
</html>