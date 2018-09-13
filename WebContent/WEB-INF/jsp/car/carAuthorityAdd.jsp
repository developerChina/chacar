<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>车辆管理</title>
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
    	
    	$('#park').change(function(){ 
			$.post(
				"${ctx}/car/selectByParkid",
				{packid:$(this).children('option:selected').val()},
				function(ways){
					$("#passageway_div").html("");
					for (var i = 0; i < ways.length; i++) {
						$("#passageway_div").append("<input type='checkbox' name='passageway_ids' value='"+ways[i].id+"' checked />&nbsp;&nbsp;"+ways[i].name+"&nbsp;&nbsp;");
					}
				}
			);
		}) 
		
		
		$("#entityForm").submit(function(){
			var passageway_ids = "";
            $("input[name='passageway_ids']:checkbox").each(function(){ 
                if($(this).attr("checked")){
                	passageway_ids += $(this).val()+","
                }
            })
           var carnos ="";
            /** 获取到选中的复选框  */
 		   var checkedBoxs = boxs.filter(":checked");
 		   $(checkedBoxs).each(function(){ 
 			  carnos += $(this).val()+","
           })
            
		   $("#carnos").val(carnos);
            
		   var msg = "";
			if ($.trim(passageway_ids) == ""){
				msg = "出入口不能为空！";
			}else if($.trim($("#carnos").val()) == ""){
				msg = "请选择要授权的车辆";
			}
			
			if (msg != ""){
				$.ligerDialog.warn(msg);
				return false;
			}else{
				return true;
			}
			
			$("#entityForm").submit();
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
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：车辆管理 &gt; 车辆查询</td>
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
			  	<form name="carform" method="post" id="carform" action="${ctx}/car/addcarAuthority">
				    <table width="100%" border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td class="font3">
					    	<!-- 隐藏表单，flag表示添加标记 -->
    	 					<input type="hidden" name="flag" value="1">
					    	车主姓名：<input type="text" name="name" value="${carInfo.name}">
					    	车牌号：<input type="text" name="carno" value="${carInfo.carno }">
					    	所在单位：<input type="text" name="company" value="${carInfo.company}">
					    	工号：<input type="text" name="workNumber" value="${carInfo.workNumber}">
					    	<input type="submit" value="&nbsp;&nbsp;搜索&nbsp;&nbsp;"/>&nbsp;
					    </td>
					  </tr>
					</table>
				</form>
			  </td>
			</tr>
			<tr>
			  <td class="fftd">
			  	<form action="${ctx}/car/addcarAuthority" id="entityForm" method="post">
				  	  <!-- 隐藏表单，flag表示添加标记 -->
			    	  <input type="hidden" name="flag" value="2">
			    	  <input type="hidden" name="carnos" id="carnos" value=""/>
					  <table width="100%" border="0" cellpadding="0" cellspacing="0">
					    <tr>
			    			<td class="font3" style="width:250px;">
				    			停车场：
				    			<select name="park" id="park" style="width:143px;">
					    			<option value="">--请选择--</option>
					    			<c:forEach items="${parks}" var="park">
					    				<option value="${park.id }">${park.name }</option>
					    			</c:forEach>
					    		</select>
			    				出入口：&nbsp;&nbsp;<span id="passageway_div"></span>
			    				<input type="submit" value="&nbsp;&nbsp;授权&nbsp;&nbsp;">
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
			  <td>车牌号</td>
			  <td>联系电话</td>
			  <td>身份证号</td>
			  <td>工号</td>
			  <td>所在单位</td>
			  <td>班组</td>
			  <td>岗位及职务</td>
			  <td>车辆品牌型号</td>
			  <td>车辆属性</td>
			  <td>颜色</td>
			</tr>
			<c:forEach items="${requestScope.cars}" var="car" varStatus="stat">
				<tr id="data_${stat.index}" align="center" class="main_trbg">
					<td><input type="checkbox" id="box_${stat.index}" value="${car.carno}"></td>
					  <td>${car.name}</td>
					  <td>${car.carno}</td>
					  <td>${car.tel}</td>
					  <td>${car.idNumber}</td>
					  <td>${car.workNumber}</td>
					  <td>${car.company}</td>
					  <td>${car.team}</td>
					  <td>${car.job}</td>
					  <td>${car.model}</td>
					  <td>${car.attribute}</td>
					  <td>${car.colour}</td>
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
		  	        submitUrl="${ctx}/car/addcarAuthority?pageIndex={0}&flag=1${pageParam}"/>
		  </td>
	  </tr>
	</table>
	<div style="height:10px;"></div>
</body>
</html>