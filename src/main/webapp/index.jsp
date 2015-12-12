<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>xxx</title>

<link href="/styles/default.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<c:out value="${area.name}" escapeXml="true" default="默认值" />
	<c:forEach var='city' items="${area.cities}">
		<c:out value="${city.name}" escapeXml="true" default="默认值" />
	</c:forEach>
</body>
</html>