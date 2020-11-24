<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	pageContext.setAttribute("path", request.getContextPath());
%>
<form class="form-horizontal" id="update">
	<input type="hidden" name="id" value="${userInfo.id }">
	<input type="hidden" name="img" value="${userInfo.img }">
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
	<!-- 文件和选择文件的按钮 -->
	<div class="form-group center-block" style="text-align: center">
		<input type="file" id="image" name="image" style="display: none" accept="image/*">
		<label for="image" class="btn btn-default">选择文件</label>
	</div>
	
	<div class="form-group">
		<label for="name" class="col-sm-2 control-label">姓名</label>
		<div class="col-sm-10">
			<input class="form-control" id="name" name="name" placeholder="请输入姓名"
				value="${userInfo.name }">
		</div>
	</div>
	<div class="form-group">
		<label for="mobile" class="col-sm-2 control-label">电话</label>
		<div class="col-sm-10">
			<input class="form-control" id="mobile" name="mobile"
				placeholder="请输入电话" value="${userInfo.mobile }">
		</div>
	</div>
	<div class="form-group">
		<label for="address" class="col-sm-2 control-label">地址</label>
		<div class="col-sm-10">
			<input class="form-control" id="address" name="address"
				placeholder="请输入地址" value="${userInfo.address }">
		</div>
	</div>
</form>
<script type="text/javascript">
	document.getElementById("image").addEventListener('change', function() {
		var files = this.files;
		if(files.length>0) {
			var file = files[0];
			if(/^image\/png$|jpeg$/.test(file.type)) {
				document.getElementById('show_image').src = URL.createObjectURL(file);
			} else {
				alert("请选择png或jpeg类型的图片文件！");
			}
		} else {
			alert("请选择图片文件！");
		}
	}, false);
</script>