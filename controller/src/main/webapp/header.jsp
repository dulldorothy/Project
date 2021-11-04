<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <div class="navigation-div-main"><a href="${pageContext.request.contextPath}/Controller?page=index&command=goToPage" class="header-logo"> ePay</a></div>
            <div class="navigation-pages">
             <ul class="pages-list">
                 <li class="pages-element"><a href="${pageContext.request.contextPath}/Controller?page=catalog&command=goToPage" class="header-logo"> Catalog</a></li>
                 
             </ul>
            </div>
            <div class="log-navigator" id="userplaceholder">
                <ul class="pages-list" id="log-reg">
                    <li class="pages-element">   <a href="${pageContext.request.contextPath}/Controller?page=login&command=goToPage" class="list-link">Login</a></li>
                    <li class="pages-element"> <a href="${pageContext.request.contextPath}/Controller?page=register&command=goToPage" class="list-link">Register</a></li>
                </ul>
                
                
            </div>
        </div>
    </nav>
</header>
<script defer type="module" src="js/js.js">    </script>
