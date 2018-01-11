<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>通道组的授权</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
		            
		  var boxs  = $("input[type='checkbox'][id^='box_']");
		  // 给其授权 按钮添加 点击事件
		  $("#upPG").click(function(){
			  /* var mypjname =document.getElementById("mypjname").value; */
			  var msg = "";
		 		/* if ($.trim(mypjname) == ""){
					msg = "授权名称不能为空！";
				} */
		 		if (msg != ""){
					$.ligerDialog.warn(msg);
					return false;
				}else{
					/** 获取到用户选中的复选框  */
		      		   var checkedBoxs = boxs.filter(":checked");
		      		   if(checkedBoxs.length < 1){
		      			   $.ligerDialog.warn(" 请选择一个通道组！");
		      		   }else{
		      			   var ids = checkedBoxs.map(function(){
		      				   return this.value;
		      			   })
		      			 $("#addEmpToPj").submit();
		      		   		}
				}
		 		
		 		
/* 点击事件end */  })
 
		   /* end */});
	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：通道的绑定授权  &gt; 添加授权</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    <form action="${ctx}/PassagewayJurisdiction/shouPJG" method="post" id="addEmpToPj">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    <input type="hidden" name="flag" value="2"/>
		    	<table>
		    		<tr>
		    		<tr>
		    			<td class="font3 fftd">授权名称：<input type="text" name="pjname" id="mypjname"  size="20"/>
		    			</td>
		    		</tr>
		    			<td class="font3 fftd">给当前员工添加权限：
		    			<c:forEach items="${requestScope.findEmployeeById}" var="emps">
		    				<input type="hidden" name="pjemps" id="empname" value="${emps.id}" size="20"/>${emps.name}&nbsp;
		    			</c:forEach>
		    			<%-- <input type="hidden" name="pjemp" id="empname" value="${findEmployeeById.id}" size="20"/>
		    			${findEmployeeById.name}
		    			<input type="hidden" name="pjcard" id="cardno" value="${findEmployeeById.cardno}" size="20"/>
		    			 --%></td>
		    		</tr>
		    			
		    		<tr>
		    			<td class="font3 fftd">请选择一个分组：<br/><br/>
		    			<c:forEach items="${passageGroups}" var="passageGroup" varStatus="stat">
         				<input type="checkbox" name="pjgroup" id="box_${stat.index}" value="${passageGroup.pgid}"/>
         					${passageGroup.pgname}
         					(<c:forEach items="${passageGroup.orderItems}" var="myAs">
					  		${myAs.passagewayName}
					  		</c:forEach>)
					  		<br/>
		    			</c:forEach>
		    			</td>
		    		</tr>
		    		
		    		
		    		
		    		<%-- <tr>	
		    		<tr>
		    		<td class="font3 fftd">请选择一个通道：<br>
		    			<c:forEach items="${pgPassageways}" var="Pname" varStatus="stat">
         				<input type="checkbox" name="pjpassageway" id="box_${stat.index}" value="${Pname.passagewayName}"/>${Pname.passagewayName}&nbsp;
		    			</c:forEach>
		    		</td>
		    		<tr> --%>
		    		
		    		
		    		
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd"><input type="button" id="upPG" value="&nbsp;&nbsp;给其授权&nbsp;&nbsp;">&nbsp;<input type="button" onclick="javascript:window.history.back(-1);" value="&nbsp;&nbsp;返回&nbsp;&nbsp; "></td></tr>
		  </table>
		  </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>