<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>修改门通道的信息</title>
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
		         
		  //当页面加载完成的时候，自动调用该方法
          window.onload=function(){
              //获得所要回显的值，
              var checkeds = $("#meidaHidden").val();
            //拆分为字符串数组
              var checkArray =checkeds.split(",");
              //获得所有的复选框对象
             var checkBoxAll = $("input[name='pjgroup']");
             //获得所有复选框的value值，然后，用checkArray中的值和他们比较，如果有，则说明该复选框被选中
            for(var i=0;i<checkArray.length;i++){
                 //获取所有复选框对象的value属性，然后，用checkArray[i]和他们匹配，如果有，则说明他应被选中
                 $.each(checkBoxAll,function(j,checkbox){
                     //获取复选框的value属性
                     var checkValue=$(checkbox).val();
                     if(checkArray[i]==checkValue){
                         $(checkbox).attr("checked",true);
                     }
                 })
             }
         };   
		            
		             
		             
		             
		           var boxs  = $("input[type='checkbox'][id^='box_']");
		           /** 修改绑定点击事件 */
		      	   $("#upEG").click(function(){
		      		 	var mypgname =document.getElementById("mypgname").value;
		      			var mypgid =document.getElementById("mypgid").value;
				 		var msg = "";
				 		if ($.trim(mypgname) == ""){
							msg = "门禁授权名称不能为空！";
						}
				 		if (msg != ""){
							$.ligerDialog.error(msg);
							return false;
				}else{
		      		   /** 获取到用户选中的复选框  */
		      		   var checkedBoxs = boxs.filter(":checked");
		      		   if(checkedBoxs.length < 1){
		      			   $.ligerDialog.error(" 请选择一个门禁组！");
		      		   }else{
		      			   /** 得到用户选中的所有的需要删除的ids */
		      			   var ids = checkedBoxs.map(function(){
		      				   return this.value;
		      			   })
		      			   
		      			   $.ligerDialog.confirm("确认要修改吗?","修改门禁组",function(r){
		      				   if(r){
		      					   //alert("删除："+ids.get()+"=========="+mypgname+"========"+mypgid);
		      					   // 发送请求
		      					   $("#updatePJ").submit();
		      					  //window.location = "${ctx}/PassagewayJurisdiction/updetaPj?flag=2&pjgroup=" + ids.get()+"&pjid="+mypgid+"&pjname="+mypgname;
		      				   }
		      			  	 });
		      		   		}
					}  
		      	   })
		      	   
		      	   
		             
		             
		            
		   /* end */});
	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：电梯组管理  &gt; 修改电梯组</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	  <input type="hidden" value="${myUPPassagewayj.pjgroup}" id="meidaHidden">
    	  <form action="${ctx}/PassagewayJurisdiction/updetaPj" method="post" id="updatePJ">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    <input type="hidden" name="flag" value="2"/>
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">电梯授权名称：<input type="text" name="pjname" id="mypgname" value="${myUPPassagewayj.pjname}" size="20"/>
		    				<input type="hidden" name="pjid" value="${myUPPassagewayj.pjid}" id="mypgid">
		    			</td>
		    		</tr>
		    			
		    		<tr>
		    			<td class="font3 fftd">所属下级电梯组名称：
		    			<c:forEach items="${passagewayGroups}" var="EGname" varStatus="stat">
		    			<br>
         				<input type="checkbox" name="pjgroup" id="box_${stat.index}" value="${EGname.pgid}"/>
		    			${EGname.pgname}:
		    			<c:forEach items="${EGname.orderItems}" var="EGxj" varStatus="stat">
		    				${EGxj.passagewayName}
		    			</c:forEach>
		    			<br>
		    			</c:forEach>
		    			</td>
		    		</tr>
		    		
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd"><input type="button" id="upEG" value="修改">&nbsp;&nbsp;<input type="button" onclick="javascript:window.history.back(-1);" value="返回 "></td></tr>
		  </table>
		  </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>