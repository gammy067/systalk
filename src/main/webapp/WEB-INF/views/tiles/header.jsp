<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<script type="text/javascript">

	function changeLocale(locale) {
		var form = $('#changeLanguageForm');
		$('#lang').val(locale);
		$('#currentUrl').val(window.location.pathname);
		var url = 
		form.submit();
	}

</script>


<section class="header">
    <nav class="navbar navbar-dark navbar-expand-lg nav-menu"><a class="navbar-brand logo mr-auto"
            href="${contextPath}/"><img src="${contextPath}/images/logo-white@2x.png"></a>
            <button class="navbar-toggler" type="button"
            data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
            aria-expanded="false" aria-label="Toggle navigation">
            <div id="menu-hamburger"><span></span><span></span><span></span><span></span><span></span><span></span>
            </div>
        </button>
        
        <div style="display;">
			<a href="#" onclick="changeLocale('zh_TW')"><s:message code="language.zh.tw"/></a>
			<a href="#" onclick="changeLocale('en_US')"><s:message code="language.en.us"/></a>
        </div>
                
        <div class="justify-content-lg-end collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav nav-menu">
                <li class="nav-item"><a class="nav-link nav-add addActive" href="${contextPath}/"><s:message code="header.menu.systalk.ai"/><span class="sr-only">(current)</span></a> <!-- SysTalk.ai -->
                    <ul class="menu-sub">
                        <li>
                        	<a class="" href="${contextPath}/chat"><img class="img-responsive" src="${contextPath}/images/m-icon-chat.png" srcset="${contextPath}/images/m-icon-chat@2x.png 2x">
								<span><s:message code="header.menu.systalk.chat"/></span> <!--SysTalk.Chat -->
                            </a>
                       </li>
                       
                       	<li>
							<a href="${contextPath}/rpa"><img class="img-responsive" src="${contextPath}/images/m-icon-rpa.png" srcset="${contextPath}/images/m-icon-rpa@2x.png 2x">
                				<span><s:message code="header.menu.systalk.rpa"/></span> <!-- SysTalk.RPA -->
                			</a>
                		</li>
                    </ul>
                </li>

                <li class="nav-item"><a class="nav-link" href="${contextPath}/newsArea"><s:message code="header.menu.news"/></a></li> <!-- 最新消息 -->
                <li class="nav-item"><a class="nav-link" href="https://medium.com/systalk-ai" target="_blank"><img
                            class="nav-icon-blog" src="${contextPath}/images/icon-blog@2x.png"><s:message code="header.menu.blog"/></a></li>  <!-- blog -->
                <li class="nav-item d-none"><a class="nav-link" href="mailto:service@systalk-digital.com"><s:message code="header.menu.contact"/></a></li> <!-- 聯絡我們 -->
                <li class="nav-item"><a class="nav-link" href="#contact" id="m-contact"><s:message code="header.menu.contact"/></a></li>  <!-- 聯絡我們 -->
            </ul>
        </div>
    </nav>
    
    <form id="changeLanguageForm" action="${contextPath}/changeLocale" method="post">
		<input name="lang" id="lang" type="hidden">
		<input name="currentUrl" id="currentUrl" type="hidden">
	</form>
</section>