<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="cn.com.peds.login.bean.UserBean"%>
<%@page import="cn.com.peds.login.LoginBo"%>
<%
	UserBean bean = (UserBean) request.getSession().getAttribute(LoginBo.SESSION_LOGIN_USERBEAN);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title><%=application.getAttribute("ProjectName")%></title>
	<%@ include file="/global.jsp"%>
	<link rel="stylesheet" href="<%=contentpath %>css/menutree.css"
		type="text/css" media="screen, projection" />
	<link rel="stylesheet" type="text/css"
		href="<%=contentpath %>css/menutreeie7.css" media="screen" />
	<script type="text/javascript" language="javascript"
		src="<%=contentpath %>js/jquery.menutree.js">
	</script>
	<script type="text/javascript" language="javascript">
function go(loc) {
	var frame = parent.document.getElementById("buttomFrame");
	if (loc == 1) {
		frame.src = "<%=contentpath%>fileMgrAction.do?method=initBat";
	}
}
</script>
</head>
<body>
	<div id="page-wrap">
		<ul class="dropdown">
			<li>
				<%
					if (bean.getRole() == 0) {
				%>
				<a href="#">管理员入口</a>
				<ul class="sub_menu">
					<li>
						<a href="javascript:go('1');">批量处理文件</a>
					</li>
					<li>
						<a href="#">文件加密</a>
					</li>
					<li>
						<a href="#">分类管理</a>
					</li>
					<li>
						<a href="#">本地程序注册</a>
					</li>
				</ul>
			</li>
			<%
				}
			%>
			<li>
				<a href="#">个人信息修改</a>
			</li>
			<li>
				<a href="#">注销</a>
			</li>
		</ul>
	</div>
	<h1></h1>
</body>
</html>