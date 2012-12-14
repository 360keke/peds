<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String contentpath = path + "/";
	request.setAttribute("contentpath", contentpath);
	if (application.getAttribute("ProjectName") == null) {
		application.setAttribute("ProjectName", "私人安全助手");
	}
%>

