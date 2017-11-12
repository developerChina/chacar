<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="${ctx}/css/visitor/common.css" />
		<link rel="stylesheet" href="${ctx}/css/visitor/single-visited.css" />
    	<script src="${ctx}/scripts/boot.js" type="text/javascript"></script>
    	
    	
    	<style type="text/css">
			 
			.mydiv {
			background-color: #fff;
			border: 1px solid #f90;
			text-align: center;
			line-height: 20px;
			font-size: 12px;
			font-weight: bold;
			z-index:99;
			width: 900px;
			height: 300px;
			left:25%;/*FF IE7*/
			top: 100px;/*FF IE7*/
			
			margin-left:-150px!important;/*FF IE7 该值为本身宽的一半 */
			margin-top:-60px!important;/*FF IE7 该值为本身高的一半*/
			
			margin-top:0px;
			
			position:fixed!important;/*FF IE7*/
			position:absolute;/*IE6*/
			
			}
			
		</style>
    	
    	
    	<script type="text/javascript">
    	
    	 function sendMessage() {
    		 var tree = mini.get("treegrid1");
             var nodes = tree.getCheckedNodes(false);
             console.info(nodes);
             if(nodes.length<=0){
            	 alert('请选择您要拜访的人');
            	 return;
             }
             
             var tels =[];
             for (var i = 0; i < nodes.length; i++) {
            	 if(nodes[i].tel==undefined || nodes[i].tel==''){
                	 alert(nodes[i].name+'没有维护电话不能访问');
                	 return; 
                 }
            	 tels.push(nodes[i].id);
			}
            $.ajax({
			  type: 'POST',
			  url: '${ctx}/bevisited/sendMoreMessage',
			  data: {"recordVisitors":$("#recordVisitors").val(),"ids":tels.join(",")},
			  success: function(data){
				  $("#contextDiv").html(data);
				  document.getElementById('popDiv').style.display='block';
				  //window.location.href='${ctx}/vindex.jsp'; 
			  }
			});
             
         }
    	
    	</script>
	</head>
	<body>
		<div class="wrap">
			<div class="head">
				<h2>单访客登记</h2>
			</div>
			<div class="content clearfix">
				<div class="left">
					<div class="clearfix title">
						<span class="fl"></span>
						<span>拜访对象</span>
					</div>
					<div id="treegrid1" class="mini-treegrid" style="width:800px;height:280px;"     
					    url="${ctx}/bevisited/getBevisitedTree" showTreeIcon="true" 
					    treeColumn="taskname" idField="id" parentField="pid" resultAsTree="false"
					    showCheckBox="true" checkRecursive="true" showFolderCheckBox="false" expandOnLoad="true">
					    <div property="columns">
					        <div type="indexcolumn" width="50">编号</div>
					        <div field="id" visible="false">资源id</div>
					        <div name="taskname" field="name" width="150">资源名称</div>
					        <div field="door" width="100">门禁</div>
					        <div field="floor" width="50">楼层</div>
					        <div field="room" width="100">房间</div>
					        <div field="channel" width="100">通道</div>
					        <div field="pname" width="pname">上级资源</div>
					        <div field="status" width="50">状态</div>
					    </div>
					</div>
					<input type="hidden" id="recordVisitors" name="recordVisitors" value="${recordVisitors}">
				</div>
				<div class="btnArea clearfix">
					<a href="${ctx}/visitor/forwardSingleVisitor" class="fl prevpage"></a>
					<a href="#" class="fl notice" onclick="sendMessage()"></a>
				</div>
			</div>
			<div>
				<a href="${ctx}/vindex.jsp" class="foot">返回</a>
			</div>
		</div>
		
		
		<div id="popDiv" class="mydiv" style="display:none;">
		  <br/>
		  <div id="contextDiv"></div>
		  <br/>
		  <a href="javascript:document.getElementById('popDiv').style.display='none';window.location.href='${ctx}/vindex.jsp';">关闭窗口</a>
		</div>

	</body>
</html>
