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
	
<style>
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
		/**
		$(window).bind('beforeunload',function(){
			return '您確定離開此頁面嗎？請記得存檔！';
		});
		*/

		$("#imageUploadInput").on("change", function (event) {
			var file = $(this).get(0).files[0];
			//上傳圖片不可大於10mb
			if (file && (file.size / 1024 / 1024 > 10)){
				$('#showFileErr').addClass('is-invalid');
				$('#saveBtn').prop('disabled',true);
				return false;
			}else{
				$('#showFileErr').removeClass('is-invalid');
				$('#saveBtn').prop('disabled',false);
			}
			
			if ( /\.(jpe?g|png|gif)$/i.test(file.name) ) {
		      var reader = new FileReader();
		      reader.onload = function () {
		        var image = new Image();
		        image.src = reader.result;
				$('#previewImage').attr('src',reader.result);
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

	});

		function saveAction() {
			$(window).unbind('beforeunload');
			var form = $('#editLogoWallForm');
			
			$('#fileName').val($('#imageUploadInput').val());
			
			form.attr('method', 'post');
			form.attr('action', '${contextPath}/backend/doLogoWallSaveAction');
			form.submit();
		}
		
    	function doGoBack() {
    		window.location.replace('${contextPath}/backend/logoWallHome');
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
                            <h2 class="lg-title">成功案例設定</h2>
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="setting input-type mt-4">
                    <sf:form name="editLogoWallForm" id="editLogoWallForm" modelAttribute="viewForm" autocomplete="off" enctype="multipart/form-data" >
						<sf:input type="hidden" path="uploadFileForm.seq"/>
						<sf:input type="hidden" id="fileName" name="fileName" path="uploadFileForm.fileName"/>
						<sf:input type="hidden" id="imageUrl" name="imageUrl" path="imageUrl" />
											
                        <div class="video-img">

                            <div class="upload d-inline-block mx-4 mb-5">
                                <!-- 圖片未上傳 -->
                                
                                <sf:errors path="uploadFileForm.fileName" cssClass="text-red" />
                                <div class="">
                                    <sf:input class="choose-file" type="file" path="uploadFileForm.file" name="imageUploadInput" id="imageUploadInput" />
                                    <label for="imageUploadInput" class="upload-file success-file" data-text="filename">
                                        <div class="btn full-yellow"><i class="icons-icon-pic i-icon mr-1"></i>選擇檔案
                                        </div>
                                        <p>圖片建議尺寸300*100px</p>
                                    </label>
                                    <div id="showFileErr" class="form-control" style="display:none;">
                                    </div>
                                    <div class="invalid-feedback">上傳的圖片不可大於10MB</div>
                                </div>
                                <!-- 圖片未上傳 end-->
                                <!-- 圖片上傳 -->
                                <div class="img-box">
                                    <div class="upload-img img-success">
                                    	<c:choose>
                                            <c:when test="${not empty viewForm.imageUrl}">
                                                <img class="img-fluid" id="previewImage" src="${viewForm.imageUrl}" />
                                            </c:when>
                                            <c:otherwise>
                                                <img class="img-fluid" id="previewImage" src="${contextPath}/backendStatic/images/success-logo.png" alt="">
                                            </c:otherwise>
                                        </c:choose>
                                        <!--  i class="icons-img-cancel i-icon mr-1"></i> -->
                                    </div>
                                </div>
                                <!-- 圖片上傳 end-->
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
