<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="utf-8">
<meta name="keywords"
	content="校招季，应届生，校园招聘，宣讲会，简历，面经，2015年，求职，找工作，毕业生，大学生">
<meta name="description" content="宣讲会_宣讲会查询系统_校园宣讲会_校招季">

<title>校招季_2015年校园招聘_宣讲会_应届生_大学生找工作_上校招季</title>

<link href="../default/css/xiaozhao.css" rel="stylesheet">
<link href="../default/css/pagination.css" rel="stylesheet">
<script type="text/javascript" src="../default/js/jquery.1.9.1.min.js"></script>
<script type="text/javascript" src="../default/js/jquery.pagination.js"></script>
<script type="text/javascript" src="../default/js/common.js"></script>
<script type="text/javascript" src="../default/js/xjh.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>

	<div class="bar">
		<div class="w1000">
			<span class="welcome">校招季-专业为在校大学生而生的就业信息平台！ </span>
			<ul class="tlinks">
				<li><a href="http://xiaozhaoji.com/index/index.html#">加入收藏</a></li>
				<li><a href="http://xiaozhaoji.com/index/index.html#">联系我们</a></li>
				<li><a href="http://xiaozhaoji.com/admin.php/Login/login.html">管理</a></li>
				<li><a href="http://xiaozhaoji.com/index/report.html">报告</a></li>
			</ul>
		</div>
	</div>


	<div class="top clear">
		<a href="#" class="logo" title="校招季"><img
			src="../default/img/logo.png" alt="校招季"></a>
		<div class="areaTab">
			<c:if test="${not empty curArea.name}">
				<span class="curArea">地区：${curArea.name}</span>
				<div class="allArea" style="display: none;">
					<ul class="allAreaList">
						<c:forEach var='area' items="${areas}">
							<c:url var="areaUrl" value="./index.do">
								<c:param name="areaId" value="${area.id}" />
							</c:url>
							<li><a href='${areaUrl}'>${area.name}</a></li>
						</c:forEach>
					</ul>
					<p class="allAreaBg"></p>
				</div>
			</c:if>
		</div>

		<div class="search">
			<input type="text" name="q" style="display: none" id="txt_Keywords"
				class="searchInput"> <input type="text" value="请输入关键字"
				id="text_Keywords" class="searchInput"> <a target="_self"
				name="sa" class="searchBtn" href="javascript:void(0);" title="搜索"
				id="searchBtn">搜索</a>
			<div class="hint" style="display: none;">
				<dl class="hint-list">
					<dt>宣讲会</dt>
					<dd>-</dd>
				</dl>
			</div>
		</div>
	</div>