<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript">

$(function () {
	// 首頁footer 
	$('footer').removeClass('text-dark');
});

function loginAction() {
	$('#loginForm').submit();
}

</script>

<title>Login</title>
</head>

<body class="index-bg">
    <main>
        <div class="d-table-cell mx-auto text-center align-middle login-content">
            <div class="login-box row mx-auto">
                <div class="col-12">
                    <h3 class="font-weight-bold color-navy des-text-30 text-blue">後臺維護登入</h3>
                </div>
                <div class="index-input mx-auto">
                <form action='<spring:url value="/loginAction"/>' id="loginForm" method="post">
                	<input type="text" class="mx-auto mb-3" placeholder="使用者名稱" name="username">
                	
                	<input type="password" class="mx-auto" placeholder="密碼" name="password">
                	
                <!-- invalid credentials error msg -->
        			<c:if test="${not empty error}">
            			<p class="text-left text-red">${error}</p>
        			</c:if>
        			
        		<div class="col-12">
					<!--登入  -->
                    <a href="#" onclick="javascript:loginAction();" class="btn full-blue">登入</a>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </div> 
                
                </form>
            </div>
        </div>
    </main>
</body>
</html>