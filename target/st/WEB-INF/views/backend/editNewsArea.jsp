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
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	
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

		tinymce.init({
	  		// 初始化參數設定[註1]
	  		selector: "#editContent", // 目標物件
	  		language: "zh_TW", // 語系(CDN沒有中文，需要下載原始source才有)
	  		plugins: ["textcolor colorpicker charmap table code codesample preview image fileuploader link anchor media"],
	  		toolbar: "charmap | bold italic underline | forecolor backcolor | alignleft aligncenter alignright alignjustify | bullist numlist | code | preview | codesample | image | anchor | link | media",  // 工具列設定
	  		resize: true,
	  		width: '100%',
	  	  	autoresize_min_height: 600,
	  	  	autoresize_max_height: 800,height: 600,
			automatic_uploads:true,
			file_picker_types: 'image',
		    convert_urls:false,
		    images_reuse_filename: false,
		    images_upload_handler: function (blobInfo, success, failure) {
				if (blobInfo.blob().size/1024/1024 > 1){
					failure('上傳的圖片不可大於1MB');
					return false;
				};
		    	var ext = getExtension(blobInfo.filename()).toLowerCase();
		    	var allows = ['jpg','gif','png'];
		    	if (allows.indexOf(ext) >= 0) 
		    		callUploadFunction(blobInfo.blob(),'images',success);
		    	else
		    		failure('\u6a94\u6848\u683c\u5f0f\u9650\u006a\u0070\u0067\u3001\u0067\u0069\u0066\u3001\u0070\u006e\u0067');
		      },
				init_instance_callback:function(){
					tinyMCE.activeEditor.setContent("${viewForm.uploadFileForm.content}");
					//getEditorContent();
			}
		});
		
		$("#imageUploadInput").on("change", function (event) {
			var file = $(this).get(0).files[0];
			//上傳圖片不可大於1mb
			if (file && (file.size/1024/1024 > 5)){
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
	
	function getEditorContent(){
		var formData = {};
		formData.seq ='${uploadFileForm.seq}';

		var postUrl = '${contextPath}/backend/getEditorContent';
		
		postUrl = postUrl +"?"+'${_csrf.parameterName}=${_csrf.token}';
	   $.ajax({
			url: postUrl,
			type: 'POST',
			data: JSON.stringify(formData),
			processData: false,
			contentType: "application/json",
			success: function (data) {
				if (data !== null && data !== undefined) {
					tinyMCE.activeEditor.setContent(data.content);
						
					// hashContent = hashCode(data.content);
				} else {
					
				}
			},
			error: function (jqXHR, textStatus, errorThrown) {
				
			}
		});
	}

	function saveAction() {
		$(window).unbind('beforeunload');
		var form = $('#editNewsAreaForm');
		var content = tinyMCE.activeEditor.getContent();
		$('#content').val(content);
		
		$('#fileName').val($('#imageUploadInput').val());
		
		form.attr('method', 'post');
		form.attr('action', '${contextPath}/backend/doNewsAreaSaveAction');
		form.submit();
	}
	
	function doGoBack() {
		window.location.replace('${contextPath}/backend/newsAreaHome');
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
                            <h2 class="lg-title">最新消息設定</h2>
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="setting input-type mt-4">
                    	<sf:form name="editNewsAreaForm" id="editNewsAreaForm" modelAttribute="viewForm" autocomplete="off" enctype="multipart/form-data">
	                    
	                    <sf:input type="hidden" id="seq" name="seq" path="uploadFileForm.seq" />
						<sf:input type="hidden" id="content" name="content" path="uploadFileForm.content" />
						<sf:input type="hidden" id="fileName" name="fileName" path="uploadFileForm.fileName"/>
						<sf:input type="hidden" id="imageUrl" name="imageUrl" path="imageUrl" />
	                    	
	                    	<div class="upload d-inline-block">
	                            <!-- 圖片未上傳 -->
	                            <div class="">
	                                <sf:input class="choose-file" type="file" path="uploadFileForm.file" name="imageUploadInput" id="imageUploadInput" />
	                                <label for="imageUploadInput" class="upload-file news-file" data-text="filename">
	                                    <div class="btn full-yellow"><i class="icons-icon-pic i-icon mr-1"></i>選擇封面圖片</div>
	                                </label>
	                                <div id="showFileErr" class="form-control" style="display:none;">
                                    </div>
                                    <div class="invalid-feedback">上傳的圖片不可大於10MB</div>
	                            </div>
	                            <!-- 圖片未上傳 end-->
	                            <!-- 圖片上傳 -->
	                            <div class="img-box">
	                                <div class="upload-img img-news">
	                                    <c:choose>
                                            <c:when test="${not empty viewForm.imageUrl}">
                                                <img class="img-fluid" id="previewImage" src="${viewForm.imageUrl}" />
                                            </c:when>
                                            <c:otherwise>
                                                <img class="img-fluid" id="previewImage" src="${contextPath}/backendStatic/images/success-logo.png" alt="">
                                            	<!-- 09.25 暫時移除 不需要取消  <i class="icons-img-cancel i-icon mr-1"></i> -->                                            	<sf:errors path="imageUrl" cssClass="text-red" />
                                            </c:otherwise>
                                        </c:choose>
	                                </div>
	                            </div>
	                            <!-- 圖片上傳 end-->
	                        </div>

                        <div class="setting-inform d-inline-block news-inform">
                        	<sf:errors path="uploadFileForm.title" cssClass="text-red" />
                            <div class="news-title">
                                <div class="setting-line"><span class="text-grey">新聞標題</span>
                                <sf:input type="text" class="mx-auto " path="uploadFileForm.title" placeholder="請輸入標題(建議字數20字以內)" />
                                </div>
                            </div>
                            <div class="news-sort">
                                <div class="setting-line news-sort"><span class="text-grey">分類名稱</span>
                                    <div class="status">
		                        		<sf:select path="type" id="type" class="filter combo" >
		   									<sf:options items="${viewForm.typeCombo.options}" itemValue="value" itemLabel="label" />
										</sf:select>
                                    </div>
                                </div>
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

                  	</div>
                  	<!-- 編輯器錯誤訊息 -->
                  	<sf:errors path="uploadFileForm.content" cssClass="text-red" />
                </div>
                
	                <!-- 編輯器位置 -->
					<div class="col-md-12 text-center">
						<div class="place-tit" id="editContent"></div>
					</div>
						                        
                </sf:form>

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

    <script type="text/javascript" src="<%=request.getContextPath()%>/backendStatic/js/select.js"></script>
</html>