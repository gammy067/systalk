<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<html lang="zh-TW">

<head>
    <!-- Google Tag Manager -->
    <script>
        (function (w, d, s, l, i) {
            w[l] = w[l] || [];
            w[l].push({
                'gtm.start': new Date().getTime(),
                event: 'gtm.js'
            });
            var f = d.getElementsByTagName(s)[0],
                j = d.createElement(s),
                dl = l != 'dataLayer' ? '&l=' + l : '';
            j.async = true;
            j.src =
                'https://www.googletagmanager.com/gtm.js?id=' + i + dl;
            f.parentNode.insertBefore(j, f);
        })(window, document, 'script', 'dataLayer', 'GTM-52MJJHC');
    </script>
    <!-- End Google Tag Manager -->
    
    <title><tiles:insertAttribute name="title" /></title>

    <!-- meta 資料 index chat rpa 各別管理-->
    <tiles:insertAttribute name="meta" ignore="true"/>
    
 	<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/favicon.ico" />
 	
    <script type="text/javascript"> var contextPath = "${pageContext.request.contextPath}"</script>
    
    <!-- 
    <link href="<%=request.getContextPath()%>/css/slick.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/slick-theme.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/plugin/style.css" rel="stylesheet" type="text/css" media="all" />
    -->

    <!-- 整理後的css 08.16 -->
    <!-- Bootstrap -->
    <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
	<!--select-->
	<!--<link rel="stylesheet" href="<%=request.getContextPath()%>/css/select.css">-->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/css/plugin/bootstrap-datepicker.min.css" rel="stylesheet" />
   	<!-- dataTable 08.14 -->
	<link href="<%=request.getContextPath()%>/css/plugin/datatables.min.css" rel="stylesheet" type="text/css" media="all" />
	<!-- 分頁用到css -->
	<link href="<%=request.getContextPath()%>/css/plugin/datatable.css" rel="stylesheet" type="text/css" media="all" />
	
	<!-- <link href="<%=request.getContextPath()%>/js/slick-1.8.1/slick/slick.css" rel="stylesheet" type="text/css" /> -->
	<!-- <link href="<%=request.getContextPath()%>/js/slick-1.8.1/slick/slick-theme.css" rel="stylesheet" type="text/css" /> -->
	<!-- <link href="<%=request.getContextPath()%>/css/plugin/style.css" rel="stylesheet" type="text/css" media="all" /> -->
    <link href="<%=request.getContextPath()%>/css/plugin/select.css" rel="stylesheet" type="text/css" media="all" />
    
    <!-- slide css -->
    <link href="<%=request.getContextPath()%>/css/slick.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/css/slick-theme.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
	

    <!-- <link href="<%=request.getContextPath()%>/css/plugin/bootstrap.min.css" rel="stylesheet" type="text/css" media="all" /> -->
	<!-- <link href="<%=request.getContextPath()%>/css/plugin/bootstrap-datepicker.min.css" rel="stylesheet" /> -->
	<!-- <link href="<%=request.getContextPath()%>/css/plugin/bootstrap-tagsinput.css" rel="stylesheet" type="text/css" media="all" /> -->
	<!-- <link href="<%=request.getContextPath()%>/css/plugin/prism.css" rel="stylesheet" type="text/css" /> -->
	<!-- <link href="<%=request.getContextPath()%>/css/plugin/datatable.css" rel="stylesheet" type="text/css" media="all" /> -->
	<!-- <link href="<%=request.getContextPath()%>/css/plugin/style.css" rel="stylesheet" type="text/css" media="all" /> -->

	<!-- 整理後的js 08.16 (思拓給的js檔)-->
    <!-- jQuery -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.js"></script>
    <!--select-->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/wow.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/popper.min.js"></script>
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
    
    <!--
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/tinymce/tinymce.min.js"></script>
    -->
    
    <!--datepicker 07.04 -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/bootstrap-datepicker.min.js"></script>
    <!-- 分頁處理 js -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/jquery.twbsPagination.min.js"></script>
    
    <!-- 2019.10.09 新增的js -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/owl.carousel.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/owl.js"></script>
    
    <!-- 強制使用https (先停用 08.26)
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/_force_https.js"></script>
    -->
     
</head>
<div class="wrapper">
	<body>
	    <!-- Google Tag Manager (noscript) -->
	    <noscript><iframe src="https://www.googletagmanager.com/ns.html?id=GTM-52MJJHC" height="0" width="0"
	            style="display:none;visibility:hidden"></iframe></noscript>
	    <!-- End Google Tag Manager (noscript) -->
	
	        <header id="header">
	            <tiles:insertAttribute name="header" />
	        </header>
	
	        <tiles:insertAttribute name="body" />
	         
	        <footer id="footer">
	            <tiles:insertAttribute name="footer" />
	        </footer>
	
	</body>
</div>

	 <script type="text/javascript">
            
            new WOW({
                mobile: false
            }).init();
            
            var myToggle= true;
            $('.btn-collapse').click(function(){
            if(myToggle){
                $('.img-collapse').slideDown(900);
                $('.icon-collapse').attr('src','${contextPath}/images/icon-minus@2x.png');
                $('.collapse-txt').text('<s:message code="chat.section02.collapse.close"/>');	<!-- 收合 -->
            }else{
                $('.img-collapse').slideUp(900);
                $('.icon-collapse').attr('src','${contextPath}/images/icon-more@2x.png');
                $('.collapse-txt').text('<s:message code="chat.section02.collapse.open"/>');	<!-- 展開 -->
            }
                myToggle=! myToggle;
            });
            
     </script>
	<!-- 共通事件 -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/custom.js"></script>
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/main.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

</html>