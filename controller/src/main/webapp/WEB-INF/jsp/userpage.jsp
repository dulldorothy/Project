<%@ page language="java" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Document</title>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="prop.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="userpage.addlot" var="addlot"/>
    <fmt:message bundle="${loc}" key="userpage.firstname" var="firstname"/>
    <fmt:message bundle="${loc}" key="userpage.change.button" var="change"/>
    <fmt:message bundle="${loc}" key="userpage.last.name" var="lastname"/>
    <fmt:message bundle="${loc}" key="userpage.logout" var="logout"/>
    <fmt:message bundle="${loc}" key="userpage.old.password" var="oldpass"/>
    <fmt:message bundle="${loc}" key="userpage.userlots" var="userlots"/>
    <fmt:message bundle="${loc}" key="userpage.new.password" var="newpass"/>
</head>
<body class="body-main">
    <%@ include file="header.jsp" %>
    <div class="main-container">
        <div class="profile-container-wrapper">
            <div class="profile-pic-container">
                <img src="data:image/jpg;base64,${sessionScope.user.image}" alt="" class="profile-pic">
                <form action="/Controller" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="command" value="change_user_image"/>
                    <input type="file" name="image"/>
                    <button type="submit"> <c:out value="${change}"/></button>
                </form>
            </div>
            <div class="profile-info-container">
                
                <p><c:out value="${firstname}"/>: ${sessionScope.user.userName}</p>
               
                <form action="/Controller" method="post">
                    <input type="hidden" name="command" value="change_user_firstname"/>
                    <input type="text" name="firstname" value="${sessionScope.user.firstName}"/>
                    <button type="submit"> <c:out value="${change}"/></button>
                </form>
                <p><c:out value="${lastname}"/>: ${sessionScope.user.firstName}</p>
                <form action="/Controller" method="post">
                    <input type="hidden" name="command" value="change_user_lastname"/>
                    <input type="text" name="lastname" value="${sessionScope.user.lastName}"/>
                    <button type="submit"> <c:out value="${change}"/></button>
                </form>
                <form action="/Controller" method="post">
                    <input type="hidden" name="command" value="change_user_password"/>
                    <input type="password" name="oldpassword" placeholder="${oldpass}"/>
                    <input type="password" name="newpassword" placeholder="${newpass}"/>
                    <button type="submit"> <c:out value="${change}"/></button>
                    <span>${requestScope.errorMessage}</span>
                </form>
            
                
            </div>
            
            <a href="${pageContext.request.contextPath}/Controller?command=logout"><c:out value="${logout}"/></a>
            <a href="${pageContext.request.contextPath}/Controller?page=lotcreation&command=go_to_page"><c:out value="${addlot}"/></a>
            <a href="${pageContext.request.contextPath}/Controller?page=lotcreation&command=go_to_user_lots&currentPage=1"><c:out value="${userlots}"/></a>
        </div>
        
    </div>
   
    <%@ include file="footer.jsp" %>
</body>
</html>