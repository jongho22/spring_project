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

	<h3>MEMBER HOME</h3>
	
	<c:choose>
		<c:when test="${cookie.loginMember.value eq null}">
			<a href="<c:url value='/member/loginForm'/>">로그인</a>
		</c:when>
		<c:otherwise>
			<a href="<c:url value='/member/logoutForm'/>">로그아웃</a>
		</c:otherwise>
	</c:choose>

</body>
</html>