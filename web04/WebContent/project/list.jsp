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
  <table>
         <tr>
	       <th>��ȣ</th>
	       <th>����</th>
	       <th>����</th>
	       <th>���۽ð�</th>
	       <th>����ð�</th>
	       <th>����</th>
	       <th>�±�</th>
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