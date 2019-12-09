<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="locale" value="${pageContext.request.locale}" />

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
                    	<s:message code="chat.product.title"/>	<!-- 多語系 -->
                    </div>
                    <div class="product-DESC"><s:message code="chat.product.desc"/></div>	<!-- 多語系 -->
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
<section class="SYS-section-02 sub-bgtit">
    <div class="tab-content" id="pills-tabContent">
        <!-- 雙腦+一流程：核心模組運行模式 -->
        <div class="tab-pane fade show active" id="pills-01" role="tabpanel" aria-labelledby="pills-tab-01">
            <div class="SYS-module-box tit">
                <div class="title mb-10">
                    <div>
                    	<h2><s:message code="chat.section02.title"/></h2>	<!-- 多語系 -->
                    </div>
                </div>
                <div class="sub-title">
                    <div>
                        <h4><s:message code="chat.section02.sub.title"/></h4>	<!-- 多語系 -->
                    </div>
                </div>
                <div class="container">
                    <div class="row justify-content-center d-none d-mb-flex">
						<button href="#" class="btn btn-collapse">
							<span class="btn-collapse-sub"> <img class="icon-collapse"
								src="${contextPath}/images/icon-more@2x.png" alt="">
							</span> 
							<span class="collapse-txt">
								<s:message code="chat.section02.collapse.open"/> <!-- 展開 -->
							</span>
						</button>
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
                                <div class="flow-subtitle"><s:message code="chat.section02.flow.subtitle"/></div>	<!-- 多語系 -->
                                <div class="flow-DESC">
                                    <ul>
                                        <li><s:message code="chat.section02.flow.desc01"/></li>	<!-- 多語系 -->
                                        <li><s:message code="chat.section02.flow.desc02"/></li>	<!-- 多語系 -->
                                        <li><s:message code="chat.section02.flow.desc03"/></li>	<!-- 多語系 -->
                                    </ul>
                                </div>
                            </div>
                            <!-- NLU -->
                            <div class="about-flow-box about-NLU wow fadeInDown" data-wow-delay=".6s">
                                <div class="NLU-title"><img src="${contextPath}/images/img-flowTitle-NLU@2x.png" alt="">
                                </div>
                                <div class="NLU-subtitle"><s:message code="chat.section02.nlu.subtitle"/></div>
                                <div class="NLU-DESC">
                                    <ul>
                                        <li><s:message code="chat.section02.nlu.desc01"/></li>	<!-- 多語系 -->
                                        <li><s:message code="chat.section02.nlu.desc02"/></li>	<!-- 多語系 -->
                                        <li><s:message code="chat.section02.nlu.desc03"/></li>	<!-- 多語系 -->
                                    </ul>
                                </div>
                            </div>
                            <!-- FAQ -->
                            <div class="about-flow-box about-FAQ wow fadeInLeft" data-wow-delay="1.5s">
                                <div class="FAQ-title"><img src="${contextPath}/images/img-flowTitle-FAQ@2x.png" alt="">
                                </div>
                                <div class="FAQ-subtitle"><s:message code="chat.section02.faq.subtitle"/></div>	<!-- 多語系 -->
                                <div class="FAQ-DESC">
                                    <ul>
                                        <li><s:message code="chat.section02.faq.desc01"/></li>	<!-- 多語系 -->
                                        <li><s:message code="chat.section02.faq.desc02"/></li>	<!-- 多語系 -->
                                        <li><s:message code="chat.section02.faq.desc03"/></li>	<!-- 多語系 -->
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
                <h2 class="c-FFF"><s:message code="chat.section03.title"/></h2>	<!-- 多語系 -->
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
                            <div class="product-cred-tit"><s:message code="chat.section03.product.cred.tit"/></div>	<!-- 多語系 -->
                        </div>
                    </div>
                    <div class="product-tab">
                        <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" id="step1-tab" data-toggle="pill" href="#step1" role="tab"
                                    aria-controls="step1" aria-selected="true">
                                    <div class="nlu-step">step 1</div>
                                    <h5>N-gram<span><s:message code="chat.section03.nlu.step01"/></span></h5>	<!-- 多語系 -->
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="step2-tab" data-toggle="pill" href="#step2" role="tab"
                                    aria-controls="step2" aria-selected="false">
                                    <div class="nlu-step">step 2</div>
                                    <h5><s:message code="chat.section03.nlu.step02"/></h5>	<!-- 多語系 -->
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="step3-tab" data-toggle="pill" href="#step3" role="tab"
                                    aria-controls="step3" aria-selected="false">
                                    <div class="nlu-step">step 3</div>
                                    <h5><s:message code="chat.section03.nlu.step03"/></h5>	<!-- 多語系 -->
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="step4-tab" data-toggle="pill" href="#step4" role="tab"
                                    aria-controls="step4" aria-selected="false">
                                    <div class="nlu-step">step 4</div>
                                    <h5><s:message code="chat.section03.nlu.step04"/></h5>	<!-- 多語系 -->
                                </a>
                            </li>
                        </ul>
                        <div class="tab-content text-center">
                            <div class="tab-pane fade show active" id="step1" role="tabpanel"
                                aria-labelledby="step1-tab">
                                <div class="nlu-tab">
                                    <p><s:message code="chat.section03.nlu.tab01"/></p>	<!-- 多語系 -->
                                    <div class="nlu-img"><img class="img-responsive"
                                            src="${contextPath}/images/product-nlu-tab-img01.jpg"
                                            srcset="${contextPath}/images/product-nlu-tab-img01@2x.jpg 2x" /></div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="step2" role="tabpanel" aria-labelledby="step2-tab">
                                <div class="nlu-tab">
                                    <p><s:message code="chat.section03.nlu.tab02"/></p>	<!-- 多語系 -->
                                    <div class="nlu-img"><img class="img-responsive"
                                            src="${contextPath}/images/product-nlu-tab-img02.jpg"
                                            srcset="${contextPath}/images/product-nlu-tab-img02@2x.jpg 2x" /></div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="step3" role="tabpanel" aria-labelledby="step3-tab">
                                <div class="nlu-tab">
                                    <p><s:message code="chat.section03.nlu.tab03"/></p>	<!-- 多語系 -->
                                    <div class="nlu-img"><img class="img-responsive"
                                            src="${contextPath}/images/product-nlu-tab-img03.jpg"
                                            srcset="${contextPath}/images/product-nlu-tab-img03@2x.jpg 2x" /></div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="step4" role="tabpanel" aria-labelledby="step4-tab">
                                <div class="nlu-tab">
                                    <p><s:message code="chat.section03.nlu.tab04"/></p>	<!-- 多語系 -->
                                    <div class="nlu-img"><img class="img-responsive"
                                            src="${contextPath}/images/product-nlu-tab-img04.jpg"
                                            srcset="${contextPath}/images/product-nlu-tab-img04@2x.jpg 2x" />
                                    </div>
                                </div>
                            </div>
                            <div class="nlu-frame"><s:message code="chat.section03.nlu.frame"/></div>	<!-- 多語系 -->
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
                <h2 class="c-FFF"><s:message code="chat.section03.product.nlu.title"/></h2>	<!-- 多語系 -->
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
                                <h3><s:message code="chat.section03.product.leftbrain.tit01"/></h3>	<!-- 多語系 -->
                                <hr>
                                <span><s:message code="chat.section03.product.leftbrain.tit02"/></span>	<!-- 多語系 -->
                            </div>
                            <p><s:message code="chat.section03.product.leftbrain.desc"/></p>	<!-- 多語系 -->
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
                                <h3><s:message code="chat.section03.product.rightbrain.tit01"/></h3>	<!-- 多語系 -->
                                <hr>
                                <span><s:message code="chat.section03.product.rightbrain.tit02"/></span>	<!-- 多語系 -->
                            </div>
                            <p><s:message code="chat.section03.product.rightbrain.desc"/></p>	<!-- 多語系 -->
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
                        <h2><s:message code="chat.section02.product.faq.title"/></h2>	<!-- 多語系 -->
                    </div>
                </div>
                <div class="sub-title">
                    <div>
                        <h4><s:message code="chat.section02.product.faq.sub.title"/></h4>	<!-- 多語系 -->
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
                                    <h3><s:message code="chat.section02.product.faq.box01.tit"/></h3>	<!-- 多語系 -->
                                    <hr>
                                    <span><s:message code="chat.section02.product.faq.box01.desc01"/></span>	<!-- 多語系 -->
                                </div>
                                <p><s:message code="chat.section02.product.faq.box01.desc02"/></p>	<!-- 多語系 -->
                                <h4><s:message code="chat.section02.product.faq.box01.desc03"/></h4>	<!-- 多語系 -->
                            </div>
                        </div>
                        <div class="faq-question">
                            <p><s:message code="chat.section02.product.faq.question"/></p>	<!-- 多語系 -->
                        </div>
                    </div>
                    <div class="faq-people">
                        <div class="faq-two faq-box">
                            <div class="faq-tit">
                                <h3><s:message code="chat.section02.product.faq.box02.tit"/></h3>	<!-- 多語系 -->
                                <hr>
                                <span><s:message code="chat.section02.product.faq.box02.desc01"/></span>	<!-- 多語系 -->
                            </div>
                            <p><s:message code="chat.section02.product.faq.box02.desc02"/></p>	<!-- 多語系 -->
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
            <div class="rule-DESC">
            	<s:message code="chat.section02.rule.desc"/> <!-- 多語系 -->
			</div>
        </div>
        <div class="rule-content">
            <div class="rule-tit title">
                <div>
                    <h2><s:message code="chat.section02.rule.content"/></h2>	<!-- 多語系 -->
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
                                <h4><s:message code="chat.section02.rule01.inn01"/></h4>	<!-- 多語系 -->
                                <p><s:message code="chat.section02.rule01.inn02"/></p>	<!-- 多語系 -->
                            </div>
                        </div>
                    </div>
                    <div class="col-md-auto">
                        <div class="rule-row text-center">
                            <div class="rule-icon"><img class="img-responsive"
                                    src="${contextPath}/images/product-rule-icon02.png"
                                    srcset="${contextPath}/images/product-rule-icon02@2x.png 2x" /></div>
                            <div class="rule-inn">
                                <h4><s:message code="chat.section02.rule02.inn01"/></h4>	<!-- 多語系 -->
                                <p><s:message code="chat.section02.rule02.inn02"/></p>	<!-- 多語系 -->
                            </div>

                        </div>
                    </div>
                    <div class="col col-lg-3">
                        <div class="rule-row text-center">
                            <div class="rule-icon"><img class="img-responsive"
                                    src="${contextPath}/images/product-rule-icon03.png"
                                    srcset="${contextPath}/images/product-rule-icon03@2x.png 2x" /></div>
                            <div class="rule-inn">
                                <h4><s:message code="chat.section02.rule03.inn01"/></h4>	<!-- 多語系 -->
                                <p><s:message code="chat.section02.rule03.inn02"/></p>	<!-- 多語系 -->
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
                    <h2><s:message code="chat.section02.product.flow.title"/></h2>	<!-- 多語系 -->
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
                                <span class="flow-DESC"><s:message code="chat.section02.product.flow01.desc01"/></span>	<!-- 多語系 -->
                            </div>
                            <div class="col col-sm-12 col-md-6 order-sm-first">
                                <div class="flow-tit"><s:message code="chat.section02.product.flow01.desc02"/></div>	<!-- 多語系 -->
                            </div>
                        </div>
                        <p><s:message code="chat.section02.product.flow01.desc03"/></p>	<!-- 多語系 -->
                    </div>
                    <div class="flow-big-item item-two">
                        <div class="row">
                            <div class="col col-sm-12 col-md-6">
                                <span class="flow-DESC"><s:message code="chat.section02.product.flow02.desc01"/></span>	<!-- 多語系 -->
                            </div>
                            <div class="col col-sm-12 col-md-6 order-sm-first">
                                <div class="flow-tit"><s:message code="chat.section02.product.flow02.desc02"/></div>	<!-- 多語系 -->
                            </div>
                        </div>
                        <p><s:message code="chat.section02.product.flow02.desc03"/></p>	<!-- 多語系 -->
                    </div>
                    <div class="flow-smal-box d-none d-md-block">
                        <div class="qa-smal">
                            <div class="qa-smal-img"> <img class="img-responsive"
                                    src="${contextPath}/images/product-flow-smalicon01.png"
                                    srcset="${contextPath}/images/product-flow-smalicon01@2x.png 2x" /></div>
                            <h4>Smart</h4>
                            <p><s:message code="chat.section02.product.flow.qa.smal01"/></p>	<!-- 多語系 -->
                        </div>
                        <div class="qa-smal">
                            <div class="qa-smal-img"> <img class="img-responsive"
                                    src="${contextPath}/images/product-flow-smalicon02.png"
                                    srcset="${contextPath}/images/product-flow-smalicon02@2x.png 2x" /></div>
                            <h4>Fast</h4>
                            <p><s:message code="chat.section02.product.flow.qa.smal02"/></p>	<!-- 多語系 -->
                        </div>
                        <div class="qa-smal">
                            <div class="qa-smal-img"> <img class="img-responsive"
                                    src="${contextPath}/images/product-flow-smalicon03.png"
                                    srcset="${contextPath}/images/product-flow-smalicon03@2x.png 2x" /></div>
                            <h4>Scalable</h4>
                            <p><s:message code="chat.section02.product.flow.qa.smal03"/></p>	<!-- 多語系 -->
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
        	<div class="product-systalkai-aircraft"></div>
     		<div class="product-systalkai-circle circle01"></div>
       		<div class="product-systalkai-circle circle02"></div>
     		<div class="product-systalkai-circle circle03"></div>
        	<div class="product-systalkai-circle circle04"></div>
      		<div class="product-systalkai-circle circle05"></div>
         	<div class="product-systalkai-circle circle06"></div>
        	<div class="product-systalkai-circle circle07"></div>
         	<div class="product-systalkai-circle circle08"></div>
                <div class="systalkai-box">
                    <div class="mb-10">
                        <div>
                            <h2><s:message code="chat.section02.product.systalkai.box"/></h2>	<!-- 多語系 -->
                        </div>
                    </div>
                    <div class="sub-title">
                        <div>
                            <h4><s:message code="chat.section02.product.systalkai.sub.title"/></h4>	<!-- 多語系 -->
                        </div>
                    </div>
                    <div class="container">

                        <div class="systalkai-inn">
                        	<s:message code="chat.section02.product.systalkai.inn"/>	<!-- 多語系 -->
                        </div>
                    </div>
                    <div class="product-sys-ai"> <img class="img-responsive"
                            src="${contextPath}/images/product-systalkai-768.png"
                            srcset="${contextPath}/images/product-systalkai-768@2x.png 2x" /></div>
                    <div class="product-sys-mob"> <img class="img-responsive"
                            src="${contextPath}/images/product-systalkaimob.png"
                            srcset="${contextPath}/images/product-systalkaimob@2x.png 2x" /></div>
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
                <h2><s:message code="chat.solution.tit"/></h2>	<!-- 多語系 -->
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
                    <div class="headline"><s:message code="chat.card01.headline"/></div>	<!-- 多語系 -->
                    <ul>
                        <li><s:message code="chat.card01.headline.desc01"/></li>	<!-- 多語系 -->
                        <li><s:message code="chat.card01.headline.desc02"/></li>	<!-- 多語系 -->
                        <li><s:message code="chat.card01.headline.desc03"/></li>	<!-- 多語系 -->
                    </ul>
                    <div class="solution-divider">YOU NEED</div>
                    <div class="mt-2 mb-3">
                        <span class="tag">SysTalk ai</span>
                        <span class="icon-add"><img src="${contextPath}/images/icon-add@2x.png" alt=""></span>
                        <span class="tag"><s:message code="chat.card01.headline.desc04"/></span>	<!-- 多語系 -->
                    </div>
                    <div class="img">
                        <img class="img-responsive" src="${contextPath}/images/product-solution-icon01.png"
                            srcset="${contextPath}/images/product-solution-icon01@2x.png 2x" />
                    </div>
                    <div class="desc"><s:message code="chat.card01.headline.desc05"/></div>	<!-- 多語系 -->
                </div>
            </div>
            <div class="item">
                <div class="card">
                    <div class="headline"><s:message code="chat.card02.headline"/></div>	<!-- 多語系 -->
                    <ul>
                        <li><s:message code="chat.card02.headline.desc01"/></li>	<!-- 多語系 -->
                        <li><s:message code="chat.card02.headline.desc02"/></li>	<!-- 多語系 -->
                        <li><s:message code="chat.card02.headline.desc03"/></li>	<!-- 多語系 -->
                    </ul>
                    <div class="solution-divider">YOU NEED</div>
                    <div class="mt-2 mb-3">
                        <span class="tag">SysTalk ai</span>
                        <span class="icon-add"><img src="${contextPath}/images/icon-add@2x.png" alt=""></span>
                        <span class="tag"><s:message code="chat.card02.headline.desc04"/></span>	<!-- 多語系 -->
                    </div>
                    <div class="img">
                        <img class="img-responsive" src="${contextPath}/images/product-solution-icon02.png"
                            srcset="${contextPath}/images/product-solution-icon02@2x.png 2x" />
                    </div>
                    <div class="desc"><s:message code="chat.card02.headline.desc05"/></div>	<!-- 多語系 -->
                </div>
            </div>
            <div class="item">
                <div class="card">
                    <div class="headline"><s:message code="chat.card03.headline"/></div>	<!-- 多語系 -->
                    <ul>
                        <li><s:message code="chat.card03.headline.desc01"/></li>	<!-- 多語系 -->
                        <li><s:message code="chat.card03.headline.desc02"/></li>	<!-- 多語系 -->
                        <li><s:message code="chat.card03.headline.desc03"/></li>	<!-- 多語系 -->
                    </ul>
                    <div class="solution-divider">YOU NEED</div>
                    <div class="mt-2 mb-3">
                        <span class="tag">SysTalk ai</span>
                        <span class="icon-add"><img src="${contextPath}/images/icon-add@2x.png" alt=""></span>
                        <span class="tag"><s:message code="chat.card03.headline.desc04"/></span>	<!-- 多語系 -->
                    </div>
                    <div class="img">
                        <img class="img-responsive" src="${contextPath}/images/product-solution-icon03.png"
                            srcset="${contextPath}/images/product-solution-icon03@2x.png 2x" />
                    </div>
                    <div class="desc"><s:message code="chat.card03.headline.desc05"/></div>	<!-- 多語系 -->
                </div>
            </div>
            <div class="item">
                <div class="card">
                    <div class="headline"><s:message code="chat.card04.headline"/></div>
                    <ul>
                        <li><s:message code="chat.card04.headline.desc01"/></li>
                        <li><s:message code="chat.card04.headline.desc02"/></li>
                        <li><s:message code="chat.card04.headline.desc03"/></li>
                    </ul>
                    <div class="solution-divider">YOU NEED</div>
                    <div class="mt-2 mb-3">
                        <span class="tag">SysTalk ai</span>
                        <span class="icon-add"><img src="${contextPath}/images/icon-add@2x.png" alt=""></span>
                        <span class="tag"><s:message code="chat.card04.headline.desc04"/></span>
                    </div>
                    <div class="img">
                        <img class="img-responsive" src="${contextPath}/images/product-solution-icon04.png"
                            srcset="${contextPath}/images/product-solution-icon04@2x.png 2x" />
                    </div>
                    <div class="desc"><s:message code="chat.card04.headline.desc05"/></div>
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
