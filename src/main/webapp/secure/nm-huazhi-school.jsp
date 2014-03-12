<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="../header.jsp"%>

<div class="container-fluid">
<div class="row-fluid">
	
	<div class="span4">
	<div class="hero-unit">
	<table class="table table-condensed">
		<tbody>
		<s:bean name="com.nm_huazhi.struts2.comparator.NewsIDComparator" var="newsIDComparator"/>
		<s:sort comparator="newsIDComparator" source="courses">
		<s:iterator>
		<tr>
		<td>
			<a href="<%=basePath%>secure/courses-list.action?courseId=<s:property value="id"/>">
				<s:property value="title" escape="false" />
			</a>
		</td>
		</s:iterator>
		</s:sort>
		</tbody>
		</table>
	</div>
	</div>
	
	<div class="span8">
		<div class="hero-unit">
		<h2><s:property value="course.title"/></h2>
		<p><s:text name="format.date.news"><s:param value="course.modifyTime" /></s:text></p>
        <p><s:property value="course.content" escape="false"/></p>
		</div>
	</div>
	
</div>
</div>

</body>
</html>
