<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
ul{
    padding: 0;
}

li { 
    list-style : none;
}

label {
    float: left;
    text-align: right;
    width:60px;
}
</style>
</head>
<body>

<jsp:include page="/Header.jsp"/>
<h1>������Ʈ ����</h1>
<form action='update.do' method='post'>
<ul>
    <li><label for="no">��ȣ</label>
            <input id="no" type='text' name='no' size="5" value='${project.no}' readonly=readonly>
    </li>
    <li>
        <label for="title">����</label>
        <input id="title" type='text' name='title' size="50" value="${project.title}">
    </li>
    <li>
        <label for="content">����</label>
        <textarea id="content" type="content" name="content" rows="5" cols="40">${project.content }</textarea>
    </li>
    <li>
        <label for="sdate">������</label>
        <input id="sdate" type='text' name='startDate' placeholder="��)2013-01-01" value="${project.startDate ">
    </li>
    <li>
        <label for ="edate">������</label>
        <input id="edate" type='text' name="endDate" placeholder="��)2013-01-01" value="${project.endDate}">
    </li>
    <li><label for="state">����</label>
        <select id="state" name="state">
            <option value="0"  ${project.state == 0 ? "selected" : ""}> �غ�</option>
            <option value="1" ${project.state == 1 ? "selected" : ""}>����</option>
            <option value="2" ${project.state == 2 ? "selected" : "" }>�Ϸ�</option>
            <option value="3" ${project.state == 3 ? "selected" : "" }>���</option>     
        </select>
    </li>    
    <li><label for="tags">�±�</label>
    <input id="tags" type='text' name='tags' placeholder="��) �±�1 �±�2 �±�3" size="50" value="${project.tags}">
    </li>
</ul>
<input type='submit' value='����'>
<input type='button' value='����' onclick='location.href="delete.do?no=${project.no}";'>
<input type='button' value='���' onclick='location.href="list.do";'>
</form>

<jsp:include page="/Tail.jsp"/>

</body>
</html>