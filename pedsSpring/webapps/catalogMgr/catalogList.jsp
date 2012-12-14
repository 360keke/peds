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
				action="${path }/catMgr/search.htm">
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
					action="${path }/catMgr/search.htm" method="post">
					<div class="searchBar">
						<ul class="searchContent">
							<li>
								<label>
									分类名称：
								</label>
								<input type="text" name="catalogName" value="${search.catalogName }" />
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
							<a class="add" href="${path }/catMgr/gotoAdd.htm" target="navTab"><span>添加</span>
							</a>
						</li>
						<li>
							<a class="edit" href="${path }/catMgr/gotoEdit.htm?catalogId={sid_user}"
								target="navTab" warn="请选择一个用户"><span>修改</span> </a>
						</li>
						<li>
							<a title="确实要删除这些记录吗?" target="selectedTodo" rel="ids"
								href="demo/common/ajaxDone.html" class="delete"><span>批量删除</span>
							</a>
						</li>
						<li class="line">
							line
						</li>
					</ul>
				</div>
				<table class="table" width="600" layoutH="138">
					<thead>
						<tr>
							<th width="22">
								<input type="checkbox" group="ids" class="checkboxCtrl">
							</th>

							<th  width="30">
								分类ID
							</th>
							<th orderField="name" width="60">
								分类名称
							</th>
							<th width="60" orderField="parentName">
								上级分类名称
							</th>
							<th width="80">
								操作  
							</th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach var="catalog" items="${catalogList }">
							<tr target="sid_user" rel="${catalog.id }">
								<td width="22" align="center">
									<input name="ids" value="${catalog.id }" type="checkbox">
								</td>
								<td align="center">
									${catalog.id }
								</td>
								<td align="center">
									${catalog.name }
								</td>
								<td align="center">
									${catalog.parentName }
								</td>
								<td align="center">
									<a title="编辑" target="dialog"
										href="${path }/catMgr/gotoEdit.htm?catalogId=${catalog.id }"
										class="btnEdit">编辑</a>
									<a title="删除" target="ajaxTodo"
										href="${path }/catMgr/del.htm?catalogId=${catalog.id }"
										class="btnDel">删除</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="panelBar">
					<div class="pages">
						<span>显示</span>
						<select class="combox" name="numPerPage"
							onchange="navTabPageBreak({numPerPage:this.value})">
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
