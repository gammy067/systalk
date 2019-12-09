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
        });

    </script>

</head>

<div class="wrapper">
        <!-- new article -->
        <section class="news-content">
            <div class="container">
                <div class="info py-2">
                    <div class="desc d-flex align-items-center">
                        <div class="justify-content-start mr-auto">
                            <span class="sort">${viewForm.newsAreaBean.displayType}</span>
                        </div>
                        <div class="justify-content-end">
                            <img src="${contextPath}/images/icon-time@2x.png" alt="發表時間" title="發表時間">
                            <span class="date">${viewForm.newsAreaBean.pushingDate}</span>
                        </div>
                    </div>
                </div>
                <div class="article">
                    <h2 class="py-2">${viewForm.newsAreaBean.displayTitle}</h2>
                    <!-- 暫時先不放標題圖片 0918 
                    <img src="${viewForm.newsAreaBean.url}" alt="" title="" class="img-fluid py-3">
                    -->
                    <br>
                    
                    ${viewForm.newsAreaBean.displayContent}
   
                    <!-- 影片範例 -->
                    <!-- <div class="embed-responsive embed-responsive-16by9 my-4">
                    <iframe class="embed-responsive-item" src="https://www.youtube.com/embed/RE87rQkXdNw" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                    </div> -->
                </div>
                <div class="text-center article-button">
                    <button type="button" class="btn btn-primary" onclick="location.href='${contextPath}/newsArea';"><s:message code="news.content.back.button"/></button> <!-- 回上一頁 -->
                </div>
            </div>
        </section>
        <!-- /new article end -->
</div>

    <script type="text/javascript">
        $(function () {
            /* 按下GoTop按鈕時的事件 */
            $('#GoTop').click(function () {
                $('html,body').animate({
                    scrollTop: 0
                }, 'slow'); /* 返回到最頂上 */
                return false;
            });

            /* 偵測卷軸滑動時，往下滑超過400px就讓GoTop按鈕出現 */
            $(window).scroll(function () {
                if ($(this).scrollTop() > 400) {
                    $('#GoTop').fadeIn();
                } else {
                    $('#GoTop').fadeOut();
                }
            });
        });
    </script>
    
    <script type="text/javascript" src="${contextPath}/js/select.js"></script>

</html>
