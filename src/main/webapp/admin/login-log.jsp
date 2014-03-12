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
		<li class="active"><a href="#"><s:text name="list.li.log" /></a></li>
		<li><a href="<%=basePath%>admin/image-list.action"><s:text name="home.image" /></a></li>
		<li class="nav-header"><s:text name="list.head.user" /></li>
		<li><a href="<%=basePath%>admin/load.jsp"><s:text name="list.li.alart" /></a></li>
		<li><a href="<%=basePath%>admin/adduser.action"><s:text name="menu.add"/></a></li>
		<li><a href="<%=basePath%>admin/bill.jsp"><s:text name="menu.bill"/></a></li>
		<li><a href="<%=basePath%>login-success.jsp"><s:text name="menu.user"/></a></li>
		</ul>
	</div>
	</div>
	
	<div class="span10">
	<div class="hero-unit">
		<d:table name="logs" class="table table-condensed table-striped" pagesize="15" requestURI="/admin/view-log.action" export="true">
			<d:column property="id" title="#" />
			<d:column property="userName" title="Name" /> 
			<d:column property="loginTime" title="Time" format="{0, date, yyyy-MM-dd HH:mm:ss}" />
		</d:table>
	</div>
	</div>
	
</div>
</div>

</body>
</html>
