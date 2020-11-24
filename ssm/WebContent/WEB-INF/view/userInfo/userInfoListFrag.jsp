<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="table table-striped table-bordered">
	<thead>
		<tr>
			<th>id</th>
			<th>名称</th>
			<th>位置</th>
			<th>标号</th>
			<td>操作</td>
		</tr>
	</thead>
	<tbody>
		<c:if test="${empty list }">
			<tr>
				<td colspan="5" style="text-align:center;">当前无数据</td>
			</tr>	
		</c:if>
		<c:forEach var="ui" items="${list }" varStatus="status">
			<tr>
				<td>${ui.id }</td>
				<td>${ui.name }</td>
				<td>${ui.address }</td>
				<td>${ui.mobile }</td>
				<td><a href="#" onclick="select('${ui.id}')">查看</a>   <a href="#" onclick="drop('${ui.id}')">删除</a>   <a href="#" onclick="update('${ui.id}')">更改</a></td>
			</tr>	
		</c:forEach>
	</tbody>
</table>
<input type="hidden" id="pageTotal" value="${pageTotal }"/>