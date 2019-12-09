<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html>
    <!-- google recaptcha api -->
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    
    <!-- Animate.css v3.5.2 -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css" rel="stylesheet" />
    
	<!-- 本地端網頁打不開請註解掉這行，檔案上傳後要把這行註解打開 
    <script src="js/_force_https.js"></script>
    -->
<script type="text/javascript">
$(function () {
	//關閉視窗前呼叫
	$("#doneModal").on("hide.bs.modal",function(e){
		window.location.reload();
	});
});


function doSendRpaEmailAction() {
	$('#sendRpaEmailForm input').next().remove();
	$('#sendRpaEmailForm textarea').next().remove();

	$('#captchaResponse').val(grecaptcha.getResponse()) ;

	$.ajax({
			url: '${contextPath}/rpa/doSendRpaEmailAction',
			type: 'POST',
			data:  $('#sendRpaEmailForm').serialize(),
			success: function (data) {
				if(data.hasException) {
					alert("發信系統異常請稍後再試");
					window.location.reload();
				} else {
					if(data.validate){
						$('#doneModal').modal('toggle');
						$('#advisoryModal').modal('toggle');
						//$("#sendRpaEmailForm").trigger('reset');
						//grecaptcha.reset();
						
					// 欄位驗證錯誤
					} else {
						grecaptcha.reset();
						//Set error messages
						$.each(data.errorMessages,function(key,value){
							$('#sendRpaEmailForm input[name='+key+']').after('<span class="text-red">'+value+'</span>');
							$('#sendRpaEmailForm textarea[name='+key+']').after('<span class="text-red">'+value+'</span>');
						});
					}
				}
			}
	});
}
</script>
        <!-- banner -->
        <section class="product-bg rpa-bg">
            <div class="container">
                <div class="row d-flex">
                    <div class="col-lg-6 col-md-12 col-sm-12">
                        <div class="product-banner-tit">
                            <img src="${contextPath}/images/rap-logo.png" srcset="${contextPath}/images/rap-logo@2x.png 2x" alt="Systalk.RPA Logo" />
                            <div class="product-rpa-title"><s:message code="rpa.banner.tit"/><br>Robotic Process Automation	<!-- 多語系 -->
                         	</div>
                            <div class="product-rpa-DESC"><s:message code="rpa.banner.desc"/></div>	<!-- 多語系 -->
                            <div>
                                <div class="banner-button" data-toggle="modal" data-target="#advisoryModal">
									<s:message code="rpa.button"/>	<!-- 多語系 -->
                                    <img src="${contextPath}/images/rpa-banner-arrow.png" srcset="${contextPath}/images/rpa-banner-arrow@2x.png 2x" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-12 col-sm-12">
                        <div class="product-banner-img">
                            <img src="${contextPath}/images/rap-banner.png" srcset="${contextPath}/images/rap-banner@2x.png 2x" class="img-rpa"
                                alt="Systalk.RPA Logo" />
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- banner end -->
        <!-- menu -->
        <section class="tsmp-menu">
            <div class="">
                <nav class="menubar">
                    <a href="SYS-rpa-1" class="active">
                        <img src="${contextPath}/images/rpa-menu-Intro.png" srcset="${contextPath}/images/rpa-menu-Intro@2x.png 2x"
                            class="img-fluid" />
                        <span><s:message code="rpa.menubar01"/></span></a>	<!-- 多語系 -->
                    <!-- <a href="SYS-rpa-2"><img src="${contextPath}/images/rpa-menu-benefit.png"
                            srcset="${contextPath}/images/rpa-menu-benefit@2x.png 2x" class="img-fluid" />
                        <span>產品效益</span></a> -->
                    <a href="SYS-rpa-2"><img src="${contextPath}/images/rpa-menu-service.png"
                            srcset="${contextPath}/images/rpa-menu-service@2x.png 2x" class="img-fluid" />
                        <span><s:message code="rpa.menubar02"/></span></a>	<!-- 多語系 -->
                    <!-- <a href="SYS-rpa-4"><img src="${contextPath}/images/rpa-menu-appli.png" srcset="${contextPath}/images/rpa-menu-appli@2x.png 2x"
                            class="img-fluid" /><span>產業應用</span></a> -->
                    <a href="SYS-rpa-3">
                        <img src="${contextPath}/images/rpa-menu-success.png" srcset="${contextPath}/images/rpa-menu-success@2x.png 2x"
                            class="img-fluid" /><span><s:message code="rpa.menubar03"/></span></a>	<!-- 多語系 -->
                    <a href="" data-toggle="modal" data-target="#advisoryModal">
                        <img src="${contextPath}/images/rpa-menu-advisory.png" srcset="${contextPath}/images/rpa-menu-advisory@2x.png 2x"
                            class="img-fluid" /><span><s:message code="rpa.menubar04"/></span></a>	<!-- 多語系 -->
                </nav>
            </div>
        </section>
        <!-- /end menu -->

        <!-- SYS-rpa-1 -->
        <a id="SYS-rpa-1"></a>
        <section class="SYS-section-02 rpa-robot">
            <div class="SYS-module-box">
                <div class="title mb-3">
                    <div>
                        <h1><s:message code="rpa.section02.title"/></h1>	<!-- 多語系 -->
                    </div>
                </div>
                <div class="sub-title">
                    <div>
                        <h4><s:message code="rpa.section02.sub.title"/></h4>	<!-- 多語系 -->
                    </div>
                </div>
                <div class="container mt-5">
                    <div class="row d-flex">
                        <div class="col-lg-6 col-md-6 col-sm-12 text-center">
                            <img src="${contextPath}/images/rpa-intro.png" srcset="${contextPath}/images/rpa-intro@2x.png 2x" class="img-fluid" alt="RPA 人機協作" />
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-12 align-self-center rpa-automatic">
                            <p><s:message code="rpa.section02.desc"/></p>	<!-- 多語系 -->
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- SYS-rpa-1 end -->

        <!-- SYS-rpa-1 -->
        <section class="SYS-section-03 rpa-intro">
            <div class="product-nlu-top"><img class="img-responsive d-none d-md-block" src="${contextPath}/images/rpa-intro-topbg.png"
                    srcset="${contextPath}/images/rpa-intro-topbg@2x.png 2x" />
            </div>
            <div class="product-nlu-top"><img class="img-responsive d-md-none" src="${contextPath}/images/rpa-intro-topbgmob.png"
                    srcset="${contextPath}/images/rpa-intro-topbgmob@2x.png 2x" />
            </div>
            <div class="rpa-intro-box">
                <div class="text-center mb-3">
                    <div>
                        <h2 class="">SysTalk.RPA</h2>
                    </div>
                </div>
                <div class="sub-title">
                    <div>
                        <h4><s:message code="rpa.section03.sub.title"/></h4>	<!-- 多語系 -->
                    </div>
                </div>
                <div class="container">
                    <div class="product-nlu-content nlu-arrow">
                        <ul class="nav nav-pills justify-content-center" id="pills-tab" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#pills-home"
                                    role="tab" aria-controls="pills-home" aria-selected="true"><s:message code="rpa.section03.navitem01"/></a>	<!-- 多語系 -->
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#pills-profile"
                                    role="tab" aria-controls="pills-profile" aria-selected="false"><s:message code="rpa.section03.navitem02"/></a>	<!-- 多語系 -->
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="pills-contact-tab" data-toggle="pill" href="#pills-contact"
                                    role="tab" aria-controls="pills-contact" aria-selected="false"><s:message code="rpa.section03.navitem03"/></a>	<!-- 多語系 -->
                            </li>
                        </ul>
                        <div class="tab-content" id="pills-tabContent">
                            <div class="tab-pane fade show active" id="pills-home" role="tabpanel"
                                aria-labelledby="pills-home-tab">
                                <div class="d-flex">
                                    <div class="intro-box">
                                        <div class="intro-article">
                                            <div class="intro-icon align-self-center"><img class=""
                                                    src="${contextPath}/images/rpa-intro-process.png"
                                                    srcset="${contextPath}/images/rpa-intro-process@2x.png 2x" />
                                            </div>
                                            <ul class="align-self-center intro-content">
                                                <li><s:message code="rpa.section03.intro.article01.desc01"/></li>	<!-- 多語系 -->
                                                <li><s:message code="rpa.section03.intro.article01.desc02"/></li>	<!-- 多語系 -->
                                                <li><s:message code="rpa.section03.intro.article01.desc03"/></li>	<!-- 多語系 -->
                                       			<li><s:message code="rpa.section03.intro.article01.desc04"/></li>	<!-- 多語系 -->
                                            </ul>
                                        </div>
                                        <div class="align-self-center"><img class="img-fluid"
                                                src="${contextPath}/images/rpa-intro-process-img.jpg"
                                                srcset="${contextPath}/images/rpa-intro-process-img@2x.jpg 2x" alt="編輯自動化流程" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="pills-profile" role="tabpanel"
                                aria-labelledby="pills-profile-tab">
                                <div class="d-flex">
                                    <div class="intro-box">
                                        <div class="intro-article">
                                            <div class="intro-icon align-self-center"><img class=""
                                                    src="${contextPath}/images/rpa-intro-manag-icon.png"
                                                    srcset="${contextPath}/images/rpa-intro-manag-icon@2x.png 2x" />
                                            </div>
                                            <ul class="align-self-center intro-content">
												<li><s:message code="rpa.section03.intro.article02.desc01"/></li>	<!-- 多語系 -->
                                                <li><s:message code="rpa.section03.intro.article02.desc02"/></li>	<!-- 多語系 -->
                                                <li><s:message code="rpa.section03.intro.article02.desc03"/></li>	<!-- 多語系 -->
												<li><s:message code="rpa.section03.intro.article02.desc04"/></li>	<!-- 多語系 -->
                                            </ul>
                                        </div>
                                        <div class="align-self-center"><img class="img-fluid"
                                                src="${contextPath}/images/rpa-intro-manag-img.jpg"
                                                srcset="${contextPath}/images/rpa-intro-manag-img@2x.jpg 2x" alt="管理中心派送" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="pills-contact" role="tabpanel"
                                aria-labelledby="pills-contact-tab">
                                <div class="d-flex">
                                    <div class="intro-box">
                                        <div class="intro-article">
                                            <div class="intro-icon align-self-center carried-icon"><img class=""
                                                    src="${contextPath}/images/rpa-intro-carried-icon.png"
                                                    srcset="${contextPath}/images/rpa-intro-carried-icon@2x.png 2x" />
                                            </div>
                                            <ul class="align-self-center intro-content">
                                                <li><s:message code="rpa.section03.intro.article03.desc01"/></li>	<!-- 多語系 -->
                                                <li><s:message code="rpa.section03.intro.article03.desc02"/></li>	<!-- 多語系 -->
                                                <li><s:message code="rpa.section03.intro.article03.desc03"/></li>	<!-- 多語系 -->
                                                <li><s:message code="rpa.section03.intro.article03.desc04"/></li>	<!-- 多語系 -->
                                            </ul>
                                        </div>
                                        <div class="align-self-center"><img class="img-fluid"
                                                src="${contextPath}/images/rpa-intro-carried-img.jpg"
                                                srcset="${contextPath}/images/rpa-intro-carried-img@2x.jpg 2x" alt="流程機器人自動執行" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="product-nlu-top"><img class="img-responsive d-none d-md-block"
                    src="${contextPath}/images/rpa-intro-underbg.png" srcset="${contextPath}/images/rpa-intro-underbg@2x.png 2x" />
            </div>
            <div class="product-nlu-top"><img class="img-responsive d-md-none" src="${contextPath}/images/rpa-intro-underbgmob.png"
                    srcset="${contextPath}/images/rpa-intro-underbgmob@2x.png 2x" />
            </div>

        </section>
        <!-- SYS-rpa-1 end -->
        <!-- SYS-rpa-2 -->
        <!-- <a id="SYS-rpa-2"></a>
        <section class="SYS-section-02 rpa-benefit">
            <div class="SYS-module-box">
                <div class="title mb-5">
                    <div>
                        <h1>導入六大效益</h1>
                    </div>
                </div>
                <div class="container mt-5">
                    <div class="row d-flex">
                        <div class="col-lg-4 col-md-6 col-sm-12 text-center">
                            <div class="rpa-benefit-box">
                                <div class="rpa-benefit-tit align-self-center">
                                    <h4>跨系統運行不掉拍</h4>
                                    <p>資料絕對抓取<br>使命必達執行任務</p>
                                </div>
                                <img src="${contextPath}/images/rpa-benefit-img01.jpg" srcset="${contextPath}/images/rpa-benefit-img01@2x.jpg 2x"
                                    class="img-fluid" />
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6 col-sm-12 text-center">
                            <div class="rpa-benefit-box">
                                <div class="rpa-benefit-tit align-self-center">
                                    <h4>自訂化導入範疇</h4>
                                    <p>小案到大案漸進式導入<br>逐步改善業務流程</p>
                                </div>
                                <img src="${contextPath}/images/rpa-benefit-img02.jpg" srcset="${contextPath}/images/rpa-benefit-img02@2x.jpg 2x"
                                    class="img-fluid" />
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6 col-sm-12 text-center">
                            <div class="rpa-benefit-box">
                                <div class="rpa-benefit-tit align-self-center">
                                    <h4>7x24 工作不間斷</h4>
                                    <p>自由排程工作時段<br>無須受時間限制</p>
                                </div>
                                <img src="${contextPath}/images/rpa-benefit-img03.jpg" srcset="${contextPath}/images/rpa-benefit-img03@2x.jpg 2x"
                                    class="img-fluid" />
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6 col-sm-12 text-center">
                            <div class="rpa-benefit-box">
                                <div class="rpa-benefit-tit align-self-center">
                                    <h4>執行快狠準</h4>
                                    <p>邏輯化擬人動作<br>精準行動不贅行</p>
                                </div>
                                <img src="${contextPath}/images/rpa-benefit-img04.jpg" srcset="${contextPath}/images/rpa-benefit-img04@2x.jpg 2x"
                                    class="img-fluid" />
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6 col-sm-12 text-center">
                            <div class="rpa-benefit-box">
                                <div class="rpa-benefit-tit align-self-center">
                                    <h4>減少錯誤率</h4>
                                    <p>失敗記錄集中管理<br>遠端排程再執行</p>
                                </div>
                                <img src="${contextPath}/images/rpa-benefit-img05.jpg" srcset="${contextPath}/images/rpa-benefit-img05@2x.jpg 2x"
                                    class="img-fluid" />
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-6 col-sm-12 text-center">
                            <div class="rpa-benefit-box">
                                <div class="rpa-benefit-tit align-self-center">
                                    <h4>提高員工產值</h4>
                                    <p>釋放制式化行為<br>提升員工再學習動力</p>
                                </div>
                                <img src="${contextPath}/images/rpa-benefit-img06.jpg" srcset="${contextPath}/images/rpa-benefit-img06@2x.jpg 2x"
                                    class="img-fluid" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section> -->
        <!-- SYS-rpa-2 end -->
        <!-- 服務內容 -->
        <a id="SYS-rpa-2"></a>
        <section class="SYS-section-02 rpa-service">
            <div class="SYS-module-box">
                <div class="title mb-5">
                    <div>
                        <h3><s:message code="rpa.section02.rpa.service.title"/></h3> <!-- 多語系  -->
                    </div>
                </div>
                <div class="container mt-5">
                    <div class="row d-flex">
                        <div class="col-lg-4 col-md-4 col-sm-12 text-center">

                            <img src="${contextPath}/images/rpa-service-img01.jpg" srcset="${contextPath}/images/rpa-service-img01@2x.jpg 2x"
                                class="img-fluid" />
                            <div class="">
                                <h4><s:message code="rpa.section02.rpa.service.item01.title"/></h4>	<!-- 多語系  -->
                                <p><s:message code="rpa.section02.rpa.service.item01.desc01"/></p>	<!-- 多語系  -->
                            </div>
                            <div class="rpa-service-label"><s:message code="rpa.section02.rpa.service.item01.desc02"/></div>	<!-- 多語系  -->
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-12 text-center">

                            <img src="${contextPath}/images/rpa-service-img02.jpg" srcset="${contextPath}/images/rpa-service-img02@2x.jpg 2x"
                                class="img-fluid" />
                            <div class="">
                                <h4><s:message code="rpa.section02.rpa.service.item02.title"/></h4>	<!-- 多語系  -->
                                <p><s:message code="rpa.section02.rpa.service.item02.desc01"/></p>	<!-- 多語系  -->
                            </div>
                            <div class="rpa-service-label label-two"><s:message code="rpa.section02.rpa.service.item02.desc02"/></div>	<!-- 多語系  -->
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-12 text-center">

                            <img src="${contextPath}/images/rpa-service-img03.jpg" srcset="${contextPath}/images/rpa-service-img03@2x.jpg 2x"
                                class="img-fluid" />
                            <div class="">
                                <h4><s:message code="rpa.section02.rpa.service.item03.title"/></h4>	<!-- 多語系  -->
                                <p><s:message code="rpa.section02.rpa.service.item03.desc01"/></p>	<!-- 多語系  -->
                            </div>
                            <div class="rpa-service-label label-three"><s:message code="rpa.section02.rpa.service.item03.desc02"/></div>	<!-- 多語系  -->
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- 服務內容 end -->
        <!-- <a id="SYS-rpa-4"></a>
        <section class="solution rpa-appli">
            <div class="solution-tit">
                <div class="mb-10">
                    <div>
                        <h2>產業應用</h2>
                    </div>
                </div>
                <div class="sub-title">
                    <div>
                        <h4>Industry Solution</h4>
                    </div>
                </div>
            </div>
            <div class="container">
                <div id="carousel" class="owl-carousel">
                    <div class="item">
                        <div class="card">
                            <div class="headline">金融</div>
                            <ul>
                                <li>開戶審核</li>
                                <li>信用評級</li>
                            </ul>
                            <div class="img">
                                <img class="img-responsive" src="${contextPath}/images/rpa-appli01.jpg"
                                    srcset="${contextPath}/images/rpa-appli01@2x.jpg 2x" />
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="card">
                            <div class="headline">製造</div>
                            <ul>
                                <li>庫存管理</li>
                                <li>出退貨單據處理</li>
                            </ul>
                            <div class="img">
                                <img class="img-responsive" src="${contextPath}/images/rpa-appli02.jpg"
                                    srcset="${contextPath}/images/rpa-appli02@2x.jpg 2x" />
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="card">
                            <div class="headline">醫療</div>
                            <ul>
                                <li>藥品價格監控</li>
                                <li>批價對帳</li>
                            </ul>
                            <div class="img">
                                <img class="img-responsive" src="${contextPath}/images/rpa-appli03.jpg"
                                    srcset="${contextPath}/images/rpa-appli03@2x.jpg 2x" />
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="card">
                            <div class="headline">企業客服</div>
                            <ul>
                                <li>電子郵件分派</li>
                                <li>加快業務處理</li>
                            </ul>
                            <div class="img">
                                <img class="img-responsive" src="${contextPath}/images/rpa-appli.jpg"
                                    srcset="${contextPath}/images/rpa-appli@2x.jpg 2x" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </section> -->

        <!-- 成功案例 -->
        <a id="SYS-rpa-3"></a>
        <section class="SYS-section-02 rpa-intro rpa-success">
            <div class="SYS-module-box">
                <div class="title mb-5">
                    <div>
                        <h3><s:message code="rpa.section02.rpa.success.title"/></h3>	<!-- 多語系  -->
                    </div>
                </div>
                <div class="container">
                    <div class="">
                        <ul class="nav nav-pills justify-content-center" id="success-tab" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" id="success-home-tab" data-toggle="pill" href="#success-home"
                                    role="tab" aria-controls="pills-home" aria-selected="true"><s:message code="rpa.section02.rpa.success.tab01"/></a>	<!-- 多語系  -->
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="success-profile-tab" data-toggle="pill" href="#success-profile"
                                    role="tab" aria-controls="pills-profile" aria-selected="false"><s:message code="rpa.section02.rpa.success.tab02"/></a>	<!-- 多語系  -->
                            </li>
                        </ul>
                        <div class="tab-content" id="success-tabContent">
                            <div class="tab-pane fade show active" id="success-home" role="tabpanel"
                                aria-labelledby="success-home-tab">
                                <div class="row d-flex">
                                    <div class="success-unit-box align-self-center">
                                        <div class="success-unit">
                                            <h4><s:message code="rpa.section02.rpa.success.tab01.title"/></h4>	<!-- 多語系  -->
                                            <span><s:message code="rpa.section02.rpa.success.tab01.desc01"/></span>	<!-- 多語系  -->
                                            <ul>
                                                <li><s:message code="rpa.section02.rpa.success.tab01.desc02"/></li>	<!-- 多語系  -->
                                                <li><s:message code="rpa.section02.rpa.success.tab01.desc03"/></li>	<!-- 多語系  -->
                                            </ul>
                                            <img src="${contextPath}/images/rpa-success-financial-img.png"
                                                srcset="${contextPath}/images/rpa-success-financial-img@2x.png 2x" class="" />
                                        </div>
                                    </div>
                                    <div class="success-caption-box">
                                        <div class="success-caption">
                                            <h4><s:message code="rpa.section02.rpa.success.tab01.box.title"/></h4>	<!-- 多語系  -->
                                            <ul>
                                                <li><s:message code="rpa.section02.rpa.success.tab01.box.desc01"/></li>	<!-- 多語系  -->
                                                <li><s:message code="rpa.section02.rpa.success.tab01.box.desc02"/></li>	<!-- 多語系  -->
                                                <li><s:message code="rpa.section02.rpa.success.tab01.box.desc03"/></li>	<!-- 多語系  -->
                                                <li><s:message code="rpa.section02.rpa.success.tab01.box.desc04"/></li>	<!-- 多語系  -->
                                            </ul>
                                            <h4><s:message code="rpa.section02.rpa.success.tab01.box.desc05"/></h4>	<!-- 多語系  -->
                                            <div class="text-center">
                                                <div class="success-circle">
                                                    <img src="${contextPath}/images/rpa-success-financial-circle01.png"
                                                        srcset="${contextPath}/images/rpa-success-financial-circle01@2x.png 2x"
                                                        class="img-fluid" />
                                                    <p><s:message code="rpa.section02.rpa.success.tab01.box.desc06"/></p>	<!-- 多語系  -->
                                                </div>
                                                <div class="success-circle">
                                                    <img src="${contextPath}/images/rpa-success-financial-circle02.png"
                                                        srcset="${contextPath}/images/rpa-success-financial-circle02@2x.png 2x"
                                                        class="img-fluid" />
                                                    <p><s:message code="rpa.section02.rpa.success.tab01.box.desc07"/></p>	<!-- 多語系  -->
                                                </div>
                                                <div class="success-circle">
                                                    <img src="${contextPath}/images/rpa-success-financial-circle03.png"
                                                        srcset="${contextPath}/images/rpa-success-financial-circle03@2x.png 2x"
                                                        class="img-fluid" />
                                                    <p><s:message code="rpa.section02.rpa.success.tab01.box.desc08"/></p>	<!-- 多語系  -->
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="success-profile" role="tabpanel"
                                aria-labelledby="success-profile-tab">
                                <div class="row d-flex">
                                    <div class="success-unit-box align-self-center">
                                        <div class="success-unit">
                                            <h4><s:message code="rpa.section02.rpa.success.tab02.title"/></h4>	<!-- 多語系  -->
                                            <span><s:message code="rpa.section02.rpa.success.tab02.box.desc01"/></span>	<!-- 多語系  -->
                                            <ul>
                                                <li><s:message code="rpa.section02.rpa.success.tab02.box.desc02"/>	<!-- 多語系  -->
                                                </li>
                                                <li><s:message code="rpa.section02.rpa.success.tab02.box.desc03"/></li>	<!-- 多語系  -->
                                                <li><s:message code="rpa.section02.rpa.success.tab02.box.desc04"/></li>	<!-- 多語系  -->
                                            </ul>
                                            <img src="${contextPath}/images/rpa-success-technology-img01.png"
                                                srcset="${contextPath}/images/rpa-success-technology-img01@2x.png 2x" class="" />
                                        </div>
                                    </div>
                                    <div class="success-caption-box">
                                        <div class="success-caption">
                                            <img src="${contextPath}/images/rpa-success-technology-img02.jpg"
                                                srcset="${contextPath}/images/rpa-success-technology-img02@2x.jpg 2x"
                                                class="img-fluid" />
                                            <h4><s:message code="rpa.section02.rpa.success.tab02.box.desc05"/></h4>	<!-- 多語系  -->
                                            <div class="text-center">
                                                <div class="success-circle">
                                                    <img src="${contextPath}/images/rpa-success-technology-circle01.png"
                                                        srcset="${contextPath}/images/rpa-success-financial-circle01@2x.png 2x"
                                                        class="img-fluid" />
                                                    <p><s:message code="rpa.section02.rpa.success.tab02.box.desc06"/></p>	<!-- 多語系  -->
                                                </div>
                                                <div class="success-circle">
                                                    <img src="${contextPath}/images/rpa-success-technology-circle02.png"
                                                        srcset="${contextPath}/images/rpa-success-technology-circle02@2x.png 2x"
                                                        class="img-fluid" />
                                                    <p><s:message code="rpa.section02.rpa.success.tab02.box.desc07"/></p>	<!-- 多語系  -->
                                                </div>
                                                <div class="success-circle">
                                                    <img src="${contextPath}/images/rpa-success-technology-circle03.png"
                                                        srcset="${contextPath}/images/rpa-success-technology-circle03@2x.png 2x"
                                                        class="img-fluid" />
                                                    <p><s:message code="rpa.section02.rpa.success.tab02.box.desc08"/></p>	<!-- 多語系  -->
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>

                            </div>
                            <div class="text-center">
                                <div class="rpa-button rpa-color-blue" data-toggle="modal" data-target="#advisoryModal">
                          			<s:message code="rpa.section02.rpa.success.button"/>	<!-- 多語系  -->
                                    <img src="${contextPath}/images/rpa-success-arrow.png" srcset="${contextPath}/images/rpa-success-arrow@2x.png 2x">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- 成功案例 end -->
        <section class="SYS-section-02 rpa-success-listen">
            <div class="product-nlu-top"><img class="img-responsive d-none d-md-block"
                    src="${contextPath}/images/rpa-success-listenbg.png" srcset="${contextPath}/images/rpa-success-listenbg@2x.png 2x" />
            </div>
            <div class="product-nlu-top"><img class="img-responsive d-md-none" src="${contextPath}/images/rpa-success-listenbgmob.png"
                    srcset="${contextPath}/images/rpa-success-listenbgmob@2x.png 2x" /></div>
            <div class="SYS-module-box">
                <div class="title mb-5">
                    <div>
						<h3><s:message code="rpa.section02.rpa.success.listen.title"/></h3>	<!-- 多語系  -->
                    </div>
                </div>
                <div class="container mt-5">
                    <div class="row d-flex">
                        <div class="col-lg-4 col-md-4 col-sm-12">
                            <div class="success-listen-box">
                                <!-- 人像 -->
                                <!-- <div class="listen-img">
                                    <img class="img-responsive" src="${contextPath}/images/rpa-success-listen01.jpg"
                                        srcset="${contextPath}/images/rpa-success-listen01@2x.jpg 2x" />
                                </div> -->
                                <!-- 人像 end-->
                                <div class="listen-content">
                                    <h4><s:message code="rpa.section02.rpa.success.listen.content01.title"/></h4>	<!-- 多語系  -->
                                    <p>
                                    	<s:message code="rpa.section02.rpa.success.listen.content01.desc"/>	<!-- 多語系  -->
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-12">
                            <div class="success-listen-box">
                                <!-- 人像 -->
                                <!-- <div class="listen-img">
                                    <img class="img-responsive" src="${contextPath}/images/rpa-success-listen02.jpg"
                                        srcset="${contextPath}/images/rpa-success-listen02@2x.jpg 2x" />
                                </div> -->
                                <!-- 人像 end-->
                                <div class="listen-content">
                                    <h4><s:message code="rpa.section02.rpa.success.listen.content02.title"/></h4>	<!-- 多語系  -->
                                    <p>
                                    	<s:message code="rpa.section02.rpa.success.listen.content02.desc"/>	<!-- 多語系  -->
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-12">
                            <div class="success-listen-box">
                                <!-- 人像 -->
                                <!-- <div class="listen-img">
                                    <img class="img-responsive" src="${contextPath}/images/rpa-success-listen03.jpg"
                                        srcset="${contextPath}/images/rpa-success-listen03@2x.jpg 2x" />
                                </div> -->
                                <!-- 人像 end-->
                                <div class="listen-content">
                                    <h4><s:message code="rpa.section02.rpa.success.listen.content03.title"/></h4>
                                    <p>
                                    	<s:message code="rpa.section02.rpa.success.listen.content03.desc"/>	<!-- 多語系  -->
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- SYS-rpa-5 end -->
        <section class="SYS-section-02 rpa-success-report">
            <div class="SYS-module-box">
                <div class="title mb-5">
                    <div>
                        <h3><s:message code="rpa.section02.rpa.success.report.title"/></h3>	<!-- 多語系  -->
                    </div>
                </div>
                <div class="container mt-5">
                    <div class="row d-flex">
                        <div class="col-lg-4 col-md-4 col-sm-12">
                            <a href="https://medium.com/systalk-ai/%E8%AA%8D%E8%AD%98rpa-%E6%95%B8%E4%BD%8D%E5%8B%9E%E5%8B%95%E5%8A%9B%E8%A7%A3%E6%B1%BA%E6%96%B9%E6%A1%88%E7%AC%AC%E4%B8%80%E6%AD%A5-fd2f15741c17"
                                target="_blank">
                                <div class="report-box">
                                    <div class="report-img">
                                        <img class="img-responsive" src="${contextPath}/images/rpa-report-img01.jpg"
                                            srcset="${contextPath}/images/rpa-report-img01@2x.jpg 2x" />
                                    </div>
                                    <div class="report-content align-self-center">
                                        <h4><s:message code="rpa.section02.rpa.success.report.box01.title"/></h4>	<!-- 多語系  -->
                                        <span>systalk-ai</span>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-12">
                            <a href="https://www.ithome.com.tw/tech/124228" target="_blank">
                                <div class="report-box">
                                    <div class="report-img">
                                        <img class="img-responsive" src="${contextPath}/images/rpa-report-img02.jpg"
                                            srcset="${contextPath}/images/rpa-report-img02@2x.jpg 2x" />
                                    </div>
                                    <div class="report-content align-self-center">
                                        <h4><s:message code="rpa.section02.rpa.success.report.box02.title"/></h4>	<!-- 多語系  -->
                                        <span>ithome</span>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-12">
                            <a href="https://buzzorange.com/techorange/2019/02/21/rpa/" target="_blank">
                                <div class="report-box">
                                    <div class="report-img">
                                        <img class="img-responsive" src="${contextPath}/images/rpa-report-img03.jpg"
                                            srcset="${contextPath}/images/rpa-report-img03@2x.jpg 2x" />
                                    </div>
                                    <div class="report-content align-self-center">
                                        <h4><s:message code="rpa.section02.rpa.success.report.box03.title"/></h4>	<!-- 多語系  -->
                                        <span>TechOrange</span>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="text-center">
                    <div class="rpa-button rpa-color-light-blue" data-toggle="modal" data-target="#advisoryModal">
               			<s:message code="rpa.button"/>	<!-- 多語系  -->
                     	<img src="${contextPath}/images/rpa-success-arrow.png" srcset="${contextPath}/images/rpa-success-arrow@2x.png 2x">
                    </div>
                </div>
            </div>
        </section>


        <!-- Modal -->
        <div class="modal fade rpa-modal" id="advisoryModal" tabindex="-1" role="dialog"
            aria-labelledby="advisoryModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="sendRpaEmailForm" name="sendRpaEmailForm" >
                            <div class="chatF__form-field__logo text-center">
                                <img src="${contextPath}/images/rpa-modal-logo.png" srcset="${contextPath}/images/rpa-modal-logo@2x.png 2x" alt="rpa"
                                    class="img-fluid">
                            </div>
                            <div class="chatF__form-field__row">
                                <input type="text" placeholder="<s:message code="rpa.send.rpa.email.placeholder.name"/>" id="rpaName" name="rpaName">	<!-- ＊姓名  -->
                            </div>
                            <div class="chatF__form-field__row">
                                <input type="email" placeholder="<s:message code="rpa.send.rpa.email.placeholder.email"/>" id="rpaEmail" name="rpaEmail">	<!-- ＊E-mail  -->
                            </div>
                            <div class="chatF__form-field__row">
                                <input type="number" placeholder="<s:message code="rpa.send.rpa.email.placeholder.phone"/>" id="rpaPhone" name="rpaPhone">	<!-- ＊電話 -->
                            </div>
                            <div class="chatF__form-field__row">
                                <input type="text" placeholder="<s:message code="rpa.send.rpa.email.placeholder.companyName"/>" id="rpaCompanyName" name="rpaCompanyName">	<!-- ＊公司名稱  -->
                            </div>
                            <div class="chatF__form-field__row">
                                <!--<input type="text" placeholder="需求描述" name="requirement">-->
                                <textarea name="rpaDes" id="rpaDes" placeholder="<s:message code="rpa.send.rpa.email.placeholder.desc"/>"></textarea>	<!-- ＊需求描述  -->
                            </div>

							<!-- Google reCAPTCHA V2 -->
							<div class="chatF__form-field__captcha">
								<div id="recaptchaField">
									<div class="g-recaptcha" data-sitekey="6Lerf8EUAAAAAI8SSwTb_9ZSuUbmNNkgKiN_uwV6"></div>
									<input type="hidden" id="captchaResponse" name="captchaResponse">
								</div>
							</div>
							
							<div class="text-center modal-bottom">
                                <div class="chatF__btn-wrap">
                                    <a id="buttonInquiry" href="#" class="chatF__btn" onclick="doSendRpaEmailAction()"><s:message code="rpa.send.rpa.email.submit"/></a>	<!-- 立即諮詢  -->
                            </div>
                                <small><s:message code="rpa.send.rpa.email.footer"/></small>	<!-- 多語系  -->
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Thank you -->
        <div class="modal fade rpa-modal" id="doneModal" tabindex="-1" role="dialog"
            aria-labelledby="doneModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body text-center">
                        <div class="rpa-modal-thankyou">
                            <img src="${contextPath}/images/rpa-modal-thankyou.png" srcset="${contextPath}/images/rpa-modal-thankyou@2x.png 2x"
                                alt="rpa" class="img-fluid">
                            <h4><s:message code="rpa.thankyou.pop.title"/></h4>	<!-- 多語系  -->
                            <p><s:message code="rpa.thankyou.pop.desc"/></p>	<!-- 多語系  -->
                            <img src="${contextPath}/images/rpa-modal-logo.png" srcset="${contextPath}/images/rpa-modal-logo@2x.png 2x" alt="rpa"
                                class="rpa-logo-small">
                        </div>
                    </div>
                </div>
            </div>
        </div>


<!-- 2019.10.09 新增的css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/owl.carousel.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/owl.css">

<!-- 2019.11.08 新增的css center menu -->
<link href="<%=request.getContextPath()%>/css/center-menu-style.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/center-menu.css" rel="stylesheet">

<!-- 2019.10.09 新增的js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/owl.carousel.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/owl.js"></script>

<!-- 2019.11.08 新增的js -->

<!-- 引用該js  dialog 及小版nav bar 會無法收回-->
<!-- <script src="<%=request.getContextPath()%>/js/vendor/jquery.min.js"></script> --> 

<script src="<%=request.getContextPath()%>/js/vendor/jquery.fancybox.min.js"></script>
<script src="<%=request.getContextPath()%>/js/vendor/main.js"></script>


</html>