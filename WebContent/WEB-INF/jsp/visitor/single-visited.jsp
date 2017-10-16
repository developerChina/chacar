<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="${ctx}/css/visitor/common.css" />
		<link rel="stylesheet" href="${ctx}/css/visitor/single-visited.css" />
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
					<table width="800" height="280" border="1" cellpadding="0" cellspacing="0">
						<thead>
							<tr>
								<th>编号</th>
								<th>资源名称</th>
								<th>门禁</th>
								<th>楼层</th>
								<th>房间</th>
								<th>通道</th>
								<th>上级资源ID</th>
								<th>状态</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>8</td>
								<td class="clearfix">
									<span class="fl down"></span>
									<span class="fl member"></span>
									<span class="fl">权限管理</span>
								</td>
								<td></td>
								<td>1</td>
								<td></td>
								<td></td>
								<td></td>
								<td>正常</td>
							</tr>
							<tr>
								<td>9</td>
								<td class="clearfix">
									<input type="checkbox" class="fl check"/>
									<span class="fl single"></span>
									<span class="fl">用户管理</span>
								</td>
								<td></td>
								<td>1</td>
								<td></td>
								<td></td>
								<td></td>
								<td>正常</td>
							</tr>
							<tr>
								<td>10</td>
								<td>
									<input type="checkbox" class="fl check"/>
									<span class="fl member"></span>
									<span class="fl">角色管理</span>
								</td>
								<td></td>
								<td>1</td>
								<td></td>
								<td></td>
								<td></td>
								<td>正常</td>
							</tr>
							<tr>
								<td>11</td>
								<td>
									<input type="checkbox" class="fl check"/>
									<span class="fl source"></span>
									<span class="fl">资源管理</span>
								</td>
								<td></td>
								<td>1</td>
								<td></td>
								<td></td>
								<td></td>
								<td>正常</td>
							</tr>
							<tr>
								<td>12</td>
								<td>
									<span class="fl down"></span>
									<span class="fl setting"></span>
									<span class="fl">系统设置</span>
								</td>
								<td></td>
								<td>1</td>
								<td></td>
								<td></td>
								<td></td>
								<td>正常</td>
							</tr>
							<tr>
								<td>13</td>
								<td>
									<input type="checkbox" class="fl check"/>
									<span class="fl setting"></span>
									<span class="fl">系统参数</span>
								</td>
								<td></td>
								<td>1</td>
								<td></td>
								<td></td>
								<td></td>
								<td>正常</td>
							</tr>
							<tr>
								<td>14</td>
								<td>
									<input type="checkbox" class="fl check"/>
									<span class="fl setting"></span>
									<span class="fl">操作日志</span>
								</td>
								<td></td>
								<td>1</td>
								<td></td>
								<td></td>
								<td></td>
								<td>正常</td>
							</tr>
							<tr>
								<td>15</td>
								<td>
									<span class="fl down"></span>
									<span class="fl process"></span>
									<span class="fl">测试资源</span>
								</td>
								<td></td>
								<td>1</td>
								<td></td>
								<td></td>
								<td></td>
								<td>正常</td>
							</tr>
							<tr>
								<td>16</td>
								<td>
									<span class="fl down"></span>
									<span class="fl process"></span>
									<span class="fl">子资源</span>
								</td>
								<td></td>
								<td>1</td>
								<td></td>
								<td></td>
								<td></td>
								<td>正常</td>
							</tr>
							<tr>
								<td>17</td>
								<td>
									<input type="checkbox" class="fl check"/>
									<span class="fl rigth"></span>
									<span class="fl">子子资源1</span>
								</td>
								<td></td>
								<td>1</td>
								<td></td>
								<td></td>
								<td></td>
								<td>正常</td>
							</tr>
							<tr>
								<td>18</td>
								<td>
									<input type="checkbox" class="fl check"/>
									<span class="fl rigth"></span>
									<span class="fl">子子资源2</span>
								</td>
								<td></td>
								<td>1</td>
								<td></td>
								<td></td>
								<td></td>
								<td>正常</td>
							</tr>
							<tr>
								<td>19</td>
								<td>
									<span class="fl process processChild"></span>
									<span class="fl">子资源1</span>
								</td>
								<td></td>
								<td>1</td>
								<td></td>
								<td></td>
								<td></td>
								<td>正常</td>
							</tr>
							<tr>
								<td>20</td>
								<td>
									<span class="fl process processChild"></span>
									<span class="fl">子资源2</span>
								</td>
								<td></td>
								<td>1</td>
								<td></td>
								<td></td>
								<td></td>
								<td>正常</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="btnArea clearfix">
					<a href="${ctx}/visitor/forwardSingleVisitor" class="fl prevpage"></a>
					<a href="#" class="fl notice"></a>
				</div>
			</div>
			<div>
				<a href="${ctx}/vindex.jsp" class="foot">返回</a>
			</div>
		</div>
	</body>
</html>
