<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>访客管理</title>
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
	<script src="${ctx}/js/ligerUI/js/plugins/ligerTree.js" type="text/javascript"></script>
	<link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet" />
	
	<script src="${ctx}/scripts/boot.js" type="text/javascript"></script>
	
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
	    	
	 	   
	    })
	    
	</script>
</head>
<body>
	<!-- 导航 -->
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	  <tr><td height="10"></td></tr>
	  <tr>
	    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：访客管理 &gt; 访客查询</td>
		<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
	  </tr>
	</table>
	
	<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
	  <!-- 查询区  -->
	  <tr valign="top">
	    <td colspan="2" height="30">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr>
			  <td class="fftd">
			  	<form name="deptform" method="post" id="deptform" action="${ctx}/visitor/selectVisitor">
				    <table width="100%" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="font3">
					    	姓名：<input type="text" name="cardName" value="${visitorInfo.cardName }">
					    	身份证：<input type="text" name="cardID" value="${visitorInfo.cardID }">
					    	电话：<input type="text" name="telephone" value="${visitorInfo.telephone }">
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
			  <td>姓名</td>
			  <td>身份证号码</td>
			  <td>性别</td>
			  <td>民族</td>
			  <td>电话</td>
			  <td>工作单位</td>
			  <td>地址</td>
			  <td>证件照片</td>
			</tr>
			<c:forEach items="${requestScope.visitors}" var="visitor" varStatus="stat">
				<tr id="data_${stat.index}" align="center" class="main_trbg">
					<td><input type="checkbox" id="box_${stat.index}" value="${visitor.visitorID}"></td>
					 <td>${visitor.cardName }</td>
					 <td>${visitor.cardID }</td>
					 <td>${visitor.cardSex }</td>
					 <td>${visitor.cardNation }</td>
					 <td>${visitor.telephone }</td>
					 <td>${visitor.company }</td>
					 <td>${visitor.cardAddress }</td>
					 <td>
					     <c:if test="${visitor.cardPhoto!=null && visitor.cardPhoto!='' }">
					     <a target="_blank" href="${imgurl}${visitor.cardPhoto }">
						  <img width="80px;" height="60px;" src="${imgurl}${visitor.cardPhoto }">
						  </a>
					     </c:if>
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
		  	        submitUrl="${ctx}/visitor/selectVisitor?pageIndex={0}${pageParam}"/>
		  </td>
	  </tr>
	</table>
	<div style="height:10px;"></div>
</body>
</html>