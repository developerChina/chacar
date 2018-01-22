<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>员工出入记录</title>
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
		 /** 给数据行绑定鼠标覆盖以及鼠标移开事件 git提交 */
    	$("tr[id^='data_']").hover(function(){
    		$(this).css("backgroundColor","#eeccff");
    	},function(){
    		$(this).css("backgroundColor","#ffffff");
    	})
		
		 $("#search").click(function(){
	 		   var actionURL = $("#empform").attr("action");
	 	       $("#empform").attr("action","${ctx}/employee/selectTrajectoryEmp");
	 	       $("#empform").submit(); 
	 	   })
	 	   
	 	  /** 给导出添加绑定点击事件 */
		 	   $("#add").click(function(){
		 		  var actionURL = $("#empform").attr("action");
		 	       $("#empform").attr("action","${ctx}/employee/exportExcel");
		 	       $("#empform").submit();
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
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：员工出入记录 &gt; 员工出入记录查询</td>
		<td class="main_locbg font2" height="32" align="right">
		员工总数：${all} 
		&nbsp;&nbsp;在场人数：${in} 
		&nbsp;&nbsp;场外人数：${out} 
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
			  	<form name="empform" method="post" id="empform" >
				    <table width="100%" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="font3">
					    	员工姓名:<input type="text" name="name" size="20" value="${name}">
					    	员工卡号:<input type="text" name="cardno" size="20" value="${cardno}">
					    	员工电话:<input type="text" name="phone" size="20" value="${phone}">
					    </td>
					  </tr>
					  	<tr>
							 <td class="font3">
					  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  		</td>
					  </tr>
					  <tr>
					    <td class="font3">
					    	刷卡时间:<input class="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'});" 
									  name="sDate" size="20" value="${sDate}"/>
							      —   <input class="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'});" 
									  name="eDate" size="20" value="${eDate}"/>
					    	所属部门:<select  name="dept_id" style="width:166px;" value="${dept_id }">
								   <option value="0">--部门选择--</option>
								   <c:forEach items="${requestScope.depts }" var="dept">
					    				<option value="${dept.id }"  <c:if test="${dept_id==dept.id }">selected </c:if>  >${dept.name}</option>
					    			</c:forEach>
							</select>&nbsp;    
					    		     <input type="button" id="search" value="&nbsp;搜索&nbsp;"/>
					    		      <input type="button" id="add" value="&nbsp;导出记录&nbsp;"/>
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
			  <!-- <td><input type="checkbox" name="checkAll" id="checkAll"></td> -->
			  <td>姓名</td>
			  <td>手机号码</td>
			  <td>员工卡号</td>
			  <td>部门</td>
			  <td>进出</td>
			  <td>时间</td>
			</tr>
			<c:forEach items="${requestScope.traEmps}" var="traEmp" varStatus="stat">
				<tr id="data_${stat.index}" align="center" class="main_trbg">
					<%-- <td><input type="checkbox" id="box_${stat.index}" value="${traEmp.id}"></td> --%>
					<td>${traEmp.employees.name}</td>
					<td>${traEmp.employees.phone}</td>
					<td>${traEmp.employees.cardno}</td>
					<td>${traEmp.employees.dept.name}</td>
					<td>${traEmp.optAction}</td>
					<td><f:formatDate value="${traEmp.optTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
		  	        submitUrl="${ctx}/employee/selectTrajectoryEmp?pageIndex={0}${pageParam}"/>
		  </td>
	  </tr>
	</table>
	<div style="height:10px;"></div>
</body>
</html>