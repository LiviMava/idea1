<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	pageContext.setAttribute("path", request.getContextPath());
%>
<form class="form-horizontal">
	<!-- 头像 -->
	<c:if test="${empty userInfo.img }">
		<div class="form-group" style="text-align: center">
			<img src="${path }/img/default.jpg" id="show_image" width="120px" height="180px" class="img-circle" />
		</div>
	</c:if>
	<c:if test="${not empty userInfo.img }">
		<div class="form-group" style="text-align: center">
			<img src="${fileServer }${userInfo.img }" id="show_image" width="120px" height="180px" class="img-circle" />
		</div>
	</c:if>
	<div class="form-group">
		<label class="col-sm-2 control-label">姓名</label>
		<div class="col-sm-10">
			<p class="form-control-static">${userInfo.name }</p>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">电话</label>
		<div class="col-sm-10">
			<p class="form-control-static">${userInfo.mobile }</p>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">地址</label>
		<div class="col-sm-10">
			<p class="form-control-static">${userInfo.address }</p>
		</div>
	</div>
</form>