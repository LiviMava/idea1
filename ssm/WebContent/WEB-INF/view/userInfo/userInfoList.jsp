<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%
	pageContext.setAttribute("path", request.getContextPath());
%>
<%-- <%=new Date().toLocaleString()%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
	<title>Bootstrap 101 Template</title>
	<!-- Bootstrap -->
	<link href="${path }/css/bootstrap.min.css" rel="stylesheet">
	<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
	<script src="${path }/js/jquery-3.2.1.min.js"></script>
	<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
	<script src="${path }/js/bootstrap.min.js"></script>
	<script src="${path }/js/bootstrap-dialog.js"></script>
	<script type="text/javascript">
		var pageNo=1;
		var pageTotal=0;

		/* 翻页  */
		function up(){
			if(pageNo==1){
				Bootstrap.tip("当前已第一页");
				return;
			}
			pageNo--;
			page();
		}
		function down(){
			if(pageNo==pageTotal){
				Bootstrap.tip("最后一页");
				return;
			}
			pageNo++;
			page();
		}

		/* 分页  */
		function page(){
			var name=$("#name").val();
			var mobile=$("#mobile").val();
			$("#name").val("");
			$("#mobile").val("");
			$.ajax({
				url:"userInfoListFrag.do?pageNo="+pageNo+"&name="+name+"&mobile="+mobile,
				success:function(data){
					$("#datas").html(data);
					pageTotal=$("#pageTotal").val();
					$("#pTotal").html("一共"+pageTotal+"页");
					$("#pNo").html("当前页:"+pageNo);
				}
			});
		}
		function rtn(){
			$.ajax({
				url:"userInfoListFrag.do?pageNo="+pageNo,
				success:function(data){
					$("#datas").html(data);
					pageTotal=$("#pageTotal").val();
					$("#pTotal").html("一共"+pageTotal+"页");
					$("#pNo").html("当前页:"+pageNo);
				}
			});
		}
		$(function(){
			page();
		})

		/* 增删改查 */
		function add(){
			var dialog = Bootstrap.dialog({
				title: '添加信息',
				url: 'addView.do',
				onOK: function() {
					var obj = {
						url:"add.do",
						type:"post",
						data:new FormData(document.getElementById("add")),
						cache: false,
						processData: false, // 不处理数据
						contentType: false, // 不设置内容类型
						dataType:"text",
						success:function(data){
							if(data=="0"){
								Bootstrap.tip("添加失败");
								return;
							}
							Bootstrap.tip("添加成功");
							dialog.modal('hide');//隐藏模态框
							page();
						}
					}
					$.ajax(obj);
				}
			});
		}

		function drop(id){
			var tip = Bootstrap.confirm({ message: "确认要删除选择的数据吗？" });
			tip.ok(function () {
				var obj = {
					url:"delete.do?id="+id,
					dataType:"json",
					success:function(data){
						if(data=="0"){
							Bootstrap.tip("删除失败");
							return;
						}
						Bootstrap.tip("删除成功："+data+"、"+data.i);
						page();
					}
				}
				$.ajax(obj);
			});
		}

		function update(id){
			var dialog = Bootstrap.dialog({
				title: '修改信息',
				url: 'updateView.do?id='+id,
				onOK: function() {
					var name=$("#name").val();
					console.log($("#update").serialize());
					var obj = {
						url:"update.do",
						type:"post",
						data:new FormData(document.getElementById("update")),
						cache: false,
						processData: false, // 不处理数据
						contentType: false, // 不设置内容类型
						dataType:"text",
						success:function(data){
							if(data=="0"){
								Bootstrap.tip("修改失败");
								return;
							}
							Bootstrap.tip("修改成功");
							dialog.modal('hide');//隐藏模态框
							page();
						}
					}
					$.ajax(obj);
				}
			});
		}

		function select(id){
			Bootstrap.dialog({
				title: '查看',
				okHide: true,
				cancelValue: "关闭",
				url: 'select.do?id='+id
			});
		}

		function search(){
			pageNo=1;
			page();
		}
	</script>
</head>
<body>
<%-- 		<h3>${path }</h3> --%>


<!-- 添加按钮 -->

<form class="form-inline" style="margin-top:20px;">
	<div class="form-group">
		<label for="name">名称</label>
		<input type="text" class="form-control" id="name" name="name" placeholder="请输入灭火器名...">
	</div>
	<div class="form-group">
		<label for="mobile">标号</label>
		<input type="text" class="form-control" id="mobile" name="mobile" placeholder="请输入标号...">
	</div>
	<button type="button" class="btn btn-default" onclick="search()">搜索</button>
	<button type="button" class="btn btn-default" onclick="rtn()">返回</button>
	<button type="button" class="btn btn-default" onclick="add()"/>添加</button>
</form>
<!-- 表格 -->
<div id="datas" style="margin-top:20px;"></div>

<!-- 当前多少页/共几页 -->
<div id="pTotal" style="position:fixed;top:550px;left:750px;"></div><br/>
<div id="pNo" style="position:fixed;top:580px;left:750px;"></div>

<!-- 翻页 -->
<div style="position:fixed;top:550px;left:600px;">
	<input type="button" onclick="up()" value="上一页" style="margin-right: 200px;" class="btn btn-default btn-lg"/>
	<input type="button" onclick="down()" value="下一页" class="btn btn-default btn-lg" />
</div>
</body>
</html>