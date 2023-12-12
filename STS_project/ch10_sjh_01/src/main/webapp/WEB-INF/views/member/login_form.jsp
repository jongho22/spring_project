<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h3>MEMBER LOGIN FORM</h3>
	
	<form action="<c:url value='/member/loginConfirm'/>" method="post">
		<input type="text" name="m_id"><br>
		<input type="password" name="m_pw"><br>
		<input type="submit" name="login">
		<input type="reset" name="reset">
	</form>
	
</body>
</html>