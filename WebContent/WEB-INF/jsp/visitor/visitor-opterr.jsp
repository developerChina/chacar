<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- HTML5文件 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>短信确认</title>
<link rel="stylesheet" href="${ctx}/layout/agile/css/agile.layout.css">
<link rel="stylesheet"	href="${ctx}/layout/third/flat/flat.component.css">
<link rel="stylesheet" href="${ctx}/layout/third/flat/flat.color.css">
<link rel="stylesheet" href="${ctx}/layout/third/flat/iconline.css">
<link rel="stylesheet" href="${ctx}/layout/third/flat/iconform.css">
<link rel="stylesheet"	href="${ctx}/layout/third/seedsui/plugin/seedsui/seedsui.min.css">
<script type="text/javascript" src="${ctx}/scripts/boot.js"></script>
</head>
<body>
	<div id="section_container">

			<article id="main_article" data-role="article" class="active" class="box box-middle box-center">
				<div style="width: 200px; margin: 0px auto;">
					<div><img alt="" src="${ctx}/images/error.png"></div>
					<p class="error"><font color="red">操作失败，请重试！！！</font></p>
				</div>
			</article>
	</div>
</body>
</html>