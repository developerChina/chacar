<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>添加员工</title>
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
	    	/** 员工表单提交 */
			$("#add").click(function(){
				var msg = "";
				var name = $("#name");
				var phone = $("#phone");
				var cardno = $("#cardno");
				if ($.trim(name.val()) == ""){
					msg = "姓名不能为空！";
				}
				if ($.trim(phone.val()) == ""){
					msg = "手机号不能为空！";
				}
				if ($.trim(cardno.val()) == ""){
					msg = "员工卡号不能为空！";
				}
				if (msg != ""){
					$.ligerDialog.warn(msg);
					return false;
				}
				$.ajax({
					  type: 'post',
					  url: '${ctx}/employee/AddEmpProving',
					  data: {
						  "cardno":cardno.val()
					  },success: function(data){
						  if(data.status){
							   $("#employeeForm").submit();
						  }else{
							  $.ligerDialog.error(data.message);
						  }
					  }
				});
			});
});
	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：员工管理  &gt; 添加员工</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/employee/addEmployee" id="employeeForm" method="post">
		 	<!-- 隐藏表单，flag表示添加标记 -->
    	 	<input type="hidden" name="flag" value="2">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">姓名：&nbsp;<input type="text" name="name" id="name" size="20"/></td>
		    			<td class="font3 fftd">身份证号码：&nbsp;&nbsp;<input type="text" name="cardId" id="cardId" size="20"/></td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">性别：
									<select name="sex" style="width:143px;">
					    			<option value="0">--请选择性别--</option>
					    			<option value="1">男</option>
					    			<option value="2">女</option>
					    		</select></td>
		    			<td class="font3 fftd">员工编号：&nbsp;&nbsp;&nbsp;&nbsp;<input name="hobby" id="hobby" size="20"/>
					    </td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">
		    			 部门：
						<select  name="dept_id" id="dept_id" style="width:143px;">
						   <option value="">--部门选择--</option>
						   <c:forEach items="${requestScope.depts }" var="dept">
			    				<option value="${dept.id }">${dept.name }</option>
			    			</c:forEach>
						</select>
		    			</td>
		    			<td class="font3 fftd">职位：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    			 <select name="job_id" id="job_id" style="width:143px;">
					    			<option value="">--请选择职位--</option>
					    			<c:forEach items="${requestScope.jobs }" var="job">
					    				<option value="${job.id }">${job.name }</option>
					    			</c:forEach>
					    		</select>
					</td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">手机：&nbsp;<input name="phone" id="phone" size="20"/></td>
		    			
		    			<td class="font3 fftd">员工物理卡号：<input name="cardno" id="cardno" size="20"/>
						<!--&nbsp;车&nbsp;牌&nbsp;号：&nbsp;<input name="carno" id="carno" size="20"/> -->
		    			</td>
		    		</tr>
		    		
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr>
				<td class="font3 fftd">
					政治面貌：<input name="party" id="party" size="40"/>&nbsp;&nbsp;
					QQ&nbsp;&nbsp;号码：<input name="qqNum" id="qqNum" size="20"/>
				</td>
			</tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr>
				<td class="font3 fftd">
					联系地址：<input name="address" id="address" size="40"/>&nbsp;&nbsp;
					邮政编码：<input name="postCode" id="postCode" size="20"/>
				</td>
			</tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr>
				<td class="font3 fftd">
					出生日期：<input class="Wdate" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'});" 
					name="birthday" id="birthday" size="40"/>&nbsp;&nbsp;
					民&nbsp;&nbsp;&nbsp;&nbsp;族：<input name="race" id="race" size="20"/>
				</td>
			</tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr>
				<td class="font3 fftd">
					所学专业：<input  name="speciality" id="speciality" size="40"/>&nbsp;&nbsp;
					电&nbsp;&nbsp;&nbsp;&nbsp;话：<input name="tel" id="tel" size="20"/>
				</td>
			</tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr>
				<td class="font3 fftd">
					学&nbsp;&nbsp;&nbsp;&nbsp;历：<input name="education" id="education" size="40"/>&nbsp;&nbsp;
					邮&nbsp;&nbsp;&nbsp;&nbsp;箱：<input name="email" id="email" size="20"/>
				</td>
			</tr>
			<tr><td class="main_tdbor"></td></tr>
			<td class="font3 fftd">
				备&nbsp;&nbsp;&nbsp;&nbsp;注：<input name="remark" id="remark" size="40"/>&nbsp;
					</td>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd">
			<input type="button" id="add" value="&nbsp;添加&nbsp;">&nbsp;&nbsp;<input type="button" onclick="javascript:window.history.back(-1);"  value="&nbsp;返回 &nbsp; "></td></tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>