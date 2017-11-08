<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>修改电梯信息</title>
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
	<script type="text/javascript">
	
	$(function(){
    	/** 员工表单提交 */
		$("#elevatorForm").submit(function(){
			var elevatorName = $("#elevatorName");
			var floorNumber = $("#floorNumber");
			var controllerSN = $("#controllerSN");
			var controllerIP = $("#controllerIP");
			var msg = "";
			if ($.trim(elevatorName.val()) == ""){
				msg = "电梯名称不能为空！";
				elevatorName.focus();
			}else if ($.trim(floorNumber.val()) == ""){
				msg = "楼层编号不能为空！";
				floorNumber.focus();
			}else if ($.trim(controllerSN.val()) == ""){
				msg = "控制器SN不能为空！";
				controllerSN.focus();
			}else if ($.trim(controllerIP.val()) == ""){
				msg = "控制器IP不能为空！";
				controllerIP.focus();
			}
			if (msg != ""){
				$.ligerDialog.error(msg);
				return false;
			}else{
				return true;
			}
			$("#elevatorForm").submit();
		});
    });
		

	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：电梯管理  &gt; 修改电梯</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/elevator/updateElevator" id="elevatorForm" method="post">
    	 	<!-- 隐藏表单，flag表示添加标记 -->
    	 	<input type="hidden" name="flag" value="2">
			<input type="hidden" name="elevatorID" value="${myelevator.elevatorID}">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">电梯&nbsp;名称：<input type="text" name="elevatorName" id="elevatorName" value="${myelevator.elevatorName }" size="20"/></td>
		    			<td class="font3 fftd">楼层&nbsp;编号：<input type="text" name="floorNumber" id="floorNumber" value="${myelevator.floorNumber }" size="20"/></td>
		    		</tr>
		    			
		    		<tr>
		    			<td class="font3 fftd">控制器&nbsp;SN：<input name="controllerSN" id="controllerSN"  value="${myelevator.controllerSN }" size="20" /></td>
		    			<td class="font3 fftd">控制器&nbsp;IP：<input name="controllerIP" id="controllerIP" value="${myelevator.controllerIP }" size="20" /></td>
		    		</tr>
		    		
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd"><input type="submit" value="修改">&nbsp;&nbsp;<input type="button" onclick="javascript:window.history.back(-1);" value="返回 "></td></tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>