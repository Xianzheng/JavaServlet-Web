﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>商品列表</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
				width: 100%;
			}
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
		</style>
	</head>

	<body>
		
			<%@include file="/jsp/header.jsp" %>
		<c:if test="${empty page.records}">
			<div class="col-md-12">
				
					<h1>There is no any product</h1>
				
			</div>
		</c:if>
		
		 <c:if test="${not empty page.records}">
			<div class="row" style="width:1210px;margin:0 auto;">
			<div class="col-md-12">
				<ol class="breadcrumb">
					<li><a href="#">首页</a></li>
				</ol>
			</div>
			<c:forEach items="${page.records}" var="p">
			<div class="col-md-2">
				<a href="${pageContext.request.contextPath}/ProductServlet?method=getProductInfoById&pid=${p.pid}">
					<img src="${pageContext.request.contextPath}/${p.pimage}" width="170" height="170" style="display: inline-block;">
				</a>
				<p><a href="${pageContext.request.contextPath}/ProductServlet?method=getProductInfoById&pid=${p.pid}" style='color:green'>${p.pname}</a></p>
				<p><font color="#FF0000">商城价：&yen;${p.shop_price}</font></p>
			</div>
			</c:forEach>
			<%@include file="/jsp/pageFile.jsp" %>

		</div>
		</c:if>
 
		

		<!--分页 -->
		
		<%@include file="/jsp/pageFile.jsp" %>
		
		<%@include file="/jsp/footer.jsp" %>
	</body>

</html>