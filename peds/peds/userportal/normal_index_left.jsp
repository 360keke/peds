<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<META HTTP-EQUIV="Pragma" content="No-cache">
<META HTTP-EQUIV="Cache-Control" content="no-cache">
<META HTTP-EQUIV="Expires" content=0>
<title><%=application.getAttribute("ProjectName")%></title>
<link  rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/css/main.css" >
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script language="javaScript" src="<%=request.getContextPath()%>/js/dom-drag.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
	/*jQuery(document).ready(function($){
		$(".flex_btn").hover(function() {
			$(this).css("background","url(<%=request.getContextPath()%>/style/img/home/flex_btn_right.jpg) no-repeat 0 -34px");
		},function(){
			$(this).css("background","url(<%=request.getContextPath()%>/style/img/home/flex_btn_right.jpg) no-repeat");
		});
		$(".flex_btn02").hover(function() {
			$(this).css("background","url(<%=request.getContextPath()%>/style/img/home/flex_btn_left.jpg) no-repeat 0 -34px");
		},function(){
			$(this).css("background","url(<%=request.getContextPath()%>/style/img/home/flex_btn_left.jpg) no-repeat");
		});
		});*/
	function getDatas(id,num,all){ 
		for(var i=0;i<all;i++){	 
			if(num==i){
				document.getElementById('li_'+num).className="menu_on";	 	
			}else{ 
				document.getElementById('li_'+i).className="menu_off";		
			}
		}
		parent.parent.document.all.mainFrame.src="<%=request.getContextPath()%>/AppSourceList.do?actionType=normalIndexRight&initMask=true&id="+id;
   		
    }
    //隐藏或是展开左边的树形结构
    var hintType = true;
    function leftHint(obj){
    	if(hintType){
    		document.all.flexBtn.className="flex_btn";
    		document.all.leftBg.style.display = "none";
    		window.parent.frames['mainTitle'].cols="8,*";
    		hintType = false;
    	}else{
    		document.all.flexBtn.className="flex_btn02";
    		document.all.leftBg.style.display = "";
    		window.parent.frames['mainTitle'].cols="177,*";
    		hintType = true;
    	}
    }
    //加载初始化样式
    function initClassName(){
    	var count = "${count}";
    	if(count > 0){
    		document.getElementById("li_0").className = "menu_on";
    	}
    }
</script>
</head>
<body onLoad="initClassName();">
	<div id="leftBg" name="leftBg" class="left_bg">
		<ul>
			<html:present name="sourceList" scope="request">
			<html:vectorobject id="source" name="sourceList" scope="request">
			<html:databean id="source" name="source">
	        	<li title="<html:show name="source" type="String" property="codeName"/>" class="menu_off" id="li_<html:show name="source" type="int" property="num"/>">
	        		<a href="#" onclick="getDatas(<html:show name="source" type="String" property="code"/>,<html:show name="source" type="int" property="num"/>,${count });">
	                   	<html:show name="source" type="String" property="codeName"/>
	        		</a>
	        	</li>
	        	<li class="menu_line"></li>
        	</html:databean>
	     	</html:vectorobject>
	     	</html:present>
     	</ul>
	</div>
	<div class="left_flex">
		 <div class="flex_btn02" id="flexBtn" name="flexBtn" onclick="leftHint(this);"></div>
	</div>
</body>
</html>