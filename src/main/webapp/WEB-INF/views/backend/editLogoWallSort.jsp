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
	  #sortable { list-style-type: none; margin: 0; padding: 0; margin-bottom: 10px; }
	  .ui-state-default { margin: 5px; padding: 5px; width: 1100px;}
  </style>
	
	<script type="text/javascript">

	$(function() {
		/**
		$(window).bind('beforeunload',function(){
			return '您確定離開此頁面嗎？請記得存檔！';
		});
		*/
		
	    $( "#sortable" ).sortable({
	      revert: true
	    });
	    /**
	    $( "#draggable" ).draggable({
	      connectToSortable: "#sortable",
	      helper: "clone",
	      revert: "invalid"
	    });
	    $( "ul, li" ).disableSelection();
		    */
	  	});


	function saveAction() {
		$(window).unbind('beforeunload');
			var dataArray = [];
		
	    	var items = document.getElementsByClassName("ui-state-default");
	    	for (i = 0; i < items.length; i++) {
	    	  dataArray.push(items[i].id);

	    	  console.log(items[i].id);
	    	}
	    	
			$.ajax({
				contentType: "application/json",
				type: "POST",
				data: JSON.stringify(dataArray),
				url: '${contextPath}/backend/doLogoWallSortSaveAction',
					success: function(data) {
						var dataObj = JSON.parse(data);
			   			if (dataObj.isSuccess) {
			   				window.location.replace('${contextPath}/backend/logoWallHome');
						} else {
							window.location.reload();
						}
	    	        },
	    	        error: function(jqXHR, textStatus, errorThrown) {
	    	            console.log('error while post');
	    	            window.location.reload();
	    	        }
			 });
			   	
//		var form = $('#editLogoWallSortForm');
//		form.attr('method', 'post');
//		form.attr('action', '${contextPath}/backend/doLogoWallSortSaveAction');
//		form.submit();
	}
	
	function doGoBack() {
		window.location.replace('${contextPath}/backend/logoWallHome');
	}
	</script>

</head>

<body class="index-bg menu-bg">
	<main>
		<div class="d-table-cell mx-auto align-middle login-content">
			<div class="about-box row mx-auto">
                <div class="w-100">
                    <div class="title">
                        <div>
                            <h2 class="lg-title">排序編輯</h2>
                        </div>
                    </div>
                </div>

			<div class="col-md-12">
				<sf:form name="editLogoWallSortForm" id="editLogoWallSortForm" modelAttribute="viewForm" autocomplete="off" >
					<table  style="width:100%">
						<thead>
							<tr>
								<th class="text-center">圖片</th>
								<th class="text-center">上傳日期</th>
							</tr>
						</thead>
					</table>
					<br>
					<ul id="sortable">
						<c:forEach var="logoWall" items="${viewForm.logoWallList}" varStatus="state">
							<li id="${logoWall.seq}" class="ui-state-default">
								<div style="width: 350px; height: 350px; display: inline; position:relative;left:120px;top:20px;">
									<c:choose>
										<c:when test="${logoWall.url ne null}">
											<div style="width: 100%; height: 50px;">
												<img id="previewImage" src="${logoWall.url}"
												style="width:auto; height: auto; max-width: 100px; max-height: 50px; min-width: 80px; min-height: 40px;" />
												
												${logoWall.fileName}
											</div>
										</c:when>
										<c:otherwise>
											<img id="previewImage" src="" />${logoWall.fileName}
										</c:otherwise>
									</c:choose>
								</div>
	
								<div style="width: 250px; display: inline;position:relative;left:680px;top:-25px;">
									${logoWall.pushingDate}
								</div>
	
							</li>
						</c:forEach>
					</ul>
				</sf:form>
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
	
	    <!-- 排序拖拉js 08.14 -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/jquery-ui.min.js"></script>
</html>
