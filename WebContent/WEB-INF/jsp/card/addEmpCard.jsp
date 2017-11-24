<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>人事管理系统——添加个人授权</title>
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
	    	/**表单提交 */
			$("#cardForm").submit(function(){
				var cardno = $("#cardno");
				var validate = $("#validate");
				var msg = "";
				if ($.trim(cardno.val()) == ""){
					msg = "卡号不能为空！";
					cardno.focus();
				}else if ($.trim(validate.val()) == ""){
					msg = "有效期不能为空！";
					validate.focus();
				}
				if (msg != ""){
					$.ligerDialog.error(msg);
					return false;
				}else{
					return true;
				}
				$("#cardForm").submit();
			});
			
	    	
	    });
		
	 function f_open() {
         $.ligerDialog.open({
             height:600,
             width: 800,
             title : '选择员工',
             url: '${ctx}/employee/selectEmployee', 
             showMax: false,
             showToggle: true,
             showMin: false,
             isResize: true,
             slide: false,
             buttons: [
                 { text: '选择', onclick: function (item, dialog) { appenEmp(dialog, dialog.frame.$("input[type='checkbox'][id^='box_']"),dialog.frame.$("[id='employee_table']")   )  } },
                 { text: '取消', onclick: function (item, dialog) { dialog.close(); } }
             ]
         });
     }
	 function appenEmp(dialog, boxs,table ){
	   var checkedBoxs = boxs.filter(":checked");
	   /** 得到用户选中的所有的需要删除的ids */
	   var ids = checkedBoxs.map(function(){
		   return this.value;
	   })
	   $("#txt2").val(ids.get());
	   
	   console.info(checkedBoxs.lenght)
	   
	   //dialog.close();
	 }
	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：授权管理  &gt; 个人授权</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/card/addEmpCard" id="cardForm" method="post">
    	  <!-- 隐藏表单，flag表示添加标记 -->
    	  <input type="hidden" name="flag" value="2">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd" colspan="2">员工信息：<input type="text" id="txt2"  size="20" onclick='f_open()'></td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">卡&nbsp;&nbsp;&nbsp;&nbsp;号：<input type="text" name="cardno" id="cardno" size="20"/></td>
		    			<td class="font3 fftd">有&nbsp;效&nbsp;期：<input type="text" name="validate" id="validate" size="20"  class="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});" /></td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">通&nbsp;&nbsp;&nbsp;&nbsp;道：<input type="text" name="channel" id="channel" size="20"/></td>
		    			<td class="font3 fftd">电&nbsp;&nbsp;&nbsp;&nbsp;梯：<input type="text" name="floor" id="floor" size="20"/></td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">门&nbsp;&nbsp;&nbsp;&nbsp;禁：<input type="text" name="room" id="room" size="20"/></td>
		    			<td class="font3 fftd">停&nbsp;车&nbsp;场：<input type="text" name="park" id="park" size="20"/></td>
		    		</tr>
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd"><input type="submit" value="添加 ">&nbsp;&nbsp;<input type="reset" value="取消 "></td></tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>