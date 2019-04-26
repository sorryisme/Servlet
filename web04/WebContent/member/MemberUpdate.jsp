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
<h1>회원정보</h1>
<form action='update' method='post'>
번호 <input type='text' name='no' value='${member.no}' readonly><br>         
이름 : * <input type='text' name='name' value='${member.name }'><br>
이메일 : <input type='text' name='email' value='${member.email }'><br>
가입일 : ${member.createDate}<br>
<input type='submit' value='저장'>
<intput type='button' value='취소' onclick='location.href="list"'>
</form>

</body>
</html>