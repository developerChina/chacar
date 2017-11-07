<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>电梯组的授权</title>
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
		            
		  var boxs  = $("input[type='checkbox'][id^='box_']");
		  // 给其授权 按钮添加 点击事件
		  $("#upEG").click(function(){
			  var myejname =document.getElementById("myejname").value;
			  var empid =document.getElementById("empname").value;
			  var cardno =document.getElementById("cardno").value;
			  var msg = "";
		 		if ($.trim(myejname) == ""){
					msg = "授权名称不能为空！";
				}
		 		if (msg != ""){
					$.ligerDialog.error(msg);
					return false;
				}else{
					/** 获取到用户选中的复选框  */
		      		   var checkedBoxs = boxs.filter(":checked");
		      		   if(checkedBoxs.length < 1){
		      			   $.ligerDialog.error(" 请选择一个电梯或电梯组！");
		      		   }else{
		      			   var ids = checkedBoxs.map(function(){
		      				   return this.value;
		      			   })
		      			   
		      			   $.ligerDialog.confirm("确认要修改吗?","修改电梯组",function(r){
		      				   if(r){
		      					    //alert("卡号："+cardno+"员工"+empid+"删除："+ids.get()+"授权名"+myejname);
		      					   // 发送请求
		      					   window.location = "${ctx}/Jurisdiction/getParts?flag=2&ejgroup=" + ids.get()+"&ejname="+myejname+"&ejemp="+empid+"&ejcard"+cardno;
		      				   }
		      			  	 });
		      		   		}
				}
		 		
		 		
/* 点击事件end */  })
 
		   /* end */});
	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：电梯的绑定授权  &gt; 添加授权</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">给当前员工添加权限：<input type="hidden" name="empname" id="empname" value="${findEmployeeById.id}" size="20"/>
		    			${findEmployeeById.name}
		    			
		    			<input type="hidden" name="cardno" id="cardno" value="${findEmployeeById.cardno}" size="20"/>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">授权名称：<input type="text" name="ejname" id="myejname"  size="20"/>
		    			</td>
		    		</tr>
		    		
		    		<tr>
		    			<td class="font3 fftd">请选择一个分组：<br/><br/>
		    			<c:forEach items="${elevatorGroups}" var="EGroups" varStatus="stat">
         				<input type="checkbox" name="media" id="box_${stat.index}" value="${EGroups.egid}"/>
         					${EGroups.egname}
         					(<c:forEach items="${EGroups.orderItems}" var="E">
					  		${E.elevatorName}&nbsp;
					  		</c:forEach>)
					  		<br/>
		    			</c:forEach>
		    			</td>
		    		</tr>
		    		<tr>
		    		<td>
		    		<hr>
		    		</td>
		    		<tr>	
		    		<tr>
		    		<td class="font3 fftd">请选择一个电梯：
		    			<c:forEach items="${egElevators}" var="Ename" varStatus="stat">
         				<input type="checkbox" name="media" id="box_${stat.index}" value="${Ename.elevatorID}"/>${Ename.elevatorName}&nbsp;&nbsp;
		    			</c:forEach>
		    		</td>
		    		<tr>
		    		
		    		
		    		
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd"><input type="button" id="upEG" value="给其授权">&nbsp;&nbsp;<input type="button" onclick="javascript:window.history.back(-1);" value="取消 "></td></tr>
		  </table>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>