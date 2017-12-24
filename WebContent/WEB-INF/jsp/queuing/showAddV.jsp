<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>添加VIP队列</title>
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
	
	function car_codeControl(){
		var car_code = $("#car_code").val();
		if ($.trim(car_code)!=""){
			document.getElementById("car_codeColor").style.color="green";
			document.getElementById("car_codeColor").innerText="√";
		}else{
			document.getElementById("car_codeColor").style.color="red";
			document.getElementById("car_codeColor").innerText="*必填";
		}
	}
	
	function remarksControl(){
		var remarks = $("#remarks").val();
		if ($.trim(remarks)!=""){
			document.getElementById("remarksColor").style.color="green";
			document.getElementById("remarksColor").innerText="√";
		}else{
			document.getElementById("remarksColor").style.color="red";
			document.getElementById("remarksColor").innerText="*必填";
		}
	}
	
	

	
	
	
	$(function(){
		
		$('#island_no').change(function(){
			var rtypeid=$(this).children('option:selected').val()
			if(rtypeid!=""){
				document.getElementById("island_noColor").style.color="green";
				document.getElementById("island_noColor").innerText="√";
			}
		});
		
		
		
    	/* VIP队列添加提交 事件  */
		$("#VipAddForm").submit(function(){
			var car_code = $("#car_code");
			var island_no = $("#island_no");
			var remarks = $("#remarks");
			
			var msg = "";
			if ($.trim(car_code.val()) == ""){
				msg = "请填写车牌号！";
			}else if ($.trim(island_no.val()) == ""){
				msg = "请选择卸货岛！";
			}else if ($.trim(remarks.val()) == ""){
				msg = "请填写原因！";
			}
			
			if (msg != ""){
				
				$.ligerDialog.error(msg);
				return false;
			}else{
				
				return true;
			}
			$("#VipAddForm").submit();
		});
    });
	
	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：VIP队列管理  &gt; 添加VIP队列</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/queuingV/VipAdd" id="VipAddForm" method="post">
    	 	<!-- 隐藏表单，flag表示添加标记 -->
    	 	<input type="hidden" name="flag" value="2">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>

		    		<tr>
		    		<td class="font3 fftd">车牌号：</td>
		    		<td><input name="car_code" id="car_code" type="text"  size="20" onblur="car_codeControl()"></td>
		    		<td><font id="car_codeColor" color="red">*必填&nbsp;</font></td>
		    		</tr>
		    		
		    		<%-- <tr>
		    		<td class="font3 fftd">排号位置：</td>
		    		<td><input name="queue_number" id="queue_number" value="${maxnum}" type="number"  size="20" onblur="queue_numberControl()"></td>
		    		<td><font id="queue_numberColor" color="red">*必填&nbsp;</font></td>
		    		</tr> --%>
		    		
		    		<tr>
		    			<td class="font3 fftd">选择卸货岛：</td>
		    			<td>
		    				<select name="island_no" id="island_no" >
								<option disabled="disabled" selected="selected">-请选择卸货岛-</option>
								<c:forEach items="${requestScope.AddVgetI}" var="avi" varStatus="stat">
								<option value="${avi.no}" >${avi.iname}</option>
								</c:forEach>
							</select>
		    			</td>
		    			<td><font id="island_noColor" color="red">*必选&nbsp;</font></td>
		    		</tr>
		    		
		    		
		    		<tr>
		    		<td class="font3 fftd">添加原因：</td>
		    		<td>
		    		<textarea name="remarks" id="remarks" style="width: 180px; height: 80px" onblur="remarksControl()"></textarea>
		    		</td>
		    		<td><font id="remarksColor" color="red">*必填&nbsp;</font></td>
		    		</tr>
		    		
		    	</table>
		    	
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			<tr><td align="left" class="fftd"><input type="submit" value="&nbsp;&nbsp;添加&nbsp;&nbsp;">&nbsp;&nbsp;<input type="button" onclick="javascript:window.history.back(-1);" value="&nbsp;&nbsp;返回 &nbsp;&nbsp;"></td></tr>
		  </table>
		  
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>