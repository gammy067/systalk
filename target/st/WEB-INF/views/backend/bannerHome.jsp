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

	<script type="text/javascript">	
	$(function () {
/**
	    $('#queryTable').dataTable({
	    	"bLengthChange": true,
	    	"sPaginationType":"full_numbers","bPaginate":true,
	    	"iDisplayLength": 10,
	    	"aLengthMenu":[[10, 20, -1], [10, 20, "All"]],
	    	"order": [],
	    	"oLanguage": {
	    		"sSearch": "搜尋",
	    		"sLengthMenu": " _MENU_ 筆/頁",
	    		"sInfo":"顯示第 _START_ 至 _END_ 項結果，共 _TOTAL_ 項",
	    		"sInfoFiltered": " - 找到 _TOTAL_ 筆 資料",
	    		"sInfoEmpty": "共 0 頁",
	    		"oPaginate": {
	    			"sFirst": "<<",
	                "sPrevious": "<",
	                "sNext": ">",
	                "sLast": ">>"
	            }
	    	}
	    });
*/
	    
	    $('#delete').on('show.bs.modal', function(e) {

	        //get data-id attribute of the clicked element
	        var deleteSeq = $(e.relatedTarget).data('id');
	        //populate the textbox
	        $(e.currentTarget).find('a[id="deleteSeq"]').prop('deleteSeq',deleteSeq);
	    });
	});

	function doButtonAction(seq, event) {
		var form = $('#goToEditBannerForm');
		$("#seq").val(seq);
		var url = '${contextPath}/backend/editBanner'

		if (event === 'delete') {
			doDeleteBannerAction(seq);

		} else {
			if (event === 'new') {
				// 輪播圖上架個數(上架10個不允許新增)
				if('${validateMessage}' != '') {
					alert('${validateMessage}');
					return;
				}
			}
			form.attr('method', 'post');
			form.attr('action', url);
			form.submit();
		}
	}
	
	function doDeleteBannerAction(seq) {
    	var postUrl = "${contextPath}/backend/doDeleteBannerAction";	
		postUrl = postUrl +"?"+'${_csrf.parameterName}=${_csrf.token}';
			$.ajax({
				url: postUrl,
				type: 'POST',
				data:  {
					seq: seq
	            },
				success: function (data) {
					var dataObj = JSON.parse(data);
		   			if (dataObj.isSuccess) {
		   				window.location.replace('${contextPath}/backend/bannerHome');
					} else {
						window.location.reload();
					}
				},
				error: function (jqXHR, textStatus, errorThrown) {
					window.location.reload();
				}
			});
	}

	</script>

</head>

<body class="index-bg menu-bg">
	<main>
        <div class="d-table-cell mx-auto text-center align-middle login-content">
            <div class="about-box row mx-auto">
                <div class="w-100">
                    <div class="title">
                        <div>
                            <h2 class="lg-title">首頁輪播設定</h2>
                        </div>
                    </div>
                </div>
                <div class="text-right about-button">
                    <a href="#" name="new" onclick="doButtonAction('', this.name);" class="btn full-yellow"><i class="icons-btn-add i-icon mr-1"></i>新增項目</a>
                </div>
                <div class="table-style">
                    <table class="datatable table table-responsive">
                        <thead>
                            <tr>
                                <th style="width: 10%;"></th>
                                <th style="width: 12%;">標題</th>
                                <th style="width: 12%;">編輯人員</th>
                                <th style="width: 12%;">上架日期</th>
                                <th style="width: 12%;">狀態</th>
                                <th style="width: 12%;">內文</th>
                                <th style="width: 12%;">檔案名稱</th>
                                <th style="width: 15%;">編輯</th>
                            </tr>
                        </thead>
                        <tbody>
						<c:forEach var="bannerSetting" items="${bannerSettingBeanList}"
							varStatus="state">
							<tr>
								<td>
									<c:choose>
									 	<c:when test="${bannerSetting.draft eq true}">
	                                 		<div class="status status-dark">草稿</div>
	                                  	</c:when>
	                                    <c:otherwise>
	                                   		<div class="status status-blue">上架中</div>
	                             		</c:otherwise>
	                        		</c:choose>
								</td>
								<td><c:out value="${bannerSetting.title}" /></td>
								<td><c:out value="${bannerSetting.userName}" /></td>
								<td><fmt:parseDate value="${bannerSetting.pushingDate}" var="pushingDate" pattern="yyyy-MM-dd" />
									<fmt:formatDate type="DATE" pattern="yyyy/MM/dd" value="${pushingDate}" />
								</td>
								<td><c:out value="${bannerSetting.displayStatus}" /></td>
								<td><c:out value="${bannerSetting.content}" /></td>
								<td><c:out value="${bannerSetting.fileName}" /><br><c:out value="${bannerSetting.fileName2}"/>
								</td>
                                <td>
                                    <div class="btn-edit">
                                    	<a name="edit" href="#" value="" name="edit" onclick="doButtonAction(${bannerSetting.seq}, this.name);">
                                    		<i class="icons-btn-edit i-icon"></i>
                                    	</a>
                                    </div>
                                    
                                    <!-- 確定要刪除時樣式-->
                                    <div class="btn-edit">
	                                    <a href="#" data-toggle="modal" data-target="#delete" data-id="${bannerSetting.seq}">
	                                    	<i class="icons-btn-delete i-icon"></i>
	                                    </a>
                                    </div>
                                    <!-- 確定要刪除時樣式 end-->

                                </td>
							</tr>
						</c:forEach>
					</tbody>
                    </table>
                </div>

            </div>
        </div>
    </main>

	<form id="goToEditBannerForm" name="goToEditBannerForm">
		<input type="hidden" id="seq" name="seq" /> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<form>

	<!-- modal -->
	<!-- 確定要刪除-->
	<div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-show="true">
		<div class="modal-dialog dally_black" role="document">
			<div class="modal-content">
				<div class="modal-body modal-space">
					<div class="text-center modal-box">
						<div class="modal-tit">確定要刪除檔案嗎?</div>
					</div>
					<div class="col-md-12">
						<div class="text-center step-button mt-4">
							<a href="#" class="btn line-blue" data-dismiss="modal" aria-label="Close">取消</a> 
							<a href="#" name="delete" id="deleteSeq" deleteSeq="" onclick="doButtonAction(this.deleteSeq, this.name);" class="btn full-blue" data-dismiss="modal" aria-label="Close">確定</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 確定要刪除 end-->

</body>
 <!--  <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.js"></script>  -->

</html>