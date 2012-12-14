<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<%@include file="/include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=path%>">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>
	<body>
		<div id="main-div">
			<form id="pagerForm" method="post"
				action="${path }/fileMgr/search.htm">
				<input type="hidden" name="currentPage"
					value="${search.currentPage }">
				<input type="hidden" name="pageNum" value="${search.pageNum }"
					<input type="hidden" name="orderField" value="${search.orderField}" />
					<input type="hidden" name="orderDirection" value="${search.orderDirection}" />
					<input type="hidden" name="totalCount" value="${search.totalCount }"/>
					<input type="hidden" name="numPerPage" value="${search.numPerPage }" />
			</form>
			<div class="pageHeader">
				<form rel="pagerForm" onsubmit="return navTabSearch(this);"
					action="${path }/fileMgr/search.htm" method="post">
					<div class="searchBar">
						<ul class="searchContent">
							<li>
								<label>
									文件名称：
								</label>
								<input type="text" name="fileName" value="${search.fileName }" />
							</li>
							<li>
								<label>
									所属分类：
								</label>
								<a class="btnLook" href="${path }/catMgr/lookUp.htm?catalogId="
									lookupGroup="">查找带回</a>
								<input type="hidden" name="catalogId" value="" />
								<input type="text" name="catalogName" value=""
									suggestFields="catalogId,catalogName
										suggestUrl="
									${path }/catMgr/lookUp.htm?catalogId="
										lookupGroup="" size="20" />
							</li>
							<li>
								<label>
									文件后缀：
								</label>
								<a class="btnLook" href="${path }/catMgr/lookUp.htm?catalogId="
									lookupGroup="">查找带回</a>
								<input type="text" name="suffixs" value=""
									suggestFields="suffixs"
									suggestUrl="${path }/catMgr/lookUp.htm?catalogId="
									lookupGroup="" size="20" />
							</li>
						</ul>
						<div class="subBar">
							<ul>
								<li>
									<div class="buttonActive">
										<div class="buttonContent">
											<button type="submit">
												查询
											</button>
										</div>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</form>
			</div>
			<div class="pageContent">
				<div class="panelBar">
					<ul class="toolBar">
						<li>
							<a class="add" href="${path }/fileMgr/gotoAdd.htm"
								target="navTab"><span>添加</span> </a>
						</li>
						<li>
							<a class="edit"
								href="${path }/fileMgr/gotoEdit.htm?catalogId={sid_file}"
								target="navTab" warn="请选择一个文件"><span>修改</span> </a>
						</li>
						<li>
							<a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids"
								href="${path }/fileMgr/del.htm" class="delete"><span>批量删除</span>
							</a>
						</li>
						<li class="line">
							line
						</li>
					</ul>
				</div>
				<table class="table" width="100%" layoutH="138">
					<thead>
						<tr>
							<th width="15">
								<input type="checkbox" group="ids" class="checkboxCtrl">
							</th>
							<th orderField="fileName" width="60">
								文件名称
							</th>
							<th width="60" orderField="catalogName">
								所属分类
							</th>
							<th width="60" orderField="length">
								文件大小
							</th>
							<th width="60" orderField="path">
								文件路径
							</th>
							<th width="60" orderField="updateTime">
								文件最后修改时间
							</th>
							<th width="60" orderField="createTime">
								文件扫描创建时间
							</th>
							<th width="80">
								操作
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="file" items="${fileList }">
							<tr target="sid_file" rel="${file.id }">
								<td width="22" align="center">
									<input name="ids" value="${file.id }" type="checkbox">
								</td>
								<td align="center">
									${file.fileName }
								</td>
								<td align="center">
									${file.catalogName }
								</td>
								<td align="center">
									${file.length }kb
								</td>
								<td align="center">
									${file.path }
								</td>
								<td align="center">
									${file.updateTime }
								</td>
								<td align="center">
									${file.createTime }
								</td>
								<td align="center">
									<a title="查看" target="dialog"
										href="${path }/fileMgr/gotoEdit.htm?ids=${file.id }"
										class="btnInfo">查看</a>
									<a title="编辑" target="dialog"
										href="${path }/fileMgr/gotoEdit.htm?ids=${file.id }"
										class="btnEdit">编辑</a>
									<a title="删除" target="ajaxTodo"
										href="${path }/fileMgr/del.htm?ids=${file.id }"
										class="btnDel">删除</a>
									<a title="加密" target="ajaxTodo"
										href="${path }/fileMgr/encryptFile.htm?ids=${file.id }"
										class="btnEncrypt">加密</a>
									<a title="解密" target="ajaxTodo"
										href="${path }/fileMgr/decryptFile.htm?ids=${file.id }"
										class="btnDecrypt">解密</a>
										<!-- 
									<a title="备份到网盘" target="ajaxTodo"
										href="${path }/catMgr/del.htm?catalogId=${catalog.id }"
										class="btnBackup">备份到网盘</a>
										 -->
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="panelBar">
					<div class="pages">
						<span>显示</span>
						<select class="combox" name="numPerPage"
							onchange="navTabPageBreak({numPerPage:this.value})" onkeyup="this.blur();this.focus();">
							<option value="10"
								<c:if test="${search.numPerPage ==10}">selected</c:if>>
								10
							</option>
							<option value="20"
								<c:if test="${search.numPerPage ==20}">selected</c:if>>
								20
							</option>
							<option value="30"
								<c:if test="${search.numPerPage ==30}">selected</c:if>>
								30
							</option>
							<option value="50"
								<c:if test="${search.numPerPage ==50}">selected</c:if>>
								50
							</option>
							<option value="100"
								<c:if test="${search.numPerPage ==100}">selected</c:if>>
								100
							</option>
							<option value="200"
								<c:if test="${search.numPerPage ==200}">selected</c:if>>
								200
							</option>
						</select>
						<span>条，共${search.totalCount}条</span>
					</div>
					<div class="pagination" targetType="navTab"
						totalCount="${search.totalCount }"
						numPerPage="${search.numPerPage }"
						pageNumShown="${search.pageNumShown }"
						currentPage="${search.currentPage }"></div>
				</div>
			</div>
		</div>
	</body>
</html>
