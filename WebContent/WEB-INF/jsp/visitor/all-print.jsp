<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="${ctx}/css/visitor/common.css" />
		<link rel="stylesheet" href="${ctx}/css/visitor/print.css" />
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
						<table width="450" border="1" cellpadding="0" cellspacing="0">
							<caption>Load Data</caption>
							<thead>
								<tr>
									<th></th>
									<th>序号</th>
									<th>姓名</th>
									<th>办公地点</th>
									<th>确认状态</th>
								</tr>
							</thead>

							<tbody>
								<tr>
									<th>11</th>
									<th></th>
									<th></th>
									<th></th>
									<th>同意</th>
								</tr>
								<tr>
									<th>12</th>
									<th></th>
									<th></th>
									<th></th>
									<th>上午不在公司</th>
								</tr>
								<tr>
									<th>13</th>
									<th></th>
									<th></th>
									<th></th>
									<th>请找前台帮忙</th>
								</tr>
								<tr>
									<th>14</th>
									<th></th>
									<th></th>
									<th></th>
									<th>等待确认</th>
								</tr>
								<tr>
									<th>15</th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
								</tr>
								<tr>
									<th>16</th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
								</tr>
								<tr>
									<th>17</th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
								</tr>
								<tr>
									<th>18</th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
								</tr>
								<tr>
									<th>19</th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
								</tr>
								<tr>
									<th>20</th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
								</tr>
							</tbody>
							

						</table>
						<div class="tfoot clearfix">
							<select name="" class="fl">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select>
							<span class="fl shu"></span>
							<span class="fl prev"></span>
							<span class="fl leftIcon"></span>
							<span class="fl shu"></span>
							<span class="fl">Pages</span>
							<input type="text" class="fl num" placeholder="2"/>
							<span class="fl">of&nbsp;</span>
							<span class="fl">3</span>
							<span class="fl shu"></span>
							<span class="fl rightIcon"></span>
							<span class="fl next"></span>
							<span class="fl shu"></span>
							<span class="fl refresh"></span>
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
</html>
