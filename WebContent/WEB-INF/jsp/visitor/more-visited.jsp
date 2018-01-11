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
					 <div style="width:800px;">
				        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
				            <table style="width:100%;">
				                <tr>
					                <td style="width:100%;">
				                    </td>
				                    <td style="white-space:nowrap;">
				                        <input id="telphone" class="mini-textbox" emptyText="手机号" style="width:150px;" onBlur="search"/>
				                        <input id="name" class="mini-textbox" emptyText="姓名" style="width:150px;" onBlur="search"/>    
				                        <a class="mini-button" iconCls="icon-search" onclick="search()">查询</a>
				                        <a class="mini-button" iconCls="icon-cancel" onclick="clear()">清空</a>
				                    </td>
				                </tr>
				            </table>           
				        </div>
				    </div>
				    <div id="datagrid1" class="mini-datagrid" style="width:800px;height:280px;" 
				        url="${ctx}/bevisited/getEmployeees" idField="id" 
				        allowCellEdit="true" allowCellSelect="true" 
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
    	
    	    mini.parse();
	    	var grid = mini.get("datagrid1");
	        grid.load();
	        
	    	function sendMessage() {
	             var node = grid.getSelected ();
	             if(node==undefined || node==''){
	            	 alert('请选择您要拜访的人');
	            	 return; 
	             }
	             if(node.phone==undefined || node.phone==''){
	            	 alert(node.name+'没有维护电话不能访问');
	            	 return; 
	             }
	             
	            $.ajax({
				  type: 'POST',
				  url: '${ctx}/bevisited/sendSingleMessage',
				  data: {"recordVisitors":$("#recordVisitors").val(),"id":node.id},
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
	    	 grid.load(
	    			 {name:name,telphone:telphone},
	    			 function() {grid.selectAll();}
	    			 );
	     }
	     function clear(){
	    	 mini.get("telphone").setValue('');
	    	 mini.get("name").setValue('');
	     }
        $(document).ready(function(){
      	     $(document).bind("contextmenu",function(e){
      	         return false;
      	     });
			 var WinAlerts = window.alert;
		     window.alert = function (e) {
			     if (e != null && e.indexOf("提示内容")>-1){
			     	 
			     }else{
			     	WinAlerts (e);
			     }
		     };
    	 });
    	</script>
	</body>
</html>
