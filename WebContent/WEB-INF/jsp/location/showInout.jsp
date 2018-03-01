<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>车辆进出口查询</title>
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
	 	       $("#Alarmform").attr("action","${ctx}/Inout/selectInout");
	 	       $("#Alarmform").submit(); 
	 	   })
		    	
		    	
		 	   /** 给导出绑定点击事件 导出excel表 */
		 	   $("#add").click(function(){
		 		  var actionURL = $("#Alarmform").attr("action");
		 	       $("#Alarmform").attr("action","${ctx}/Inout/exportExcel");
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
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：车辆进出口 &gt; 车辆进出口记录查询</td>
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
					    	<%--  定位仪类型：
							    <select name="vehicleType"  style="width:143px;">
					<option value="-1" >-请选择定位仪类型-</option>
					<option value="1"  <c:if test="${model.vehicleType==1}">selected</c:if> >固定定位仪</option>
					<option value="0" <c:if test="${model.vehicleType==0}">selected</c:if>>临时定位仪</option>
					</select>  --%>
							供货商:<input type="text" name="supplier" value="${targetSupplier}">&nbsp;&nbsp;
							车牌号码：<input type="text" name="vehicleCode" value="${model.vehicleCode}">
							进入时间：
							<input class="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'});" 
					<%-- name="cominDate" id="cominDate" size="20" value='<fmt:formatDate value='${cominDate}' pattern='yyyy-MM-dd HH:mm:ss' />'/> --%>
					name="sDate" id="cominDate" size="20" value="${cominDate}"/>
					    	-<input class="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'});" 
					name="eDate" id="outDate" size="20" value="${outDate}"/>
					    	
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
			  <td>供应商</td>
			  <td>车牌号</td>
			  <td>进场门岗</td>
			  <td>进入时间</td>
			  <td>出场门岗</td>
			  <td>离开时间</td>
			 <!--  <td>定位仪类型</td> -->
			  <!-- 
			  <td>车牌号码</td>
			   -->
			</tr>
			<c:forEach items="${requestScope.locationInouts}" var="employee" varStatus="stat">
				<tr id="data_${stat.index}" class="main_trbg" align="center">
				  <td>${employee.supplier }</td>
				  <td>${employee.vehicleCode }</td>
				  	<td>${employee.serverInName }</td>
				 <td><fmt:formatDate value="${employee.cominDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss" dateStyle="long"/></td>
				 	<td>${employee.serverOutName }</td>
				 <td><fmt:formatDate value="${employee.outDate}" type="date"  pattern="yyyy-MM-dd HH:mm:ss" dateStyle="long"/></td>    
				 <%--  <td><c:if test="${employee.vehicleType==1}">固定定位仪</c:if>
					  	  <c:if test="${employee.vehicleType==0}">临时定位仪</c:if>
					  	</td> --%>
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
	  	        submitUrl="${ctx}/Inout/selectInout?pageIndex={0}${pageParam}"/>
	  </td></tr>
	</table>
	<div style="height:10px;"></div>
</body>
</html>