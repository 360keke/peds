<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@page
	import="cn.com.peds.common.StringUtils,cn.com.peds.common.DateUtil,java.util.Date"%>
<%@page import="cn.com.peds.login.bean.UserBean"%>
<%@page import="cn.com.peds.login.LoginBo"%>
<%
	String color = (String) request.getAttribute("color");
	if (color == null) {
		color = request.getParameter("color");
	}
	UserBean bean = (UserBean) request.getSession().getAttribute(LoginBo.SESSION_LOGIN_USERBEAN);
	//String name = LoginBo.getUserName(request);
	String name = bean.getName();
	String content = "今天是" + DateUtil.getNowDate() + "  " + StringUtils.TransNumToChinese(DateUtil.getWeekDayNum(new Date()));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/main.css">
		<script language="javaScript"
			src="<%=request.getContextPath()%>/js/img.js">
</script>
		<script type="text/javascript">
function browser_reg(url, factorx, factory, n) {
	if (!factorx)
		factorx = 1;
	if (!factory)
		factory = 1;
	var l = (factorx > 1) ? (screen.availWidth - factorx) / 2
			: screen.availWidth * (1 - factorx) / 2;
	var t = (factorx > 1) ? (screen.availHeight - factory) / 2
			: screen.availHeight * (1 - factory) / 2;
	var w = (factorx > 1) ? factorx : factorx * screen.availWidth;
	var h = (factory > 1) ? factory : factory * screen.availHeight;
	if (!n)
		n = "__popup";
	var bd = window
			.open(
					url,
					n,
					"left="
							+ l
							+ ",top="
							+ t
							+ ",width="
							+ w
							+ ",height="
							+ h
							+ ",location=0,titlebar=0,menubar=0,status=0,toolbar=0,scrollbars=1,resizable=1");
	bd.focus();
	bd.moveTo(l, t);
	bd.resizeTo(w, h);
	return bd;
}
//注册
function goto(){
		var clientIp = window.parent.frames['mainFrame'].document.all.dnsName.value;
        if ((clientIp==null)||("undefined"==clientIp)||(""==(clientIp))){
            clientIp="127.0.0.1"; 
        } 
        var url="<%=request.getContextPath()%>/ClientServerAutoLogin.do?method=list&clientIp="+clientIp;
        childflag=browser_reg(url,0.8,0.6,"reg");
	}
	//跳转至首页
	function homePage(){
		top.window.location.href="<%=request.getContextPath()%>/AppSourceList.do?color=<%=color%>";
	}
	//管理员配置
	function managerPortal(){
		top.window.location.href="<%=request.getContextPath()%>/default_index.jsp";
	}
	//注销
	function exit(){
		top.window.location.href="<%=request.getContextPath()%>/LogoutAction.do";
	}
	//个人设置
	function personalSetting(){
		top.document.all.mainFrame.src="<%=request.getContextPath()%>/selfServiceAction.do?method=loadAccForUpdate&masterId=yudabo";
		window.parent.frames['mainTitle'].cols="0,*";
	}
</script>
	</head>
	<body>
		<div class="top">
			<div class="top_right">
				
			</div>
		</div>
		<div class="nav">
			<div class="user"><%=name%>
				您好！
				<span><%=content%></span>
			</div>
		</div>
	</body>
</html>