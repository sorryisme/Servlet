<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1>ȸ������</h1>
<form action='update' method='post'>
��ȣ <input type='text' name='no' value='${member.no}' readonly><br>         
�̸� : * <input type='text' name='name' value='${member.name }'><br>
�̸��� : <input type='text' name='email' value='${member.email }'><br>
������ : ${member.createDate}<br>
<input type='submit' value='����'>
<intput type='button' value='���' onclick='location.href="list"'>
</form>

</body>
</html>