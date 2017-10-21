<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- HTML5文件 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>短信确认</title>
<link rel="stylesheet" href="${ctx}/layout/agile/css/agile.layout.css">
<link rel="stylesheet" href="${ctx}/layout/third/flat/flat.component.css">
<link rel="stylesheet" href="${ctx}/layout/third/flat/flat.color.css">
<link rel="stylesheet" href="${ctx}/layout/third/flat/iconline.css">
<link rel="stylesheet" href="${ctx}/layout/third/flat/iconform.css">
</head>
<body>
	<div id="section_container">
		<section id="form_section" data-role="section" class="active">
			<header>
				<div class="titlebar">
					<h1>访客确认</h1>
				</div>
			</header>
			<article data-role="article" id="border_article" class="active" data-scroll="verticle" style="top: 88px; bottom: 0px;">
				<div class="scroller">
					<form autocomplete="off" class="form-square">
						<label class="label-left" style="height: 40px;">是否同意</label> 
						<label class="label-right" style="height: 40px;">
							<a href="#" data-role="radio">
							     <input type="radio" name="sex" id="rdo1"/>
								 <label for="baskball1" class="black">是</label>
							</a> 
							<a href="#" data-role="radio">
							    <input type="radio" name="sex" id="rdo2"/>
								<label for="football1" class="black">否</label>
							</a>
						</label> 
						<label class="label-left" for="user" style="height: 80px;">确认原因</label>
						 <label class="label-right" style="height: 80px;">
						    <input id="user" type="text" title="英文数字下滑线" required/>
						</label>
						<button class="block">确认</button>
					</form>
				</div>
			</article>
		</section>
	</div>
</body>
</html>