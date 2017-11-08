<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>多租户车辆定位系统</title>
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge; chrome=1">
		<meta name="keywords" content="多租户车辆定位系统">
		<meta name="description" content="多租户车辆定位系统">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<link href="${ctx }/css/location/login-fx_pc_all.css" rel="stylesheet" type="text/css">
		<link href="${ctx }/css/location/login-account.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" href="${ctx }/css/location/login-public.css"/>
		<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
		<script type="text/javascript"> 
		  function subgo()
			{
			  var username = $("#username").val();
			  var password = $("#password").val(); 
			  if(username!=''&&null!=username&&username!="" && password!=''&&null!=password&&password!=""){
				  $.get("http://118.192.5.136:89/gpsonline/GPSAPI?version=1&method=loginSystem&name="+username+"&pwd="+password,function(data,status){
				       //alert("Data: " + data + "\nStatus: " + status);
				       var o=JSON.parse(data);
				       if(o.success && status){
				    	   window.open ('http://118.192.5.136:89/mygpsonline/gpsonline/jsp/monitor/main.jsp','车辆定位','width=1024,height=768,top=20,left=20,toolbar=no,toolbar=no,menubar=no,scrollbars=no,resizable=no, location=no,status=no'); 
				       }else{
				    	   alert("用户名密码错误，请重新输入！");
				       }
				   });	
				 
			  }else{
				  if(username=='' || null==username || username==""){
					  $("#username").focus();
					  return ;
				  }
                  if(password==''|| null==password || password==""){
                	  $("#password").focus();
                	  return ;
				  }
			  }
			}
		</script>
	</head>
	<body>
		<div class="account-pane" style="margin-top:20px">
			<div class="account-container signin-container" style="position: relative;">
				<ul class="signin-menu hide clearfix" id="account-menu">
					<li class="menu-account select fl" role="account">位置信息登录</li>
				</ul>
					<div class="signin-content" id="account-content">
						<div class="signin-row">
							<input id="username" name="username" placeholder="用户名">
							<div class="invalid-info" style="display: none;"></div>
						</div>
						<div class="signin-row">
							<input id="password" name="password" type="password" placeholder="密码">
							<div class="invalid-info" style="display: none;"></div>
						</div>
						<div class="signin-row">
							<a href="javascript:void(0)" onclick="subgo()"  class="x-btn" id="signin-btn" style="display: block">登录</a>
						</div>
					</div>
			</div>
		</div>
	</body>
</html>