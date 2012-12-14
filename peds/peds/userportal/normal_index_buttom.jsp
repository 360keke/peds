<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="cn.com.peds.derby.DataStore"%>
<%@page import="cn.com.peds.common.dao.SysParamDao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<title><%=application.getAttribute("ProjectName")%></title>
				<%@ include file="/global.jsp"%>
		<link rel="stylesheet" href="<%=contentpath%>css/processbar.css"
			type="text/css" media="screen" />
		<link rel="stylesheet" href="<%=contentpath%>css/main.css"
			type="text/css" media="screen" />
		<script type="text/javascript" src="<%=contentpath%>js/processbar.js">
</script>
		<script src='<%=contentpath%>dwr/interface/FileManager.js'>
</script>
		<script src="<%=contentpath%>lib/ligerUI/js/plugins/ligerGrid.js"
			type="text/javascript">
</script>
<script type="text/javascript" src="<%=contentpath %>js/EmployeeData.js"></script>
		<script type="text/javascript">
DWREngine.setAsync(false);
var PedsFiles='{Rows:[{"filename":"yudabo","createtime":2011-01-11},{"filename":"yudabo","createtime":2011-01-11}],Total:2}';
//FileManager.getAllFiles('-1', function(result) {
	//PedsFiles = result;
//});
alert(PedsFiles);
PedsFiles = PedsFiles.Rows;
$(f_initGrid);
var manager, g;
function f_initGrid() {
	g = manager = $("#maingrid").ligerGrid( {
		columns : [{
			display : '文件名',
			name : 'filename',
			width : 50
		}, {
			display : '创建时间',
			name : 'createtime'
		} ],
		onSelectRow : function(rowdata, rowindex) {
			$("#txtrowindex").val(rowindex);
		},
		enabledEdit : true,
		clickToEdit : false,
		isScroll : false,
		data : PedsFiles,
		width : '100%'
	});
}
function getData() {
	alert(PedsFiles);
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
		<a class="l-button" style="width: 120px"
			onclick="alert('1');getData()">获取当前的值</a>
		<div style="display: none;">
			<!-- g data total ttt -->
		</div>
	</body>
</html>