<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>修改门禁组的信息</title>
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
		                  var checkeds = document.getElementsByName("meidaHidden");
		                //拆分为字符串数组
		                  //var checkArray =checkeds.split(",");
		                  //获得所有的复选框对象
		                 var checkBoxAll = $("input[name='aids']");
		                 //获得所有复选框的value值，然后，用checkArray中的值和他们比较，如果有，则说明该复选框被选中
		                for(var i=0;i<checkeds.length;i++){
		                     //获取所有复选框对象的value属性，然后，用checkArray[i]和他们匹配，如果有，则说明他应被选中
		                     $.each(checkBoxAll,function(j,checkbox){
		                         //获取复选框的value属性
		                         var checkValue=$(checkbox).val();
		                         if(checkeds[i].value==checkValue){
		                             $(checkbox).attr("checked",true);
		                         }
		                     })
		                 }
		             };
		             var boxs  = $("input[type='checkbox'][id^='box_']");
		           /** 修改绑定点击事件 */
		      	   $("#upAG").click(function(){
		      		 var myagname =document.getElementById("myagname").value;
		      			var myagid =document.getElementById("myagid").value;
				 		var msg = "";
				 		if ($.trim(myagname) == ""){
							msg = "门禁组名称不能为空！";
						}
				 		if (msg != ""){
							$.ligerDialog.error(msg);
							return false;
				}else{
		      		   /** 获取到用户选中的复选框  */
		      		   var checkedBoxs = boxs.filter(":checked");
		      		   if(checkedBoxs.length < 1){
		      			   $.ligerDialog.error(" 请选择一个门禁到门禁组！");
		      		   }else{
		      			   /** 得到用户选中的所有的需要删除的ids */
		      			   var ids = checkedBoxs.map(function(){
		      				   return this.value;
		      			   })
		      			   
		      			   $.ligerDialog.confirm("确认要修改吗?","修改门禁组",function(r){
		      				   if(r){
		      					    //弹框 alert("删除："+ids.get()+myagname+"id"+myagid);
		      					   // 发送请求
		      					   $("#updateAg").submit();
		      					   //window.location = "${ctx}/accessGroup/updateAG?flag=2&agssxj=" + ids.get()+"&agid="+myagid+"&agname="+myagname;
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
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：门禁组管理  &gt; 修改门禁组</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    		<c:forEach items="${accessgroupById.orderItems}" var="myid">
    			<input type="hidden" value="${myid.accessid}" name="meidaHidden">
    		</c:forEach>
    	  
    	  <form action="${ctx}/accessGroup/updateAG" method="post" id="updateAg">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<input type="hidden" name="flag" value="2"/>
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">门禁组名称：<input type="text" name="agname" id="myagname" value="${accessgroupById.agname}" size="20"/>
		    				<input type="hidden" name="agid" value="${accessgroupById.agid}" id="myagid">
		    			</td>
		    		</tr>
		    			
		    		<tr>
		    			<td class="font3 fftd">所属下级门禁名称：<br>
		    			<c:forEach items="${agAlevators}" var="Aname" varStatus="stat">
         				<input type="checkbox" name="aids" id="box_${stat.index}" value="${Aname.accessid}"/>${Aname.accessname}&nbsp;&nbsp;${Aname.accessid}
		    			</c:forEach>
		    			</td>
		    		</tr>
		    		
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd"><input type="button" id="upAG" value="修改">&nbsp;&nbsp;<input type="button" onclick="javascript:window.history.back(-1);" value="返回"></td></tr>
		  </table>
		  </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>