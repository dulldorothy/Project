<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inconsolata&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/headercss.css">
    <script src="js/js.js"></script>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
</head>


<header class="header">
    <div id="product" data-prodnumber="${sessionScope.username}">
    <nav class="navigation"> 
        <div class="navigation-container">
            <div class="navigation-div-main"><a href="${pageContext.request.contextPath}/Controller?page=index&command=go_to_page" class="header-logo"> ePay</a></div>
            <div class="navigation-pages">
             <ul class="pages-list">
                 <li class="pages-element"><a href="${pageContext.request.contextPath}/Controller?page=catalog&command=go_to_page" class="header-logo"> Catalog</a></li>
                 <c:choose>
                   <c:when test="${sessionScope.user == null}">
                   </c:when>
                   <c:otherwise>
                        <a href="${pageContext.request.contextPath}/Controller?page=searchresult&command=go_to_user_bookmarks&currentPage=1">Bookmarks</a>
                   </c:otherwise>
               </c:choose>
                 
             </ul>
            </div>
            <div class="log-navigator" id="userplaceholder">
               <c:choose>
                   <c:when test="${sessionScope.user == null}">
                    <ul class="pages-list" id="log-reg">
                        <li class="pages-element">   <a href="${pageContext.request.contextPath}/Controller?page=login&command=go_to_page" class="list-link">Login</a></li>
                        <li class="pages-element"> <a href="${pageContext.request.contextPath}/Controller?page=register&command=go_to_page" class="list-link">Register</a></li>
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

