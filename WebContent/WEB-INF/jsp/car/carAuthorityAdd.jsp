<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>添加车辆授权</title>
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
	<script language="javascript" type="text/javascript" src="${ctx }/js/My97DatePicker/WdatePicker.js"></script>
	<link href="${ctx}/js/My97DatePicker/skin/WdatePicker.css" type="text/css" rel="stylesheet" />
	
	<script type="text/javascript">
	
	$(function(){
		$("#entityForm").submit(function(){
			var carno = $("#carno");
			var passageway_ids = "";
            $("input[name='passageway_ids']:checkbox").each(function(){ 
                if($(this).attr("checked")){
                	passageway_ids += $(this).val()+","
                }
            })
			var msg = "";
			if ($.trim(carno.val()) == ""){
				msg = "车牌号不能为空！";
			}else if ($.trim(passageway_ids) == ""){
				msg = "出入口不能为空！";
			}
			if (msg != ""){
				$.ligerDialog.error(msg);
				return false;
			}else{
				return true;
			}
			$("#entityForm").submit();
		});
		
		
		$('#park').change(function(){ 
			$.post(
				"${ctx}/car/selectByParkid",
				{packid:$(this).children('option:selected').val()},
				function(ways){
					$("#passageway_div").html("");
					for (var i = 0; i < ways.length; i++) {
						$("#passageway_div").append("<p><input type='checkbox' name='passageway_ids' value='"+ways[i].id+"' checked />&nbsp;&nbsp;"+ways[i].name+"</p><br/>");
					}
				}
			);
		}) 
		
    });
		

	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：车辆授权管理  &gt; 添加车辆授权</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/car/addcarAuthority" id="entityForm" method="post">
    	 <!-- 隐藏表单，flag表示添加标记 -->
    	 <input type="hidden" name="flag" value="2">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">车牌号：<input type="text" name="carno" id="carno" size="20"/></td>
	    			</tr>
	    			<tr>
		    			<td class="font3 fftd">
			    			停车场：
			    			<select name="park" id="park" style="width:143px;">
				    			<option value="">--请选择--</option>
				    			<c:forEach items="${parks}" var="park">
				    				<option value="${park.id }">${park.name }</option>
				    			</c:forEach>
				    		</select>
				    	</td>
			    	</tr>
	    			<tr>
		    			<td class="font3 fftd">
		    				<p>出入口</p><br/>
		    				<div id="passageway_div"></div>
		    			</td>
	    			</tr>
	    			<tr>
		    			<td class="font3 fftd">有效期：<input type="text" name="validate" id="validate" size="20"  class="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});" /></td>
		    		</tr>
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd"><input type="submit" value="&nbsp;授权&nbsp;">&nbsp;&nbsp;<input type="reset" value="&nbsp;取消&nbsp; "></td></tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>