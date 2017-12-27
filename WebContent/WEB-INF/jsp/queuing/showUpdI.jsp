<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html> 
	<head>
	<title>修改卸货岛</title>
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
	
	window.onload=function(){
		
		document.getElementById("rnameColor").style.color="green";
		document.getElementById("rnameColor").innerText="√";
		
		document.getElementById("cameraipColor").style.color="green";
		document.getElementById("cameraipColor").innerText="√";
		
		document.getElementById("big_screenipColor").style.color="green";
		document.getElementById("big_screenipColor").innerText="√";
		
		document.getElementById("small_screenipColor").style.color="green";
		document.getElementById("small_screenipColor").innerText="√";
	}
	
	/* 卸货岛编号光标移开事件 */
	function noControl(){
		var no = $("#no").val();
		if ($.trim(no)!=""){
			document.getElementById("noColor").style.color="green";
			document.getElementById("noColor").innerText="√";
		}else{
			document.getElementById("noColor").style.color="red";
			document.getElementById("noColor").innerText="*必填";
		}
	}
	
	
	/* 卸货岛名称光标移开事件 */
	function rnameControl(){
		var iname = $("#iname").val();
		if ($.trim(iname)!=""){
			//alert(iname);
			document.getElementById("rnameColor").style.color="green";
			document.getElementById("rnameColor").innerText="√";
		}else{
			document.getElementById("rnameColor").style.color="red";
			document.getElementById("rnameColor").innerText="*必填";
		}
	}
	/* 识别仪的ip光标移开事件 */
	function cameraipControl(){
		var cameraip = $("#cameraip").val();
		if ($.trim(cameraip)!=""){
			document.getElementById("cameraipColor").style.color="green";
			document.getElementById("cameraipColor").innerText="√";
		}else{
			document.getElementById("cameraipColor").style.color="red";
			document.getElementById("cameraipColor").innerText="*必填";
		}
	}
	/* 大屏幕的ip光标移开事件 */
	function big_screenipControl(){
		var big_screenip = $("#big_screenip").val();
		if ($.trim(big_screenip)!=""){
			document.getElementById("big_screenipColor").style.color="green";
			document.getElementById("big_screenipColor").innerText="√";
		}else{
			document.getElementById("big_screenipColor").style.color="red";
			document.getElementById("big_screenipColor").innerText="*必填";
		}
	}
	/* 小屏幕的ip光标移开事件 */
	function small_screenipControl(){
		var small_screenip = $("#small_screenip").val();
		if ($.trim(small_screenip)!=""){
			document.getElementById("small_screenipColor").style.color="green";
			document.getElementById("small_screenipColor").innerText="√";
		}else{
			document.getElementById("small_screenipColor").style.color="red";
			document.getElementById("small_screenipColor").innerText="*必填";
		}
	}
	
	$(function(){
		
		$("#IslandUpForm").submit(function(){
			var iname = $("#iname");
			var cameraip = $("#cameraip");
			var big_screenip = $("#big_screenip");
			var small_screenip = $("#small_screenip");
			
			var msg = "";
			if ($.trim(iname.val()) == ""){
				msg = "请填写卸货岛名称！";
			}else if ($.trim(cameraip.val()) == ""){
				msg = "请填写识别仪的IP！";
			}else if ($.trim(big_screenip.val()) == ""){
				msg = "请填写大屏幕的IP！";
			}else if ($.trim(small_screenip.val()) == ""){
				msg = "请填写小屏幕的IP！";
			}
			
			if (msg != ""){
				
				$.ligerDialog.error(msg);
				return false;
			}else{
				
				return true;
			}
			$("#IslandUpForm").submit();
		});
    });
		

	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：卸货岛管理  &gt; 修改卸货岛</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/queuingI/updateIslandAck" id="IslandUpForm" method="post">
    	 	<!-- 隐藏表单，flag表示添加标记 -->
    	 	<input type="hidden" name="flag" value="2">
    	 	<input type="hidden" name="id" value="${updateI.id}">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">卸货岛编号：</td>
		    			<td><input name="no" id="no" type="hidden" value="${updateI.no}" size="20" onblur="noControl()"/>
		    			${updateI.no}
		    			</td>
		    			<td><font id="noColor" color="red"></font></td>
		    		</tr>
		    		
		    		<tr>
		    			<td class="font3 fftd">卸货岛名称：</td>
		    			<td><input name="iname" id="iname" value="${updateI.iname}" type="text"  size="20" onblur="rnameControl()"/>
		    			</td>
		    			<td><font id="rnameColor" color="red">*必填&nbsp;</font></td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">识别仪IP：</td>
		    			<td><input name="cameraip" id="cameraip" value="${updateI.cameraip}" type="text"  size="20" onblur="cameraipControl()"/>
		    			</td>
		    			<td><font id="cameraipColor" color="red">*必填&nbsp;</font></td>
		    		</tr>
		    		
		    		<tr>
		    			<td class="font3 fftd">大屏幕IP：</td>
		    			<td><input name="big_screenip" id="big_screenip" value="${updateI.big_screenip}" type="text"  size="20" onblur="big_screenipControl()"/>
		    			</td>
		    			<td><font id="big_screenipColor" color="red">*必填&nbsp;</font></td>
		    		</tr>
		    		
		    		
		    		<tr>
		    			<td class="font3 fftd">小屏幕IP：</td>
		    			<td><input name="small_screenip" id="small_screenip" value="${updateI.small_screenip}" type="text"  size="20" onblur="small_screenipControl()"/>
		    			</td>
		    			<td><font id="small_screenipColor" color="red">*必填&nbsp;</font></td>
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