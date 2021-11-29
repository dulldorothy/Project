<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    
</head>
<body>
    
    <%@ include file="header.jsp" %>
    <div class="main-container">
        <div class="category-container">
            <div class="category-element">
                <a href="${pageContext.request.contextPath}/Controller?page=searchresult&command=go_to_full_catalog&currentPage=1" class="category-link"><img src="pic/чайник.png" alt="" class="category-image"></a>
                <span class="category-name">Kithcen things</span>
            </div>
            <div class="category-element">
                <a href="${pageContext.request.contextPath}/Controller?page=searchresult&command=go_to_tag_catalog&currentPage=1&tag=goods" class="category-link"><img src="pic/чайник.png" alt="" class="category-image"></a>
                <span class="category-name">Goods</span>
            </div>
            <div class="category-element">
                <a href="${pageContext.request.contextPath}/Controller?page=searchresult&command=go_to_tag_catalog&currentPage=1&tag=householdproducts" class="category-link"><img src="pic/чайник.png" alt="" class="category-image"></a>
                <span class="category-name">Household products</span>
            </div>
            <div class="category-element">
                <a href="${pageContext.request.contextPath}/Controller?page=searchresult&command=go_to_tag_catalog&currentPage=1&tag=electronics" class="category-link"><img src="pic/чайник.png" alt="" class="category-image"></a>
                <span class="category-name">Electronics</span>
            </div>
            <div class="category-element">
                <a href="${pageContext.request.contextPath}/Controller?page=searchresult&command=go_to_tag_catalog&currentPage=1&tag=petsupplies" class="category-link"><img src="pic/чайник.png" alt="" class="category-image"></a>
                <span class="category-name">Pet supplies</span>
            </div>
            <div class="category-element">
                <a href="${pageContext.request.contextPath}/Controller?page=searchresult&command=go_to_tag_catalog&currentPage=1&tag=furniture" class="category-link"><img src="pic/чайник.png" alt="" class="category-image"></a>
                <span class="category-name">Furniture</span>
            </div>
            <div class="category-element">
                <a href="${pageContext.request.contextPath}/Controller?page=searchresult&command=go_to_tag_catalog&currentPage=1&tag=clothing" class="category-link"><img src="pic/чайник.png" alt="" class="category-image"></a>
                <span class="category-name">Clothing</span>
            </div>
            
        </div>
    </div>
    <a href="${pageContext.request.contextPath}/Controller?page=lotcreation&command=goToPage"></a>
    <%@ include file="footer.jsp" %>
</body>
</html>