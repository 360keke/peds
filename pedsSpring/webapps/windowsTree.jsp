<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<!DOCTYPE html>
<HTML>
	<HEAD>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<!-- zTree 引用 -->
<link rel="stylesheet" href="${ path}/zTree/css/zTreeStyle/zTreeStyle.css"	type="text/css">
<script type="text/javascript"	src="${ path}/zTree/js/jquery.ztree.core-3.5.js"></script>
<!-- end zTree 引用 -->
<style>
div.content_wrap {
	width: 600px;
	height: 380px;
}

div.content_wrap div.left {
	float: left;
	width: 450px;
}

div.content_wrap div.right {
	float: right;
	width: 340px;
}

div.zTreeDemoBackground {
	width: 450px;
	height: 362px;
	text-align: left;
}

ul.ztree {
	margin-top: 10px;
	border: 1px solid #617775;
	background: #f0f6e4;
	width: 450px;
	height: 360px;
	overflow-y: scroll;
	overflow-x: auto;
}

ul.log {
	border: 1px solid #617775;
	background: #f0f6e4;
	width: 300px;
	height: 170px;
	overflow: hidden;
}

ul.log.small {
	height: 45px;
}

ul.log li {
	color: #666666;
	list-style: none;
	padding-left: 10px;
}

ul.log li.dark {
	background-color: #E3E3E3;
}

/* ruler */
div.ruler {
	height: 20px;
	width: 220px;
	background-color: #f0f6e4;
	border: 1px solid #333;
	margin-bottom: 5px;
	cursor: pointer
}

div.ruler div.cursor {
	height: 20px;
	width: 30px;
	background-color: #3C6E31;
	color: white;
	text-align: right;
	padding-right: 5px;
	cursor: pointer
}

</style>

		<SCRIPT type="text/javascript">
		var setting = {
			view: {
				selectedMulti: false
			},
			async: {
				enable: true,
				url:"${path}/fileMgr/ajaxWindowsTree.htm",
				autoParam:["id", "name", "level","path"],
				otherParam:{"otherParam":"zTreeAsyncTest"},
				dataFilter: filter
			},
			check: {
				enable: true
			},
			view: {
				expandSpeed: ""
			},
			callback: {
				beforeAsync: beforeAsync,
				onAsyncError: onAsyncError,
				onAsyncSuccess: onAsyncSuccess,
				onClick:mouseClick
			}
		};
        function mouseClick(e,treeId,treeNode){
            var path =treeNode.path;
            var json = ''+path+'';
            if(path ==''){
               alert('不是有效的文件或者目录!');
            }else{
            $.bringBack({name:json})
            }
        }
		function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			for (var i=0, l=childNodes.length; i<l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}
		function beforeClick(treeId, treeNode) {
			if (treeNode.path == '') {
				alert("不是有效文件或目录！");
				return false;
			} else {
				return true;
			}
		}
		var log, className = "dark";
		function beforeAsync(treeId, treeNode) {
			className = (className === "dark" ? "":"dark");
			showLog("[ "+getTime()+" beforeAsync ]&nbsp;&nbsp;&nbsp;&nbsp;" + ((!!treeNode && !!treeNode.name) ? treeNode.name : "root") );
			return true;
		}
		function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
			showLog("[ "+getTime()+" onAsyncError ]&nbsp;&nbsp;&nbsp;&nbsp;" + ((!!treeNode && !!treeNode.name) ? treeNode.name : "root") );
		}
		function onAsyncSuccess(event, treeId, treeNode, msg) {
			showLog("[ "+getTime()+" onAsyncSuccess ]&nbsp;&nbsp;&nbsp;&nbsp;" + ((!!treeNode && !!treeNode.name) ? treeNode.name : "root") );
		}
		
		function showLog(str) {
			if (!log) log = $("#log");
			log.append("<li class='"+className+"'>"+str+"</li>");
			if(log.children("li").length > 8) {
				log.get(0).removeChild(log.children("li")[0]);
			}
		}
		function getTime() {
			var now= new Date(),
			h=now.getHours(),
			m=now.getMinutes(),
			s=now.getSeconds(),
			ms=now.getMilliseconds();
			return (h+":"+m+":"+s+ " " +ms);
		}

		function refreshNode(e) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			type = e.data.type,
			silent = e.data.silent,
			nodes = zTree.getSelectedNodes();
			if (nodes.length == 0) {
				alert("请先选择一个父节点");
			}
			for (var i=0, l=nodes.length; i<l; i++) {
				zTree.reAsyncChildNodes(nodes[i], type, silent);
				if (!silent) zTree.selectNode(nodes[i]);
			}
		}

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting,${roots});
			//$("#refreshNode").bind("click", {type:"refresh", silent:false}, refreshNode);
			//$("#refreshNodeSilent").bind("click", {type:"refresh", silent:true}, refreshNode);
			//$("#addNode").bind("click", {type:"add", silent:false}, refreshNode);
			//$("#addNodeSilent").bind("click", {type:"add", silent:true}, refreshNode);
		});
	</SCRIPT>
	</HEAD>
	<BODY>
		<div class="content_wrap" style="width:100%;">
			<div class="zTreeDemoBackground left">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
		</div>
	</BODY>
</HTML>