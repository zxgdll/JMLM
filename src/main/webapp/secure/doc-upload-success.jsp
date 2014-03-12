<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>

<div class="container">
	<div class="row">
		<div class="span10 offset1">
			<d:table name="words" class="table table-condensed table-striped" pagesize="15" requestURI="doc-analyze.action" export="true">
				<d:column property="content" title="#" />
				<d:column property="count" title="Name" /> 
			</d:table>
		</div>
	</div>
</div>
</body>
</html>
