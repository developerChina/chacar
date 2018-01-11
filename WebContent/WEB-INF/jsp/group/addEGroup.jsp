<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>添加电梯分组</title>
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
	 	  /** 给全选按钮绑定点击事件  */
	    	$("#checkAll").click(function(){
	    		// this是checkAll  this.checked是true
	    		// 所有数据行的选中状态与全选的状态一致
	    		boxs.attr("checked",this.checked);
	    	})
	 	  /** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
	    	$("tr[id^='data_']").hover(function(){
	    		$(this).css("backgroundColor","#eeccff");
	    	},function(){
	    		$(this).css("backgroundColor","#ffffff");
	    	})
			
	    	
	    	
		 	$("#addEtoEG").click(function(){
		 		/** 获取到用户填写的文本框  */
		 		var myegname =document.getElementById("myegname").value;
		 		var msg = "";
		 		if ($.trim(myegname) == ""){
					msg = "电梯组名称不能为空！";
				}
		 		if (msg != ""){
					$.ligerDialog.warn(msg);
					return false;
				}else{
					/** 获取到用户选中的复选框  */
			 		   var checkedBoxs = boxs.filter(":checked");
			 		   if(checkedBoxs.length < 1){
			 			   $.ligerDialog.warn(" 请选择一个需要添加的电梯！");
			 		   }else{
			 			   /** 得到用户选中的所有的需要添加的ids */
			 			   var ids = checkedBoxs.map(function(){
			 				   return this.value;
			 			   })
			 			   
			 			   $.ligerDialog.confirm("确认要添加吗?","添加电梯到电梯组 ",function(r){
			 				   if(r){
			 					   // alert(myegname+"删除："+ids.get());
			 					   // 发送请求
			 					   $("#addEgroup").submit();
			 					  /* window.location ="${ctx }/Grouping/addEGroup?flag=2&egname="
			 						 +myegname+"&ids="+ids.get(); */
			 				   }
			 			   });
			 		   }
			 		 
				}
		 		
		 	   })
		 	  
	    	
    });
		

	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：电梯管理  &gt; 添加电梯组</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
				
		    		
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/Grouping/addEGroup" id="addEgroup" method="post">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<input type="hidden" name="flag" value="2"/>
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">电梯分组名称：<input type="text" name="egname" id="myegname" size="20"/></td>
		    		</tr>
		    		
		    		<tr>
		    		<td>
		    			<!-- TD 表格BEGIN  -->
		    			<table width="100%" border="1" cellpadding="5" cellspacing="0" style="border:#c2c6cc 1px solid; border-collapse:collapse;">
					    <tr class="main_trbg_tit" align="center">
						  <td><input type="checkbox" name="checkAll" id="checkAll"></td>
						  <td>电梯名</td>
						</tr>
						 <c:forEach items="${requestScope.egElevators}" var="Ele" varStatus="stat">
								<tr id="data_${stat.index}" align="center" class="main_trbg">
								<td><input type="checkbox" name="ids"  id="box_${stat.index}" value="${Ele.elevatorID}"></td>
									 <td>${Ele.elevatorName}</td>
								</tr>
						</c:forEach>
						<tr><td></td><td></td></tr>
					  	</table>
					  	<!-- TD 表格END  -->
		    		</td>	
		    		</tr>
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd"><input type="button" id="addEtoEG" value="&nbsp;&nbsp;添加&nbsp;&nbsp;">&nbsp;<input type="button" onclick="javascript:window.history.back(-1);" value="&nbsp;&nbsp;返回&nbsp;&nbsp;"></td></tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>