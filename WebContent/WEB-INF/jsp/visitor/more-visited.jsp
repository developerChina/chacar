<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="${ctx}/css/visitor/common.css" />
		<link rel="stylesheet" href="${ctx}/css/visitor/more-visited.css" />
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
	</head>
	<body>
		<div class="wrap">
			<div class="head">
				<h2>多访客登记</h2>
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
					    showRadioButton="true" showFolderCheckBox="false" expandOnLoad="true" onbeforenodeselect="onBeforeNodeSelect" >
					    <div property="columns">
					        <div type="indexcolumn" width="50">编号</div>
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
					<a href="${ctx}/visitor/forwardMoreVisitor" class="fl prevpage"></a>
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
		
		
		<script type="text/javascript">
    	
    	 function sendMessage() {
    		 var tree = mini.get("treegrid1");
             var node = tree.getSelectedNode();
             console.info(node)
             if(node==undefined || node==''){
            	 alert('请选择您要拜访的人');
            	 return; 
             }
             
           //异步发送短信 alert("${recordid}-"+node.tel);
            $.ajax({
			  type: 'POST',
			  url: '${ctx}/bevisited/sendSingleMessage',
			  data: {"recordVisitors":$("#recordVisitors").val(),"tel":node.tel},
			  success: function(data){
				  $("#contextDiv").html(data);
				  document.getElementById('popDiv').style.display='block';
				  //window.location.href='${ctx}/vindex.jsp';
			  }
			});
            
         }
    	 //禁止选择父类
    	 function onBeforeNodeSelect(e) {
             var tree = e.sender;
             var node = e.node;
             if (tree.hasChildren(node)) {
                 e.cancel = true;
             }
         }
    	</script>
	</body>
</html>
