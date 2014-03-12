<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="nm-huazhi-head.jsp"%>
<script src="<%=basePath%>bootstrap/js/jquery.js"></script>
<script src="<%=basePath%>bootstrap/js/bootstrap-carousel.js"></script>
<div class="container">
	<div class="header-unit">
		<div id="siteHeader">
			<div id="siteInfo">
				<h2>
					<s:text name="home.title" />
				</h2>
				<p id="motto">
					<s:text name="home.motto" />
				</p>
			</div>
		</div>
 		<div class="navbar">
		<div class="navbar-inner">
		<div class="container">
		<div class="nav-collapse pull-left">
		<ul class="nav">
		<li class="active"><a href="#"><s:text name="home.home" /></a></li>
		<li><a href="<%=basePath%>viewabout.action"><s:text name="home.about" /></a></li>
		<li><a href="<%=basePath%>viewnews.action"><s:text name="home.news" /></a></li>
		<li><a href="<%=basePath%>viewproduct.action"><s:text name="home.product" /></a></li>
		<!-- 
		<li><a href="<%=basePath%>viewcontact.action"><s:text name="home.contact" /></a></li>
		 -->
		<li><a href="<%=basePath%>admin/newslist.action"><s:text name="home.login" /></a></li>
		</ul>
		</div>
		</div>
		</div>
		</div>
			
	</div>
	
	<div class="container-fluid">
	<div class="row-fluid">
  	
	<div class="span7">
		<!-- 
		<h2>Example carousel</h2>
		<p>Watch the slideshow below.</p>
		-->
		<div id="myCarousel" class="carousel slide">
		<div class="carousel-inner">
		
		<s:bean name="com.nm_huazhi.struts2.comparator.NewsIDComparator" var="newsIDComparator"/>
		<s:sort comparator="newsIDComparator" source="products">
		<s:iterator status="rowstatus">
		<div class="<s:if test="#rowstatus.first">item active</s:if><s:else>item</s:else>">
			<img src="<s:property value="image" />" alt="">
			<div class="carousel-caption">
				<h4><a href="<%=basePath%>product-show.action?newsID=<s:property value="id"/>"><s:property value="title" escape="false" /></a></h4>
				<!-- 
        		<p><s:property value="content" escape="false" /></p>
        		 -->
			</div>
		</div>
		</s:iterator>
		</s:sort>
		
		</div>
		<a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
		<a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
		</div>
		
		<%-- 
		<div class="hero-unit">
		<s:bean name="com.nm_huazhi.struts2.comparator.NewsModifytimeComparator" 
				var="newsModifytimeComparator" />
		<s:sort comparator="newsModifytimeComparator" source="headline">
		<s:iterator status="rowstatus">
		<s:if test="#rowstatus.first">
		<h2><s:property value="title" /></h2>
		<p><s:property value="content" escape="false" /></p>
		<p>
			<a href="<%=basePath%>viewnews.action?newsID=<s:property value="id"/>"
					class="btn btn-primary btn-large">
				<s:text name="home.detail" />
			</a>
		</p>
		</s:if>
		</s:iterator>
		</s:sort>
		</div>
		 --%>
		 
		<div class="hero-unit">
		<s:bean name="com.nm_huazhi.struts2.comparator.NewsModifytimeComparator" 
				var="newsModifytimeComparator" />
		<s:sort comparator="newsModifytimeComparator" source="headline">
		<table class="table table-condensed">
		<tbody>
		<s:iterator>
		<tr>
		<td>
			<a href="<%=basePath%>viewnews.action?newsID=<s:property value="id"/>">
				<s:property value="title" escape="false" />
			</a>
		</td>
		<td><s:text name="format.date.index"><s:param value="modifyTime" /></s:text></td>
		</tr>
		</s:iterator>
		</tbody>
		</table>
		</s:sort>
		</div>
		 
	</div>
	
	<div class="span5">

    <!-- 
	<ul class="thumbnails">
		<s:bean name="com.nm_huazhi.struts2.comparator.NewsIDComparator" var="newsIDComparator"/>
		<s:sort comparator="newsIDComparator" source="products">
		<s:iterator>
		<li class="span4">
		<div class="thumbnail">
		<img src="<s:property value="image" />" alt="<s:property value="title" />">
		<div class="caption">
		<h5><s:property value="title" escape="false" /></h5>
		<p><s:property value="content" escape="false" /></p>
		</div>
		</div>
		</li>				
		</s:iterator>
		</s:sort>
	</ul>
	 -->
	 
	 	<div class="hero-unit">
	 	<p><s:property value="#application['about'].content" escape="false" /></p>
	 	</div>
		
	</div>
    
	</div>
	</div>
	
	<%@ include file="footer.jsp"%>
	
</div>

</body>
</html>
<%-- END --%>
