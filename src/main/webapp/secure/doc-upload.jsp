<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="../header.jsp"%>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span10">
			<div class="container">
				<div class="row">
					<div class="hero-unit">
						<s:form action="doc-upload" method="post" enctype="multipart/form-data">
							<s:file name="file" />
							<s:submit />
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
