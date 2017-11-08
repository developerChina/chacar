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
    	/** 通道表单提交 */
		$("#accessForm").submit(function(){
			var accessname = $("#accessname");
			var csn = $("#csn");
			var cip = $("#cip");
			var acno = $("#acno")
			var floorno = $("#floorno")
			var msg = "";
			if ($.trim(accessname.val()) == ""){
				msg = "姓名不能为空！";
				accessname.focus();
			}else if ($.trim(csn.val()) == ""){
				msg = "控制器SN不能为空！";
				csn.focus();
			}else if ($.trim(cip.val()) == ""){
				msg = "控制器IP不能为空！";
				cip.focus();
			}else if($.trim(acno.val()) == ""){
				msg = "门禁编号不能为空！";
				acno.focus();
			}else if($.trim(floorno.val()) == ""){
				msg = "楼层编号不能为空！";
				floorno.focus();
			}
			if (msg != ""){
				$.ligerDialog.error(msg);
				return false;
			}else{
				return true;
			}
			$("#accessForm").submit();
		});
    });
		

	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：门禁管理  &gt; 修改门禁</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/floor/updateAccess" id="accessForm" method="post">
    	 	<!-- 隐藏表单，flag表示添加标记 -->
    	 	<input type="hidden" name="flag" value="2">
			<input type="hidden" name="accessid" value="${access.accessid }">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">门禁名称：<input type="text" name="accessname" id="accessname" size="20" value="${access.accessname }"/></td>
		    			<td class="font3 fftd">控制器SN：<input type="text" name="csn" id="csn" size="20" value="${access.csn }"/></td>
		    		</tr>
		    			
		    		<tr>
		    			<td class="font3 fftd">控制器IP:<input name="cip" id="cip" size="20" value="${access.cip }"/></td>
		    			<td class="font3 fftd">门禁编号:<input name="acno" id="acno" size="20" value="${access.acno}" /></td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">楼层编号:<input name="floorno" id="floorno" size="20" value="${access.floorno}" /></td>
		    		</tr>
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd"><input type="submit" value="修改 ">&nbsp;&nbsp;<input type="reset" value="返回 " onclick="javascript:window.history.back(-1);"></td></tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>