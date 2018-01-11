<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>违规管理</title>
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
	    			   $.ligerDialog.error("请选择一个需要要办理违规的车辆！");
	    		   }else{
	    			   /** 得到用户选中的所有的需要删除的ids */
	    			   var ids = checkedBoxs.map(function(){
	    				   return this.value;
	    			   })
	    			   window.location = "${ctx }/Alarm/updateAlarm?flag=1&ids=" + ids.get();
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
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：违规管理 &gt; 违规查询</td>
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
			  	<form name="Alarmform" method="post" id="Alarmform" action="${ctx}/Alarm/selectAlarm">
				    <table width="100%" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="font3">
					    	经度：<input type="text" name="lng" size="15" value="${model.lng}">
					    	位置：<input type="text" name="posinfo" value="${model.posinfo}">	
							接收时间：<input class="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});" 
					name="recvtime" id="recvtime" size="10" value="${model.recvtime}"/>
					    	 处理状态：
							    <select name="handletype"  style="width:143px;">
								<option value="-1">-请选择处理状态-</option>
								<option value="1" <c:if test="${model.handletype==1}">selected</c:if> >已处理 </option>
								<option value="0" <c:if test="${model.handletype==1}">selected</c:if> >未处理</option>
								</select> 
							速度：<input type="text" name="veo" size="15" value="${model.veo}">
					    </td>
					  </tr>
					  <tr>
					  	<td class="font3">
					  		纬度：<input type="text" name="lat" size="15" value="${model.lat}">
					    	里程：<input type="text" name="totaldistance" value="${model.totaldistance}">
					    	G P S时间:<input class="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});" 
					name="gpstime" id="gpstime" size="10" value="${model.gpstime}"/>
							处理意见：<input type="text" name="handleidea" maxlength="18" value="${model.handleidea}">
					    	<input type="submit" value="&nbsp;搜索&nbsp;" />
					    	<input id="bingd" type="button" value="&nbsp;办理违规&nbsp;">
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
			  <td><input type="checkbox" name="checkAll" id="checkAll"></td>
			  <td>车牌号</td>
			  <td>GPS时间</td>
			  <td>接收时间</td>
			  <td>经度</td>
			  <td>纬度</td>
			  <td>速度</td>
			  <td>方向</td>
			  <td>状态</td>
			   <td>状态描述</td>
			    <td>位置</td>
			     <td>里程</td>
			      <td>总里程</td>
			       <td>处理状态</td>
			       <td>处理意见</td>
			  <!-- 
			  <td>车牌号码</td>
			   -->
			</tr>
			<c:forEach items="${requestScope.locationAlarms}" var="employee" varStatus="stat">
				<tr id="data_${stat.index}" class="main_trbg" align="center">
				  <td><input type="checkbox" id="box_${stat.index}" value="${employee.id}"></td>
				  <td>${employee.vehicleCode }</td>
				 <td><f:formatDate value="${employee.gpstime}" type="date" dateStyle="long"/></td>
				 <td><f:formatDate value="${employee.recvtime}" type="date" dateStyle="long"/></td>    
				  <td>${employee.lng }</td>
				  <td>${employee.lat  }</td>
				  <td>${employee.veo }</td>
				  <td>${employee.direct }</td>
				  <td>${employee.istate }</td>
				  <td>${employee.cstate }</td>
				   <td>${employee.posinfo }</td>
				   <td>${employee.distance }</td>
				   <td>${employee.totaldistance }</td>
				   <td>
			        <c:choose>
			        	<c:when test="${employee.handletype == 1 }"><font color="red">已处理</font></c:when>
			        	<c:otherwise><font color="green">未处理</font></c:otherwise>
			        </c:choose>
				  </td>
				   <td>${employee.handleidea }</td>
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
	  	        submitUrl="${ctx}/Alarm/selectAlarm?pageIndex={0}${pageParam}"/>
	  </td></tr>
	</table>
	<div style="height:10px;"></div>
</body>
</html>