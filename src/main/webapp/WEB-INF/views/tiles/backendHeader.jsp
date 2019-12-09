<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<script type="text/javascript">

$(function () {
    var url = window.location.pathname;
    urlRegExp = new RegExp(url.replace(/\/$/,'') + "$"); // create regexp to match current url pathname and remove trailing slash if present as it could collide with the link in navigation in case trailing slash wasn't present there
    // now grab every link from the navigation
    $('.about-menu').find('a').each(function(){
        // and test its normalized href against the url pathname regexp
        
        if(url.toLowerCase().indexOf(this.id) > 0){
            $(this).parent().addClass('active');
        }
        
        /** 選單成功案例設置#logowall active */
        if(url.toLowerCase().indexOf('successcase') > 0) {
        	$('#logowall').parent().addClass('active');
        }

        /**
        if(urlRegExp.test(this.id.replace(/\/$/,''))){
            $(this).parent().addClass('active');
        }
        */
    });

    
    // 首頁不顯示功能選單
    if (url.indexOf('index') > 0) {
    	$('.about-menu').css("display","none");
    }
});

	function logoutFormSubmit() {
		var form = $('#logoutForm');
		form.submit();	
	}

</script>

	<div class="container">
		<a href="${contextPath}/backend/index"><img class="justify-content-start mr-auto"
			src="${contextPath}/backendStatic/images/logo-white.png" srcset="${contextPath}/backendStatic/images/logo-white@2x.png 2x"
			alt=""></a>
			
		<div class="justify-content-end">
			<div class="about-menu">
				<ul>
					<li><a id="index" href="${contextPath}/backend/index">回首頁</a></li>
					<li><a id="banner" href="${contextPath}/backend/doGoToBannerHomeAction">首頁輪播畫面設定</a></li>
					<li><a id="video" href="${contextPath}/backend/doGoToEditVideoAction">行銷影片設定</a></li>
					<li><a id="logowall" href="${contextPath}/backend/doGoToSuccessCaseAction">成功案例設定</a></li>
					<li><a id="newsarea" href="${contextPath}/backend/doGoToEditNewsAreaAction">最新消息設定</a></li>
				</ul>
			</div>
			
			<a href="#" onclick="javascript:logoutFormSubmit()">登出</a>
		</div>
	</div>
	
	<form id="logoutForm" action='<spring:url value="/logout"/>' method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
