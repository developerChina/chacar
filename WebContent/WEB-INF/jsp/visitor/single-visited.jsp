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
					
				   <div style="width:800px;">
				        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
				            <table style="width:100%;">
				                <tr>
					                <td style="width:100%;">
					                 <div id="ids"></div>
				                    </td>
				                    <td style="white-space:nowrap;">
				                        <input id="telphone" class="mini-textbox" emptyText="手机号" style="width:150px;" onBlur="search"/>
				                        <input id="name" class="mini-textbox" emptyText="姓名" style="width:150px;" onBlur="search"/>    
				                        <a class="mini-button" iconCls="icon-search" onclick="search()">查询</a>
				                    </td>
				                </tr>
				            </table>           
				        </div>
				    </div>
				    <div id="datagrid1" class="mini-datagrid" style="width:800px;height:280px;" 
				        url="${ctx}/bevisited/getEmployeees" idField="id" 
				        allowCellEdit="true" allowCellSelect="true"  multiSelect="true" 
				        editNextOnEnterKey="true"  editNextRowCell="true" showPager="false">
				        <div property="columns">
				            <div type="checkcolumn" width="20"></div>
					    	<div field="id" visible="false">员工id</div>
					        <div field="dept" width="50">部门</div>
					        <div field="job" width="80">职位</div>
					        <div field="name" width="80" >姓名</div>
					        <div field="phone" width="100">手机</div>                      
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


		<script type="text/javascript">
			mini.parse();
	    	var grid = mini.get("datagrid1");
	        grid.load();
	        
	    	function sendMessage() {
	             var nodes = grid.getSelecteds();
	             console.info(nodes);
	             if(nodes.length<=0){
	            	 alert('请选择您要拜访的人');
	            	 return;
	             }
	             
	             var ids =[];
	             for (var i = 0; i < nodes.length; i++) {
	            	 if(nodes[i].phone==undefined || nodes[i].phone==''){
	                	 alert(nodes[i].name+'没有维护电话不能访问');
	                	 return; 
	                 }
	            	 ids.push(nodes[i].id);
				}
	            $.ajax({
				  type: 'POST',
				  url: '${ctx}/bevisited/sendMoreMessage',
				  data: {"recordVisitors":$("#recordVisitors").val(),"ids":ids.join(",")},
				  success: function(data){
					  //$("#contextDiv").html(data);
					  //document.getElementById('popDiv').style.display='block';
					  window.location.href='${ctx}/vindex.jsp'; 
				  }
				});
	      }
	    
	     function search(){
	    	 var telphone = mini.get("telphone").getValue();
	    	 var name = mini.get("name").getValue();
	    	 $.ajax({
				  type: 'POST',
				  url: '${ctx}/bevisited/getEmployeees',
				  data: {name:name,telphone:telphone},
				  success: function(datas){
					  for (i = 0; i < datas.length; i++) {
						  //判断是否添加
						  var data=datas[i];
						  if(isAdd(data)){
							  grid.addRow(data);
							  grid.setSelected(data); 
						  }else{
							  console.info("存在")
						  }
					  }
				  }
				});
	     }
	     
	     function isAdd(data){
	    	var selecteds=grid.getSelecteds();
	    	grid.selectAll();
	    	var selectedAll=grid.getSelecteds();
	    	var bool=true;
	    	for (i = 0; i < selectedAll.length; i++) {
				 if(selectedAll[i].id==data.id){
					 bool= false;
				 }
			}
	    	grid.deselectAll();
	    	for (var j = 0; j < selecteds.length; j++) {
	    		grid.setSelected(selecteds[j]);
			}
	        return bool;
	     }
	     
	     $(document).ready(function(){
		     $(document).bind("contextmenu",function(e){
		         return false;
		     });
		 });
    	</script>
	</body>
</html>
