<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@page import="cn.com.peds.common.GlobalContants"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	if (application.getAttribute("ProjectName") == null) {
		application.setAttribute("ProjectName", "私人安全助手");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title><%=application.getAttribute("ProjectName")%></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<link rel="stylesheet" type="text/css" href="css/login.css">
		<script src="js/jquery/jquery-1.6.min.js" type="text/javascript">
</script>
		<script src='dwr/engine.js'>
</script>
		<script src='dwr/util.js'>
</script>
		<script type="text/javascript">
function init() { //让本窗口不被其它窗口包含
	if (window.top.location != window.self.location)
		window.top.location = window.self.location;
}
function submit() {
	//if(authmode == '2') document.logonform.action = "LoginAction.do?check=false";
	//else document.logonform.action = "LoginAction.do";
	//alert("提交：" + document.logonform.action);
	//return;

	document.logonform.submit();
}

function isNotNull() {
	var sms_login_name = document.getElementById("login_name");
	var sms_password = document.getElementById("password");
	if (sms_login_name.value == "") {
		alert("登录名不能为空");
		sms_login_name.focus();
		return false;
	}
	return true;
}
</script>
	</head>
	<body onload="init()">
		<div class="login_input">
			<form name="logonform" method="post" action="loginAction.do">
				<table width="512" height="186" border="0" cellspacing="0">
					<tr height="20">
						<td colspan="3" align="center" valign="bottom">
							<%
								if (request.getAttribute(GlobalContants.ALERT_MESSAGE) != null) {
							%>
							<img src="<%=request.getContextPath()%>/img/login/error.png" />
							<span class="info_error"> <%=request.getAttribute(GlobalContants.ALERT_MESSAGE)%></span>
							<%
								}
							%>
						</td>
					</tr>
					<tr>
						<td class="column">
							用户名:
						</td>
						<td colspan="1">
							<span class="text01"><input name="login_name" type="text"
									class="input"
									value="<%=request.getParameter("login_name") == null ? "" : request
					.getParameter("login_name")%>" />
							</span>
						</td>
						<td>
							<a href="#" onclick="selfService();">申请账号</a>
						</td>
					</tr>
					<tr>
						<td class="column">
							密&nbsp;&nbsp;码:
						</td>
						<td>
							<span class="text02"><input id="password" name="password"
									type="password" class="input" /> </span>
						</td>
						<td width="187">
							<a href="forget.jsp?pg=index">忘记密码？</a>
						</td>
					</tr>
					<tr>
						<td colspan="3" align="right" valign="bottom">
							<input id="login" name="login" type="button" class="btn"
								onclick="submit();" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="logo">
		</div>
	</body>
</html>



