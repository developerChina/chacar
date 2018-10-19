<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!-- http://blog.csdn.net/Qiufeng_0ng/article/details/53535352 -->  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>北京长安总控平台</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
		<script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
		<script language="javascript" type="text/javascript"> 
			$(function(){
				/** 给左侧功能菜单绑定点击事件  */
				$("td[id^='navbg']").click(function(){
				   	 /** 获取一级菜单的id */
				   	 var navbgId = this.id;
				   	 /** 获取对应的二级菜单id */
				   	 var submenuId = navbgId.replace('navbg','submenu');
				   	 /** 控制二级菜单显示或隐藏  */
				   	 $("#"+submenuId).toggle();
				   	 /** 控制关闭或者开启的图标*/
				   	 $("#"+navbgId).toggleClass("left_nav_expand");
				   	 /** 控制其他的一级菜单的二级菜单隐藏按钮都关闭  */
				   	 $("tr[id^='submenu']").not("#"+submenuId).hide();
				   	 /** 控制其他一级菜单的图片显示关闭  */
				   	 $("td[id^='navbg']").not(this).removeClass().addClass("left_nav_closed");
				})
			})
		</script>
	</head>
<body> 
	<div style="margin:10px;background-color:#FFFFFF; text-align:left;">
		<table width="200" height="100%" border="0" cellpadding="0" cellspacing="0" class="left_nav_bg">
		  <c:set value="${fn:split(userPower,',')}" var="ths"></c:set>
		  <c:forEach items="${ths}" var="th">
	 		  <c:if test="${th=='1'}">
	 		      <tr><td class="left_nav_top"><div class="font1">系统管理</div></td></tr>
		 		  <tr valign="top">
				    <td class="left_nav_bgshw" height="30">
					  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/user/selectUser" target="main">用户管理</a></img></p>
					  <!-- <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/resource/resourcesAck" target="main">资源管理</a></img></p>
					  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/authority/authorityAck" target="main">权限分配</a></img></p> -->
					</td>
				  </tr>
				  <tr><td height="2"></td></tr>
	          </c:if>
	          <c:if test="${th=='2'}">
				  <tr><td id="navbg1" class="left_nav_closed" ><div class="font1">员工系统</div></td></tr>
				  <tr valign="top" id="submenu1" style="display: none">
				    <td class="left_nav_bgshw" height="50">
					  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/dept/selectDept" target="main">部门管理</a></img></p>
					  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/job/selectJob" target="main">职位管理</a></img></p>
					  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/employee/selectEmployee" target="main">员工管理</a></img></p>
					  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/employee/selectTrajectoryEmp" target="main">出入记录</a></img></p>
					  <!--  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/card/cardAck" target="main">授权管理</a></img></p>-->
					</td>
				  </tr>
				  <tr><td height="2"></td></tr>
			  </c:if>
			  <c:if test="${th=='3'}">
				  <tr><td id="navbg2" class="left_nav_closed" ><div class="font1">访客系统</div></td></tr>
				  <tr valign="top" id="submenu2" style="display: none">
				    <td class="left_nav_bgshw" height="30">
					  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/visitor/blackAck" target="main">黑名单</a></img></p>
					  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/visitor/reasonAck" target="main">拜访事由</a></img></p>
					  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/visitor/selectVisitor" target="main">访客信息</a></img></p>
					  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/visitor/trajectoryAck" target="main">访问查询</a></img></p>
					</td>
				  </tr>
				  <tr><td height="2"></td></tr>
	          </c:if>
	          <c:if test="${th=='4'}">
				  <tr><td id="navbg3" class="left_nav_closed" ><div class="font1">门禁系统</div></td></tr>
				  <tr valign="top" id="submenu3" style="display: none">
				    <td class="left_nav_bgshw tdbtmline" height="50">
				      <%-- <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/floor/floorAck" target="main">门禁规划</a></img></p> --%>
				      <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/accessGroup/floorSplit" target="main">门禁分组</a></img></p>
				      <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/AccessJurisdiction/selectAJ" target="main">绑定授权</a></img></p>
					</td>
				  </tr>
				  <tr><td height="2"></td></tr>
	          </c:if>
	          <c:if test="${th=='5'}">
		          <tr><td id="navbg4" class="left_nav_closed" ><div class="font1">梯控系统</div></td></tr>
				  <tr valign="top" id="submenu4" style="display: none">
				    <td class="left_nav_bgshw tdbtmline" height="40">
				      <%-- <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/elevator/elevatorAck" target="main">梯控规划</a></img></p> --%>
				      <!-- <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/Grouping/elevatorSplit" target="main">梯控分组</a></img></p>
				      <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx }/Jurisdiction/getEJ" target="main">绑定授权</a></img></p> -->
					</td>
				  </tr>
				  <tr><td height="2"></td></tr>
			  </c:if>
			  <c:if test="${th=='6'}">
				  <tr><td id="navbg5" class="left_nav_closed" ><div class="font1">通道系统</div></td></tr>
				  <tr valign="top" id="submenu5" style="display: none">
				    <td class="left_nav_bgshw tdbtmline" height="50">
				      <%-- <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/door/doorAck" target="main">通道规划</a></img></p> --%>
				      <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/passagewayGroup/doorSplit" target="main">通道分组</a></img></p>
				      <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/PassagewayJurisdiction/selectPJ" target="main">绑定授权</a></img></p>
					</td>
				  </tr>
				  <tr><td height="2"></td></tr>
			  </c:if>
			  <c:if test="${th=='7'}">
		         <tr><td id="navbg6" class="left_nav_closed" ><div class="font1">车辆系统</div></td></tr>
				  <tr valign="top" id="submenu6" style="display: none">
				    <td class="left_nav_bgshw tdbtmline" height="50">
				      <%-- <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx }/car/carDistinguish" target="main">识别仪</a></img></p> --%>
				      <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx }/car/carPark" target="main">车场管理</a></img></p>
				      <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx }/car/carPassageway" target="main">识别仪->车场</a></img></p>
				      <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx }/car/carInfo" target="main">车辆维护</a></img></p>
				      <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx }/car/carAuthority" target="main">车辆授权</a></img></p>
					  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx }/car/carRecord" target="main">停车场出入记录</a></img></p>
					</td>
				  </tr>
				  <tr><td height="2"></td></tr>
	          </c:if>
	          <c:if test="${th=='8'}">
				  <tr><td id="navbg7" class="left_nav_closed" ><div class="font1">定位系统</div></td></tr>
				  <tr valign="top" id="submenu7" style="display: none">
				    <td class="left_nav_bgshw tdbtmline" height="50">
				     <!--  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/Client/LocationClientAck"  target="main">客户管理</a></img></p>
				      <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/location/LUserAck"  target="main">用户管理</a></img></p>
				      <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/location/GroupAck"  target="main">分组管理</a></img></p>
				      <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/LocationVehicel/LocationVehicelAck"  target="main">设备管理</a></img></p>
				      <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/location/login"  target="main">位置信息服务</a></img></p> -->
				      <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/Locator/selectLocator"  target="main">定位仪管理</a></img></p>
				      <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/logisVehicle/selectlogisVehicle"  target="main">车辆绑定查询</a></img></p>
				      <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/Inout/selectInout"  target="main">车辆进出口查询</a></img></p>
				      <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/Alarm/selectAlarm" target="main">车辆违规办理</a></img></p>
					</td>
				  </tr>
				  <tr><td height="2"></td></tr>
	           </c:if>
	           <c:if test="${th=='9'}">
		           <tr><td id="navbg8" class="left_nav_closed" ><div class="font1">排队叫号</div></td></tr>
				  <tr valign="top" id="submenu8" style="display: none">
				    <td class="left_nav_bgshw tdbtmline" height="50">
				    <%-- <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/queuingI/IslandAck" target="main">卸货岛维护</a></img></p> --%>
					<p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/queuingV/VipAck" target="main">急件队列维护</a></img></p>
					<p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/queuingO/OrdinaryAck" target="main">普通队列维护</a></img></p>
					<%-- <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/queuingH/TodayAck" target="main">现场查询统计1</a></img></p> --%> 
					<p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/queuingS/SceneStatistics" target="main">现场查询统计</a></img></p>
					<p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/queuingH/HistoryAck" target="main">历史查询统计</a></img></p>
					</td>
				  </tr>
				  <tr><td height="2"></td></tr>
			  </c:if>
			  
			   <c:if test="${th=='10'}">
		           <tr><td id="navbg9" class="left_nav_closed" ><div class="font1">维护系统</div></td></tr>
				  <tr valign="top" id="submenu9" style="display: none">
				    <td class="left_nav_bgshw tdbtmline" height="50">
				    <!--通道  -->
					<p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/door/doorAck" target="main">通道规划</a></img></p>
					<!--梯控  -->
					<p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/elevator/elevatorAck" target="main">梯控规划</a></img></p>
					<!--门禁  -->
					<p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/floor/floorAck" target="main">门禁规划</a></img></p>
					<!-- 卸货岛维护 -->
				    <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/queuingI/IslandAck" target="main">卸货岛维护</a></img></p>
					<!--识别仪  -->
					<p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx }/car/carDistinguish" target="main">识别仪</a></img></p>
					<!--车主信息维护  -->
					<p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx }/driver/DriverAck" target="main">车主信息维护</a></img></p>
					</td>
				  </tr>
				  <tr><td height="2"></td></tr>
			  </c:if>
			  
			  <!-- 2018-10-17 新增的临时GPS办理 -->
			   <c:if test="${th=='11'}">
		           <tr><td id="navbg10" class="left_nav_closed" ><div class="font1">临时定位仪系统</div></td></tr>
				  <tr valign="top" id="submenu10" style="display: none">
				    <td class="left_nav_bgshw tdbtmline" height="50">
				    <!--查询-->
					<p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/temporary/queryAck" target="main">查询</a></img></p>
					<!--办理-->
					<p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/temporary/handleAck" target="main">办理</a></img></p>
					</td>
				  </tr>
				  <tr><td height="2"></td></tr>
			  </c:if>
			  
			  
			</c:forEach>
			  <tr><td height="2"></td></tr>
			  
			  <tr valign="top"><td height="100%" align="center"><div class="copycct"><br /><strong>技术支持：</strong><br><strong>北京华隆辰信息技术有限公司</strong><br>www.hualc.com</div></td></tr>
			  <tr><td height="10"><img src="${ctx}/images/left_nav_bottom.gif" height="10"></img></td></tr>
			  <tr><td height="10" bgcolor="#e5f0ff">&nbsp;</td></tr>
		  
		</table>
	</div>
</body>
</html>