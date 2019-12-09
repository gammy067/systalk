<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html>


<head>

    <!-- 資料結構化JSON-LD標記 -->
    <script type="application/ld+json">{
        "@context": "http://schema.org",
        "@type": "WebPage",
        "name": "SysTalk.Chat｜AI智能客服：帶NLU大腦的聊天機器人",
        "url": "https://systalk.ai/chat",
        "description": "SysTalk.Chat透過NLU+FAQ雙腦對話引擎，幫助客服機器人更了解客戶需求。我們具有專業的AI服務導入團隊，擅長協助企業進行大量語料解析、分類，並且規劃業務流程，協助進行建置AI大腦。想導入AI智能客服系統，找SysTalk就對了!"}
    </script>
    <!-- 資料結構化JSON-LD標記END -->

    <meta name="description" content="SysTalk.Chat透過NLU+FAQ雙腦對話引擎，幫助Chatbot更了解客戶需求。我們具有專業的AI服務導入團隊，擅長協助企業進行大量語料解析、分類，並且規劃業務流程，協助進行建置AI大腦。想導入AI對話服務，找SysTalk就對了!">
    <meta property="og:title" content="SysTalk.Chat｜AI對話式服務導入：帶NLU大腦的Chatbot">
    <meta property="og:site_name" content="SysTalk.AI">
    <meta property="og:url" content="https://systalk.ai/chat">
    <meta property="og:description" content="SysTalk.Chat透過NLU+FAQ雙腦對話引擎，幫助Chatbot更了解客戶需求。我們具有專業的AI服務導入團隊，擅長協助企業進行大量語料解析、分類，並且規劃業務流程，協助進行建置AI大腦。想導入AI對話服務，找SysTalk就對了!">
    <meta property="og:type" content="website">
    <meta property="og:image" content="附件圖檔絕對路徑">
    <meta property="og:locale" content="zh_TW">    
    <title>SysTalk.Chat｜AI對話式服務導入：帶NLU大腦的Chatbot聊天機器人</title>
    
    <!-- google recaptcha api -->
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    
    <!-- Animate.css v3.5.2 -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css" rel="stylesheet" />

</head>

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
                            <img src="${contextPath}/images/rap-logo.png" srcset="${contextPath}/images/rap-logo@2x.png 2x" />
                            <div class="product-rpa-title">機器人流程自動化<br>
                                Robotic Process Automation</div>
                            <div class="product-rpa-DESC">流程自動化服務 實現數位勞動力第一步</div>
                            <div>
                                <div class="banner-button" data-toggle="modal" data-target="#advisoryModal">了解如何導入RPA
                                    <img src="${contextPath}/images/rpa-banner-arrow.png" srcset="${contextPath}/images/rpa-banner-arrow@2x.png 2x" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-12 col-sm-12">
                        <div class="product-banner-img">
                            <img src="${contextPath}/images/rap-banner.png" srcset="${contextPath}/images/rap-banner@2x.png 2x" class="img-rpa" />
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
                        <span>產品簡介</span></a>
                    <a href="SYS-rpa-2"><img src="${contextPath}/images/rpa-menu-benefit.png"
                            srcset="${contextPath}/images/rpa-menu-benefit@2x.png 2x" class="img-fluid" />
                        <span>產品效益</span></a>
                    <a href="SYS-rpa-3"><img src="${contextPath}/images/rpa-menu-service.png"
                            srcset="${contextPath}/images/rpa-menu-service@2x.png 2x" class="img-fluid" />
                        <span>服務內容</span></a>
                    <a href="SYS-rpa-4"><img src="${contextPath}/images/rpa-menu-appli.png" srcset="${contextPath}/images/rpa-menu-appli@2x.png 2x"
                            class="img-fluid" /><span>產業應用</span></a>
                    <a href="SYS-rpa-5">
                        <img src="${contextPath}/images/rpa-menu-success.png" srcset="${contextPath}/images/rpa-menu-success@2x.png 2x"
                            class="img-fluid" /><span>成功案例</span></a>
                    <a href="" data-toggle="modal" data-target="#advisoryModal">
                        <img src="${contextPath}/images/rpa-menu-advisory.png" srcset="${contextPath}/images/rpa-menu-advisory@2x.png 2x"
                            class="img-fluid" /><span>立即諮詢</span></a>
                </nav>
            </div>
        </section>
        <!-- /end menu -->

        <!-- SYS-rpa-1 -->
        <a id="SYS-rpa-1"></a>
        <section class="SYS-section-02">
            <div class="SYS-module-box">
                <div class="title mb-5">
                    <div>
                        <h1>RPA 機器人<span class="d-inline-block">流程自動化</span></h1>
                    </div>
                </div>
                <div class="container mt-5">
                    <div class="row d-flex">
                        <div class="col-lg-6 col-md-6 col-sm-12 text-center">
                            <img src="${contextPath}/images/rpa-intro.png" srcset="${contextPath}/images/rpa-intro@2x.png 2x" class="img-fluid" />
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-12 align-self-center">
                            <h4>RPA – 實現數位勞動力的第一步</h4>
                            <p>機器人流程自動化 (RPA) 被譽為 21 世紀最簡易的數位勞動力解決方案，透過輕量級程式編輯、分散式佈署架構，將自動化擴展到企業各項流程中，提升企業生產力。<br>RPA
                                以標準化形式，降低以往程式開發的障礙門檻，透過編輯器即可撰寫自己想要的自動化腳本，拆解複雜的程式邏輯，回歸使用者導向邏輯。不只減輕IT人員負擔，也將人力從繁瑣重複的工作中釋放，創造雙贏的人機共工工作環境。
                            </p>
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
                        <h4>完整提供 RPA 功能與導入服務</h4>
                    </div>
                </div>
                <div class="container">
                    <div class="product-nlu-content">
                        <ul class="nav nav-pills justify-content-center" id="pills-tab" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#pills-home"
                                    role="tab" aria-controls="pills-home" aria-selected="true">流程編輯機器人</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#pills-profile"
                                    role="tab" aria-controls="pills-profile" aria-selected="false">管理中心</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="pills-contact-tab" data-toggle="pill" href="#pills-contact"
                                    role="tab" aria-controls="pills-contact" aria-selected="false">執行機器人</a>
                            </li>
                        </ul>
                        <div class="tab-content" id="pills-tabContent">
                            <div class="tab-pane fade show active" id="pills-home" role="tabpanel"
                                aria-labelledby="pills-home-tab">
                                <div class="row d-flex">
                                    <div class="col-lg-6 col-md-6 col-sm-12">
                                        <div class="d-flex">
                                            <div class="rpa-intro-icon"> <img src="${contextPath}/images/rpa-intro-process.png"
                                                    srcset="${contextPath}/images/rpa-intro-process@2x.png 2x" class="" />
                                            </div>
                                            <ul class="rpa-tab rpa-process">
                                                <li>
                                                    內建 26 種操作類別
                                                </li>
                                                <li>
                                                    內建 100+ 模擬動作
                                                </li>
                                                <li>
                                                    C# 可追加自訂模擬動作
                                                </li>
                                                <li>
                                                    拖拉式介面輕鬆設計流程
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12">
                                        <img src="${contextPath}/images/rpa-intro-process-img.png"
                                            srcset="${contextPath}/images/rpa-intro-process-img@2x.png 2x" class="img-fluid" />
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="pills-profile" role="tabpanel"
                                aria-labelledby="pills-profile-tab">
                                <div class="row d-flex">
                                    <div class="col-lg-6 col-md-6 col-sm-12 align-self-center">
                                        <div class="d-flex">
                                            <div class="rpa-intro-icon"> <img src="${contextPath}/images/rpa-intro-manag.png"
                                                    srcset="${contextPath}/images/rpa-intro-manag@2x.png 2x" class="" />
                                            </div>
                                            <ul class="rpa-tab rpa-manag">
                                                <li>
                                                    即時儀錶板顯示運行狀態
                                                </li>
                                                <li>
                                                    平台集中排程 24/7 安排任務不間斷
                                                </li>
                                                <li>
                                                    WEB 介面管理方便又輕鬆
                                                </li>
                                                <li>
                                                    執行 log 集中管理
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12">
                                        <img src="${contextPath}/images/rpa-intro-manag-img.png"
                                            srcset="${contextPath}/images/rpa-intro-manag-img@2x.png 2x" class="img-fluid" />
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="pills-contact" role="tabpanel"
                                aria-labelledby="pills-contact-tab">
                                <div class="row d-flex">
                                    <div class="col-lg-6 col-md-6 col-sm-12 align-self-center">
                                        <div class="d-flex">
                                            <div class="rpa-intro-icon"> <img src="${contextPath}/images/rpa-intro-carried.png"
                                                    srcset="${contextPath}/images/rpa-intro-carried@2x.png 2x" class="" />
                                            </div>
                                            <ul class="rpa-tab rpa-carried">
                                                <li>
                                                    開機後自動運行
                                                </li>
                                                <li>
                                                    鎖定/螢幕保護也可正常運行
                                                </li>
                                                <li>
                                                    連接中心準確回報執行狀態
                                                </li>
                                                <li>
                                                    連接中心準確回報執行狀態
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-12">
                                        <img src="${contextPath}/images/rpa-intro-carried-img.png"
                                            srcset="${contextPath}/images/rpa-intro-carried-img@2x.png 2x" class="img-fluid" />
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
        <a id="SYS-rpa-2"></a>
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
        </section>
        <!-- SYS-rpa-2 end -->
        <!-- SYS-rpa-3 -->
        <a id="SYS-rpa-3"></a>
        <section class="SYS-section-02 rpa-service">
            <div class="SYS-module-box">
                <div class="title mb-5">
                    <div>
                        <h1>SysTalk.RPA <span class="d-inline-block">服務內容</span></h1>
                    </div>
                </div>
                <div class="container mt-5">
                    <div class="row d-flex">
                        <div class="col-lg-4 col-md-4 col-sm-12 text-center">

                            <img src="${contextPath}/images/rpa-service-img01.jpg" srcset="${contextPath}/images/rpa-service-img01@2x.jpg 2x"
                                class="img-fluid" />
                            <div class="">
                                <h4>RPA 軟體授權</h4>
                                <p>標準化授權<br>可隨時擴充</p>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-12 text-center">

                            <img src="${contextPath}/images/rpa-service-img02.jpg" srcset="${contextPath}/images/rpa-service-img02@2x.jpg 2x"
                                class="img-fluid" />
                            <div class="">
                                <h4>顧問諮詢</h4>
                                <p>客戶需求理解<br>流程分析及評估</p>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-12 text-center">

                            <img src="${contextPath}/images/rpa-service-img03.jpg" srcset="${contextPath}/images/rpa-service-img03@2x.jpg 2x"
                                class="img-fluid" />
                            <div class="">
                                <h4>流程開發</h4>
                                <p>客製化流程、開發通用模板<br>
                                    開發流程維護評估</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section class="SYS-section-02 rpa-service-select">
            <div class="product-nlu-top"><img class="img-responsive d-none d-md-block"
                    src="${contextPath}/images/rpa-service-centerbg.png" srcset="${contextPath}/images/rpa-service-centerbg@2x.png 2x" />
            </div>
            <div class="product-nlu-top"><img class="img-responsive d-md-none" src="${contextPath}/images/rpa-service-centerbgmob.png"
                    srcset="${contextPath}/images/rpa-service-centerbgmob@2x.png 2x" /></div>
            <div class="SYS-module-box">
                <div class="title mb-5">
                    <div>
                        <h1>為何選擇 SysTalk.RPA</h1>
                    </div>
                </div>
                <div class="container mt-5">
                    <div class="row d-flex">
                        <div class="col-lg-4 col-md-12 col-sm-12">
                            <div class="rpa-select-box">
                                <div class="rpa-select-icon align-self-center">
                                    <img src="${contextPath}/images/rpa-service-icon01.png"
                                        srcset="${contextPath}/images/rpa-service-icon01@2x.png 2x" class="img-fluid" />
                                </div>
                                <div class="rpa-select-tit align-self-center">
                                    <h4>輕量級導入成本</h4>
                                    <p>相較於他牌產品專案<br>
                                        費用僅需 6 成</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-12 col-sm-12">
                            <div class="rpa-select-box">
                                <div class="rpa-select-icon align-self-center">
                                    <img src="${contextPath}/images/rpa-service-icon02.png"
                                        srcset="${contextPath}/images/rpa-service-icon02@2x.png 2x" class="img-fluid" /></div>
                                <div class="rpa-select-tit align-self-center">
                                    <h4>金融級導入服務團隊</h4>
                                    <p>訪談 + 標準導入服務 =<br>
                                        更有效地完成專案</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-12 col-sm-12">
                            <div class="rpa-select-box">
                                <div class="rpa-select-icon align-self-center">
                                    <img src="${contextPath}/images/rpa-service-icon03.png"
                                        srcset="${contextPath}/images/rpa-service-icon03@2x.png 2x" class="img-fluid" /></div>
                                <div class="rpa-select-tit align-self-center">
                                    <h4>導入架構可彈性擴充</h4>
                                    <p>管理簡易<br>可執行多點架構</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- SYS-rpa-3 end -->
        <a id="SYS-rpa-4"></a>
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

        </section>

        <!-- SYS-rpa-5 -->
        <a id="SYS-rpa-5"></a>
        <section class="SYS-section-02 rpa-intro rpa-success">
            <div class="SYS-module-box">
                <div class="title mb-5">
                    <div>
                        <h1>成功案例</h1>
                    </div>
                </div>
                <div class="container">
                    <div class="">
                        <ul class="nav nav-pills justify-content-center" id="success-tab" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" id="success-home-tab" data-toggle="pill" href="#success-home"
                                    role="tab" aria-controls="pills-home" aria-selected="true">金融</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="success-profile-tab" data-toggle="pill" href="#success-profile"
                                    role="tab" aria-controls="pills-profile" aria-selected="false">科技</a>
                            </li>
                        </ul>
                        <div class="tab-content" id="success-tabContent">
                            <div class="tab-pane fade show active" id="success-home" role="tabpanel"
                                aria-labelledby="success-home-tab">
                                <div class="row d-flex">
                                    <div class="success-unit-box align-self-center">
                                        <div class="success-unit">
                                            <h4>日本某商業銀行 <span class="d-inline-block">匯款流程自動化</span></h4>
                                            <span>商務挑戰</span>
                                            <ul>
                                                <li>生產力低：長時間的人工流程處理</li>
                                                <li>系統老舊：因程序複雜以及開發成本高，無法進行系統更新</li>
                                            </ul>
                                            <img src="${contextPath}/images/rpa-success-financial-img.png"
                                                srcset="${contextPath}/images/rpa-success-financial-img@2x.png 2x" class="" />
                                        </div>
                                    </div>
                                    <div class="success-caption-box">
                                        <div class="success-caption">
                                            <h4>RPA 解決方案</h4>
                                            <ul>
                                                <li>標準化流程：將匯款流程及形式標準化</li>
                                                <li>流程自動化：不更動現有系統，以akaBot 自動化匯款手續</li>
                                                <li>資料驗證：利用 akaBot 驗證數據，發送通知電子郵件和了解配置參數</li>
                                                <li>電子化記錄：使用 OCR 技術分析匯款資料並轉換為文本。</li>
                                            </ul>
                                            <h4>成效</h4>
                                            <div class="text-center">
                                                <div class="success-circle">
                                                    <img src="${contextPath}/images/rpa-success-financial-circle01.png"
                                                        srcset="${contextPath}/images/rpa-success-financial-circle01@2x.png 2x"
                                                        class="img-fluid" />
                                                    <p>可用性</p>
                                                </div>
                                                <div class="success-circle">
                                                    <img src="${contextPath}/images/rpa-success-financial-circle02.png"
                                                        srcset="${contextPath}/images/rpa-success-financial-circle02@2x.png 2x"
                                                        class="img-fluid" />
                                                    <p>減少工作量</p>
                                                </div>
                                                <div class="success-circle">
                                                    <img src="${contextPath}/images/rpa-success-financial-circle03.png"
                                                        srcset="${contextPath}/images/rpa-success-financial-circle03@2x.png 2x"
                                                        class="img-fluid" />
                                                    <p>減少處理時間</p>
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
                                            <h4>FPT 集團 <span class="d-inline-block">季度財報表製作</span></h4>
                                            <span>企業挑戰</span>
                                            <ul>
                                                <li>人工處理 6 種不同文件及報表<span class="d-inline-block">(超過 50 種不同會計項目)</span></li>
                                                <li>造成報表資料錯誤，影響投資人投資意願</li>
                                                <li>Oracle 系統開發 API 耗費人力與時間過長</li>
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
                                            <h4>結果及影響</h4>
                                            <div class="text-center">
                                                <div class="success-circle">
                                                    <img src="${contextPath}/images/rpa-success-technology-circle01.png"
                                                        srcset="${contextPath}/images/rpa-success-financial-circle01@2x.png 2x"
                                                        class="img-fluid" />
                                                    <p>可用性</p>
                                                </div>
                                                <div class="success-circle">
                                                    <img src="${contextPath}/images/rpa-success-technology-circle02.png"
                                                        srcset="${contextPath}/images/rpa-success-technology-circle02@2x.png 2x"
                                                        class="img-fluid" />
                                                    <p>節省勞力</p>
                                                </div>
                                                <div class="success-circle">
                                                    <img src="${contextPath}/images/rpa-success-technology-circle03.png"
                                                        srcset="${contextPath}/images/rpa-success-technology-circle03@2x.png 2x"
                                                        class="img-fluid" />
                                                    <p>節省時間</p>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>

                            </div>
                            <div class="text-center">
                                <div class="rpa-button rpa-color-blue" data-toggle="modal" data-target="#advisoryModal">
                                    立即導入
                                    <img src="${contextPath}/images/rpa-success-arrow.png" srcset="${contextPath}/images/rpa-success-arrow@2x.png 2x">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- SYS-rpa-5 end -->
        <section class="SYS-section-02 rpa-success-listen">
            <div class="product-nlu-top"><img class="img-responsive d-none d-md-block"
                    src="${contextPath}/images/rpa-success-listenbg.png" srcset="${contextPath}/images/rpa-success-listenbg@2x.png 2x" />
            </div>
            <div class="product-nlu-top"><img class="img-responsive d-md-none" src="${contextPath}/images/rpa-success-listenbgmob.png"
                    srcset="${contextPath}/images/rpa-success-listenbgmob@2x.png 2x" /></div>
            <div class="SYS-module-box">
                <div class="title mb-5">
                    <div>
                        <h1>聽聽用過 RPA 的人怎麼說</h1>
                    </div>
                </div>
                <div class="container mt-5">
                    <div class="row d-flex">
                        <div class="col-lg-4 col-md-4 col-sm-12">
                            <div class="success-listen-box">
                                <div class="listen-img">
                                    <img class="img-responsive" src="${contextPath}/images/rpa-success-listen01.jpg"
                                        srcset="${contextPath}/images/rpa-success-listen01@2x.jpg 2x" />
                                </div>
                                <div class="listen-content">
                                    <h4>行銷總監</h4>
                                    <p>「過去在整理不同來源的報表總是要花費許多時間。現在有了 RPA，不管是 Facebook, 還是 Google
                                        Analytics 的數據都能自動抓取，讓我省下更多時間可以去從事創意發想。」
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-12">
                            <div class="success-listen-box">
                                <div class="listen-img">
                                    <img class="img-responsive" src="${contextPath}/images/rpa-success-listen02.jpg"
                                        srcset="${contextPath}/images/rpa-success-listen02@2x.jpg 2x" />
                                </div>
                                <div class="listen-content">
                                    <h4>財會經理</h4>
                                    <p>「平常複雜的對帳、查帳流程由 RPA 處理只要 1/5 的時間就能完成。透過 RPA 的協助我們部門的員工可以花更多時間在處理其他財務問題上了」
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-12">
                            <div class="success-listen-box">
                                <div class="listen-img">
                                    <img class="img-responsive" src="${contextPath}/images/rpa-success-listen03.jpg"
                                        srcset="${contextPath}/images/rpa-success-listen03@2x.jpg 2x" />
                                </div>
                                <div class="listen-content">
                                    <h4>人事主任</h4>
                                    <p>「自從人力資源部門導入 RPA 後，過去需要我花費大量人力處理的新進人員登錄工作現在只要花不到十分之一的時間就能完成了。」
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
                        <h1>相關 RPA 報導</h1>
                    </div>
                </div>
                <div class="container mt-5">
                    <div class="row d-flex">
                        <div class="col-lg-4 col-md-4 col-sm-12">
                            <a href="#">
                                <div class="report-box">
                                    <div class="report-img">
                                        <img class="img-responsive" src="${contextPath}/images/rpa-report-img01.jpg"
                                            srcset="${contextPath}/images/rpa-report-img01@2x.jpg 2x" />
                                    </div>
                                    <div class="report-content align-self-center">
                                        <h4>Azure Function 結合 Line Bot 玩出新花</h4>
                                        <span>電子時報</span>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-12">
                            <a href="#">
                                <div class="report-box">
                                    <div class="report-img">
                                        <img class="img-responsive" src="${contextPath}/images/rpa-report-img01.jpg"
                                            srcset="${contextPath}/images/rpa-report-img01@2x.jpg 2x" />
                                    </div>
                                    <div class="report-content align-self-center">
                                        <h4>流程引擎 - Activiti簡介與測試</h4>
                                        <span>電子時報</span>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-12">
                            <a href="#">
                                <div class="report-box">
                                    <div class="report-img">
                                        <img class="img-responsive" src="${contextPath}/images/rpa-report-img01.jpg"
                                            srcset="${contextPath}/images/rpa-report-img01@2x.jpg 2x" />
                                    </div>
                                    <div class="report-content align-self-center">
                                        <h4>撰寫一份好寫又好懂的Spec</h4>
                                        <span>中國時報</span>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="text-center">
                    <div class="rpa-button rpa-color-light-blue" data-toggle="modal" data-target="#advisoryModal">
                        立即導入
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
                                <input type="text" placeholder="＊姓名" id="rpaName" name="rpaName">
                            </div>
                            <div class="chatF__form-field__row">
                                <input type="email" placeholder="＊E-mail" id="rpaEmail" name="rpaEmail">
                            </div>
                            <div class="chatF__form-field__row">
                                <input type="number" placeholder="＊電話" id="rpaPhone" name="rpaPhone">
                            </div>
                            <div class="chatF__form-field__row">
                                <input type="text" placeholder="＊公司名稱" id="rpaCompanyName" name="rpaCompanyName">
                            </div>
                            <div class="chatF__form-field__row">
                                <!--<input type="text" placeholder="需求描述" name="requirement">-->
                                <textarea name="rpaDes" id="rpaDes" placeholder="＊需求描述"></textarea>
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
                                    <a id="buttonInquiry" href="#" class="chatF__btn" onclick="doSendRpaEmailAction()">立即諮詢</a>
                            </div>
                                <small>感謝您來信詢問，24 小時內將由專人與您聯繫。</small>
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
                            <h4>感謝您的詢問 / 留言</h4>
                            <p>我們會盡快回覆您</p>
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
<script src="<%=request.getContextPath()%>/js/vendor/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/vendor/jquery.fancybox.min.js"></script>
<script src="<%=request.getContextPath()%>/js/vendor/main.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.js"></script>


</html>