<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="<c:url value='/resources/css/include/header.css' />" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.3.js" integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM=" crossorigin="anonymous"></script>
<script type="text/javascript">

	$(function() {
		console.log('HEADER DOCUMENT READY!!');
		
		/*
		console.log('---> ' + $(location).attr('href'));
		console.log('---> ' + $(location).attr('pathname'));
		*/
		
		let pathname = $(location).attr('pathname');
		let isIncludeUser = pathname.includes('/user');
		
		if (isIncludeUser) {
			$('#header_wrap .menu ul li a.user').css('text-decoration', 'green wavy underline');
			
		} else {
			$('#header_wrap .menu ul li a.admin').css('text-decoration', 'red wavy underline');
		}
		
		
	});

</script>

<header>
	
	<div id="header_wrap">
		
		<div class="menu">
			<ul>
				<li><a class="user" href="<c:url value='/' />">페이지1</a></li>
				<li><a class="admin" href="<c:url value='/admin' />">페이지2</a></li>
			</ul>
		</div>
		
		<div class="title">
			<h3>ACIN - 테스트 웹사이트</h3>
		</div>
		
	</div>

</header>