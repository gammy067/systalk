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
		
		var getExtension = function (path) {
		    var basename = path.split(/[\\/]/).pop(),  // extract file name from full path ...
		                                               // (supports `\\` and `/` separators)
		        pos = basename.lastIndexOf(".");       // get last position of `.`

		    if (basename === "" || pos < 1)            // if file name is empty or ...
		        return "";                             //  `.` not found (-1) or comes first (0)

		    return basename.slice(pos + 1);            // extract extension ignoring `.`
		}

		var callUploadFunction = function(file,type,callback){
			var formData = new FormData();
			formData.append("file", file);
			formData.append('type', type);
			var serviceURL = "doUploadEditImageAction"+"?"+'${_csrf.parameterName}=${_csrf.token}';
			$.ajax({
				url: serviceURL,
				type: 'POST',
				data: formData,
				processData: false,
				contentType: false,
				success: function (imageUrl) {
					//var data = jQuery.parseJSON(decodeURIComponent(escape(str)));
					//callback(data.uriLink);
					callback(imageUrl);
				},
				error: function (jqXHR, textStatus, errorThrown) {
					failure(errorThrown);
					callback('');
				}
			})
		};

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

        $('.datetimepicker').datetimepicker({
            format: 'YYYY/MM/DD'
        });
        
        // status = 立即上架 disabled 日期元件
        if("${viewForm.uploadFileForm.status}" == "1") {
        	$('.datetimepicker').prop("disabled", "disabled");
        }
  
        // radioButton切換事件
        $("input[type=radio][name='uploadFileForm.status']").change(function () {
            if ($(this).val() === "0") {
            	$('.datetimepicker').prop("disabled", "");
            } else {
            	$('.datetimepicker').prop("disabled", "disabled");
            }
        });
	});

	function saveAction() {
		$(window).unbind('beforeunload');
		var form = $('#editSuccessCaseForm');
		//var content = tinyMCE.activeEditor.getContent();
		//$('#content').val(content);
		
		$('#statusCheckVal').val(document.getElementById("checkStatus").checked);
		$('#fileName').val($('#imageUploadInput').val());
			
		form.attr('method', 'post');
		form.attr('action', '${contextPath}/backend/doSuccessCaseSaveAction');
		form.submit();
	}
	
	function doGoBack() {
		window.location.replace('${contextPath}/backend/successCaseHome');
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
                    <sf:form name="editSuccessCaseForm" id="editSuccessCaseForm" modelAttribute="viewForm" autocomplete="off" enctype="multipart/form-data">
                    	<sf:input type="hidden" path="uploadFileForm.seq"/>
						<sf:input type="hidden" id="fileName" name="fileName" path="uploadFileForm.fileName"/>
						<sf:input type="hidden" id="imageUrl" name="imageUrl" path="imageUrl" />
						<sf:input type="hidden" path="statusCheckVal"/>
						
                        <div class="upload d-inline-block">
                            <!-- 圖片未上傳 -->
                            <sf:errors path="uploadFileForm.fileName" cssClass="text-red" />
                           	<div class="">
                       			<sf:input class="choose-file" type="file" path="uploadFileForm.file" name="imageUploadInput" id="imageUploadInput" />
                             	<label for="imageUploadInput" class="upload-file success-radius" data-text="filename">
                               		<div class="btn full-yellow"><i class="icons-icon-pic i-icon mr-1"></i>選擇檔案
                             		</div>
                               		<p>圖片建議尺寸240*240px</p>
                               	</label>
                               	<div id="showFileErr" class="form-control" style="display:none;">
                              	</div>
                             	<div class="invalid-feedback">上傳的圖片不可大於10MB</div>
                            </div>
                            <!-- 圖片未上傳 end-->
                            <!-- 圖片上傳 -->
                            <div class="img-box">
                                <div class="upload-img img-radius">
                                 <c:choose>
                       				<c:when test="${not empty viewForm.imageUrl}">
                                    	<img class="img-fluid" id="previewImage" src="${viewForm.imageUrl}" />
                                 	</c:when>
                                	<c:otherwise>
                                   		<img class="img-fluid" id="previewImage" src="${contextPath}/backendStatic/images/success-img.png" alt="">
                              		</c:otherwise>
                                 </c:choose>
                                 <!--  <i class="icons-img-cancel i-icon mr-1"></i> -->
                                </div>
                            </div>
                            <!-- 圖片上傳 end-->
                        </div>
                        
                        <div class="setting-inform input-box-left">
                        	<sf:errors path="uploadFileForm.title" cssClass="text-red" />
                            <div class="setting-line"><span class="text-grey">標題</span>
                            	<sf:input type="text" class="mx-auto" path="uploadFileForm.title" placeholder="請輸入標題(建議字數40字以內)" onkeydown="if(event.keyCode==13)return false;" />
                            </div>
                            
                 			<sf:errors path="uploadFileForm.titleEn" cssClass="text-red" />
	                 		<div class="setting-line"><span class="text-grey">英文標題</span>
	               				<sf:input type="text" class="mx-auto" path="uploadFileForm.titleEn" placeholder="請輸入標題(建議字數40字以內)" onkeydown="if(event.keyCode==13)return false;" />
	                		</div>
                            
                            <sf:errors path="uploadFileForm.content" cssClass="text-red" />
                            <div class="setting-line d-flex"><span class="text-grey">內文</span>
                                <sf:textarea placeholder="請輸入內文(建議字數100字以內)" class="textarea-style" path="uploadFileForm.content"/></textarea>
                            </div>
                            
      						<sf:errors path="uploadFileForm.contentEn" cssClass="text-red" />
	              			<div class="setting-line"><span class="text-grey">英文內文</span>
	           					<sf:textarea class="textarea-style" path="uploadFileForm.contentEn" placeholder="請輸入標題(建議字數40字以內)" />
							</div>

                            <div class="setting-row">
                                <div class="d-inline-block setting-checkbox">
                                	<c:choose>
                                  		<c:when test="${viewForm.statusCheckVal eq true}">
                                    		<input id="checkStatus" type="checkbox" checked><label for="checkStatus">立即上架</label>
                                         </c:when>
                                         <c:otherwise>
                                         	<input id="checkStatus" type="checkbox"><label for="checkStatus">立即上架</label>
                                         </c:otherwise>
                                	</c:choose>
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



    
    <!-- 設定textarea高度 -->
    <script type="text/javascript">
        $(".textarea-style").css("overflow", "hidden").bind("keydown keyup", function () {
            $(this).height('0px').height($(this).prop("scrollHeight") + "px");
        }).keydown();
    </script>
	
</body>

</html>
