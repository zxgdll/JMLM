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
<title><s:text name="home.title" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="<s:text name="home.desc" />">
<link href="<%=basePath%>img/favicon.ico" type="image/x-icon" rel="icon" />
<link rel="stylesheet" href="<%=basePath%>bootstrap/css/bootstrap-default.css">
<link rel="stylesheet" href="<%=basePath%>bootstrap/css/nm-huazhi.css" type="text/css" media="screen, projection" >
<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
</head>
<body>