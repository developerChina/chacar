<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>人事管理系统——添加部门</title>
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
	
	<script src="${ctx}/scripts/boot.js" type="text/javascript"></script>
	
	<script type="text/javascript">
	 $(function(){
	    	/** 部门表单提交 */
			$("#deptForm").submit(function(){
				var name = $("#name");
				var remark = $("#remark");
				var msg = "";
				if ($.trim(name.val()) == ""){
					msg = "部门名称不能为空！";
					name.focus();
				}else if ($.trim(remark.val()) == ""){
					msg = "详细描述不能为空！";
					remark.focus();
				}
				if (msg != ""){
					mini.showMessageBox({
	 		           showModal: false,
	 		            width: 250,
	 		            title: "提示",
	 		            message: msg,
	 		            timeout: 2000,
	 		            x:"center",
	 		            y:"top"
	 		        });
					return false;
				}else{
					return true;
				}
				$("#deptForm").submit();
			});
	    });
		
	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：部门管理  &gt; 添加部门</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="middle">
    <td>
    	 <form action="${ctx}/dept/addDept" id="deptForm" method="post">
    	 	<!-- 隐藏表单，flag表示添加标记 -->
    	 	<input type="hidden" name="flag" value="2">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr style="height:200px;">
		    			<td class="font3 fftd">
		    			父级部门：
		    			<input name="pid" id="pid" class="mini-treeselect" url="${ctx}/dept/selectAllDept" multiSelect="false"
		    			 textField="name" valueField="id" parentField="pid" expandOnLoad="false" size="20" style="width:150px;"/>
    					</td>
		    			<td class="font3 fftd">部门名称：<input type="text" name="name" id="name" size="20"/></td>
		    			<td class="font3 fftd">详细描述：<input type="text" name="remark" id="remark" size="20"/></td>
		    		</tr>
		    			
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd"><input type="submit" value="&nbsp;添加 &nbsp;">&nbsp;&nbsp;<input type="button" onclick="javascript:window.history.back(-1);"  value="&nbsp;返回&nbsp; "></td></tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>