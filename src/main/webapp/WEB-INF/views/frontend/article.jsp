<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <script>
	    function doArticleQueryAction(){
	    	var postUrl = "${contextPath}/doArticleQueryAction";
			postUrl = postUrl +"?"+'${_csrf.parameterName}=${_csrf.token}';
    	    jQuery.ajax({
            type : "post",
            url : postUrl,
            cache:false,
            async: false,
            data : {
            	queryType : $('#queryType').val(),
            	queryStr : $('#queryStr').val(),
            	querySort : $('#querySort').val()
            }
            }).done(function(data) {
                var obj = jQuery.parseJSON(data);
                var str = obj.data
        	    $('#articleForm').html(str);
            });
        }
    </script>


<title>Index</title>
</head>

<section class="popular-article padding-header mt-3">
	<div class="container">
		<div class="box">
			<div class="row">
				<div class="col-md-12">

					<sf:form name="articleForm" id="articleForm" modelAttribute="articleQueryCondition" autocomplete="off">
						<table>
							<tr>
								<td>queryType</td>
								<td>
									<sf:select path="queryType" id="queryType" class="filter combo" >
   										<sf:options items="${articleViewForm.typeCombo.options}" itemValue="value" itemLabel="label" />
									</sf:select>
								</td>
							</tr>
							<tr>
								<td>queryStr</td>
								<td><sf:input path="queryStr" /></td>
							</tr>
							<tr>
								<td>querySort</td>
								<td>
									<sf:select path="querySort"  class="filter combo" >
   										<sf:options items="${articleViewForm.typeCombo.options}" itemValue="value" itemLabel="label"/>
									</sf:select>
								</td>
							</tr>
						</table>
					</sf:form>

					<div class="text-center">
						<button type="button " onclick="doArticleQueryAction();">查詢</button>
					</div>
				</div>
			</div>
		</div>


	</div>
</section>

</html>
