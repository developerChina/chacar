<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>车主信息维护</title>
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

	<script language="javascript" type="text/javascript" src="${ctx }/js/My97DatePicker/WdatePicker.js"></script>
	
	<script type="text/javascript">
	
	function deleteD(a){
    	$.ligerDialog.confirm("确认要撤销此车主信息吗?","撤销车主信息 ",function(r){
			   if(r){
				   window.location = "${ctx}/driver/delDriverAck?id=" + a;
			   }
		   });
    }
	
	function updateD(a){
		 window.location = "${ctx}/driver/upDriverAck?flag=1&id=" + a;
    }
	
	
	       $(function(){
	    	   /** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
		    	$("tr[id^='data_']").hover(function(){
		    		$(this).css("backgroundColor","#eeccff");
		    	},function(){
		    		$(this).css("backgroundColor","#ffffff");
		    	})
		    //搜索的点击事件
            $("#search").click(function(){
	 		   var actionURL = $("#Alarmform").attr("action");
	 	       $("#Alarmform").attr("action","${ctx}/driver/DriverAck");
	 	       $("#Alarmform").submit(); 
	 	   })
		    	
	 	   //给添加 绑定点击事件 
	 	    	$("#add").click(function(){
		 		   window.location = "${ctx }/driver/addDriver?flag=1";
		 	   })
	 	   
		 	   
	})
	       
	</script>
</head>
<body>
	<!-- 导航 -->
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	  <tr><td height="10"></td></tr>
	  <tr>
	    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：车主信息维护 &gt; 车主记录查询</td>
		<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
	  </tr>
	</table>
	
	<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
	  <!-- 查询区  -->
	  <tr valign="top">
	    <td height="30">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr>
			  <td class="fftd">
			  	<form name="Alarmform" method="post" id="Alarmform" >
				    <table width="100%" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="font3">
							供应商:<input type="text" name="name" value="${targetSupplier}">&nbsp;&nbsp;
							车牌号码：<input type="text" name="vehicleCode" value="${vehicleCode}">
					    	<input type="button" id="search" value="&nbsp;搜索&nbsp;" />&nbsp;&nbsp;&nbsp;
					    	<input type="button" id="add" value="&nbsp;添加&nbsp;" />&nbsp;&nbsp;&nbsp;
					    </td>
					  </tr>
					</table>
				</form>
			  </td>
			</tr>
		  </table>
		</td>
	  </tr>
	  
	  <!-- 数据展示区 -->
	  <tr valign="top">
	    <td height="20">
		  <table width="100%" border="1" cellpadding="5" id="Alarmtable" cellspacing="0" style="border:#c2c6cc 1px solid; border-collapse:collapse;">
		    <tr class="main_trbg_tit" align="center">
			  <td>供应商</td>
			  <td>车牌号</td>
			  <td>车辆类型</td>
			  <td>操作类型</td>
			  <td>操作时间</td>
			  <td>操作</td>
			</tr>
			<c:forEach items="${requestScope.pageListD}" var="driver" varStatus="stat">
			<tr id="data_${stat.index}" class="main_trbg" align="center">
			<td>${driver.name}</td>
			<td>${driver.vehicleCode}</td>
			<td>${driver.cartType}</td>
			<td>
			<c:if test="${driver.type==0}">定位仪进行了删除操作</c:if>
			<c:if test="${driver.type==1}">定位仪进行了修改操作</c:if>
			</td>
			
			<td>
			<fmt:formatDate value="${driver.optdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
			
			<td> <a href="javascript:void(0)" onclick="deleteD('${driver.id}')">撤销</a>/<a href="javascript:void(0)" onclick="updateD('${driver.id}')">修改</a></td>
			</tr>
			</c:forEach>
		  </table>
		</td>
	  </tr>
	  <!-- 分页标签 -->
	  <tr valign="top"><td align="center" class="font3">
	  	 <fkjava:pager
	  	        pageIndex="${requestScope.pageModel.pageIndex}" 
	  	        pageSize="${requestScope.pageModel.pageSize}" 
	  	        recordCount="${requestScope.pageModel.recordCount}" 
	  	        style="digg"
	  	        submitUrl="${ctx}/driver/DriverAck?pageIndex={0}${pageParam}"/>
	  </td></tr>
	</table>
	<div style="height:10px;"></div>
</body>
</html>