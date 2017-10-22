<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="${ctx}/css/visitor/common.css" />
		<link rel="stylesheet" href="${ctx}/css/visitor/print.css" />
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
		<div class="wrap">
			<div class="head">
				<h2>打印访客单</h2>
			</div>
			<div class="content clearfix">
				<div class="top">
					<div class="clearfix title">
						<span class="fl"></span>
						<span class="fl">打印列表</span>
					</div>
				</div>
				<div class="bottom clearfix">
					<div class="fl left">
						打印输入:<input type="text" class="text" id="cardno" title='身份证物理卡号'/>
						<input type="hidden" id="cardid" title='省份证号'/>
						<br/>
						<div id="datagrid1" class="mini-datagrid" style="width:455px;height:258px;" idField="cardName" multiSelect="true" showPager="false" allowSortColumn="false">
						      <div property="columns">
						      	  <div type="checkcolumn"></div>
						          <div field="bevisitedName" width="80" headerAlign="center">姓名</div>                
						          <div field="bevisitedAddress" width="200" headerAlign="center">办公地点</div>
						          <div field="auditContent" width="100" headerAlign="center">确认状态</div>
						      </div>
						</div>
					</div>
					<div class="right fl clearfix">
						<div class="choice">
							<div class="search">
								查询
								<span></span>
							</div>
							<div class="print">
								打印
								<span></span>
							</div>
						</div>
					</div>
				</div>
				<div class="btnArea clearfix">
						<input type="submit" class="fl search" value="查询" onclick="findRecord()"/>
						<input type="submit" class="fl print" value="打印" onclick="printRecord()"/>
					</div>
			</div>
			<div>
				<a href="${ctx}/vindex.jsp" class="foot">返回</a>
			</div>
		</div>
	</body>
	<script type="text/javascript">
	 mini.parse();
     var grid = mini.get("datagrid1");
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
     
     
	function findRecord(){
		var cardInfo=readIDCard();
		if(cardInfo.state){
			$("#cardid").val(cardInfo.message.code);
			$.ajax({
				  type: 'POST',
				  url: '${ctx}/visitor/selectRecordInfo',
				  data: {"cardid":cardInfo.message.code},
				  success: function(data){
					  grid.clearRows();//清除所有行，重新添加
					 for (var i = 0; i < data.length; i++) {
						 var row = {};
						 row["bevisitedName"]=data[i].bevisited.bevisitedName;
						 row["bevisitedAddress"]=data[i].bevisited.bevisitedAddress;
						 //isAudit;   // tinyint(4) NOT NULL COMMENT '是否同意（0=未审核，1=同意，2=拒绝）' ,
						 if(data[i].visitor.isAudit===0){
							 row["auditContent"]='未审核-'+(data[i].visitor.auditContent==null?'':data[i].visitor.auditContent);
						 }else if(data[i].visitor.isAudit===1){
							 row["auditContent"]='同意-'+(data[i].visitor.auditContent==null?'':data[i].visitor.auditContent);
						 }else if(data[i].visitor.isAudit===2){
							 row["auditContent"]='拒绝-'+(data[i].visitor.auditContent==null?'':data[i].visitor.auditContent);
						 }else{
							 row["auditContent"]=(data[i].visitor.auditContent==null?'':data[i].visitor.auditContent);
						 }
						 grid.addRow(row);
						 grid.beginEditRow(row);
					}
				  }
			});
		}else{
			alert(cardInfo.message);
		}
	}
     function printRecord(){
    	var cardid=$("#cardid").val();
    	var cardno=$("#cardno").val();
    	if(cardno=="" || cardid==''){
    		alert("请登记身份证信息");
    		return;
    	}
		$.ajax({
		  type: 'POST',
		  url: '${ctx}/visitor/printRecordInfo',
		  data: {"cardid":cardid,"cardno":cardno},
		  success: function(data){
			 alert(data);
		  }
		});
     }
	</script>
</html>
