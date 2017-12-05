<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- HTML5文件 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>短信确认</title>
<link rel="stylesheet" href="${ctx}/layout/agile/css/agile.layout.css">
<link rel="stylesheet" href="${ctx}/layout/third/flat/flat.component.css">
<link rel="stylesheet" href="${ctx}/layout/third/flat/flat.color.css">
<link rel="stylesheet" href="${ctx}/layout/third/flat/iconline.css">
<link rel="stylesheet" href="${ctx}/layout/third/flat/iconform.css">
<script src="${ctx}/scripts/boot.js" type="text/javascript"></script>
</head>
<body>
	<div id="section_container">
		<section id="form_section" data-role="section" class="active">
			<header>
				<div class="titlebar">
					<h1>访客确认</h1>
				</div>
			</header>
			<article data-role="article" id="border_article" class="active" data-scroll="verticle" style="top: 40px; bottom: 0px;">
				<div class="scroller">
					<form autocomplete="off" class="form-group form-square" id="autidForm" action="${ctx}/visitor/auditRecord" method="post">
						<input type="hidden" id="recordid" name="recordid" value="${recordid }">
						<label class="label-left" style="height: 40px;">是否同意</label> 
						<label class="label-right" style="height: 40px;">
							<a href="#" data-role="radio">
							     <input type="radio" name="isAudit" value="1" checked/>
								 <label for="baskball1" class="black">是</label>
							</a> 
							<a href="#" data-role="radio">
							    <input type="radio" name="isAudit" value="2"/>
								<label for="football1" class="black">否</label>
							</a>
						</label> 
						<label class="label-left" for="user" style="height: 80px;">确认原因</label>
						 <label class="label-right" style="height: 80px;">
						    <input id="auditContent" name="auditContent" value="${auditContent}" type="text" />
						</label>
						<label class="label-left" for="user" id="pw_left_lable">访客通道</label>
						 <label class="label-right" id="pw_right_lable">
						   <c:forEach items="${pws}" var="pw" varStatus="stat">
							<a href="#" data-role="checkbox">
						    	<input type="hidden" name="pw" value="${pw.passagewayID}"/>
					    		<input type="checkbox" checked disabled/>
					  			<label for="baskball" class="black">${pw.passagewayName}</label>
					  		</a><br/>
						   </c:forEach>
						</label>
						
						<label class="label-left" for="user" id="elt_left_lable">访客电梯</label>
						 <label class="label-right" id="elt_right_lable">
						   <c:forEach items="${elts}" var="elt" varStatus="stat">
							<a href="#" data-role="checkbox">
						    	<input type="hidden" name="elt" value="${elt.elevatorID }"/>
					    		<input type="checkbox" checked disabled/>
					  			<label for="baskball" class="black">${elt.elevatorName }</label>
					  		</a><br/>
						   </c:forEach>
						</label>
						
						<label class="label-left" for="user" style="height: 60px;">访客门禁</label>
						 <label class="label-right" style="height: 60px;">
							 <div data-role="select" class="card">
									<select name="acce" placeholder="选择门禁">
									  	<c:forEach items="${acces}" var="acce" varStatus="stat">
									  		<option value="${acce.accessid }">${acce.accessname }</option>	
									   </c:forEach>
									</select>
							  	</div>
						 </label>
						 <c:if test="${isShow=='yes'}">
						 	<button class="block" onclick="auditRecord()">确认</button>
						 </c:if>
						 
						 
						  <br/><br/><br/><br/><br/><br/><br/>
						 
					</form>
				</div>
			</article>
		</section>
		<script type="text/javascript">
			function auditRecord(){
				$('#autidForm').submit();
			}
			
			console.info("${pws}");
			console.info("${elts}");
			console.info("${acces}");
			
			console.info("${fn:length(pws)}");
			
			$(function(){
				$("#pw_left_lable").height(40*parseInt("${fn:length(pws)}"));
				$("#pw_right_lable").height(40*parseInt("${fn:length(pws)}"));
				
				$("#elt_left_lable").height(40*parseInt("${fn:length(elts)}"));
				$("#elt_right_lable").height(40*parseInt("${fn:length(elts)}"));
				
		    })
		    
		</script>
	</div>
</body>
</html>