<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html>
	<head>
	<style>

	/* 輪播 */
	.carousel-item {
	    width: 100vw;
	    height: 100vh;
	    background-repeat: no-repeat;
	    background-size: cover;
	    background-position: center center;
	    background-position: left center;
	}
	</style>
</head>

<script type="text/javascript">
	var ismMobile = false;
	var bannerIndex = 0;
	
	$(function() {
		/* 首頁輪播圖 */
		var banners = [];
		/* 品牌牆 */
		var logoWalls = [];
		/* 成功案例*/
		var successCases = [];

        var wdth = $(window).width();

		<c:forEach items="${viewForm.bannerList}" var="banner">
			// 一般web 圖片
			var url = '${banner.url}';
			// 設定mobile 圖片
			if(wdth < 992 ) {
				url = '${banner.url2}';
				ismMobile = true;
			}
			if(url != '') {
				var banner = {
						displayTitle : '${banner.displayTitle}',
						url : url,
						displayContent : '${banner.displayContent}',
						date : '${banner.updateDate}'
					};
				banners.push(banner);
			}
		</c:forEach>

		<c:forEach items="${viewForm.logoWallList}" var="logoWall">
			var logoWall = {
				url : '${logoWall.url}'
			};
			logoWalls.push(logoWall);
		</c:forEach>

		<c:forEach items="${viewForm.successCaseList}" var="successCase">
			var successCase = {
				displayTitle: '${successCase.displayTitle}',
				url : '${successCase.url}',
				displayContent : '${successCase.displayContent}'
			};
			successCases.push(successCase);
		</c:forEach>

		var $div = function(classes) {
			return $('<div/>').addClass(classes);
		}

		/* 行銷影片
		var $videoWrapper = $div('col-12');
		var $videoUrl = $('<div/>').html("${viewForm.videoSetting.url}");
		var $content = $('<div/>').addClass('txt').html("${viewForm.videoSetting.content}");
		$videoWrapper.append($videoUrl).append($content);
		$('#videoSet').append($videoWrapper);
		 */

		/* 行銷影片 新作法 */
		$('#videoSet').html("${viewForm.videoSetting.url}");

		/* 設定輪播card*/
		var setSlickItems = function(banners) {
			
			var carrusel = document.querySelector("#carrusel"); 
			carrusel.innerHTML = "";
			
			var carousel_indicators = document.querySelector("#carousel_indicators"); 
			carousel_indicators.innerHTML = ""; 
			
			for (let i = 0; i < banners.length; i++) {
				var banner = banners[i];
				/*var $wrapper = $div('col-12');
				var $cardSlider = $div('card-slider');
				var $top_title = $div('tag').text(banner.title);
				var $articleImg = $div('article-img d-flex align-items-center');
				var $img = $('<img/>').attr('src',banner.url);
				var $title = $div('title').css('width','100%');
				var $content = $('<div/>').addClass('txt').text(banner.content);

				$wrapper.append(
					$cardSlider.append($top_title).append($articleImg).append($title)
				);
				$img.appendTo($articleImg);
				$content.appendTo($title);
				$('.single-item').append($wrapper);	*/

				var carouselItem;
				if (i == bannerIndex) {
					carouselItem = $div('carousel-item item active').css('background-image', 'url(' + banner.url + ')');
					$('#carrusel').append(carouselItem);
					$('#carousel_indicators').append('<li data-target="#carouselSysTalkCaptions" data-slide-to=' + i +' class="active"></li>');
				} else {
					carouselItem = $div('carousel-item').css('background-image', 'url(' + banner.url + ')');
					$('#carrusel').append(carouselItem);
					$('#carousel_indicators').append('<li data-target="#carouselSysTalkCaptions" data-slide-to=' + i +'></li>');
				}

				var inside = $div('carousel-caption d-md-block text');
				carouselItem.append(inside);

				var carouselTitle = $('<h2/>').html(banner.displayTitle)
				inside.append(carouselTitle);

				var carouselContent = $('<p/>').addClass('desc');
				carouselContent.html('<span class="d-inline-block">'
						+ banner.displayContent + '</span>');
				//carouselTitle.append(carouselContent);
				inside.append(carouselContent);

			}
		}
		// 設定輪播
		setSlickItems(banners);

		/*設定品牌牆*/
		var setLogoWalls = function(logoWalls) {
			for (let i = 0; i < logoWalls.length; i++) {
				var logowall = logoWalls[i];
				var $wrapper = $div('col-lg-20 col-md-4 col-sm-6 brand-item');
				var $img = $('<img/>').attr('src', logowall.url);

				$wrapper.append($img);
				$('#logoWallSet').append($wrapper);
			}
		}
		setLogoWalls(logoWalls);

		/*設定成功案例*/
		var setSuccessCases = function(successCases) {
			for (let i = 0; i < successCases.length; i++) {
				var successCase = successCases[i];
				var $wrapper = $div('');
				var $item = $div('testimonials-item');
				var $photo = $div('professional-photo');
				var $img = $('<img/>').attr('src', successCase.url);
				$photo.append($img);
				
				var $title = $div('testimonials-title').html(successCase.displayTitle);
				
				var $content = $div('testimonials-txt').html(successCase.displayContent);

				$item.append($photo).append($title).append($content);
				$wrapper.append($item);
				$('#successCaseSet').append($wrapper);
			}
		}
		setSuccessCases(successCases);


		
        /** 螢幕解析度變更 變換banner */
	    $(window).resize(function() {
			var active = $($("#carouselSysTalkCaptions")).find('.carousel-inner > .active');
			if(active.index() > 0) {
				bannerIndex = active.index();
			} else {
				bannerIndex = 0;
			}
	
	    	var banners = [];
	        var wdth = $(window).width();
	        if(wdth < 992 && !ismMobile) {
	        	ismMobile = true;
	        	
	    		<c:forEach items="${viewForm.bannerList}" var="banner">
		    		<c:if test="${not empty banner.url2}">
		    		var banner = {
		    			displayTitle : '${banner.displayTitle}',
		    			url : '${banner.url2}',
		    			displayContent : '${banner.displayContent}',
		    			date : '${banner.updateDate}'
		    		};
		    		banners.push(banner);
		    		</c:if>
	    		</c:forEach>
	    		
		    	// 設定輪播
		    	setSlickItems(banners);

	        } 
	        if (wdth > 992 && ismMobile) {
	        	ismMobile = false;
	        	
	    		<c:forEach items="${viewForm.bannerList}" var="banner">
	    		var banner = {
	    			displayTitle : '${banner.displayTitle}',
	    			url : '${banner.url}',
	    			displayContent : '${banner.displayContent}',
	    			date : '${banner.updateDate}'
	    		};
	    		banners.push(banner);
	    		</c:forEach>

		    	// 設定輪播
		    	setSlickItems(banners);
  
	        }
	    });
        
        $('.item-box').slick({
            infinite: true,
            speed: 300,
            slidesToShow: 1,
            adaptiveHeight: true,
            autoplay: true,
            autoplaySpeed: 5000
          });

	});

</script>
     <div class="wrapper">   
        <!-- banner -->
        <section class="index_banner d-flex align-items-center">
            <div id="carouselSysTalkCaptions" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators" id="carousel_indicators">

                </ol>
                
                <div class="carousel-inner" id="carrusel">

                </div>
                
                <a class="carousel-control-prev" href="#carouselSysTalkCaptions" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselSysTalkCaptions" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </section>
        

		<section class="SYS-section-01">
            <div class="title">
                <div>
                    <h2>SysTalk.ai</h2>
                </div>
            </div>
            <div class="ai-bg-lt">
                <img alt="" src="${contextPath}/images/bg-SYS-ai-lt@2x.png">
            </div>
            <div class="container">
				<div class="SYS-ai-DESC">
					<s:message code="index.section01.sys.ai.desc"/> <!-- 多語系 -->
                </div>
            </div>
            <div class="ai-bg-rb">
                <img alt="" src="${contextPath}/images/bg-SYS-ai-rb@2x.png">
            </div>
        </section>

		<section class="SYS-section-02">
            <!-- 頁籤 -->
            <!-- <ul class="nav nav-pills justify-content-center mb-3" id="pills-tab" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" id="pills-tab-01" data-toggle="pill" href="#pills-01" role="tab" aria-controls="pills-01" aria-selected="true">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="pills-tab-02" data-toggle="pill" href="#pills-02" role="tab" aria-controls="pills-02" aria-selected="false">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="pills-tab-03" data-toggle="pill" href="#pills-03" role="tab" aria-controls="pills-03" aria-selected="false">Contact</a>
                </li>img_systalk_chat.pngimg_systalk_chat.png
            </ul> -->
            
            <div class="tab-content" id="pills-tabContent">
                <!-- 雙腦+一流程：核心模組運行模式 -->
                <div class="tab-pane fade show active" id="pills-01" role="tabpanel" aria-labelledby="pills-tab-01">
                    <div class="SYS-module-box">
                    	<div class="img_systalkChat"><img src="${contextPath}/images/img_systalk_chat.png" alt=""></div>
                        <div class="title mb-10">
                            <div>
                          		<h1><s:message code="index.section02.title.mb10"/></h1> <!-- 多語系 -->
                            </div>
                        </div>
                        <div class="sub-title">
                            <div>
                                <h4><s:message code="index.section02.sub.title"/></h4> <!-- 多語系 -->
                            </div>
                        </div>
                        <div class="container">
                            <div class="row justify-content-center d-none d-mb-flex">
                                <button class="btn btn-collapse" href="#"><span class="btn-collapse-sub"><img class="icon-collapse" alt="" src="${contextPath}/images/icon-minus@2x.png"></span><span class="collapse-txt">收合</span></button>
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
                                    <img class="img-about-flow" alt="" src="${contextPath}/images/img-about-flow@2x.png">
                                    <!-- 手機版 出現小圖 -->
                                    <img class="img-about-flow-m" alt="" src="${contextPath}/images/img-about-flow-m@2x.png">
                                    <!-- FLOW -->
                                    <div class="about-flow-box about-FLOW wow fadeInRight" style="visibility: visible; animation-name: fadeInRight;">
                                        <div class="flow-title"><img alt="" src="${contextPath}/images/img-flowTitle-FLOW@2x.png"></div>
                                        <div class="flow-subtitle"><s:message code="index.section02.flow.subtitle"/></div> <!-- 多語系 -->
                                        <div class="flow-DESC">
                                            <ul>
                                                <li><s:message code="index.section02.flow.desc01"/></li> <!-- 多語系 -->
                                                <li><span class="c-0076C5"><s:message code="index.section02.flow.desc02"/></li> <!-- 多語系 -->
                                                <li>以 <span class="c-0076C5"><s:message code="index.section02.flow.desc03"/></li> <!-- 多語系 -->
                                            </ul>
                                        </div>
                                    </div>
                                    <!-- NLU -->
                                    <div class="about-flow-box about-NLU wow fadeInDown" style="visibility: visible; animation-name: fadeInDown; animation-delay: 0.6s;" data-wow-delay=".6s">
                                        <div class="NLU-title"><img alt="" src="${contextPath}/images/img-flowTitle-NLU@2x.png"></div>
                                        <div class="NLU-subtitle"><s:message code="index.section02.nlu.subtitle"/></div>
                                        <div class="NLU-DESC">
                                            <ul>
                                                <li><span class="c-0076C5"><s:message code="index.section02.nlu.desc01"/></li> <!-- 多語系 -->
                                                <li><span class="c-0076C5"><s:message code="index.section02.nlu.desc02"/></li> <!-- 多語系 -->
                                                <li><s:message code="index.section02.nlu.desc03"/></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <!-- FAQ -->
                                    <div class="about-flow-box about-FAQ wow fadeInLeft" style="visibility: visible; animation-name: fadeInLeft; animation-delay: 1.5s;" data-wow-delay="1.5s">
                                        <div class="FAQ-title"><img alt="" src="${contextPath}/images/img-flowTitle-FAQ@2x.png"></div>
                                        <div class="FAQ-subtitle"><s:message code="index.section02.faq.subtitle"/></div>
                                        <div class="FAQ-DESC">
                                            <ul>
                                                <li><s:message code="index.section02.faq.desc01"/></li> <!-- 多語系 -->
                                                <li><s:message code="index.section02.faq.desc02"/></li> <!-- 多語系 -->
                                                <li><s:message code="index.section02.faq.desc03"/></li> <!-- 多語系 -->
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 雙腦+一流程：核心模組運行模式 end -->
                <!-- 模組2 -->
                <!-- <div class="tab-pane fade show" id="pills-02" role="tabpanel" aria-labelledby="pills-tab-02">
                    <div class="SysTalk SYS-module-box">
                        <div class="title">
                            <div>
                                <h2>模組2</h2>
                            </div>
                        </div>
                        <div class="sub-title">
                            <div>
                                <h4>HIHI</h4>
                            </div>
                        </div>
                        <div class="container">

                        </div>
                    </div>
                </div> -->
                <!-- 模組2 end -->
                <!-- 模組3 -->
                <!-- <div class="tab-pane fade show" id="pills-03" role="tabpanel" aria-labelledby="pills-tab-03">
                    <div class="SysTalk SYS-module-box">
                        <div class="title">
                            <div>
                                <h2>模組3</h2>
                            </div>
                        </div>
                        <div class="sub-title">
                            <div>
                                <h4>HIHI</h4>
                            </div>
                        </div>
                        <div class="container">

                        </div>
                    </div>
                </div> -->
                <!-- 模組3 end -->
            </div>
        </section>
        
        <!-- section-3 -->
        <section class="SYS-section-03">
            <div class="title-white">
                <div>
                    <!-- 大版文字呈現 -->
                    <h2 class="c-FFF d-mb-none"><s:message code="index.section03.title.dmb.none"/></h2>  <!-- 多語系 -->
                    <!-- 小版文字呈現 -->
                    <h2 class="c-FFF d-none d-mb-block"><s:message code="index.section03.title.dmb.block"/></h2>  <!-- 多語系 -->
                </div>
            </div>
            <div class="container">
                <div class="architecture-box">
                    <!-- 終端使用者區塊 -->
                    <div class="endUser-block">
                        <div class="endUser wow bounceIn">
                            <div class="icon-endUser"><img src="${contextPath}/images/icon-endUser@2x.png" alt=""></div>
                            <div class="architecture-txt"><s:message code="index.section03.architecture.enduser"/></div> <!-- 多語系 -->
                        </div>
                        <div class="endUser-phone wow fadeInRight">
                            <div class="icon-endUser-phone"><img src="${contextPath}/images/icon-endUser-phone@2x.png" alt=""></div>
                        </div>
                        <div class="endUser-pad wow fadeInRight">
                            <div class="icon-endUser-pad"><img src="${contextPath}/images/icon-endUser-pad@2x.png" alt=""></div>
                        </div>
                        <div class="endUser-computer wow fadeInRight">
                            <div class="icon-endUser-computer"><img src="${contextPath}/images/icon-endUser-computer@2x.png" alt=""></div>
                        </div>
                    </div>
                    <!-- 終端使用者區塊 end -->
                    <!-- STT語音轉文字 -->
                    <div class="Stt-block wow bounceIn" data-wow-delay=".4s" data-wow-offset="600">
                        <div class="icon-Stt-voice"><img src="${contextPath}/images/icon-Stt-voice@2x.png" alt=""></div>
                        <div class="icon-Stt"><img src="${contextPath}/images/icon-STT@2x.png" alt=""></div>
                        <div class="architecture-txt"><s:message code="index.section03.architecture.voice01"/></div> <!-- 多語系 -->
                        <div class="architecture-txt-m"><s:message code="index.section03.architecture.voice02"/></div> <!-- 多語系 -->
                    </div>
                    <!-- STT語音轉文字 end -->
                    <!-- AI大腦演算推論意圖 -->
                    <div class="AiBrain-block wow bounceIn" data-wow-delay="1s" data-wow-offset="300">
                        <div class="icon-AiBrain-chat"><img src="${contextPath}/images/icon-AiBrain-chat@2x.png" alt=""></div>
                        <div class="icon-AiBrain"><img src="${contextPath}/images/icon-AiBrain@2x.png" alt=""></div>
                        <div class="architecture-txt-w"><s:message code="index.section03.architecture.brain01"/></b></div> <!-- 多語系 -->
                        <div class="architecture-txt-w-m"><s:message code="index.section03.architecture.brain02"/></b></div> <!-- 多語系 -->
                    </div>
                    <!-- AI大腦演算推論意圖 end -->
                    <!-- 進入企業系統提供深度服務 -->
                    <div class="businessService-block">
                        <div class="icon-business-service"><img src="${contextPath}/images/icon-business-service@2x.png" alt=""></div>
                        <div class="architecture-txt-m"><s:message code="index.section03.architecture.business.service"/></div> <!-- 多語系 -->
                    </div>
                    <!-- 進入企業系統提供深度服務 end -->
                    <!-- TTS文字轉語音 -->
                    <div class="TTS-block wow bounceIn" data-wow-delay="1.2s" data-wow-offset="150">
                        <div class="icon-Stt-voice"><img src="${contextPath}/images/icon-Stt-voice@2x.png" alt=""></div>
                        <div class="icon-Stt"><img src="${contextPath}/images/icon-TTS@2x.png" alt=""></div>
                 		<div class="architecture-txt"><s:message code="index.section03.architecture.stt.voice01"/></div> <!-- 多語系 -->
                        <div class="architecture-txt-m"><s:message code="index.section03.architecture.stt.voice02"/></div> <!-- 多語系 -->
                    </div>
                    <!-- TTS文字轉語音 end -->
                    <!-- 非參與執行企業內部 -->
                    <div class="BusinessFlow-block wow bounceIn" data-wow-delay="1.6s" data-wow-offset="150">
                        <div class="icon-Stt"><img src="${contextPath}/images/icon-businessFlow@2x.png" alt=""></div>
                        <div class="architecture-txt"><s:message code="index.section03.architecture.business.flow"/></div> <!-- 多語系 -->
                    </div>
                    <!-- 非參與執行企業內部 end -->
                    <!-- TSMP -->
                    <div class="TSMP-block wow bounceIn" data-wow-delay="1.6s" data-wow-offset="600">
                        <div class="icon-Stt"><img src="${contextPath}/images/icon-TSMP@2x.png" alt=""></div>
                        <div class="architecture-txt"><s:message code="index.section03.architecture.tsmp"/></div> <!-- 多語系 -->
                    </div>
                    <!-- TSMP end -->
                    <!-- DB -->
                    <div class="DB-block wow bounceIn" data-wow-delay="2.4s" data-wow-offset="300">
                        <div class="d-flex align-items-center justify-content-center">
                            <div class="icon-DB"><img src="${contextPath}/images/icon-DB@2x.png" alt=""></div>
                            <div class="architecture-txt"><s:message code="index.section03.architecture.db"/></div> <!-- 多語系 -->
                        </div>
                    </div>
                    <!-- DB end -->
                </div>
            </div>
        </section>
        <!-- section-3 end -->
        
        <section class="SYS-section-04">
            <div class="ai-bg-lt">
                <img alt="" src="${contextPath}/images/bg-SYS-ai-lt@2x.png">
            </div>
            <div class="container">
                <div class="row d-flex align-items-center">
                    <div class="col-lg-4 col-md-12 col-sm-12">
                        <div class="video-title">${viewForm.videoSetting.displayTitle}</div>
                        <div class="video-DESC">${viewForm.videoSetting.displayContent}</div>
                    </div>
                    <div class="col-lg-8 col-md-12 col-sm-12">
                        <div class="video-box">
                            <div class="bg-video"><img alt="" src="${contextPath}/images/bg-video@2x.png"></div>

							<!-- 行銷影片 -->
                            <div id="videoSet" name="videoSet">			
							</div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="ai-bg-rb">
                <img alt="" src="${contextPath}/images/bg-SYS-ai-rb@2x.png">
            </div>
        </section>
        
        <!-- section-5 -->
        <section class="SYS-section-05" style="">
            <div class="container">
                <!-- 品牌牆 -->
                <div class="row align-items-center justify-content-center" id="logoWallSet">
					<!-- 品牌牆設定 -->
                </div>
            </div>
            <!-- 品牌牆 end -->
            <div class="container customer-testimonials">
                <!-- 客戶證言 -->
                <!-- 此區域切版圖與文字為測試使用，非正式內內容 -->
                <div class="item-box" id="successCaseSet">
					<!-- 客戶證言設定 -->
                </div>
                <!-- 客戶證言 end -->
            </div>
        </section>
        <!-- section-5 end -->

       </div>
       <!-- 
       	<section class="category-article mt-3">
            <div class="container">

                <div class="box">
                    <div class="row">
                        <div class="col-12" style="text-align:center">
                            <div class="title">行銷影片</div>
                            <div class="txt">
								<div id="videoSet" name="videoSet">			
							
								</div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </section>
       
		
		<section class="category-article mt-3">
            <div class="container">

                <div class="box">
                    <div class="row">
                        <div class="col-12" style="text-align:center">
                            <div class="title">logoWall</div>
                            <div class="txt">
								<div id="logoWallSet" name="logoWallSet">			
							
								</div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </section>
		
		<section class="category-article mt-3">
            <div class="container">

                <div class="box">
                    <div class="row">
                        <div class="col-12" style="text-align:center">
                            <div class="title">成功案例</div>
                            <div class="txt">
								<div id="successCaseSet" name="successCaseSet">			
							
								</div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </section>

    </div>
	 -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
    <!-- 2019.10.21 品牌牆輪播js -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/slick.min.js"></script>
 </html>
