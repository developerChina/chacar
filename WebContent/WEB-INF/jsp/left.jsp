<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>北京长安总控平台</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
		<script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
		<script language="javascript" type="text/javascript"> 
			$(function(){
				/** 给左侧功能菜单绑定点击事件  */
				$("td[id^='navbg']").click(function(){
				   	 /** 获取一级菜单的id */
				   	 var navbgId = this.id;
				   	 /** 获取对应的二级菜单id */
				   	 var submenuId = navbgId.replace('navbg','submenu');
				   	 /** 控制二级菜单显示或隐藏  */
				   	 $("#"+submenuId).toggle();
				   	 /** 控制关闭或者开启的图标*/
				   	 $("#"+navbgId).toggleClass("left_nav_expand");
				   	 /** 控制其他的一级菜单的二级菜单隐藏按钮都关闭  */
				   	 $("tr[id^='submenu']").not("#"+submenuId).hide();
				   	 /** 控制其他一级菜单的图片显示关闭  */
				   	 $("td[id^='navbg']").not(this).removeClass().addClass("left_nav_closed");
				})
			})
		</script>
	</head>
<body>
	<div style="margin:10px;background-color:#FFFFFF; text-align:left;">
		<table width="200" height="100%" border="0" cellpadding="0" cellspacing="0" class="left_nav_bg">
		  
		  <tr><td class="left_nav_top"><div class="font1">系统管理</div></td></tr>
		  <tr valign="top">
		    <td class="left_nav_bgshw" height="50">
			  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/user/selectUser" target="main">用户管理</a></img></p>
			  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="user/addUser?flag=1" target="main">资源管理</a></img></p>
			  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="user/addUser?flag=1" target="main">权限分配</a></img></p>
			</td>
		  </tr>
		  <tr><td height="2"></td></tr>

		  <tr><td id="navbg2" class="left_nav_closed" ><div class="font1">员工管理</div></td></tr>
		  <tr valign="top" id="submenu2">
		    <td class="left_nav_bgshw" height="50">
			  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/dept/selectDept" target="main">部门管理</a></img></p>
			  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/job/selectJob" target="main">职位管理</a></img></p>
			  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="dept/selectDept" target="main">楼层分配</a></img></p>
			  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx}/employee/selectEmployee" target="main">员工管理</a></img></p>
			</td>
		  </tr>
		  <tr><td height="2"></td></tr>
		  
		  <tr><td id="navbg3" class="left_nav_closed" ><div class="font1">访客管理</div></td></tr>
		  <tr valign="top" id="submenu3" style="display: none">
		    <td class="left_nav_bgshw" height="50">
			  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="job/selectJob" target="main">黑名单</a></img></p>
			  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="job/addJob?flag=1" target="main">备份事由</a></img></p>
			  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="job/addJob?flag=1" target="main">访客查询</a></img></p>
			</td>
		  </tr>
		  <tr><td height="2"></td></tr>

		  <tr><td id="navbg4" class="left_nav_closed" ><div class="font1">停车场管理</div></td></tr>
		  <tr valign="top" id="submenu4" style="display: none">
		    <td class="left_nav_bgshw" height="50">
			  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="dept/addDept?flag=1" target="main">出入口管理</a></img></p>
			  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx }/employee/addEmployee?flag=1" target="main">车辆管理</a></img></p>
			  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx }/employee/addEmployee?flag=1" target="main">出入查询</a></img></p>
			</td>
		  </tr>
		  <tr><td height="2"></td></tr>
		  
		  <tr><td id="navbg5" class="left_nav_closed" ><div class="font1">排队叫号</div></td></tr>
		  <tr valign="top" id="submenu5" style="display: none">
		    <td class="left_nav_bgshw tdbtmline" height="50">
		      <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx }/notice/selectNotice" target="main">业务类别</a></img></p>
		      <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="dept/addDept?flag=1" target="main">卸货岛管理</a></img></p>
		      <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx }/notice/selectNotice" target="main">操作人员管理</a></img></p>
			  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx }/notice/selectNotice" target="main">操作员绩效统计</a></img></p>
		      <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx }/employee/addEmployee?flag=1" target="main">排队监控统计</a></img></p>
			</td>
		  </tr>
		  <tr><td height="2"></td></tr>

		  <tr><td id="navbg6" class="left_nav_closed" ><div class="font1">物流车管理</div></td></tr>
		  <tr valign="top" id="submenu6" style="display: none">
		    <td class="left_nav_bgshw tdbtmline" height="50">
		      <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx }/employee/selectEmployee" target="main">定位仪</a></img></p>
		      <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx }/employee/selectEmployee" target="main">车辆入驻</a></img></p>
			  <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx }/notice/selectNotice" target="main">查询统计</a></img></p>
              <p class="left_nav_link"><img src="${ctx}/images/left_nav_arrow.gif">&nbsp;&nbsp;<a href="${ctx }/notice/selectNotice" target="main">违规处理</a></img></p>
			</td>
		  </tr>
		  <tr><td height="2"></td></tr>

		  <tr valign="top"><td height="100%" align="center"><div class="copycct"><br /><strong>技术支持：</strong><br><strong>北京华隆辰信息技术有限公司</strong><br>Http://www.hualc.com</div></td></tr>
		  <tr><td height="10"><img src="${ctx}/images/left_nav_bottom.gif" height="10"></img></td></tr>
		  <tr><td height="10" bgcolor="#e5f0ff">&nbsp;</td></tr>
		</table>
	</div>
</body>
</html>