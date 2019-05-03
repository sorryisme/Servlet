<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<title>회원목록</title>
<body>

    <jsp:include page="/Header.jsp"/>
    <p><a href='add.do'>신규 회원</a></p>
  <table>
         <tr>
	       <th>번호</th>
	       <th>제목</th>
	       <th>내용</th>
	       <th>시작시간</th>
	       <th>종료시간</th>
	       <th>상태</th>
	       <th>태그</th>
         </tr>
    <c:forEach var="project" items="${projectList}">
              <tr style="text-align:center;">
	            <td>${project.no}</td>
	            <td>${project.title}</td>
	            <td>${project.content}</td>
	            <td>${project.startDate}</td>
	            <td>${project.endDate}</td>
	            <td>${project.state} </td>
	            <td>${project.tags}</td>
            </tr>
    </c:forEach>
    </table>
<jsp:include page="/Tail.jsp"/>
</body>
</html>