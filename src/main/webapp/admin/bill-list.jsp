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
			<table class="table">
				<thead>
				<tr>
					<td width="125"><s:text name="bill.date"/></td>
					<td width="135"><s:text name="bill.name"/></td>
					<td width="125"><s:text name="bill.in"/></td>
					<td width="125"><s:text name="bill.out"/></td>
				</tr>
				</thead>
				<s:set name="total" value="0"/>
				<s:iterator value="#session['users']" var="user">
					<s:set var="include" value="false"/>
					<s:iterator value="#session['tree'].createAndBill(#user)">
						<s:if test="value.id.equals(#request['billUser'])">
							<s:set var="include" value="true"/>
						</s:if>
					</s:iterator>
					<s:if test="include">
						<tbody>
						<tr>
						<td colspan="4">
						<table class="table">
						<tbody>
						<s:iterator value="#session['tree'].createAndBill(#user)">
							<s:if test="billID == 1 || value.id.equals(#request['billUser'])">
							<s:set var="total" value="#total + billOUT"/>
							<tr>
								<td width="125">
									<s:date name="#session['tree'].createAndBill(#user).get(0).getValue().userCreateTime"
										format="yyyy-MM-dd HH:mm"/>
								</td>
								<td width="135">
									<s:property value="value.userName"/>(<s:property value="value.id"/>)
								</td>
								<td width="125"><span style="color: green"><s:property value="billIN"/></span></td>
								<td width="125"><span style="color: red"><s:property value="billOUT"/></span></td>
							</tr>				
						</s:if>
						</s:iterator>
						</tbody>
						</table>
						</td>
						</tr>
						</tbody>
					</s:if>
				</s:iterator>
				<tr>
					<td colspan="4">
						<table class="table">
							<tbody>
							<tr>
								<td width="125"></td>
								<td width="135"></td>
								<td width="125"><span style="color: green"></span></td>
								<td width="125"><span style="color: red; font-weight: bold"><s:property value="#total"/></span></td>
							</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</table>
		</div>
		</div>
		
</div>
</div>

</body>
</html>
