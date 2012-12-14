<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/global.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<title><%=application.getAttribute("ProjectName")%></title>
	</head>
	<frameset rows="117,*" cols="*" frameborder="no" border="0"
		framespacing="0">
		<frame src="${contentpath}menutree.jsp" name="topFrame"
			scrolling="No" noresize="noresize" id="topFrame" />
		<frame
			src="<%=request.getContextPath()%>/fileMgrAction.do?method=normal_index_buttom"
			name="buttomFrame" id="buttomFrame" />
	</frameset>
	<noframes>
	</noframes>
</html>