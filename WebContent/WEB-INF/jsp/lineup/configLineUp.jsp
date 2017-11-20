<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>呼叫服务</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${ctx}/css/css.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${ctx }/js/jquery.qrcode.min.js"></script>
	<link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript">
	  function myQrCode(){ 
		  $("#qrcode").html("");
		  $("#qrcode").qrcode({width:  $("#mywidth").val(),height:  $("#myheight").val(),text: $("#qrcodeTxt").val()});
	  }
	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：排队叫号  &gt; 服务管理</td>
	<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${ctx}/car/addcarPark" id="entityForm" method="post">
    	 <!-- 隐藏表单，flag表示添加标记 -->
    	 <input type="hidden" name="flag" value="2">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">系统名称：<input type="text" id="name" size="20" value="长安汽车排队叫号系统"/></td>
		    			<td class="font3 fftd">系统地址：<input type="text" id="qrcodeTxt" size="45" value=${pageContext.request.contextPath}"http://127.0.0.1:8080/chacar/lineup/forwardLineUp"/></td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">
		    			      二维码长：<input type="text"  id="mywidth" size="5" value="128"/>
		    			      宽：<input type="text"  id="myheight" size="5" value="128"/> 
		    			</td>
		    			<td class="font3 fftd">
		    			      <div id="qrcode"></div>
		    			</td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">
		    			  <input type="button" onclick="myQrCode()" value="&nbsp;&nbsp;生成二维码&nbsp;&nbsp;">
		    			</td>
		    		</tr>
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			<tr>
			    <td align="middle" class="fftd">
			        <input type="submit" value="&nbsp;&nbsp;保存 &nbsp;&nbsp;">
			    </td>
			</tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>