<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />



<section class="header">
    <nav class="navbar navbar-dark navbar-expand-lg nav-menu"><a class="navbar-brand logo mr-auto"
            href="${contextPath}/index"><img src="${contextPath}/images/logo-white@2x.png"></a><button class="navbar-toggler" type="button"
            data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
            aria-expanded="false" aria-label="Toggle navigation">
            <div id="menu-hamburger"><span></span><span></span><span></span><span></span><span></span><span></span>
            </div>
        </button>
        <div class="justify-content-lg-end collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav nav-menu">
                <li class="nav-item"><a class="nav-link nav-add addActive" href="${contextPath}/index">SysTalk.ai<span
                            class="sr-only">(current)</span></a>
                    <ul class="menu-sub">
                        <li>
                        	<a class="" href="${contextPath}/chat"><img class="img-responsive" src="${contextPath}/images/m-icon-chat.png" srcset="${contextPath}/images/m-icon-chat@2x.png 2x">
								<span>SysTalk.Chat</span>
                            </a>
                       </li>
                       
                       	<li>
							<a href="${contextPath}/rpa"><img class="img-responsive" src="${contextPath}/images/m-icon-rpa.png" srcset="${contextPath}/images/m-icon-rpa@2x.png 2x">
                				<span>SysTalk.RPA</span>
                			</a>
                		</li>
                    </ul>
                </li>

                <li class="nav-item"><a class="nav-link" href="${contextPath}/newsArea">最新消息</a></li>
                <li class="nav-item"><a class="nav-link" href="https://medium.com/systalk-ai" target="_blank"><img
                            class="nav-icon-blog" src="${contextPath}/images/icon-blog@2x.png">Blog</a></li>
                <li class="nav-item d-none"><a class="nav-link" href="mailto:service@systalk-digital.com">聯絡我們</a></li>
                <li class="nav-item"><a class="nav-link" href="#contact" id="m-contact">聯絡我們</a></li>
            </ul>
        </div>
    </nav>
</section>