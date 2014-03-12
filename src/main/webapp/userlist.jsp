<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="header.jsp"%>

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
		<li><a href="<%=basePath%>admin/bill.jsp"><s:text name="menu.bill"/></a></li>
		<li class="active"><a href="#"><s:text name="menu.user"/></a></li>
		</ul>
		</div>
	</s:if>
	</div>

	<div class="span10">
	<div class="hero-unit">
	
	<%-- 
	<s:if test="#session['user'].userType == 0">
	<div class="container">
		<object type="application/x-java-applet" height="320" width="1080">
  			<param name="code" value="net.realqinwei.hzcrm.crm.applet.TestApplet.class" />
  			<param name="archive" value="test-applet.jar" />
  			<param name="java_arguments" value="-Djnlp.packEnabled=true"/>
  			<param name="userID" value="<s:property value="#request['userID']"/>"/>
			<param name="path" value="<%=basePath%>"/>
  		Applet failed to run.  No Java plug-in was found.
		</object>
	</div>
	</s:if>
	 --%>
	
	<table class="table">
			<s:if test="#session['user'].userType == 0">
			<thead>
			<tr align="center">
					<th><s:text name="table.name" /></th>
					<th><s:text name="table.IDcard" /></th>
					<th><s:text name="table.bank" /></th>
					<th><s:text name="table.phone" /></th>
					<th><s:text name="table.address" /></th>
					<th><s:text name="table.date" /></th>
					<th><s:text name="table.refer" /></th>
					<th/>
			</tr>
			</thead>
			<tbody>
			<s:iterator value="#request['leafs'].entrySet()">
				<tr>
					<td>
						<s:property value="value.userName"/>(<s:property value="value.id"/>)
					</td>
					<td><s:property value="value.userIDCard"/></td>
					<td><s:property value="value.userLoginID"/></td>
					<td><s:property value="value.userPhone"/></td>
					<td><s:property value="value.userAddress"/></td>
					<td><s:text name="format.date.user"><s:param value="value.userCreateTime" /></s:text></td>
					<td>
						<s:property value="value.userReferID == null ? '' : #session['userDAO'].findById(value.userReferID).userName"/>
					</td>
					<td>
						<a href="<%=basePath%>admin/edit-user.action?userID=<s:property value="value.id"/>">
						<s:text name="table.modify"/>
					</a>
					</td>
				</tr>
			</s:iterator>
			</tbody>
			</s:if>
			<s:else>
			<thead>
			<tr align="center">
				<th width="135"><s:text name="table.name"/></th>
				<th><s:text name="table.address"/></th>
				<th width="130"><s:text name="table.date"/></th>
				<th width="135"><s:text name="table.refer"/></th>
			</tr>
			</thead>
			<tbody>
			<s:iterator value="#request['leafs'].entrySet()">
				<tr>
					<td>
						<s:property value="value.userName"/>(<s:property value="value.id"/>)
					</td>
					<td><s:property value="value.userAddress"/></td>
					<td><s:text name="format.date.user"><s:param value="value.userCreateTime" /></s:text></td>
					<td>
						<s:property value="value.userReferID == null ? '' : #session['userDAO'].findById(value.userReferID).userName"/>
					</td>
				</tr>
			</s:iterator>
			</tbody>
			</s:else>
		</tbody>
	</table>
	</div>
	</div>
	
</div>
</div>

</body>
</html>
