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
<title>회원목록</title>
<body>
<!-- 
    <jsp:include page="/Header.jsp"/>
     -->
    <p><a href='add'>신규 회원</a></p>
    
    <% 
    ArrayList<Member> members = (ArrayList<Member>)request.getAttribute("members");
    for(Member member : members){
    %>
    <%=member.getNo() %>
    <a href='update?no = <%= member.getNo() %>'><%=member.getName()%></a>
    <%=member.getEmail() %>
    <%=member.getCreateDate() %>
    <a href='delete?no=<%=member.getNo() %>'>[삭제]</a><br>
    <%} %>
    <!-- 
    <jsp:include page="/Tail.jsp"/>
     -->

</body>
</html>