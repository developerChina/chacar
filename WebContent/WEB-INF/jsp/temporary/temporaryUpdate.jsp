<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>办理临时定位仪</title>
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
		$("#add").click(function(){
			var name = $("#name");
			var carno = $("#carno");
			var gps = $("#gps");
			var msg = "";
			if ($.trim(name.val()) == ""){
				msg = "车主名称不能为空！";
				name.focus();
			}else if ($.trim(carno.val()) == ""){
				msg = "车牌号不能为空！";
			}else if ($.trim(gps.val()) == ""){
				msg = "GPS号码不能为空！";
			}
			if (msg != ""){
				$.ligerDialog.warn(msg);
				return false;
			}else{
				$("#entityForm").submit();
			}
		});
    
/*end*/});
		

	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：车辆管理  &gt; 添加车辆</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/temporary/updateTemporary" id="entityForm" method="post">
    	 <!-- 隐藏表单，flag表示添加标记 -->
    	 <input type="hidden" name="flag" value="2">
    	 <!-- 发放状态  -->
    	 <input type="hidden" name="type" value="2">
    	  <input type="hidden" name="id" value="${entity.id}">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">车主名称：<input type="text" name="name" id="name"  value="${entity.name}" size="20"/></td>
		    			<td class="font3 fftd">车牌号：&nbsp;&nbsp;<input type="text" name="carno" id="carno" value="${entity.carno}" size="20"/></td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">联系人：&nbsp;&nbsp;<input type="text" name="contacts" id="contacts" value="${entity.contacts}" size="20"/></td>
		    			<td class="font3 fftd">联系电话：<input type="text" name="tel" id="tel" value="${entity.tel}"  size="20"/></td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">GPS号码：&nbsp;<input type="text" name="gps" id="gps" value="${entity.gps}" size="20"/></td>
		    			<td class="font3 fftd">备注：&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="remarks" id="remarks" value="${entity.remarks}"  size="20"/></td>
		    		</tr>
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd"><input type="button" id="add" value="&nbsp;&nbsp;完成修改&nbsp;&nbsp;">&nbsp;&nbsp;<input type="button" onclick="javascript:window.history.back(-1);" value="&nbsp;&nbsp;返回 &nbsp;&nbsp;"></td></tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>