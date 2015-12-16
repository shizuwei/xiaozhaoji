<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="./head.jsp"%>

<div class="nav">
	<ul class="w1000 clear">
		<li class="cur"><a href="#">宣讲会</a></li>
		<li><a href="http://xiaozhaoji.com/xyzp/index.html">校招会</a></li>
		<li><a href="http://xiaozhaoji.com/zph/index.html">社招会</a></li>
		<li><a href="http://xiaozhaoji.com/news/index.html">求职攻略</a></li>
	</ul>
</div>

<%-- <%@ include file="./city_college.jsp"%> --%>

<div class="w1000 clear">
	<div id='pos' style='float: left; width: 100%'>首页 &gt; 招聘会 &gt;
		西安交通大学附属中学</div>
	<div class="main">
		<div class="sBox">
			<div class='content_header'>
				<h2>西安交通大学附属中学</h2>
				<h3>
					<span>发布时间：2015-11-24 14:35</span> <span>来源：<a
						href="http://scc.lb.pku.edu.cn/employment_ff8080815115747801513726616d06a3_1.html">北京大学学生就业指导服务中心</a></span>
				</h3>
			</div>
			<div class='content_body'>
				<%@ include file="./share.html"%>
				<ul>
					<li><i>●</i> 举办时间：	${talk.holdTime}</li>

					<li><i>●</i> 举办地点：	${talk.address}</li>

				</ul>

				<div id='content'>
				${talk.content}
				</div>

			</div>

		</div>
	</div>
	<%@ include file="./rightside.jsp"%>
</div>

<%@ include file="./footer.jsp"%>