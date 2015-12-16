<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8"%>
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
			<c:forEach var='news' items="${newsList}">
				<c:url var="newsUrl" value="../news/get.do">
					<c:param name="id" value="${news.id}" />
				</c:url>
				<li><a target="_blank" href="${newsUrl}">${news.title}</a></li>
			</c:forEach>
		</ul>
	</div>
	<div class="sBox">
		<div class="sTitle">官方微信</div>
		<ul class="sCon clear">
			<div class="ma">
				<img src="../default/img/ma.png">
			</div>
			<div class="maWz">
				<strong>扫描有惊喜</strong><br>您的求职帮手
			</div>
		</ul>
	</div>
</div>

<!-- right-side-box end-->