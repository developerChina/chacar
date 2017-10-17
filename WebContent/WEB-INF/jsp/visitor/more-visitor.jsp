<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="${ctx}/css/visitor/common.css" />
		<link rel="stylesheet" href="${ctx}/css/visitor/more-visitor.css" />
	</head>
	<body>
		<div class="wrap">
			<div class="head">
				<h2>多访客登记</h2>
			</div>
			<div class="content clearfix">
				<div class="top">
					<div class="clearfix title">
						<span class="fl"></span>
						<span class="fl">访客信息</span>
					</div>
					<div class="ensure clearfix">
						<div class="fl">
							<img src="${ctx}/images/shuacard.png" alt="" />
						</div>
						<div class="fl"></div>
						<div class="fl">
							<img src="${ctx}/images/photoCollect.png" alt="" />
						</div>
					</div>
				</div>
				<div class="bottom clearfix">
					<div class="fl left">
						<table width="450" border="1" cellpadding="0" cellspacing="0">
							<caption>Load Data</caption>
							<thead>
								<tr>
									<th></th>
									<th>姓名</th>
									<th>性别</th>
									<th>民族</th>
									<th>身份证号码</th>
								</tr>
							</thead>

							<tbody>
								<tr>
									<th>11</th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
								</tr>
								<tr>
									<th>12</th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
								</tr>
								<tr>
									<th>13</th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
								</tr>
								<tr>
									<th>14</th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
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
							
							<!--<tfoot>
								<tr>
									<td>
										<select name="">
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
										</select>
									</td>
								</tr>
							</tfoot>-->

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
						<div class="fl">
							<img src="${ctx}/images/photoOne.png" alt="" />
							<p>证件照片</p>
						</div>
						<div class="fl">
							<img src="${ctx}/images/photoTwo.png" alt="" />
							<p>拍照照片</p>
						</div>
						<a href="${ctx}/visitor/forwardMoreVisited" ><div class="page"></div></a>
					</div>
				</div>
			</div>
			<div>
				<a href="${ctx}/vindex.jsp" class="foot">返回</a>
			</div>
		</div>
	</body>
</html>
