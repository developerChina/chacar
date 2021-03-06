<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>临时定位仪查询</title>
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
	 		   var actionURL = $("#temporaryform").attr("action");
	 	       $("#temporaryform").attr("action"," ${ctx}/temporary/queryAck");
	 	       $("#temporaryform").submit(); 
	 	   })
		 /** 给导出添加绑定点击事件 */
		 $("#induce").click(function(){
		 		  var actionURL = $("#temporaryform").attr("action");
		 	       $("#temporaryform").attr("action","${ctx}/temporary/induce");
		 	       $("#temporaryform").submit();
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
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：临时定位仪 &gt; 查询</td>
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
			  	<form name="temporaryform" method="post" id="temporaryform" >
				    <table width="100%" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					       <td class="font3">
					    	车主名称：<input type="text" name="name" value="${temporaryInfo.name}">
					    	&nbsp;车牌号：&nbsp;<input type="text" name="carno" value="${temporaryInfo.carno}">
					    	&nbsp;联系人：&nbsp;<input type="text" name="contacts" value="${temporaryInfo.contacts}">
					    	</td>
					    </tr>
					     <tr><td class="font3"><hr></td></tr>
					    <tr>
					    	<td class="font3">
					    	GPS号码：&nbsp;<input type="text" name="gps" value="${temporaryInfo.gps}">
					    	发放时间：<input class="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'});" 
									name="sDate" size="20" value="${sDate}"/>—<input class="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'});" 
									name="eDate" size="20" value="${eDate}"/>
					    	&nbsp;&nbsp;<input type="button" id="search" value="&nbsp;&nbsp;搜索&nbsp;&nbsp;"/>&nbsp;
					    	&nbsp;&nbsp;<input id="induce" type="button" value="&nbsp;&nbsp;导出&nbsp;&nbsp;"/>
					    
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
			  <td>车主名称</td>
			  <td>车牌号</td>
			  <td>联系人</td>
			  <td>联系电话</td>
			  <td>GPS号码</td>
			  <td>发放时间</td>
			  <td>回收时间</td>
			  <td>备注</td>
			  <td align="center">操作</td>
			</tr>
			<c:forEach items="${requestScope.entityList}" var="entity" varStatus="stat">
				<tr id="data_${stat.index}" align="center" class="main_trbg">
					 	<td>${entity.name}</td>
					  	<td>${entity.carno}</td>
						<td>${entity.contacts}</td>
						<td>${entity.tel}</td>
						<td>${entity.gps}</td>
						<td>
						<f:formatDate value="${entity.cominDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
						<f:formatDate value="${entity.outDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>${entity.remarks}</td>
 					  <td align="center" width="40px;">
 					       <a href="${ctx}/temporary/repeat?flag=1&id=${entity.id}">
							   <img title="重新绑定gps" src="${ctx}/images/update.gif"/>
						   </a>
					  </td>
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
		  	        submitUrl="${ctx}/temporary/queryAck?pageIndex={0}${pageParam}"/>
		  </td>
	  </tr>
	</table>
	<div style="height:10px;"></div>
</body>
</html>