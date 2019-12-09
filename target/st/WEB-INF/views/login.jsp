<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/tinymce/tinymce.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/jquery.blImageCenter.js"></script>

<script type="text/javascript">
var serverDomain = "${hostUrl}st/";

$(function () {

	tinymce.init({
		selector : '#editContent',
		mode : "textareas"
	});

	$('#uploadImageBtn').on('click',function(){
		var input = $("#imageUploadInput");
		var file = $("#imageUploadInput").get(0).files[0];
		if (!file) return;
		name = file.name;
		size = file.size;
		type = file.type;

		var target = $(this).closest("form");
		var uploadTypes = [
			"image/x-png", "image/gif", "image/jpeg"
		];


		var aoeSeq = $("#aoeSeq").val();
		var aoeSeq = '1';
		var formData = new FormData();
		formData.append("file", file);
		formData.append('aoeSeq', aoeSeq);
		formData.append('type', '${imagesType}');

		var serviceURL = "online/uploadTitlePageImg"+"?"+'${_csrf.parameterName}=${_csrf.token}';

		$.ajax({
			url: serviceURL,
			type: 'POST',
			data: formData,
			processData: false,
			contentType: false,
			success: function (str) {
				var data = jQuery.parseJSON(decodeURIComponent(escape(str)));
				if (data && data.aoeSeq){
					uploaded = true;
					alert('上傳成功!');
				}else
					alert("<spring:message code="online.edit.alert.upload.getfailure"/>" + str);
			},
			error: function (jqXHR, textStatus, errorThrown) {
				if (target.length > 0)
					target[0].reset();
				

				alert("<spring:message code="online.edit.alert.upload.failure"/>");
			}
		}).done(function () {
			
		});

	})
	
	$("#imageUploadInput").on("change", function (event) {
		var file = $(this).get(0).files[0];
		//上傳圖片不可大於1mb
		if (file && (file.size / 1024 / 1024 > 1)){
			$('#imageUploadInput').addClass('is-invalid');
			$('#uploadImageBtn').prop('disabled',true);
			return false;
		}else{
			$('#imageUploadInput').removeClass('is-invalid');
			$('#uploadImageBtn').prop('disabled',false);
		}
		
		if ( /\.(jpe?g|png|gif)$/i.test(file.name) ) {
	      var reader = new FileReader();
	      reader.onload = function () {
	        var image = new Image();
	        image.src = reader.result;
			$('#previewImage').attr('src',reader.result);
			$('#previewImage').centerImage();
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

});



</script>

<title>min</title>
</head>
<body>

	<h1>Spring MVC 5 + Spring Security 5 + Hibernate 5 輕輕鬆鬆入門[${sessions.size()}]</h1>
	<h4>登入頁面(<spring:message code="app.page.header"/>)</h4>
	
	<form action='<spring:url value="/loginAction"/>' method="post">
    <table>
      <tr>
        <td>Username</td>
        <td><input type="text" name="username"></td>
      </tr>
      <tr>
        <td>Password</td>
        <td><input type="password" name="userpwd"></td>
      </tr>
      <tr>
        <td>
        <button type="submit">Login${1+1}</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </td>
      </tr>
    </table>
  </form>

	<!-- 編輯器位置 -->
	<div>
	111
		<div id="editContent"></div>
	</div>
	
	<input type="file" id="imageUploadInput" class="form-control" accept="image/*" />

	<label for="choose-file" class="upload-file" data-text="filename">
		<button type="button" id="uploadImageBtn">上傳圖片</button>
	</label>

	<div  style="width:300px;height:600px;">
		<img id="previewImage" src="" />
	</div>

	<form action='<spring:url value="homeAction"/>' method="post">
    <table>
      <tr>
        <td>
        <button type="submit">goToHome</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </td>
      </tr>
    </table>
  </form>

</body>
</html>