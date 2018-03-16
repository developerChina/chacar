<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<title>北京长安取号系统</title>
<link rel="stylesheet" href="${ctx}/css/frozen/css/frozen.css">
<link rel="stylesheet" href="${ctx}/css/frozen/demo.css">
<link rel="stylesheet" href="${ctx}/css/keybord/keybord.css">

<script type="text/javascript" src="${ctx}/css/keybord/js/jquery.min.js"></script>
<script src="${ctx}/css/frozen/lib/zepto.min.js"></script>
<script src="${ctx}/css/frozen/js/frozen.js"></script>

<script src="${ctx}/css/keybord/layer_mobile/layer.js"	type="text/javascript"></script>
<script src="${ctx}/css/keybord/js/index.js" type="text/javascript"></script>
</head>

<body ontouchstart>
	<header class="ui-header ui-header-positive ui-border-b">
		<h1>北京长安取号系统(${island.iname })</h1>
	</header>
	<section class="ui-container">
		<section id="table">
			<div class="demo-item">
				<p class="demo-desc">请输入车牌号：</p>
				<div class="demo-block">
					<div class="ui-flex ui-flex-pack-start">
						<div style="width: 98%;">
							<div class="car_input">
								<ul class="clearfix ul_input">
									<li class="input_pro"><span></span></li>
									<li class="input_pp input_zim"><span></span></li>
									<li class="input_pp"><span></span></li>
									<li class="input_pp"><span></span></li>
									<li class="input_pp"><span></span></li>
									<li class="input_pp"><span></span></li>
									<li class="input_pp"><span></span></li>
								</ul>
							</div>
						</div>
						<div id="jp_pro"></div>
					</div>
					<div class="ui-flex ui-flex-pack-center">
						<div class="demo-block">
							<div class="ui-btn-wrap">
								<button id="btn1" class="ui-btn ui-btn-primary">取号</button>
								<button id="btn2" class="ui-btn ui-btn-danger">查询</button>
							</div>
						</div>
					</div>

				</div>
			</div>

			<div class="demo-item">
				<p class="demo-desc">总排队：<span id="all"></span> &nbsp;&nbsp;&nbsp;&nbsp; 等待人数：<span id="waiting"></span></p>
				<div class="demo-block">
					<table class="ui-table ui-border" id="list">
						<thead>
							<tr>
								<th>状态</th>
								<th>排队号码</th>
								<th>车牌号码</th>
								<th>备注</th>
							</tr>
						</thead>
						<tbody>
							<!--  
							<tr style="color:green">
								<td>当前</td>
								<td>2</td>
								<td>京N3G8B0</td>
								<td>VIP号</td>
							</tr>
							<tr>
								<td>等待</td>
								<td>3</td>
								<td>京N3G8B0</td>
								<td>普通号</td>
							</tr>
							<tr style="color:red">
								<td>等待</td>
								<td>4</td>
								<td>京N3G8B0</td>
								<td>普通号</td>
							</tr>
							-->
						</tbody>
					</table>
				</div>
			</div>
		</section>
	</section>
	<!-- /.ui-container-->
	<script>
	$(function(){
		
		$("#btn1").click(function(){
	         var carno=new String(getCarNo());
	         if(carno.length!=7){
	        	 layer.open({
					content: '请录入完整车牌',
					skin: 'msg',
					time: 1
				 });
				 return 
	         }else{
	        	 	if(confirm("是否确定排队,排队成功后将之前所有卸货岛排队信息删除")==true){
	        	 		//加入队列，查询排队情况
		   	        	 var no=${island.no};
		   	        	 $.post("${ctx}/queuingI/addQueue",{island_no:no,car_code:carno.toString(),isadd:0},
	   	        			 function(obj){
	   	        				 if(obj.status){
	   	        					 $("#all").html(obj.all);
	   	        					 $("#waiting").html(obj.waiting);
	   	        					 var tbody=$("#list").find("tbody");
	   	        					 tbody.empty();
	   	        					 loopQueue(tbody,obj.list);
	   	        					 layer.open({
	   	        						 	content: obj.message,
	   	        							skin: 'msg',
	   	        							time: 1
	   	        						 });
	   	        				 }else{
	   	        					 layer.open({
	   	        							content: obj.message,
	   	        							skin: 'msg',
	   	        							time: 1
	   	        						 }); 
	   	        				 }
	   		        	});
	        	 	}
	         }
		});
		
		$("#btn2").click(function(){
			 var carno=new String(getCarNo());
			 if(carno.length!=7){
	        	 layer.open({
					content: '请录入完整车牌',
					skin: 'msg',
					time: 1
				 });
				 return 
	         }else{
	        	//查询排队情况
	        	 var no=${island.no};
	        	 $.post("${ctx}/queuingI/addQueue",{island_no:no,car_code:carno.toString(),isadd:1},
	        			 function(obj){
			        		 if(obj.status){
			        			 layer.open({
	        							content: obj.message,
	        							skin: 'msg',
	        							time: 1
	        						 });
			        			 $("#all").html(obj.all);
	        					 $("#waiting").html(obj.waiting);
	        					 var tbody=$("#list").find("tbody");
	        					 tbody.empty();
	        					 loopQueue(tbody,obj.list);
		    				 }else{
		    					 layer.open({
	        							content: obj.message,
	        							skin: 'msg',
	        							time: 1
	        						 });
		    				 }
		        	     });
	         }
		});
	})
	
	function loopQueue(tbody,list){
		var carno=new String(getCarNo());
		for (i = 0; i < list.length; i++) {
			if(i==0){
				tbody.append("<tr style='color:green'>"+
						"<td>当前</td>"+
						"<td>"+list[i].queue_number+"</td>"+
						"<td>"+list[i].car_code+"</td>"+
						"<td>"+list[i].remarks+"</td>"+
					"</tr>")
			}else if(carno.toString()==list[i].car_code){
				tbody.append("<tr style='color:red'>"+
						"<td>自己</td>"+
						"<td>"+list[i].queue_number+"</td>"+
						"<td>"+list[i].car_code+"</td>"+
						"<td>"+list[i].remarks+"</td>"+
					"</tr>")
			}else{
				tbody.append("<tr>"+
						"<td>等待</td>"+
						"<td>"+list[i].queue_number+"</td>"+
						"<td>"+list[i].car_code+"</td>"+
						"<td>"+list[i].remarks+"</td>"+
					"</tr>")
			}
		 }
	}
	
	function getCarNo(){
		return $(".car_input").attr("data-pai");
	}
	</script>
</body>
</html>