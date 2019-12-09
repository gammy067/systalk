<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<title>404</title>
<body>
    404找不到網頁<span id="counter">3</span>秒後跳轉至首頁...
    <script>
        setInterval(function() {
            var div = document.querySelector("#counter");
            var count = div.textContent * 1 - 1;
            div.textContent = count;
            if (count <= 0) {
                window.location.replace("${contextPath}/st/index");
            }
        }, 1000);
    </script>
</body>
</html>