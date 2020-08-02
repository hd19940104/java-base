<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="gb2312">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>牛人视频集锦_幽默大师_搞笑大师_一乐岛搞笑视频网</title>
<meta name="keywords" content="搞笑牛人" />
<meta name="description"
	content="牛人在民间，这里有众多搞笑大师自制的精彩搞笑视频集锦！还在等什么，近百位幽默大师在这里表演，总有一款适合你！" />
<link rel="stylesheet"
	href="http://www.yiledao.com/templets/default/bns/common.css">
<script src="http://www.yiledao.com/templets/default/bns/jquery.js"></script>
<base target="_blank">
</head>
<body>
	<script type="text/javascript">
		if ((navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i))) {
			url = window.location.href;
			url = url.replace('www.yiledao', 'm.yiledao');
			window.location.href = url;
		}
	</script>
	<script>
		//收藏本站
		function AddFavorite(title, url) {
			try {
				window.external.addFavorite(url, title);
			} catch (e) {
				try {
					window.sidebar.addPanel(title, url, "");
				} catch (e) {
					alert("抱歉，您所使用的浏览器无法完成此操作。\n\n加入收藏失败，请使用Ctrl+D进行添加");
				}
			}
		}
	</script>
	<div id="header">
		<div class="logo">
			<a href="http://www.yiledao.com" title="搞笑视频"><img
				src="http://www.yiledao.com/templets/default/bns/logo.gif"
				alt="搞笑视频" /></a>
		</div>
		<div class="search">
			<div style="height: 40px;">
				<script type="text/javascript">
					document
							.write(unescape('%3Cdiv id="bdcs"%3E%3C/div%3E%3Cscript charset="utf-8" src="http://znsv.baidu.com/customer_search/api/js?sid=3291430195817302146')
									+ '&plate_url='
									+ (encodeURIComponent(window.location.href))
									+ '&t='
									+ (Math.ceil(new Date() / 3600000))
									+ unescape('"%3E%3C/script%3E'));
				</script>
			</div>
			<div class="fl mrs">
				<a href="/meinv/" target="_self">美女搞笑</a><a href="/remenhuati/"
					target="_self">搞笑新闻</a><a href="/mengbaodouchong/" target="_self">萌宝逗宠</a><a
					href="/egaozhenggu/" target="_self">恶搞整蛊</a><a href="/shenqu/"
					target="_self">洗脑神曲</a><a href="/zhongkouwei/" target="_self">重口味</a>
			</div>
		</div>
	</div>

	<div id="nav">
		<ul>
			<li class="home"><a href="http://www.yiledao.com/"
				target="_self" title="搞笑视频">首页</a> <img
				src="/images/navigation/hot.gif"
				style="position: absolute; z-index: 1001; margin-left: 205px; margin-top: -45px;">
				<img src="/images/navigation/hot.gif"
				style="position: absolute; z-index: 1001; margin-left: 730px; margin-top: -45px;">
			</li>
			<li class="dropdown"><a href="/gaoxiaoniuren/" target="_self">搞笑牛人</a>
			</li>
			<li class="dropdown"><a href="/lianzai/" target="_self">网络短剧</a>
			</li>
			<li class="dropdown"><a href="/youxi/" target="_self">搞笑游戏</a></li>
			<li class="dropdown"><a href="/dongman/" target="_self">搞笑动画</a>
			</li>
			<li class="dropdown"><a href="/xiaopinxiangsheng/"
				target="_self">相声小品</a></li>
			<li class="dropdown"><a href="/zongyi/" target="_self">综艺</a></li>
			<li class="dropdown"><a href="/peiyin/" target="_self">配音</a></li>
			<li class="dropdown"><a href="/zhibo/" target="_self">直播</a></li>
			<li class="dropdown"><a href="/qingsong/" target="_self">集锦</a>
			</li>
			<li class="about-web dropdown hover"><a
				style="color: #000; padding: 0 35px 0 5px;"
				onclick="AddFavorite('一乐岛搞笑视频网',location.href)">收藏本站</a></li>
		</ul>
	</div>
	<script>
		var _hmt = _hmt || [];
		(function() {
			var hm = document.createElement("script");
			hm.src = "//hm.baidu.com/hm.js?f89a5a3b1b605c067fe785a545063abd";
			var s = document.getElementsByTagName("script")[0];
			s.parentNode.insertBefore(hm, s);
		})();
	</script>
	<div class="blank10"></div>
	<div class="layout">

		<!-- 右边栏{ -->
		<div class="main sa-comic_show">
			<div class="sa-toolbar clearfix"
				style='padding-bottom: 10px; font-size: 16px; padding-left: 0px; padding-top: 3px; width: 639px;'>
				<div class="l_bar" style="line-height: 32px">
					<a href='http://www.yiledao.com/'>最新新闻</a>
				</div>
			</div>
			<div class="sa-comic_show_list" id="sa-comic_show_list">
				<table style='height: 10px;'>
				</table>
				<div class="box-bd">
					<ul class="mod-pic">
						<c:forEach items="${listVideoInfo}" var="p">
							<li><a class="hele-text" href="indexVideoDetails?id=${p.id}"
								target="_blank">${p.videoName}</a> <a href="indexVideoDetails?id=${p.id}"
								target="_blank"><img src='static/imgs/${p.videoUrl}'
									border='0' width='120' height='120' alt='你有一个医生朋友是一种什么体验'></a><span><font
									color="red">09-14</font><img
									src="http://www.yiledao.com/images/navigation/new.gif"
									style="width: 25px; height: 16px; margin: -143px 0px 0px -39px;"></span>
							</li>

						</c:forEach>

					</ul>
				</div>

				<div class="center">
					<style>
.mod-page2 li {
	float: left;
	display: inline;
	white-space: nowrap;
	height: 32px;
	padding: 0 5px;
	margin: 2px;
	color: #333;
	font: 14px/40px tahoma;
	overflow: hidden;
}
</style>
					<div class="mod-page2">
						<li>首页</li>
						<li class="thisclass">1</li>
				
						<li><a target='_self' href='list_1_2.html'>下一页</a></li>
						<li><a target='_self' href='list_1_223.html'>末页</a></li>
						<li><select name='sldd' style='width: 66px'
							onchange='location.href=this.options[this.selectedIndex].value;'>
								<option selected>1</option>
								
						</select></li>
						


					</div>
				</div>
			</div>
		</div>
		<!-- }右边栏 -->
		<!-- 左边栏{ -->
		<div class="aside">
			<div class="fenlei_list">
				<div class="mod-tabs-s2">
					<div class="mod-tabs-hd">
						<h3 class="title">播放排行榜top10</h3>
					</div>
					<ul>
						<c:forEach items="${listVideoInfo}" var="p">
							<li><i class='num1'>1</i>
								<div class="pic">
									<a href="/201409/29030.html"><img src='static/imgs/${p.videoUrl}'
										border='0' width='120' height='120' alt='史上最最奇葩的面试'></a>
								</div>
								<div class="txt">
									<a href="/201409/29030.html">${p.videoName}</a>
								</div></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="blank10"></div>
			<div class="sa-comic_sortlist">
				<dl class="listform">

					<dd>
						<span class="selected"><strong>《搞笑牛人》相关栏目:</strong></span> <a
							href=" /jiaoshou/ "> 叫兽易小星 </a> <a href=" /laoshi/ "> 老湿 </a> <a
							href=" /gaoxiaoniuren/zhengyun/ "> 郑云工作室 </a> <a
							href=" /gaoxiaoniuren/pizi/ "> 司文痞子 </a> <a
							href=" /gaoxiaoniuren/hulang/ "> 胡狼工作室 </a> <a
							href=" /gaoxiaoniuren/bigxiaogongfang/ "> Big笑工坊 </a> <a
							href=" /papijiang/ "> papi酱 </a> <a
							href=" /gaoxiaoniuren/hexiangufu/ "> 何仙姑夫 </a> <a
							href=" /gaoxiaoniuren/luzhengyu/ "> 卢正雨 </a> <a
							href=" /lihongchou/ "> 李洪绸 </a>

					</dd>
				</dl>
			</div>
		</div>

		<!-- }左边栏 -->
	</div>
	<div class="blank20"></div>
	<div id="footer">
		<div class="layout">
			<p>
				<img src="/images/foot.jpg" alt="声明"> <a
					href="/gaoxiaoniuren/zhengyun/">郑云</a> | <a
					href="/gaoxiaoniuren/tangtangshentucao/">唐唐神吐槽</a> | <a
					href="/gaoxiaoniuren/bigxiaogongfang/">唐唐big笑工坊</a> | <a
					href="/remenhuati/guanaibagua/">关爱八卦成长协会</a> | <a
					href="/qingsong/qingsongshike/">轻松时刻</a> | <a href="/jiaoshou/">叫兽</a>
				| <a href="/laoshi/">老湿</a> | <a href="/baogaolaoban/02/">报告老板第二季</a>
				| <a href="/dongman/jianbixiaohua/">简笔笑画</a> | <a
					href="/lihongchou/">李洪绸</a> | <a href="/qingsong/xiyouji/">恶搞西游记</a>
				| <a href="/youxi/kengdie/">风一样的坑爹哥</a> | <a href="/youxi/shezhang/">舍长驾到</a>
				| <a href="/gaoxiaoniuren/aochangchang/">敖厂长</a> | <a
					href="/youxi/jilun/">基轮</a> | <a href="/gaoxiaoniuren/bge/">B哥</a>
				| <a href="/zhongkouwei/jinguanzhang/">金馆长</a>

			</p>
			<p>
				Copyright 2013-2014 <a href="http://www.yiledao.com">搞笑视频</a>
				京ICP备13047387号-2 <a href="http://www.yiledao.com/sitemap.html"
					target="_blank">网站地图</a>
			</p>
			<script src="/templets/default/js/bdlm.js"></script>
		</div>
	</div>
</body>