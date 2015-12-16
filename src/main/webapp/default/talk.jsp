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

<%@ include file="./city_college.jsp"%>

	<div class="w1000 clear">
		<div class="main">
			<div class="content" style="position: relative; overflow: hidden;">
				<!-- MASK START		 -->
				<div id="mask_list" style="display: none;">
					<img
						style="position: absolute; top: 50%; left: 50%; margin: -33px; z-index: 2000;"
						src="../default/img/loader.gif">
					<div
						style="text-align: center; position: absolute; top: 0px; left: 0px; background: white; width: 100%; height: 1000px; z-index: 1000; opacity: 0.90;"></div>
				</div>
				<!-- MASK START	END	 -->
				<table border="0" cellspacing="0" cellpadding="0" class="myLxtable"
					id="content_tbl">
					<tbody>
						<tr style="background: rgb(242, 242, 242);">
							<th class="myNm">公司标题</th>
							<th>举办时间</th>
							<th>举办地点</th>
							<th>发布时间</th>
							<th>点击量</th>
						</tr>
						<c:forEach var='talk' items="${talks}">
							<c:url var="talkUrl" value="./get.do">
								<c:param name="id" value="${talk.id}" />
							</c:url>
							<tr style="background: white;">
								<td class="myNm"><a title="${talk.title}"
									class="titleAnchor" target="_blank" href="${talkUrl}">${talk.title}</a>
								</td>
								<td>${talk.holdTime}</td>
								<td>${talk.address}</td>
								<td>${talk.addTime}</td>
								<td>${talk.click}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<jsp:include page="pagination.jsp" flush="true">
				<jsp:param name="page" value="${page}" />
				<jsp:param name="url" value="./index.do" />
			</jsp:include>

		</div>
			<%@ include file="./rightside.jsp"%>
	</div>

	<%@ include file="./footer.jsp"%>