<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 	<div id="wait">
		<img src="../default/img/ajax-loader.gif" alt="校招季">
	</div> -->
<div class="productFilter">
	<div class="filterTitle">
		当前位置: ${ctx.areaName} >> ${ctx.cityName} >> ${ctx.collegeName} <span
			class="righttop">点击 ▲</span>
	</div>
	<dl style="display: none">
		<dt>城市：</dt>
		<dd id="citys">
			<c:forEach var='city' items="${curArea.cities}">

				<c:choose>
					<c:when test="${city.id == ctx.cityId}">
						<span class='select'><a href='${cityUrl}'>${city.name}</a></span>
					</c:when>
					<c:otherwise>
						<c:url var="cityUrl" value="./index.do">
							<c:param name="cityId" value="${city.id}" />
						</c:url>
						<span><a href='${cityUrl}'>${city.name}</a></span>
					</c:otherwise>
				</c:choose>

			</c:forEach>

		</dd>
	</dl>
	<dl style="display: none">
		<dt>学校：</dt>
		<dd id="colleges">
			<!-- <span rel="3" class="select">清华大学</span> -->
			<c:forEach var='college' items="${colleges}">
				<c:choose>
					<c:when test="${college.id == ctx.collegeId}">
						<span class='select'> ${college.name}</span>
					</c:when>
					<c:otherwise>
						<c:url var="collegeUrl" value="./index.do">
							<c:param name="collegeId" value="${college.id}" />
						</c:url>
						<span><a href='${collegeUrl}'>${college.name}</a></span>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</dd>
	</dl>
</div>