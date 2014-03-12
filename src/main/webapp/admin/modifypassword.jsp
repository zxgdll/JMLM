<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="../header.jsp"%>

<div class="container">
<div class="hero-unit span6 offset2">
	<s:form action="modify-password">
	<table class="table">
		<s:password name="oldPassword" label="%{getText('password.old')}"/>
		<s:password name="newPassword" label="%{getText('password.new')}"/>
		<s:password name="newPasswordAgain" label="%{getText('password.new-again')}"/>
		<s:submit cssClass="btn" value="%{getText('password.submit')}"/>
	</table>
	</s:form>
</div>
</div>

</body>
</html>
