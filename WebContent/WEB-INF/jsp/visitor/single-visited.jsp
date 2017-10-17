<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="${ctx}/css/visitor/common.css" />
		<link rel="stylesheet" href="${ctx}/css/visitor/single-visited.css" />
		<link href="${ctx}/demo/demo.css" rel="stylesheet" type="text/css" />
    	<script src="${ctx}/scripts/boot.js" type="text/javascript"></script>
    	<script type="text/javascript">
    	
    	 function getCheckedNodes() {
    		 var tree = mini.get("treegrid1");
             var nodes = tree.getCheckedNodes(false);
             console.info(nodes);
             for (var i = 0; i < nodes.length; i++) {
            	 alert(nodes[i].tel)
			}
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
					    showCheckBox="true" checkRecursive="true" allowMoveColumn="false" allowResizeColumn="false"
					    showFolderCheckBox="false" expandOnLoad="true">
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
					<a href="${ctx}/visitor/forwardSingleVisitor" class="fl prevpage"></a>
					<a href="#" class="fl notice" onclick="getCheckedNodes()"></a>
				</div>
			</div>
			<div>
				<a href="${ctx}/vindex.jsp" class="foot">返回</a>
			</div>
		</div>
	</body>
</html>
