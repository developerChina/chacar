<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>排队统计</title>
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
    <script src="${ctx}/js/echarts.common.min.js" type="text/javascript"></script>
</head>
<body>
	<!-- 导航 -->
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	  <tr><td height="10"></td></tr>
	  <tr>
	    <td width="15" height="32"><img src="${ctx}/images/main_locleft.gif" width="15" height="32"></td>
		<td class="main_locbg font2"><img src="${ctx}/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：排队叫号系统 &gt; 现场查询统计</td>
		<td width="15" height="32"><img src="${ctx}/images/main_locright.gif" width="15" height="32"></td>
	  </tr>
	</table>
	
	<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
	  <!-- 数据展示区 -->
	  <c:forEach items="${lands}" varStatus="stat" step="4">
		<tr valign="top">
		    <td height="20">
			  <div id="container_${stat.index+0}" style="height:300px;width:300px;"></div>
			</td>
			<td height="20">
			  <div id="container_${stat.index+1}" style="height:300px;width:300px;"></div>
			</td>
			<td height="20">
			  <div id="container_${stat.index+2}" style="height:300px;width:300px;"></div>
			</td>
			<td height="20">
			  <div id="container_${stat.index+3}" style="height:300px;width:300px;"></div>
			</td>
		</tr>
	 </c:forEach>
	  
	</table>
	<div style="height:10px;"></div>
	<script type="text/javascript">
	 
	$.post(
			"${ctx}/queuingH/toPie",
			 function(list){
				for (var i = 0; i < list.length; i++) {
					showChart(list[i]);
				}
			}
		);
	
	
	 function showChart(map){
		var dom = document.getElementById("container_"+map.index);
		var myChart = echarts.init(dom);
		var queueName="";
		console.info(map.ing)
		if(map.ing!=null){
			if(map.ing.source==1){
				queueName="当前处理:VIP,车牌号:"+map.ing.car_code;
			}else{
				queueName="当前处理:普通,车牌号:"+map.ing.car_code;;
			} 
		}
		
		option = {
		    title : {
		        text: map.land.iname,
		        subtext:queueName,
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		   // legend: {
		   //     orient: 'vertical',
		   //     left: 'left',
		   //     data: ['总数','vip','普通']
		   // },
		    series : [
		        {
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:[
		                {value:map.all, name:'总数('+map.all+")"},
		                {value:map.vip, name:'vip('+map.vip+")"},
		                {value:map.o, name:'普通('+map.o+")"},
		            ],
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
		;
		if (option && typeof option === "object") {
		    myChart.setOption(option, true);
		}
	 }
     </script>
</body>
</html>