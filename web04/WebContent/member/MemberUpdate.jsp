<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="member" scope="request" class="web04.vo.Member"></jsp:useBean>
<h1>ȸ������</h1>
<form action='update' method='post'>
��ȣ <input type='text' name='no' value='<%=member.getNo() %>' readonly><br>         
�̸� : * <input type='text' name='name' value='<%=member.getName() %>'><br>
�̸��� : <input type='text' name='email' value='<%=member.getEmail()%>'><br>
<input type='submit' value='����'>
<intput type='button' value='���' onclick='location.href="list"'>
</form>

</body>
</html>