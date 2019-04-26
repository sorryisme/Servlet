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
<h1>회원정보</h1>
<form action='update' method='post'>
번호 <input type='text' name='no' value='<%=member.getNo() %>' readonly><br>         
이름 : * <input type='text' name='name' value='<%=member.getName() %>'><br>
이메일 : <input type='text' name='email' value='<%=member.getEmail()%>'><br>
<input type='submit' value='저장'>
<intput type='button' value='취소' onclick='location.href="list"'>
</form>

</body>
</html>