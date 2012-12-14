<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
                       "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
			<%@include file="global.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>JQuery文件浏览器插件示例</title>
		<!-- 由 于css和图片没有放在css代码中可读的图片路径下,所在这里的css读取图片路径的代码改成适应本示例路径 -->
		<link type="text/css" rel="stylesheet" href="jqueryFileTree.css"
			mce_href="css/jqueryFileTree.css"></link>
		<style type="text/css" mce_bogus="1">
BODY,HTML {
	padding: 0px;
	margin: 0px;
}

BODY {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 11px;
	background: #EEE;
	padding: 15px;
}

H2 {
	font-family: Georgia, serif;
	font-size: 16px;
	font-weight: normal;
	margin: 0px 0px 10px 0px;
}

.example {
	float: center;
	margin: 215px;
}

.show {
	width: 250px;
	height: 600px;
	border-top: solid 1px #BBB;
	border-left: solid 1px #BBB;
	border-bottom: solid 1px #FFF;
	border-right: solid 1px #FFF;
	background: #FFF;
	overflow: scroll;
	padding: 5px;
}
</style>

		<script type="text/javascript" src="js/jquery/jquery.easing.1.3.js">
</script>
		<script type="text/javascript"
			src="js/jquery/jquery.easing.compatibility.js">
</script>
		<script type="text/javascript" src="js/jquery/jqueryFileTree.js">
</script>
		<script type="text/javascript" src="dwr/interface/FileManager.js">
</script>
		<script type="text/javascript">
$(document).ready(function() {
	$('#show').fileTree( {
		root : 'c:/',//指定加载文件的路径   
		script : 'jqueryFileTree.jsp',
		expandSpeed : 750,
		collapseSpeed : 750,
		multiFolder : false,
		expandEasing : 'easeOutBounce', //打开时的动画效果   
		collapseEasing : 'easeOutBounce',//关闭时的动画效果   
		loadMessage : '正在加载中...'
	}, function loadDoc(file) {
		DWREngine.setAsync(false);
		FileManager.openFile(file, function(flag) {
			if (flag == false) {
				alert('不支持的文件格式！');
			}
		})

	});
});
</script>
	</head>
	<body>
		<object id="fileDialog" width="0px" height="0px"
			classid="clsid:F9043C85-F6F2-101A-A3C9-08002B2F49FB"
			codebase="http://activex.microsoft.com/controls/vb5/comdlg32.cab">
		</object>
		<div class="example">
			<h2>
				JQuery文件浏览器插件示例1
			</h2>
			<div id="show" class="show"></div>
		</div>
	</body>
</html>
