<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="${ctx}/css/visitor/common.css" />
<link rel="stylesheet" href="${ctx}/css/visitor/more-visited.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/js/ligerUI/skins/ligerui-icons.css"/>
<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
<script src="${ctx}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="${ctx}/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
<script src="${ctx}/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script src="${ctx}/js/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
    	/** 员工表单提交 */
		$("#exampleForm").submit(function(){
			var username = $("#username");
			var status = $("#status");
			var msg = "";
			if ($.trim(username.val()) == ""){
				msg = "姓名不能为空！";
				username.focus();
			}else if ($.trim(status.val()) == ""){
				msg = "状态不能为空！";
				status.focus();
			}
			if (msg != ""){
				$.ligerDialog.warn(msg);
				return false;
			}else{
				return true;
			}
			$("#exampleForm").submit();
		});
    });
</script>
</head>
<body>
	<!--   添加      -->
	<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
		<tr valign="top">
			<td>
				<form action="${ctx}/example/addExample" id="exampleForm" method="post">
					<!-- 隐藏表单，flag表示添加标记 -->
					<input type="hidden" name="flag" value="2">
					<table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
						<tr>
							<td class="font3 fftd">
								<table>
									<tr>
										<td>姓&nbsp;名：
										   <input type="text" name="username" id="username" size="20" />
										</td>
										<td>状&nbsp;态：
											<input type="text"name="status" id="status" size="20" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td class="main_tdbor"></td>
						</tr>
						<tr>
							<td align="left">
							  <input type="submit" value="添加">&nbsp;&nbsp;
							  <input type="reset" value="取消 ">
						   </td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>
</body>
</html>
