<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>定位管理系统——添加定位</title>
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
	<script type="text/javascript">
	
	$(function(){
    	/** 通道表单提交 */
		$("#LocatorForm").submit(function(){
			var name = $("#name");
			var sim = $("#sim");
			var deviceid = $("#deviceid");
			var type = $("#type");
			var msg = "";
			if ($.trim(name.val()) == ""){
				msg = "定位仪名称不能为空！";
				name.focus();
			}else if ($.trim(sim.val()) == ""){
				msg = "sim卡号不能为空！";
				sim.focus();
			}else if ($.trim(deviceid.val()) == ""){
				msg = "设备id不能为空！";
				deviceid.focus();
			}
			if (msg != ""){
				$.ligerDialog.warn(msg);
				return false;
			}else{
				return true;
			}
			$("#LocatorForm").submit();
		});
    });
		

	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：定位仪管理  &gt; 添加定位仪</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/Locator/addLocationLocator" id="LocatorForm" method="post">
    	 	<!-- 隐藏表单，flag表示添加标记 -->
    	 	<input type="hidden" name="flag" value="2">
			<input type="hidden" name="id" value="${locationLocator.id}">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">定位仪名称:<input type="text" name="name" id="name" size="20" /></td>
		    			<td class="font3 fftd">设备id:<input type="text" name="deviceid" id="deviceid" size="20" /></td>
		    			<td class="font3 fftd">SIM卡号:<input type="text" name="sim" id="sim" size="20" /></td>
		    			<td class="font3 fftd">定位仪类型:
		    			<select name="type">
					<option disabled="disabled">-请选择定位仪类型-</option>
					<option value="1">固定定位仪</option>
					<option value="0" selected = "selected">临时定位仪</option>
					</select>
						</td>
		    		</tr>
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd"><input type="submit" value="&nbsp;&nbsp;添加&nbsp;&nbsp; ">&nbsp;<input type="reset" value="&nbsp;&nbsp;返回&nbsp;&nbsp; " onclick="javascript:window.history.back(-1);"></td></tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>