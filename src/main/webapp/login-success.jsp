<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ page import="net.earthcoder.jmlm.domain.*" %>
<%@ page import="java.util.List" %>
<%@ page import="net.realqinwei.hzcrm.crm.been.Node" %>
<%@ page import="net.realqinwei.hzcrm.crm.been.User" %>
<%@ page import="net.realqinwei.hzcrm.crm.service.intf.NodeService" %>
<%@ page import="java.util.ArrayList" %>
<%@ include file="header.jsp"%>

<div class="container-fluid">
<div class="row-fluid">
	
	<div class="span2">
	<s:if test="#session['user'].userType == 0">
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
		<li><a href="<%=basePath%>admin/addnode.action"><s:text name="addnode.submit"/></a></li>
		<li><a href="<%=basePath%>admin/bill.jsp"><s:text name="menu.bill"/></a></li>
		<li class="active"><a href="#"><s:text name="menu.user"/></a></li>
		<li><a href="<%=basePath%>tree-show"><s:text name="home.tree"/></a></li>
        <li><a href="<%=basePath%>admin/outbill.action">出账</a></li>
		</ul>
		</div>
	</s:if>
	</div>

	<div class="span10">
	<div class="hero-unit">

	<table class="table table-striped">
		<s:if test="#session['user'].userType == 0">
			<thead>
				<tr>
					<th><s:text name="table.name" /></th>
					<th><s:text name="table.IDcard" /></th>
					<th><s:text name="table.bank" /></th>
					<th><s:text name="table.phone" /></th>
					<th><s:text name="table.address" /></th>
					<th><s:text name="table.date" /></th>
					<th/>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#session['allUsers']">
					<tr>
						<td>
							<a href="<%=basePath%>userlist.action?userID=<s:property value="id"/>">
                                <s:property value="id" />&nbsp;<s:property value="userName" />
                            </a>
						</td>
						<td><s:property value="userIDCard" /></td>
						<td><s:property value="userLoginID" /></td>
						<td><s:property value="userPhone" /></td>
						<td><s:property value="userAddress" /></td>
						<td><s:text name="format.date.user"><s:param value="userCreateTime" /></s:text></td>
						<td>
                            <a href="<%=basePath%>admin/edit-user.action?userID=<s:property value="id"/>">
								<s:text name="table.modify" />
                            </a>
                        </td>
					</tr>
				</s:iterator>
			</tbody>
		</s:if>
		<s:else>
            <thead>
            <tr>
                <th width="125"><s:text name="bill.name"/></th>
                <th width="100">A区业绩</th>
                <th width="100">B区业绩</th>
                <th width="100">运营奖</th>
                <th width="100">辅导奖</th>
            </tr>
            </thead>
            <tbody>
            <%
                User loginUser = (User) session.getAttribute("user");
                NodeService service = (NodeService) session.getAttribute("service");
                List<Node> nodes = service.findByOwner(loginUser);
                BinaryTree tree = (BinaryTree) session.getAttribute("tree");
                List<BinaryNode> binaryNodes = tree.getNodes();

                boolean newStart = false;

                List<BinaryNode> result = new ArrayList<BinaryNode>();
                for (BinaryNode bn: binaryNodes) {
                    if (!newStart && bn.getContent().nodeID() > 691) {
                        newStart = true;
                    }
                    for (Node content: nodes) {
                        if (bn.contains(content)) {
                            result.add(bn);
                        }
                    }
                }
                for (BinaryNode bnode: result) {
            %>
            <tr>
                <td>
                    <a href="<%=basePath%>tree-show.action?nodeID=<%=bnode.getContent().nodeID()%>">
                        <%=bnode.getContent().name()%>
                    </a>
                </td>
                <td><%=newStart ? bnode.getLeftCurrent() : 0 %></td>
                <td><%=newStart ? bnode.getRightCurrent() : 0 %></td>
                <td><%=newStart ? bnode.getOperatingExpenses() : 0 %></td>
                <td><%=newStart ? bnode.getCounselingFee() : 0 %></td>
            </tr>
            <%
                }
            %>
            </tbody>
		</s:else>
	</table>
	</div>
	</div>
	
</div>
</div>

</body>
</html>
