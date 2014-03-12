<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<footer>
<div class="container">
	<div class="row">
		<div class="span6">
			<p><s:property value="#application['contact'].content" escape="false" /></p>
		</div>
		<div class="span6">
		<p>
			<s:text name="home.online">
				<s:param><s:property value="#application['online'].size()" /></s:param>
			</s:text>
		</p>
		<p><s:text name="home.copyright" /></p>
		</div>
	</div>
</div>
</footer>