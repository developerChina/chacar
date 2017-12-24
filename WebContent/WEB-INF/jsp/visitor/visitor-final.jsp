<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>智能訪客系統</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<link href='http://fonts.googleapis.com/css?family=Revalia' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="${ctx}/layout/final/css/jquery.qtip.css" type="text/css" media="screen">
<script type="text/javascript" src="${ctx}/layout/final/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/layout/final/js/photobooth_min.js"></script>
<script type="text/javascript" src="${ctx}/layout/final/js/jquery.qtip.min.js"></script>
<script type="text/javascript" src="${ctx}/layout/final/js/gbin1.js"></script>
<style>
body {
	font-size: 12px;
	background: #333;
	font-family: 'Revalia', cursive, arial;
}

div#pictures, div#webcam {
	margin: 10px 0 0;
}

h1 {
	margin: 20px 0;
}

div#from {
	margin: 20px 0px;
}

div#from a {
	color: #FFF;
	font-size: 12px;
	background: #1557C3;
	margin: 20px 0;
	border-radius: 5px;
	padding: 10px;
	line-height: 25px;
}

h1 a {
	background: #333;
	border-radius: 5px;
	padding: 5px;
	float: right;
	cusor: hand;
	color: #FFF;
	text-decoration: none;
	margin-left: 20px;
}

#main {
	width: 90%;
	margin: 10px auto;
	background: #FFF;
	color: #333;
	border: 2px solid #FFF;
	box-shadow: 0px 0px 10px #CCC;
	padding: 20px;
	border-radius: 5px;
}

#main article {
	margin-bottom: 50px;
	background: #F8F8F8;
	border-radius: 5px;
	padding: 20px;
	border: 1px solid #bbb;
}

#main #webcam {
	height: 400px;
	width: 100%;
}

#main #plist {
	margin: 20px 0;
	font-weight: bold;
	border-radius: 5px;
	background: #CCC;
	padding: 10px;
}

#main img {
	margin-bottom: 50px;
	background: #F8F8F8;
	border-radius: 10px;
	box-shadow: 0px 0px 5px #888;
}

.clear {
	clear: both;
}

#main ul {
	list-style: none;
	margin: 0;
	padding: 0;
}

#main .photobooth {
	border: 1px solid #ccc;
	border-radius: 5px;
}
</style>
</head>
<body>

	<section id="main">
		<h1>
			長安汽車.自助訪客系統 
			<a href="http://www.gbin1.com" id="site">退出</a>
		</h1>
		<article>
			<h1>
			    click the controll menu below, you can controll crop, hue,
				brightness, take picture
			</h1>
			<div id="webcam"></div>
			<div id="plist">picture list</div>
			<div id="pictures">
				<div class="nopic">no pictures</div>
			</div>
			<div id="from">
				<a href="http://www.gbin1.com/gb/">via gbtags.com</a>
			</div>
			<div class="clear"></div>
		</article>
	</section>
</body>
</html>