<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>修改车辆</title>
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
		$("#entityForm").submit(function(){
			var name = $("#name");
			var carno = $("#carno");
			var msg = "";
			if ($.trim(name.val()) == ""){
				msg = "车主姓名不能为空！";
				name.focus();
			}else if ($.trim(no.val()) == ""){
				msg = "车牌号不能为空！";
				no.focus();
			}
			if (msg != ""){
				$.ligerDialog.warn(msg);
				return false;
			}else{
				return true;
			}
			$("#entityForm").submit();
		});
    });
		

	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：车辆管理  &gt; 修改车辆</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/car/updatecarInfo" id="entityForm" method="post">
    	 <!-- 隐藏表单，flag表示添加标记 -->
    	 <input type="hidden" name="flag" value="2">
    	 <input type="hidden" name="id" value="${car.id }">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">车主姓名：<input type="text" name="name" id="name" size="20" value="${car.name }"/></td>
		    			<td class="font3 fftd">车牌号：&nbsp;&nbsp;<input type="hidden" name="carno" id="carno" size="20" value="${car.carno}"/>${car.carno}</td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">联系电话：<input type="text" name="tel" id="tel" size="20" value="${car.tel}"/></td>
		    			<td class="font3 fftd">身份证号：<input type="text" name="idNumber" id="idNumber" size="20" value="${car.idNumber}"/></td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">工号：&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="workNumber" id="workNumber" size="20" value="${car.workNumber}"/></td>
		    			<td class="font3 fftd">所在单位：<input type="text" name="company" id="company" size="20" value="${car.company}"/></td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">班组：&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="team" id="team" size="20" value="${car.team}"/></td>
		    			<td class="font3 fftd">岗位职务：<input type="text" name="job" id="job" size="20" value="${car.job}"/></td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">品牌型号：<input type="text" name="model" id="model" size="20" value="${car.model}"/></td>
		    			<td class="font3 fftd">车辆属性：<input type="text" name="attribute" id="attribute" size="20" value="${car.attribute}"/></td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">颜色：&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="colour" id="colour" size="20" value="${car.colour}"/></td>
		    		</tr>
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd"><input type="submit" value="&nbsp;&nbsp;修改&nbsp;&nbsp;">&nbsp;&nbsp;<input type="button" onclick="javascript:window.history.back(-1);" value="&nbsp;&nbsp;返回 &nbsp;&nbsp;"></td></tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>