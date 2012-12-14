<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="cn.com.peds.derby.DataStore"%>
<%@page import="cn.com.peds.common.dao.SysParamDao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@ include file="/global.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<title><%=application.getAttribute("ProjectName")%></title>
		<link rel="stylesheet" href="<%=contentpath%>css/processbar.css"
			type="text/css" media="screen" />
		<link rel="stylesheet" href="<%=contentpath%>css/main.css"
			type="text/css" media="screen" />
		<script type="text/javascript" src="<%=contentpath%>js/processbar.js">
</script>
		<script src='<%=contentpath%>dwr/interface/FileManager.js'>
</script>
		<script src="<%contentpath %>lib/ligerUI/js/plugins/ligerGrid.js"
			type="text/javascript">
</script>
		<script type="text/javascript">

var PedsFiles = FileManager.getAllFiles('-1');
alert(PedsFiles);
$(f_initGrid);
var manager, g;
function f_initGrid() {
g = manager = $("#maingrid").ligerGrid( {
		columns : [ {
			display : '文件名',
			name : 'filename',
			width : 50,
		}, {
			display : '创建时间',
			name : 'createtime',
		},
		onSelectRow : function(rowdata, rowindex) {
			$("#txtrowindex").val(rowindex);
		},
		enabledEdit : true,
		clickToEdit : false,
		isScroll : false,
		data : EmployeeData,
		width : '100%'
	});
}
function beginEdit() {
	var row = manager.getSelectedRow();
	if (!row) {
		alert('请选择行');
		return;
	}
	manager.beginEdit(row);
}
function cancelEdit() {
	var row = manager.getSelectedRow();
	if (!row) {
		alert('请选择行');
		return;
	}
	manager.cancelEdit(row);
}
function cancelAllEdit() {
	manager.cancelEdit();
}
function endEdit() {
	var row = manager.getSelectedRow();
	if (!row) {
		alert('请选择行');
		return;
	}
	manager.endEdit(row);
}
function endAllEdit() {
	manager.endEdit();
}
function deleteRow() {
	manager.deleteSelectedRow();
}
var newrowid = 100;
function addNewRow() {
	manager.addEditRow();
}

function getSelected() {
	var row = manager.getSelectedRow();
	if (!row) {
		alert('请选择行');
		return;
	}
	alert(JSON.stringify(row));
}
function getData() {
	var data = manager.getData();
	alert(JSON.stringify(data));
}
</script>
	</head>
	<body style="padding: 10px">
		<div class="l-clear"></div>
		<div id="maingrid" style="margin-top: 20px"></div>
		<br />
		<br />
		<a class="l-button" style="width: 120px" onclick="getSelected()">获取选中的值(选择行)</a>

		<br />
		<a class="l-button" style="width: 120px" onclick="getData()">获取当前的值</a>
		<div style="display: none;">
			<!-- g data total ttt -->
		</div>
	</body>
	<!--  
	<body>
		<div style="margin-left: 100px;">
			<table width="80%">
				<tr>
					<td align="right">
						<input type="text" value="" name="search" />
					</td>
					<td align="left">
						<img style="cursor: hand;" src="images/icon_bt_search.png"
							onclick="javascript:">
					</td>
				</tr>
			</table>
		</div>
		<div style="margin-left: 100px;">
			<table border="1" width="80%">
				<tr>
					<td>
						文件名
					</td>
					<td>
						创建时间
					</td>
					<td>
						动作
					</td>
				</tr>
				<%
					DataStore files = (DataStore) request.getAttribute("files");
					if (files != null) {
						for (int i = 0; i < files.rowCount(); i++) {
				%>
				<tr id="filediv<%=files.getInt(i, "id")%>" style="display: inline">
					<td><%=files.getString(i, "filename")%></td>
					<td><%=files.getString(i, "createtime")%></td>
					<td>
						<div id="decrypt<%=files.getInt(i, "id")%>">
							<%if(Integer.parseInt(files.getString(i,"isencrypt"))==0){ %>
							<a
								href="javascript:ajaxencryptFile('<%=files.getInt(i, "id")%>','<%=contentpath%>fileMgrAction.do','encrypt')"
								name="decrypt_a" class="decrypt_b">加密</a>
							<%} %>
							<a
								href="javascript:ajaxDecryptFile('<%=files.getInt(i, "id")%>','<%=contentpath%>fileMgrAction.do','decrypt')"
								name="decrypt_a" class="decrypt_b">解密</a>
														
							<a	href="javascript:openFile('<%=files.getInt(i, "id")%>','<%=contentpath%>fileMgrAction.do','browser')" name="decrypt_a" class="decrypt_b">查看</a>
							<a	href="javascript:ajaxDecryptFile('<%=files.getInt(i, "id")%>','<%=contentpath%>fileMgrAction.do','del')"
								name="decrypt_a" class="decrypt_b">删除明文文件</a>
						</div>
						<img src="<%=request.getContextPath()%>/img/loadingdecrypt.gif"
							style="display: none" id="waitbar<%=files.getInt(i, "id")%>" />

					</td>
				</tr>
				<%
					}
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
		</div>
	</body>
	-->
</html>