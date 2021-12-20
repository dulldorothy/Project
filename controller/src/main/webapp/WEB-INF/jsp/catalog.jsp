<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="prop.locale" var="loc"/>
    <fmt:message bundle="${loc}" key="catalog.goods" var="goods"/>
    <fmt:message bundle="${loc}" key="catalog.electronics" var="electronics"/>
    <fmt:message bundle="${loc}" key="catalog.furniture" var="furniture"/>
    <fmt:message bundle="${loc}" key="catalog.clothing" var="clothing"/>
    <fmt:message bundle="${loc}" key="catalog.pet.supplies" var="pet"/>
    <fmt:message bundle="${loc}" key="catalog.household.products" var="household"/>
    <fmt:message bundle="${loc}" key="catalog.kitchen.furniture" var="kitchen"/>
</head>
<body>
    
    <%@ include file="header.jsp" %>
    <div class="main-container">
        <div class="category-container">
            <div class="category-element">
                <a href="${pageContext.request.contextPath}/Controller?page=searchresult&command=go_to_full_catalog&currentPage=1" class="category-link"><img src="pic/все.png" alt="" class="category-image"></a>
                <span class="category-name"><c:out value="${goods}"/></span>
            </div>
            <div class="category-element">
                <a href="${pageContext.request.contextPath}/Controller?page=searchresult&command=go_to_tag_catalog&currentPage=1&tag=goods" class="category-link"><img src="pic/чайник.png" alt="" class="category-image"></a>
                <span class="category-name"><c:out value="${kitchen}"/></span>
            </div>
            <div class="category-element">
                <a href="${pageContext.request.contextPath}/Controller?page=searchresult&command=go_to_tag_catalog&currentPage=1&tag=householdproducts" class="category-link"><img src="pic/пылесос.png" alt="" class="category-image"></a>
                <span class="category-name"><c:out value="${household}"/></span>
            </div>
            <div class="category-element">
                <a href="${pageContext.request.contextPath}/Controller?page=searchresult&command=go_to_tag_catalog&currentPage=1&tag=electronics" class="category-link"><img src="pic/телеофн.png" alt="" class="category-image"></a>
                <span class="category-name"><c:out value="${electronics}"/></span>
            </div>
            <div class="category-element">
                <a href="${pageContext.request.contextPath}/Controller?page=searchresult&command=go_to_tag_catalog&currentPage=1&tag=petsupplies" class="category-link"><img src="pic/кот.png" alt="" class="category-image"></a>
                <span class="category-name"><c:out value="${pet}"/></span>
            </div>
            <div class="category-element">
                <a href="${pageContext.request.contextPath}/Controller?page=searchresult&command=go_to_tag_catalog&currentPage=1&tag=furniture" class="category-link"><img src="pic/кресло.png" alt="" class="category-image"></a>
                <span class="category-name"><c:out value="${furniture}"/></span>
            </div>
            <div class="category-element">
                <a href="${pageContext.request.contextPath}/Controller?page=searchresult&command=go_to_tag_catalog&currentPage=1&tag=clothing" class="category-link"><img src="pic/майка.png" alt="" class="category-image"></a>
                <span class="category-name"><c:out value="${clothing}"/></span>
            </div>
            
        </div>
    </div>
    <a href="${pageContext.request.contextPath}/Controller?page=lotcreation&command=goToPage"></a>
    <%@ include file="footer.jsp" %>
</body>
</html>