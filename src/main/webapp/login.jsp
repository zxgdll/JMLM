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
		<li><a href="<%=basePath%>viewcontact.action"><s:text name="home.contact" /></a></li>
		<li class="active"><a href="#"><s:text name="home.login" /></a></li>
		</ul>
		</div>
		</div>
		</div>
		</div>
			
	</div>
	
	<!-- 
	<div class="row">
		<s:actionerror />
	</div>
	 -->
	<div class="container">
	
		<div class="row">
		<div class="span10 offset1">
		<div class="hero-unit">
		<s:form action="login" theme="simple">
			<div class="row">
				<div class="span4 offset2" id="title">
					<h2><s:text name="login.title" /></h2>
				</div>
			</div>
			<div class="row">
				<div class="span1 offset2">用户名</div>
			</div>
			<div class="row">
				<div class="span3 offset2"><s:textfield name="loginId" /></div>
				<div class="span3"><s:fielderror name="loginId" /></div>
			</div>
			<div class="row">
				<div class="span1 offset2">密码</div>
			</div>
			<div class="row">
				<div class="span3 offset2"><s:password name="password" /></div>
				<div class="span3"><s:fielderror name="password" /></div>
			</div>
			<div class="row">
				<div class="span1 offset3"><s:reset cssClass="btn" value="%{getText('news.button.channel')}" /></div>
				<div class="span1"><s:submit cssClass="btn" value="%{getText('user.submit')}" /></div>
			</div>
		</s:form>
		</div>
			<!-- 
			<h2><s:text name="login.title" /></h2>
			<s:form action="login" cssClass="table">
				<tbody>
					<s:textfield name="loginID" label="%{getText('user.userID')}" /><s:fielderror name="loginID" />
					<s:password name="password" label="%{getText('user.userPassword')}" /><s:fielderror name="password" />
				<tr>
					<td colspan="2">
						<s:submit cssClass="btn" value="%{getText('user.submit')}" theme="simple" />
						<s:reset cssClass="btn" value="%{getText('news.button.channel')}" theme="simple" />
					</td>
				</tr>
				</tbody>
			</s:form>
			 -->
		
		</div>
		</div>
	</div>
	
	<%@ include file="footer.jsp"%>
	
	</div>
</body>
</html>
