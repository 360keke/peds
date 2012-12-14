<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<html>
	<head>
	</head>
	<body>
		<div class="pageContent">
			<form method="post" action="${path }/catMgr/add.htm"
				class="pageForm required-validate" validate="require-validate"
				onsubmit="return validateCallback(this, navTabAjaxDone);">
				<input type="hidden" name="type" id="type" value="0"/> 
				<div class="pageFormContent" layoutH="56">
					<p>
						<label>
							分类名称：
						</label>
						<input name="name" class="required" type="text" size="30" value="" />
					</p>
					<p>
						<label>
							上级分类名称：
						</label>
						<input type="hidden" name="parentId" value="" title="不设置，此分类将作为一级分类"/><span></span>
						<input type="text" name="parentName"
							value="" suggestFields="parentId,parentName"
							suggestUrl="${path }/catMgr/lookUp.htm?catalogId="
							lookupGroup="" size="30" />
						<a class="btnLook" href="${path }/catMgr/lookUp.htm?catalogId="
							lookupGroup="">查找带回</a>
					</p>
				</div>
				<div class="formBar">
					<ul>
						<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
						<li>
							<div class="buttonActive">
								<div class="buttonContent">
									<button type="submit">
										保存
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
			</form>
		</div>
	</body>
</html>
