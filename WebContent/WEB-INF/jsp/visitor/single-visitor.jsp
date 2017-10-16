<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="${ctx}/css/visitor/common.css" />
		<link rel="stylesheet" href="${ctx}/css/visitor/single-visitor.css" />
	</head>
	<body>
		<div class="wrap">
			<div class="head">
				<h2>单访客登记</h2>
			</div>
			<div class="content clearfix">
				<div class="left fl">
					<div class="clearfix title">
						<span class="fl"></span>
						<span>访客信息</span>
					</div>
					<div class="photoArea clearfix">
						<div class="fl">
							<img src="${ctx}/images/photoOne.png" alt="" />
							<p>证件照片</p>
						</div>
						<div class="fl">
							<img src="${ctx}/images/photoTwo.png" alt="" />
							<p>拍照照片</p>
						</div>
					</div>
					<div class="infoArea">
						<p class="clearfix">
							<span class="fl">姓名：金阳</span>
							<span class="fl">	性别：女</span>
							<span class="fl">	名族：汉</span>
						</p>
						<p>身份号码：110102197810272321</p>
						<p>住址：北京西城区复兴门大街11号3单元</p>
					</div>
					<div class="page fr"><a href="${ctx}/visitor/forwardSingleVisited">下一页</a></div>
				</div>
				<div class="right fl">
					<div>
						<img src="${ctx}/images/shuacard.png" alt="" />
					</div>
					<div></div>
					<div>
						<img src="${ctx}/images/photoCollect.png" alt="" />
					</div>
				</div>
			</div>
			<div>
				<a href="${ctx}/vindex.jsp" class="foot">返回</a>
			</div>
		</div>
	</body>
</html>
