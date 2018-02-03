<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html> 
<head>
	<title>排队叫号系统-历史记录管理</title>
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
		 	  /** 给数据行绑定鼠标覆盖以及鼠标移开事件 git提交 */
		    	$("tr[id^='data_']").hover(function(){
		    		$(this).css("backgroundColor","#eeccff");
		    	},function(){
		    		$(this).css("backgroundColor","#ffffff");
		    	})
		    	
		    $("#search").click(function(){
	 		   var actionURL = $("#Historyform").attr("action");
	 	       $("#Historyform").attr("action","${ctx}/queuingH/HistoryAck");
	 	       $("#Historyform").submit(); 
	 	   })
		    	
		    	
		 	   /** 给导出添加绑定点击事件 */
		 	   $("#add").click(function(){
		 		  var actionURL = $("#Historyform").attr("action");
		 	       $("#Historyform").attr("action","${ctx}/queuingH/exportExcel");
		 	       $("#Historyform").submit();
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
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：排队叫号系统-历史记录管理 &gt; 历史记录查询</td>
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
			  	<form name="Historyform" method="post" id="Historyform" >
				    <table width="100%" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					   <td class="font3">
		    				卸货岛:<select name="island_no" id="island_no" >
								<option value="0">-请选择卸货岛-</option>
								<c:forEach items="${requestScope.AddVgetI}" var="avi" varStatus="stat">
								<option value="${avi.no}" <c:if test="${island_no==avi.no}">selected </c:if> >${avi.iname}</option>
								</c:forEach>
							</select>
							
							卸货岛名称:<input type="text" name="vagueiname" value="${model}">&nbsp;&nbsp;
					    	
					    	车牌号:<input type="text" name="car_code" value="${target}">&nbsp;&nbsp;
					   		</td>
					  	</tr>
					  	 <tr>
					  <td class="font3">
					  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  </td>
					  </tr>
					  <tr>
					    <td class="font3">
					 	 	供货商:<input type="text" name="supplier" value="${targetSupplier}">&nbsp;&nbsp;
					 
					   		驶入时间:<input class="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'});" 
									name="sDate" size="20" value="${sDate}"/>—<input class="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'});" 
									name="eDate" size="20" value="${eDate}"/>
					     	<input type="button" id="search" value="&nbsp;&nbsp;搜索&nbsp;&nbsp;"/>&nbsp;&nbsp;
					   
					    	<input id="add" type="button" value="&nbsp;&nbsp;导出&nbsp;&nbsp;"/>&nbsp;&nbsp; 
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
		  <table width="100%" border="1" cellpadding="5" cellspacing="0" style="border:#c2c6cc 1px solid; border-collapse:collapse;">
		    <tr class="main_trbg_tit" align="center">
			  <td>卸货岛名称</td>
			  <td>供应商</td>
			  <td>车牌号码</td>
			  <td>进场时间</td>
			 <!--  <td>取号时间</td> -->
			  <td>卸货开始</td>
			  <td>卸货结束</td>
			  <td>出场时间</td>
			  <td>卸货时长</td>
			  <td>在场时长</td>
			  <td>备注</td>
			</tr>
			<c:forEach items="${requestScope.pageListH}" var="ph" varStatus="stat">
				<tr id="data_${stat.index}" align="center" class="main_trbg">
					  
					  <td>${ph.hpartsI.iname}</td>
					  <td>${ph.supplier}</td>
					  <td>${ph.car_code}</td>
					  
					  <td>
					  <f:formatDate value="${ph.inplant}" pattern="yyyy-MM-dd HH:mm:ss"/>
					  </td>
					  
					 <%--  
					 <td>
					  <f:formatDate value="${ph.take_time}" pattern="yyyy-MM-dd HH:mm:ss"/>
					  </td> 
					 --%>
					  
					  <td>
					  <f:formatDate value="${ph.comein_time}" pattern="yyyy-MM-dd HH:mm:ss"/>
					  </td>
					  
					  <td>
					  <f:formatDate value="${ph.goout_time}" pattern="yyyy-MM-dd HH:mm:ss"/>
					  </td>
					  
					  <td>
					  <f:formatDate value="${ph.outplant}" pattern="yyyy-MM-dd HH:mm:ss"/>
					  </td>
					  
					  <td>
					  <font color="green">${ph.reduce}</font>
					  </td>
					  
					  <td>
					  <font color="green">${ph.plant}</font> 
					  </td>
					  
					  <td>
					  <c:if test="${ph.source==1}">普通</c:if>
					  <c:if test="${ph.source==0}">急件</c:if>
					  </td>
					  <%-- <td>${ph.empname}</td> --%> 
				</tr>
			</c:forEach>
		  </table>
		</td>
	  </tr>
	  <!-- 分页标签 -->
	  <tr valign="top">
		    <td align="center" class="font3">
		  	 <fkjava:pager
		  	        pageIndex="${requestScope.pageModel.pageIndex}" 
		  	        pageSize="${requestScope.pageModel.pageSize}" 
		  	        recordCount="${requestScope.pageModel.recordCount}" 
		  	        style="digg"
		  	        submitUrl="${ctx}/queuingH/HistoryAck?pageIndex={0}${pageParam}"/>
		  </td>
	  </tr>
	</table>
	<div style="height:10px;"></div>
</body>
</html>
