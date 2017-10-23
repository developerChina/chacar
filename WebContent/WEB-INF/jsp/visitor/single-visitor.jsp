<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="${ctx}/css/visitor/common.css" />
		<link rel="stylesheet" href="${ctx}/css/visitor/single-visitor.css" />
		<script src="${ctx}/scripts/boot.js" type="text/javascript"></script>
	</head>
	<body>
		<div style="width: 0px;height: 0px;">
			<object classid="clsid:5EB842AE-5C49-4FD8-8CE9-77D4AF9FD4FF" id="idrControl" width="0" height="0" ></object>
		</div>
		<form id="single-visitor-form" method="post" action="${ctx}/visitor/forwardSingleVisited">
			<input type="hidden" id="recordVID" name="recordVID" value="${modle.recordVID}">
			<input type="hidden" id="recordID" name="recordID" value="${recordid}" >
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
						<div id="capctrl-div" style="position: absolute;display: none">
							<object classid="clsid:34681DB3-58E6-4512-86F2-9477F1A9F3D8" id="CapCtrl" width="420px" height="315px" codebase="ImageCapOnWeb.cab#version=1,2,0,0" style="margin:0;padding:0;float:left;">
							<param name="Visible" value="0">
							<param name="AutoScroll" value="0">
							<param name="AutoSize" value="0">
							<param name="AxBorderStyle" value="1">
							<param name="Caption" value="scaner">
							<param name="Color" value="4278190095">
							<param name="Font" value="宋体">
							<param name="KeyPreview" value="0">
							<param name="PixelsPerInch" value="96">
							<param name="PrintScale" value="0.2">
							<param name="Scaled" value="-1">
							<param name="DropTarget" value="0">
							<param name="HelpFile" value>
							<param name="PopupMode" value="0">
							<param name="ScreenSnap" value="0">
							<param name="SnapBuffer" value="10">
							<param name="DockSite" value="0">
							<param name="DoubleBuffered" value="0">
							<param name="ParentDoubleBuffered" value="0">
							<param name="UseDockManager" value="0">
							<param name="Enabled" value="-1">
							<param name="AlignWithMargins" value="0">
							<param name="ParentCustomHint" value="-1">
							<param name="licenseMode" value="2">
							<param name="key1" value="jpkQqZaD6QlBq6L7AIl1LA9MJ04Ds+N6Ft9b47KUxAZzgfPCATMIiQ==">
							<param name="key2" value="UoteC+oam7pRXJD+LR6+PearD5PI+tnbTOPp1vs13dnBQrPkJFItST16wBOTEaiSWUwWZ1JnKUHcCkvM+Ie+CeRbZvvaT2ATi9yZ1Q==">
							</object>
						</div>
						<div class="photoArea clearfix">
							<div class="fl">
								<img id="cardPhoto-img" src="${ctx}/images/photoOne.png" alt="" />
								<input type="hidden" id="cardPhoto" name="cardPhoto" value="">
								<p>证件照片</p>
							</div>
							<div class="fl">
								<img id="photo1-img" src="${ctx}/images/photoTwo.png" alt="" />
								<input type="hidden" id="photo1" name="photo1" value="">
								<p>拍照照片</p>
							</div>
						</div>
						<div class="infoArea">
							<p class="clearfix">
								<span class="fl">
									<input type="hidden" id="cardName" name="cardName" value="">
									姓名：<span id="cardName-span"></span>
								</span>
								<span class="fl">
									<input type="hidden" id="cardSex" name="cardSex" value="">
									&nbsp;&nbsp;性别：<span id="cardSex-span"></span>
								</span>
								<span class="fl">
									<input type="hidden" id="cardNation" name="cardNation" value="">
									&nbsp;&nbsp;民族：<span id="cardNation-span"></span>
								</span>
							</p>
							<p>
								<input type="hidden" id="cardID" name="cardID" value="">
								身份号码：<span id="cardID-span"></span>
							</p>
							<p>
								<input type="hidden" id="cardAddress" name="cardAddress" value="">
								住址：<span id="cardAddress-span"></span>
							</p>
							<p>联系电话：<input type="text" class="text" id="telephone" name="telephone" style="width: 200px"/></p>
							<p>工作单位：<input type="text" class="text" id="company" name="company" style="width: 400px"/></p>
							<p>访问事由：<input type="text" class="text" id="visitReason" name="visitReason" style="width: 400px"/></p>
						</div>
					</div>
					<div class="right fl">
						<div>
							<a href="#" onclick="newvisitor()"><img src="${ctx}/images/shuacard.png" alt=""/></a>
						</div>
						<div></div>
						<div>
							<a href="#" onclick="photograph()"><img src="${ctx}/images/photoCollect.png" alt="" /></a>
						</div>
						<div class="page fr">
							<a href="${ctx}/vindex.jsp" class="fl prevpage"></a>
							<a href="#" class="fl nextpage" onclick="submitRecord();"></a>
						</div>
					</div>
				</div>
				<div>
					<a href="${ctx}/vindex.jsp" class="foot">返回</a>
				</div>
			</div>
		</form>
	</body>
	<script type="text/javascript">
		/**
		* 创建访问记录(保存或者更新访客)
		*/
		function submitRecord(){
			var cardid=$("#cardID").val();
			if(cardid==""){
				alert("请登记身份证信息");
				return;
			}
			$('#single-visitor-form').submit();
		}
		
		/**
		* 读取身份证信息
		*/
		function readIDCard(){
			var result;
			var cardInfo={};
			try {  
			    var ax = new ActiveXObject("IDRCONTROL.IdrControlCtrl.1");  
			} catch(e) {  
			    cardInfo["state"]=false;
			    cardInfo["message"]="控件未安装";
			}  	
			//注意：第一个参数为对应的设备端口，USB型为1001，串口型为1至16
			result=idrControl.ReadCard("1001","");
			if (result==1){
				var message={};
				message["name"]=idrControl.GetName();//姓名
				message["folk"]=idrControl.GetFolk();//民族
				message["sex"]=idrControl.GetSex();//性别
				message["birthyear"]=idrControl.GetBirthYear();//出生年
				message["birthmonth"]=idrControl.GetBirthMonth();//出生月
				message["birthday"]=idrControl.GetBirthDay();//出生日
				message["birthdate"]=idrControl.GetBirthYear() + "年" + idrControl.GetBirthMonth() + "月" + idrControl.GetBirthDay() +  "日";//出生日期
				message["code"]=idrControl.GetCode();//公民身份证号码 
				message["address"]=idrControl.GetAddress();//地址
				message["agency"]=idrControl.GetAgency();//签发机关
				message["valid"]=idrControl.GetValid();//有效日期
				message["photobuf"]=idrControl.GetJPGPhotobuf();//照片Base64编码
				message["samid"]=idrControl.GetSAMID();//安全模块号
				message["cardsn"]=idrControl.GetIDCardSN();//身份证卡号
				cardInfo["state"]=true;
				cardInfo["message"]=message;
			}else{
				if (result==-1){
					cardInfo["state"]=false;
				    cardInfo["message"]="端口初始化失败";
				}
				if (result==-2){
					cardInfo["state"]=false;
				    cardInfo["message"]="请重新将卡片放到读卡器上";
				}
				if (result==-3){
					cardInfo["state"]=false;
				    cardInfo["message"]="读取数据失败";
				}
				if (result==-4){
					cardInfo["state"]=false;
				    cardInfo["message"]="生成照片文件失败，请检查设定路径和磁盘空间！";
				}
			}
			return cardInfo;
		}
		
		/**
		* 读取身份证信息 测试
		*/
		function readIDCard_test(){
			var result;
			var cardInfo={};
			var message={};
			message["name"]="张兮兮";//姓名
			message["folk"]="维吾尔族";//民族
			message["sex"]="男";//性别
			message["birthyear"]="2017";//出生年
			message["birthmonth"]="08";//出生月
			message["birthday"]="21";//出生日
			message["birthdate"]="2017年08月21日";//出生日期
			message["code"]="17282520170821032X";//公民身份证号码 
			message["address"]="乌鲁木齐沙依巴克区沙依巴克境内";//地址
			message["agency"]="乌鲁木齐公安厅";//签发机关
			message["valid"]="2028-12-31";//有效日期
			message["photobuf"]="123";//照片Base64编码
			message["samid"]="23213";//安全模块号
			message["cardsn"]="17282520170821032X";//身份证卡号
			cardInfo["state"]=true;
			cardInfo["message"]=message;
			return cardInfo;
		}
		 
		
		/**
		*  新建访问人
		*/	
		function newvisitor(){
			//var cardInfo=readIDCard_test();//硬件测试用例
			var cardInfo=readIDCard();
			if(cardInfo.state){
				$("#cardPhoto").val(cardInfo.message.photobuf);
				$("#cardPhoto-img").attr("src", "data:image/png;base64,"+cardInfo.message.photobuf);
			   
				$("#cardName").val(cardInfo.message.name);
				$("#cardName-span").html(cardInfo.message.name);
				
				$("#cardSex").val(cardInfo.message.sex);
				$("#cardSex-span").html(cardInfo.message.sex);
				
				$("#cardNation").val(cardInfo.message.folk);
				$("#cardNation-span").html(cardInfo.message.folk);
				 
				$("#cardID").val(cardInfo.message.code);
				$("#cardID-span").html(cardInfo.message.code);
				
				$("#cardAddress").val(cardInfo.message.address);
				$("#cardAddress-span").html(cardInfo.message.address);
				
				$.ajax({
				  type: 'POST',
				  url: '${ctx}/visitor/getVisitorBycardID',
				  data: {"cardid":cardInfo.message.code},
				  success: function(data){
					 $("#telephone").val(data.telephone); 
					 $("#company").val(data.company); 
				  }
				});
				
			}else{
				alert(cardInfo.message);
			}
		}
		
		var capCtrl;
		try {
			//拍照控件初始化
			capCtrl = document.getElementById("CapCtrl");
			capCtrl.SwitchWatchOnly();
	    }
	    catch (e) {
	        console.info(e);
	    }
		
		/**
		* 拍照
		*/
		function photograph(){
			var cardid=$("#cardID").val();
			if(cardid==""){
				alert("请登记身份证信息");
				return;
			}
			if($('#capctrl-div').css('display') == 'none'){
				$("#capctrl-div").show();
				capCtrl.start();
			}else{
				capCtrl.cap();
				var baseStr64=capCtrl.jpegBase64Data;
				if(baseStr64!=""){
				   $("#photo1").val(baseStr64);
				   $("#photo1-img").attr("src", "data:image/png;base64,"+baseStr64);
				}
				capCtrl.stop();
				$("#capctrl-div").hide();
			}
		}
		
		function init(){
			if("${modle}"==''){
				return;
			}
			$("#recordVID").val("${modle.recordVID}");
			$("#recordID").val("${recordid}");
			if("${modle.cardPhoto}"!=''){
				$("#cardPhoto").val("${modle.cardPhoto}");
				$("#cardPhoto-img").attr("src", "data:image/png;base64,"+"${modle.cardPhoto}");
			}
				$("#photo1").val("${modle.photo1}");
				$("#photo1-img").attr("src", "data:image/png;base64,"+"${modle.photo1}");
		   
			$("#cardName").val("${modle.cardName}");
			$("#cardName-span").html("${modle.cardName}");
			
			$("#cardSex").val("${modle.cardSex}");
			$("#cardSex-span").html("${modle.cardSex}");
			
			$("#cardNation").val("${modle.cardNation}");
			$("#cardNation-span").html("${modle.cardNation}");
			 
			$("#cardID").val("${modle.cardID}");
			$("#cardID-span").html("${modle.cardID}");
			
			$("#cardAddress").val("${modle.cardAddress}");
			$("#cardAddress-span").html("${modle.cardAddress}");
			
			$("#telephone").val("${modle.telephone}"); 
			$("#company").val("${modle.company}"); 
			$("#visitReason").val("${modle.visitReason}"); 
			
		}
		
	</script>
</html>
