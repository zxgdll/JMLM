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
		<li class="active"><a href="#"><s:text name="home.course"/></a></li>
		<li><a href="<%=basePath%>admin/view-log.action"><s:text name="list.li.log" /></a></li>
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

		<table class="table table-striped">
		<thead>
		<tr>
		<th><s:text name="news.id"/></th>
		<th><s:text name="news.title"/></th>
		<th><s:text name="news.create"/></th>
		<th><s:text name="news.modify"/></th>
		<th />
		<th />
		</tr>
		</thead>
		<tbody>
		<s:bean name="com.nm_huazhi.struts2.comparator.NewsIDComparator" var="newsIDComparator"/>
		<s:sort comparator="newsIDComparator" source="courses">
		<s:iterator>
		<tr>
		<td><s:property value="id"/></td>
		<td><s:property value="title"/></td>
		<td><s:text name="format.date.news"><s:param value="createTime" /></s:text></td>
		<td><s:text name="format.date.news"><s:param value="modifyTime" /></s:text></td>
		<td>
			<a class="btn btn-info" href="<%=basePath%>admin/courses-edit.action?courseId=<s:property value="id"/>">
			<i class="icon-edit icon-white"></i>&nbsp;<s:text name="news.button.edit" />
			</a>
		</td>
		<td>
			<a class="btn btn-danger" href="<%=basePath%>admin/courses-delete.action?courseId=<s:property value="id"/>" 
					onclick="javascript: return del_confirm()">
			<i class="icon-trash icon-white"></i>&nbsp;<s:text name="news.button.delete" />
			</a>
		</td>
		</tr>
		</s:iterator>
		</s:sort>
		<tr>
		<td colspan="6">
			<a class="btn" href="<%=basePath%>admin/course-create.jsp">
			<s:text name="news.button.create" />
			</a>
		</td>
		</tr>
		</tbody>
		</table>
		
	</div>
	</div>

</div>
</div>

</body>
</html>
