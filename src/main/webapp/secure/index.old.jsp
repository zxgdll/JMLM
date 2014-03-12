<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="../header.jsp"%>

	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="brand" href="#"><s:text name="home.title" />
				</a>
				<div class="nav-collapse pull-right">
					<ul class="nav">
						<li class="active"><a href="#"><s:text name="home.home" />
						</a>
						</li>
						<li><a href="<%=basePath%>viewabout.action"><s:text
									name="home.about" />
						</a>
						</li>
						<li><a href="<%=basePath%>viewnews.action"><s:text
									name="home.news" />
						</a>
						</li>
						<li><a href="<%=basePath%>viewproduct.action"><s:text
									name="home.product" />
						</a>
						</li>
						<li><a href="<%=basePath%>viewcontact.action"><s:text
									name="home.contact" />
						</a>
						</li>
						<li><a href="<%=basePath%>admin/newslist.action"><s:text
									name="home.login" />
						</a>
						</li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<s:bean
		name="com.nm_huazhi.struts2.comparator.NewsModifytimeComparator"
		var="newsModifytimeComparator" />
	<div class="container">
		<s:sort comparator="newsModifytimeComparator" source="headline">
			<s:iterator status="rowstatus">
				<s:if test="#rowstatus.first">
					<div class="hero-unit">
						<h1>
							<s:property value="title" />
						</h1>
						<p>
							<s:property value="content" escape="false" />
						</p>
						<p>
							<a
								href="<%=basePath%>viewnews.action?newsID=<s:property value="id"/>"
								class="btn btn-primary btn-large"> <s:text
									name="home.detail" />
							</a>
						</p>
					</div>
				</s:if>
			</s:iterator>
		</s:sort>
		<div class="row">
			<s:sort comparator="newsModifytimeComparator" source="headline">
				<s:iterator status="rowstatus">
					<s:if test="#rowstatus.index >= 1 && #rowstatus.index <= 3">
						<div class="span4">
							<h2>
								<s:property value="title" />
							</h2>
							<p>
								<s:property value="content" escape="false" />
							</p>
							<p>
								<a class="btn"
									href="<%=basePath%>viewnews.action?newsID=<s:property value="id"/>">
									<s:text name="home.detail" /> </a>
							</p>
						</div>
					</s:if>
				</s:iterator>
			</s:sort>
		</div>
		<hr>
		<footer>
			<p>
				<s:text name="home.copyright" />
			</p>
		</footer>
	</div>
</body>
</html>
