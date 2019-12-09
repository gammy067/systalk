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

	<!-- fa fa style 刪除圖示 -->
    <link rel="stylesheet" href="https://formden.com/static/cdn/font-awesome/4.4.0/css/font-awesome.min.css" />

<style>

.removeIcon {
    background-repeat: no-repeat;
    right: 18px;
    top: 0px;
    position: relative;
    z-index: 1;
    width: 10px;
    height: 25px;
    cursor: pointer;
}

.pull-right {
    float: none;
}

</style>
	<script type="text/javascript">
	$(function () {
		
	    $('#delete').on('show.bs.modal', function(e) {

	        //get data-id attribute of the clicked element
	        var deleteSeq = $(e.relatedTarget).data('id');
	        //populate the textbox
	        $(e.currentTarget).find('a[id="deleteSeq"]').prop('deleteSeq',deleteSeq);
	    });

	});

	function doEditNewsAreaType() {	
		var elems = document.querySelectorAll(".sort-item");

		document.getElementById("displayTagBlock").style = "display:none;";
		document.getElementById("editBlock").style = "display:block;";

		
		$('#editNewsAreaTypeBtn').prop("hidden", "hidden");
		$('#addNewsAreaTypeBtn').prop("hidden", "");
		$('#saveNewsAreaTypeBtn').prop("hidden", "");
		$('#cancelNewsAreaTypeBtn').prop("hidden", "");
	}
	
	var newItemIndex = 0;
	//var leftpx = 0;
	function doAddNewsAreaType() {
		var div = document.createElement('div');
		div.setAttribute('class', "news-setup");

		var newItemId =  "tmp" + newItemIndex++;
		div.setAttribute('id', newItemId);
		//leftpx = leftpx + 5;
		
		var input = document.createElement('input');
		input.setAttribute('type', "text");
		input.setAttribute('class', "mx-auto");
		input.setAttribute('placeholder', "請輸入分類名稱");
		input.setAttribute('seq', "");

		div.appendChild(input);
		
	    var newEl = document.createElement('i');
	    newEl.setAttribute('class', "icons-btn-cancel i-icon mr-1");
	    var itemId = div.id;
	    newEl.setAttribute('onclick', 'removeItem("' +  itemId + '")');
	    div.appendChild(newEl);
	
		document.getElementById("editBlock").appendChild(div);	
	}
	
	function doSaveNewsAreaTypeAction() {
		var newsAreaTypes = [];
		
		var elems = document.querySelectorAll('input');

		[].forEach.call(elems, function(el) {
			if(el.value != '') {
				var typeObj = {
						seq : el.getAttribute("seq"),
						name : el.value	
				}
				newsAreaTypes.push(typeObj);
			}
		});
		
		$.ajax({
			contentType: "application/json",
			type: "POST",
			data: JSON.stringify(newsAreaTypes),
			url: '${contextPath}/backend/doSaveNewsAreaTypeAction',
				success: function(data) {
					var dataObj = JSON.parse(data);
		   			if (dataObj.isSuccess) {
		   				window.location.replace('${contextPath}/backend/newsAreaHome');
					} else {
						window.location.reload();
					}
    	        },
    	        error: function(jqXHR, textStatus, errorThrown) {
    	            console.log('error while post');
    	        }
		 });
	}
	
	function doCancelNewsAreaTypeAction(seq, event) {
		if (confirm ("是否取消該次編輯? ") ) {
			window.location.reload();
		} else {
			return;
		}
	}

	function doButtonAction(seq, event) {
		var form = $('#goToEditNewsAreaForm');
		$("#seq").val(seq);
		var url = '${contextPath}/backend/editNewsArea'

		if (event === 'delete') {
			doDeleteNewsAreaAction(seq);

		} else {
			form.attr('method', 'post');
			form.attr('action', url);
			form.submit();
		}
	}
	
	function doDeleteNewsAreaAction(seq) {
    	var postUrl = "${contextPath}/backend/doDeleteNewsAreaAction";	
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
		  				window.location.replace('${contextPath}/backend/newsAreaHome');
				} else {
					window.location.reload();
				}
			},
			error: function (jqXHR, textStatus, errorThrown) {
				window.location.reload();
			}
		});
	}
	
	function removeItem(itemId) {
		document.getElementById(itemId).remove();
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
                            <h2 class="lg-title">最新消息設定</h2>                     
                        </div>
                    </div>  
                </div>
                
          	<!-- 最新消息分類名稱設定 -->
                <div class="col-12 d-flex align-items-center p-0">
                    <div class="justify-content-start mr-auto num-tag">
                        <span class="text-blue md-title">最新消息分類名稱設定</span>
                    </div>
                    <div class="justify-content-end">
                        <div class="text-right about-button">
                            <a href="#" id="editNewsAreaTypeBtn" class="btn full-yellow" onclick="doEditNewsAreaType();"><i class="icons-icon-edit i-icon mr-1"></i>編輯項目</a>
                            <a href="#" id="addNewsAreaTypeBtn" class="btn full-yellow mr-2" hidden onclick="doAddNewsAreaType();"><i class="icons-btn-add i-icon mr-1"></i>新增項目</a>
                            <a href="#" id="saveNewsAreaTypeBtn" class="btn full-blue" hidden onclick="doSaveNewsAreaTypeAction();"><i class="icons-icon-hook i-icon mr-1"></i>確認儲存</a>
                            <a href="#" id="cancelNewsAreaTypeBtn" class="btn full-blue" hidden onclick="doCancelNewsAreaTypeAction();"></i>取消</a>
                        </div>
                    </div>
                </div>
                
                <div class="col-12 p-0 mb-5 mt-3 text-left"  id="displayTagBlock">
                	<c:forEach var="newsAreaType" items="${newsAreaTypeBeanList}" varStatus="state">
                    	<div class="sort-item">${newsAreaType.typeName}</div>
                    </c:forEach>
                </div>
                
                <div class="col-12 p-0 mb-5 mt-3 text-left" id="editBlock" style="display:none;">
                    <c:forEach var="newsAreaType" items="${newsAreaTypeBeanList}" varStatus="state">
                    	<div class="news-setup" id="${newsAreaType.seq}">
                    		<input type="text" seq="${newsAreaType.seq}" class="mx-auto" placeholder="請輸入分類名稱"  value="${newsAreaType.typeName}">
                        	<i class="icons-btn-cancel i-icon mr-1" onclick="removeItem(${newsAreaType.seq})"></i>
                        </div>
                    </c:forEach>
                </div>
                
			<!-- 最新消息分類名稱設定 END -->
 
            <div class="col-12 d-flex align-items-center p-0">
                    <div class="justify-content-start mr-auto num-tag">
                        <span class="text-blue md-title">最新消息內容設定</span>
                    </div>
                    <div class="justify-content-end">
                        <div class="text-right about-button">
                            <a href="#" class="btn full-yellow" name="new" onclick="doButtonAction('', this.name);"><i class="icons-btn-add i-icon mr-1"></i>新增項目</a>
                        </div>
                    </div>
             </div>

            <div class="table-style">
                <table class="datatable table table-responsive">
                    <thead>
                        <tr>
                        	<th style="width: 10%;"></th>
                            <th style="width: 12%;">標題</th>
                            <th style="width: 12%;">更新時間</th>
                            <th style="width: 12%;">上架時間</th>
                            <th style="width: 12%;">狀態</th>
                            <th style="width: 15%;">編輯</th>
                        </tr>
                    </thead>
                    <tbody>
					<c:forEach var="newsArea" items="${newsAreaBeanList}" varStatus="state">
						<tr>
							<td>
								<c:choose>
									<c:when test="${newsArea.draft eq true}">
	                                 		<div class="status status-dark">草稿</div>
	                                  	</c:when>
	                                    <c:otherwise>
	                                   		<div class="status status-blue">上架中</div>
	                             		</c:otherwise>
	                        		</c:choose>
							</td>
							<td><c:out value="${newsArea.title}" /></td>
							<td >
								<fmt:parseDate value="${newsArea.updateDate}" var="updateDate" pattern="yyyy-MM-dd mm:ss" />
								<fmt:formatDate type="DATE" pattern="yyyy/MM/dd mm:ss" value="${updateDate}" />
							</td>
							<td>
								<fmt:parseDate value="${newsArea.pushingDate}" var="pushingDate" pattern="yyyy-MM-dd" />
								<fmt:formatDate type="DATE" pattern="yyyy/MM/dd" value="${pushingDate}" />
							</td>
							<td><c:out value="${newsArea.displayStatus}" /></td>
							</td>
                            <td>
                                <div class="btn-edit">
                                	<a name="edit" href="#" value="" name="edit" onclick="doButtonAction(${newsArea.seq}, this.name);">
                                		<i class="icons-btn-edit i-icon"></i>
                                	</a>
                                </div>
                                
                                <!-- 確定要刪除時樣式-->
                                <div class="btn-edit">
                                    <a href="#" data-toggle="modal" data-target="#delete" data-id="${newsArea.seq}">
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
    </main>
    
    <form id="goToEditNewsAreaForm" name="goToEditNewsAreaForm">
		<input type="hidden" id="seq" name="seq" />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
	
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