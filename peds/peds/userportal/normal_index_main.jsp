<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>个人媒体助手</title>
    <%@include file="/global.jsp" %>
        <script type="text/javascript">
            var tab = null;
            var accordion = null;
            var tree = null;
            $(function ()
            {
                //布局
                $("#layout1").ligerLayout({ leftWidth: 190, height: '100%', onHeightChanged: f_heightChanged });

                var height = $(".l-layout-center").height();

                //Tab
                $("#framecenter").ligerTab({ height: height });

                //面板
                $("#accordion1").ligerAccordion({ height: height - 24, speed: null });

                $(".l-link").hover(function ()
                {
                    $(this).addClass("l-link-over");
                }, function ()
                {
                    $(this).removeClass("l-link-over");
                });
                //树
                $("#tree1").ligerTree({
                    checkbox: false,
                    slide: false,
                    nodeWidth: 120,
                    attribute: ['nodename', 'url'],
                    onSelect: function (node)
                    {
                        if (!node.data.url) return;
                        var tabid = $(node.target).attr("tabid");
                        if (!tabid)
                        {
                            tabid = new Date().getTime();
                            $(node.target).attr("tabid", tabid)
                        }
                        /*if ($(">ul >li", tab.tab.links).length >= 4)
                        {
                            var currentTabid = $("li.l-selected", tab.tab.links).attr("tabid"); //当前选择的tabid
                            if (currentTabid == "home") return;
                            tab.overrideTabItem(currentTabid, { tabid: tabid, url: node.data.url, text: node.data.text });
                            return;
                        }*/
                        f_addTab(tabid, node.data.text, node.data.url);
                    }
                });

                tab = $("#framecenter").ligerGetTabManager();
                accordion = $("#accordion1").ligerGetAccordionManager();
                tree = $("#tree1").ligerGetTreeManager();
                $("#pageloading").hide();

            });
            function f_heightChanged(options)
            {
                if (tab)
                    tab.addHeight(options.diff);
                if (accordion && options.middleHeight - 24 > 0)
                    accordion.setHeight(options.middleHeight - 24);
            }
            function f_addTab(tabid,text, url)
            { 
                tab.addTabItem({ tabid : tabid,text: text, url: url });
            } 
     </script> 
<style type="text/css"> 
        body,html{height:100%;}
    body{ padding:0px; margin:0;   overflow:hidden;}  
    .l-link{ display:block; height:26px; line-height:26px; padding-left:10px; text-decoration:underline; color:#333;}
    .l-link2{text-decoration:underline; color:white;}
    .l-layout-top{background:#102A49; color:White;}
    .l-layout-bottom{ background:#E5EDEF; text-align:center;}
    #pageloading{position:absolute; left:0px; top:0px; background:white url('loading.gif') no-repeat center; width:100%; height:100%;z-index:99999;}
    .l-link{ display:block; line-height:22px; height:22px; padding-left:20px;border:1px solid white; margin:4px;}
    .l-link-over{ background:#FFEEAC; border:1px solid #DB9F00;}
    
    .l-winbar{ background:#2B5A76; height:30px; position:absolute; left:0px; bottom:0px; width:100%; z-index:99999;}
 </style>
</head>
<body style="padding:0px;">  
<div id="pageloading"></div> 
  <div id="layout1" style="width:100%">
        <div position="top" style="background:#102A49; color:White; ">
            <div style="margin-top:10px; margin-left:10px">
            personal_E_docs
            </div>
        </div>
        <div position="left"  title="主要菜单" id="accordion1"> 
                     <div title="功能列表" class="l-scroll">
                         <ul id="tree1" style="margin-top:3px;">
                            <li isexpand="false"><span>文件管理</span><ul> 
                                <li url="demos/base/resizable.htm"><span>批量文件导入</span></li>
                                <li url="demos/base/drag.htm"><span>文件加密</span></li>
                                <li url="demos/base/drag2.htm"><span>文件分类管理</span></li>
                            </ul></li>
                            <li isexpand="false"><span>分类管理</span><ul>
                                <li url="demos/dialog/dialogAll.htm"><span>新建分类</span></li>
                                <li url="demos/dialog/dialogTarget.htm"><span>分类管理</span></li>
                            </ul></li>
                            <li isexpand="false"><span>本地软件注册</span><ul>
                                <li url="demos/menu/menu.htm"><span>注册本地程序</span></li>
                                <li url="demos/menu/evenmenu.htm"><span>自动搜索可用程序</span></li>
                            </ul></li>
                         </ul>
                    </div>
        </div>
        <div position="center" id="framecenter"> 
            <div tabid="home" title="我的主页" style="height:300px" >
                <iframe frameborder="0" name="home" id="home" src="<%=request.getContextPath()%>/fileMgrAction.do?method=normal_index_buttom"></iframe>
            </div> 
        </div> 
        <div position="bottom" style=" padding-top:5px;">
        </div>
    </div>  
        <div style="display:none">
    </div>
</body>
</html>
