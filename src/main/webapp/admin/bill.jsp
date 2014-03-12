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
		<li><a href="<%=basePath%>admin/adduser.action"><s:text name="menu.add"/></a></li>
		<li class="active"><a href="#"><s:text name="menu.bill"/></a></li>
		<li><a href="<%=basePath%>login-success.jsp"><s:text name="menu.user"/></a></li>
		</ul>
		</div>
	</div>

	<div class="span10">
	<div class="hero-unit">
		<table class="table table-condensed">
		<thead>
			<tr>
				<th width="50">#</th>
				<th width="125"><s:text name="bill.name"/></th>
				<th width="125"><s:text name="bill.bank"/></th>
				<th width="100"><s:text name="bill.in"/></th>
				<th width="100"><s:text name="bill.out"/></th>
			</tr>
		</thead>
		<tbody>
		<s:set name="peopleId" value="0"/>
		<s:set name="totalIN" value="0"/>
		<s:set name="totalOUT" value="0"/>
		<s:iterator value="#session['users']" var="user">
			<s:set name="total" value="0"/>
			<s:set var="peopleId" value="#peopleId + 1"/>
					<s:iterator value="#session['tree'].createAndBill(#user)" status="rowstatus">
						<tr>
							<th width="50">
							<s:if test="#rowstatus.first">
								<s:property value="#peopleId"/>
							</s:if>
							</th>
							<td width="125">
								<a href="<%=basePath%>billlist.action?userID=<s:property value="value.id"/>">
									<s:property value="value.userName"/>(<s:property value="value.id"/>)
								</a>
							</td>
							<td width="125"><s:property value="value.userLoginID"/></td>
							<td width="100" style="color: green"><s:property value="billIN"/></td>
							<td width="100" style="color: red"><s:property value="billOUT"/></td>
							<s:set var="totalIN" value="#totalIN + billIN"/>
							<s:set var="totalOUT" value="#totalOUT + billOUT"/>
							<s:set var="total" value="#total + billOUT"/>
						</tr>
					</s:iterator>
					<tr>
						<td/>
						<td/>
						<td/>
						<td/>
						<td style="color: red; font-weight: bold">
							<s:property value="#total"/>
						</td>
					</tr>
		</s:iterator>
				<tr>
								<th width="50" />
								<td width="125"></td>
								<td width="125"></td>
								<td width="100"><span style="color: green; font-weight: bold"><s:property value="#totalIN"/></span></td>
								<td width="100"><span style="color: red; font-weight: bold"><s:property value="#totalOUT"/></span></td>
							</tr>
		</tbody>
		</table>
	</div>
	</div>
	
</div>
</div>

</body>
</html>
