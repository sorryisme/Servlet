<%@page import="web04.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<title>ȸ�����</title>
<body>
<!-- 
    <jsp:include page="/Header.jsp"/>
     -->
    <p><a href='add'>�ű� ȸ��</a></p>
    <jsp:useBean id="members" scope="request" class="java.util.ArrayList" type="java.util.ArrayList<web04.vo.Member>"/>
    <% 
    for(Member member : members){
    %>
    <%=member.getNo() %>
    <a href='update?no=<%=member.getNo() %>'><%=member.getName()%></a>
    <%=member.getEmail() %>
    <%=member.getCreateDate() %>
    <a href='delete?no=<%=member.getNo() %>'>[����]</a><br>
    <%} %>
    <!-- 
    <jsp:include page="/Tail.jsp"/>
     -->

</body>
</html>