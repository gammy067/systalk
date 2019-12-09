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

</head>

<script type="text/javascript">

</script>
<!-- banner -->
<section class="product-bg">
    <div class="container">
        <div class="row d-flex">
            <div class="col-lg-7 col-md-7 col-sm-12">
                <div class="product-banner-tit wow fadeInLeft">
                    <img src="${contextPath}/images/product-banner-logo.png"
                        srcset="${contextPath}/images/product-banner-logo@2x.png 2x" />
                    <div class="product-title">
                    NLU + FAQ 雙腦對話引擎，<br>
                        讓 AI 機器人更懂人的心。</div>
                    <div class="product-DESC">對話，完整滿足客戶需求</div>
                </div>
            </div>
            <div class="col-lg-5 col-md-5 col-sm-12">
                <div class="product-banner-img wow bounceInUp">
                    <img src="${contextPath}/images/product-banner-phone.png" srcset="${contextPath}/images/product-banner-phone@2x.png 2x" alt="Chatbot Demo"/>
                </div>
            </div>
        </div>
    </div>
    <div class="product-banner-bottom"><img class="img-responsive" src="${contextPath}/images/product-banner-bottom.png"
            srcset="${contextPath}/images/product-banner-bottom@2x.png 2x" />
    </div>
</section>
<!-- banner end -->


<!-- section-2 -->
<section class="SYS-section-02">
    <div class="tab-content" id="pills-tabContent">
        <!-- 雙腦+一流程：核心模組運行模式 -->
        <div class="tab-pane fade show active" id="pills-01" role="tabpanel" aria-labelledby="pills-tab-01">
            <div class="SYS-module-box">
                <div class="title mb-10">
                    <div>
                    	<h1>雙腦 + 一流程：打造最聰明的AI Chatbot</h1>
                    </div>
                </div>
                <div class="sub-title">
                    <div>
                        <h4>雙腦對話引擎，讓 AI 機器人更懂人的心</h4>
                    </div>
                </div>
                <div class="container">
                    <div class="row justify-content-center d-none d-mb-flex">
                        <button href="#" class="btn btn-collapse"><span class="btn-collapse-sub"><img
                                    class="icon-collapse" src="${contextPath}/images/icon-more@2x.png"
                                    alt=""></span><span class="collapse-txt">展開</span></button>
                    </div>
                    <div class="row justify-content-center img-collapse">
                        <!-- web版 出現大圖 -->
                        <div class="img-flow"><img src="${contextPath}/images/img-flow@2x.png" alt="雙腦 + 一流程：打造最聰明的AI Chatbot"></div>
                        <!-- 手機版 出現小圖 -->
                        <div class="img-flow-m"><img src="${contextPath}/images/img-flow-m@2x.png" alt="雙腦 + 一流程：打造最聰明的AI Chatbot"></div>
                    </div>
                    <div class="row justify-content-center p-relative">
                        <div class="about-SYS-box">
                            <!-- web版 出現大圖 -->
                            <img class="img-about-flow" src="${contextPath}/images/img-about-flow@2x.png" alt="">
                            <!-- 手機版 出現小圖 -->
                            <img class="img-about-flow-m" src="${contextPath}/images/img-about-flow-m@2x.png" alt="">
                            <!-- FLOW -->
                            <div class="about-flow-box about-FLOW wow fadeInRight">
                                <div class="flow-title"><img src="${contextPath}/images/img-flowTitle-FLOW@2x.png"
                                        alt="">
                                </div>
                                <div class="flow-subtitle">負責彈性串接各種處理引擎成為完整流程</div>
                                <div class="flow-DESC">
                                    <ul>
                                        <li>調整<span class="c-0076C5">自由度高</span>：照情境設計單雙引擎流程</li>
                                        <li><span class="c-0076C5">節點式</span>流程設計：易確認錯誤發生點</li>
                                        <li>以 <span class="c-0076C5">Java script</span> 編碼：簡潔易懂好上手</li>
                                    </ul>
                                </div>
                            </div>
                            <!-- NLU -->
                            <div class="about-flow-box about-NLU wow fadeInDown" data-wow-delay=".6s">
                                <div class="NLU-title"><img src="${contextPath}/images/img-flowTitle-NLU@2x.png" alt="">
                                </div>
                                <div class="NLU-subtitle">處理有個人需求的查詢類問題</div>
                                <div class="NLU-DESC">
                                    <ul>
                                        <li><span class="c-0076C5">判斷意圖</span>模擬人類思考，理解人類問法</li>
                                        <li><span class="c-0076C5">上下文判斷填槽</span>，分辨人類話語結構</li>
                                        <li>無須窮舉，較<span class="c-0076C5">有效率訓練資料</span>與建模</li>
                                    </ul>
                                </div>
                            </div>
                            <!-- FAQ -->
                            <div class="about-flow-box about-FAQ wow fadeInLeft" data-wow-delay="1.5s">
                                <div class="FAQ-title"><img src="${contextPath}/images/img-flowTitle-FAQ@2x.png" alt="">
                                </div>
                                <div class="FAQ-subtitle">處理有固定答案的諮詢類問題</div>
                                <div class="FAQ-DESC">
                                    <ul>
                                        <li>不只關鍵字單一對照，<span class="c-0076C5">演算法</span>比對更準確</li>
                                        <li><span class="c-0076C5">多問一答設計</span>，可適應擴展問</li>
                                        <li><span class="c-0076C5">同義詞與停用詞</span>設計，提升答對率</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 雙腦+一流程：核心模組運行模式 end -->
    </div>
</section>
<!-- section-2 end -->

<!-- section-3 -->
<section class="SYS-section-03 product-nlu">
    <div class="product-nlu-top"><img class="img-responsive d-none d-md-block"
            src="${contextPath}/images/product-nlu-topbg.png"
            srcset="${contextPath}/images/product-nlu-topbg@2x.png 2x" />
    </div>
    <div class="product-nlu-top"><img class="img-responsive d-md-none"
            src="${contextPath}/images/product-nlu-topbg-mob.png"
            srcset="${contextPath}/images/product-nlu-topbg-mob@2x.png 2x" />
    </div>
    <div class="product-nlu-box">
        <div class="title-white">
            <div>
                <h2 class="c-FFF">NLU自然語言理解</h2>
            </div>
        </div>
        <div class="container">
            <div class="product-nlu-content">
                <div class="product-nlu-topline d-none d-md-block"> <img class="img-responsive"
                        src="${contextPath}/images/product-nlu-top.png"
                        srcset="${contextPath}/images/product-nlu-top@2x.png 2x" />
                </div>

                <div class="product-creditcard">
                    <div class="product-cred-img">
                        <div class="product-cred-phone"> <img class="img-responsive"
                                src="${contextPath}/images/product-nlu-tab-phone.png"
                                srcset="${contextPath}/images/product-nlu-tab-phone@2x.png 2x" />
                            <div class="product-cred-tit">我想要信用卡繳費！</div>
                        </div>
                    </div>
                    <div class="product-tab">
                        <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" id="step1-tab" data-toggle="pill" href="#step1" role="tab"
                                    aria-controls="step1" aria-selected="true">
                                    <div class="nlu-step">step 1</div>
                                    <h5>N-gram<span>模型向量化</span></h5>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="step2-tab" data-toggle="pill" href="#step2" role="tab"
                                    aria-controls="step2" aria-selected="false">
                                    <div class="nlu-step">step 2</div>
                                    <h5>語言<span>實體特徵萃取</span></h5>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="step3-tab" data-toggle="pill" href="#step3" role="tab"
                                    aria-controls="step3" aria-selected="false">
                                    <div class="nlu-step">step 3</div>
                                    <h5>建立高維度<span>語言向量空間</span></h5>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="step4-tab" data-toggle="pill" href="#step4" role="tab"
                                    aria-controls="step4" aria-selected="false">
                                    <div class="nlu-step">step 4</div>
                                    <h5>分類<span>確認向量空間劃分</span></h5>
                                </a>
                            </li>
                        </ul>
                        <div class="tab-content text-center">
                            <div class="tab-pane fade show active" id="step1" role="tabpanel"
                                aria-labelledby="step1-tab">
                                <div class="nlu-tab">
                                    <p>利用 N-Gram 模型，將每一句對話拆解，進行向量化，取得特徵值結果。作為向量空間定位的基準值。</p>
                                    <div class="nlu-img"><img class="img-responsive"
                                            src="${contextPath}/images/product-nlu-tab-img01.jpg"
                                            srcset="${contextPath}/images/product-nlu-tab-img01@2x.jpg 2x" /></div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="step2" role="tabpanel" aria-labelledby="step2-tab">
                                <div class="nlu-tab">
                                    <p>透過取得的向量值進行比對，確認語句中的特徵值。此外初步將使用者意圖化為所需要補充的資訊。</p>
                                    <div class="nlu-img"><img class="img-responsive"
                                            src="${contextPath}/images/product-nlu-tab-img02.jpg"
                                            srcset="${contextPath}/images/product-nlu-tab-img02@2x.jpg 2x" /></div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="step3" role="tabpanel" aria-labelledby="step3-tab">
                                <div class="nlu-tab">
                                    <p>透過事前訓練的監督式語料形成的高維度語言向量空間確認該語句位置。</p>
                                    <div class="nlu-img"><img class="img-responsive"
                                            src="${contextPath}/images/product-nlu-tab-img03.jpg"
                                            srcset="${contextPath}/images/product-nlu-tab-img03@2x.jpg 2x" /></div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="step4" role="tabpanel" aria-labelledby="step4-tab">
                                <div class="nlu-tab">
                                    <p>以各種演算法進行向量空間劃分類別，再次確認使用者意圖使否正確。</p>
                                    <div class="nlu-img"><img class="img-responsive"
                                            src="${contextPath}/images/product-nlu-tab-img04.jpg"
                                            srcset="${contextPath}/images/product-nlu-tab-img04@2x.jpg 2x" />
                                    </div>
                                </div>
                            </div>
                            <div class="nlu-frame">透過導入服務<span class="d-inline-block">可降低建立服務門檻加速上線</span></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</section>
<!-- section-3 end -->
<!-- section-3 -->
<section class="SYS-section-03 product-nlu">
    <div class="product-nlu-box product-nlu-two">
        <div class="title-white">
            <div>
                <h2 class="c-FFF">NLU兩大招</h2>
            </div>
        </div>
        <div class="container">
            <div class="">
                <div class="product-nlu-leftline d-none d-md-block"> <img class="img-responsive"
                        src="${contextPath}/images/product-nlu-left.png"
                        srcset="${contextPath}/images/product-nlu-left@2x.png 2x" /></div>
                <div class="product-nlu-rightline d-none d-md-block"> <img class="img-responsive"
                        src="${contextPath}/images/product-nlu-right.png"
                        srcset="${contextPath}/images/product-nlu-right@2x.png 2x" /></div>
                <div class="brain-content">
                    <div class="link-left"></div>
                    <div class="link-right"></div>
                    <div class="product-nlu-leftbrain">
                        <div class="brain-box">
                            <div class="brain-tit">
                                <h3>實體識別</h3>
                                <hr>
                                <span>理性左腦</span>
                            </div>
                            <p>分辨使用者對話內容之指涉實體，於語料向量空間中定位，找出特別的資訊、特殊含義、語句。</p>
                        </div>
                    </div>
                    <div class="brainimg">
                        <img class="img-responsive leftbrainimg" src="${contextPath}/images/product-nlu-leftbrain.png"
                            srcset="${contextPath}/images/product-nlu-leftbrain@2x.png 2x" />
                        <img class="img-responsive rightbrainimg" src="${contextPath}/images/product-nlu-rightbrain.png"
                            srcset="${contextPath}/images/product-nlu-rightbrain@2x.png 2x" />
                    </div>
                    <div class="product-nlu-rightbrain">

                        <div class="brain-box">
                            <div class="brain-tit">
                                <h3>意圖分類</h3>
                                <hr>
                                <span>感性右腦</span>
                            </div>
                            <p>分析客戶對話內容之需求意圖，於語料向量空間中分類，找出正確的方向。</p>
                        </div>

                    </div>
                </div>

            </div>
        </div>
    </div>
    <div class="product-nlu-under d-none d-md-block">
    </div>
    <div class="product-nlu-top"><img class="img-responsive d-md-none"
            src="${contextPath}/images/product-nlu-bottom-mob.png"
            srcset="${contextPath}/images/product-nlu-bottom-mob@2x.png 2x" />
    </div>
</section>
<!-- section-3 end -->

<!-- faq-2 -->
<section class="SYS-section-02 product-faq">
    <div class="tab-content">
        <!-- FAQ 輕量級大腦兩段式跑分 -->
        <div class="">
            <div class="SYS-module-box">
                <div class="title mb-10">
                    <div>
                        <h2>FAQ 輕量級大腦 <span class="d-inline-block">兩段式跑分</span></h2>
                    </div>
                </div>
                <div class="sub-title">
                    <div>
                        <h4>共同評審制: 演算優先、再行比對</h4>
                    </div>
                </div>
                <div class="container">
                    <div class="faq-ball-box">
                        <div class="faq-under under-one d-none d-md-block"><img class="img-responsive"
                                src="${contextPath}/images/product-faq-under.png"
                                srcset="${contextPath}/images/product-faq-under@2x.png 2x" /></div>
                        <div class="faq-ball d-none d-md-block">
                            <img class="img-responsive" src="${contextPath}/images/product-faq-ball.jpg"
                                srcset="${contextPath}/images/product-faq-ball@2x.jpg 2x" />
                        </div>
                        <div class="faq-under under-two d-none d-md-block"><img class="img-responsive"
                                src="${contextPath}/images/product-faq-undertwo.png"
                                srcset="${contextPath}/images/product-faq-undertwo@2x.png 2x" /></div>
                        <div>
                            <div class="faq-one faq-box">
                                <div class="faq-tit">
                                    <h3>第一階段</h3>
                                    <hr>
                                    <span>巨量資料快速跑分</span>
                                </div>
                                <p>透過 Elastic search 第一階段快速比對大量資料，去除中低關聯性的語料</p>
                                <h4>低耗能、速度快</h4>
                            </div>
                        </div>
                        <div class="faq-question">
                            <p>收到問題</p>
                        </div>
                    </div>
                    <div class="faq-people">
                        <div class="faq-two faq-box">
                            <div class="faq-tit">
                                <h3>第二階段</h3>
                                <hr>
                                <span>小量資料精準跑分</span>
                            </div>
                            <p>經過第一階段巨量比對，第二階段將由 6 種不同演算法針對高關聯性語料進行跑分，強化比對語料間細微差異。</p>
                        </div>
                        <div class="text-center">
                            <img class="img-responsive" src="${contextPath}/images/product-faq-people.png"
                                srcset="${contextPath}/images/product-faq-people@2x.png 2x" /></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="faq-rule d-none d-md-block"><img class="img-responsive"
                src="${contextPath}/images/product-rule-top.jpg"
                srcset="${contextPath}/images/product-rule-top@2x.jpg 2x" />
        </div>
        <!-- <div class="faq-rule d-md-none"><img class="img-responsive" src="${contextPath}/images/product-rule-topmob.png"
                        srcset="${contextPath}/images/product-rule-topmob@2x.png 2x" />
                </div> -->
        <div class="rule-header text-center">
            <div class="rule-DESC">掰掰！傳統 Rule based<br>
                SysTalk.Chat 之 FAQ 運用演算法，<span class="d-inline-block">得以將字詞正確區分，演算後得出最佳分數，</span><span
                    class="d-inline-block">精準回答正確率高。</span></div>
        </div>
        <div class="rule-content">
            <div class="rule-tit title">
                <div>
                    <h2>輕量級系統三大特點</h2>
                </div>
            </div>
            <div class="container">
                <div class="row justify-content-md-center">
                    <div class="col col-lg-3">
                        <div class="rule-row text-center">
                            <div class="rule-icon"><img class="img-responsive"
                                    src="${contextPath}/images/product-rule-icon01.png"
                                    srcset="${contextPath}/images/product-rule-icon01@2x.png 2x" /></div>
                            <div class="rule-inn">
                                <h4>可建立階層式對話</h4>
                                <p>模擬人類對話，降低對話情境斷裂感</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-auto">
                        <div class="rule-row text-center">
                            <div class="rule-icon"><img class="img-responsive"
                                    src="${contextPath}/images/product-rule-icon02.png"
                                    srcset="${contextPath}/images/product-rule-icon02@2x.png 2x" /></div>
                            <div class="rule-inn">
                                <h4>可多問一答</h4>
                                <p>不設限人類提問方式，找尋正確專一答案</p>
                            </div>

                        </div>
                    </div>
                    <div class="col col-lg-3">
                        <div class="rule-row text-center">
                            <div class="rule-icon"><img class="img-responsive"
                                    src="${contextPath}/images/product-rule-icon03.png"
                                    srcset="${contextPath}/images/product-rule-icon03@2x.png 2x" /></div>
                            <div class="rule-inn">
                                <h4>可標記同義詞與停用詞</h4>
                                <p>強化同義專有詞庫，提升答對率</p>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="rule-footer"></div>
        <!-- FAQ 輕量級大腦兩段式跑分 end -->
    </div>
</section>
<!-- faq end -->
<!-- FLOW -->
<section class="SYS-section-02 product-flow">
    <div class="tab-content">
        <!-- FLOW創造多輪對話 貼近真人對話習慣 -->
        <div class="SYS-module-box">
            <div class="title mb-10">
                <div>
                    <h2>FLOW創造多輪對話 <span class="d-inline-block">貼近真人對話習慣</span></h2>
                </div>
            </div>
            <!-- <div class="sub-title">
                    <div>
                        <h4>可放一些說明文說明文說明文說明文說明文說明文</h4>
                    </div>
                </div> -->
        </div>
        <!-- FLOW創造多輪對話 貼近真人對話習慣 end-->
        <!-- FLOW創造多輪對話 內容 -->
        <div class="SYS-module-box product-flow-content">
            <div class="container">
                <div class="product-flow-box">
                    <div class="flow-img img-one">
                        <div class=""> <img class="img-responsive" src="${contextPath}/images/product-flow-icon01.png"
                                srcset="${contextPath}/images/product-flow-icon01@2x.png 2x" /></div>
                    </div>
                    <div class="flow-img img-two">
                        <div class=""> <img class="img-responsive" src="${contextPath}/images/product-flow-icon02.png"
                                srcset="${contextPath}/images/product-flow-icon02@2x.png 2x" /></div>
                    </div>
                    <div class="flow-big-item item-one">
                        <div class="row">
                            <div class="col col-sm-12 col-md-6">
                                <span class="flow-DESC">提高服務完成率</span>
                            </div>
                            <div class="col col-sm-12 col-md-6 order-sm-first">
                                <div class="flow-tit">多輪式對話</div>
                            </div>
                        </div>
                        <p>可透過多次來回詢問的方式取得客戶相關資訊，並協助完成業務流程。</p>
                    </div>
                    <div class="flow-big-item item-two">
                        <div class="row">
                            <div class="col col-sm-12 col-md-6">
                                <span class="flow-DESC">降低跳離率</span>
                            </div>
                            <div class="col col-sm-12 col-md-6 order-sm-first">
                                <div class="flow-tit">多輪轉多輪</div>
                            </div>
                        </div>
                        <p>符合現實使用者跳轉話題的習慣</p>
                    </div>
                    <div class="flow-smal-box d-none d-md-block">
                        <div class="qa-smal">
                            <div class="qa-smal-img"> <img class="img-responsive"
                                    src="${contextPath}/images/product-flow-smalicon01.png"
                                    srcset="${contextPath}/images/product-flow-smalicon01@2x.png 2x" /></div>
                            <h4>Smart</h4>
                            <p>提高一次<br>解決率</p>
                        </div>
                        <div class="qa-smal">
                            <div class="qa-smal-img"> <img class="img-responsive"
                                    src="${contextPath}/images/product-flow-smalicon02.png"
                                    srcset="${contextPath}/images/product-flow-smalicon02@2x.png 2x" /></div>
                            <h4>Fast</h4>
                            <p>提升服務效率<br>與效果</p>
                        </div>
                        <div class="qa-smal">
                            <div class="qa-smal-img"> <img class="img-responsive"
                                    src="${contextPath}/images/product-flow-smalicon03.png"
                                    srcset="${contextPath}/images/product-flow-smalicon03@2x.png 2x" /></div>
                            <h4>Scalable</h4>
                            <p>可應未來新增<br>流程擴充</p>
                        </div>
                    </div>
                    <div class="product-flow-qa qa-one">
                        <div class="qa-row">
                            <div class="qa-left wow swing"> <img class="img-responsive"
                                    src="${contextPath}/images/product-flow-q01.png"
                                    srcset="${contextPath}/images/product-flow-q01@2x.png 2x" /></div>
                            <div class="qa-right wow swing"> <img class="img-responsive"
                                    src="${contextPath}/images/product-flow-a01.png"
                                    srcset="${contextPath}/images/product-flow-a01@2x.png 2x" /></div>
                        </div>
                    </div>
                    <div class="product-flow-qa qa-two">
                        <div class="qa-row">
                            <div class="qa-left wow swing"> <img class="img-responsive"
                                    src="${contextPath}/images/product-flow-q02.png"
                                    srcset="${contextPath}/images/product-flow-q02@2x.png 2x" /></div>
                            <div class="qa-right wow swing"> <img class="img-responsive"
                                    src="${contextPath}/images/product-flow-a02.png"
                                    srcset="${contextPath}/images/product-flow-a02@2x.png 2x" /></div>
                        </div>
                    </div>
                    <div class="product-flow-qa qa-three">
                        <div class="qa-row">
                            <div class="qa-left wow swing"> <img class="img-responsive"
                                    src="${contextPath}/images/product-flow-q01.png"
                                    srcset="${contextPath}/images/product-flow-q01@2x.png 2x" /></div>
                            <div class="qa-right wow swing"> <img class="img-responsive"
                                    src="${contextPath}/images/product-flow-a01.png"
                                    srcset="${contextPath}/images/product-flow-a01@2x.png 2x" /></div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="product-flow-bg01 d-none d-md-block"> <img class="img-responsive"
                    src="${contextPath}/images/product-flow-bg01.png"
                    srcset="${contextPath}/images/product-flow-bg01@2x.png 2x" /></div>
            <div class="product-flow-bg02 d-none d-md-block"> <img class="img-responsive"
                    src="${contextPath}/images/product-flow-bg02.png"
                    srcset="${contextPath}/images/product-flow-bg02@2x.png 2x" /></div>
            <div class="product-flow-bg03 d-none d-md-block"> <img class="img-responsive"
                    src="${contextPath}/images/product-flow-bg03.png"
                    srcset="${contextPath}/images/product-flow-bg03@2x.png 2x" /></div>
        </div>
        <!-- FFLOW創造多輪對話 內容 end-->
        <!-- FLOW創造多輪對話 影片 -->
        <div class="SYS-module-box product-flow-video">
            <div class="container">
                <div class="video-box">
                    <iframe width="560" height="315" src="https://www.youtube.com/embed/NiO-SE6Uu48" frameborder="0"
                        allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                        allowfullscreen></iframe>
                </div>
            </div>
        </div>
        <!-- FFLOW創造多輪對話 影片 end-->
        <!-- SysTalk.ai專門團隊 -->
        <div class="product-systalkai">
            <div class="product-flow-bg04 d-none d-md-block"> <img class="img-responsive"
                    src="${contextPath}/images/product-flow-bg04.png"
                    srcset="${contextPath}/images/product-flow-bg04@2x.png 2x" /></div>
            <div class="product-flow-bg05 d-none d-md-block"> <img class="img-responsive"
                    src="${contextPath}/images/product-flow-bg05.png"
                    srcset="${contextPath}/images/product-flow-bg05@2x.png 2x" /></div>

        </div>
        <div class="product-systalkai-bg">
            <div class="product-systalkai-animation">
                <div class="systalkai-box">
                    <div class="mb-10">
                        <div>
                            <h2>SysTalk.ai專門團隊</h2>
                        </div>
                    </div>
                    <div class="sub-title">
                        <div>
                            <h4>八大流程完整導入</h4>
                        </div>
                    </div>
                    <div class="container">

                        <div class="systalkai-inn">
                            SysTalk.ai 具有專門導入服務團隊，擅長協助企業進行大量語料解析、分類，並且規劃業務流程，協助進行建置 AI
                            大腦。團隊成員包含專案管理師、語言訓練師、流程規劃工程師等等。
                            協助您完整完成 SysTalk.Chat 專案。</div>
                    </div>
                    <div class="product-sys-ai"> <img class="img-responsive"
                            src="${contextPath}/images/product-systalkai-768.png"
                            srcset="${contextPath}/images/product-systalkai-768@2x.png 2x" /></div>
                    <div class="product-sys-mob"> <img class="img-responsive"
                            src="${contextPath}/images/product-systalkaimob.png"
                            srcset="${contextPath}/images/product-systalkaimob@2x.png 2x" /></div>
                </div>
            </div>
        </div>
        <!-- SysTalk.ai專門團隊 end-->
    </div>
</section>
<!-- FLOW end -->

<section class="solution">
    <div class="solution-tit">
        <div class="mb-10">
            <div>
                <h2>SysTalk.ai 解決方案</h2>
            </div>
        </div>
    <!-- <div class="sub-title">
            <div>
                <h4>SysTalk.ai Solution</h4>
            </div>
        </div> -->
    </div>
    <div class="container">
        <div id="carousel" class="owl-carousel">
            <div class="item">
                <div class="card">
                    <div class="headline">金融</div>
                    <ul>
                        <li>資產查詢</li>
                        <li>金融交易</li>
                        <li>金融商品諮詢</li>
                    </ul>
                    <div class="solution-divider">YOU NEED</div>
                    <div class="mt-2 mb-3">
                        <span class="tag">SysTalk ai</span>
                        <span class="icon-add"><img src="${contextPath}/images/icon-add@2x.png" alt=""></span>
                        <span class="tag">人臉辨識</span>
                    </div>
                    <div class="img">
                        <img class="img-responsive" src="${contextPath}/images/product-solution-icon01.png"
                            srcset="${contextPath}/images/product-solution-icon01@2x.png 2x" />
                    </div>
                    <div class="desc">金融核身</div>
                </div>
            </div>
            <div class="item">
                <div class="card">
                    <div class="headline">法律</div>
                    <ul>
                        <li>合約審閱</li>
                        <li>文字校閱</li>
                        <li>條文提示</li>
                    </ul>
                    <div class="solution-divider">YOU NEED</div>
                    <div class="mt-2 mb-3">
                        <span class="tag">SysTalk ai</span>
                        <span class="icon-add"><img src="${contextPath}/images/icon-add@2x.png" alt=""></span>
                        <span class="tag">文本分析</span>
                    </div>
                    <div class="img">
                        <img class="img-responsive" src="${contextPath}/images/product-solution-icon02.png"
                            srcset="${contextPath}/images/product-solution-icon02@2x.png 2x" />
                    </div>
                    <div class="desc">法律合約</div>
                </div>
            </div>
            <div class="item">
                <div class="card">
                    <div class="headline">交通</div>
                    <ul>
                        <li>票務交易</li>
                        <li>時刻查詢</li>
                        <li>即時通行資訊</li>
                    </ul>
                    <div class="solution-divider">YOU NEED</div>
                    <div class="mt-2 mb-3">
                        <span class="tag">SysTalk ai</span>
                        <span class="icon-add"><img src="${contextPath}/images/icon-add@2x.png" alt=""></span>
                        <span class="tag">專業知識</span>
                    </div>
                    <div class="img">
                        <img class="img-responsive" src="${contextPath}/images/product-solution-icon03.png"
                            srcset="${contextPath}/images/product-solution-icon03@2x.png 2x" />
                    </div>
                    <div class="desc">通行服務</div>
                </div>
            </div>
            <div class="item">
                <div class="card">
                    <div class="headline">醫療</div>
                    <ul>
                        <li>醫病共同決策系統</li>
                        <li>用藥提醒</li>
                        <li>掛號 / 繳費</li>
                    </ul>
                    <div class="solution-divider">YOU NEED</div>
                    <div class="mt-2 mb-3">
                        <span class="tag">SysTalk ai</span>
                        <span class="icon-add"><img src="${contextPath}/images/icon-add@2x.png" alt=""></span>
                        <span class="tag">情緒偵測</span>
                    </div>
                    <div class="img">
                        <img class="img-responsive" src="${contextPath}/images/product-solution-icon04.png"
                            srcset="${contextPath}/images/product-solution-icon04@2x.png 2x" />
                    </div>
                    <div class="desc">醫護幫手</div>
                </div>
            </div>
        </div>
    </div>

</section>

<!-- 2019.10.09 新增的css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/owl.carousel.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/owl.css">

<!-- 2019.10.09 新增的js -->
<script type="text/javascript" src="<%=request.getContextPath()%>/js/owl.carousel.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/owl.js"></script>

</html>