<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>访客轨迹</title>
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
	<link href="${ctx}/js/My97DatePicker/skin/WdatePicker.css" type="text/css" rel="stylesheet" />
    
	<script type="text/javascript">
	$(function(){
		    /** 获取上一次选中的部门数据 */
	 	    var boxs  = $("input[type='checkbox'][id^='box_']");
	 	    /** 给数据行绑定鼠标覆盖以及鼠标移开事件  */
	    	$("tr[id^='data_']").hover(function(){
	    		$(this).css("backgroundColor","#eeccff");
	    	},function(){
	    		$(this).css("backgroundColor","#ffffff");
	    	})
	    	$("#checkAll").click(function(){
	    		boxs.attr("checked",this.checked);
	    	})
	 })
 	function dj(){
   		   /** 获取到用户选中的复选框  */
   		   var checkedBoxs = boxs.filter(":checked");
   		   var ids = checkedBoxs.map(function(){
			   return this.value;
		   })
		   window.location = "${ctx }/visitor/deleteTrajectory?ids=" + ids.get();
   	   }
	</script>
</head>
<body>
	<!-- 导航 -->
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	  <tr><td height="10"></td></tr>
	  <tr>
	    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：访客轨迹 &gt; 访客轨迹查询</td>
		<td class="main_locbg font2" height="32" align="right">
		访问总人数：${all} 
		&nbsp;&nbsp;正在访问人数：${ing} 
		&nbsp;&nbsp;已访问完人数：${ed} 
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</td>
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
			  	<form name="empform" method="post" id="empform" action="${ctx}/visitor/trajectoryAck">
				    <table width="100%" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="font3">
					    	&nbsp;&nbsp;访客姓名：<input type="text" name="cardName" size="20" value="${cardName}">
					    	访客身份证号：<input type="text" name="cardID" size="20" value="${cardID}">
					    	访客电话：<input type="text" name="telephone" size="20" value="${telephone}">
					    </td>
					  </tr>
					  <tr>
					  <td class="font3">
					  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  </td>
					  </tr>
					  <tr>
					    <td class="font3">
					    	被访人姓名：<input type="text" name="bevisitedName" size="20" value="${bevisitedName}">
					    	&nbsp;&nbsp;被访人电话：<input type="text" name="bevisitedTel" size="20" value="${bevisitedTel}">
					    	访问时间：<input class="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'});" 
									name="sDate" size="20" value="${sDate}"/>—<input class="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'});" 
									name="eDate" size="20" value="${eDate}"/>
					    	<input type="submit" value="&nbsp;搜索&nbsp;"/>
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
			  <td><input type="checkbox" name="checkAll" id="checkAll"></td>
			  <td>访客名称</td>
			  <td>访客身份证</td>
			  <td>访客电话</td>
			  <td>控制器</td>
			  <td>被访人</td>
			  <td>时间</td>
			  <td>状态</td>
			  <td>证件照片</td>
			  <!-- 
			  <td>现场照片</td>
			   -->
			</tr>
			<c:forEach items="${requestScope.trajectorys}" var="trajectory" varStatus="stat">
				<tr id="data_${stat.index}" align="center" class="main_trbg">
					<td><input type="checkbox" id="box_${stat.index}" value="${trajectory.id}"></td>
					 <td>${trajectory.recordVisitors.cardName }</td>
					  <td>${trajectory.recordVisitors.cardID }</td>
					  <td>${trajectory.recordVisitors.telephone }</td>
					  <td>${trajectory.controllerDesc }</td>
					  <td>
						<c:forEach items="${trajectory.employees }" var="employe" varStatus="stat">
							${employe.name }<br/>
						</c:forEach>
					  </td>
					  <td>
					  
					  ${trajectory.optDate }
					  </td>
					  <td>
					     <c:if test="${trajectory.recordVisitors.visitStatus!=null && trajectory.recordVisitors.visitStatus!='' }">
					      <c:if test="${trajectory.recordVisitors.visitStatus==0}">
					       	申请中
					      </c:if>
					      <c:if test="${trajectory.recordVisitors.visitStatus==1}">
					       	审核中
					      </c:if>
					      <c:if test="${trajectory.recordVisitors.visitStatus==2}">
					       	审核通过
					      </c:if>
					      <c:if test="${trajectory.recordVisitors.visitStatus==3}">
					       	正在访问
					      </c:if>
					      <c:if test="${trajectory.recordVisitors.visitStatus==4}">
					     	访问结束  
					      </c:if>
					      <c:if test="${trajectory.recordVisitors.visitStatus==5}">
					   		审核拒绝   
					      </c:if>
					     </c:if>
					  </td>
					  <td>
					     <c:if test="${trajectory.recordVisitors.cardPhoto!=null && trajectory.recordVisitors.cardPhoto!='' }">
					     <a target="_blank" href="${imgurl}${trajectory.recordVisitors.cardPhoto }">
						  <img width="80px;" height="60px;" src="${imgurl}${trajectory.recordVisitors.cardPhoto }">
						  </a>
					     </c:if>
					  </td>
					  <!-- 
					  <td>
					     <c:if test="${trajectory.recordVisitors.photo1!=null && trajectory.recordVisitors.photo1!='' }">
					     <a target="_blank" href="${imgurl}${trajectory.recordVisitors.photo1 }">
						  <img width="80px;" height="60px;" src="${imgurl}${trajectory.recordVisitors.photo1 }">
						  </a>
					     </c:if>
					  </td>
					   -->
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
		  	        submitUrl="${ctx}/visitor/trajectoryAck?pageIndex={0}${pageParam}"/>
		  </td>
	  </tr>
	</table>
	<div style="height:10px;"></div>
</body>
</html>