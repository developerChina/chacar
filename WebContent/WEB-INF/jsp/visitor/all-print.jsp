<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="${ctx}/css/visitor/common.css" />
		<link rel="stylesheet" href="${ctx}/css/visitor/print.css" />
		<script src="${ctx}/scripts/boot.js" type="text/javascript"></script>
		<link rel="stylesheet" href="${ctx}/css/sweet/sweetalert.css" />
		<script src="${ctx}/js/sweetalert.min.js" type="text/javascript"></script>
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
						<input type="hidden" id="cardno" title='身份证物理卡号'/>
						<input type="hidden" id="cardid" title='省份证号'/>
						<input type="hidden" id="name" title='访客姓名'/>
						<input type="hidden" id="phone" title='访客电话'/>
						<input type="hidden" id="company" title='工作单位'/>
						<input type="hidden" id="date" title='访问时间'/>
						<div id="datagrid1" class="mini-datagrid" style="width:455px;height:258px;" idField="cardName" multiSelect="true" showPager="false" allowSortColumn="false">
						      <div property="columns">
						          <div field="bevisitedName" width="80" headerAlign="center">姓名</div>
						          <div field="bevisitedAddress" width="200" headerAlign="center">办公地点</div>
						          <div field="auditContent" width="100" headerAlign="center">确认状态</div>
						          <div field="deptName" width="100" headerAlign="center" visible="false">部门</div>
						          <div field="isAudit" width="100" headerAlign="center" visible="false">是否同意</div>
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
					<input type="submit" class="fl search" value="查询" onclick="findRecordByRead()"/>
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
     
     
	function findRecordByRead(){
		var cardInfo=readIDCard();
		if(cardInfo.state){
			$("#cardid").val(cardInfo.message.code);
			findRecord(cardInfo.message.code);
		}else{
			alert(cardInfo.message);
		}
	}
	
	
	
	function findRecord(cardid){
		if(cardid==undefined || cardid==''){
	       	 alert('请扫描省份证');
	       	 return; 
        }
		$.ajax({
			  type: 'POST',
			  url: '${ctx}/visitor/selectRecordInfo',
			  data: {"cardid":cardid},
			  success: function(data){
				  grid.clearRows();//清除所有行，重新添加
				 for (var i = 0; i < data.length; i++) {
					 var row = {};
					 row["bevisitedName"]=data[i].bevisited.bevisitedName;
					 row["bevisitedAddress"]=data[i].bevisited.bevisitedAddress;
					 //isAudit;   // tinyint(4) NOT NULL COMMENT '是否同意（0=未审核，1=同意，2=拒绝）' ,
					 if(data[i].visitor.isAudit===0){
						 row["auditContent"]='未审核-'+(data[i].visitor.auditContent==null?'':":"+data[i].visitor.auditContent);
					 }else if(data[i].visitor.isAudit===1){
						 row["auditContent"]='同意-'+(data[i].visitor.auditContent==null?'':":"+data[i].visitor.auditContent);
					 }else if(data[i].visitor.isAudit===2){
						 row["auditContent"]='拒绝'+(data[i].visitor.auditContent==null?'':":"+data[i].visitor.auditContent);
					 }else{
						 row["auditContent"]=(data[i].visitor.auditContent==null?'':data[i].visitor.auditContent);
					 }
					 row["deptName"]=data[i].bevisited.deptName;
					 row["isAudit"]=data[i].visitor.isAudit;
					 grid.addRow(row);
					 grid.beginEditRow(row);
					 
					 $("#name").val(data[i].visitor.cardName);
					 $("#phone").val(data[i].visitor.telephone);
					 $("#company").val(data[i].visitor.company);
					 $("#date").val(data[i].date);
				}
			  }
		});
	}

     function printRecord(){
    	 
    	 
    	var cardid=$("#cardid").val();
    	if(cardid==''){
       		alert("请登记身份证信息");
       		return;
        }
    	
    	var bool=false;
    	var datagrid = mini.get("datagrid1");
   		datagrid.selectAll();
   		var nodes=datagrid.getSelecteds();
   		
   		if(nodes.length<=0){
   			return;
   		}
   		
   		for (var i = 0; i < nodes.length; i++) {
             if(nodes[i].isAudit==1){
            	 bool=true;
             }
   		}
    	
   		if(!bool){
   			
	   		$.ajax({
	      		  type: 'POST',
	      		  url: '${ctx}/visitor/printRecordInfo',
	      		  data: {"cardid":cardid,"cardno":""},
	      		  success: function(data){
	      			 findRecord($("#cardid").val());//刷新
	      		  }
	      	});
   			
   			alert("你的申请被拒绝，不能打印");
   			return;
   		}
    	 
	   	 swal({
	         title: "请在右侧读取身份证信息",
	         text: "",
	         type: "input",
	         showCancelButton: true,
	         closeOnConfirm: false,
	         animation: "slide-from-top",
	         inputPlaceholder: ""
	     }, function(inputValue) {
	         if (inputValue === false)
	             return false;
	         if (inputValue === "") {
	             swal.showInputError("请输入!");
	             return false
	         }
	          
	        re = /[\u4E00-\u9FA5]/g; //测试中文字符的正则
          	if (re.test(inputValue)) //使用正则判断是否存在中文
          	{
          		alert("请勿手动输入");
           		return;
          	}
          	
          	$("#cardno").val(inputValue);
            var cardno=$("#cardno").val();
          	
            $.ajax({
         		  type: 'POST',
         		  url: '${ctx}/visitor/printRecordInfo',
         		  data: {"cardid":cardid,"cardno":cardno},
         		  success: function(data){
         			//自动关闭
       	            swal({
       				  title: "",
       				  text: "",
       				  timer: 0,
       				  showConfirmButton: false
       				});
         			findRecord($("#cardid").val());//刷新
         			$("#cardid").val('');
         		  }
         	});
           	 
           	//获取打印数据
       		var datagrid = mini.get("datagrid1");
       		datagrid.selectAll();
       		var nodes=datagrid.getSelecteds();
       		for (var i = 0; i < nodes.length; i++) {
                   if(nodes[i].isAudit==1){
                   	/**
                   	printTicket(
               			$("#name").val(),
               			$("#phone").val(),
               			$("#company").val(),
               			nodes[i].bevisitedName,
               			nodes[i].deptName,
               			$("#date").val()
                   	);
                   	*/
                  	$.ajax({
               		  type: 'POST',
               		  url: '${ctx}/visitor/mySelfPrint',
               		  data: {"serIp":"192.168.1.110:8756","cardName":$("#name").val(),"telephone":$("#phone").val(),"unit":$("#company").val(),"bevisitedName":nodes[i].bevisitedName,"dept":nodes[i].deptName,"visitDate":$("#date").val()},
               		  success: function(data){
               			 
               		  }
               	});
              }
       		}
	         
	         
	     });
    	 
     }
     
     
     /**
     * cardName 访客姓名
     * bevisitedName 被访人姓名
     * telephone 访客电话
     * dept 被访人部门
     * unit	访客工作单位
     * visitDate 访问时间
     */
     function printTicket(cardName,telephone,unit,bevisitedName,dept,visitDate){
    	 var TSCObj;
    	 TSCObj = new ActiveXObject("TSCActiveX.TSCLIB");
    	 TSCObj.ActiveXopenport ("Gprinter  GP-1625D");
    	 TSCObj.ActiveXdownloadpcx ("C:/Windows/System/UL.PCX","UL.PCX");

    	 TSCObj.ActiveXclearbuffer();
    	 TSCObj.ActiveXwindowsfont (280, 10, 68, 0, 2, 0, "标楷体", "访客单");
    	 TSCObj.ActiveXwindowsfont (320, 80, 45, 0, 2, 0, "标楷体", "Guest");
    	 TSCObj.ActiveXwindowsfont (40, 150, 30, 0, 0, 0, "标楷体", "姓名："+cardName);
    	 TSCObj.ActiveXwindowsfont (265, 150, 30, 0, 0,0, "标楷体", "被访人："+bevisitedName);

    	 TSCObj.ActiveXwindowsfont (40, 200, 30, 0, 0, 0, "标楷体", "电话："+telephone);
    	 TSCObj.ActiveXwindowsfont (265, 200, 30, 0, 0, 0, "标楷体", "被访部门："+dept);

    	 TSCObj.ActiveXwindowsfont (40, 250, 30, 0, 0, 0, "标楷体", "单位："+unit);

    	 TSCObj.ActiveXwindowsfont (40, 300, 30, 0, 0, 0, "标楷体", "日期："+visitDate);

    	 TSCObj.ActiveXprintlabel ("1","1");
    	 TSCObj.ActiveXcloseport();		 
     }
	</script>
</html>
