<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<style>
	.text-red {
 	 color: #CD5F50;
}
</style>
<script type="text/javascript">
$(function() {
	/** 舊方法
        $('button[type=submit]').click(function(e) {
        	e.preventDefault();
        	
    		//Remove all errors
    		$('input').next().remove();
    		$('textarea').next().remove();
		
    		$.ajax({
    				url: '${contextPath}/doSendEmailAction',
    				type: 'POST',

    				data: $('form[name=sendEmailForm]').serialize(),
    				success: function (data) {
    					if(data.hasException) {
    						alert("發信系統異常請稍後再試");
							window.location.reload();
    					} else {
    						if(data.validate){
        						window.location.reload();
        						
        					// 欄位驗證錯誤
        					} else {
        						//Set error messages
        						$.each(data.errorMessages,function(key,value){
        							$('[name='+key+']').after('<span class="text-red">'+value+'</span>');
        						});
        					}
    					}
    				}
    		});
    	});
	*/
});

function doSendEmailAction() {
	$('#sendEmailForm input').next().remove();
	$('#sendEmailForm textarea').next().remove();

	$.ajax({
			url: '${contextPath}/doSendEmailAction',
			type: 'POST',

			data: $('#sendEmailForm').serialize(),
			success: function (data) {
				if(data.hasException) {
					alert("發信系統異常請稍後再試");
					window.location.reload();
				} else {
					if(data.validate){
						window.location.reload();
						
					// 欄位驗證錯誤
					} else {
						//Set error messages
						$.each(data.errorMessages,function(key,value){
							$('#sendEmailForm input[name='+key+']').after('<span class="text-red">'+value+'</span>');
							$('#sendEmailForm textarea[name='+key+']').after('<span class="text-red">'+value+'</span>');
						});
					}
				}
			}
	});
}
</script>

<section class="footer-box" id="contact">
    <div class="container">
        <div class=" row">
            <div class="col-md-5 col-sm-12">
                <div class="footer-logo"><span><img src="${contextPath}/images/logo-white@2x.png"></span></div>
                <div class="footer-DESC">
                    SysTalk 源自於 ThinkPower，就像一個成長中的小男孩，面對外部世界充滿好奇心，積極學習、不斷探索，創造 AI 解決方案，讓世界變得更有趣。腦力、活力、同感力讓我們一起以腦力優化技術瓶頸、以活力面對新舊挑戰、以同感力了解使用情境成為一個時時刻刻為您著想的 AI 團隊。
                </div>
                <div class="d-flex">
                    <div class="icon-footer-link"><a href="https://www.thinkpower.com.tw/" target="_blank"><img
                                src="${contextPath}/images/icon-link-TP@2x.png" alt=""></a></div>
                    <div class="icon-footer-link"><a href="https://www.instagram.com/systalk/?igshid=rqup9dgxe90o"
                            target="_blank"><img src="${contextPath}/images/icon-link-IG@2x.png" alt=""></a></div>
                    <div class="icon-footer-link"><a
                            href="https://www.facebook.com/SysTalkai-AI-murmur-talk-961153060884919/"
                            target="_blank"><img src="${contextPath}/images/icon-link-FB@2x.png" alt=""></a></div>
                    <div class="icon-footer-link"><a href="https://medium.com/systalk-ai" target="_blank"><img
                                src="${contextPath}/images/icon-link-MBlog@2x.png" alt=""></a></div>
                </div>
            </div>
            <div class="col-md-7 col-sm-12">
                <div class="footer-input">
                    <form id="sendEmailForm" name="sendEmailForm">
                        <div class="row">
                            <div class="col-md-6 col-sm-12 mb-3">
                            	<input type="text" id="name" name="name" class="form-control" placeholder="請輸入姓名" required>
                            </div>
                            <div class="col-md-6 col-sm-12 mb-3">
                            	<input type="tel" id="phone" name="phone" class="form-control" placeholder="請輸入電話">
                            </div>
                        </div>
                        <div class="form-group">
                        	<input type="email" id="email" name="email" class="form-control" placeholder="請輸入 E-mail">
                        </div>
                        <div class="form-group">
                        	<textarea class="form-control" id="content" name="content" rows="3" placeholder="請留言"></textarea>
                        </div>

                        <button class="btn btn-warning btn-lg btn-block" type="button" onclick="doSendEmailAction()" >送出</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="footer-info">
            <div class="container">
                <div class="row justify-content-center justify-content-start-m">
                    <div class="col-lg-4 col-mb-12 col-sm-12">
                        <div class="d-flex align-items-center justify-content-center"><img class="icon-info-address"
                                src="${contextPath}/images/icon-footer-home@2x.png" alt="">
                            <div class="info-address">114 台北市內湖區瑞光路441號4樓</div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-mb-6 col-sm-6">
                        <div class="d-flex align-items-center justify-content-center"><img class="icon-info-email"
                                src="${contextPath}/images/icon-footer-mail@2x.png" alt="">
                            <div class="info-email">service@thinkpower.com.tw</div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-mb-6 col-sm-6">
                        <div class="d-flex align-items-center justify-content-center"><img class="icon-info-tel"
                                src="${contextPath}/images/icon-footer-tel@2x.png" alt="">
                            <div class="info-tel">02) 2545-7020</div>
                        </div>
                    </div>
                </div>
                <div class="divider-879097 mt-10-m"></div>
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="copy-right">Copyright©昕力資訊股份有限公司 ThinkPower Information Corporation</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


<div id="GoTop">
	<div class="btn-top"><span><img src="${contextPath}/images/btn-top@2x.png" alt=""></span></div>
</div>