<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>车辆授权管理</title>
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
		 		   /** 获取到选中的复选框  */
		 		   var checkedBoxs = boxs.filter(":checked");
		 		   if(checkedBoxs.length < 1){
		 			   $.ligerDialog.warn("请选择一个需要删除的车辆授权！");
		 		   }else{
		 			   /** 得到选中的所有的需要删除的ids */
		 			   var ids = checkedBoxs.map(function(){
		 				   return this.value;
		 			   })
		 			   
		 			   $.ligerDialog.confirm("确认要删除吗?","删除车辆授权",function(r){
		 				   if(r){
		 					   window.location = "${ctx }/car/deletecarAuthority?ids=" + ids.get();
		 				   }
		 			   });
		 		   }
		 	   })
		 	   /** 添加员工绑定点击事件 */
		 	   $("#add").click(function(){
		 		   window.location = "${ctx }/car/addcarAuthority?flag=1";
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
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：车辆授权 &gt; 车辆授权查询</td>
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
			  	<form name="empform" method="post" id="empform" action="${ctx}/car/carAuthority">
				    <table width="100%" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="font3">
					    	车主姓名：<input type="text" name="name" value="${name}">
					    	车牌号：<input type="text" name="carno" value="${carAuthority.carno }">
					    	<input type="submit" value="&nbsp;&nbsp;搜索&nbsp;&nbsp;"/>&nbsp;
					    	<input id="delete" type="button" value="&nbsp;&nbsp;删除&nbsp;&nbsp;"/>&nbsp;
					    	<input id="add" type="button" value="&nbsp;&nbsp;授权&nbsp;&nbsp;"/>
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
			  <td>车主姓名</td>
			  <td>车牌号码</td>
			  <td>所属车场</td>
			</tr>
			<c:forEach items="${requestScope.authoritys}" var="authority" varStatus="stat">
				<tr id="data_${stat.index}" align="center" class="main_trbg">
					<td><input type="checkbox" id="box_${stat.index}" value="${authority.id}"></td>
					 <td>${authority.name }</td>
					 <td>${authority.carno }</td>
					 <td>${authority.carPark.name }</td>
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
		  	        submitUrl="${ctx}/car/carAuthority?pageIndex={0}${pageParam}"/>
		  </td>
	  </tr>
	</table>
	<div style="height:10px;"></div>
</body>
</html>