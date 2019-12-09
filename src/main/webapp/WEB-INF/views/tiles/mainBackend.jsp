<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
 
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=1100px, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="format-detection" content="telephone=no">
	
	<!-- SET: FAVICON -->
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" />
	<!-- END: FAVICON -->

    <script type="text/javascript"> var contextPath="${pageContext.request.contextPath}"</script>

	<!-- 前臺靜態資源 先註解掉 0924 -->
    <!-- Bootstrap -->
    <!--<link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet"> -->
    <!--<link href="<%=request.getContextPath()%>/css/slick.css" rel="stylesheet"> -->
    <!--<link href="<%=request.getContextPath()%>/css/slick-theme.css" rel="stylesheet"> -->
    <!--<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet"> -->
    <!--<link href="<%=request.getContextPath()%>/css/article.css" rel="stylesheet"> -->
    
    <!--<link href="<%=request.getContextPath()%>/css/plugin/bootstrap.min.css" rel="stylesheet" type="text/css" media="all" /> -->
	<!--<link href="<%=request.getContextPath()%>/css/plugin/bootstrap-datepicker.min.css" rel="stylesheet" /> -->
	<!-- <link href="<%=request.getContextPath()%>/css/plugin/datatable.css" rel="stylesheet" type="text/css" media="all" /> -->
	
	<!-- dataTable 08.14 -->
	<!--<link href="<%=request.getContextPath()%>/css/plugin/datatables.min.css" rel="stylesheet" type="text/css" media="all" /> -->
	
	<!-- 排序拖拉css 08.14 -->
	<link href="<%=request.getContextPath()%>/css/plugin/jquery-ui.min.css" rel="stylesheet" type="text/css" media="all" /> 
	
	
	
	
	<!-- <link href="<%=request.getContextPath()%>/css/plugin/select.css" rel="stylesheet" type="text/css" media="all" /> -->
	<!-- <link href="<%=request.getContextPath()%>/css/plugin/style.css" rel="stylesheet" type="text/css" media="all" /> -->

	
    <!--select-->
    <!-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/select.css"> -->

    <!-- jQuery -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/popper.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/slick.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/main.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/tinymce/tinymce.min.js"></script>
    <!-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script> -->
    <!-- dataTable 08.14 -->

    
    <!-- 排序拖拉js 08.14 -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/jquery-ui.min.js"></script>
    
 
    <!--datepicker 07.04 -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/bootstrap-datepicker.min.js"></script>

    <!--select-->
    <script type="text/javascript" src="<%=request.getContextPath()%>/backendStatic/js/select.js"></script>
    

    <!-- 後臺的靜態資源 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/backendStatic/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/backendStatic/css/icon.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/backendStatic/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/backendStatic/css/select.css">

    
    <!--datatable-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/backendStatic/css/datatable.css">
    
    
    <!--date 會影響datable css (上一頁 下一頁), 移至編輯頁面引入-->
    <!-- <link rel="stylesheet" href="<%=request.getContextPath()%>/backendStatic/build/css/bootstrap-datetimepicker-standalone.css"> -->
    <!-- <link rel="stylesheet" href="<%=request.getContextPath()%>/backendStatic/build/css/bootstrap-datetimepicker.min.css"> -->
    
    <script src="<%=request.getContextPath()%>/backendStatic/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/backendStatic/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/backendStatic/js/jquery-1.12.3.min.js"></script>
    <script src="<%=request.getContextPath()%>/backendStatic/js/moment.js"></script>
    <script src="<%=request.getContextPath()%>/backendStatic/build/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/backendStatic/build/js/bootstrap-datetimepicker.min.js"></script>
    

    
    <!-- datatable -->
    <script src='<%=request.getContextPath()%>/backendStatic/js/jquery.dataTables.min.js'></script>
    <script src="<%=request.getContextPath()%>/backendStatic/js/datatable.js"></script>
    
    
<title><tiles:insertAttribute name="title" /></title>

</head>

  
<body>
        <header class="navbar fixed-top" id="header">
            <tiles:insertAttribute name="header" />
        </header>
    
        <section id="site-content">
            <tiles:insertAttribute name="body" />
        </section>
        
        <footer class="text-center text-dark" id="footer">
            <tiles:insertAttribute name="footer" />
        </footer>
</body>

    <!-- 未存檔時離開-->
    <div class="modal fade" id="noSave" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog dally_black" role="document">
            <div class="modal-content">
                <div class="modal-body modal-space">
                    <div class="modal-box">
                        <div class="modal-tit">離開此頁後將不會儲存未儲存的內容，確定要離開嗎?</div>
                    </div>
                    <div class="col-md-12">
                        <div class="text-center step-button mt-4">
                            <a href="#" class="btn line-blue" data-dismiss="modal" aria-label="Close">取消</a>
                            <a href="#" onclick="doGoBack();" class="btn full-blue" data-dismiss="modal" aria-label="Close">確定</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 未存檔時離開 end-->
    
    <!-- modal -->
    <!-- 上傳成功-->
    <div class="modal fade" id="UploadSuccess" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog dally_black" role="document">
            <div class="modal-content">
                <div class="modal-body modal-space">
                    <div class="text-center modal-box">
                        <div class="modal-tit">
                            <i class="icons-bomb-hook i-icon d-block"></i>檔案上傳成功。
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="text-center step-button mt-5">
                            <a href="#" class="btn full-blue" data-dismiss="modal" aria-label="Close">確定</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 上傳成功 end-->
    
    <!-- 上傳失敗-->
    <div class="modal fade" id="UploadFail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog dally_black" role="document">
            <div class="modal-content">
                <div class="modal-body modal-space">
                    <div class="text-center modal-box">
                        <div class="modal-tit">
                            <i class="icons-bomb-cancel i-icon d-block"></i>
                            上傳失敗，檔案格式錯誤。</div>
                    </div>
                    <div class="col-md-12">
                        <div class="text-center step-button mt-5">
                            <a href="#" class="btn full-blue" data-dismiss="modal" aria-label="Close">確定</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 上傳失敗 end-->
    <!-- modal END-->
 


