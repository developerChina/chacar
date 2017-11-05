<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>添加出入口</title>
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
			var type = $("#type");
			var park_id = $("#park_id");
			var distinguish_ids = "";
            $("input[name='distinguish_ids']:checkbox").each(function(){ 
                if($(this).attr("checked")){
                	distinguish_ids += $(this).val()+","
                }
            })
			var msg = "";
			if ($.trim(name.val()) == ""){
				msg = "名称不能为空！";
				name.focus();
			}else if ($.trim(type.val()) == ""){
				msg = "类型不能为空！";
				type.focus();
			}else if ($.trim(park_id.val()) == ""){
				msg = "所属停车场不能为空！";
			}else if ($.trim(distinguish_ids) == ""){
				msg = "识别仪列表不能为空！";
			}
			if (msg != ""){
				$.ligerDialog.error(msg);
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
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：出入口管理  &gt; 添加出入口</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/car/addcarPassageway" id="entityForm" method="post">
    	 <!-- 隐藏表单，flag表示添加标记 -->
    	 <input type="hidden" name="flag" value="2">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">出入口名称：<input type="text" name="name" id="name" size="20"/></td>
		    			<td class="font3 fftd">
		    				出入口类型：
	    					<select name="type" id="type" style="width:143px;">
	    					    <option value="">--请选择--</option>
				    			<option value="0">入口</option>
				    			<option value="1">出口</option>
				    			<option value="2">入/出口</option>
				    		</select>
					    </td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">
			    			所属停车场：
			    			<select name="park_id" id="park_id" style="width:143px;">
				    			<option value="">--请选择--</option>
				    			<c:forEach items="${parks}" var="park">
				    				<option value="${park.id }">${park.name }</option>
				    			</c:forEach>
				    		</select>
		    			</td>
		    			<td class="font3 fftd">
		    				<p>识别仪列表</p><br/>
		    				<c:forEach items="${distinguishs}" var="distinguish">
			    				<p>
				    				<input type="checkbox" name="distinguish_ids" id="distinguish_ids"  value="${distinguish.id }"/>&nbsp;&nbsp;${distinguish.name }(${distinguish.ip })
				    			</p><br/>
			    			</c:forEach>
		    			</td>
		    		</tr>
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd"><input type="submit" value="添加">&nbsp;&nbsp;<input type="reset" value="取消 "></td></tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>