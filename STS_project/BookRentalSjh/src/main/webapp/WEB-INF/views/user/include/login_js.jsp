<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">

	function loginForm() {
		console.log('loginForm() CALLED!!');
		
		let form = document.login_form;
		
		if (form.u_m_id.value == '') {
			alert('INPUT USER ID.');
			form.u_m_id.focus();
			
		} else {
			form.submit();
			
		}
		
	}

</script>