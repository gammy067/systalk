<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


    <script type="text/javascript">
        $(function () {
    		$('.pagination').twbsPagination({
    			startPage: ${viewForm.pageObject.startPage},
    			totalPages: ${viewForm.pageObject.totalPages},
    			visiblePages: 3,
    			first :'<s:message code="news.pagination.first"/>',  // 首頁
    			prev:'<s:message code="news.pagination.previous"/>', // 上一頁
    			next:'<s:message code="news.pagination.next"/>', 	// 下一頁
    			last :'<s:message code="news.pagination.last"/>',	// 尾頁
    			onPageClick: function (event, page) {
					nextPage(page);
    			}
    		});
    		
    	    //setPage();
        });
        
    	function setPage(viewForm){
    		$('.pagination').twbsPagination({
    			startPage: viewForm.pageObject.startPage,
    			totalPages: viewForm.pageObject.totalPages,
    			visiblePages: 3,
    			first :'<s:message code="news.pagination.first"/>',
    			prev:'<s:message code="news.pagination.previous"/>',
    			next:'<s:message code="news.pagination.next"/>',
    			last :'<s:message code="news.pagination.last"/>',
    			onPageClick: function (event, page) {
					nextPage(page);
    			}
    		});

    		var itemHtml = "";
        	viewForm.newsAreaBeanList.forEach(function(newsArea){
    	    	itemHtml+= '<div class="col-sm-12 col-md-4">';
				itemHtml+= '	<div class="card-article">';
				itemHtml+= '		<a href="#" onclick="doGoToNewsAreaContentAction(' + newsArea.seq + ');">';
				itemHtml+= '			<div class="tag">' + newsArea.displayType + '</div>';
				var url = newsArea.url;
				itemHtml+= '			<div class="article-img article-img-01" style="background-image: url(' + url + ');"></div>';
				itemHtml+= '			<div class="title">';
				itemHtml+= '				<img src="${contextPath}/images/icon-time@2x.png" alt="發表時間" title="發表時間">';
				// var date = new Date(newsArea.pushingDate).toISOString().slice(0,10);
				itemHtml+= '				<span class="date">' + newsArea.displayPushingDate + '</span>';
				itemHtml+= '				<p>' + newsArea.displayTitle + '</p>';
				itemHtml+= '			</div>';
				itemHtml+= '		</a>';
				itemHtml+= '	</div>';
				itemHtml+= '</div>';
        	});
			
			$('#newsAreaItem').html(itemHtml);
    	}

    	function nextPage(page){
    		if(page != null && page != ''){
    			$('#startPage').val(page);
        		$('#typeSeq').val(${viewForm.typeSeq});
        		$('#dateYear').val(${viewForm.dateYear});
        		$('#searchText').val(${viewForm.searchText});
    		}
    		
    		$.ajax({
				url: '${contextPath}/newsArea/doNewsAreaListCurrentPageAction',
				type: 'POST',
				data: $('form[name=queryForm]').serialize(),
				success: function (viewForm) {
					if(viewForm){
						setPage(viewForm);
					}else{
						window.location.reload();
					}
				}
			});
    	}
        
    	function doQueryNewsAction() {
    		var form = $('#queryForm');
    		
    		var typeSeq = $('#typeCombo').val();
    		var dateYear = $('#dateYearCombo').val();
    		
    		if(typeSeq === '') {	
    			$('#showTypeErr').addClass('is-invalid');
    			return;
    		}
    		
    		$('#typeSeq').val(typeSeq);
    		$('#dateYear').val(dateYear);
    		$('#searchText').val($('#searchTextInput').val());
    		
    		var url = "${contextPath}/newsArea/doQueryNewsAction";

    		form.attr('method', 'post');
    		form.attr('action', url);
    		form.submit();
    	}
    	
    	function doGoToNewsAreaContentAction(seq) {
    		var form = $('#doGoToNewsAreaContentForm');
    		
    		//$('#seq').val(seq);
    		 
			form.attr('method', 'post');
			form.attr('action', '${contextPath}/newsAreaContent/' + seq);
			form.submit();
    	}

    </script>

</head>

<div class="wrapper">
        <section class="systalk-news">
            <div class="title">
                <div>
                    <h2><s:message code="news.title"/></h2>	<!-- 多語系  -->
                </div>
            </div>
            <div class="container">
                <div class="select-box">
                    <div class="select-item select-sort">
                        <select id="typeCombo" class="selectpicker filter">
                        	<option value=""><s:message code="news.typeCombo.option.default"/></option>	<!-- 分類  -->
                        	<c:forEach var="option" items="${viewForm.typeCombo.options}" varStatus="state">
                        		<option value="${option.value}">${option.label}</option>
                        	</c:forEach>
                        </select>
                        <span id="showTypeErr" class="form-control" style="display:none;"></span>
                        <span class="invalid-feedback"><s:message code="news.typeCombo.validate.error"/></span> <!-- 請選擇分類  -->
                    </div>
                    <div class="select-item select-year">
                    
                        <select id="dateYearCombo" class="selectpicker filter">
                            <option value=""><s:message code="news.dateYearCombo.option.default"/></option>	<!-- 年份  -->
                            <c:forEach var="option" items="${viewForm.dateCombo.options}" varStatus="state">
                        		<option value="${option.value}">${option.label}</option>
                        	</c:forEach>
                        </select>
                    </div>
                    <div class="select-item">
                        <div id="autocomplete" class="autocomplete">
                        	<span class="searchIcon" onclick="doQueryNewsAction();"></span>
                        	<!-- 請輸入關鍵字搜尋 -->
                            <input id="searchTextInput" class="autocomplete-input" placeholder="<s:message code="news.search.placeholder"/>" aria-label="<s:message code="news.search.placeholder"/>">	
                        </div> 
                    </div>
                </div>
            </div>
        </section>

        <sf:form id="queryForm" name="queryForm" modelAttribute="viewForm" autocomplete="off">
			<sf:input type="hidden" path="typeSeq" id="typeSeq" name="typeSeq"/>
			<sf:input type="hidden" path="dateYear" id="dateYear" name="dateYear"/>
			<sf:input type="hidden" path="searchText" id="searchText" name="searchText"/>
			<sf:input type="hidden" path="pageObject.startPage" id="startPage" name="startPage"/>
		</sf:form>

        <!-- new article -->
        <section class="new-article">
            <div class="container">
				<div class="article-list">
					<div class="row" id="newsAreaItem">
					<c:forEach var="newsAreaBean" items="${viewForm.newsAreaBeanList}"
						varStatus="state">
						<div class="col-sm-12 col-md-4">
							<div class="card-article">
								<a href="#" onclick="doGoToNewsAreaContentAction(${newsAreaBean.seq});">
									<div class="tag">${newsAreaBean.displayType}</div>
									<div class="article-img article-img-01"
										style="background-image: url('${newsAreaBean.url}');"></div>
									<div class="title">
										<img src="${contextPath}/images/icon-time@2x.png" alt="發表時間" title="發表時間"> <span class="date">${newsAreaBean.pushingDate}</span>
										<p>${newsAreaBean.displayTitle}</p>
									</div>
								</a>
							</div>
						</div>
					</c:forEach>

					<!--  
										<c:if test="${empty viewForm.newsAreaBeanList}">
											<div class="col-sm-12 col-md-4">
												<div class="title">
													查無資料。
												</div>
											</div>
										</c:if>
										-->				
					</div>
                    
					<!--  
                     <div class="col-sm-12 col-md-4">
                            <div class="card-article">
                                <a href="">
                                    <div class="tag">活動</div>
                                    <div class="article-img"></div>
                                    <div class="title">
                                        <img src="images/icon-time@2x.png" alt="發表時間" title="發表時間">
                                        <span class="date">2019/1/16</span>
                                        <p>標題會超過字數標題會超過字數標題會超過字數標題會超過字數標題會超過字數標題會超過字數標題會超過字數</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="col-sm-12 col-md-4">
                            <div class="card-article">
                                <a href="">
                                    <div class="tag">新聞</div>
                                    <div class="article-img"></div>
                                    <div class="title">
                                        <img src="images/icon-time@2x.png" alt="發表時間" title="發表時間">
                                        <span class="date">2019/1/16</span>
                                        <p>從GUI到CUI，新一代Chatbot給你直覺、高效體驗！</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="col-sm-12 col-md-4">
                            <div class="card-article">
                                <a href="">
                                    <div class="tag">活動</div>
                                    <div class="article-img"></div>
                                    <div class="title">
                                        <img src="images/icon-time@2x.png" alt="發表時間" title="發表時間">
                                        <span class="date">2019/1/16</span>
                                        <p>標題會超過字數標題會超過字數標題會超過字數標題會超過字數標題會超過字數標題會超過字數標題會超過字數</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="col-sm-12 col-md-4">
                            <div class="card-article">
                                <a href="">
                                    <div class="tag">新聞</div>
                                    <div class="article-img"></div>
                                    <div class="title">
                                        <img src="images/icon-time@2x.png" alt="發表時間" title="發表時間">
                                        <span class="date">2019/1/16</span>
                                        <p>標題會超過字數標題會超過字數標題會超過字數標題會超過字數標題會超過字數標題會超過字數標題會超過字數</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="col-sm-12 col-md-4">
                            <div class="card-article">
                                <a href="">
                                    <div class="tag">新聞</div>
                                    <div class="article-img"></div>
                                    <div class="title">
                                        <img src="images/icon-time@2x.png" alt="發表時間" title="發表時間">
                                        <span class="date">2019/1/16</span>
                                        <p>標題會超過字數標題會超過字數標題會超過字數標題會超過字數標題會超過字數標題會超過字數標題會超過字數</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                   
                     <nav class="mt-3" aria-label="Page navigation">
                        <ul class="pagination pagination-style justify-content-center">
                            <li class="page-item"><a class="page-link" href="#">
                            <img src="images/icon-left@2x.png" alt="上一頁" title="上一頁"></a></li>
                            <li class="page-item"><a class="page-link num active" href="#">1</a></li>
                            <li class="page-item"><a class="page-link num" href="#">2</a></li>
                            <li class="page-item"><a class="page-link num" href="#">3</a></li>
                            <li class="page-item"><a class="page-link" href="#">
                            <img src="images/icon-right@2x.png" alt="下一頁" title="下一頁">
                            </a></li>
                        </ul>
                    </nav>
                     -->

			<nav class="mt-3" aria-label="Page navigation">
				<div align="center">
					<ul class="pagination d-flex justify-content-center"></ul>
				</div>
			</nav>

        </section>
        <!-- /new article end -->
        
    <form id="doGoToNewsAreaContentForm" name="doGoToNewsAreaContentForm"">

	</form>

</div>
    
    <script type="text/javascript" src="${contextPath}/js/select.js"></script>
</html>
