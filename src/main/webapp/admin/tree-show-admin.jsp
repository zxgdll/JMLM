<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
    <a href="<%=basePath%>admin/newslist.action">返回</a>
	<div class="tree">
		<ul>
            <s:property value="content" escape="false"/>
		</ul>
	</div>
</body></html>
