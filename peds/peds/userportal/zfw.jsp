<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/style/style.css" type="text/css">
<html>
  <head>
  <%@ include file="/global.jsp" %>
    <title>自服务</title>
    <style type="text/css">
	<!--
	.bord {
		border: thin solid #94badf;
	}
	-->
	</style>
    <script language="javascript" >
		function a(b) {
			var frame = document.getElementById("mainiFrame");
			var a1 = document.getElementById("a1");
			var a2 = document.getElementById("a2");
			var a3 = document.getElementById("a3");
			
			var b1 = document.getElementById("b1");
			var b2 = document.getElementById("b2");
			var b3 = document.getElementById("b3");
			
			var c1 = document.getElementById("c1");
			var c2 = document.getElementById("c2");
			var c3 = document.getElementById("c3");
			if(b == 1) {
				frame.src = "<%=basePath %>NormalUser.do?method=loadupdateup";
				a1.src = "<%=request.getContextPath() %>/images/10.jpg";
				a2.background = "<%=request.getContextPath() %>/images/11.jpg";
				a3.src = "<%=request.getContextPath() %>/images/13.jpg";
				
				c1.src = "<%=request.getContextPath() %>/images/14.jpg";
				c2.background = "<%=request.getContextPath() %>/images/15.jpg";
				c3.src = "<%=request.getContextPath() %>/images/16.jpg";
			}else if(b == 2){
				frame.src = "<%=basePath %>portal/userportal/normal_peizhi.jsp";
				a1.src = "<%=request.getContextPath() %>/images/14.jpg";
				a2.background = "<%=request.getContextPath() %>/images/15.jpg";
				a3.src = "<%=request.getContextPath() %>/images/16.jpg";
				
				c1.src = "<%=request.getContextPath() %>/images/14.jpg";
				c2.background = "<%=request.getContextPath() %>/images/15.jpg";
				c3.src = "<%=request.getContextPath() %>/images/16.jpg";
			}else if(b == 3) {
				frame.src = "<%=basePath %>PortalManageListAction.do";
				c1.src = "<%=request.getContextPath() %>/images/10.jpg";
				c2.background = "<%=request.getContextPath() %>/images/11.jpg";
				c3.src = "<%=request.getContextPath() %>/images/13.jpg";

				a1.src = "<%=request.getContextPath() %>/images/14.jpg";
				a2.background = "<%=request.getContextPath() %>/images/15.jpg";
				a3.src = "<%=request.getContextPath() %>/images/16.jpg";
			}
		}
</script>
  </head>
  
  <BODY BGCOLOR=#FFFFFF LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0>
  <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
  	<tr>
    	<td>&nbsp;</td>
    	<td>&nbsp;</td>
  	</tr>
  	<tr>
    	<td width="48"><IMG SRC="<%=path %>/images/manger_index_11.jpg" WIDTH=48 HEIGHT=47 ALT=""></td>
    	<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      			<tr>
        			<td class="title">自服务信息</td>
      			</tr>
      			<tr>
        			<td height="1" background="<%=path %>/images/line2.gif"></td>
      			</tr>
    		</table>
    	</td>
  	</tr>
  </table>
  <table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="110">
          <table border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="3"><img id="a1" src="<%=request.getContextPath() %>/images/10.jpg" width="3" height="20"></td>
              <td id="a2" width="120" align="center" background="<%=request.getContextPath() %>/images/11.jpg"><a href="javascript:a(1)"><span class="title5">个人信息修改</span></a></td>
              <td width="14"><img id="a3" src="<%=request.getContextPath() %>/images/13.jpg" width="14" height="20"></td>
            </tr>
          </table>
        </td>
        <td width="150">
          <table border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="5"><img id="c1" src="<%=request.getContextPath() %>/images/14.jpg" width="5" height="20"></td>
              <td id="c2" width="100" align="center" background="<%=request.getContextPath() %>/images/15.jpg"><a href="javascript:a(3)"><span class="title5">portal显示管理</span></a></td>
              <td width="14"><img id="c3" src="<%=request.getContextPath() %>/images/16.jpg" width="14" height="20"></td>
            </tr>
          </table>
        </td>
        <td>&nbsp;</td>
      </tr>
      <tr>
      </tr>
    </table>
	<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
		 <tr class="liststyle1">
			<td>
				<iframe src="<%=basePath %>NormalUser.do?method=loadupdateup" id="mainiFrame" marginwidth=0 marginheight=0 frameborder=0 scrolling=no width="100%" onload="this.height=mainiFrame.document.body.scrollHeight" class="bord">
	
				</iframe>
			</td>
		</tr>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
  		<tr>
    		<td>&nbsp;</td>
  		</tr>
	</table>
</BODY>
</html>
