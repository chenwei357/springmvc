<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title>我的订单页面</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="${pageContext.request.contextPath}/index">
				<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.gif" alt="网上商城"/>
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
	<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
</div>	
</div>

	<%@include file="menu.jsp" %>
	
</div>	

<div class="container cart">

		<div class="span24">
		
			<div class="step step1">
				<ul>
					
					<li  class="current"></li>
					<li  >我的订单的显示</li>
				</ul>
			</div>
	
		
				<table>
					<tbody>
					<c:forEach items="${list.list}" var="orders">
					<tr>
						<th colspan="5">订单编号:${orders.oid}&nbsp;&nbsp;&nbsp;&nbsp;
							订单状态:
							<c:if test="${orders.state == 1}">
								<a href="${pageContext.request.contextPath}/order/findOrder?oid=${orders.oid}"><font color="red">请付款</font></a>
							</c:if>
							<c:if test="${orders.state == 2}">
								已付款
							</c:if>
							<c:if test="${orders.state == 3}">
								<a href=""><font color="red">确认收货</font></a>
							</c:if>
							<c:if test="${orders.state == 4}">
								交易完成
							</c:if>
						</th>
					</tr>
					
					<tr>
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
					</tr>
					<c:forEach items="${orders.orderItems}" var="orderItems">
						<tr>
							<td width="60">
								<input type="hidden" name="id" value="22"/>
								<img src="${pageContext.request.contextPath}/${orderItems.product.image}"/>
							</td>
							<td>
								<a target="_blank">${orderItems.product.pname}</a>
							</td>
							<td>
								${orderItems.product.shopPrice}
							</td>
							<td class="quantity" width="60">
								${orderItems.count}
							</td>
							<td width="140">
								<span class="subtotal">￥${orderItems.subtotal}</span>
							</td>
							
						</tr>
						</c:forEach>
					</c:forEach>
					<tr>
						<th colspan="5">
							<div class="pagination">
									<span>第${list.pageNo}/${list.totalPage}页</span>
										<c:if test="${list.pageNo != 1}">
											<a
												href="${pageContext.request.contextPath}/order/myOrder?cid=${id}&pageNo=1"
												class="firstPage">&nbsp;</a>
											<a
												href="${pageContext.request.contextPath}/order/myOrder?cid=${id}&pageNo=${list.pageNo-1}"
												class="previousPage">&nbsp;</a>
										</c:if>
					
										<c:forEach var="i" begin="1" end="${list.totalPage}">
											<c:if test="${list.pageNo != i}">
												<a
													href="${pageContext.request.contextPath}/order/myOrder?cid=${id}&pageNo=${i}">${i}</a>
											</c:if>
											<c:if test="${list.pageNo == i}">
												<span class="currentPage">${i}</span>
											</c:if>
										</c:forEach>
					
										<c:if test="${list.pageNo != list.totalPage}">
											<a class="nextPage"
												href="${pageContext.request.contextPath}/order/myOrder?cid=${id}&pageNo=${list.pageNo+1}">&nbsp;</a>
											<a class="lastPage"
												href="${pageContext.request.contextPath}/order/myOrder?cid=${id}&pageNo=${list.totalPage}">&nbsp;</a>
										</c:if>
								</div>
						</th>
					</tr>
				</tbody>
			</table>
		</div>
		
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="${pageContext.request.contextPath}/image\r___________renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势" height="52" width="950" />
</div>
</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a href="#">关于我们</a>
						|
					</li>
					<li>
						<a href="#">联系我们</a>
						|
					</li>
					<li>
						<a href="#">诚聘英才</a>
						|
					</li>
					<li>
						<a href="#">法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a >SHOP++官网</a>
						|
					</li>
					<li>
						<a>SHOP++论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
	</div>
</div>
</body>
</html>