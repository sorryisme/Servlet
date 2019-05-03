<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    
<div style="background-color:#00008b;color:#ffffff;height:20px;padding:5px;">SPMS(Simple Project ManageMent System)
<span style="float:right;">
    <a style="<%=request.getContextPath()%>/project/list.do">프로젝트</a>
    <a style="color:white:white;" href="<%=request.getContextPath()%>/member/list.do">회원</a>
    <c:if test="${empty sessionScope.member or empty sessionScope.member.email}">
        <a style="color:white;" href="<%=request.getContextPath()%>/auth/login.do">로그인</a>
    </c:if>
    
    <c:if test="${!empty sessionScope.member and !empty sessionScope.member.email}">
        (<a style="color:white;" href="<%=request.getContextPath()%>/auth/logout.do" >로그아웃</a>)
    </c:if>
    
    </a>
</span>
</div>