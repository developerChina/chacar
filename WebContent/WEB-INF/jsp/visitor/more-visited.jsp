<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="${ctx}/css/visitor/common.css" />
		<link rel="stylesheet" href="${ctx}/css/visitor/more-visited.css" />
		<link href="${ctx}/demo/demo.css" rel="stylesheet" type="text/css" />
    	<script src="${ctx}/scripts/boot.js" type="text/javascript"></script>
    	<script type="text/javascript">
    	
    	 function getCheckedNodes() {
    		 var tree = mini.get("treegrid1");
             var node = tree.getSelectedNode();
             console.info(node);
             alert(node.tel)
         }
    	 
    	 function onBeforeNodeSelect(e) {
             var tree = e.sender;
             var node = e.node;
             if (tree.hasChildren(node)) {
                 e.cancel = true;
             }
         }
    	</script>
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
					    showRadioButton="true" allowMoveColumn="false" allowResizeColumn="false"
					    showFolderCheckBox="false" expandOnLoad="true" onbeforenodeselect="onBeforeNodeSelect" >
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
				</div>
				<div class="btnArea clearfix">
					<a href="${ctx}/visitor/forwardMoreVisitor" class="fl prevpage"></a>
					<a href="#" class="fl notice" onclick="getCheckedNodes()"></a>
				</div>
			</div>
			<div>
				<a href="${ctx}/vindex.jsp" class="foot">返回</a>
			</div>
		</div>
	</body>
</html>