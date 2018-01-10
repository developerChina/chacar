<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="${ctx}/css/visitor/common.css" />
		<link rel="stylesheet" href="${ctx}/css/visitor/more-visitor.css" />
    	<script src="${ctx}/scripts/boot.js" type="text/javascript"></script>
	    <style type="text/css">
	        .New_Button, .Edit_Button, .Delete_Button, .Update_Button, .Cancel_Button
	        {
	            font-size:11px;color:#1B3F91;font-family:Verdana;  
	            margin-right:5px;
	        }
	        .actionIcons span
	        {
	            width:16px;
	            height:16px;
	            display:inline-block;
	            background-position:50% 50%;
	            cursor:pointer;
	        }
	    </style> 
	</head>
	<body>
		<div style="width: 0px;height: 0px;">
			<object classid="clsid:5EB842AE-5C49-4FD8-8CE9-77D4AF9FD4FF" id="idrControl" width="0" height="0" ></object>
		</div>
		<form id="more-visitor-form" method="post" action="${ctx}/visitor/forwardMoreVisited">
			<input type="hidden" id="recordVisitors" name="recordVisitors" />
		</form>
		<div class="wrap">
			<div class="head">
				<h2>多访客登记</h2>
			</div>
			<div class="content clearfix">
				<div class="top">
					<div class="clearfix title">
						<span class="fl"></span>
						<span class="fl">访客信息</span>
					</div>
					<div class="ensure clearfix">
						<div class="fl">
							<a href="#" onclick="newvisitor()"><img src="${ctx}/images/shuacard.png" alt="" /></a>
						</div>
						<div class="fl"></div>
						<div class="fl">
							<a href="#" onclick="photograph()"><img src="${ctx}/images/photoCollect.png" alt="" /></a>
						</div>
					</div>
				</div>
				<div class="bottom clearfix">
					<div class="fl left">
					  <div id="datagrid1" class="mini-datagrid"  idField="cardID" showPager="false" allowSortColumn="false" frozenStartColumn="0" frozenEndColumn="2">
					      <div property="columns">
					      	  <div type="checkcolumn"></div>
					          <div cellCls="actionIcons" name="action" width="40" headerAlign="center" align="center" renderer="onActionRenderer">操作</div>
					          <div field="cardName" width="80" headerAlign="center">姓名</div>                
					          <div field="cardSex" width="60" visible="false">性别</div>
					          <div field="cardNation" width="60" visible="false">民族</div>
					          <div field="cardID" width="124">身份证号码</div>
					          <div field="cardAddress" visible="false">住址</div>
					          <div field="cardPhoto" visible="false">证件照片</div>
					          <div field="photo1" visible="false">拍照照片</div>
					          <div field="telephone" width="100" headerAlign="center">联系电话
				                <input property="editor" class="mini-textbox" style="width:100%;"/>
				              </div>
				              <div field="company" width="150" headerAlign="center">工作单位
				                <input property="editor" class="mini-textbox" style="width:100%;"/>
				              </div>
				              <div field="visitReason" width="100" headerAlign="center">访问事由
				                <input property="editor" class="mini-combobox" style="width:100%;" data="resons" valueField	="content" textField="content" />  
				              </div>  
					      </div>
					  </div>
					</div>
					<div class="fl right clearfix">
						<div class="fl">
							<img id="cardPhoto-img" src="${ctx}/images/photoOne.png" alt="" />
							<p>证件照片</p>
						</div>
						<div class="fl" >
							<img id="photo1-img" src="${ctx}/images/photoTwo.png" alt="" />
							<p>拍照照片</p>
						</div>
						<!-- 
						style="width:671px;height:271px;"
						style="width:246px;height:212px"
						style="width:240px;height:180px"
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
						-->
					</div>
					<div class="page fr">
						<a href="${ctx}/vindex.jsp" class="fl prevpage"></a>
						<a href="#" class="fl nextpage" onclick="submitRecord()"></a>
					</div>
				</div>
			</div>
			<div>
				<a href="${ctx}/vindex.jsp" class="foot">返回</a>
			</div>
		</div>
	</body>
	
	<script type="text/javascript">
	
		var resons = ${resonStr};
	
        mini.parse();
        var grid = mini.get("datagrid1");

        function newvisitor() {
        	//var cardInfo=readIDCard_test(Math.floor(Math.random()*10)); //硬件测试用例
        	var cardInfo=readIDCard();
			if(cardInfo.state){
				//判断是否添加
				var data = grid.getEditData(true);
				for (var i = 0; i < data.length; i++) {
					if(data[i].cardID==cardInfo.message.code){
						grid.setSelected(data[i]);
						return;
					}
				}
				var row = {};
				row["cardPhoto"]=cardInfo.message.photobuf;
				$("#cardPhoto-img").attr("src", "data:image/png;base64,"+cardInfo.message.photobuf);
				row["cardName"]=cardInfo.message.name;
				row["cardSex"]=cardInfo.message.sex;
				row["cardNation"]=cardInfo.message.folk;
				row["cardID"]=cardInfo.message.code;
				row["cardAddress"]=cardInfo.message.address;
				
				$.ajax({
				  type: 'POST',
				  url: '${ctx}/visitor/getVisitorBycardID',
				  data: {"cardid":cardInfo.message.code},
				  success: function(data){
					  row["telephone"]=data.telephone; 
					  row["company"]=data.company; 
					  grid.addRow(row);
			          grid.beginEditRow(row);
			          grid.setSelected(row);
				  }
				});
				
			}else{
				alert(cardInfo.message);
			}
        }
        
        //添加选中事件
        grid.on("select", function (sender,record) {
        	if(undefined!=sender.record.cardPhoto && ""!=sender.record.cardPhoto){
        		$("#cardPhoto-img").attr("src", "data:image/png;base64,"+sender.record.cardPhoto);
        	}else{
        		$("#cardPhoto-img").attr("src", "${ctx}/images/photoOne.png");
        	}
        	if(undefined!=sender.record.photo1 && ""!=sender.record.photo1){
        		$("#photo1-img").attr("src", "data:image/png;base64,"+sender.record.photo1);
        	}else{
        		$("#photo1-img").attr("src", "${ctx}/images/photoTwo.png");
        	}
        });
        
        
        
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
		function readIDCard_test(i){
			var result;
			var cardInfo={};
			var message={};
			message["name"]="张兮兮"+i;//姓名
			message["folk"]="维吾尔族";//民族
			message["sex"]="男";//性别
			message["birthyear"]="2017";//出生年
			message["birthmonth"]="08";//出生月
			message["birthday"]="21";//出生日
			message["birthdate"]="2017年08月21日";//出生日期
			message["code"]="17282520170821032"+i;//公民身份证号码 
			message["address"]="乌鲁木齐沙依巴克区沙依巴克境内";//地址
			message["agency"]="乌鲁木齐公安厅";//签发机关
			message["valid"]="2028-12-31";//有效日期
			message["photobuf"]="123";//照片Base64编码
			message["samid"]="23213";//安全模块号
			message["cardsn"]="17282520170821032"+i;//身份证卡号
			cardInfo["state"]=true;
			cardInfo["message"]=message;
			return cardInfo;
		}
        
		//var capCtrl;
		//try {
			//拍照控件初始化
		//	capCtrl = document.getElementById("CapCtrl");
		//	capCtrl.SwitchWatchOnly();
	    //}
	   // catch (e) {
	   //     console.info(e);
	   // }
		/**
		* 拍照
		*/
		function photograph(){
			return;
			var row=grid.getSelected();
			if(row==null){
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
				   $("#photo1-img").attr("src", "data:image/png;base64,"+baseStr64);
				   row['photo1']=baseStr64;
				   //grid.commitEditRow(row); 
				}
				capCtrl.stop();
				$("#capctrl-div").hide();
			}
		}
		
        function delRow(row_uid) {
            var row = grid.getRowByUID(row_uid);
            if (row) {
                grid.removeRow(row);
            }
        }
        
        function submitRecord() {
            var datas = grid.getEditData(true);
            if(datas.length<=0){
            	alert("请登记身份证信息");
				return;
            }else{
            	for (i=0;i<datas.length;i++) {
					if(datas[i].telephone==""){
						alert(datas[i].cardName+"请登记联系电话");
						return;
					}
					if(datas[i].company==""){
						alert(datas[i].company+"请登记工作单位");
						return;
					}
				}
            }
            var json = mini.encode(datas);
            $("#recordVisitors").val(json);
            
            
          //黑名单和正在访问校验
			$.ajax({
			  type: 'POST',
			  url: '${ctx}/visitor/validateMoreVisitor',
			  data: {"recordVisitors": $("#recordVisitors").val()},
			  success: function(data){
				   if(data.status){
					   $('#more-visitor-form').submit();
				   }else{
					   alert(data.message);
				   }
			  }
			});
            
        }
        
        function onActionRenderer(e) {
            var uid = e.record._uid;
            var s = '<span class="icon-remove" title="删除记录" onclick="delRow('+uid+')"></span>';
            return s;
        }
        
        
        $(document).ready(function(){
   	     $(document).bind("contextmenu",function(e){
   	         return false;
   	     });
   	    });
        </script>  
    
</html>