<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<title>ȸ�����</title>
<body>

    <jsp:include page="/Header.jsp"/>
    <p><a href='add.do'>�ű� ȸ��</a></p>
    <c:forEach var="member" items="${members}">
        ${member.no}<a href='update.do?no=${member.no}'>${member.name}</a>,
        ${member.email},${member.createDate}
        <a href='delete.do?no=${member.no}'>[����]</a><br>
    </c:forEach>
<jsp:include page="/Tail.jsp"/>
</body>
</html>