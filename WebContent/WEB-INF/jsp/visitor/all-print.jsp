<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="${ctx}/css/visitor/common.css" />
		<link rel="stylesheet" href="${ctx}/css/visitor/print.css" />
		<script src="${ctx}/scripts/boot.js" type="text/javascript"></script>
		<style type="text/css">
	        .New_Button, .Edit_Button, .Delete_Button, .Update_Button, .Cancel_Button
	        {
	            font-size:11px;color:#1B3F91;font-family:Verdana;  
	            margin-right:5px;
	        }
	        .actionIcons span
	        {
	            width:16px;
	            height:16px;
	            display:inline-block;
	            background-position:50% 50%;
	            cursor:pointer;
	        }
	    </style>
	</head>
	<body>
		<div class="wrap">
			<div class="head">
				<h2>打印访客单</h2>
			</div>
			<div class="content clearfix">
				<div class="top">
					<div class="clearfix title">
						<span class="fl"></span>
						<span class="fl">打印列表</span>
					</div>
				</div>
				<div class="bottom clearfix">
					<div class="fl left">
						 <div id="datagrid1" class="mini-datagrid" style="width:450px;height:258px;" idField="cardName" multiSelect="true" showPager="false" allowSortColumn="false">
					      <div property="columns">
					      	  <div type="checkcolumn"></div>
					          <div field="bevisitedName" width="80" headerAlign="center">姓名</div>                
					          <div field="bevisitedAddress" width="200" headerAlign="center">办公地点</div>
					          <div field="auditContent" width="100" headerAlign="center">确认状态</div>
					      </div>
					  </div>
					</div>
					<div class="right fl clearfix">
						<div class="choice">
							<div class="search">
								查询
								<span></span>
							</div>
							<div class="print">
								打印
								<span></span>
							</div>
						</div>
					</div>
					
				</div>
				<div class="btnArea clearfix">
						<input type="submit" class="fl search" value="查询"/>
						<input type="submit" class="fl print" value="打印"/>
					</div>
			</div>
			<div>
				<a href="${ctx}/vindex.jsp" class="foot">返回</a>
			</div>
		</div>
	</body>
	<script type="text/javascript">
	 mini.parse();
     var grid = mini.get("datagrid1");
     
     $.ajax({
	  type: 'POST',
	  url: '${ctx}/visitor/selectRecordInfo',
	  data: {"cardid":"15272519880716031X"},
	  success: function(data){
		 for (var i = 0; i < data.length; i++) {
			 var row = {};
			 row["bevisitedName"]=data[i].bevisited.bevisitedName;
			 row["bevisitedAddress"]=data[i].bevisited.bevisitedAddress;
			 //isAudit;   // tinyint(4) NOT NULL COMMENT '是否同意（0=未审核，1=同意，2=拒绝）' ,
			 if(data[i].visitor.isAudit===0){
				 row["auditContent"]='未审核-'+(data[i].visitor.auditContent==null?'':data[i].visitor.auditContent);
			 }else if(data[i].visitor.isAudit===1){
				 row["auditContent"]='同意-'+(data[i].visitor.auditContent==null?'':data[i].visitor.auditContent);
			 }else if(data[i].visitor.isAudit===2){
				 row["auditContent"]='拒绝-'+(data[i].visitor.auditContent==null?'':data[i].visitor.auditContent);
			 }else{
				 row["auditContent"]=(data[i].visitor.auditContent==null?'':data[i].visitor.auditContent);
			 }
			 grid.addRow(row);
		}
	  }
	});
     
     
     
	</script>
</html>
