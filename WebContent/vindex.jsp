<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="${ctx}/css/visitor/common.css" />
		<link rel="stylesheet" href="${ctx}/css/visitor/index.css" />
		<link href="${ctx}/images/favicon.ico" type="image/x-icon" rel="shortcut icon"/>
	</head>
	<body>
		<div class="wrap">
			<div class="head">
				<h2>无人值守访客系统</h2>
			</div>
			<div class="content clearfix">
				<div class="left fl"></div>
				<div class="right fl">
					<div class="btn">
						<a href="${ctx}/visitor/forwardSingleVisitor">单访客登记</a>
					</div>
					<div class="btn">
						<a href="${ctx}/visitor/forwardMoreVisitor">多访客登记</a>
					</div>
					<div class="btn">
						<a href="${ctx}/visitor/forwardAllPrint">打印访客单</a>
					</div>
				</div>
			</div>
			<div class="foot">
				在使用过程中，有任何问题请于工作人员联系 
			</div>
		</div>
	</body>
</html>
