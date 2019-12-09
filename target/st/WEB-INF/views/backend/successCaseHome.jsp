<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<script type="text/javascript">
	$(function () {

	    $('#delete').on('show.bs.modal', function(e) {
	        //get data-id attribute of the clicked element
	        var deleteSeq = $(e.relatedTarget).data('id');
	        //populate the textbox
	        $(e.currentTarget).find('a[id="deleteSeq"]').prop('deleteSeq',deleteSeq);
	    });
	});

	function doButtonAction(seq, event) {
		var form = $('#goToEditSuccessCaseForm');
		$("#seq").val(seq);
		var url = '${contextPath}/backend/editSuccessCase'

		if (event === 'delete') {
			doDeleteSuccessCaseAction(seq);

		} else {
			form.attr('method', 'post');
			form.attr('action', url);
			form.submit();
		}
	}
	
	function doDeleteSuccessCaseAction(seq) {
    	var postUrl = "${contextPath}/backend/doDeleteSuccessCaseAction";	
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
	   				window.location.replace('${contextPath}/backend/successCaseHome');
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

    <!--main 中間內容 Start-->
    <main>
        <div class="d-table-cell mx-auto text-center align-middle login-content">
            <div class="about-box row mx-auto">
                <div class="w-100">
                    <div class="title">
                        <div>
                            <h2 class="lg-title">成功案例設定</h2>
                        </div>
                    </div>
                </div>
                <!-- Nav tabs -->
                <ul class="nav nav-tabs success-tabs">
                    <li class="nav-item">
                        <a class="nav-link tabls-left" href="${contextPath}/backend/logoWallHome">LOGO</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active tabls-right" href="#">客戶證言</a>
                    </li>
                </ul>

                <!-- Tab panes -->
                <div class="tab-content w-100">
                    <div class="tab-pane container active" id="successCase">
                        <div class="text-right about-button">
                            <a href="#" name="new" onclick="doButtonAction('', this.name);" class="btn full-yellow"><i class="icons-btn-add i-icon mr-1"></i>新增項目</a>
                        </div>
                        <div class="table-style">
                            <table class="datatable table table-responsive">
                                <thead>
                                    <tr>
                                        <th style="width: 12%;"></th>
                                        <th style="width: 12%;">編輯人員</th>
		                                <th style="width: 12%;">上架日期</th>
		                                <th style="width: 12%;">狀態</th>
		                                <th style="width: 12%;">檔案名稱</th>
		                                <th style="width: 15%;">編輯</th>
                                    </tr>
                                </thead>
			                    <tbody>
									<c:forEach var="successCase" items="${successCaseBeanList}" varStatus="state">
										<tr>
											<td>
												<c:choose>
													<c:when test="${successCase.draft eq true}">
					                                 	<div class="status status-dark">草稿</div>
					                                 </c:when>
					                                 <c:otherwise>
					                                   	<div class="status status-blue">上架中</div>
					                             	</c:otherwise>
					                        	</c:choose>
											</td>
											<td><c:out value="${successCase.userName}" /></td>
											<td>
												<fmt:parseDate value="${successCase.pushingDate}" var="pushingDate" pattern="yyyy-MM-dd" />
												<fmt:formatDate type="DATE" pattern="yyyy/MM/dd" value="${pushingDate}" />
											</td>
											<td><c:out value="${successCase.displayStatus}" /></td>
											<td><c:out value="${successCase.fileName}" />
											</td>
			                                <td>
			                                    <div class="btn-edit">
			                                    	<a name="edit" href="#" value="" name="edit" onclick="doButtonAction(${successCase.seq}, this.name);">
			                                    		<i class="icons-btn-edit i-icon"></i>
			                                    	</a>
			                                    </div>
			                                    
			                                    <!-- 確定要刪除時樣式-->
			                                    <div class="btn-edit">
				                                    <a href="#" data-toggle="modal" data-target="#delete" data-id="${successCase.seq}">
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
            </div>
        </div>
    </main>

	<form id="goToEditSuccessCaseForm" name="goToEditSuccessCaseForm">
		<input type="hidden" id="seq" name="seq" /> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form
	
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

</html>