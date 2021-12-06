<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
    <link href="https://fonts.googleapis.com/css2?family=Inconsolata&display=swap" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/test.css"/>
     <fmt:setLocale value="${sessionScope.locale}"/>
     <fmt:setBundle basename="prop.locale" var="loc"/>
     <fmt:message bundle="${loc}" key="register.password" var="password"/>
     <fmt:message bundle="${loc}" key="register.e-mail" var="email"/>
     <fmt:message bundle="${loc}" key="register.register" var="register"/>
     <fmt:message bundle="${loc}" key="userpage.firstname" var="firstname"/>
     <fmt:message bundle="${loc}" key="userpage.last.name" var="lastname"/>
     <fmt:message bundle="${loc}" key="register.username" var="username"/>
</head>
<body>
    <div class="login-form-container">
    <form class="login-form-wrapper" action="/Controller" method="post">
            <input type="text" name="username" placeholder="<c:out value="${username}"/>"/>
            <input type="email" name="email" placeholder="<c:out value="${email}"/>"/>
            <input type="password" name="password" placeholder="<c:out value="${password}"/>"/>
            <div class="names-input">
                <input type="text" name="firstname" placeholder="<c:out value="${firstname}"/>"/>
                <input type="text" name="lastname" placeholder="<c:out value="${lastname}"/>"/>
            </div>
            <input type="hidden" name="command" value="register"/>
            <button class="confrim-button" type="submit"><c:out value="${register}"/></button>
    </form>
    <a> ${requestScope.error} </a>
</div>
</body> 
</html>