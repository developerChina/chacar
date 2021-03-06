<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>停车场进出记录查询</title>
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
	       $(function(){
		    	
	    	   /** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
		    	$("tr[id^='data_']").hover(function(){
		    		$(this).css("backgroundColor","#eeccff");
		    	},function(){
		    		$(this).css("backgroundColor","#ffffff");
		    	})
		    	
            $("#search").click(function(){
	 		   var actionURL = $("#Alarmform").attr("action");
	 	       $("#Alarmform").attr("action","${ctx}/car/carRecord");
	 	       $("#Alarmform").submit(); 
	 	   })
		    	
		    	
		 	   /** 给导出绑定点击事件 导出excel表 */
		 	   $("#add").click(function(){
		 		  var actionURL = $("#Alarmform").attr("action");
		 	       $("#Alarmform").attr("action","${ctx}/car/exportExcel");
		 	       $("#Alarmform").submit();
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
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：停车场进出记录 &gt; 停车场进出记录查询</td>
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
							车主:<input type="text" name="carMaster" value="${targetCarMaster}">&nbsp;&nbsp;
							车牌号码：<input type="text" name="cacrno" value="${targetCacrno}">
							进入时间：
							<input class="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'});" 
					name="sDate"  size="20" value="${sDate}"/>
					    	-<input class="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'});" 
					name="eDate"  size="20" value="${eDate}"/>
					    	
					    	<input type="button" id="search" value="&nbsp;搜索&nbsp;" />&nbsp;&nbsp;&nbsp;
					    	<input type="button" id="add" value="&nbsp;导出&nbsp;" />
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
			  <td>车主</td>
			  <td>车牌号</td>
			  <!-- <td>进口ip</td> -->
			  <td>进口</td>
			  <td>驶入时间</td>
			  <!-- <td>出口ip</td> -->
			  <td>出口</td>
			  <td>驶出时间</td>
			  <td>停车时长</td>
			</tr>
			<c:forEach items="${requestScope.carLogsList}" var="carLogs" varStatus="stat">
				<tr id="data_${stat.index}" class="main_trbg" align="center">
				  <td>${carLogs.carMaster}</td>
				  <td>${carLogs.cacrno}</td>
				  <%-- <td>${carLogs.serverIp}</td> --%>
				  <td>${carLogs.inIpName}</td>
				 <td>
				 <f:formatDate value="${carLogs.shootTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				 </td>
				  <%-- <td>${carLogs.outIp}</td> --%>
				  <td>${carLogs.outIpName}</td>
				 <td>
				  <f:formatDate value="${carLogs.outTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				 </td>
				  <td><font color="green"> ${carLogs.plant}</font></td>
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
	  	        submitUrl="${ctx}/car/carRecord?pageIndex={0}${pageParam}"/>
	  </td></tr>
	</table>
	<div style="height:10px;"></div>
</body>
</html>