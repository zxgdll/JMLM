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
		<li><a href="<%=basePath%>admin/courses-manage.action"><s:text name="home.course"/></a></li>
		<li><a href="<%=basePath%>admin/view-log.action"><s:text name="list.li.log" /></a></li>
		<li class="nav-header"><s:text name="list.head.user" /></li>
		<li><a href="<%=basePath%>admin/load.jsp"><s:text name="list.li.alart" /></a></li>
		<li><a href="<%=basePath%>admin/adduser.action"><s:text name="menu.add"/></a></li>
		<li class="active"><a href="#"><s:text name="addnode.submit"/></a></li>
		<li><a href="<%=basePath%>admin/bill.jsp"><s:text name="menu.bill"/></a></li>
		<li><a href="<%=basePath%>login-success.jsp"><s:text name="menu.user"/></a></li>
		</ul>
		</div>
	</div>
	
	<div class="span10">
	<div class="hero-unit">
		<s:form name="nodeFrom" action="save-node">
		<s:token></s:token>
        <style>
            .table br{display:none}
        </style>
		<table class="table">
            <s:select name="node.nodeUserID" label="%{getText('table.owner')}"
                      list="userList" listKey="id" listValue="id + ' ' + userName"
                      headerKey="0" headerValue="%{getText('adduser.select.headerValue')}" />

            <s:doubleselect id= "doubleSelectID" label="%{getText('table.refer')}"
                            headerKey="0" headerValue="%{getText('table.owner.select')}"
                            list="usersHaveNodes" listKey="id" listValue="id + ' ' + userName"
                            doubleId="nodeSelectID" doubleName="node.userReferID"
                            doubleValue="%{getText('adduser.select.headerValue')}"
                            doubleList="userNodeMap.get(top.id)" doubleListKey="id" doubleListValue="userName" />

            <s:doubleselect id= "doubleSelectID2" label="%{getText('table.owner.load')}"
                            headerKey="0" headerValue="%{getText('table.owner.load')}"
                            list="usersHaveNodes" listKey="id" listValue="id + ' ' + userName"
                            doubleId="nodeSelectID2" doubleName="node.nodeLoaderID"
                            doubleValue="%{getText('adduser.select.headerValue')}"
                            doubleList="userNodeMap.get(top.id)" doubleListKey="id" doubleListValue="userName" />

            <s:submit value="%{getText('adduser.submit')}" />
		</table>
		</s:form>
	</div>
	</div>
	
</div>
</div>

</body>
</html>
