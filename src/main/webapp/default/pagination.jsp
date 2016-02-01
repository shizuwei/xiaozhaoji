<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="pagination">

	<c:forEach begin="1" end="${page.totalPages}" varStatus="status">

		<c:if test="${status.count==1 && page.curPage > 1}">
			<c:url var="preUrl" value="${url}">
				<c:param name="page" value="${page.curPage - 1}" />
				<c:if test="${not empty ctx.areaId}">
					<c:param name="areaId" value="${ctx.areaId}" />
				</c:if>
				<c:if test="${not empty ctx.cityId}">
					<c:param name="cityId" value="${ctx.cityId}" />
				</c:if>
				<c:if test="${not empty ctx.collegeId}">
					<c:param name="collegeId" value="${ctx.collegeId}" />
				</c:if>
			</c:url>
			<a href="${preUrl}" class="pre">上一页</a>
		</c:if>

		<c:choose>
			<c:when test="${status.count == page.curPage}">
				<span class="current">${status.count }</span>
			</c:when>
			<c:otherwise>
				<c:url var="pageUrl" value="${url}">
					<c:param name="page" value="${status.count}" />
					<c:if test="${not empty ctx.areaId}">
						<c:param name="areaId" value="${ctx.areaId}" />
					</c:if>
					<c:if test="${not empty ctx.cityId}">
						<c:param name="cityId" value="${ctx.cityId}" />
					</c:if>
					<c:if test="${not empty ctx.collegeId}">
						<c:param name="collegeId" value="${ctx.collegeId}" />
					</c:if>
				</c:url>
				<a href="${pageUrl}">${status.count }</a>
			</c:otherwise>
		</c:choose>

		<c:if
			test="${status.count == page.totalPages && page.curPage < page.totalPages}">
			<c:url var="nextUrl" value="${url}">
				<c:param name="page" value="${page.curPage + 1}" />
				<c:if test="${not empty ctx.areaId}">
					<c:param name="areaId" value="${ctx.areaId}" />
				</c:if>
				<c:if test="${not empty ctx.cityId}">
					<c:param name="cityId" value="${ctx.cityId}" />
				</c:if>
				<c:if test="${not empty ctx.collegeId}">
					<c:param name="collegeId" value="${ctx.collegeId}" />
				</c:if>
			</c:url>
			<a href="${nextUrl}" class="next">下一页</a>
		</c:if>

	</c:forEach>
</div>