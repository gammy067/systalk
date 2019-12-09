<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
						title : '${banner.title}',
						url : url,
						content : '${banner.content}',
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
				title: '${successCase.title}',
				url : '${successCase.url}',
				content : '${successCase.content}'
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

				var carouselTitle = $('<h2/>').html(banner.title)
				inside.append(carouselTitle);

				var carouselContent = $('<p/>').addClass('desc');
				carouselContent.html('<span class="d-inline-block">'
						+ banner.content + '</span>');
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
				
				var $title = $div('testimonials-title').html(successCase.title);
				
				var $content = $div('testimonials-txt').html(successCase.content);

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
		    			title : '${banner.title}',
		    			url : '${banner.url2}',
		    			content : '${banner.content}',
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
	    			title : '${banner.title}',
	    			url : '${banner.url}',
	    			content : '${banner.content}',
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
				<div class="SYS-ai-DESC">SysTalk.ai 為昕力資訊專屬 AI 產品品牌，致力於將 AI 成為全球性數位勞動力的主角以「Empower your
                    work」為概念，SysTalk.ai 期盼打造人機共工的工作型態，其產品藍圖專注「Chat、Voice、Auto」三大領域，在台灣集結研發團隊，致力研發演算法，提供企業交談式解決方案，從溝通開始，讓服務變得更便利。
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
                                    <div class="about-flow-box about-NLU wow fadeInDown" style="visibility: visible; animation-name: fadeInDown; animation-delay: 0.6s;" data-wow-delay=".6s">
                                        <div class="NLU-title"><img alt="" src="${contextPath}/images/img-flowTitle-NLU@2x.png"></div>
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
                                    <div class="about-flow-box about-FAQ wow fadeInLeft" style="visibility: visible; animation-name: fadeInLeft; animation-delay: 1.5s;" data-wow-delay="1.5s">
                                        <div class="FAQ-title"><img alt="" src="${contextPath}/images/img-flowTitle-FAQ@2x.png"></div>
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
                    <h2 class="c-FFF d-mb-none">新一代交談式服務運行架構</h2>
                    <!-- 小版文字呈現 -->
                    <h2 class="c-FFF d-none d-mb-block">新一代交談式服務<br>運行架構</h2>
                </div>
            </div>
            <div class="container">
                <div class="architecture-box">
                    <!-- 終端使用者區塊 -->
                    <div class="endUser-block">
                        <div class="endUser wow bounceIn">
                            <div class="icon-endUser"><img src="${contextPath}/images/icon-endUser@2x.png" alt=""></div>
                            <div class="architecture-txt">終端使用者</div>
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
                        <div class="architecture-txt">STT語音轉文字<br>高辨識速度與正確率</div>
                        <div class="architecture-txt-m">使用者語音轉文字</div>
                    </div>
                    <!-- STT語音轉文字 end -->
                    <!-- AI大腦演算推論意圖 -->
                    <div class="AiBrain-block wow bounceIn" data-wow-delay="1s" data-wow-offset="300">
                        <div class="icon-AiBrain-chat"><img src="${contextPath}/images/icon-AiBrain-chat@2x.png" alt=""></div>
                        <div class="icon-AiBrain"><img src="${contextPath}/images/icon-AiBrain@2x.png" alt=""></div>
                        <div class="architecture-txt-w">AI大腦演算推論意圖<br><b>取得精確回答</b></div>
                        <div class="architecture-txt-w-m">進入SysTalk.Chat<br><b>大腦比對解答</b></div>
                    </div>
                    <!-- AI大腦演算推論意圖 end -->
                    <!-- 進入企業系統提供深度服務 -->
                    <div class="businessService-block">
                        <div class="icon-business-service"><img src="${contextPath}/images/icon-business-service@2x.png" alt=""></div>
                        <div class="architecture-txt-m">進入企業系統提供深度服務</div>
                    </div>
                    <!-- 進入企業系統提供深度服務 end -->
                    <!-- TTS文字轉語音 -->
                    <div class="TTS-block wow bounceIn" data-wow-delay="1.2s" data-wow-offset="150">
                        <div class="icon-Stt-voice"><img src="${contextPath}/images/icon-Stt-voice@2x.png" alt=""></div>
                        <div class="icon-Stt"><img src="${contextPath}/images/icon-TTS@2x.png" alt=""></div>
                 		<div class="architecture-txt">TTS 文字轉語音<br>以在地口音回應</div>
                        <div class="architecture-txt-m">回傳服務結果/答案給使用者</div>
                    </div>
                    <!-- TTS文字轉語音 end -->
                    <!-- 非參與執行企業內部 -->
                    <div class="BusinessFlow-block wow bounceIn" data-wow-delay="1.6s" data-wow-offset="150">
                        <div class="icon-Stt"><img src="${contextPath}/images/icon-businessFlow@2x.png" alt=""></div>
                        <div class="architecture-txt">非參與執行企業內部<br>業務流程代理服務</div>
                    </div>
                    <!-- 非參與執行企業內部 end -->
                    <!-- TSMP -->
                    <div class="TSMP-block wow bounceIn" data-wow-delay="1.6s" data-wow-offset="600">
                        <div class="icon-Stt"><img src="${contextPath}/images/icon-TSMP@2x.png" alt=""></div>
                        <div class="architecture-txt">服務管理及 API 管理平台</div>
                    </div>
                    <!-- TSMP end -->
                    <!-- DB -->
                    <div class="DB-block wow bounceIn" data-wow-delay="2.4s" data-wow-offset="300">
                        <div class="d-flex align-items-center justify-content-center">
                            <div class="icon-DB"><img src="${contextPath}/images/icon-DB@2x.png" alt=""></div>
                            <div class="architecture-txt">與現有系統或 DB 整合</div>
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
                        <div class="video-title">${viewForm.videoSetting.title}</div>
                        <div class="video-DESC">${viewForm.videoSetting.content}</div>
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
        <section class="SYS-section-05">
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
