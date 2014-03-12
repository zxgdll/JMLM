<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="nm-huazhi-head.jsp"%>

<div class="container">

	<div class="header-unit">
		<div id="siteHeader">
		<div id="siteInfo">
		<h2><s:text name="home.title" /></h2>
		<p id="motto"><s:text name="home.motto" /></p>
		</div>
		</div>
		
 		<div class="navbar">
		<div class="navbar-inner">
		<div class="container">
		<div class="nav-collapse pull-left">
		<ul class="nav">
		<li><a href="<%=basePath%>index.action"><s:text name="home.home" /></a></li>
		<li><a href="<%=basePath%>viewabout.action"><s:text name="home.about" /></a></li>
		<li><a href="<%=basePath%>viewnews.action"><s:text name="home.news" /></a></li>
		<li><a href="<%=basePath%>viewproduct.action"><s:text name="home.product" /></a></li>
		<li class="active"><a href="#contact"><s:text name="home.contact" /></a></li>
		<li><a href="<%=basePath%>admin/newslist.action"><s:text name="home.login" /></a></li>
		</ul>
		</div>
		</div>
		</div>
		</div>
	</div>

	<div class="hero-unit">
	<p><s:property value="contact.content" escape="false" /></p>
	</div>
	
	<%@ include file="footer.jsp"%>
	
</div>
</body>
</html>
