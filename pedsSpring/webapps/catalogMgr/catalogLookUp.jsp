<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<%@include file="/include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="分类查找">
	</head>
	<body>
		<form id="pagerForm" method="post" action="${path }/catMgr/lookUp.htm">
			<input type="hidden" name="currentPage"
				value="${search.currentPage }">
			<input type="hidden" name="pageNum" value="${search.pageNum }"
				<input type="hidden" name="orderField" value="${search.orderField}" />
				<input type="hidden" name="orderDirection" value="${search.orderDirection}" />
				<input type="hidden" name="totalCount" value="${search.totalCount }"/>
				<input type="hidden" name="numPerPage" value="${search.numPerPage }" />
		</form>
		<div class="pageHeader">
			<form rel="pagerForm" onsubmit="return dwzSearch(this,'dialog');"
				action="${path }/catMgr/lookUp.htm" method="post">
				<div class="searchBar">
					<ul class="searchContent">
						<li>
							<label>
								分类名称：
							</label>
							<input type="text" name="catalogName"
								value="${search.catalogName }" />
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
			<table class="table" width="600" layoutH="138">
				<thead>
					<tr>
						<th width="80">
							分类ID
						</th>
						<th orderField="name" width="80">
							分类名称
						</th>
						<th width="80" orderField="parentName">
							上级分类名称
						</th>
						<th width="80">
							操作
						</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="catalog" items="${catalogList }">
						<tr>
							<td width="22" align="center">
								${catalog.id }
							</td>
							<td align="center">
								${catalog.name }
							</td>
							<td align="center">
								${catalog.parentName }
							</td>
							<td align="center">
								<a class="btnSelect"
									href="javascript:$.bringBack({parentId:'${catalog.id }', parentName:'${catalog.name}'})"
									title="查找带回">选择</a>
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
				<div class="pagination" targetType="dialog"
					totalCount="${search.totalCount }"
					numPerPage="${search.numPerPage }"
					pageNumShown="${search.pageNumShown }"
					currentPage="${search.currentPage }"></div>
			</div>
		</div>
	</body>
</html>
