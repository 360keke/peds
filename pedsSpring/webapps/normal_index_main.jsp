<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
	if (application.getAttribute("ProjectName") == null) {
		application.setAttribute("ProjectName", "私人安全助手");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible"/>
		<title>私人安全助手</title>

		
<link href="${path}/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${path}/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${path}/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="${path}/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${path}/css/peds.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="${path}/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<script src="${path}/dwz/js/speedup.js" type="text/javascript"></script>
<script src="${path}/dwz/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="${path}/dwz/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${path}/dwz/js/jquery.validate.js" type="text/javascript"></script>
<script src="${path}/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="${path}/dwz/xheditor/xheditor-1.1.14-zh-cn.min.js" type="text/javascript"></script>
<script src="${path}/dwz/uploadify/scripts/swfobject.js" type="text/javascript"></script>
<script src="${path}/dwz/uploadify/scripts/jquery.uploadify.v2.1.0.js" type="text/javascript"></script>

<!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
<script type="text/javascript" src="${path}/dwz/chart/raphael.js"></script>
<script type="text/javascript" src="${path}/dwz/chart/g.raphael.js"></script>
<script type="text/javascript" src="${path}/dwz/chart/g.bar.js"></script>
<script type="text/javascript" src="${path}/dwz/chart/g.line.js"></script>
<script type="text/javascript" src="${path}/dwz/chart/g.pie.js"></script>
<script type="text/javascript" src="${path}/dwz/chart/g.dot.js"></script>

<script src="${path}/dwz/js/dwz.core.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.util.date.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.drag.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.tree.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.accordion.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.ui.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.theme.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.navTab.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.tab.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.resize.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.dialog.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.stable.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.ajax.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.pagination.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.database.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.effects.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.panel.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.history.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.combox.js" type="text/javascript"></script>
<script src="${path}/dwz/js/dwz.print.js" type="text/javascript"></script>
<!--        
<script src="${path}/dwz/dwz.min.js" type="text/javascript"></script>
-->          
<script src="${path}/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
			<!-- zTree 引用 -->
<link rel="stylesheet" href="${ path}/zTree/css/zTreeStyle/zTreeStyle.css"	type="text/css">
<script type="text/javascript"	src="${ path}/zTree/js/jquery.ztree.core-3.5.js"></script>
			<!-- end zTree 引用 -->
		<script type="text/javascript">
$(function(){
	DWZ.init("${path}/dwz/dwz.frag.xml", {
		loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
		loginUrl:"${path}/index.jsp",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"${path}/dwz/themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});

</script>
	</head>
	<body scroll="yes">
		<div id="layout">
			<div id="header">
				<div class="headerNav">
					<a class="logo" href="www.360keke.com">标志</a>
					<ul class="nav">
						<li>
							<a href="https://me.alipay.com/360keke" target="_blank">捐赠</a>
						</li>
						<li>
							<a href="changepwd.html" target="dialog" width="600">设置</a>
						</li>
						<li>
							<a href="http://www.cnblogs.com/dwzjs" target="_blank">博客</a>
						</li>
						<li>
							<a href="http://weibo.com/dwzui" target="_blank">微博</a>
						</li>
						<li>
							<a href="http://bbs.dwzjs.com" target="_blank">论坛</a>
						</li>
						<li>
							<a href="${contenpath }quit.htm">退出</a>
						</li>
					</ul>
					<ul class="themeList" id="themeList">
						<li theme="default">
							<div class="selected">
								蓝色
							</div>
						</li>
						<li theme="green">
							<div>
								绿色
							</div>
						</li>
						<!--<li theme="red"><div>红色</div></li>-->
						<li theme="purple">
							<div>
								紫色
							</div>
						</li>
						<li theme="silver">
							<div>
								银色
							</div>
						</li>
						<li theme="azure">
							<div>
								天蓝
							</div>
						</li>
					</ul>
				</div>
				<!-- navMenu -->
			</div>
			<div id="leftside">
				<div id="sidebar_s">
					<div class="collapse">
						<div class="toggleCollapse">
							<div></div>
						</div>
					</div>
				</div>
				<div id="sidebar">
					<div class="toggleCollapse">
						<h2>
							主菜单
						</h2>
						<div>
							收缩
						</div>
					</div>
					<div class="accordion" fillSpace="sidebar">
						<div class="accordionHeader">
							<h2>
								<span>Folder</span>系统参数设置
							</h2>
						</div>
						<div class="accordionContent">
							<ul class="tree treeFolder">
								<li>
									<a href="tabsPage.html" target="navTab">系统设置</a>
									<ul>
										<li>
											<a href="http://www.baidu.com" target="navTab"
												rel="commsetting">参数设置</a>
										</li>
										<li>
											<a href="http://www.baidu.com" target="navTab"
												rel="commsetting">工具注册</a>
										</li>
										<li>
											<a href="http://www.baidu.com" target="navTab"
												rel="commsetting">文件过滤器设置</a>
										</li>
									</ul>
								</li>
							</ul>
							<ul class="tree">
								<li>
									<a href="http://www.baidu.com" target="navTab"
										rel="commsetting">工具注册</a>
								</li>
							</ul>
						</div>
						<div class="accordionHeader">
							<h2>
								<span>Folder</span>文件管理
							</h2>
						</div>
						<div class="accordionContent">
							<ul class="tree treeFolder">
								<li>
									<a>文件管理</a>
									<ul>
										<li>
											<a href="${path}/catMgr/search.htm?type=0" target="navTab"
												rel="file_catalog">文件分类管理</a>
										</li>
										<li>
											<a href="w_tabs.html" target="navTab" rel="file_tree">文件树</a>
										</li>
										<li>
											<a href="${path }/fileMgr/fileScanner.jsp" target="navTab" rel="file_scan">文件扫描</a>
										</li>
										<li>
											<a href="${path }/fileMgr/search.htm" target="navTab" rel="file_mgr">文件管理</a>
										</li>
											<li>
											<a href="w_tabs.html" target="navTab" rel="w_tabs">孤立文件管理</a>
										</li>
											<li>
											<a href="w_tabs.html" target="navTab" rel="file_dup">重复文件管理</a>
										</li>
											<li>
											<a href="w_tabs.html" target="navTab" rel="file_dup">网盘管理</a>
										</li>
									</ul>
								</li>
								<li>
									<a>日程安排及提醒</a>
									<ul>
										<li>
											<a href="chart/test/hbarchart.html" target="navTab"
												rel="chart">参数设置</a>
										<li>
											<a href="chart/test/barchart.html" target="navTab"
												rel="chart">日程安排</a>
										</li>
								</li>
								<li>
									<a href="chart/test/linechart.html" target="navTab" rel="chart">事件提醒设置</a>
								</li>
								<li>
									<a href="chart/test/piechart.html" target="navTab" rel="chart">饼图</a>
								</li>
							</ul>
							</li>
							</ul>
						</div>
						<div class="accordionHeader">
							<h2>
								<span>Folder</span>资源管理
							</h2>
						</div>
						<div class="accordionContent">
							<ul class="tree treeFolder">
								<li>
									<a>网络站点管理</a>
									<ul>
										<li>
											<a href="w_button.html" target="navTab" rel="w_button">站点分类管理</a>
										</li>
										<li>
											<a href="w_textInput.html" target="navTab" rel="w_textInput">站点管理</a>
										</li>
										<li>
											<a href="w_combox.html" target="navTab" rel="w_combox">自定义站点</a>
										</li>
										<li>
											<a href="w_checkbox.html" target="navTab" rel="w_checkbox">站点树</a>
										</li>
									</ul>
								</li>
								<li>
									<a>Windows资源管理</a>
									<ul>
										<li>
											<a href="demo/pagination/layout1.html" target="navTab"
												rel="pagination1">账号管理</a>
										</li>
										<li>
											<a href="demo/pagination/layout2.html" target="navTab"
												rel="pagination2">主机资源</a>
										</li>
										<li>
											<a href="demo/pagination/layout2.html" target="navTab"
												rel="pagination2">网络设备资源</a>
										</li>
										<li>
											<a href="demo/pagination/layout2.html" target="navTab"
												rel="pagination2">数据库</a>
										</li>
									</ul>
								</li>
								<li>
									<a>收藏夹</a>
									<ul>
										<li>
											<a href="demo/pagination/layout1.html" target="navTab"
												rel="pagination1">账号管理</a>
										</li>
										<li>
											<a href="demo/pagination/layout2.html" target="navTab"
												rel="pagination2">主机资源</a>
										</li>
										<li>
											<a href="demo/pagination/layout2.html" target="navTab"
												rel="pagination2">网络设备资源</a>
										</li>
										<li>
											<a href="demo/pagination/layout2.html" target="navTab"
												rel="pagination2">数据库</a>
										</li>
									</ul>
								</li>
							</ul>
						</div>
						<div class="accordionHeader">
							<h2>
								<span>Folder</span>日程管理
							</h2>
						</div>
						<div class="accordionContent">
							<ul class="tree treeFolder">
								<li>
									<a href="tabsPage.html" target="navTab">系统设置</a>
									<ul>
										<li>
											<a href="http://www.baidu.com" target="navTab"
												rel="commsetting">参数设置</a>
										</li>
										<li>
											<a href="http://www.baidu.com" target="navTab"
												rel="commsetting">工具注册</a>
										</li>
										<li>
											<a href="http://www.baidu.com" target="navTab"
												rel="commsetting">文件过滤器设置</a>
										</li>
									</ul>
								</li>
							</ul>
							<ul class="tree">
								<li>
									<a href="http://www.baidu.com" target="navTab"
										rel="commsetting">工具注册</a>
								</li>
							</ul>
						</div>
						<div class="accordionHeader">
							<h2>
								<span>Folder</span>个人记账管理
							</h2>
						</div>
						<div class="accordionContent">
							<ul class="tree treeFolder">
								<li>
									<a href="tabsPage.html" target="navTab">系统设置</a>
									<ul>
										<li>
											<a href="http://www.baidu.com" target="navTab"
												rel="commsetting">参数设置</a>
										</li>
										<li>
											<a href="http://www.baidu.com" target="navTab"
												rel="commsetting">工具注册</a>
										</li>
										<li>
											<a href="http://www.baidu.com" target="navTab"
												rel="commsetting">文件过滤器设置</a>
										</li>
									</ul>
								</li>
							</ul>
							<ul class="tree">
								<li>
									<a href="http://www.baidu.com" target="navTab"
										rel="commsetting">工具注册</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div id="container">
				<div id="navTab" class="tabsPage">
					<div class="tabsPageHeader">
						<div class="tabsPageHeaderContent">
							<!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
							<ul class="navTab-tab">
								<li tabid="main" class="main">
									<a href="javascript:;"><span><span class="home_icon">我的主页</span>
									</span> </a>
								</li>
							</ul>
						</div>
						<div class="tabsLeft">
							left
						</div>
						<!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
						<div class="tabsRight">
							right
						</div>
						<!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
						<div class="tabsMore">
							more
						</div>
					</div>
					<ul class="tabsMoreList">
						<li>
							<a href="javascript:;">我的主页</a>
						</li>
					</ul>
					<div class="navTab-panel tabsPageContent layoutBox">
						<div class="page unitBox">
							<div class="accountInfo">
								<div class="alertInfo">
									<h2>
										<a href="doc/dwz-user-guide.pdf" target="_blank">DWZ框架使用手册(PDF)</a>
									</h2>
									<a href="doc/dwz-user-guide.swf" target="_blank">DWZ框架演示视频</a>
								</div>
								<div class="right">
									<p>
										<a href="doc/dwz-user-guide.zip" target="_blank"
											style="line-height: 19px">DWZ框架使用手册(CHM)</a>
									</p>
									<p>
										<a href="doc/dwz-ajax-develop.swf" target="_blank"
											style="line-height: 19px">DWZ框架Ajax开发视频教材</a>
									</p>
								</div>
								<p>
									<span>DWZ富客户端框架</span>
								</p>
								<p>
									DWZ官方微博:
									<a href="http://weibo.com/dwzui" target="_blank">http://weibo.com/dwzui</a>
								</p>
							</div>
							<div class="pageFormContent" layoutH="80"
								style="margin-right: 230px">

								<p style="color: red">
									DWZ官方微博
									<a href="http://weibo.com/dwzui" target="_blank">http://weibo.com/dwzui</a>
								</p>
								<p style="color: red">
									DWZ官方微群
									<a href="http://q.weibo.com/587328/invitation=11TGXSt-148c2"
										target="_blank">http://q.weibo.com/587328/invitation=11TGXSt-148c2</a>
								</p>

								<div class="divider"></div>
								<h2>
									dwz v1.2视频教程:
								</h2>
								<p>
									<a href="http://www.u-training.com/thread-57-1-1.html"
										target="_blank">http://www.u-training.com/thread-57-1-1.html</a>
								</p>

								<div class="divider"></div>
								<h2>
									DWZ系列开源项目:
								</h2>
								<div class="unit">
									<a href="http://code.google.com/p/dwz/" target="_blank">dwz富客户端框架
										- jUI</a>
								</div>
								<div class="unit">
									<a href="http://code.google.com/p/dwz4j" target="_blank">dwz4j(Java
										Web)快速开发框架 + jUI整合应用</a>
								</div>
								<div class="unit">
									<a href="http://code.google.com/p/j-hi" target="_blank">J-HI(Java
										Web)快速开发平台 + jUI整合应用（Eclipse插件生成项目代码）</a>
								</div>
								<div class="unit">
									<a href="http://code.google.com/p/dwz4php" target="_blank">ThinkPHP
										+ jUI整合应用</a>
								</div>
								<div class="unit">
									<a href="http://code.google.com/p/dwz4php" target="_blank">Zend
										Framework + jUI整合应用</a>
								</div>
								<div class="unit">
									<a href="http://www.yiiframework.com/extension/dwzinterface/"
										target="_blank">YII + jUI整合应用</a>
								</div>

								<div class="divider"></div>
								<h2>
									常见问题及解决:
								</h2>
								<pre style="margin: 5px; line-height: 1.4em">
Error loading XML document: dwz.frag.xml
直接用IE打开index.html弹出一个对话框：Error loading XML document: dwz.frag.xml
原因：没有加载成功dwz.frag.xml。IE ajax laod本地文件有限制, 是ie安全级别的问题, 不是框架的问题。
解决方法：用firefox打开或部署到apache下。
</pre>

								<div class="divider"></div>
								<h2>
									有偿服务请联系:
								</h2>
								<pre style="margin: 5px; line-height: 1.4em;">
定制化开发，公司培训，技术支持
合作电话：010-52897073
邮箱：support@dwzjs.com
</pre>
							</div>
						</div>

					</div>
				</div>
			</div>

		</div>

		<div id="footer">
			Copyright &copy; 2010
			<a href="www.360keke.com" target="dialog">PEDS</a> Tel：13078553360
		</div>
	</body>
</html>