<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <!--date-->
    <link rel="stylesheet" href="${contextPath}/backendStatic/build/css/bootstrap-datetimepicker-standalone.css">
    <link rel="stylesheet" href="${contextPath}/backendStatic/build/css/bootstrap-datetimepicker.min.css">

<style>
	/** dialog 控制 css可能有互衝 */
	.modal-backdrop.fade {
	    opacity: 0;
	}
	
	.fade.in {
	    opacity: 1;
	}
	
	.modal-backdrop {
	    background-color: unset;
	}
</style>
    <script type="text/javascript">
        $(function () {

    		/** 暫時移除跳頁事件
    		$(window).bind('beforeunload',function(e){
    			return '您確定離開此頁面嗎？請記得存檔！';
    		});
    		*/

            $("#imageUploadInput").on("change", function (event) {
                var file = $(this).get(0).files[0];
                //上傳圖片不可大於10mb
                if (file && (file.size / 1024 / 1024 > 10)) {
                    $('#showFileErr').addClass('is-invalid');
                    $('#saveBtn').prop('disabled', true);
                    return false;
                } else {
                    $('#showFileErr').removeClass('is-invalid');
                    $('#saveBtn').prop('disabled', false);
                }

                if (/\.(jpe?g|png|gif)$/i.test(file.name)) {
                    var reader = new FileReader();
                    reader.onload = function () {
                        var image = new Image();
                        image.src = reader.result;
                        $('#previewImage').attr('src', reader.result);
                        //$('#previewImage').centerImage();

                        $('#imageUrl').val(reader.result);

                        /* setTimeout(function(){
                            var srcWidth = $('#previewImage').width();
                            var srcHeight = $('#previewImage').height();
                            var ratio = calculateAspectRatioFit(srcWidth,srcHeight,300,150);
                            $('#previewImage').width(ratio.width).height(ratio.height);
                        }) */
                    };
                    reader.readAsDataURL(file);
                    
                    $("#UploadSuccess").modal('show');

                } else {
                	$("#UploadFail").modal('show');
                }
            });
            
            $("#imageUploadInput2").on("change", function (event) {
                var file = $(this).get(0).files[0];
                //上傳圖片不可大於10mb
                if (file && (file.size / 1024 / 1024 > 10)) {
                    $('#showFileErr2').addClass('is-invalid');
                    $('#saveBtn').prop('disabled', true);
                    return false;
                } else {
                    $('#showFileErr2').removeClass('is-invalid');
                    $('#saveBtn').prop('disabled', false);
                }

                if (/\.(jpe?g|png|gif)$/i.test(file.name)) {
                    var reader = new FileReader();
                    reader.onload = function () {
                        var image = new Image();
                        image.src = reader.result;
                        $('#previewImage2').attr('src', reader.result);
                        //$('#previewImage').centerImage();

                        $('#imageUrl2').val(reader.result);

                        /* setTimeout(function(){
                            var srcWidth = $('#previewImage').width();
                            var srcHeight = $('#previewImage').height();
                            var ratio = calculateAspectRatioFit(srcWidth,srcHeight,300,150);
                            $('#previewImage').width(ratio.width).height(ratio.height);
                        }) */
                    };
                    reader.readAsDataURL(file);
                }
            });

            /*
            $('#pushingDate').datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                startDate: '0',
                todayHighlight: true
            });*/

            $('.datetimepicker').datetimepicker({
                format: 'YYYY/MM/DD'
            });
            
            // status = 立即上架 disabled 日期元件
            if("${viewForm.uploadFileForm.status}" == "1") {
            	$('.datetimepicker').prop("disabled", "disabled");
            }

            // radioButton切換事件
            $("input[type=radio][name='uploadFileForm.status']").change(function () {
                if ($(this).val() == "0") {
                	$('.datetimepicker').prop("disabled", "");
                } else {
                	$('.datetimepicker').prop("disabled", "disabled");
                }
            });
        });


        function saveAction() {
            $(window).unbind('beforeunload');
            var form = $('#editBannerForm');

            $('#fileName').val($('#imageUploadInput').val());

            form.attr('method', 'post');
            form.attr('action', '${contextPath}/backend/doBannerSettingSaveAction');
            form.submit();
        }
        
    	function doGoBack() {
    		window.location.replace('${contextPath}/backend/bannerHome');
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
                            <h2 class="lg-title">首頁輪播設定</h2>
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
					<div class="setting input-type mt-4">
                        <sf:form name="editBannerForm" id="editBannerForm" modelAttribute="viewForm" autocomplete="off" enctype="multipart/form-data">

                            <sf:input type="hidden" path="uploadFileForm.seq" />
                            <sf:input type="hidden" id="fileName" name="fileName" path="uploadFileForm.fileName" />
                            <sf:input type="hidden" id="imageUrl" name="imageUrl" path="imageUrl" />
                            <sf:input type="hidden" id="imageUrl2" name="imageUrl2" path="imageUrl2" />

                        <div class="video-img">
                            <!-- 電腦版banner -->
                            <div class="upload d-inline-block mx-4">
                                <!-- 圖片未上傳 -->
                                <sf:errors path="uploadFileForm.fileName" cssClass="text-red" />
                                 <div class="img-box">
                                    <sf:input class="choose-file" type="file" path="uploadFileForm.file" name="imageUploadInput" id="imageUploadInput" />
                                    <label for="imageUploadInput" class="upload-file" data-text="filename">
                                        <div class="btn full-yellow"><i class="icons-icon-pic i-icon mr-1"></i>選擇檔案
                                        </div>
                                        <p>電腦/平板<br>圖片建議尺寸2880*1800px</p>
                                    </label>
                                    <div id="showFileErr" class="form-control" style="display:none;">
                                    </div>
                                    <div class="invalid-feedback">上傳的圖片不可大於10MB</div>
                                </div>
                                <!-- 圖片未上傳 end-->
                                <!-- 圖片上傳 -->

                                <div class="">
                                    <div class="upload-img img-web">
                                        <c:choose>
                                            <c:when test="${not empty viewForm.imageUrl}">
                                                <img class="img-fluid" id="previewImage" src="${viewForm.imageUrl}" />
                                            </c:when>
                                            <c:otherwise>
                                                <img class="img-fluid" id="previewImage" src="${contextPath}/backendStatic/images/banner.jpg" alt="">
                                            </c:otherwise>
                                        </c:choose>

                                        <!-- 09.25 暫時移除 不需要取消 
                                        	<i class="icons-img-cancel i-icon mr-1"></i>
                                        -->
                                    </div>
                                </div>
                                <!-- 圖片上傳 end-->
                            </div>
                            <!-- 電腦版banner end -->

                            <!-- 手機版banner -->
                            <div class="upload d-inline-block mx-4 mob-img">
                                <!-- 圖片未上傳 -->
                                <div class="img-box">
                                    <sf:input class="choose-file" type="file" path="uploadFileForm.file2" name="imageUploadInput2" id="imageUploadInput2" />
                                    <label for="imageUploadInput2" class="upload-file" data-text="filename">
                                        <div class="btn full-yellow"><i class="icons-icon-pic i-icon mr-1"></i>選擇檔案
                                        </div>
                                        <p>手機圖片建議尺寸750*1334px</p>
                                    </label>
                                </div>
                                <!-- 圖片未上傳 end-->
                                <!-- 圖片上傳 -->
                                <div class="">
                                    <div class="upload-img img-mob">

                                        <c:choose>
                                            <c:when test="${not empty viewForm.imageUrl2}">
                                                <img class="img-fluid" id="previewImage2" src="${viewForm.imageUrl2}" />
                                            </c:when>
                                            <c:otherwise>
                                                <img class="img-fluid" id="previewImage2" src="${contextPath}/backendStatic/images/banner-web.jpg" alt="">
                                            </c:otherwise>
                                        </c:choose>

                                        <!-- 09.25 暫時移除 不需要取消 
                                        	<i class="icons-img-cancel i-icon mr-1"></i>
                                        -->
                                    </div>
                                </div>
                                <!-- 圖片未上傳 end-->
                            </div>
                            <!-- 手機版banner end -->
                        </div>

                        <div class="setting-inform center-upload">
                        	<sf:errors path="uploadFileForm.title" cssClass="text-red" />
                            <div class="setting-line"><span class="text-grey">標題</span>
                                <sf:input type="text" class="mx-auto " path="uploadFileForm.title" placeholder="請輸入標題(建議字數40字以內)" />
                            </div>
                            
                            <sf:errors path="uploadFileForm.titleEn" cssClass="text-red" />
                            <div class="setting-line"><span class="text-grey">英文標題</span>
                                <sf:input type="text" class="mx-auto " path="uploadFileForm.titleEn" placeholder="請輸入標題(建議字數40字以內)" />
							</div>
							
							<sf:errors path="uploadFileForm.content" cssClass="text-red" />
                            <div class="setting-line"><span class="text-grey">內文</span>
                                <sf:input type="text" class="mx-auto " path="uploadFileForm.content" placeholder="請輸入內文(建議字數100字以內)" />
                            </div>
                            
                            <sf:errors path="uploadFileForm.contentEn" cssClass="text-red" />
                            <div class="setting-line"><span class="text-grey">英文內文</span>
                                <sf:input type="text" class="mx-auto " path="uploadFileForm.contentEn" placeholder="請輸入內文(建議字數100字以內)" />
                            </div>

                            <div class="setting-row"><span class="text-blue font-weight-bold">上架時間</span>
                                <div class="setting-time d-inline-block">
                                    <div class="d-inline-block setting-radio">
                                        <sf:radiobutton name="time" value="0" path="uploadFileForm.status" class="" /><label>指定時間</label>
                                    </div>
                                    
                                    <div class="calendar">
                                        <sf:input type="text" class="form-control datetimepicker" path="uploadFileForm.pushingDate" id="pushingDate" name="pushingDate" placeholder="yyyy/mm/dd" />
                                        <i class="icons-icon-calander i-icon mr-1"></i>
                                    	
                                    </div>
                                    
                                </div>
                                <div class="d-inline-block setting-radio ml-4">
                                    <sf:radiobutton name="time" value="1" path="uploadFileForm.status" class="" /><label>立即上架</label>
                                </div>

                                <div>
                                	<sf:errors path="uploadFileForm.pushingDate" cssClass="text-red" />
                                </div>
                            </div>
                            <div class="setting-row"><span class="text-blue font-weight-bold">下架時間</span>
                                <div class="setting-under-time d-inline-block">
                                    <span>指定時間</span>
                                    <div class="calendar">
                                        <sf:input type="text" class="form-control datetimepicker" path="uploadFileForm.invalidDate" id="invalidDate" name="invalidDate" placeholder="yyyy/mm/dd" />
                                        <i class="icons-icon-calander i-icon mr-1"></i>
                                    </div>
                                    
                                </div>
                                
                                <div>
                                	<sf:errors path="uploadFileForm.invalidDate" cssClass="text-red" />
                                </div>
                            </div>
                        </div>

                        </sf:form>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="text-center step-button mt-4">
                        <a href="#" class="btn line-blue" data-toggle="modal" data-target="#noSave">上一頁</a>
                        <a href="#" class="btn full-blue" onclick="saveAction();">確認儲存</a>
                    </div>
                </div>

            </div>
        </div>
    </main>
	
</body>

</html>
