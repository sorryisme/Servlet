<%@page import="web04.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<jsp:useBean id="member" scope="session" class="web04.vo.Member"/>
    
<div style="background-color:#00008b;color:#ffffff;height:20px;padding:5px;">SPMS(Simple Project ManageMent System)
<%if(member.getEmail() != null){  %>
<span style="float:right;">
    <%=member.getName() %>
    <a style="color:white;" href="<%=request.getContextPath() %>/auth/logout.do">·Î±×¾Æ¿ô
    </a>
</span>
<% } %>
</div>