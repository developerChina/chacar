<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>添加用户</title>
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
		
    	/** 员工表单提交git */
		$("#userFormadd").click(function(){
			
			var vals = [];
			$('input:checkbox:checked').each(function (index, item) {
			        vals.push($(this).val());
			});
			 
			//alert(vals);
			
			var username = $("#username");
			var status = $("#status");
			var loginname = $("#loginname");
			var password = $("#password");
			var msg = "";
			if ($.trim(username.val()) == ""){
				msg = "姓名不能为空！";
				username.focus();
			}else if ($.trim(status.val()) == ""){
				msg = "状态不能为空！";
				status.focus();
			}else if ($.trim(loginname.val()) == ""){
				msg = "登录名不能为空！";
				loginname.focus();
			}else if ($.trim(password.val()) == ""){
				msg = "密码不能为空！";
				password.focus();
			}else if (vals.length == 0){
				msg = "请配置一个系统！";
			}
			
			if (msg != ""){
				$.ligerDialog.warn(msg);
				return false;
			}else{
				/* return true; */
				$("#userForm").submit();
			}
			
		});
    });
	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：用户管理  &gt; 添加用户</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/user/addUser" id="userForm" method="post">
    	 	<!-- 隐藏表单，flag表示添加标记 -->
    	 	<input type="hidden" name="flag" value="2">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">姓&nbsp;名：<input type="text" name="username" id="username" size="20"/></td>
		    			<td class="font3 fftd">状&nbsp;态：<input type="text" name="status" id="status" size="20"/></td>
		    		</tr>
		    			
		    		<tr>
		    			<td class="font3 fftd">登录名：<input name="loginname" id="loginname" size="20" /></td>
		    			<td class="font3 fftd">密&nbsp;码：<input name="password" id="password" size="20" /></td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd" colspan="4">
		    			   <input type="checkbox" name="userPower" id="authority_1" value="1" checked/>系统管理
		    			   &nbsp;
		    			   <input type="checkbox" name="userPower" id="authority_2" value="2" checked/>员工管理 
		    			   &nbsp;
		    			   <input type="checkbox" name="userPower" id="authority_3" value="3" checked/>访客系统
		    			   &nbsp;
		    			   <input type="checkbox" name="userPower" id="authority_4" value="4" checked/>门禁系统
		    			   &nbsp;
		    			   <input type="checkbox" name="userPower" id="authority_5" value="5" checked/>梯控系统
		    			   &nbsp; 
		    			</td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd" colspan="4">
		    			   <input type="checkbox" name="userPower" id="authority_6" value="6" checked/>通道系统
		    			   &nbsp;
		    			   <input type="checkbox" name="userPower" id="authority_7" value="7" checked/>车辆系统
		    			   &nbsp;
		    			   <input type="checkbox" name="userPower" id="authority_8" value="8" checked/>定位系统
		    			   &nbsp;
		    			   <input type="checkbox" name="userPower" id="authority_9" value="9" checked/>排队叫号
		    			   &nbsp;
		    			   <input type="checkbox" name="userPower" id="authority_11" value="11" checked/>临时定位仪系统 
		    			   &nbsp;
		    			</td>
		    		</tr>
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd"><input type="button" id="userFormadd" value="&nbsp;添加&nbsp;">&nbsp;&nbsp;<input type="button" onclick="javascript:window.history.back(-1);"  value="&nbsp;返回 &nbsp;"></td></tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>