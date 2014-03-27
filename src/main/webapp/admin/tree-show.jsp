<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ page import="net.earthcoder.jmlm.domain.*" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%!
	private String getContent(BinaryNode tree) {
		StringBuilder content = new StringBuilder();
		content.append("<li>");
		content.append("<a href=\"#\">");
        content.append(tree.getContent().nodeID()).append(" ").append(tree.getContent().name());
        content.append("</a>");
        BinaryNode[] childs = tree.getChilds();
        if (null != childs[0]) {
            content.append("<ul>");
            content.append(getContent(childs[0]));
            if (null != childs[1]) {
                content.append(getContent(childs[1]));
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
</head>
<body style="width: 33300px;">
	<div class="tree">
		<ul>
<%
    BinaryTree tree = (BinaryTree) session.getAttribute("tree");
 %>
<%=getContent(tree.getRootNode()) %>
		</ul>
	</div>
</body></html>
