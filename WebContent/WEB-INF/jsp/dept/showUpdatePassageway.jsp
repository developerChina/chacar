<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>门禁管理系统——修改门禁</title>
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
		
		// 控制文档加载完成以后 选中性别 
		$("#ptype").val("${passageway.ptype}");
		
    	/** 通道表单提交 */
		$("#passagewayForm").submit(function(){
			var passagewayName = $("#passagewayName");
			var ControllerSN = $("#ControllerSN");
			var ControllerIP = $("#ControllerIP");
			
			var msg = "";
			if ($.trim(passagewayName.val()) == ""){
				msg = "通道名称不能为空！";
				passagewayName.focus();
			}else if ($.trim(ControllerSN.val()) == ""){
				msg = "控制器SN不能为空！";
				ControllerSN.focus();
			}else if ($.trim(ControllerIP.val()) == ""){
				msg = "控制器IP不能为空！";
				ControllerIP.focus();
			}
			if (msg != ""){
				$.ligerDialog.error(msg);
				return false;
			}else{
				return true;
			}
			$("#passagewayForm").submit();
		});
    });
		

	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：通道管理  &gt; 修改通道</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/door/updatePassageway" id="passagewayForm" method="post">
    	 	<!-- 隐藏表单，flag表示添加标记 -->
    	 	<input type="hidden" name="flag" value="2">
			<input type="hidden" name="passagewayID" value="${passageway.passagewayID}">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">通道名称:<input type="text" name="passagewayName" id="passagewayName" size="20" value="${passageway.passagewayName}"/></td>
		    			<td class="font3 fftd">控制器SN:<input type="text" name="ControllerSN" id="ControllerSN" size="20" value="${passageway.controllerSN}"/></td>
		    			<td class="font3 fftd">通道类型:
					<select name="ptype" id="ptype" style="width:143px;">
					<option disabled="disabled">-请选择通道类型-</option>
					<option value="1">进入通道</option>
					<option value="0">离开通道</option>
					</select>
					</td>
		    		</tr>
		    			
		    		<tr>
		    			<td class="font3 fftd">控制器IP:<input name="ControllerIP" id="ControllerIP" size="20" value="${passageway.controllerIP}"/></td>
		    			<td class="font3 fftd">通道编号:<input name="pno" id="pno" size="20" value="${passageway.pno}"/></td>
		    		</tr>
		    		
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd"><input type="submit" value="&nbsp;&nbsp;修改&nbsp;&nbsp; ">&nbsp;<input type="reset" value="&nbsp;&nbsp;返回 &nbsp;&nbsp;" onclick="javascript:window.history.back(-1);"></td></tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>