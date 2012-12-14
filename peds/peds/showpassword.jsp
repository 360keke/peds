<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!DOCTYPE html>
<html>
	<head>
		<%@include file="global.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="<%=contentpath%>css/main.css"
			type="text/css" media="screen" />
		<script lang="javascript">
function OnClose(check) {
	if (check == true) {
		var value = document.getElementById("password").value;
		window.returnValue = value;
	}
	window.close();
}
</script>
	</head>
	<body scroll="no">
		<table>
			<tr>
				<td>
					密码:&nbsp;
					<input type="password" id="password" size="6" maxlength="20" width="50px;" />
					&nbsp;
					<a href="javascript:OnClose(true)">确认</a>
				</td>
			</tr>
		</table>
	</body>
</html>
