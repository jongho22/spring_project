<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3>This page is SIGN_UP</h3>
	
	<form action="/hello/signUpConfirm">
		ID : <input type="text" name="m_id"><br/>
		PW : <input type="password" name="m_pw"><br/>
		MAIL : <input type="text" name="m_mail"><br/>
		PHONE : <input type="text" name="m_phone"><br/>
		<input type="submit" value="SIGN UP">
		<input type="reset" value="CANCEL">
	</form>

</body>
</html>