<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="${ctx}/css/visitor/common.css" />
		<link rel="stylesheet" href="${ctx}/css/visitor/index.css?time=121" />
		<link href="${ctx}/images/favicon.ico" type="image/x-icon" rel="shortcut icon"/>
		<script src="${ctx}/scripts/boot.js" type="text/javascript"></script>
	</head>
	<body>
		<div class="wrap">
			<div class="head">
				<h2>无人值守访客系统</h2>
				<div style="width: 0px;height: 0px;">
					<object classid="clsid:5EB842AE-5C49-4FD8-8CE9-77D4AF9FD4FF" id="idrControl" width="0" height="0" ></object>
				</div>
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
					<div class="btn">
						<a href="#" onclick="SignOut()">自助签离</a>
					</div>
				</div>
			</div>
			<div class="foot">
				在使用过程中，有任何问题请联系工作人员
			</div>
		</div>
	</body>
	<script type="text/javascript">
	 $(document).ready(function(){
	     $(document).bind("contextmenu",function(e){
	         return false;
	     });
	 });
	 
	 function SignOut(){
			var result;
			var cardInfo={};
			try {  
			    var ax = new ActiveXObject("IDRCONTROL.IdrControlCtrl.1");  
			} catch(e) {  
				alert("控件未安装");
			}  	
			//注意：第一个参数为对应的设备端口，USB型为1001，串口型为1至16
			result=idrControl.ReadCard("1001","");
			if (result==1){
				var cardid=idrControl.GetCode();//公民身份证号码
				$.ajax({
					  type: 'POST',
					  url: '${ctx}/visitor/visitorSignOut',
					  data: {"cardid":cardid},
					  success: function(data){
						   if(data.status){
							   alert(data.message);
						   }else{
							   alert(data.message);
						   }
					  }
				});
			}else{
				if (result==-1){
					alert("端口初始化失败");
				}
				if (result==-2){
					alert("请重新将卡片放到读卡器上");
				}
				if (result==-3){
					alert("读取数据失败");
				}
				if (result==-4){
					alert("生成照片文件失败，请检查设定路径和磁盘空间！");
				}
			}
		}
	</script>
</html>
