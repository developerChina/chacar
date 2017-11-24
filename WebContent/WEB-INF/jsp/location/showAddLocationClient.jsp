<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>客户管理系统——添加客户</title>
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
		$("#ClientForm").submit(function(){
			var compyname = $("#compyname");
			var phone = $("#phone");
			var officeaddrs = $("#officeaddrs");
			var beacon = $("#beacon");
			var msg = "";
			if ($.trim(compyname.val()) == ""){
				msg = "企业名称不能为空！";
				compyname.focus();
			}else if ($.trim(phone.val()) == ""){
				msg = "企业电话不能为空！";
				phone.focus();
			}else if ($.trim(officeaddrs.val()) == ""){
				msg = "企业地址不能为空！";
				officeaddrs.focus();
			}else if ($.trim(beacon.val()) == ""){
				msg = "法人代表不能为空！";
				beacon.focus();
			}
			if (msg != ""){
				$.ligerDialog.error(msg);
				return false;
			}else{
				return true;
			}
			$("#ClientForm").submit();
		});
    });
	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：客户管理  &gt; 添加客户信息</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/Client/addLocationClient" id="ClientForm" method="post">
    	 	<!-- 隐藏表单，flag表示添加标记 -->
    	 	<input type="hidden" name="flag" value="2">
			<input type="hidden" name="id" value="${locationClient.id}">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">企业名称:<input type="text" name="compyname" id="compyname" size="20" /></td>
		    			<td class="font3 fftd">企业电话:<input type="text" name="phone" id="phone" size="20" /></td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">企业地址:<input name="officeaddrs" id="officeaddrs" size="20" /></td>
		    			<td class="font3 fftd">法人代表:<input name="beacon" id="beacon" size="20" /></td>
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