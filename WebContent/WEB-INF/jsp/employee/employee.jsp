<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>员工管理</title>
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
	    	   $("#delete").click(function(){
	    		   /** 获取到用户选中的复选框  */
	    		   var checkedBoxs = boxs.filter(":checked");
	    		   if(checkedBoxs.length < 1){
	    			   $.ligerDialog.error("请选择一个需要删除的员工！");
	    		   }else{
	    			   /** 得到用户选中的所有的需要删除的ids */
	    			   var ids = checkedBoxs.map(function(){
	    				   return this.value;
	    			   })
	    			   
	    			   $.ligerDialog.confirm("确认要删除吗?","删除员工",function(r){
	    				   if(r){
	    					   // alert("删除："+ids.get());
	    					   // 发送请求
	    					   window.location = "${ctx }/employee/removeEmployee?ids=" + ids.get();
	    				   }
	    			   });
	    		   }
	    	   })
	    	   $("#search").click(function(){
		 		   var actionURL = $("#empform").attr("action");
		 	       $("#empform").attr("action","${ctx}/employee/selectEmployee");
		 	       $("#empform").submit();
		 	   })
	    	   
		       /** 添加员工绑定点击事件 */
		 	   $("#add").click(function(){
		 		   window.location = "${ctx }/employee/addEmployee?flag=1";
		 	   })
		 	   
		 	  $("#import").click(function(){
		 		   window.location = "${ctx }/employee/importEmployeePage";
		 	   }) 
		 	  
		 	   $("#bingd").click(function(){
	    		   /** 获取到用户选中的复选框  */
	    		   var checkedBoxs = boxs.filter(":checked");
	    		   if(checkedBoxs.length < 1){
	    			   $.ligerDialog.error("请选择一个需要要绑定的员工！");
	    		   }else{
	    			   /** 得到用户选中的所有的需要删除的ids */
	    			   var ids = checkedBoxs.map(function(){
	    				   return this.value;
	    			   })
	    			   
      			 	   $.ajax({
	    	         		  type: 'POST',
	    	         		  url: '${ctx}/employee/bingdEmployee?flag=1&ids='+ids.get(),
	    	         		  success: function(data){
	    	         			 alert(data);
	    	         		  }
	    	         	});
	    		   }
	    	   })
 
             $("#unbingd").click(function(){
	    		   /** 获取到用户选中的复选框  */
	    		   var checkedBoxs = boxs.filter(":checked");
	    		   if(checkedBoxs.length < 1){
	    			   $.ligerDialog.error("请选择一个需要要绑定的员工！");
	    		   }else{
	    			   /** 得到用户选中的所有的需要删除的ids */
	    			   var ids = checkedBoxs.map(function(){
	    				   return this.value;
	    			   })
	    			   
      			 	   $.ajax({
	    	         		  type: 'POST',
	    	         		  url: '${ctx}/employee/bingdEmployee?flag=0&ids='+ids.get(),
	    	         		  success: function(data){
	    	         			 alert(data);
	    	         		  }
	    	         	});
	    		   }
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
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：员工管理 &gt; 员工查询</td>
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
			  	<form name="empform" method="post" id="empform" action="${ctx}/employee/selectEmployee">
				    <table width="100%" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="font3">
					    	职位：
							    <select name="job_id" style="width:143px;" value="${job_id }">
					    			<option value="0">--请选择职位--</option>
					    			<c:forEach items="${requestScope.jobs }" var="job">
					    				<option value="${job.id }"  <c:if test="${job_id==job.id }">selected </c:if> >${job.name }</option>
					    			</c:forEach>
					    		</select>
					    	姓名：<input type="text" name="name" value="${model.name }">
					    	身份证号码：<input type="text" name="cardId" maxlength="18" value="${model.cardId }">
					    	<input id="search" type="button" value="&nbsp;搜索&nbsp;"/>
					    	<input id="delete" type="button" value="&nbsp;删除&nbsp;"/>
					    	<input id="add" type="button" value="&nbsp;添加&nbsp;"/>
					    	<input id="import" type="button" value="&nbsp;导入&nbsp;"/>
					    </td>
					  </tr>
					  <tr><td class="font3"><hr></td></tr>
					  <tr>
					    <td class="font3">
					    	卡状态：
					    		<select name="carstatus" style="width:130px;" value="${model.carstatus }">
					    			<option value="-1">--请选择--</option>
					    			<option value="1" <c:if test="${model.carstatus==1 }">selected </c:if> >已授权</option>
					    			<option value="0" <c:if test="${model.carstatus==0 }">selected </c:if> >未授权</option>
					    		</select>
					    	手机：<input type="text" name="phone" value="${model.phone }">
					    	所属部门：&nbsp;<select  name="dept_id" style="width:166px;" value="${dept_id }">
								   <option value="0">--部门选择--</option>
								   <c:forEach items="${requestScope.depts }" var="dept">
					    				<option value="${dept.id }"  <c:if test="${dept_id==dept.id }">selected </c:if>  >${dept.name }</option>
					    			</c:forEach>
							</select>&nbsp;
					    	
					    	<input id="bingd" type="button" value="&nbsp;绑定权限  &nbsp;"/>
					    	<input id="unbingd" type="button" value="&nbsp;解除绑定 &nbsp;"/>
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
		  <table width="100%" border="1" cellpadding="5" id="employee_table" cellspacing="0" style="border:#c2c6cc 1px solid; border-collapse:collapse;">
		    <tr class="main_trbg_tit" align="center">
			  <td><input type="checkbox" name="checkAll" id="checkAll"></td>
			  <td>姓名</td>
			  <td>手机号码</td>
			  <td>员工卡号</td>
			  <td>卡授权状态</td>
			  <td>职位</td>
			  <td>学历</td>
			  <td>身份证号码</td>
			  <td>部门</td>
			  <!-- 
			  <td>车牌号码</td>
			   -->
			  <td>建档日期</td>
			  <td align="center">操作</td>
			</tr>
			<c:forEach items="${requestScope.employees}" var="employee" varStatus="stat">
				<tr id="data_${stat.index}" class="main_trbg" align="center">
				  <td><input type="checkbox" id="box_${stat.index}" value="${employee.id}"></td>
				  <td>${employee.name }</td>
				  <td>${employee.phone }</td>
				  <td>${employee.cardno }</td>
				  <td>
			        <c:choose>
			        	<c:when test="${employee.carstatus == 1 }"><font color="green">内部员工授权</font></c:when>
			        	<c:otherwise><font color="red">外部员工授权</font></c:otherwise>
			        </c:choose>
				  </td>
				  <td>${employee.job.name  }</td>
				  <td>${employee.education }</td>
				  <td>${employee.cardId }</td>
				  <td>${employee.dept.name }</td>
				  <!--
				  <td>${employee.carno }</td>
				  -->
				  <td>
				  	<f:formatDate value="${employee.createDate}" type="date" dateStyle="long"/> 
				  </td>
				  <td align="center" width="40px;">
				      <a href="${ctx}/employee/updateEmployee?flag=1&id=${employee.id}">
						<img title="修改" src="${ctx}/images/update.gif"/>
					  </a>
				  </td>
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
	  	        submitUrl="${ctx}/employee/selectEmployee?pageIndex={0}${pageParam}"/>
	  </td></tr>
	</table>
	<div style="height:10px;"></div>
</body>
</html>