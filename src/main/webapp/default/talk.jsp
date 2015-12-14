<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

			<span class="curArea">地区：${curArea.name} </span>

			<div class="allArea" style="display: block;">
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

		</div>


		<div class="search">
			<input type="text" name="q" style="display: none" id="txt_Keywords"
				class="searchInput"> <input type="text" value="请输入关键字"
				id="text_Keywords" class="searchInput"> <a target="_self"
				name="sa" class="searchBtn" href="javascript:void(0);" title="搜索"
				id="searchBtn">搜索</a>
			<div class="hint" style="display: block;">
				<dl class="hint-list">
					<dt>宣讲会</dt>
					<dd>-</dd>
				</dl>
			</div>
		</div>
	</div>


	<div class="nav">
		<ul class="w1000 clear">
			<li class="cur"><a href="#">宣讲会</a></li>
			<li><a href="http://xiaozhaoji.com/xyzp/index.html">校招会</a></li>
			<li><a href="http://xiaozhaoji.com/zph/index.html">社招会</a></li>
			<li><a href="http://xiaozhaoji.com/news/index.html">求职攻略</a></li>
		</ul>
	</div>

	<div id="wait">
		<img src="../default/img/ajax-loader.gif" alt="校招季">
	</div>
	<div class="productFilter">
		<div class="filterTitle">
			选择<span class="owz">城市和学校：</span><span id="cur_col">清华大学</span><span
				class="righttop">点击 ▲</span>
		</div>
		<dl>
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
		<dl>
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
							<c:url var="talkUrl" value="./talk.do">
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
		<!-- right-side-box -->
		<div class="sider">
			<div class="sBox">
				<div class="sTitle">求职交流QQ群</div>
				<ul class="sList">
					<li>校招季综合群：<a target="_blank"
						href="http://shang.qq.com/wpa/qunwpa?idkey=a6125f9a7f69e3878782da898c27b565d342260ab96040bc413a83a825b5363d"><img
							border="0" src="../default/img/group.png" alt="校招季-综合"
							title="校招季-综合"> </a></li>
					<li>IT通讯求职群：<a target="_blank"
						href="http://shang.qq.com/wpa/qunwpa?idkey=fa179a632f456be85433ec9cba2d0c18480feb9c79937e22b0ffbbe231f49b2b"><img
							border="0" src="../default/img/group.png" alt="校招季--IT通讯求职群"
							title="校招季--IT通讯求职群"></a></li>
					<li>公考事业编群：<a target="_blank"
						href="http://shang.qq.com/wpa/qunwpa?idkey=a435c38591a5cae53cf559a101fc671ce7ce7ac670230b723fc4863d79338738"><img
							border="0" src="../default/img/group.png" alt="校招季-公考事业单位"
							title="校招季-公考事业单位"></a></li>
				</ul>
			</div>
			<div class="sBox">
				<div class="sTitle">求职学堂</div>

				<ul class="sList">
					<li><a target="_blank"
						href="http://xiaozhaoji.com/news/click/id/116.html">你适合做传说中的“管培生”吗?</a></li>
					<li><a target="_blank"
						href="http://xiaozhaoji.com/news/click/id/115.html">签约和违约，你真了解吗？</a></li>
					<li><a target="_blank"
						href="http://xiaozhaoji.com/news/click/id/114.html">找工作，如果那不是你的真爱</a></li>
					<li><a target="_blank"
						href="http://xiaozhaoji.com/news/click/id/113.html">同样十年，为什么有的成高管，有的只是主管？</a></li>
					<li><a target="_blank"
						href="http://xiaozhaoji.com/news/click/id/112.html">让老板离不开你
							请悟透一个字</a></li>
					<li><a target="_blank"
						href="http://xiaozhaoji.com/news/click/id/111.html">没几把刷子，老板请你干嘛？句句震撼
							!</a></li>
					<li><a target="_blank"
						href="http://xiaozhaoji.com/news/click/id/110.html">如果你想追随梦想，就要心无他念</a></li>
					<li><a target="_blank"
						href="http://xiaozhaoji.com/news/click/id/109.html">没人告诉过你的7条好的职业建议</a></li>
					<li><a target="_blank"
						href="http://xiaozhaoji.com/news/click/id/108.html">做好这9件事，打造精装简历</a></li>
					<li><a target="_blank"
						href="http://xiaozhaoji.com/news/click/id/107.html">你可以害怕，但不要因为别人的看法而放弃梦想</a></li>
					<!-- 				<li class="bnone"><a href="#">计算机计算机计算机计算机计算机计算机</a></li> -->
				</ul>
			</div>
			<div class="sBox">
				<div class="sTitle">官方微信</div>
				<ul class="sCon clear">
					<div class="ma">
						<img src="./img/ma.png">
					</div>
					<div class="maWz">
						<strong>扫描有惊喜</strong><br>您的求职帮手
					</div>
				</ul>
			</div>
		</div>

		<!-- right-side-box end-->
	</div>

	<!-- begin footer -->
	<div class="fLink">
		<strong>友情链接：</strong><a target="_blank" href="http://pmbaike.com/">PM百科</a>
		<a target="_blank" href="http://www.nowcoder.com/">牛客网</a> <a
			target="_blank" href="http://www.xiaozhaoji.com/">校园招聘</a> <a
			target="_blank"
			href="http://xiaozhaoji.com/index.php/Home/Index/index.html">宣讲会</a>
		<a target="_blank"
			href="http://zph.xiaozhaoji.com/index.php/Home/Zph/index.html">招聘会</a>
		<a target="_blank"
			href="http://zph.xiaozhaoji.com/index.php/Home/News/index.html">求职攻略</a>
		<a target="_blank" href="http://yzy.chinaface.com/">易周游</a>
	</div>
	<div class="footer">
		<div class="w1000">
			<p>
				<a href="http://xiaozhaoji.com/index/index.html#">关于我们</a> - <a
					href="http://xiaozhaoji.com/index/index.html#">联系我们</a> -
				<script src="./校招季_2015年校园招聘_宣讲会_应届生_大学生找工作_上校招季_files/stat.php"
					language="JavaScript"></script>
				<script src="./校招季_2015年校园招聘_宣讲会_应届生_大学生找工作_上校招季_files/core.php"
					charset="utf-8" type="text/javascript"></script>
				<a href="http://www.cnzz.com/stat/website.php?web_id=1252901765"
					target="_blank" title="站长统计">站长统计</a>
			</p>
			<p>
				Copyright © 2012-2015 xiaozhaoji.com, All Rights Reserved. <a
					href="http://xiaozhaoji.com/index/index.html#">校招季</a> 版权所有
			</p>
			<p>
				<a target="_blank" href="http://www.miitbeian.gov.cn/">京ICP备14032546号-1号</a>
			</p>
			<p style="display: none;">
				<script type="text/javascript">
					var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://"
							: " http://");
					document
							.write(unescape("%3Cscript src='"
									+ _bdhmProtocol
									+ "hm.baidu.com/h.js%3F940efc3300dbf50cde83498ac0d19d16' type='text/javascript'%3E%3C/script%3E"));
				</script>
				<script src="./校招季_2015年校园招聘_宣讲会_应届生_大学生找工作_上校招季_files/h.js"
					type="text/javascript"></script>
			</p>
		</div>
	</div>



</body>
</html>