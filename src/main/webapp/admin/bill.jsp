<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ page import="net.earthcoder.jmlm.domain.*" %>
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
				<th width="125"><s:text name="bill.name"/></th>
				<th width="100">A区业绩</th>
                <th width="100">B区业绩></th>
                <th width="100">运营奖</th>
                <th width="100">辅导奖</th>
			</tr>
		</thead>
		<tbody>
<%
    BinaryTree tree = (BinaryTree) session.getAttribute("tree");
    for (BinaryNode bnode: tree.getNodes()) {
       %>
<tr>
    <td><%=bnode.getContent().name() %></td>
    <td><%=bnode.getLeftCurrent() %></td>
    <td><%=bnode.getRightCurrent() %></td>
    <td><%=bnode.getOperatingExpenses() %></td>
    <td><%=bnode.getCounselingFee() %></td>
</tr>
<%
    }
%>
		</tbody>
		</table>
	</div>
	</div>
	
</div>
</div>

</body>
</html>
