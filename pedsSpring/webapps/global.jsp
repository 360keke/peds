<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
	if (application.getAttribute("ProjectName") == null) {
		application.setAttribute("ProjectName", "私人安全助手");
	}
%>

