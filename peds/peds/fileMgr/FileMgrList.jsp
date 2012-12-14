<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="cn.com.peds.derby.DataStore"%>
<%@page import="cn.com.peds.common.dao.SysParamDao"%>
<%@include file="/global.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

	</head>
	<script src='<%=contentpath%>/dwr/interface/FileManager.js'>
</script>
	<script src='<%=contentpath%>/dwr/engine.js'>
</script>
	<script src='<%=contentpath%>/dwr/util.js'>
</script>
	<script type="text/javascript">
function listFile(filepath, filterrule) {
	FileManager.list(filepath, filterrule);
}
</script>
	<body>
		<table>
			<tr>
				<td>
					文件名
				</td>
				<td>
					创建时间
				</td>
			</tr>
			<%
				DataStore files = (DataStore) request.getAttribute("files");
				for (int i = 0; i < files.rowCount(); i++) {
			%>
			<tr >
				<td><%=files.getString(i, "filename")%></td>
				<td><%=files.getString(i, "createtime")%></td>
				
			</tr>
			<%
				}
			%>
			<tr>
				<td>
					<%
						String filterRule = SysParamDao.getSysParamValue("filterrule");
						String filepath = "c:/temp";
					%>
					<input type="button" value="处理文件"
						onclick="javascript:listFile('<%=filepath%>','<%=filterRule%>');" />
				</td>
			</tr>
		</table>
	</body>
</html>
