<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<form method="post" action="${path }/fileMgr/fileScanner.htm"
	class="pageForm required-validate" validate="require-validate"
	onsubmit="return validateCallback(this, navTabAjaxDone);">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="97">
			<div class="tabs">
				<div class="tabsHeader">
					<div class="tabsHeaderContent">
						<ul>
							<li class="selected">
								<a href="javascript:void(0)"><span>扫描目录</span> </a>
							</li>
							<li>
								<a href="javascript:void(0)"><span>扫描文件</span> </a>
							</li>
						</ul>
					</div>
				</div>
				<div class="tabsContent" style="height: 300px;">
					<div>
						<table class="list nowrap itemDetail" addButton="添加目录"
							width="100%">
							<thead>
								<tr>
									<th type="lookup" name="dir[#index#].name"
										lookupGroup="dir[#index#]"
										lookupUrl="${path }/fileMgr/treeRoot.htm"
										suggestUrl="${path }/fileMgr/treeRoot.htm"
										suggestFields="name" postField="keywords" size="130"
										fieldClass="required" width="90%">
										目录名称
									</th>
									<th type="del" width="60" align="center">
										操作
									</th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
					</div>
					<div>
						<table class="list nowrap itemDetail" addButton="添加文件"
							width="100%">
							<thead>
								<tr>
									<th type="lookup" name="file[#index#].name"
										lookupGroup="file[#index#]"
										lookupUrl="${path }/fileMgr/treeRoot.htm" lookupPk="name"
										suggestUrl="${path }/fileMgr/treeRoot.htm"
										suggestFields="name" size="130" width="90%">
										文件路径
									</th>
									<th type="del" width="60" align="center">
										操作
									</th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
					</div>
				</div>
				<div class="tabsFooter">
					<div class="tabsFooterContent"></div>
				</div>
			</div>
			<div class="formBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">
									开始
								</button>
							</div>
						</div>
					</li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="button" class="close">
									取消
								</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
</form>
