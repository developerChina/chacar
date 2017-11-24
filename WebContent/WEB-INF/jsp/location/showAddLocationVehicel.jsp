<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>添加设备</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
	<link href="${ctx}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
	<script src="${ctx}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
	<script src="${ctx}/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	<script src="${ctx}/js/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
	<link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet" />
	<script language="javascript" type="text/javascript" src="${ctx }/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	var json={ "vehicleType":{"result":true, 
		"topics":[{"id":1,"use":"其他"},
		          {"id":2,"use":"出租车"},
		          {"id":3,"use":"小轿车"},
		          {"id":4,"use":"大型客车"},
		          {"id":5,"use":"中型客车"},
		          {"id":6,"use":"小型客车"},
		          {"id":7,"use":"运钞车"},
		          {"id":8,"use":"大型货车"},
		          {"id":9,"use":"中型货车"},
		          {"id":10,"use":"小型货车"},
		          {"id":11,"use":"货柜车"},
		          {"id":12,"use":"冷藏车"},
		          {"id":13,"use":"混凝土搅拌车"},
		          {"id":14,"use":"工程机械"},
		          {"id":15,"use":"混凝土搅拌车-小"},
		          {"id":16,"use":"小轿车ACC"},
		          {"id":17,"use":"出租车(计价器+拍照)"},
		          {"id":18,"use":"商砼车(正反转+油耗)"},
		          {"id":19,"use":"货车(ACC + 油耗)"},
		          {"id":20,"use":"翻斗车"},
		          {"id":21,"use":"货运装卸"},
		          {"id":22,"use":"汽车ACC"},
		          {"id":23,"use":"省际客运班车"},
		          {"id":24,"use":"市际客运班车"},
		          {"id":25,"use":"县际客运班车"},
		          {"id":26,"use":"旅游客运车辆"},
		          {"id":27,"use":"危险货物运输车辆"},
		          {"id":28,"use":"半挂牵引车"},
		          {"id":29,"use":"重型载货汽车"},
		          {"id":30,"use":"救援车"},
		          {"id":31,"use":"公交车"},
		          {"id":32,"use":"渣土车"},
		          {"id":33,"use":"公务车"}
		         ]
			}
	};
	
	
	
	var jsonw ={"ws": 
			{
			"ws1": 
		   		[	{"id":65536,"value":"温度传感器","checked":0}, 
		   			{"id":536870912,"value":"报警拍照","checked":0}, 
		   		 	{"id":262144,"value":"正反转传感器","checked":0}, 
		   		 	{"id":524288,"value":"门磁开关","checked":0}, 
		   		 	{"id":1048576,"value":"摄像头","checked":0}, 
		   		 	{"id":2097152,"value":"断油电","checked":0}, 
		   		 	{"id":4194304,"value":"LCD屏","checked":0}, 
		   		 	{"id":8388608,"value":"LED屏","checked":0}, 
		   		 	{"id":16777216,"value":"点火线","checked":0}, 
		   		 	{"id":33554432,"value":"车门信号线","checked":0}, 
		   		 	{"id":67108864,"value":"车灯信号线","checked":0}, 
		   		 	{"id":134217728,"value":"发动机信号线","checked":0}, 
		   		 	{"id":1073741824,"value":"双油箱","checked":0}, 
		   		 	{"id":131072,"value":"单线油耗传感器","checked":0}, 
		   		 	{"id":-2147483648,"value":"百分油耗传感器","checked":0}, 
		   		 	{"id":268435456,"value":"带总里程","checked":0}	
		   		 ], 
		   	"ws2":
		   		 [  {"id":65536,"value":"智能IC卡","checked":0}, 
		   			{"id":131072,"value":"载重传感器","checked":0}, 
		   			{"id":268435456,"value":"熄火不更新位置","checked":0}, 
		   			{"id":262144,"value":"双线油耗传感器","checked":0}, 
		   			{"id":524288,"value":"OBD数据","checked":0}, 
		   			{"id":1048576,"value":"货运数据","checked":0}, 
		   			{"id":2097152,"value":"脉冲里程","checked":0}, 
		   			{"id":4194304,"value":"湿度","checked":0}, 
		   			{"id":8388608,"value":"不使用基站定位","checked":0}, 
		   			{"id":1073741824,"value":"共享到车行天下","checked":0} 
		   		]
			}, 
		   	"bj":
		   	  { 
		   		"bj1":[ 
		   			   	{"id":1,"value":"防劫报警","checked":0}, 
		   			   	{"id":2,"value":"超速报警","checked":0}, 
		   			   	{"id":4,"value":"出区域报警","checked":0}, 
		   			   	{"id":8,"value":"入区域报警","checked":0}, 
		   			   	{"id":16,"value":"偏离路线报警","checked":0}, 
		   			   	{"id":32,"value":"电瓶拆除报警","checked":0}, 
		   			   	{"id":64,"value":"温度报警","checked":0}, 
		   			   	{"id":128,"value":"非法开门报警","checked":0},
		   			   	{"id":256,"value":"停车超时报警","checked":0}, 
		   			   	{"id":512,"value":"掉线报警","checked":0}, 
		   			   	{"id":1024,"value":"疲劳驾驶报警","checked":0},
		   			   	{"id":2048,"value":"天线开路报警","checked":0}, 
		   			   	{"id":4096,"value":"反转报警","checked":0}, 
		   			   	{"id":8192,"value":"翻斗升起报警","checked":0}, 
		   			   	{"id":16384,"value":"漏油报警","checked":0},
		   			   	{"id":32768,"value":"上线报警","checked":0} 
		   			  ], 
		   		"bj2":
		   			  [ 
		   			    {"id":1,"value":"震动报警","checked":0}, 
		   			    {"id":8,"value":"低电压报警","checked":0}, 
		   			    {"id":4,"value":"油量信号开路报警 ","checked":0},
		   			    {"id":16,"value":"停车点火报警","checked":0},
		   			    {"id":32,"value":"非法行驶报警","checked":0}, 
		   			    {"id":128,"value":"夜间接驳报警","checked":0}, 
		   			    {"id":256,"value":"非法拆除,长待机见光","checked":0},
		   			    {"id":512,"value":"行驶异常报警","checked":0}
		   			  ],
		   		}
	};
	
	var colorJson ={
			
			"color":{
				"result":true, 
				"topics":[
					 		{"id":10,"value":"其他"},
					 		{"id":11,"value":"黑色"},
					 		{"id":12,"value":"红色"},
					 		{"id":13,"value":"绿色"},
							{"id":14,"value":"黄色"},
							{"id":15,"value":"蓝色"},
					 		{"id":16,"value":"银色"},
					 		{"id":17,"value":"白色"},
					 		{"id":18,"value":"灰色"},
					 		{"id":10000,"value":"橙色"}
						]
					}
	};
	
			window.onload=function(){
				//车辆类型
				var sss = json;
				for (var i = 0; i < sss.vehicleType.topics.length; i++) {
					$("#vs").append("<option id='vehicleTypeId' value='"+sss.vehicleType.topics[i].id+"'>"+sss.vehicleType.topics[i].use+"</option>");
				}
				//复选框 外接设备
				var myshuju = jsonw;
				for (var j = 0; j < myshuju.ws.ws1.length; j++) {
					$("#ws").append("<input type='checkbox' name='ids' value='"+myshuju.ws.ws1[j].id+"'>"+myshuju.ws.ws1[j].value);
					if((j+1)%4==0){
						$("#ws").append("<br>");
					}
				}
				//车辆颜色
				var mycolor = colorJson;
				for (var j = 0; j < mycolor.color.topics.length; j++) {
					$("#color").append("<option value='"+mycolor.color.topics[j].id+"'>"+mycolor.color.topics[j].value+"</option>");
				}
			}
	</script>	
	
	
	
	<script type="text/javascript">
	    $(function(){
	    	/** 员工表单提交 */
			$("#LocationVehicelForm").submit(function(){
				var boxs  = $("input[type='checkbox'][id^='box_']");
				var myboxs  = $("input[type='checkbox'][name='ids']");
				var msg = "";
				var carName = $("#carName");
				var clientID = $("#clientID");
				var gprs = $("#gprs");
				var sim = $("#sim");
				var vs = $("#vs");
				var number = $("#number");
				var overduetime = $("#overduetime");
				var mobileId = $("#mobileId");
				var color = $("#color");
				var vedio = $("#vedio");
				if ($.trim(carName.val()) == ""){
					msg = "车牌号码不能为空！";
					carName.focus();
				}else if ($.trim(clientID.val()) == ""){
					msg = "所属客户id不能为空！";
					clientID.focus();
				}else if ($.trim(gprs.val()) == ""){
					msg = "设备id不能为空！";
					gprs.focus();
				}else if ($.trim(sim.val()) == ""){
					msg = "sim卡号不能为空！";
					sim.focus();
				}else if ($.trim(vedio.val()) == ""){
					msg = "语音号码不能为空！";
					vedio.focus();
				}else if ($.trim(overduetime.val()) == ""){
					msg = "过期时间不能为空！";
					overduetime.focus();
				}else if ($.trim(mobileId.val()) == ""){
					msg = "车机类型不能为空！";
					mobileId.focus();
				}else if ($.trim(vs.val()) == ""){
					msg = "车辆类型不能为空！";
					vs.focus();
				}else if ($.trim(number.val()) == ""){
					msg = "数据转发类型不能为空！";
					vs.focus();
				}else if ($.trim(color.val()) == ""){
					msg = "请选择车辆颜色！";
					color.focus();
				}
				else{
					var checkedBoxs = boxs.filter(":checked");
			 		   if(checkedBoxs.length < 1){
			 			  msg = " 请选择需要添加的分组！"; 
			 		   }
			 		  var checkedBoxs = myboxs.filter(":checked");
			 		   if(checkedBoxs.length < 1){
			 			  msg = " 请选择设备！"; 
			 		   }
				}
				
				if (msg != ""){
					$.ligerDialog.error(msg);
					return false;
				}else{
					return true;
				}
				$("#LocationVehicelForm").submit();
			});
	    });
	     
	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：设备管理  &gt; 添加设备</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/LocationVehicel/addLocationVehicel" id="LocationVehicelForm" method="post">
		 	<!-- 隐藏表单，flag表示添加标记 -->
    	 	<input type="hidden" name="flag" value="2">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
	<tr>
		<td class="font3 fftd">车牌号码：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="carName" id="carName" size="20"/></td>
		<td class="font3 fftd">过期时间：&nbsp;<input cssClass="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});"name="overduetime" id="overduetime" size="20"/>&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td class="font3 fftd">设备ID：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="gprs" id="gprs" size="20"/></td>
		<td class="font3 fftd">SIM卡号：&nbsp;&nbsp;<input name="sim" id="sim" size="20"/></td>
	</tr>
	<tr>
	<td class="font3 fftd">语音号码：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="vedio" id="vedio" size="20"/></td>
	<td class="font3 fftd">车标图片</td>
	</tr>
		    		<tr>
		    			<td class="font3 fftd">所属客户：&nbsp;&nbsp;&nbsp;&nbsp;
		    			 	<select name="clientID" id="clientID" style="width:143px;">
								<option disabled="disabled" selected = "selected">--请选择客户--</option>
								<c:forEach items="${requestScope.clientParts}" var="client">
								<option value="${client.id}">${client.compyname}</option>
								</c:forEach>
							</select>
					    </td>
					    <td class="font3 fftd">车机类型：
		    			 <select name="mobileId" id="mobileId" style="width:143px;">
					    			<option disabled="disabled" selected="selected">--请选择类型--</option>
					    			<option value="1" >智能语音播报类型</option>
					    			<option value="2">超宽显示屏</option>
					    			<option value="3">360°全息投影</option>
					    			<option value="4" >其他</option>
				 		</select>
				</td>
			</tr>
			<tr>
				<td class="font3 fftd">数据转发类型：
		    			 <select  style="width:143px;" name="number" id="number" >
					    		<option disabled="disabled" selected="selected">--请选择类型--</option>
					    		<option value="1" >直接交换</option>
					    		<option value="2">存储转发交换</option>
					    		<option value="3">改进的直接交换</option>
					    </select>
				</td>
				<td class="font3 fftd">车辆类型：
		    			 <select name="vehicleTypeId" id="vs" style="width:143px;">
					    			<option disabled="disabled" selected="selected">--请选择类型--</option>
					     </select>
				</td>
				
				<tr>
				
				<td class="font3 fftd">车辆颜色：&nbsp;&nbsp;&nbsp;&nbsp;
				    <select name="colorId" id="color" style="width:143px;">
					    <option disabled="disabled" selected="selected">--请选择颜色--</option>
				    </select>
				</td>
				<td class="font3 fftd">所属分组：<!-- <input type="text" name="cardId" id="cardId" size="20"/> -->
				<c:forEach items="${requestScope.groupParts}" var="group" varStatus="stat">
				    	<input type="checkbox" name="cardIds" id="box_${stat.index}"  value="${group.id}">${group.groupName}
				    	<c:if test="${stat.count % 5 == 0 }">
				    	<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    	</c:if>
		   		 </c:forEach>
				</td>
				</tr>
				
			<tr>
				<td class="font3 fftd">外接设备
				<div id="ws"></div>
				</td>
			</tr>
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			<tr><td align="left" class="fftd">
			<input type="submit" value="&nbsp;添加&nbsp;">&nbsp;&nbsp;<input type="button" onclick="javascript:window.history.back(-1);"  value="&nbsp;返回 &nbsp; "></td></tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>