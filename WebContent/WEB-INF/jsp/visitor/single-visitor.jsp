<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="${ctx}/css/visitor/common.css" />
		<link rel="stylesheet" href="${ctx}/css/visitor/single-visitor.css" />
		<script type="text/javascript">
			 
		function dd(){
			document.getElementById('single-visitor-form').submit();
		}
			
		</script>
	</head>
	<body>
		<form id="single-visitor-form" method="post" action="${ctx}/visitor/forwardSingleVisited">
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
								<img id="cardPhoto-img" src="${ctx}/images/photoOne.png" alt="" />
								<input type="hidden" id="cardPhoto" name="cardPhoto" value="/images/photoOne.png">
								<p>证件照片</p>
							</div>
							<div class="fl">
								<img id="photo1-img" src="${ctx}/images/photoTwo.png" alt="" />
								<input type="hidden" id="photo1" name="photo1" value="/images/photoTwo.png">
								<p>拍照照片</p>
							</div>
						</div>
						<div class="infoArea">
							<p class="clearfix">
								<span class="fl">
									<input type="hidden" id="cardName" name="cardName" value="金阳">
									姓名：<span id="cardName-span">金阳</span>
								</span>
								<span class="fl">
									<input type="hidden" id="cardSex" name="cardSex" value="女">
									&nbsp;&nbsp;性别：<span id="cardSex-span">女</span>
								</span>
								<span class="fl">
									<input type="hidden" id="cardNation" name="cardNation" value="汉">
									&nbsp;&nbsp;名族：<span id="cardNation-span">汉</span>
								</span>
							</p>
							<p>
								<input type="hidden" id="cardID" name="cardID" value="110102197810272321">
								身份号码：<span id="cardSex-span">110102197810272321</span>
							</p>
							<p>
								<input type="hidden" id="cardAddress" name="cardAddress" value="北京西城区复兴门大街11号3单元">
								住址：<span id="cardAddress-span">北京西城区复兴门大街11号3单元</span>
							</p>
							<p>联系电话：<input type="text" class="text" id="telephone" name="telephone" style="width: 200px"/></p>
							<p>工作单位：<input type="text" class="text" id="company" name="company"style="width: 400px"/></p>
						</div>
						<div class="page fr">
							<a href="${ctx}/vindex.jsp" class="fl prevpage"></a>
							<a href="#" class="fl nextpage" onclick="dd();"></a>
						</div>
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
		</form>
	</body>
</html>
