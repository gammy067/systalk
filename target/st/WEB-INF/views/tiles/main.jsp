<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>

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
    <link rel="canonical" href=" https://systalk.ai"/>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="keywords" content="人工智慧, AI, 聊天機器人, chatbot, NLU,自然語言理解, 語音機器人,語音服務,客服機器人,STT, TTS, 語音辨識" />
    <meta name="description"
        content="SysTalk.ai專注於台灣本地AI解決方案，提供企業完整AI導入服務，讓AI成為最有力的數位勞動力幫手。SysTalk.Chat提供「雙腦一流程」NLU與FAQ雙對話大腦引擎加上FLOW流程控管串接社群媒體與自媒體管道，提供交談式服務，完整滿足客戶需求。">
    <meta property="og:title" content="SysTalk.ai讓 AI 成為數位勞動力主角｜AI 解決方案專家">

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
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugin/tinymce/tinymce.min.js"></script>
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
     

    
<title><tiles:insertAttribute name="title" /></title>
</head>
  
<body>
    <!-- Google Tag Manager (noscript) -->
    <noscript><iframe src="https://www.googletagmanager.com/ns.html?id=GTM-52MJJHC" height="0" width="0"
            style="display:none;visibility:hidden"></iframe></noscript>
    <!-- End Google Tag Manager (noscript) -->
	<div class="wrapper">
        <header id="header">
            <tiles:insertAttribute name="header" />
        </header>
    
        <section id="site-content">
            <tiles:insertAttribute name="body" />
        </section>
         
        <footer id="footer">
            <tiles:insertAttribute name="footer" />
        </footer>
	</div>
</body>

	 <script type="text/javascript">
            
            new WOW({
                mobile: false
            }).init();
            
            var myToggle= true;
            $('.btn-collapse').click(function(){
            if(myToggle){
                $('.img-collapse').slideDown(900);
                $('.icon-collapse').attr('src','${contextPath}/images/icon-minus@2x.png');
                $('.collapse-txt').text('收合');
            }else{
                $('.img-collapse').slideUp(900);
                $('.icon-collapse').attr('src','${contextPath}/images/icon-more@2x.png');
                $('.collapse-txt').text('展開');
            }
                myToggle=! myToggle;
            });
            
     </script>
	<!-- 共通事件 -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/custom.js"></script>
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/main.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>

</html>