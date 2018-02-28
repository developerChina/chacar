<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html> 
<head>
	<title>排队叫号系统-现场查询统计</title>
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

<script type="text/javascript">
	setInterval('refreshQuery()',2*60*1000);
	/* 刷新查询 */  
	function refreshQuery(){  
		 window.location = "${ctx}/queuingS/SceneStatistics";
	}
</script>
	
</head>
<body>
	<!-- 导航 -->
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	  <tr><td height="10"></td></tr>
	  <tr>
	    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：排队叫号系统 &gt;现场查询统计 </td>
		<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
	  </tr>
	</table>
	
	<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
	 <!-- 数据展示区 -->
	  <tr valign="top">
	    <td height="20">
		  <table border="1" cellpadding="5" cellspacing="0" style="border:#c2c6cc 1px solid; border-collapse:collapse;">
		    <tr class="main_trbg_tit" align="center" >
		      <td>卸货岛名称</td>
		      <td>排队总数</td>
		      <td>急件数</td>
		      <td>普通数</td>
		      <td>排队车辆</td> 
			</tr>
			<c:forEach items="${requestScope.list}" var="land" varStatus="stat">
				<tr id="data_${stat.index}" align="center" class="main_trbg">
					<td>${land.iname}</td>
					<td>${land.all}</td>
					<td>${land.vip}</td>
					<td>${land.o}</td>
					<td  align="left">
						<table cellpadding="4">
						<tr>
						<c:if test="${land.all>0}">
						<c:forEach items="${land.QueuingVips}" var="v" >
						<td>
						<img title="${v.supplier}/${v.car_code}" style="width: 45px;height: 28px;" src="${ctx}/images/vipCar.jpg"/></td>
						</c:forEach>
						<c:forEach items="${land.Ordinarys}" var="o" >
						<td>
						<img title="${o.supplier}/${o.car_code}" style="width: 45px;height: 28px;" src="${ctx}/images/oCar.jpg"/></td>
						</c:forEach>
						</c:if>
						
						<c:if test="${land.all<=0}">
						<td width="747px" height="47px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						</c:if>
						</tr>
						</table>
					</td>
				</tr>
			</c:forEach>
			<tr class="main_trbg" align="center" >
			<td>总计</td>
			<td>${Sum}</td>
			<td>${VipSum}</td>
			<td>${OSum}</td>
			<td></td>
			</tr>
		  </table>
		</td>
	  </tr>
	  <!-- 分页标签 -->
	  <tr valign="top">
		    <td align="center" class="font3">
		  	<%--  <fkjava:pager
		  	        pageIndex="${requestScope.pageModel.pageIndex}" 
		  	        pageSize="${requestScope.pageModel.pageSize}" 
		  	        recordCount="${requestScope.pageModel.recordCount}" 
		  	        style="digg"
		  	        submitUrl="${ctx}/queuing/IslandAck?pageIndex={0}"/> --%>
		  </td>
	  </tr>
	</table>
	<div style="height:10px;"></div>
</body>
</html>
