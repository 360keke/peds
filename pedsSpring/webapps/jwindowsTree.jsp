<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<!DOCTYPE html>
<HTML>
	<!-- // 引入 jQuery框架文件 -->
	<script type="text/javascript" src="${path }/jtree/jquery-1.4.2.min.js"></script>

	<!-- // 引入 jQuery.TreeView 树形菜单插件文件 -->
	<script type="text/javascript"
		src="${path }/jtree/treeview/js/jQuery.tree.js"></script>
	<link rel="stylesheet" type="text/css"
		href="${path }/jtree/treeview/jQuery.tree.css" />

	<!-- // 装入树形菜单数据 -->
	<script type="text/javascript" src="${path }/jtree/tree2.js"></script>
	<HEAD>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">

		<SCRIPT type="text/javascript">
		var o = {  
            showcheck: true,  
            //url: "http://jscs.cloudapp.net/ControlsSample/GetChildData";  
            animate:true,  
            cbiconpath: "${path}/jtree/images/icons/", //checkbox icon的目录位置  
            //icons: ["checkbox_0.gif", "checkbox_1.gif", "checkbox_2.gif"],  
            emptyiconpath:"${path}/jtree/images/s.gif", //checkbxo三态的图片  
            theme: "bbit-tree-lines", //bbit-tree-lines, bbit-tree-no-lines, bbit-tree-arrows  
            onnodeclick:function(item){alert(item.text);}  
        };  
        o.data = treedata;  
        $("#tree").treeview(o);  
    });  
    var dfop =  
            {  
                method: "POST", //默认采用POST提交数据  
                datatype: "json", //数据类型是json  
                url: ${path}/fileMgr/treeRoot.htm, //异步请求的url  
                cbiconpath: "${path}/jtree/images/icons/", //checkbox icon的目录位置  
                icons: ["checkbox_0.gif", "checkbox_1.gif", "checkbox_2.gif"],  
                emptyiconpath:"${path}/jtree/images/s.gif", //checkbxo三态的图片  
                showcheck: false, //是否显示checkbox  
                oncheckboxclick: false, //当checkstate状态变化时所触发的事件，但是不会触发因级联选择而引起的变化  
                onnodeclick: false, // 触发节点单击事件  
                cascadecheck: true, //是否启用级联，默认启用  
                data: ${roots}, //初始化数据  
                clicktoggle: true, //点击节点展开和收缩子节点  
                theme: "bbit-tree-arrows" //三种风格备选：bbit-tree-lines ,bbit-tree-no-lines,bbit-tree-arrows  
            };  
        function mouseClick(e,treeId,treeNode){
            var path =treeNode.path;
            var json = ''+path+'';
            alert(json);
            $.bringBack({name:json})
        }
	</SCRIPT>
	</HEAD>
	<BODY>
		<div class="content_wrap" style="width: 400px;">
		<div id="tree"></div> 
		</div>
	</BODY>
</HTML>