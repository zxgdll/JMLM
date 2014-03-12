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
		<li><a href="<%=basePath%>admin/view-log.action"><s:text name="list.li.log" /></a></li>
		<li><a href="<%=basePath%>admin/courses-manage.action"><s:text name="home.course"/></a></li>
		<li class="nav-header"><s:text name="list.head.user" /></li>
		<li class="active"><a href="<%=basePath%>admin/load.jsp"><s:text name="list.li.alart" /></a></li>
		<li><a href="<%=basePath%>admin/adduser.action"><s:text name="menu.add"/></a></li>
		<li><a href="<%=basePath%>admin/bill.jsp"><s:text name="menu.bill"/></a></li>
		<li><a href="<%=basePath%>login-success.jsp"><s:text name="menu.user"/></a></li>
		</ul>
		</div>
	</div>
    
	<div class="span10">
	<div class="hero-unit">
	<table class="table">
		<thead>
		<tr>
		<th>#</th>
		<th>员工</th>
		<th>ID</th>
		<th>介绍人数</th>
		<th>小组成员数（包括组长）</th>
		<th>消息</th>
		<tr>
		</thead>
		<tbody>
		<s:iterator value="#session['users']" var="user">
			<s:set name="temp" value="#session['tree'].find(#user)"/>
			<s:if test="#temp.isAlert()">
			<tr style="color: red; font-weight: bold">
			<td><s:property value="#temp.getValue().getId()"/></td>
			<td><s:property value="#temp.getValue().getUserName()"/></td>
			<td><s:property value="#temp.getValue().getUserLoginID()"/></td>
			<td><s:property value="#temp.refered()"/></td>
			<td><s:property value="#temp.size()"/></td>
			<td><s:property value="#temp.getMessage()"/></td>
			</tr>
			</s:if>
			<s:else>
			<tr>
			<td><s:property value="#temp.getValue().getId()"/></td>
			<td><s:property value="#temp.getValue().getUserName()"/></td>
			<td><s:property value="#temp.getValue().getUserLoginID()"/></td>
			<td><s:property value="#temp.refered()"/></td>
			<td><s:property value="#temp.size()"/></td>
			<td><s:property value="#temp.getMessage()"/></td>
			</tr>
			</s:else>
		</s:iterator>
		</tbody>
		</table>
	</div>
	</div>
	
</div>
</div>
</body>
</html>
