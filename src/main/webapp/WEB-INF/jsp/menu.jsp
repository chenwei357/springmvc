<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
			<c:if test="${User == null}">
				<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/user/loginPage">登录</a>|
				</li>
				<li id="headerRegister" class="headerRegister" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/user/registerPage">注册</a>|
				</li>
			</c:if>
			<c:if test="${User != null}">
				<li id="headerUsername" class="headerUsername" style="display: list-item;">
					欢迎您,${User.username} |
				</li>
				<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="${ pageContext.request.contextPath }/order/myOrder?pageNo=1">我的订单</a>
					|
				</li>
				<li id="headerLogout" class="headerLogout" style="display: list-item;">
					<a href="${pageContext.request.contextPath}/user/logout">注销  |</a>
				</li>
			</c:if>
						<li>
							<a>会员中心</a>
							|
						</li>
						<li>
							<a>购物指南</a>
							|
						</li>
						<li>
							<a>关于我们</a>
							
						</li>
			</ul>
		</div>
		<div class="cart">
			<a  href="${pageContext.request.contextPath}/cart/myCart">购物车</a>
		</div>
			<div class="phone">
				客服热线:
				<strong>96008/53277764</strong>
			</div>
	</div>
	<div class="span24">
		<ul class="mainNav">
			<li><a href="${pageContext.request.contextPath}/index">首页</a></li>
			<c:forEach items="${clist}" var="category">
				<li>|<a href="${pageContext.request.contextPath}/product/list?cid=${category.cid}&pageNo=1">&nbsp;&nbsp;${category.cname}</a></li>
			</c:forEach>
		</ul>
	</div>