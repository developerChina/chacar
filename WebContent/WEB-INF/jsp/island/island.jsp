<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<title>北京长安取号系统</title>
<link rel="stylesheet" href="${ctx}/css/frozen/css/frozen.css">
<link rel="stylesheet" href="${ctx}/css/frozen/demo.css">
<link rel="stylesheet" href="${ctx}/css/keybord/keybord.css">

<script type="text/javascript" src="${ctx}/css/keybord/js/jquery.min.js"></script>
<script src="${ctx}/css/frozen/lib/zepto.min.js"></script>
<script src="${ctx}/css/frozen/js/frozen.js"></script>

<script src="${ctx}/css/keybord/layer_mobile/layer.js"	type="text/javascript"></script>
<script src="${ctx}/css/keybord/js/index.js" type="text/javascript"></script>
</head>

<body ontouchstart>
	<header class="ui-header ui-header-positive ui-border-b">
		<h1>北京长安取号系统(分装1号卸货岛)</h1>
	</header>
	<section class="ui-container">
		<section id="table">
			<div class="demo-item">
				<p class="demo-desc">请输入车牌号：</p>
				<div class="demo-block">
					<div class="ui-flex ui-flex-pack-start">
						<div style="width: 98%;">
							<div class="car_input">
								<ul class="clearfix ul_input">
									<li class="input_pro"><span></span></li>
									<li class="input_pp input_zim"><span></span></li>
									<li class="input_pp"><span></span></li>
									<li class="input_pp"><span></span></li>
									<li class="input_pp"><span></span></li>
									<li class="input_pp"><span></span></li>
									<li class="input_pp"><span></span></li>
								</ul>
							</div>

						</div>

						<div id="jp_pro"></div>
					</div>
					<div class="ui-flex ui-flex-pack-center">
						<div class="demo-block">
							<div class="ui-btn-wrap">
								<button id="btn1" class="ui-btn ui-btn-primary">取号</button>
								<button class="ui-btn ui-btn-danger">查询</button>
							</div>
						</div>
					</div>

				</div>
			</div>

			<div class="demo-item">
				<p class="demo-desc">总排队：30 &nbsp;&nbsp;&nbsp;&nbsp; 等待人数：10</p>
				<div class="demo-block">
					<table class="ui-table ui-border">
						<thead>
							<tr>
								<th>状态</th>
								<th>排队号码</th>
								<th>车牌号码</th>
								<th>备注</th>
							</tr>
						</thead>
						<tbody>
							<tr style="color:green">
								<td>当前</td>
								<td>2</td>
								<td>京N3G8B0</td>
								<td>VIP号</td>
							</tr>
							<tr>
								<td>等待</td>
								<td>3</td>
								<td>京N3G8B0</td>
								<td>普通号</td>
							</tr>
							<tr style="color:red">
								<td>等待</td>
								<td>4</td>
								<td>京N3G8B0</td>
								<td>普通号</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</section>
	</section>
	<!-- /.ui-container-->
	<script>
	$(function(){
        $("#btn1").click(function(){
        	
		})
	})
	</script>
</body>
</html>