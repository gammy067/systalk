<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script>
	
	/**
	<c:choose>
		<c:when test="${msg ne null}">
			alert('${msg}');
			<c:remove var="msg" scope="session" />
		</c:when>
	</c:choose>
	*/

	</script>

</head>
	<body class="index-bg menu-bg">
	
	<!--main 中間內容 Start-->
	<main>
	<div class="d-table-cell mx-auto text-center align-middle login-content">
		<div class="menu-box row mx-auto">
			<div class="w-100">
				<div class="title">
					<div>
						<h2 class="lg-title">後台維護設定</h2>
					</div>
				</div>
			</div>
			<ul>
				<li><a href="${contextPath}/backend/doGoToBannerHomeAction">
						<div class="menu-icon">
							<img class="justify-content-start mr-auto"
								src="${contextPath}/backendStatic/images/menu-icon01.png"
								srcset="${contextPath}/backendStatic/images/menu-icon01@2x.png 2x" alt="">
						</div> <span class="menu-tit">首頁輪播畫面設定</span>
				</a></li>
				<li><a href="${contextPath}/backend/doGoToEditVideoAction">
						<div class="menu-icon">
							<img class="justify-content-start mr-auto"
								src="${contextPath}/backendStatic/images/menu-icon02.png"
								srcset="${contextPath}/backendStatic/images/menu-icon02@2x.png 2x" alt="">
						</div> <span class="menu-tit">行銷影片設定</span>
				</a></li>
				<li><a href="${contextPath}/backend/doGoToSuccessCaseAction">
						<div class="menu-icon">
							<img class="justify-content-start mr-auto"
								src="${contextPath}/backendStatic/images/menu-icon03.png"
								srcset="${contextPath}/backendStatic/images/menu-icon03@2x.png 2x" alt="">
						</div> <span class="menu-tit">成功案例設定</span>
				</a></li>
				<li><a href="${contextPath}/backend/doGoToEditNewsAreaAction">
						<div class="menu-icon">
							<img class="justify-content-start mr-auto"
								src="${contextPath}/backendStatic/images/menu-icon04.png"
								srcset="${contextPath}/backendStatic/images/menu-icon04@2x.png 2x" alt="">
						</div> <span class="menu-tit">最新消息設定</span>
				</a></li>
			</ul>
		</div>
	</div>
	</main>
</body>

</html>