<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>门禁授权管理</title>
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
		$(function(){
			/** 获取上一次选中的部门数据 */
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
		    	
		 	   /** 删除员工绑定点击事件 */
		 	   $("#delaj").click(function(){
		 		   /** 获取到用户选中的复选框  */
		 		   var checkedBoxs = boxs.filter(":checked");
		 		   if(checkedBoxs.length < 1){
		 			   $.ligerDialog.warn(" 请选择一个需要解除的授权！");
		 		   }else{
		 			   /** 得到用户选中的所有的需要删除的ids */
		 			   var ids = checkedBoxs.map(function(){
		 				   return this.value;
		 			   })
		 			   
		 			   $.ligerDialog.confirm("确认要解除吗?","解除授权",function(r){
		 				   if(r){
		 					    //alert("删除："+ids.get());
		 					   // 发送请求
		 					   window.location = "${ctx}/AccessJurisdiction/removeAJ?ids=" + ids.get();
		 				   }
		 			   });
		 		   }
		 	   })
		 	   
		 	   
		 	    $("#addaj").click(function(){
		 		   window.location = "${ctx}/AccessJurisdiction/selectEmploee";
		 	   });
		 	   
		 	   
	 })
	</script>
</head>
<body>
	<!-- 导航 -->
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	  <tr><td height="10"></td></tr>
	  <tr>
	    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：门禁授权管理 &gt; 门禁授权查询</td>
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
			  	<form name="empform" method="post" id="empform" action="${ctx}/AccessJurisdiction/selectAJ">
				    <table width="100%" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="font3">
					    	<!-- 门禁授权名:<input type="text" name="ajname">&nbsp; -->
					    	<!-- 门禁名:<input type="text" name="pganame" >&nbsp; -->
					    	所属部门：<select  name="dept_id" style="width:320px;">
								   <option value="0">--部门选择--</option>
								   <c:forEach items="${requestScope.depts }" var="dept">
					    				<option value="${dept.id}" <c:if test="${dept_id==dept.id }">selected </c:if> >${dept.name }</option>
					    			</c:forEach>
							</select>&nbsp;&nbsp;
					    	员工名称:<input type="text" name="ajEmpName" value="${targetEmp}">&nbsp;
					    	<input type="submit" value="&nbsp;&nbsp;搜索&nbsp;&nbsp;"/>
					    	<input id="delaj" type="button" value="&nbsp;&nbsp;解除门禁授权&nbsp;&nbsp;"/>
					    	<input id="addaj" type="button" value="&nbsp;&nbsp;绑定门禁授权&nbsp;&nbsp;"/>
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
			  <!-- <td>名称</td> -->
			  <td>门禁组</td>
			  <td>门禁</td> 
			  <td>员工</td>
			  <td>手机号</td>
			  <td>部门</td>
			 <!--  <td align="center">操作</td> -->
			</tr>
			
			<c:forEach items="${requestScope.accessjs}" var="aj" varStatus="stat">
				<tr id="data_${stat.index}" align="center" class="main_trbg">
					<td><input type="checkbox" id="box_${stat.index}" value="${aj.ajempid};${aj.ajaccessid}"></td>
					<%--  <td>${aj.ajname}</td> --%>
					 <td>
					 		${aj.agroups.agname}<%--${aj.agroups.agid} --%>
					 		<%-- (<c:forEach items="${aj.accessList}" var="gyo">
					 		${gyo.accessname}${gyo.accessid}
					 		</c:forEach>) --%>
					 </td>
					 <td>
						${aj.accessList.accessname}<%--${aj.ajaccessid} --%>
					 </td>
					 <td>${aj.ajEmployee.name} <%--${aj.ajEmployee.id} --%></td>
					  <%-- <td align="center" width="40px;">
 					       <a href="${ctx}/AccessJurisdiction/updetaAj?flag=1&id=${passageway.ajid}">
							   <img title="修改" src="${ctx}/images/update.gif"/>
						   </a>
					  </td> --%>
					  <td>${aj.ajEmployee.phone}</td>
					  <td>${aj.ajEmployee.dept.name}</td>
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
		  	        submitUrl="${ctx}/AccessJurisdiction/selectAJ?pageIndex={0}${pageParam}"/>
		  </td>
	  </tr>
	</table>
	<div style="height:10px;"></div>
</body>
</html>


