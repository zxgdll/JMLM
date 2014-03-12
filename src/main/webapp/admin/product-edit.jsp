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
		<li class="active"><a href="#"><s:text name="home.product" /></a></li>
		<li><a href="<%=basePath%>admin/view-log.action"><s:text name="list.li.log" /></a></li>
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
		<s:form action="save-product" method="post" enctype="multipart/form-data">
		<s:hidden name="product.id" />
		<s:hidden name="product.createTime" />
		<s:hidden name="product.type" />
		<s:hidden name="product.image" />
		<table class="table">
		<tbody>
		<s:textfield name="product.title" label="%{getText('news.title')}" />
		<tr>
			<td colspan="2">
			<s:textarea id="ckeditor" cssClass="ckeditor" name="product.content"/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<s:file name="file" />
			<s:submit />
		</td>
		</tr>
		</tbody>
		</table>
		</s:form>
	</div>
	</div>

</div>
</div>

</body>
</html>
