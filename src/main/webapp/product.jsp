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
		<li class="active"><a href="#product"><s:text name="home.product" /></a></li>
		<li><a href="<%=basePath%>viewcontact.action"><s:text name="home.contact" /></a></li>
		<li><a href="<%=basePath%>admin/newslist.action"><s:text name="home.login" /></a></li>
		</ul>
		</div>
		</div>
		</div>
		</div>
	</div>

	<div class="container">
		<div class="hero-unit">
			<div class="row">
    			<div class="span10">
    				<!-- 
					<h2>Highly customizable</h2>
					<p>With a bit of extra markup, it's possible to add any kind of HTML content like headings, paragraphs, or buttons into thumbnails.</p>
					 -->
					<ul class="thumbnails">
						<s:bean name="com.nm_huazhi.struts2.comparator.NewsIDComparator" var="newsIDComparator"/>
						<s:sort comparator="newsIDComparator" source="products">
						<s:iterator>
						
						<li class="span5">
          					<div class="thumbnail">
            					<img src="<s:property value="image" />" alt="<s:property value="title" />">
            					<div class="caption">
              						<h5><s:property value="title" escape="false" /></h5>
              						<!-- 
              						<p><s:property value="content" escape="false" /></p>
              						 -->
            					</div>
          					</div>
        				</li>
						
						</s:iterator>
						</s:sort>
					</ul>
				</div>
			</div>		
		</div>
    </div>
    
	<%@ include file="footer.jsp"%>
	
</div>
</body>
</html>

