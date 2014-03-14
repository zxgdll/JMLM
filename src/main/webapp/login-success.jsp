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
		<li><a href="<%=basePath%>admin/courses-manage.action"><s:text name="home.course"/></a></li>
		<li><a href="<%=basePath%>admin/view-log.action"><s:text name="list.li.log" /></a></li>
		<li class="nav-header"><s:text name="list.head.user" /></li>
		<li><a href="<%=basePath%>admin/load.jsp"><s:text name="list.li.alart" /></a></li>
		<li><a href="<%=basePath%>admin/adduser.action"><s:text name="menu.add"/></a></li>
		<li><a href="<%=basePath%>admin/addnode.action"><s:text name="addnode.submit"/></a></li>
		<li><a href="<%=basePath%>admin/bill.jsp"><s:text name="menu.bill"/></a></li>
		<li class="active"><a href="#"><s:text name="menu.user"/></a></li>
		<li><a href="<%=basePath%>admin/tree-show"><s:text name="home.tree"/></a></li>
		</ul>
		</div>
	</s:if>
	</div>

	<div class="span10">
	<div class="hero-unit">

	<table class="table table-striped">
		<s:if test="#session['user'].userType == 0">
			<thead>
				<tr>
					<th><s:text name="table.name" /></th>
					<th><s:text name="table.IDcard" /></th>
					<th><s:text name="table.bank" /></th>
					<th><s:text name="table.phone" /></th>
					<th><s:text name="table.address" /></th>
					<th><s:text name="table.date" /></th>
					<th/>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#session['allUsers']">
					<tr>
						<td>
							<a href="<%=basePath%>userlist.action?userID=<s:property value="id"/>">
                                <s:property value="id" />&nbsp;<s:property value="userName" />
                            </a>
						</td>
						<td><s:property value="userIDCard" /></td>
						<td><s:property value="userLoginID" /></td>
						<td><s:property value="userPhone" /></td>
						<td><s:property value="userAddress" /></td>
						<td><s:text name="format.date.user"><s:param value="userCreateTime" /></s:text></td>
						<td>
                            <a href="<%=basePath%>admin/edit-user.action?userID=<s:property value="id"/>">
								<s:text name="table.modify" />
                            </a>
                        </td>
					</tr>
				</s:iterator>
			</tbody>
		</s:if>
		<s:else>
			<thead>
				<tr align="center">
					<th width="135"><s:text name="table.name" /></th>
					<th><s:text name="table.address" /></th>
					<th width="130"><s:text name="table.date" /></th>
					<th width="135"><s:text name="table.refer" /></th>
				</tr>
			</thead>
			<tbody>
				<s:iterator
					value="#session['tree'].find(#session['user']).getBranchs().entrySet()">
					<tr>
						<td><a
							href="<%=basePath%>userlist.action?userID=<s:property value="#value.id"/>">
								<s:property value="value.userName" />(<s:property
									value="value.id" />) </a></td>
						<td><s:property value="value.userAddress" />
						</td>
						<td><s:text name="format.date.user"><s:param value="value.userCreateTime" /></s:text></td>
						<td><s:property
								value="value.userReferID == null ? '' : #session['userDAO'].findById(value.userReferID).userName" />
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</s:else>
	</table>
	</div>
	</div>
	
</div>
</div>

</body>
</html>
