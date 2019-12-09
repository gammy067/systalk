<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<script type="text/javascript">

	$(function () {
		/** 暫時移除跳頁事件
		$(window).bind('beforeunload',function(e){
			return '您確定離開此頁面嗎？請記得存檔！';
		});
		*/
		
		$("#videoUrl").on("change", function (event) {
			var str = $('#videoUrl').val();
			if(str.indexOf("ifram") > 0) {
				$("#previewVideo").html($('#videoUrl').val());
			} else {
				$("#previewVideo").text('影片預覽');
			}
		});

		/**
		$('#pushingDate').datepicker({
			format: 'yyyy-mm-dd',
			autoclose: true,
			startDate: '0',
			todayHighlight: true
		});
		*/
	});

	function saveAction() {
		$(window).unbind('beforeunload');
		var form = $('#editVideoForm');
		
        form.attr('method', 'post');
		form.attr('action', '${contextPath}/backend/doVideoSaveAction');
		form.submit();
	}
	
	function doGoBack() {
		window.location.replace('${contextPath}/backend/videoHome');
	}
	</script>

</head>

<body class="index-bg menu-bg">
    <!--main 中間內容 Start-->
    <main>
        <div class="d-table-cell mx-auto align-middle login-content">
            <div class="about-box row mx-auto">
                <div class="w-100">
                    <div class="title">
                        <div>
                            <h2 class="lg-title">行銷影片設定</h2>
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                	<div class="setting input-type mt-4">
                        <div class="video-img">
                            <!-- 嵌入影片 -->
                            <div class="video-file" >
                             <c:choose>
								<c:when test="${not empty viewForm.settingForm.url}">
                          			<p id="previewVideo">${viewForm.settingForm.url}</p>
                     			</c:when>
                     			<c:otherwise>
                       				<p id="previewVideo">影片預覽</p>
               					</c:otherwise>
                              </c:choose>
                            </div>
                            <!-- 嵌入影片 -->

                        </div>
                        <sf:form name="editVideoForm" id="editVideoForm" modelAttribute="viewForm" autocomplete="off" >
                        	<sf:input type="hidden" id="seq" name="seq" path="settingForm.seq" />
                        
	                        <div class="setting-inform mt-4">
	                        	<sf:errors path="settingForm.url" cssClass="text-red" />
	                            <div class="setting-line d-flex">
	                            	<span class="text-grey">嵌入影片連結</span>
	                                <sf:textarea class="textarea-style" placeholder="請輸入影片連結" id="videoUrl" name="videoUrl" path="settingForm.url"/>
	                            </div>
	                            
								<sf:errors path="settingForm.title" cssClass="text-red" />
	                            <div class="setting-line"><span class="text-grey">影片標題</span>
	                            	<sf:input type="text" class="mx-auto" path="settingForm.title" placeholder="請輸入標題(建議字數40字以內)" onkeydown="if(event.keyCode==13)return false;" />
	                            </div>
	                            
		                        <sf:errors path="settingForm.titleEn" cssClass="text-red" />
	                            <div class="setting-line"><span class="text-grey">英文影片標題</span>
	                                <sf:input type="text" class="mx-auto " path="settingForm.titleEn" placeholder="請輸入標題(建議字數40字以內)" />
								</div>

	                            <sf:errors path="settingForm.content" cssClass="text-red" />
	                            <div class="setting-line d-flex"><span class="text-grey">影片內文</span>
	                            	<sf:textarea class="textarea-style" placeholder="請輸入內文(建議字數100字以內)"  path="settingForm.content"/>
	                            </div>
	                            
	                                                        
	                            <sf:errors path="settingForm.contentEn" cssClass="text-red" />
	                            <div class="setting-line"><span class="text-grey">英文影片內文</span>
	                                <sf:textarea class="textarea-style" placeholder="請輸入內文(建議字數100字以內)"  path="settingForm.contentEn" />
	                            </div>
	                        </div>
	                       

                        </sf:form>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="text-center step-button mt-4">
                        <!-- 上一頁-->
                        <a href="#" class="btn line-blue" data-toggle="modal" data-target="#noSave">上一頁</a>
                        <!-- 上一頁 END-->

                        <a href="#" class="btn full-blue" onclick="saveAction();">確認儲存</a>

                    </div>
                </div>

            </div>
        </div>
    </main>

	<!-- 設定textarea高度 -->
    <script type="text/javascript">
        $(".textarea-style").css("overflow", "hidden").bind("keydown keyup", function () {
            $(this).height('0px').height($(this).prop("scrollHeight") + "px");
        }).keydown();

    </script>
    
</body>
</html>
