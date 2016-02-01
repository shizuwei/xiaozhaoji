<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="./head.jsp"%>

<div class="nav">
	<ul class="w1000 clear">
		<li href="../talk/index.do"><a href="#">宣讲会</a></li>
		<li ><a href="../xyzp/index.do">校招会</a></li>
		<li><a href="../zph/index.do">社招会</a></li>
		<li class="cur"><a href="../news/index.do">求职攻略</a></li>
	</ul>
</div>

<div class="w1000 clear">
	<div id='pos' style='float: left; width: 100%'>首页 &gt;求职攻略&gt;
		${news.title}</div>
	<div class="main">
		<div class="sBox">
			<div class='content_header'>
				<h2>${news.title}</h2>
				<h3>
					<span>发布时间：${news.addTime}</span> <span>来源：<a
						href="${news.srcUrl}">${news.srcName}</a></span>
				</h3>
			</div>
			<div class='content_body'>
				<%@ include file="./share.html"%>
				<div id='content'>
				${news.content}
				</div>

			</div>

		</div>
	</div>
	<%@ include file="./rightside.jsp"%>
</div>

<%@ include file="./footer.jsp"%>