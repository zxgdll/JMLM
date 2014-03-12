<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="../header.jsp"%>
    
<div class="container-fluid">
<div class="row-fluid">
	
	<div class="span2">
		<div class="well sidebar-nav">
		<ul class="nav nav-list">
		<li class="nav-header"><s:text name="home.admin" /></li>
		<li><a href="<%=basePath%>admin/newslist.action"><s:text name="home.menu.news" /></a></li>
		<li><a href="<%=basePath%>admin/editabout.action"><s:text name="home.about" /></a></li>
		<li><a href="<%=basePath%>admin/editcontact.action"><s:text name="home.contact" /></a></li>
		<li><a href="<%=basePath%>admin/listproduct.action"><s:text name="home.product" /></a></li>
		<li><a href="<%=basePath%>admin/courses-manage.action"><s:text name="home.course"/></a></li>
		<li><a href="<%=basePath%>admin/view-log.action"><s:text name="list.li.log" /></a></li>
		<li class="nav-header"><s:text name="list.head.user" /></li>
		<li><a href="<%=basePath%>admin/load.jsp"><s:text name="list.li.alart" /></a></li>
		<li class="active"><a href="#"><s:text name="menu.add"/></a></li>
		<li><a href="<%=basePath%>admin/addnode.action"><s:text name="addnode.submit"/></a></li>
		<li><a href="<%=basePath%>admin/bill.jsp"><s:text name="menu.bill"/></a></li>
		<li><a href="<%=basePath%>login-success.jsp"><s:text name="menu.user"/></a></li>
		</ul>
		</div>
	</div>
	
	<div class="span10">
	<div class="hero-unit">
		<s:form action="save-user">
		<s:token></s:token>
		<table class="table">
			<s:textfield name="user.userName" label="%{getText('table.name')}" />
			<!-- 
			<s:select name="user.userReferID" label="%{getText('table.refer')}" 
					list="list" listKey="id" listValue="userName + '(' + id + ')'" value="%{list.{userID}}"
					headerKey="0" headerValue="%{getText('adduser.select.headerValue')}" />	
			 -->	
			<s:textfield name="user.userIDCard" label="%{getText('table.IDcard')}" />
			<s:textfield name="user.userLoginID" label="%{getText('table.bank')}" />
			<s:textfield name="user.userPhone" label="%{getText('table.phone')}" />
			<s:textfield name="user.userAddress" label="%{getText('table.address')}" />
			<s:submit value="%{getText('adduser.submit')}" />
		</table>
		</s:form>
	</div>
	</div>
	
</div>
</div>

</body>
</html>
