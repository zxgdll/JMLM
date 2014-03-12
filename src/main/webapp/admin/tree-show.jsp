<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ page import="net.realqinwei.hzcrm.crm.domain.TreeComponent" %>
<%@ page import="net.realqinwei.hzcrm.crm.been.User" %>
<%@ page import="java.io.PrintWriter" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%!
	private String getContent(TreeComponent<User> tree) {
		StringBuilder content = new StringBuilder();

		content.append("<li>");
		content.append("<a href=\"#\">").append(tree.getValue().getId()).append("</a>");
		if (null != tree.getChilds()) {
			content.append("<ul>");
			for (TreeComponent<User> t : tree.getChilds()) {
				content.append(getContent(t));
			}
			content.append("</ul>");
		}
		content.append("</li>");

		return content.toString();
	}
%>
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
<title>test.html</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>test.css">
<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
</head>
<body>
	<div class="tree">
		<ul>
<%
TreeComponent<User> tree = (TreeComponent<User>) session.getAttribute("tree");
 %>
<%=getContent(tree) %>
		</ul>
	</div>
</body></html>
