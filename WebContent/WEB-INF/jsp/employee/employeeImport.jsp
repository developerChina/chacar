<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>导入员工</title>
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
	    	$("#uploadForm").submit(function(){
				var msg = "";
				var file = $("#file").val();
				if ($.trim(file) == ""){
					msg = "请选择上传文件！";
				}
				if (msg != ""){
					$.ligerDialog.warn(msg);
					return false;
				}else{
					return true;
				}
			});
	    	
	    	$("#template").click(function(){
	 		   var actionURL = $("#templateform").attr("action");
	 	       $("#templateform").attr("action","${ctx}/employee/exportTemplate");
	 	       $("#templateform").submit();
	 	   })
	    });
	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：员工管理  &gt; 导入员工</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<form name="templateform" method="post" id="templateform"></form>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/employee/importEmployee" id="uploadForm" method="post" enctype="multipart/form-data" >
		 	<!-- 隐藏表单，flag表示添加标记 -->
	   	 	<input type="hidden" name="flag" value="2">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
			<tr><td class="main_tdbor"></td></tr>
			<tr>
				<td class="font3 fftd">
					 <input type="button" name="template" id="template" value="&nbsp; 下载模板  &nbsp;"/>
				</td>
			</tr>
			<tr><td class="main_tdbor"></td></tr>
			<tr>
				<td class="font3 fftd">
					<input type="file" name="file" id="file" size="40"/>
				</td>
			</tr>
			 
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd">
			<input type="submit" value="&nbsp;导入&nbsp;">&nbsp;&nbsp;<input type="button" onclick="javascript:window.history.back(-1);"  value="&nbsp;返回 &nbsp;"></td></tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>