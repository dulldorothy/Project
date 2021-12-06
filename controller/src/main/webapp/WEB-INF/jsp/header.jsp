

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false"%>
<html>
<head>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inconsolata&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/headercss.css">
    <script src="js/js.js"></script>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="prop.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="testEN" var="testEN"/>
    <fmt:message bundle="${loc}" key="testRU" var="testRU"/>
    <fmt:message bundle="${loc}" key="header.catalog" var="catalog"/>
    <fmt:message bundle="${loc}" key="header.login" var="login"/>
    <fmt:message bundle="${loc}" key="header.register" var="register"/>
    <fmt:message bundle="${loc}" key="header.bookmarks" var="bookmarks"/>
    <fmt:message bundle="${loc}" key="header.messages" var="messages"/>
    <fmt:message bundle="${loc}" key="header.userlist" var="userlist"/>
</head>
<body>
<header class="header">

    <div id="product" data-prodnumber="${sessionScope.username}">
    <nav class="navigation"> 
        <div class="navigation-container">
            <div class="navigation-div-main"><a href="${pageContext.request.contextPath}/Controller?page=index&command=change_locale&locale=en" class="header-logo"> En</a></div>
            <div class="navigation-div-main"><a href="${pageContext.request.contextPath}/Controller?page=index&command=change_locale&locale=ru" class="header-logo"> Ru</a></div>

            <div class="navigation-pages">
             <ul class="pages-list">
                 <li class="pages-element"><a href="${pageContext.request.contextPath}/Controller?page=catalog&command=go_to_page" class="header-logo"> <c:out value="${catalog}"/></a></li>
                 <c:if test="${sessionScope.user.role == 'admin'}">
                 <li class="pages-element"><a href="${pageContext.request.contextPath}/Controller?page=userlist&command=go_to_user_list&currentPage=1" class="header-logo"> <c:out value="${userlist}"/></a></li>
                </c:if>
                <c:choose>
                   <c:when test="${sessionScope.user.role == 'guest'}">
                   </c:when>
                   <c:otherwise>
                        <a href="${pageContext.request.contextPath}/Controller?page=messages&command=go_to_unread_messages&currentPage=1"><c:out value="${messages}"/></a>


                        <a href="${pageContext.request.contextPath}/Controller?page=searchresult&command=go_to_user_bookmarks&currentPage=1"><c:out value="${bookmarks}"/></a>
                   </c:otherwise>
                </c:choose>
                 
             </ul>
            </div>
            <div class="log-navigator" id="userplaceholder">
               <c:choose>
                   <c:when test="${sessionScope.user.role == 'guest'}">
                    <ul class="pages-list" id="log-reg">
                        <li class="pages-element">   <a href="${pageContext.request.contextPath}/Controller?page=login&command=go_to_page" class="list-link"><c:out value="${login}"/></a></li>
                        <li class="pages-element"> <a href="${pageContext.request.contextPath}/Controller?page=register&command=go_to_page" class="list-link"><c:out value="${register}"/></a></li>
                    </ul>
                   </c:when>
                   <c:otherwise>
                        <a href="${pageContext.request.contextPath}/Controller?page=userpage&command=go_to_page">${sessionScope.user.getUserName()}</a>
                   </c:otherwise>
               </c:choose>
                
                
            </div>
        </div>
    </nav>
</header>
</body>
</html>