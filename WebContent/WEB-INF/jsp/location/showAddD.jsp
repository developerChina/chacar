<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>添加车主信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
	
	$(function(){
		$("#addD").click(function(){
			var elevatorName = $("#elevatorName");
			var floorNumber = $("#floorNumber");
			var controllerSN = $("#controllerSN");
			var controllerIP = $("#controllerIP");
			var optdate = $("#optdate");
			var msg = "";
			if ($.trim(elevatorName.val()) == ""){
				msg = "车主名称不能为空！";
			}else if ($.trim(floorNumber.val()) == ""){
				msg = "车牌号码不能为空！";
			}else if ($.trim(controllerSN.val()) == ""){
				msg = "车辆类型不能为空！";
			}else if ($.trim(controllerIP.val()) == ""){
				msg = "操作类型不能为空！";
			}else if ($.trim(optdate.val()) == ""){
				msg = "操作时间不能为空！";
			}
			
			if (msg != ""){
				$.ligerDialog.warn(msg);
				return false;
			}
			
			$.ajax({
				  type: 'post',
				  url: '${ctx}/driver/addDValidate',
				  data: {
					  "vehicleCode":floorNumber.val()
				  },
				  success: function(data){
					if(data.status){
						$("#driverForm").submit();
				  	}else{
				  		$.ligerDialog.error(data.message)
				  	}
				  }
				})
			
		});
    });
		

	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：车主信息管理  &gt; 添加车主信息</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/driver/addDriver" id="driverForm" method="post">
    	 	<!-- 隐藏表单，flag表示添加标记 -->
    	 	<input type="hidden" name="flag" value="2">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">车主&nbsp;名称：&nbsp;
		    			<input name="name" id="elevatorName" type="text"  size="20"/></td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">车牌&nbsp;号码：&nbsp;
		    			<input name="vehicleCode" id="floorNumber" type="text" size="20"/></td>
		    		</tr>
		    		
		    		<tr>
		    			<td class="font3 fftd">车辆&nbsp;类型：&nbsp;
		    			<input name="cartType" id="controllerSN" type="text" size="20"/></td>
		    		</tr>
		    		
		    		<tr>
		    			<td class="font3 fftd">操作&nbsp;类型：&nbsp;
		    			<select name="type" id="controllerIP">
								<option disabled="disabled" selected="selected">-请选择操作的类型-</option>
								<option value="0">删除</option>
								<option value="1">修改</option>
						</select>
						</td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">操作&nbsp;时间：&nbsp;
		    			<input class="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'});" 
							name="optdate" id="optdate" size="20" />
						</td>
		    		</tr>
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd"><input type="button" id="addD" value="&nbsp;&nbsp;添加&nbsp;&nbsp;">&nbsp;&nbsp;<input type="button" onclick="javascript:window.history.back(-1);" value="&nbsp;&nbsp;返回 &nbsp;&nbsp;"></td></tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>