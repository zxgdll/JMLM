<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="../header.jsp"%>

<div class="container-fluid">
<div class="row-fluid">
	
	<div class="span2">
	<s:if test="#session['user'].userType == 0">
		<div class="well sidebar-nav">
		<ul class="nav nav-list">
		<li class="nav-header"><s:text name="home.admin" /></li>
		<li><a href="<%=basePath%>admin/newslist.action"><s:text name="home.menu.news" /></a></li>
		<li><a href="<%=basePath%>admin/editabout.action"><s:text name="home.about" /></a></li>
		<li><a href="<%=basePath%>admin/editcontact.action"><s:text name="home.contact" /></a></li>
		<li><a href="<%=basePath%>admin/listproduct.action"><s:text name="home.product" /></a></li>
		<li><a href="<%=basePath%>admin/view-log.action"><s:text name="list.li.log" /></a></li>
		<li class="nav-header"><s:text name="list.head.user" /></li>
		<li><a href="<%=basePath%>admin/load.jsp"><s:text name="list.li.alart" /></a></li>
		<li><a href="<%=basePath%>admin/adduser.action"><s:text name="menu.add"/></a></li>
        <li><a href="<%=basePath%>admin/addnode.action"><s:text name="addnode.submit"/></a></li>
		<li><a href="<%=basePath%>admin/bill.jsp"><s:text name="menu.bill"/></a></li>
		<li class="active"><a href="#"><s:text name="menu.user"/></a></li>
		</ul>
		</div>
	</s:if>
	</div>

	<div class="span10">
	<div class="hero-unit">
		<s:form action="edit-user-save">
			<table class="table">
				<s:hidden name="user.id"/>
				<s:hidden name="user.userPassword"/>
				<s:hidden name="user.userType"/>
				<s:hidden name="user.userReferID"/>
				<s:hidden name="user.userCreateTime"/>
				<s:textfield langth="32" name="user.userName" label="%{getText('table.name')}"/>
				<s:textfield langth="32" name="user.userIDCard" label="%{getText('table.IDcard')}"/>
				<s:textfield langth="32" name="user.userLoginID" label="%{getText('table.bank')}"/>
				<s:textfield langth="32" name="user.userPhone" label="%{getText('table.phone')}"/>
				<s:textfield langth="32" name="user.userAddress" label="%{getText('table.address')}"/>
				<s:submit value="%{getText('table.modify')}"/>
			</table>
		</s:form>
	</div>
	</div>
	
</div>
</div>
	
</body>
</html>
