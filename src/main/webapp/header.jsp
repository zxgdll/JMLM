<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
<%
	StringBuilder str = new StringBuilder();
	str.append(request.getScheme()).append(":").append("/").append("/");
	str.append(request.getServerName()).append(":");
	str.append(request.getServerPort()).append(request.getContextPath());
	str.append("/");
	String basePath = str.toString();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title><s:text name="home.title" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="<s:text name="home.desc" />">
<link href="<%=basePath%>img/favicon.ico" type="image/x-icon" rel="icon" />
<link rel="stylesheet" href="<%=basePath%>bootstrap/css/bootstrap-blue.css">
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}
</style>
<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
</head>
<body>

<div class="navbar navbar-fixed-top">
<div class="navbar-inner">
<div class="container">
	<a class="brand" href="<%=basePath%>index.action" title="返回首页"><s:text name="home.title" /></a>
	<div class="nav-collapse pull-right">
		<ul class="nav">
			<li class="dropdown">
				<a href="#" class="dropdown-toggle" data-toggle="dropdown">
					<i class="icon-user icon-white"></i>&nbsp;<s:property value="#session['user']" />
					<b class="caret"></b>
				</a>
				<ul class="dropdown-menu">
					<li>
						<s:if test="#session['user'].userType == 0">
						<a href="<%=basePath%>admin/newslist.action">
							<i class="icon-home"></i>&nbsp;我的工作
						</a>
						</s:if>
						<s:else>
						<a href="<%=basePath%>login-success.jsp">
							<i class="icon-home"></i>&nbsp;我的工作
						</a>
						</s:else>
					</li>
					<li>
						<a href="<%=basePath%>secure/modifypassword.jsp">
							<i class="icon-lock"></i>&nbsp;<s:text name="menu.pass" />
						</a>
					</li>
				</ul>
			</li>
			<li>
				<a href="<%=basePath%>secure/courses-list.action">
					<i class="icon-leaf icon-white"></i>&nbsp;<s:text name="home.course" />
				</a>
			</li>
			<li>
				<a href="<%=basePath%>logoff.action">
					<i class="icon-ok icon-white"></i>&nbsp;<s:text name="menu.relog" />
				</a>
			</li>
		</ul>
	</div>
</div>
</div>
</div>

<script src="<%=basePath%>bootstrap/js/jquery.js"></script>
<script src="<%=basePath%>bootstrap/js/bootstrap-dropdown.js"></script>

<script src="<%=basePath%>ckeditor/ckeditor.js"></script>

<script>
function del_confirm() {
	return confirm("您确定要删除吗？") ? true : false;
}
</script>